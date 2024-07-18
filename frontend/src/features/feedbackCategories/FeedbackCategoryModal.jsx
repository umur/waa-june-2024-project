import React from 'react';
import {Modal, Button, Form} from 'react-bootstrap';
import InputField from '../../core/component/dialogs/input/Input';

const FeedbackCategoryModal = ({
  show,
  handleClose,
  handleSave,
  title,
  handleChange,
  feedbackCategoryForm,
  feedbackCategoryState
}) => {
  return (
    <Modal show={show} onHide={handleClose} backdrop="static">
      <Modal.Header className="bg-info">
        <Modal.Title>
          {feedbackCategoryForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <InputField
          type="text"
          label="Category Name"
          placeholder="Enter Category"
          name="name"
          value={feedbackCategoryForm.name}
          onChange={handleChange}
          isInvalid={!!feedbackCategoryState.errors.name}
          errors={feedbackCategoryState.errors.name}
          classStyle="mb-2"
        />

        <InputField
          type="text"
          label="Description"
          placeholder="Enter Description"
          name="description"
          value={feedbackCategoryForm.description}
          onChange={handleChange}
          isInvalid={!!feedbackCategoryState.errors.description}
          errors={feedbackCategoryState.errors.description}
          classStyle="mb-2"
        />
      </Modal.Body>

      <Modal.Footer className="bg-light">
        <Button variant="secondary" className="flex-fill rounded" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" className="flex-fill rounded" onClick={handleSave}>
          {feedbackCategoryForm.id > 0 ? 'Update ' : 'Add '}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default FeedbackCategoryModal;
