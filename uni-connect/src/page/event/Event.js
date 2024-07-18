import {apiFetchEvents} from "../../action/ApiActions";
import {useEffect, useState} from "react";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import {AsideRight} from "../../component/AsideRight";
import {AiOutlineArrowUp} from "react-icons/ai";
import {Link, useNavigate} from "react-router-dom";

function Event() {

    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(8);

    useEffect(() => {
        apiFetchEvents(itemsPerPage, currentPage)
            .then(res => setData(res.data?.content))
            .catch(error => console.error(error));
    }, [currentPage, itemsPerPage]);
    const navigate = useNavigate();


    const totalPages = Math.ceil(data.length / itemsPerPage);
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentItems = data.slice(startIndex, endIndex);

    return (
        <div>
            <MobileNavBar/>
            <div className="flex justify-center px-5 sm:px-32 md:mt-4">
                <div className="flex h-screen w-screen">
                    <AsideLeft/>
                    <main className="md:mx-4 w-full sm:basis-2/3">
                        ALL EVENTS
                        {currentItems.map(item => (
                            <div className="bg-white p-4 rounded-lg shadow-md mb-4" key={item.id}>
                                <h3 className="text-lg font-semibold mb-2">{item.name}</h3>
                                <p className="text-gray-600 mb-4">{item.description}</p>
                                <a key={item.id} className="text-blue-500 hover:underline">
                                    <Link to={`/events/${item.id}/details`}>See Details</Link>
                                </a>
                            </div>
                        ))}
                    </main>
                    <AsideRight/>
                    <a href="#">
                        <AiOutlineArrowUp
                            className="hidden sm:block fixed bottom-0 right-20 bg-blue-300 text-slate-50 text-5xl p-3 rounded-full mb-2 mr-20 hover:bg-blue-500"/>
                    </a>
                </div>
            </div>
        </div>
    );
}
export default Event;