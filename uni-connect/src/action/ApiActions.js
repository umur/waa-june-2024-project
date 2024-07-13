import axios from "axios";
import ApiRoutes from "../constant/ApiRoutes";
import {getErrorResponseObject} from "../util/RequestFormUtil";
import axiosInstance from "./AxiosInstance";


export async function apiLogin(data) {
    try {
        const response = await axiosInstance.post(ApiRoutes.signin, data);
        console.log("apiLogin response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiLogin",e);
    }
}

export async function apiSignUp(data) {
    try {
        const response = await axiosInstance.post(ApiRoutes.signup, data);
        console.log("apiSignUp response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiSignUp",e);
    }
}

export async function apiFetchUsers() {
    try {
        const response = await axiosInstance.get(ApiRoutes.users);
        console.log("apiFetchUsers response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchUsers",e);
    }
}

export async function apiFetchProfile(id) {
    try {
        const profileUrl = ApiRoutes.profile(id);
        const response = await axiosInstance.get(profileUrl);
        console.log("apiFetchProfile response", response);
        return response.data;
    } catch (e) {
        return exceptionResponse("apiFetchProfile",e);
    }
}

export function exceptionResponse(apiName,e){
    console.log(apiName+" exception", e?.response?.data?? e.message);
    return e?.response?.data ?? getErrorResponseObject(e.message);
}
