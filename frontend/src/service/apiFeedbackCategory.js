import axios from 'axios';

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
