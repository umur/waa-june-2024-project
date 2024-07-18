import React from 'react';
import { Card, Stack} from 'react-bootstrap';
import { MdDeleteForever, MdEventAvailable } from "react-icons/md";
import { FaRegEdit } from "react-icons/fa";
import { IoLocationOutline } from "react-icons/io5";

function EventCard({event, onShowModal, onDelete, onShowDetails, onShowAttendees}) {
    return (
      <>
        <Card style={{ width: '20rem' }} className="my-4 mx-3">
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
          <Stack gap={1}>
          <hr style={{margin: '0.3rem 0'}}/>
          <Stack direction="horizontal" className='justify-content-between'>
          <Card.Link href="#"><FaRegEdit color='green' size={20} onClick={() => onShowModal(event, 'Edit')}/></Card.Link>
          <Card.Link href="#"><MdDeleteForever color='red' size={25} onClick={() => onDelete(event.id)}/></Card.Link>
          </Stack>
          <Stack direction="horizontal" className='justify-content-between mt-4'>
          <Card.Link href="#" onClick={() => onShowDetails(event.id)}>Show Details</Card.Link>
          <Card.Link href="#" onClick={() => onShowAttendees(event.id)}>Show Attendees</Card.Link>
          </Stack>
          </Stack>
        </Card.Body>
        </Card> 
      </>
    )
}

export default EventCard
