import axios from "axios";
import ApiRoutes from "../constant/ApiRoutes";
import { getErrorResponseObject } from "../util/RequestFormUtil";
import axiosInstance from "./AxiosInstance";


export async function apiLogin(data) {
    try {
        const response = await axiosInstance.post(ApiRoutes.signin, data);
        console.log("apiLogin response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiLogin", e);
    }
}

export async function apiSignUp(data) {
    try {
        const response = await axiosInstance.post(ApiRoutes.signup, data);
        console.log("apiSignUp response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiSignUp", e);
    }
}

export async function apiFetchEvents(size, page) {
    try {
        const response = await axiosInstance.get(`${ApiRoutes.events}?size=${size}&page=${page}`);
        console.log("apiFetchEvents response", response.data);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchEvents", e);
    }
}

export async function apiFetchEventById(id){
    try {
        const response
            = await axiosInstance.get(`${ApiRoutes.events}/${id}`);
        console.log("apiFetchEventById response", response.data);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchEvents", e);
    }
}

export async function apiFetchEventAttendees(id, size,page){
    try {
        const response
            = await axiosInstance.get(`${ApiRoutes.events}/${id}/attendees?size=${size}&page=${page}`);
        console.log("apiFetchEventAttendees response", response.data);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchEvents", e);
    }
}

export async function apiFetchUsers() {
    try {
        const response = await axiosInstance.get(ApiRoutes.users);
        console.log("apiFetchUsers response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchUsers", e);
    }
}

export async function apiFetchProfile(id) {
    try {
        const profileUrl = ApiRoutes.profile(id);
        const response = await axiosInstance.get(profileUrl);
        console.log("apiFetchProfile response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchProfile", e);
    }
}

export async function apiSaveProfile(id,data) {
    try {
        const profileUrl = ApiRoutes.profile(id);
        const response = await axiosInstance.post(profileUrl,data);
        console.log("apiSaveProfile response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiSaveProfile", e);
    }
}

export async function apiFetchResources(queryParams) {
    try {
        const response = await axiosInstance.get(ApiRoutes.resources, { params: queryParams });
        console.log("apiFetchResources response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchResources", e);
    }
}

export async function apiFetchResource(id) {
    try {
        const resourceUrl = ApiRoutes.resource(id);
        const response = await axiosInstance.get(resourceUrl);
        console.log("apiFetchResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchResource", e);
    }
}

export async function apiDownloadResource(data, id) {
    try {
        const resourceUrl = ApiRoutes.resourceDownload(id);
        const response = await axiosInstance.post(resourceUrl, data, { responseType: 'blob' });
        console.log("apiDownloadResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiDownloadResource", e);
    }
}

export async function apiUploadResource(data, id) {
    try {
        const resourceUrl = ApiRoutes.resource(id);
        const response = await axiosInstance.post(resourceUrl, data, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log("apiUploadResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiUploadResource", e);
    }
}

export async function apiFetchMyResources(userId,queryParams) {
    try {
        const myResourcesUrl = ApiRoutes.myResources(userId);
        const response = await axiosInstance.get(myResourcesUrl, { params: queryParams });
        console.log("apiFetchMyResources response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchMyResources", e);
    }
}

export async function apiDeleteResource(id) {
    try {
        const resourceUrl = ApiRoutes.resource(id);
        const response = await axiosInstance.delete(resourceUrl);
        console.log("apiDeleteResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiDeleteResource", e);
    }
}

export async function apiCreateResource(data) {
    try {
        const resourceUrl = ApiRoutes.resources;
        const response = await axiosInstance.post(resourceUrl,data);
        console.log("apiCreateResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiCreateResource", e);
    }
}

export async function apiUpdateResource(id,data) {
    try {
        const resourceUrl = ApiRoutes.resource(id);
        const response = await axiosInstance.put(resourceUrl,data);
        console.log("apiUpdateResource response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiUpdateResource", e);
    }
}

export function exceptionResponse(apiName, e) {
    console.log(apiName + " exception", e?.response?.data ?? e.message);
    return e?.response?.data ?? getErrorResponseObject(e.message);
}