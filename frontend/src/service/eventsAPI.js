import apiClient from "../core/setup/axios";

export const getAllEventsApi = async (keyword) => {
  const response = await apiClient.get(`/events/search?name=${keyword}`);
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

  export const makeEventReservationApi = async (id) => {
    const response = await apiClient.post(`/students/events/${id}/reservation`, {});
    return response.data;
  };

  export const removeEventReservationApi = async (id) => {
    const response = await apiClient.delete(`/students/events/${id}/reservation`);
    return response.data;
  };

  export const getEventsAttendeesApi = async (id) => {
    const response = await apiClient.get(`/admins/events/${id}/attendees`);
    return response.data;
  };

  export const getStudentEventsApi = async () => {
    const response = await apiClient.get(`/students/events`);
    return response.data;
  };
