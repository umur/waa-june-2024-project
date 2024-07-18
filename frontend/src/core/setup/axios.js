import axios from 'axios';
import {getTokens} from './token';

export default function setupAxios() {
  axios.defaults.baseURL = 'http://localhost:8080/api/v1';
  axios.defaults.headers.common['Authorization'] = `Bearer ${getTokens().accessToken}`;
}
