import axios from 'axios';
// axios.defaults.baseURL = 'http://localhost:8080/api/v1';
// const token = localStorage.getItem('token');
// axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

export const getAllApi = async () => {
  const response = await axios.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  const response = await axios.post('/admins/feedbackcategories', formdate);
  return response.data;
};

export const updateApi = async formdate => {
  const response = await axios.put('/admins/feedbackcategories/' + formdate.id, formdate);
  return response.data;
};

export const deleteApi = async id => {
  const response = await axios.delete('/admins/feedbackcategories/' + id);
  return response.data;
};
