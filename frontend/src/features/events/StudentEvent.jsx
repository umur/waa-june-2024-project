import React from 'react';
import { MdRsvp } from "react-icons/md";
import { MdEventAvailable } from "react-icons/md";
import {Card, Button} from 'react-bootstrap';
import { FaRegThumbsDown, FaRegThumbsUp } from "react-icons/fa";
import { IoLocationOutline } from "react-icons/io5";

function StudentEvent({event, onHandleMakeEventReservation, onHandleRemoveEventReservation, isRsvped, userEvent}) {
    return (
        <>
        <Card style={{ width: '25rem' }} className="my-4 mx-3">
        <Card.Body>
        <Card.Title className='fs-2'>
          <MdEventAvailable size={60} color='green'/>
            {new Date(event.eventDate).toString("MMMM").split(" ")[1]} {" "}
          <span className='fs-5'>{new Date(event.eventDate).toString("MMMM").split(" ")[2]}</span>
          </Card.Title>
          <Card.Text className='fs-4 mt-4'>{event.name}</Card.Text>
          <Card.Text className='fs-5  mt-4'>
          <IoLocationOutline color='purple' size={20}/>
            {event.location}, {" "}
            <span className='fs-6'>
            {event.eventTime.slice(0, -3)}
            {event.eventTime.slice(0, -3) < '12' ? "AM" : "PM"} {" "}
            </span>
          </Card.Text>
      
          <Card.Text>
            {event.description}
          </Card.Text>
          <hr style={{margin: '0.3rem 0'}}/>
            { !userEvent && (
            isRsvped ?
          <Button variant="link" onClick={() =>  onHandleRemoveEventReservation(event.id)}>  <MdRsvp color='green' size={40}/>
          <FaRegThumbsUp  color='green' size={20}/>
           </Button> :
           <Button variant="link" onClick={() => onHandleMakeEventReservation(event.id)}>
           <MdRsvp color='orange' size={40}/>
           <FaRegThumbsDown color='orange' size={20}/>
           </Button>
           )}
        </Card.Body>
      </Card>
       </>
    )
}

export default StudentEvent
