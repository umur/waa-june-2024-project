import apiClient from "../core/setup/axios";

export const getAllEventsApi = async () => {
  const response = await apiClient.get("/events");
  return response.data;
};

export const createEventApi = async (data) => {
    const response = await apiClient.post("/admins/events", data);
    return response.data;
  };

  export const getEventApi = async (id) => {
    const response = await apiClient.get(`/events/${id}`);
    return response.data;
  };

  export const deleteEventApi = async (id) => {
    const response = await apiClient.delete(`/admins/events/${id}`);
    return response.data;
  };

  export const updateEventApi = async (data) => {
    const response = await apiClient.put(`/admins/events/${data.id}`, data);
    return response.data;
  };