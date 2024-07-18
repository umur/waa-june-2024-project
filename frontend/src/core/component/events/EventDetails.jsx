import React, {useState, useEffect} from 'react';
import { useParams, useNavigate } from "react-router";
import {Card, Stack} from 'react-bootstrap';
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
          <Card.Title>{event.name}</Card.Title>
          <Stack direction="horizontal" gap={3}>
          <Card.Text>
            Date:
          </Card.Text>
          <Card.Subtitle className="mb-2 text-muted">{event.eventDate} </Card.Subtitle>
          </Stack>
          <Stack direction="horizontal" gap={3}>
          <Card.Text>
            Time:
          </Card.Text>
          <Card.Subtitle className="mb-2 text-muted">{event.eventTime} </Card.Subtitle>
          </Stack>
          <Card.Text>
            {event.description}
          </Card.Text>
          <Card.Link href="#" onClick={() => navigate("/events")}>Back</Card.Link>
        </Card.Body>
      </Card>
}
      </>
    )
}

export default EventDetails
