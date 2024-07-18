import {useParams} from "react-router";
import {lazy, Suspense, useEffect, useState} from "react";
import axios from "axios";
import {apiFetchEventAttendees, apiFetchEventById} from "../../action/ApiActions";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import {AsideRight} from "../../component/AsideRight";
import {AiOutlineArrowUp} from "react-icons/ai";

const AttendeeList =
    lazy(() => import('./Attendees'));

function EventDetail() {
    const {id} = useParams();
    const [data, setData] = useState({});
    const [attendees, setAttendees] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(10);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        apiFetchEventById(id)
            .then(res => setData(res.data))
            .catch(e => console.log(e))
        apiFetchEventAttendees(id, itemsPerPage, currentPage)
            .then(res => {
                setAttendees(res.data.content)
                setLoading(false);
            })
            .catch(e => console.log(e))
    }, [id,currentPage, itemsPerPage]);

    const addParticipant = async () => {
    //TODO:
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
                    <div className="bg-white p-4 rounded-lg shadow-md mb-4">
                        {loading? <div></div> :
                            <Suspense fallback={<p>Loading component...</p>}>
                                <AttendeeList data={attendees} />
                            </Suspense>}
                    </div>
                    <div>
                        <button onClick={addParticipant} class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                            Add new participant
                        </button>
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