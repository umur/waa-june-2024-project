import apiClient from '../core/setup/axios';

export const getAllApi = async () => {
  const response = await apiClient.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  const response = await apiClient.post('/admins/feedbackcategories', formdate);
  return response.data;
};

export const updateApi = async formdate => {
  const response = await apiClient.put('/admins/feedbackcategories/' + formdate.id, formdate);
  return response.data;
};

export const deleteApi = async id => {
  const response = await apiClient.delete('/admins/feedbackcategories/' + id);
  return response.data;
};
