import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { apiFetchProfile } from "../../action/ApiActions";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import { AsideRight } from "../../component/AsideRight";
import { AiOutlineArrowUp } from "react-icons/ai";

function Profile() {
  const params = useParams();

  const [profile, setProfile] = useState({ achievements: [], interests: [], extraCurricularActivities: [] });

  useEffect(() => {
    fetchProfile();
  }, [params.id]);

  async function fetchProfile() {
    const response = await apiFetchProfile(params.id);
    if (response.status) {
      setProfile(response.data);
    }
    else {
      alert(response.message);
    }
  }

  return (
    <div>
      <MobileNavBar />

      <div className="flex justify-center px-5 sm:px-32 md:mt-4">
        <div className="flex h-screen w-screen">

          <AsideLeft />

          <main className="md:mx-4 w-full sm:basis-2/3">

            <div className="p-4">
              <h2 className="text-2xl font-bold mb-4">Achievements</h2>
              {profile.achievements.map((achievement, index) => (
                <div key={index} className="mb-4 p-4 border border-gray-200 rounded-lg shadow-sm">
                  <p className="text-lg font-semibold">University: <span className="font-normal">{achievement.university}</span></p>
                  <p className="text-lg font-semibold">Majors: <span className="font-normal">{achievement.majors}</span></p>
                  <p className="text-lg font-semibold">Level: <span className="font-normal">{achievement.level}</span></p>
                  <p className="text-lg font-semibold">Score: <span className="font-normal">{achievement.score}</span></p>
                </div>
              ))}

              <h2 className="text-2xl font-bold mt-8 mb-4">Interests</h2>
              {profile.interests.map((interest, index) => (
                <div key={index} className="mb-4 p-4 border border-gray-200 rounded-lg shadow-sm">
                  <p className="text-lg font-semibold">Interest: <span className="font-normal">{interest.interest}</span></p>
                </div>
              ))}

              <h2 className="text-2xl font-bold mt-8 mb-4">Extra-Curricular Activities</h2>
              {profile.extraCurricularActivities.map((activity, index) => (
                <div key={index} className="mb-4 p-4 border border-gray-200 rounded-lg shadow-sm">
                  <p className="text-lg font-semibold">Activity: <span className="font-normal">{activity.activity}</span></p>
                  <p className="text-lg font-semibold">Institute: <span className="font-normal">{activity.institute}</span></p>
                  <p className="text-lg font-semibold">Accomplishment: <span className="font-normal">{activity.accomplishment}</span></p>
                </div>
              ))}
            </div>

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

export default Profile;