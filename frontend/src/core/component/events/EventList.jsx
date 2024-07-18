import React from 'react';
import { useNavigate } from 'react-router';
import {Table, Button} from 'react-bootstrap';
import { MdDeleteForever } from "react-icons/md";
import { FaRegEdit } from "react-icons/fa";
// import EditEvent from './EditEvent';
import CreateEvent from './CreateEvent';

function EventList({events, onDelete, onShowModal, show , onClose, state, validated, onChange, onSubmit}) {
  const navigate = useNavigate();

  const onShowDetails = (id) => {
      navigate(`/events/${id}`)
  }
    return (
      <>
       <h3 className='d-flex justify-content-center'>List of Events</h3>
        <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Name</th>
            <th></th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
            { events && events.map( event => 
                <tr key={event.id}>
                <td>{event.id}</td>
                <td>{event.name}</td>
                <td><FaRegEdit color='green' onClick={() => onShowModal(event, 'Edit')}/></td>
                <td><MdDeleteForever color='red' size={20} onClick={() => onDelete(event.id)}/></td>
                <td><Button variant="link" onClick={() => onShowDetails(event.id)}>Details</Button></td>
                </tr>
            )}
        </tbody>
      </Table>
     <CreateEvent onClose={onClose} show={show} state={state} validated={validated} 
            onChange={onChange} onSubmit={onSubmit}/> 
      </>
    )
}

export default EventList
