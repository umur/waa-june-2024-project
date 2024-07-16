import React from 'react';
import {Modal, Button} from 'react-bootstrap';

const AcademicResourceModal = ({
  show,
  handleClose,
  handleSave,
  title,
  handleChange,
  allCategories,
  academicResForm
}) => {
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header className="bg-info">
        <Modal.Title>
          {academicResForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="mb-3 text-start fw-bold">
          <label htmlFor="title" className="form-label">
            Name
          </label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={academicResForm.name}
            onChange={handleChange}
            placeholder="Enter Name"
            required
          />
        </div>

        <div className="mb-3 text-start fw-bold">
          <label htmlFor="body" className="form-label">
            Description
          </label>
          <textarea
            type="text"
            className="form-control"
            name="body"
            value={academicResForm.body}
            onChange={handleChange}
            placeholder="Enter Description"
            required
          />
        </div>

        <div className="mb-3 text-start fw-bold">
          <label htmlFor="resourceCategory" className="col-form-label">
            Resource Category
          </label>
          <select
            className="form-control"
            name="resourceCategory"
            onChange={handleChange}
            value={academicResForm.resourceCategory}
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

        <div className="mb-5 text-start fw-bold">
          <label htmlFor="file" className="form-label">
            File
          </label>

          <input type="file" className="form-control" name="file" onChange={handleChange} />
        </div>
      </Modal.Body>

      <Modal.Footer className="bg-light">
        <Button variant="secondary" className="flex-fill rounded" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" className="flex-fill rounded" onClick={handleSave}>
          {academicResForm.id > 0 ? 'Update ' : 'Add '}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AcademicResourceModal;
