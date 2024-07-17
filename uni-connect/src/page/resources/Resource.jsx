import { Link } from "react-router-dom";
import ResourceFile from "./ResourceFile";
import { apiDeleteResource, apiDownloadResource } from "../../action/ApiActions";
import { useEffect, useRef, useState } from "react";
import { toast } from "react-toastify";
import DeleteModal from "../../component/DeleteModal";

function Resource(props) {
    const [downloadUrl, setDownloadUrl] = useState(null);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [deletableId, setDeletableId] = useState(null);
    const [filename, setFilename] = useState("");
    const downloadLinkRef = useRef(null);
    function downloadFile(file) {
        setFilename(file);
        const response = apiDownloadResource({ filename: file }, props.resource.id);
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
                    <h3 className="text-lg font-semibold mb-2">{props.resource.title}</h3>
                    <p className="text-gray-600 mb-4">{props.resource.description}</p>
                </div>
                {props.editable ?
                    <button
                        className="ml-4 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
                        onClick={() => toggleDeleteModal(props.resource.id)}
                    >
                        Delete
                    </button> : <></>
                }
            </div>

            {
                !props.resource.files ? (
                    <Link to={`/resources/${props.resource.id}`} className="text-blue-500 hover:underline">
                        View
                    </Link>
                ) : (
                    <></>
                )
            }
            {
                props.resource.files ? (
                    <>
                        {
                            props.resource.files.map((file) => {
                                return (
                                    <div key={file}>
                                        <ResourceFile downloadFile={downloadFile} file={file} url={props.resource.url} />
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
        </div>

    );
}

export default Resource;