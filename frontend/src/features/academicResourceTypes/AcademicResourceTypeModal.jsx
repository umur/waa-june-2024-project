import React from 'react';
import {Modal, Button} from 'react-bootstrap';

const AcademicResourceTypeModal = ({show, handleClose, handleSave, title, handleChange, resourceTypeForm}) => {
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header className="bg-info">
        <Modal.Title>
          {resourceTypeForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="mb-3 text-start fw-bold">
          <label htmlFor="name" className="form-label">
            Category Name
          </label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={resourceTypeForm.name}
            onChange={handleChange}
            placeholder="Enter Category"
            required
          />
        </div>
      </Modal.Body>

      <Modal.Footer className="bg-light">
        <Button variant="secondary" className="flex-fill rounded" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" className="flex-fill rounded" onClick={handleSave}>
          {resourceTypeForm.id > 0 ? 'Update ' : 'Add '}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AcademicResourceTypeModal;
