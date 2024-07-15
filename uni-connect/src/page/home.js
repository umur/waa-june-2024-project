import { useEffect, useState } from "react";
import { apiFetchUsers } from "../action/ApiActions";
import AsideLeft from "../component/AsideLeft";
import MobileNavBar from "../component/MobileNavBar";
import { AsideRight } from "../component/AsideRight";
import { AiOutlineArrowUp } from "react-icons/ai";

function Home() {

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
                        <header className="mb-4 text-center">
                            <h1 className="text-3xl font-bold text-black">Home</h1>
                        </header>
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

export default Home;