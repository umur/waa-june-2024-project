import React from 'react';
import {Modal, Button} from 'react-bootstrap';

const FeedbackCategoryModal = ({show, handleClose, handleSave, title, handleChange, feedbackCategoryForm}) => {
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header className="bg-info">
        <Modal.Title>
          {feedbackCategoryForm.id > 0 ? 'Edit ' : 'Add '}
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
            value={feedbackCategoryForm.name}
            onChange={handleChange}
            placeholder="Enter Category"
            required
          />
        </div>

        <div className="mb-5 text-start fw-bold">
          <label htmlFor="description" className="col-form-label">
            Description
          </label>
          <textarea
            type="text"
            className="form-control"
            name="description"
            value={feedbackCategoryForm.description}
            onChange={handleChange}
            placeholder="Enter description"
            required
          />
        </div>
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
