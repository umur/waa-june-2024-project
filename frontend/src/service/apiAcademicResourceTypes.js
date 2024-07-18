import axios from 'axios';

export const getAllApi = async () => {
  const response = await axios.get('/academicresources');
  return response.data;
};

export const getAllCategoriesApi = async () => {
  const response = await axios.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  const response = await axios.post('/academicresources', formdate);
  return response.data;
};

export const updateApi = async formdate => {
  const response = await axios.put('/academicresources/' + formdate.id, formdate);
  return response.data;
};

export const deleteApi = async id => {
  const response = await axios.delete('/academicresources/' + id);
  return response.data;
};
