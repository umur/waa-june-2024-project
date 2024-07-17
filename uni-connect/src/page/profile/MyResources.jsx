import { useEffect, useRef, useState } from "react";
import { apiFetchMyResources, apiFetchResources } from "../../action/ApiActions";
import { toast } from "react-toastify";
import InfiniteScroll from "react-infinite-scroll-component";
import Resource from "../resources/Resource";
import { useParams } from "react-router";

function MyResources() {
    const hasFetchedData = useRef(false);
    const params = useParams();
    const [resources, setResources] = useState([]);
    const [page, setPage] = useState(0);
    const [keyword, setKeyword] = useState("");
    const [hasMore, setHasMore] = useState(false);

    const fetchResources = async (key = keyword, currentPage = page) => {
        setPage((currentPage) => currentPage + 1);
        const response = await apiFetchMyResources(params.id);
        if (response.status) {
            setResources(prevItems => currentPage === 0 ? response.data.content : [...prevItems, ...response.data.content]);
            setHasMore(!response.data.last);
        } else {
            toast.error(response.message);
        }
    };

    useEffect(() => {
        if (!hasFetchedData.current) {
            fetchResources("");
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
                        <Resource key={resource.id} resource={resource} />
                    </>

                ))}
            </InfiniteScroll>
        </>
    );
}

export default MyResources;