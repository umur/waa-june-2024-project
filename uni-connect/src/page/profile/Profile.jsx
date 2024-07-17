import {  useState } from "react";
import MobileNavBar from "../../component/MobileNavBar";
import AsideLeft from "../../component/AsideLeft";
import { AsideRight } from "../../component/AsideRight";
import { AiOutlineArrowUp } from "react-icons/ai";
import { EventsIcon, ProfileIcon, ResourcesIcon, SurveysIcon } from "../../component/Icons";
import MyDetails from "./MyDetails";

function Profile() {

  const tabs = [
    { name: 'My Details', icon: ProfileIcon },
    { name: 'Resources', icon: ResourcesIcon },
    { name: 'Events', icon: EventsIcon },
    { name: 'Surveys', icon: SurveysIcon },
  ];

  const [activeTab, setActiveTab] = useState('Profile');

  return (
    <div>

      <MobileNavBar />

      <div className="flex justify-center px-5 sm:px-32 md:mt-4">
        <div className="flex h-screen w-screen">
          <AsideLeft />

          <main className="md:mx-4 w-full sm:basis-2/3">

            <div>
              <div className="border-b border-gray-200 dark:border-gray-700">
                <ul className="flex flex-wrap -mb-px text-sm font-medium text-center text-gray-500 dark:text-gray-400">
                  {tabs.map((tab) => (
                    <li key={tab.name} className="me-2">
                      <a
                        href="#"
                        className={`inline-flex items-center justify-center p-4 border-b-2 rounded-t-lg ${activeTab === tab.name
                          ? 'text-blue-600 border-blue-600 dark:text-blue-500 dark:border-blue-500'
                          : 'border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300'
                          }`}
                        onClick={(e) => {
                          e.preventDefault();
                          setActiveTab(tab.name);
                        }}
                      >
                        <tab.icon />
                        {tab.name}
                      </a>
                    </li>
                  ))}
                </ul>
              </div>
              <div className="p-4">
                {activeTab === 'My Details' && <MyDetails />}
                {/* {activeTab === 'Resources' && <ResourcesContent />}
        {activeTab === 'Events' && <EventsContent />}
        {activeTab === 'Surveys' && <SurveysContent />}  */}
              </div>
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
