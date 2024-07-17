import React, {useEffect, useState} from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row, Spinner} from 'react-bootstrap';
import {useNavigate} from 'react-router';
import axios from 'axios';
import ErrorDialog from '../../../core/component/dialogs/ErrorDialog';
import SuccessDialog from '../../../core/component/dialogs/SuccessDialog';
import {State} from '../../../core/constants';

const Register = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    birthDate: '',
    genderType: 'Male',
    studentCode: '',
    academicYears: '',
    majorId: ''
  });

  const [majors, setMajors] = useState([]);
  const [registerState, setRegisterState] = useState({
    status: State.IDLE,
    error: null,
    errors: {}
  });

  const navigate = useNavigate();

  useEffect(() => {
    const fetchMajors = async () => {
      try {
        const response = await axios.get('/majors');
        setMajors(response.data);
      } catch (error) {
        console.error('Error fetching majors:', error);
      }
    };

    fetchMajors();
  }, []);

  const register = async () => {
    setRegisterState({errors: {}, error: null, status: State.LOADING});

    axios
      .post('/student/register', formData)
      .then(res => {
        if (res.status === 201) {
          setRegisterState({...registerState, status: State.SUCCEEDED});
        } else if (res.status === 400) {
          setRegisterState({
            status: State.FAILED,
            error: res.data.message || 'Something went wrong. Please try again later.'
          });
        }
      })
      .catch(err => {
        if (err.response && err.response.status === 400) {
          setRegisterState({
            status: State.FAILED,
            error: null,
            errors: err.response.data
          });
        } else {
          setRegisterState({
            status: State.FAILED,
            errors: {},
            error: 'Something went wrong. Please try again later.'
          });
        }
      });
  };

  const handleSubmit = e => {
    e.preventDefault();
    register();
  };

  const handleChange = e => {
    const {name, value} = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  return (
    <Container className="justify-content-center align-items-center">
      <Row className="justify-content-center min-vh-100 m-4">
        <Col md="4">
          <h2 className="text-center">Register</h2>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUsername">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                isInvalid={!!registerState.errors.username}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.username}</Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Enter password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                isInvalid={!!registerState.errors.password}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.password}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formFirstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter first name"
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                isInvalid={!!registerState.errors.firstName}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.firstName}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formLastName">
              <Form.Label>Last Name</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter last name"
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                isInvalid={!!registerState.errors.lastName}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.lastName}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formEmail">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                isInvalid={!!registerState.errors.email}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.email}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formBirthDate">
              <Form.Label>Birth Date</Form.Label>
              <Form.Control
                type="date"
                name="birthDate"
                value={formData.birthDate}
                onChange={handleChange}
                isInvalid={!!registerState.errors.birthDate}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.birthDate}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formGenderType">
              <Form.Label>Gender</Form.Label>
              <Form.Control as="select" name="genderType" value={formData.genderType} onChange={handleChange}>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </Form.Control>
            </Form.Group>
            <Form.Group controlId="formStudentCode">
              <Form.Label>Student Code</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter student code"
                name="studentCode"
                value={formData.studentCode}
                onChange={handleChange}
                isInvalid={!!registerState.errors.studentCode}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.studentCode}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formAcademicYears">
              <Form.Label>Academic Years</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter academic years"
                name="academicYears"
                value={formData.academicYears}
                onChange={handleChange}
                isInvalid={!!registerState.errors.academicYears}
              />
              <Form.Control.Feedback type="invalid">{registerState.errors.academicYears}</Form.Control.Feedback>
            </Form.Group>
            <Form.Group controlId="formMajorId">
              <Form.Label>Major</Form.Label>
              <Form.Control
                as="select"
                name="majorId"
                value={formData.majorId}
                onChange={handleChange}
                isInvalid={!!registerState.errors.majorId}
              >
                <option value="">None</option>
                {majors.map(major => (
                  <option key={major.id} value={major.id}>
                    {major.name}
                  </option>
                ))}
              </Form.Control>
              <Form.Control.Feedback type="invalid">{registerState.errors.majorId}</Form.Control.Feedback>
            </Form.Group>

            <ButtonGroup className="d-flex mt-2">
              <Button
                variant="primary"
                type="submit"
                className="flex-fill me-2 rounded"
                disabled={registerState.status === State.LOADING}
              >
                {registerState.status === State.LOADING ? (
                  <Spinner animation="border" size="sm" role="status" aria-hidden="true" />
                ) : (
                  'Register'
                )}
              </Button>
              <Button
                variant="outline-primary"
                type="button"
                className="flex-fill ms-2 rounded"
                onClick={() => navigate('/login')}
              >
                Login
              </Button>
            </ButtonGroup>
          </Form>
        </Col>
      </Row>
      <ErrorDialog
        show={registerState.status === State.FAILED && registerState.error}
        errorMessage={registerState.error}
        handleClose={() => {
          setRegisterState({...registerState, status: State.IDLE});
        }}
      />
      <SuccessDialog
        show={registerState.status === State.SUCCEEDED}
        successMessage="User created successfully. Please proceed to login."
        buttonText="Proceed"
        handleClose={() => {
          navigate('/login');
        }}
      />
    </Container>
  );
};

export default Register;
