import { useParams } from "react-router";
import { apiFetchResource } from "../../action/ApiActions";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import Resource from "./Resource";
import { toast } from "react-toastify";
import { useEffect, useState } from "react";
import { AiOutlineArrowUp } from "react-icons/ai";
import { AsideRight } from "../../component/AsideRight";

function ResourceDetail() {
    let [resource,setResource]=useState([]);
    let params=useParams();

    async function fetchResource() {
        const response = await apiFetchResource(params.id);
        if(response.status){
            setResource(response.data);
        }
        else{
            toast.error(response.message);
        }

    }

    useEffect(() => {
        fetchResource();
    }, []);
    return (
        <div>
        <MobileNavBar />

        <div className="flex justify-center px-5 sm:px-32 md:mt-4">
            <div className="flex h-screen w-screen">

                <AsideLeft />

                <main className="md:mx-4 w-full sm:basis-2/3">
                    <h1 className="text-3xl font-bold mb-6">Resources</h1>
                    <Resource key={resource.id} resource={resource} />
                </main>

                <AsideRight />
                <a href="#">
                    <AiOutlineArrowUp className="hidden sm:block fixed bottom-0 right-20 bg-blue-300 text-slate-50 text-5xl p-3 rounded-full mb-2 mr-20 hover:bg-blue-500" />
                </a>
            </div>
        </div>
    </div>

      );
}

export default ResourceDetail;