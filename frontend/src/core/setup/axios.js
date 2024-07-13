import axios from "axios";

export default function setupAxios() {
  axios.defaults.baseURL = "http://localhost:8080/api/v1";
}
