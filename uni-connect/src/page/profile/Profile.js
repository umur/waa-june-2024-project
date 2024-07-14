import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { apiFetchProfile } from "../../action/ApiActions";

function Profile() {
    const params=useParams();

    const [profile,setProfile]=useState({achievements:[],interests:[],extraCurricularActivities:[]});

    useEffect(()=>{
        fetchProfile();
    },[params.id]);

    async function fetchProfile(){
        const response=await apiFetchProfile(params.id);
        if (response.status) {
            setProfile(response.data);
        }
        else {
            alert(response.message);
        }
    }

    return (
        <div>
      <h2>Achievements</h2>
      {profile.achievements.map((achievement, index) => (
        <div key={index}>
          <p>University: {achievement.university}</p>
          <p>Majors: {achievement.majors}</p>
          <p>Level: {achievement.level}</p>
          <p>Score: {achievement.score}</p>
        </div>
      ))}
      
      <h2>Interests</h2>
      {profile.interests.map((interest, index) => (
        <div key={index}>
          <p>Interest: {interest.interest}</p>
        </div>
      ))}

      <h2>Extra-Curricular Activities</h2>
      {profile.extraCurricularActivities.map((activity, index) => (
        <div key={index}>
          <p>Activity: {activity.activity}</p>
          <p>Institute: {activity.institute}</p>
          <p>Accomplishment: {activity.accomplishment}</p>
        </div>
      ))}
    </div>
    
    );
}

export default Profile;