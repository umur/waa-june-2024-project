import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080/api/v1";
const token = localStorage.getItem("token");

axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

export const getAllApi = async () => {
  const response = await axios.get("/academic");
  return response.data;
};

export const getAllCategoriesApi = async () => {
  const response = await axios.get("/academicresources");
  return response.data;
};

export const saveApi = async dataForm => {
  const formData = new FormData();

  formData.append(
    "formdata",
    new Blob(
      [
        JSON.stringify({
          name: dataForm.name,
          body: dataForm.body,
          resourceId: dataForm.resourceCategory
        })
      ],
      {type: "application/json"}
    )
  );

  if (dataForm.file) {
    formData.append("file", dataForm.file);
  }

  // Ensure no conflicting headers are set
  const response = await axios.post("/academic", formData, {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });

  return response.data;
};

export const updateApi = async dataForm => {
  const formData = new FormData();
  formData.append(
    "formdata",
    new Blob(
      [
        JSON.stringify({
          id: dataForm.id,
          name: dataForm.name,
          body: dataForm.body,
          resourceId: dataForm.resourceCategory
        })
      ],
      {type: "application/json"}
    )
  );

  if (dataForm.file) {
    formData.append("file", dataForm.file);
  }

  const response = await axios.put("/academic/" + dataForm.id, formData, {
    headers: {
      "Content-Type": "multipart/form-data"
    }
  });

  return response.data;
};

export const deleteApi = async id => {
  const response = await axios.delete("/academic/" + id);
  return response.data;
};
