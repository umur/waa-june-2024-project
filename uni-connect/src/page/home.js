import { useEffect, useState } from "react";
import { apiFetchUsers } from "../action/ApiActions";
import { Link } from "react-router-dom";

function Home() {

    let [vari,setVari]=useState(1);
    const userId=1;

    async function  fetchUsers(){
        const response=await apiFetchUsers();
        
    }

    useEffect(() => {
        fetchUsers();
    }, [vari]);

    return (
        <div>
            Search bar to search and filter posts

            This below could be side bar
            1.Resource Library //Student find/manage
            2. Events //find/rsvp
            3.Students //Student find/view profile
            4. Surveys
            <Link to={`/users/${userId}/profile`}>Profile</Link> 
            <h1 onClick={()=>{setVari(vari+1)}}> Home Page</h1>
            
        </div>
    );
}

export default Home;