import React, { useEffect, useState } from 'react';
import {Container, Button} from 'react-bootstrap';
import EventList from '../../core/component/events/EventList';
import { deleteEventApi, getAllEventsApi, updateEventApi } from '../../service/eventsAPI';
import CreateEvent from '../../core/component/events/CreateEvent';
import { createEventApi } from '../../service/eventsAPI';

function Events() {
    const initialState = {
        name: "",
        eventDate: "",
        eventTime: "",
        description: "",
        
    }
    const [events, setEvents] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [isEditing, setisEditing] = useState(false);
    const [validated, setValidated] = useState(false);
    const [addEvent, setAddEvent] = useState(initialState)

    const handleOnChange = (e) => {
        setAddEvent({...addEvent, [e.target.name]: e.target.value})
    }

    useEffect(() =>{
        getEvents();
    }, [])

    const getEvents = async () => {
        const data = await getAllEventsApi();
        //TODO: Pagination
        setEvents(data.content);
    }

    const handleSubmit = async (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
    
        setValidated(true);
        if(isEditing) {
           await updateEventApi(addEvent);
           getEvents();
           setisEditing(false);
           setShowModal(false)
        } else {
            await createEventApi(addEvent);
            getEvents();
            setShowModal(false);
        }
     
        setAddEvent(initialState);

      };

      const handleDelete = async (id) => {
        await deleteEventApi(id)
        getEvents();
    }
    const handleShowModal = async (data, mode) => {
        if(mode === 'Edit') {
            setShowModal(true);
            setisEditing(true)
            setAddEvent({
                id: data.id,
                name: data.name,
                eventDate: data.eventDate,
                eventTime: data.eventTime,
                description: data.description,
            })
        } else {
            setShowModal(true);
            setisEditing(false);
        }
           
    }

    const handleCloseModal = () => {
        setShowModal(false);
        setisEditing(false);
        setAddEvent(initialState);
    }

    return (
        <Container className='mx-auto my-5'>
             <Button variant="primary" onClick={(e)=>handleShowModal(e, 'Create')}>
            Add Event
            </Button>
            <EventList events={events} 
            onDelete={handleDelete} 
            onSubmit={handleSubmit} 
            onShowModal={handleShowModal}
            onClose={handleCloseModal}
            show={showModal}
            state={addEvent}
            validated={validated} 
            onChange={handleOnChange}
            />
            <CreateEvent 
            state={addEvent}
            onChange={handleOnChange}
            onClose={setShowModal} 
            show={showModal} 
            onShowModal={handleShowModal}
            validated={validated} 
            onSubmit={handleSubmit}
            isEditing={isEditing}
            />
      </Container>
    )
}

export default Events
