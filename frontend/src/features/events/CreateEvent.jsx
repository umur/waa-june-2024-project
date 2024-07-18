import React from 'react';
import {Button, Modal,Form, Row } from 'react-bootstrap';

function CreateEvent({show, onClose, validated, onSubmit, onChange, state, isEditing}) {
  return (
    <>
      <Modal show={show} onHide={() => onClose(false)}>
        <Modal.Header closeButton>
          <Modal.Title>{ isEditing ? `Edit Event` : `Add Event`}</Modal.Title>
        </Modal.Header>
        <Form noValidate validated={validated}>
        <Row className="mx-auto my-4">
        <Form.Group  className="mb-4" controlId="validationCustom01">
          <Form.Label>Name:</Form.Label>
          <Form.Control
            required
            type="text"
            name="name"
            value={state.name}
            onChange={onChange}
          />
        </Form.Group>
        <Form.Group className="mb-4" controlId="validationCustom02">
          <Form.Label>Date:</Form.Label>
          <Form.Control
            required
            type="date"
            name="eventDate"
            value={state.eventDate}
            onChange={onChange}
          />
        </Form.Group>
        <Form.Group className="mb-4" controlId="validationCustom03">
          <Form.Label>Time:</Form.Label>
          <Form.Control
            required
            type="time"
            name="eventTime"
            value={state.eventTime}
            onChange={onChange}
          />
        </Form.Group>
        <Form.Group className="mb-4" controlId="validationCustom04">
          <Form.Label>Location:</Form.Label>
          <Form.Control
            required
            type="location"
            name="location"
            value={state.location}
            onChange={onChange}
          />
        </Form.Group>
        <Form.Group className="mb-4" controlId="validationCustom05">
          <Form.Label>Description:</Form.Label>
          <Form.Control
            required
            type="text"
            as="textarea" 
            rows={3} 
            name="description"
            value={state.description}
            onChange={onChange}
          />
        </Form.Group>
        </Row>
    </Form>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => onClose(false)}>
            Close
          </Button>
          <Button variant="primary"  onClick={onSubmit}>
            Submit
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CreateEvent;