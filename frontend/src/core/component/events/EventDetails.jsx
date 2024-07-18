import React, {useState, useEffect} from 'react';
import { useParams, useNavigate } from "react-router";
import {Card, Stack} from 'react-bootstrap';
import { MdEventAvailable } from "react-icons/md";
import { IoArrowBackSharp } from "react-icons/io5";
import { getEventApi } from '../../../service/eventsAPI';

function EventDetails() {
    const params = useParams();
    const navigate = useNavigate();
    const [event, setEvent] = useState();

    useEffect( () => {
        getEvent()
    }, [params.id])
    
    const getEvent = async () => {
        const data = await getEventApi(params.id);
        setEvent(data);
    }
    
    return (
        <>
        {event && 
        <Card style={{ width: '25rem' }} className="mx-auto my-4">
        <Card.Body>
        <Card.Title className='fs-2'>
          <MdEventAvailable size={60} color='green'/>
            {new Date(event.eventDate).toString("MMMM").split(" ")[1]} {" "}
          <span className='fs-5'>{new Date(event.eventDate).toString("MMMM").split(" ")[2]}</span> {" "}
          <span className='fs-5'>{new Date(event.eventDate).toString("MMMM").split(" ")[3]}</span>
          </Card.Title>
          <Card.Text className='fs-3 mt-3'>{event.name}</Card.Text>
          <Stack direction="horizontal" gap={3} className="mb-3 mt-2">
          <Card.Text className="mt-2 fs-4">
            Time:
          </Card.Text>
          <Card.Subtitle className="text-muted">
           <span className='fs-5'>
            {event.eventTime.slice(0, -3)}
            {event.eventTime.slice(0, -3)  ? "AM" : "PM"} {" "}
            </span>
          </Card.Subtitle>
          </Stack>
          <Stack direction="horizontal" gap={3}  className="mb-4">
          <Card.Text className="mb-2 fs-4">
            Location:
          </Card.Text>
          <Card.Subtitle className="fs-5 text-muted">{event.location} </Card.Subtitle>
          </Stack>
          <Card.Text>
            {event.description}
          </Card.Text>
          <Card.Link href="#" onClick={() => navigate("/events")}><IoArrowBackSharp />Back</Card.Link>
        </Card.Body>
      </Card>
}
      </>
    )
}

export default EventDetails
