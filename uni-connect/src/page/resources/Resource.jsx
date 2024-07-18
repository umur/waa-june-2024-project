import { Link, useParams } from "react-router-dom";
import ResourceFile from "./ResourceFile";
import { apiDeleteResource, apiDownloadResource, apiSaveProfile, apiUpdateResource } from "../../action/ApiActions";
import { useEffect, useRef, useState } from "react";
import { toast } from "react-toastify";
import DeleteModal from "../../component/DeleteModal";
import ResourceFileUpload from "./ResourceFileUpload";
import ResourceFormModal from "./ResourceFormModal";

function Resource(props) {
    let params = useParams();

    let [resource,setResource]=useState(props.resource);

    const [editModalOpen, setEditModalOpen] = useState(false);
    const [editData, setEditData] = useState({});


    const openEditModal = () => {
        setEditModalOpen(true);
        setEditData(resource);
    };

    const closeEditModal = () => {
        setEditModalOpen(false);
        setEditData({});

    };

    const handleChange = (e) => {
        console.log(e,"sasaas")
        const { name, value } = e.target;
        console.log(editData)
        setEditData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
        console.log(editData)
    };

    const handleSave = (e) => {
        closeEditModal();
        updateResource();
        setResource({ ...editData });
        
    };

    async function updateResource() {
        const response = await apiUpdateResource(resource.id, editData);
        if (response.status) {
            toast.success(response.message);
        } else {
            toast.error(response.message);
        }
    }

    const [downloadUrl, setDownloadUrl] = useState(null);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [deletableId, setDeletableId] = useState(null);
    const [filename, setFilename] = useState("");
    const downloadLinkRef = useRef(null);

    function downloadFile(file) {
        setFilename(file);
        const response = apiDownloadResource({ filename: file }, resource.id);
        response.then(result => {
            const url = URL.createObjectURL(result);
            setDownloadUrl(url);
        }).catch(error => {
            console.error(error);
        });
    }

    useEffect(() => {
        if (downloadUrl) {
            downloadLinkRef.current.click();
        }
    }, [downloadUrl]);

    const toggleDeleteModal = (id) => {
        setDeletableId(!showDeleteModal ? id : null);
        setShowDeleteModal(!showDeleteModal);
    }

    const onFileUpload = (data) => {
        setResource(data);
    }


    const deleteResource = async () => {
        setShowDeleteModal(false);
        const response = await apiDeleteResource(deletableId);
        if (response.status) {
            toast.success(response.message);
            props.removeResource(deletableId);
        } else {
            toast.error(response.message);
        }
    }



    return (
        <div className="bg-white p-4 rounded-lg shadow-md mb-4">
            <div className="flex justify-between items-center">
                <div>
                    <h3 className="text-lg font-semibold mb-2">{resource.title}</h3>
                    <p className="text-gray-600 mb-4">{resource.description}</p>
                </div>
                {params.userId ?
                    <>
                        {props.deleteable ?
                            <button
                                className="ml-4 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
                                onClick={() => toggleDeleteModal(resource.id)}
                            >
                                Delete
                            </button> : <></>
                        }
                        {props.editable ?
                            <button
                                className="ml-4 px-4 py-2 bg-green-500 text-white rounded hover:bg-red-600"
                                onClick={() => openEditModal(resource.id)}
                            >
                                Edit
                            </button> : <></>
                        }
                    </>
                    : <></>
                }
            </div>

            {
                !resource.files ? (
                    <Link to={params.userId ? `/users/${params.userId}/resources/${resource.id}` : `/resources/${resource.id}`} className="text-blue-500 hover:underline">
                        View
                    </Link>
                ) : (
                    <></>
                )
            }
            {
                resource.files ? (
                    <>
                        {
                            resource.files.map((file) => {
                                return (
                                    <div key={file}>
                                        <ResourceFile downloadFile={downloadFile} file={file} url={resource.url} />
                                    </div>
                                )
                            })
                        }
                        <a href={downloadUrl} ref={downloadLinkRef} download={filename} style={{ display: 'none' }}>Download</a>
                    </>
                ) : (
                    <></>
                )
            }

            {showDeleteModal ?
                <DeleteModal item="resource" toggleModal={toggleDeleteModal} onConfirm={deleteResource} /> : <></>}
            {params.userId && props.editable ?
                <ResourceFileUpload resource={resource} onFileUpload={onFileUpload} /> : <></>
            }
            {editModalOpen ?
                <ResourceFormModal data={editData}
                                   closeModal={closeEditModal}
                                   handleChange={handleChange}
                                   handleSave={handleSave}
                />
                : <></>}
        </div>

    );
}

export default Resource;