import React from 'react';
import {Card} from 'react-bootstrap';
import {useNavigate} from 'react-router-dom';
import {API} from '../../../core/constants';

const StudentCard = ({student}) => {
  const navigate = useNavigate();

  const handleCardClick = () => {
    navigate(`/student/${student.username}`, {
      state: student
    });
  };

  const imageUrl = API.baseURL + student.picture;

  return (
    <Card className="mb-3" onClick={handleCardClick} style={{cursor: 'pointer'}}>
      <Card.Img
        variant="top"
        src={imageUrl}
        alt="Profile Picture"
        style={{width: '100%', height: '300px', objectFit: 'cover', overflow: 'hidden'}}
      />
      <Card.Body>
        <Card.Title>{`${student.firstName} ${student.lastName}`}</Card.Title>
        <Card.Text>
          <strong>Major:</strong> {student.major.name}
          <br />
          <strong>Year:</strong> {student.academicYears}
        </Card.Text>
      </Card.Body>
    </Card>
  );
};

export default StudentCard;
