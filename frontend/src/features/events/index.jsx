import React, { useEffect, useState } from 'react';
import {Container, Button, Form} from 'react-bootstrap';
import EventList from '../../core/component/events/EventList';
import { deleteEventApi, getAllEventsApi, makeEventReservationApi, removeEventReservationApi, updateEventApi } from '../../service/eventsAPI';
import CreateEvent from '../../core/component/events/CreateEvent';
import { createEventApi } from '../../service/eventsAPI';
import getCurrentProfile from '../../core/utils/current-profile';
import { Roles } from '../../core/constants';
import { Link } from 'react-router-dom';
import NavBar from '../../core/component/NavBar';
import debounce from 'lodash/debounce';

function Events() {
    const initialState = {
        name: "",
        eventDate: "",
        eventTime: "",
        location: "",
        description: "",
        
    }
    const [events, setEvents] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [isEditing, setisEditing] = useState(false);
    const [validated, setValidated] = useState(false);
    const [addEvent, setAddEvent] = useState(initialState);
    const [searchQuery, setSearchQuery] = useState('');
    const profile = getCurrentProfile();

    const handleOnChange = (e) => {
        setAddEvent({...addEvent, [e.target.name]: e.target.value})
    }

    useEffect(() =>{
        getEvents();
    }, [])

    const getEvents = async () => {
        const data = await getAllEventsApi(searchQuery);

        setEvents(data.content);
    }

    const fetchEventsDebounced = debounce(getEvents, 700);
        
    const handleSearch = event => {
        const {value} = event.target;
        setSearchQuery(value);
        // Call the debounced function
        fetchEventsDebounced(0, value); // Reset to first page when searching
    };

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
                location: data.location,
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

    const handleMakeEventReservation = async (id) => {
        await makeEventReservationApi(id);
        getEvents();

    }

    const handleRemoveEventReservation = async (id) => {
        await removeEventReservationApi(id);
        getEvents();

    }
    return (
        <>
          <NavBar />
        <Container className='mx-auto my-5'>
            {profile.role === Roles.ADMIN && 
             <Button variant="primary" onClick={(e)=>handleShowModal(e, 'Create')}>
            Add Event
            </Button>
            }

            {profile.role === Roles.STUDENT && 
             <Link to="/my-events" >
              See My Events
            </Link>
            }

            <h3 className='d-flex justify-content-center m-4'>Events</h3>
            <div className="mb-3">
              <Form.Group controlId="search">
                <Form.Control
                  type="text"
                  placeholder="Search Events..."
                  value={searchQuery}
                  onChange={handleSearch}
                />
              </Form.Group>
            </div>
            <EventList events={events} 
            onDelete={handleDelete} 
            onSubmit={handleSubmit} 
            onShowModal={handleShowModal}
            onClose={handleCloseModal}
            show={showModal}
            state={addEvent}
            validated={validated} 
            onChange={handleOnChange}
            onHandleMakeEventReservation={handleMakeEventReservation}
            onHandleRemoveEventReservation={handleRemoveEventReservation}
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
      </>
    )
}

export default Events
