import React from 'react';
import {Modal, Button} from 'react-bootstrap';

const FeedbackModal = ({show, handleClose, handleSave, title, handleChange, allCategories, feedbackForm}) => {
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header className="bg-info">
        <Modal.Title>
          {feedbackForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="mb-3 text-start fw-bold">
          <label htmlFor="title" className="form-label">
            Title
          </label>
          <input
            type="text"
            className="form-control"
            name="title"
            value={feedbackForm.title}
            onChange={handleChange}
            placeholder="Enter Title"
            required
          />
        </div>

        <div className="mb-3 text-start fw-bold">
          <label htmlFor="body" className="col-form-label">
            Description
          </label>
          <textarea
            type="text"
            className="form-control"
            name="body"
            value={feedbackForm.body}
            onChange={handleChange}
            placeholder="Enter description"
            required
          />
        </div>

        <div className="mb-5 text-start fw-bold">
          <label htmlFor="feedbackCategory" className="col-form-label">
            Category
          </label>
          <select
            className="form-control"
            name="feedbackCategory"
            onChange={handleChange}
            value={feedbackForm.feedbackCategory}
            required
          >
            <option value="">Select</option>
            {allCategories.map(cat => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>
        </div>
      </Modal.Body>

      <Modal.Footer className="bg-light">
        <Button variant="secondary" className="flex-fill rounded" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" className="flex-fill rounded" onClick={handleSave}>
          {feedbackForm.id > 0 ? 'Update ' : 'Add '}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default FeedbackModal;
