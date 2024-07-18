import React from 'react';
import {Modal, Button} from 'react-bootstrap';
import InputField from '../../core/component/dialogs/input/Input';
import SelectField from '../../core/component/dialogs/select/Select';
import FileUploadField from '../../core/component/dialogs/fileupload/FileUploadField';

const AcademicResourceModal = ({
  show,
  handleClose,
  handleSave,
  title,
  handleChange,
  allCategories,
  academicResForm,
  academicResState
}) => {
  return (
    <Modal show={show} onHide={handleClose} backdrop="static">
      <Modal.Header className="bg-info">
        <Modal.Title>
          {academicResForm.id > 0 ? 'Edit ' : 'Add '}
          {title}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <InputField
          type="text"
          label="Category Name"
          placeholder="Enter Name"
          name="name"
          value={academicResForm.name}
          onChange={handleChange}
          isInvalid={!!academicResState.errors.name}
          errors={academicResState.errors.name}
          classStyle="mb-2"
        />

        <InputField
          type="text"
          label="Description"
          placeholder="Enter Description"
          name="body"
          value={academicResForm.body}
          onChange={handleChange}
          isInvalid={!!academicResState.errors.body}
          errors={academicResState.errors.body}
          classStyle="mb-2"
        />

        <SelectField
          label="Resource Category"
          name="resourceCategory"
          value={academicResForm.resourceCategory}
          onChange={handleChange}
          isInvalid={!!academicResState.errors.resourceCategory}
          errors={academicResState.errors.resourceCategory}
          classStyle="mb-2"
        >
          <option value="">Select</option>
          {allCategories.map(cat => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </SelectField>

        <FileUploadField
          label="File"
          name="file"
          value={academicResForm.file}
          onChange={handleChange}
          isInvalid={!!academicResState.errors.file}
          errors={academicResState.errors.file}
          classStyle="mb-2"
        />
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
