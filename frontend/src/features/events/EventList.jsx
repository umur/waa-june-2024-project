import React from 'react';
import { useNavigate } from 'react-router';
import CreateEvent from './CreateEvent';
import { Roles } from '../../core/constants';
import StudentEvent from './StudentEvent';
import EventCard from './EventCard';
import getCurrentProfile from '../../core/utils/current-profile';
import { Row } from 'react-bootstrap';

function EventList({
  events, 
  onDelete, 
  onShowModal, 
  show ,
  onClose, 
  state,
   validated, 
   onChange, 
   onSubmit, 
   onHandleMakeEventReservation,
   onHandleRemoveEventReservation
  }) {
  const navigate = useNavigate();
  const profile = getCurrentProfile();

  const onShowDetails = (id) => {
      navigate(`/events/${id}`)
  }

  const onShowAttendees = (id) => {
    navigate(`/event-attendees/${id}`)
  }
    if(events && events.length === 0) return <Row className='d-flex justify-content-center p-5'>No Events Available</Row>
    return (
      <>
       {profile.role === Roles.STUDENT ? (
         <Row className="justify-content-start">
         { 
         events && events.map(event =>
          { 
            const rsvp = event.attendedStudents.find(a => a.username === profile.user)
          return ( <StudentEvent
           event={event} 
           key={event.id} 
           onHandleMakeEventReservation={onHandleMakeEventReservation}
           onHandleRemoveEventReservation={onHandleRemoveEventReservation}
           isRsvped={rsvp ? true : false}
           />)})
         }
          </Row>
        
        ) : (
          <>
            <Row className="justify-content-start">
          {
            events && events.map(event => (
              <EventCard key={event.id} event={event} onDelete={onDelete} onShowModal={onShowModal} onShowDetails={onShowDetails} onShowAttendees={onShowAttendees}/>
            ))
          }
          
          <CreateEvent onClose={onClose} show={show} state={state} validated={validated} 
          onChange={onChange} onSubmit={onSubmit}/> 
          </Row>
          </>
        )}
      
   
      </>
    )
}

export default EventList
