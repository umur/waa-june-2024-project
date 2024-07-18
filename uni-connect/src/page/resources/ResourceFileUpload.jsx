import { useState } from "react";
import { toast } from "react-toastify";
import {  apiUploadResource } from "../../action/ApiActions";

function ResourceFileUpload(props) {

    let resource = props.resource;

    const [selectedFiles, setSelectedFiles] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFiles(event.target.files);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!selectedFiles) {
            toast.error('No files selected');
            return;
        }
        const formData = new FormData();
        for (let i = 0; i < selectedFiles.length; i++) {
            formData.append('files', selectedFiles[i]);
        }
        const response = await apiUploadResource(formData, resource.id);
        if(response.status){
            toast.success(response.message);
            props.onFileUpload(response.data);
        }
        else{
            toast.error(response.message);
        }
    };

    return (
        <div className="p-4 mt-4 border border-gray-300 rounded-lg bg-white shadow-sm">
            <form onSubmit={handleSubmit}>
                <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white" htmlFor="file_input">
                    Add Resource Files
                </label>
                <input
                    className="block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
                    id="file_input"
                    type="file"
                    multiple
                    onChange={handleFileChange}
                />
                <button
                    type="submit"
                    className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                >
                    Upload added files
                </button>
            </form>
        </div>

    );
}

export default ResourceFileUpload;
