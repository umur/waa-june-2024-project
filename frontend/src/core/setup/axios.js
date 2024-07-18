import axios from 'axios';
import {getTokens, setTokens} from './token';

// Create an Axios instance
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  }
});

// Function to get access token from local storage
const getAccessToken = () => getTokens().accessToken;

// Function to get refresh token from local storage
const getRefreshToken = () => getTokens().refreshToken;

// Function to set new access token in local storage
const setAccessToken = accessToken => {
  setTokens(accessToken, getRefreshToken());
};

// Request interceptor to add the access token to headers
apiClient.interceptors.request.use(
  config => {
    const token = getAccessToken();
    if (token !== '' && token != null && token.length > 0) {
      config.headers['Authorization'] = `Bearer ${token}`;
    } else {
      config.headers['Authorization'] = null;
    }
    return config;
  },
  error => Promise.reject(error)
);

// Response interceptor to handle 401 errors and token refresh
apiClient.interceptors.response.use(
  response => response,
  async error => {
    const originalRequest = error.config;

    const refreshToken = getRefreshToken();

    if (refreshToken === '' || refreshToken === null) {
      originalRequest._retry = true;
    }

    if (error.response.status === 401) {
      if (!originalRequest._retry) {
        originalRequest._retry = true;

        try {
          const response = await apiClient.post('/auth/token/refresh', {refreshToken: refreshToken});

          if (response.status === 200) {
            const newToken = response.data.accessToken;
            setAccessToken(newToken);
            originalRequest.headers['Authorization'] = `Bearer ${newToken}`;

            return apiClient(originalRequest);
          }
        } catch (refreshError) {
          setTokens();

          return Promise.reject(refreshError);
        }
      } else {
        setTokens();
      }
    }

    return Promise.reject(error);
  }
);

export default apiClient;
