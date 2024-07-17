import React from 'react';
import {Modal, Button} from 'react-bootstrap';
import SelectField from '../../core/component/dialogs/select/Select';
import InputField from '../../core/component/dialogs/input/Input';

const FeedbackModal = ({
  show,
  handleClose,
  handleSave,
  title,
  handleChange,
  allCategories,
  feedbackForm,
  feedbackState
}) => {
  return (
    <Modal show={show} onHide={handleClose} backdrop="static">
      <Modal.Header className="bg-info">
        <Modal.Title>
          {feedbackForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <InputField
          type="text"
          label="Title"
          placeholder="Enter Title"
          name="title"
          value={feedbackForm.title}
          onChange={handleChange}
          isInvalid={!!feedbackState.errors.title}
          errors={feedbackState.errors.title}
          classStyle="mb-2"
        />

        <InputField
          type="text"
          label="Description"
          placeholder="Enter Description"
          name="body"
          value={feedbackForm.body}
          onChange={handleChange}
          isInvalid={!!feedbackState.errors.body}
          errors={feedbackState.errors.body}
          classStyle="mb-2"
        />

        <SelectField
          label="Feedback Category"
          name="feedbackCategory"
          value={feedbackForm.feedbackCategory}
          onChange={handleChange}
          isInvalid={!!feedbackState.errors.feedbackCategory}
          errors={feedbackState.errors.feedbackCategory}
          classStyle="mb-2"
        >
          <option value="">Select</option>
          {allCategories.map(cat => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </SelectField>
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
