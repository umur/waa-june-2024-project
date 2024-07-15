import { useEffect, useState } from "react";
import { apiFetchUsers } from "../../action/ApiActions";
import AsideLeft from "../../component/AsideLeft";
import MobileNavBar from "../../component/MobileNavBar";
import { AsideRight } from "../../component/AsideRight";
import { AiOutlineArrowUp } from "react-icons/ai";

function Resource() {

    let [sampleVar, setSampleVar] = useState(1);
    const userId = 1;

    async function fetchUsers() {
        const response = await apiFetchUsers();

    }

    useEffect(() => {
        fetchUsers();
    }, [sampleVar]);

    return (
        <div>
            <MobileNavBar />

            <div className="flex justify-center px-5 sm:px-32 md:mt-4">
                <div className="flex h-screen w-screen">

                    <AsideLeft />
                    

                    <main className="md:mx-4 w-full sm:basis-2/3">

                        This is the resource Section
                        <div className="bg-white p-4 rounded-lg shadow-md mb-4">
                            <h3 className="text-lg font-semibold mb-2">Title</h3>
                            <p className="text-gray-600 mb-4">Description</p>
                            <a href="link" className="text-blue-500 hover:underline">
                                Download
                            </a>
                        </div>
                        <div className="bg-white p-4 rounded-lg shadow-md mb-4">
                            <h3 className="text-lg font-semibold mb-2">Title</h3>
                            <p className="text-gray-600 mb-4">Description</p>
                            <a href="link" className="text-blue-500 hover:underline">
                                Download
                            </a>
                        </div>

                    </main>

                    <AsideRight />
                    <a href="#">
                        <AiOutlineArrowUp className="hidden sm:block fixed bottom-0 right-20 bg-blue-300 text-slate-50 text-5xl p-3 rounded-full mb-2 mr-20 hover:bg-blue-500" />
                    </a>
                </div>
            </div>
        </div>

        // </div>
    );
}

export default Resource;