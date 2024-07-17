import React from 'react';
import {Form} from 'react-bootstrap';

const SelectField = ({
  controlId,
  label,
  name,
  value,
  onChange,
  isInvalid,
  errors,
  children // to render options for select elements
}) => (
  <Form.Group controlId={controlId}>
    <Form.Label>{label}</Form.Label>
    <Form.Control as="select" name={name} value={value} onChange={onChange} isInvalid={isInvalid} errors={errors}>
      {children}
    </Form.Control>
    <Form.Control.Feedback type="invalid">{errors}</Form.Control.Feedback>
  </Form.Group>
);

export default SelectField;
