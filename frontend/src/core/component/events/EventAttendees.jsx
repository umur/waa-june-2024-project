import React, {useState, useEffect} from 'react';
import { useParams } from "react-router";
import { getEventsAttendeesApi } from '../../../service/eventsAPI';
import {Table, Row, Container } from 'react-bootstrap';
import NavBar from '../NavBar';

function EventAttendees() {
    const params = useParams();
    const [attendees, setAttendees] = useState();

    useEffect( () => {
        getAttendees()
    }, [params.id])
    
    const getAttendees = async () => {
        const data = await getEventsAttendeesApi(params.id);
        setAttendees(data);
    }

    if(attendees && attendees.length === 0) return <Row className='d-flex justify-content-center p-5'>No Attendees Available</Row>
    return (
        <>
        <NavBar />
        <Container className='mx-auto my-5'>
        <Row className="mx-auto my-0">
        <h3 className='d-flex justify-content-center p-5'> Attendees for Events</h3>
        <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
            { attendees && attendees.map( (attendee, idx) => 
                <tr key={attendee.id}>
                <td>{idx + 1}</td>
                <td>{attendee.firstName}</td>
                <td>{attendee.lastName}</td>
                <td>{attendee.email}</td>
                </tr>
            )}
        </tbody>
      </Table> 
      </Row>
      </Container>
      </>
    )
}

export default EventAttendees
