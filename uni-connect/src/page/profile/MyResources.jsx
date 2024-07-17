import { useEffect, useRef, useState } from "react";
import {
    apiCreateResource,
    apiFetchMyResources,
} from "../../action/ApiActions";
import { toast } from "react-toastify";
import InfiniteScroll from "react-infinite-scroll-component";
import Resource from "../resources/Resource";
import { useParams } from "react-router";
import ResourceFormModal from "../resources/ResourceFormModal";

function MyResources() {
    const hasFetchedData = useRef(false);
    const params = useParams();
    const [resources, setResources] = useState([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(false);

    const [formModalOpen, setFormModalOpen] = useState(false);
    const [requestData, setRequestData] = useState({ title: "", description: "" });

    const openFormModal = () => {
        setFormModalOpen(true);
        setRequestData({ title: "", description: "" });
    };

    const closeFormModal = () => {
        setFormModalOpen(false);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setRequestData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const addResource = async () => {
        const response = await apiCreateResource(requestData);
        if (response.status) {
            toast.success(response.message);
        } else {
            toast.error(response.message);
        }
    }

    const handleSave = (e) => {
        closeFormModal();
        addResource();

    };

    const fetchResources = async (currentPage = page) => {
        setPage((currentPage) => currentPage + 1);
        //TODO page logic
        const response = await apiFetchMyResources(params.userId, { "size": 5, "page": currentPage + 1 });
        if (response.status) {
            setResources(prevItems => currentPage === 0 ? response.data.content : [...prevItems, ...response.data.content]);
            setHasMore(!response.data.last);
        } else {
            toast.error(response.message);
        }
    };

    const removeResource = (id) => {
        setResources(prevItems => prevItems.filter(x => x.id != id));
    }


    useEffect(() => {
        if (!hasFetchedData.current) {
            fetchResources();
            hasFetchedData.current = true;
        }
    }, [page]);
    return (
        <>
            <div className="relative mb-6">
                <h1 className="text-3xl font-bold">My Resources</h1>
                <button
                    onClick={openFormModal}
                    className="absolute top-0 right-0 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                >
                    Add Resource
                </button>
            </div>
            <InfiniteScroll
                dataLength={resources.length}
                next={fetchResources}
                hasMore={hasMore}
                key={crypto.randomUUID()}
                loader={<h4>Loading...</h4>}
            >
                {resources.map((resource) => (
                    <Resource
                        key={resource.id}
                        resource={resource}
                        editable={false}
                        deleteable={true}
                        removeResource={removeResource}
                    />
                ))}
            </InfiniteScroll>
            {formModalOpen ?
                <ResourceFormModal data={requestData}
                    closeModal={closeFormModal}
                    handleChange={handleChange}
                    handleSave={handleSave}

                />
                : <></>
            }
        </>




    );
}

export default MyResources;