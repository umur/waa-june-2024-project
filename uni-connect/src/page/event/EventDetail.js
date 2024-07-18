import {useParams} from "react-router";
import {useEffect, useState} from "react";
import axios from "axios";
import {apiFetchEventAttendees, apiFetchEventById} from "../../action/ApiActions";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import {Link} from "react-router-dom";
import {AsideRight} from "../../component/AsideRight";
import {AiOutlineArrowUp} from "react-icons/ai";

function EventDetail() {
    const {id} = useParams();
    const [data, setData] = useState({});
    const [attendees, setAttendees] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(2);
    const [loading, setLoading] = useState(true);

    const fetchEventAttends = async () => {
        const res = await apiFetchEventAttendees(id, itemsPerPage, currentPage);
        setAttendees(res.data?.content);
        setLoading(false);
    }

    useEffect(() => {
        apiFetchEventById(id)
            .then(res => setData(res.data))
            .catch(e => console.log(e))
        fetchEventAttends();
    }, [id,currentPage, itemsPerPage]);

    const totalPages = Math.ceil(data.length / itemsPerPage);
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentItems = attendees.slice(startIndex, endIndex);

    if (loading) {
        return <div>Loading...</div>;
    }

    return <div>
        <MobileNavBar/>
        <div className="flex justify-center px-5 sm:px-32 md:mt-4">
            <div className="flex h-screen w-screen">
                <AsideLeft/>
                <main className="md:mx-4 w-full sm:basis-2/3">
                    <div className="bg-white p-4 rounded-lg shadow-md mb-4" key={data.id}>
                        <h3 className="text-lg font-semibold mb-2">{data.name}</h3>
                        <p className="text-gray-600 mb-4">{data.description}</p>
                        <p className="text-gray-600 mb-4">Place: {data.location}</p>
                        <p className="text-gray-600 mb-4">Date: {data.date}</p>
                    </div>
                    <div className="bg-white p-4 rounded-lg shadow-md mb-4" key={data.id}>
                        <h3 className="text-lg font-semibold mb-2">Event participants</h3>
                          {currentItems?.map(item => {
                             <p className="text-gray-600 mb-4">{item.id}</p>})}
                    </div>
                    <div>
                    <button>Add new participant</button>
                    </div>
                </main>
                <AsideRight/>
                <a href="#">
                    <AiOutlineArrowUp
                        className="hidden sm:block fixed bottom-0 right-20 bg-blue-300 text-slate-50 text-5xl p-3 rounded-full mb-2 mr-20 hover:bg-blue-500"/>
                </a>
            </div>
        </div>
    </div>
}

export default EventDetail;