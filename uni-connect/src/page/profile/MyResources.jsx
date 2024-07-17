import { useEffect, useRef, useState } from "react";
import { apiDeleteResource, apiFetchMyResources, apiFetchResources } from "../../action/ApiActions";
import { toast } from "react-toastify";
import InfiniteScroll from "react-infinite-scroll-component";
import Resource from "../resources/Resource";
import { useParams } from "react-router";
import DeleteModal from "../../component/DeleteModal";

function MyResources() {
    const hasFetchedData = useRef(false);
    const params = useParams();
    const [resources, setResources] = useState([]);
    const [page, setPage] = useState(0);
    const [hasMore, setHasMore] = useState(false);

    const fetchResources = async (currentPage = page) => {
        setPage((currentPage) => currentPage + 1);
        //TODO page logic
        const response = await apiFetchMyResources(params.userId,{ "size": 5, "page": currentPage+1});
        if (response.status) {
            setResources(prevItems => currentPage === 0 ? response.data.content : [...prevItems, ...response.data.content]);
            setHasMore(!response.data.last);
        } else {
            toast.error(response.message);
        }
    };

    const removeResource=(id)=>{
        setResources(prevItems=>prevItems.filter(x=>x.id!=id));
    }


    useEffect(() => {
        if (!hasFetchedData.current) {
            fetchResources();
            hasFetchedData.current = true;
        }
    }, [page]);
    return (
        <>
            <h1 className="text-3xl font-bold mb-6">My Resources</h1>
            <InfiniteScroll
                dataLength={resources.length}
                next={fetchResources}
                hasMore={hasMore}
                key={crypto.randomUUID()}
                loader={<h4>Loading...</h4>}
            >
                {resources.map((resource) => (
                    <>
                        <Resource key={resource.id} resource={resource} editable={false} deleteable={true} removeResource={removeResource} />
                    </>

                ))}
            </InfiniteScroll>
        </>
    );
}

export default MyResources;