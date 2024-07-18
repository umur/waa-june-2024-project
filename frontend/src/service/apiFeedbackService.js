import apiClient from '../core/setup/axios';

export const getAllApi = async () => {
  const response = await apiClient.get('/feedbacks');
  return response.data;
};

export const getAllCategoriesApi = async () => {
  const response = await apiClient.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  console.log(formdate);

  const postData = {
    title: formdate.title,
    body: formdate.body,
    feedbackCategory: formdate.feedbackCategory
  };
  const response = await apiClient.post('/feedbacks', postData);
  return response.data;
};

export const updateApi = async formdate => {
  const postData = {
    title: formdate.title,
    body: formdate.body,
    feedbackCategory: formdate.feedbackCategory
  };
  const response = await apiClient.put('/feedbacks/' + formdate.id, postData);
  return response.data;
};

export const deleteApi = async id => {
  const response = await apiClient.delete('/feedbacks/' + id);
  return response.data;
};
