import axios from 'axios';
import apiClient from '../core/setup/axios';

export const getAllApi = async () => {
  const response = await apiClient.get('/academicresources');
  return response.data;
};

export const getAllCategoriesApi = async () => {
  const response = await apiClient.get('/feedbackcategories');
  return response.data;
};

export const saveApi = async formdate => {
  const response = await apiClient.post('/academicresources', formdate);
  return response.data;
};

export const updateApi = async formdate => {
  const response = await apiClient.put('/academicresources/' + formdate.id, formdate);
  return response.data;
};

export const deleteApi = async id => {
  const response = await apiClient.delete('/academicresources/' + id);
  return response.data;
};
