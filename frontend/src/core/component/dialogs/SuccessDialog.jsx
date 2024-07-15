import React from 'react';
import {Button, Modal} from 'react-bootstrap';

const SuccessDialog = ({show, handleClose, successMessage, buttonText = 'Close'}) => {
  return (
    <Modal show={show} onHide={handleClose} onClose={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Success</Modal.Title>
      </Modal.Header>
      <Modal.Body>{successMessage}</Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleClose}>
          {buttonText}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default SuccessDialog;
