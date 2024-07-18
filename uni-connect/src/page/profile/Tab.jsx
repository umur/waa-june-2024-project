
function Tabs({children,activeTab,onTabChange}) {

    const tabs = [
        { name: 'My Details', icon: ProfileIcon },
        { name: 'Resources', icon: ResourcesIcon },
        { name: 'Events', icon: EventsIcon },
        { name: 'Surveys', icon: SurveysIcon },
    ];

    return (
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
                                    onTabChange(tab.name);
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
                {children}
            </div>
        </div>

    );
}

export default Tabs;