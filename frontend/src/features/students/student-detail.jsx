import React, {useEffect, useState} from 'react';
import NavBar from '../../core/component/NavBar';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import {Button, Col, Container, Image, ListGroup, Row, Spinner} from 'react-bootstrap';
import {API, Roles, State} from '../../core/constants';
import getCurrentProfile from '../../core/utils/current-profile';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import SuccessDialog from '../../core/component/dialogs/SuccessDialog';
import apiClient from '../../core/setup/axios';
import {getTokens} from '../../core/setup/token';

export default function StudentDetail() {
  const location = useLocation();
  var {username} = useParams();
  const navigate = useNavigate();
  const profile = getCurrentProfile();

  username = username || profile.user;

  const [student, setStudent] = useState(null);
  const [deleteState, setDeleteState] = useState({
    status: State.IDLE,
    error: null,
    success: null
  });

  const {status, error, success} = deleteState;

  console.log(JSON.stringify(profile));
  console.log(JSON.stringify(getTokens()));

  if (!profile) {
    navigate('/login');
  }

  useEffect(() => {
    const fetchStudent = async () => {
      setDeleteState(prevState => ({...prevState, status: State.LOADING}));
      try {
        const response = await apiClient.get(`/students/${username}`);
        setStudent(response.data);
        setDeleteState(prevState => ({...prevState, status: State.SUCCEEDED}));
      } catch (err) {
        if (err.response && err.response.status === 401) {
          navigate('/login');
        } else {
          setDeleteState(prevState => ({
            ...prevState,
            status: State.FAILED,
            error: 'Something went wrong. Please try again later.'
          }));
        }
      }
    };

    if (location.state) {
      setStudent(location.state);
      setDeleteState(prevState => ({...prevState, status: State.IDLE}));
    } else {
      fetchStudent();
    }
  }, [location.state, username, navigate]);

  const handleDelete = async () => {
    let deleteURL = '';

    try {
      if (profile.role === Roles.ADMIN) {
        deleteURL = `/admins/students/${username}`;
      } else {
        deleteURL = `/students/${username}`;
      }

      await apiClient.delete(deleteURL);

      setDeleteState(prevState => ({
        ...prevState,
        status: State.SUCCEEDED,
        success: 'Profile deleted successfully.'
      }));

      console.log(deleteState);
    } catch (err) {
      if (err.response && err.response.status === 401) {
        navigate('/login');
      } else {
        setDeleteState(prevState => ({
          ...prevState,
          status: State.FAILED,
          error: err.response?.data?.message || 'Failed to delete the profile. Please try again.'
        }));
      }
    }
  };

  const handleUpdate = () => {
    navigate(`/profile/update`);
  };

  const handleSuccessClose = () => {
    if (profile.role === Roles.ADMIN) {
      navigate('/students');
    } else {
      navigate('/login');
    }
  };

  if (status === State.LOADING) {
    return (
      <Container className="my-4">
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </Container>
    );
  }

  if (status === State.FAILED) {
    return (
      <Container className="my-4">
        <ErrorDialog
          show={true}
          errorMessage={error}
          handleClose={() => setDeleteState(prevState => ({...prevState, status: State.IDLE}))}
        />
      </Container>
    );
  }

  if (!student) {
    return (
      <Container className="my-4">
        <h2>No student data available</h2>
      </Container>
    );
  }

  const {
    firstName,
    lastName,
    email,
    birthDate,
    genderType,
    studentCode,
    academicYears,
    picture,
    achievements,
    interest,
    extraActivities,
    major
  } = student;

  return (
    <>
      <NavBar />
      <Container className="my-4">
        <h2>
          {firstName} {lastName}
        </h2>
        <Row>
          <Col md={4}>
            <Image src={API.baseURL + picture} alt={`${firstName} ${lastName}`} fluid />
          </Col>
          <Col md={8}>
            <ListGroup variant="flush">
              <ListGroup.Item>
                <strong>Username:</strong> {username}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Name:</strong> {firstName} {lastName}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Email:</strong> {email}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Date of Birth:</strong> {birthDate}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Gender:</strong> {genderType}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Student Code:</strong> {studentCode}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Academic Years:</strong> {academicYears}
              </ListGroup.Item>
              <ListGroup.Item>
                <strong>Major:</strong> {major.name}
              </ListGroup.Item>
            </ListGroup>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col>
            <h5>Interests:</h5>
            <p>{interest.join(', ')}</p>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col>
            <h5>Extra Activities:</h5>
            <p>{extraActivities.join(', ')}</p>
          </Col>
        </Row>
        {achievements.length > 0 && (
          <Row className="mt-4">
            <Col>
              <h5>Achievements:</h5>
              <ListGroup variant="flush">
                {achievements.map((achievement, index) => (
                  <ListGroup.Item key={index}>{achievement}</ListGroup.Item>
                ))}
              </ListGroup>
            </Col>
          </Row>
        )}
        {(profile.user === username || profile.role === Roles.ADMIN) && (
          <Row className="mt-4">
            <Col>
              {profile.user === username && (
                <Button variant="primary" onClick={handleUpdate}>
                  Update Profile
                </Button>
              )}
              <Button variant="danger" onClick={handleDelete} className="ms-2">
                Delete Profile
              </Button>
            </Col>
          </Row>
        )}
      </Container>
      <SuccessDialog show={!!success} successMessage={success} handleClose={handleSuccessClose} />
    </>
  );
}
