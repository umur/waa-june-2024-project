import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/api/v1';
const token = localStorage.getItem('token');

axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

export const getAllApi = async () => {
  const response = await axios.get('/feedbacks');
  return response.data;
};

export const getAllCategoriesApi = async () => {
  const response = await axios.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  const postData = {
    title: formdate.title,
    body: formdate.body,
    feedbackCategory: formdate.feedbackCategory
  };
  const response = await axios.post('/feedbacks', postData);
  return response.data;
};

export const updateApi = async formdate => {
  const postData = {
    title: formdate.title,
    body: formdate.body,
    feedbackCategory: formdate.feedbackCategory
  };
  const response = await axios.put('/feedbacks/' + formdate.id, postData);
  return response.data;
};

export const deleteApi = async id => {
  const response = await axios.delete('/feedbacks/' + id);
  return response.data;
};
