import React from 'react';
import {Form} from 'react-bootstrap';

const FileUploadField = ({controlId, label, name, onChange, isInvalid, errors, classStyle}) => (
  <Form.Group controlId={controlId}>
    <Form.Label className={classStyle}>{label}</Form.Label>
    <Form.Control type="file" name={name} onChange={onChange} isInvalid={isInvalid} className={classStyle} />
    <Form.Control.Feedback type="invalid">{errors}</Form.Control.Feedback>
  </Form.Group>
);

export default FileUploadField;
