import React from 'react';
import {Modal, Button} from 'react-bootstrap';
import InputField from '../../core/component/dialogs/input/Input';

const AcademicResourceTypeModal = ({
  show,
  handleClose,
  handleSave,
  title,
  handleChange,
  resourceTypeForm,
  resourceTypeState
}) => {
  return (
    <Modal show={show} onHide={handleClose} backdrop="static">
      <Modal.Header className="bg-info">
        <Modal.Title>
          {resourceTypeForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <InputField
          type="text"
          label="Category Name"
          placeholder="Enter Category"
          name="name"
          value={resourceTypeForm.name}
          onChange={handleChange}
          isInvalid={!!resourceTypeState.errors.name}
          errors={resourceTypeState.errors.name}
          classStyle="mb-2"
        />
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
