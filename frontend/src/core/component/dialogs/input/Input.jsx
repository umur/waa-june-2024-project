import React from 'react';
import {Form} from 'react-bootstrap';

const InputField = ({
  controlId,
  label,
  type = 'text',
  placeholder,
  name,
  value,
  onChange,
  isInvalid,
  errors,
  classStyle,
  children // to render options for select elements
}) => (
  <Form.Group controlId={controlId}>
    <Form.Label className={classStyle}>{label}</Form.Label>
    <Form.Control
      type={type}
      placeholder={placeholder}
      name={name}
      value={value}
      onChange={onChange}
      isInvalid={isInvalid}
      className={classStyle}
    >
      {children}
    </Form.Control>
    <Form.Control.Feedback type="invalid">{errors}</Form.Control.Feedback>
  </Form.Group>
);

export default InputField;
