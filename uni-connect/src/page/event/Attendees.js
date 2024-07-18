import React from 'react';

const AttendeeList = ({data}) => {
    console.log("data ==>", data);
    const listItems = [];
    for (let i = 0; i < data.length; i++) {
        listItems.push(
            <p className="text-gray-600 mb-4" key={i}>
            {data[i].username}</p>);
    }
    return (
        <div>
            <h3 className="text-lg font-semibold mb-2">Event participants</h3>
            <div>
                {listItems}
            </div>
        </div>
    );
};
export default AttendeeList;