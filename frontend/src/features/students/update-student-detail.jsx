import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {Button, Col, Container, Form, Image, Row, Spinner} from 'react-bootstrap';
import apiClient from '../../core/setup/axios';
import {State} from '../../core/constants';
import ErrorDialog from '../../core/component/dialogs/ErrorDialog';
import SuccessDialog from '../../core/component/dialogs/SuccessDialog';
import getCurrentProfile from '../../core/utils/current-profile';

export default function UpdateStudentDetail() {
  const username = getCurrentProfile().user;
  const navigate = useNavigate();

  const [profileUpdateRequest, setProfileUpdateRequest] = useState({
    firstName: '',
    lastName: '',
    email: '',
    birthDate: '',
    genderType: '',
    studentCode: '',
    academicYears: '',
    achievements: [''],
    interest: [''],
    extraActivities: [''],
    majorId: ''
  });

  const [student, setStudent] = useState({});

  const [majors, setMajors] = useState([]);

  const [fetchStatus, setFetchStatus] = useState(State.IDLE);
  const [updateStatus, setUpdateStatus] = useState(State.IDLE);
  const [fetchMessage, setFetchMessage] = useState({status: '', error: ''});
  const [updateMessage, setUpdateMessage] = useState({status: '', error: ''});

  const [profileImage, setProfileImage] = useState(null);
  const [profileImageUrl, setProfileImageUrl] = useState(null);

  const handleImageChange = event => {
    const selectedImage = event.target.files[0];

    setProfileImageUrl(selectedImage);
    if (selectedImage) {
      const reader = new FileReader();
      reader.onload = () => {
        setProfileImage(reader.result);
      };
      reader.readAsDataURL(selectedImage);
    }
  };

  useEffect(() => {
    const fetchStudent = async () => {
      setFetchStatus(State.LOADING);
      try {
        const response = await apiClient.get(`/students/${username}`);
        setStudent(response.data);

        setProfileUpdateRequest({
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          email: response.data.email,
          birthDate: response.data.birthDate,
          genderType: response.data.genderTypes,
          studentCode: response.data.studentCode,
          academicYears: response.data.academicYears,
          achievements: response.data.achievements,
          interest: response.data.interest,
          extraActivities: response.data.extraActivities,
          majorId: response.data.majorId
        });

        setFetchStatus(State.SUCCEEDED);
      } catch (err) {
        setFetchMessage({status: 'failed', error: 'Failed to fetch student data. Please try again.'});
        setFetchStatus(State.FAILED);
      }
    };

    const fetchMajors = async () => {
      try {
        const response = await apiClient.get('/majors');
        setMajors(response.data);
      } catch (err) {
        setFetchMessage({status: 'failed', error: 'Failed to fetch majors. Please try again.'});
      }
    };

    fetchStudent();
    fetchMajors();
  }, [username]);

  const handleChange = e => {
    const {name, value} = e.target;
    setProfileUpdateRequest(prevState => ({...prevState, [name]: value}));
  };

  const handleAddItem = field => {
    setProfileUpdateRequest(prevState => ({
      ...prevState,
      [field]: [...prevState[field], '']
    }));
  };

  const handleRemoveItem = (field, index) => {
    setProfileUpdateRequest(prevState => ({
      ...prevState,
      [field]: prevState[field].filter((_, i) => i !== index)
    }));
  };

  const handleChangeItem = (field, index, value) => {
    const newList = [...profileUpdateRequest[field]];
    newList[index] = value;
    setProfileUpdateRequest(prevState => ({
      ...prevState,
      [field]: newList
    }));
  };

  console.log(profileImageUrl);

  const handleSubmit = async e => {
    e.preventDefault();

    setUpdateStatus(State.LOADING);

    const formData = new FormData();
    const studentJsonBlob = new Blob([JSON.stringify(profileUpdateRequest)], {type: 'application/json'});

    console.log(JSON.stringify(studentJsonBlob));

    formData.append('profile', studentJsonBlob);

    if (profileImageUrl !== null || profileImageUrl !== '' || profileImageUrl !== undefined)
      formData.append('picture', profileImageUrl);
    try {
      await apiClient.post(`/students/${username}/profile`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      setUpdateMessage({status: 'success', error: ''});
      setUpdateStatus(State.SUCCEEDED);
    } catch (err) {
      setUpdateMessage({
        status: 'failed',
        error: err.response.data.message || 'Failed to update profile. Please try again.'
      });
      setUpdateStatus(State.FAILED);
    }
  };

  const handleSuccessClose = () => {
    navigate(`/student/${username}`);
  };

  return (
    <Container className="my-4">
      {fetchStatus === State.LOADING && (
        <Row className="my-4">
          <Col>
            <Spinner animation="border" role="status">
              <span className="visually-hidden">Loading...</span>
            </Spinner>
          </Col>
        </Row>
      )}
      {fetchStatus === State.FAILED && (
        <ErrorDialog show={true} errorMessage={fetchMessage.error} handleClose={() => setFetchStatus(State.IDLE)} />
      )}
      {updateStatus === State.SUCCEEDED && (
        <SuccessDialog show={true} successMessage="Profile updated successfully." handleClose={handleSuccessClose} />
      )}
      {(fetchStatus === State.IDLE || fetchStatus === State.SUCCEEDED) && (
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="firstName">
            <Form.Label>First Name</Form.Label>
            <Form.Control
              type="text"
              name="firstName"
              value={profileUpdateRequest.firstName}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="lastName" className="mt-4">
            <Form.Label>Last Name</Form.Label>
            <Form.Control
              type="text"
              name="lastName"
              value={profileUpdateRequest.lastName}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="email" className="mt-4">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              name="email"
              value={profileUpdateRequest.email}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="birthDate" className="mt-4">
            <Form.Label>Birth Date</Form.Label>
            <Form.Control
              type="date"
              name="birthDate"
              value={profileUpdateRequest.birthDate}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="genderType" className="mt-4">
            <Form.Label>Gender</Form.Label>
            <Form.Control
              as="select"
              name="genderType"
              value={profileUpdateRequest.genderType}
              onChange={handleChange}
              required
            >
              <option value="">Select Major</option>
              <option key="1" value="Male">
                Male
              </option>
              <option key="2" value="Female">
                Female
              </option>
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="formFile" className="mb-3">
            <Form.Label>Choose Profile Picture</Form.Label>
            <Form.Control type="file" accept="image/*" onChange={handleImageChange} />
            {profileImage && (
              <div className="mt-3" style={{maxWidth: '300px', position: 'relative'}}>
                <div
                  style={{
                    position: 'relative',
                    width: '100%',
                    paddingTop: '98.23%' // Aspect ratio (500/509 * 100%)
                  }}
                >
                  <Image
                    src={profileImage}
                    alt="Profile Preview"
                    style={{
                      position: 'absolute',
                      top: 0,
                      left: 0,
                      width: '100%',
                      height: '100%',
                      objectFit: 'cover' // Maintain aspect ratio and cover container
                    }}
                    fluid
                  />
                </div>
              </div>
            )}
          </Form.Group>

          <Form.Group controlId="studentCode" className="mt-4">
            <Form.Label>Student Code</Form.Label>
            <Form.Control
              type="text"
              name="studentCode"
              value={profileUpdateRequest.studentCode}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="academicYears" className="mt-4">
            <Form.Label>Academic Years</Form.Label>
            <Form.Control
              type="text"
              name="academicYears"
              value={profileUpdateRequest.academicYears}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group controlId="majorId" className="mt-4">
            <Form.Label>Major</Form.Label>
            <Form.Control
              as="select"
              name="majorId"
              value={profileUpdateRequest.majorId}
              onChange={handleChange}
              required
            >
              <option value="">Select Major</option>
              {majors.map(major => (
                <option key={major.id} value={major.id}>
                  {major.name}
                </option>
              ))}
            </Form.Control>
          </Form.Group>

          <Form.Group controlId="achievements" className="mt-4">
            <Form.Label>Achievements</Form.Label>
            {profileUpdateRequest.achievements.map((achievement, index) => (
              <div key={index} className="d-flex align-items-center mb-2">
                <Form.Control
                  type="text"
                  value={achievement}
                  onChange={e => handleChangeItem('achievements', index, e.target.value)}
                />
                <Button variant="danger" onClick={() => handleRemoveItem('achievements', index)} className="ms-2">
                  Remove
                </Button>
              </div>
            ))}
            <Button variant="primary" onClick={() => handleAddItem('achievements')}>
              Add Achievement
            </Button>
          </Form.Group>

          <Form.Group controlId="interest" className="mt-4">
            <Form.Label>Interests</Form.Label>
            {profileUpdateRequest.interest.map((interest, index) => (
              <div key={index} className="d-flex align-items-center mb-2">
                <Form.Control
                  type="text"
                  value={interest}
                  onChange={e => handleChangeItem('interest', index, e.target.value)}
                />
                <Button variant="danger" onClick={() => handleRemoveItem('interest', index)} className="ms-2">
                  Remove
                </Button>
              </div>
            ))}
            <Button variant="primary" onClick={() => handleAddItem('interest')}>
              Add Interest
            </Button>
          </Form.Group>

          <Form.Group controlId="extraActivities" className="mt-4">
            <Form.Label>Extra Activities</Form.Label>
            {profileUpdateRequest.extraActivities.map((activity, index) => (
              <div key={index} className="d-flex align-items-center mb-2">
                <Form.Control
                  type="text"
                  value={activity}
                  onChange={e => handleChangeItem('extraActivities', index, e.target.value)}
                />
                <Button variant="danger" onClick={() => handleRemoveItem('extraActivities', index)} className="ms-2">
                  Remove
                </Button>
              </div>
            ))}
            <Button variant="primary" onClick={() => handleAddItem('extraActivities')}>
              Add Extra Activity
            </Button>
          </Form.Group>

          <Button variant="primary" type="submit" className="mt-4">
            Update Profile
          </Button>
        </Form>
      )}
    </Container>
  );
}
