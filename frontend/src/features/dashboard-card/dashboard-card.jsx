import React from 'react';
import {Card, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {useNavigate} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './dashboard-card.css'; // Import your CSS file

export default function DashboardCard({icon, link, title}) {
  const navigate = useNavigate();

  const handleCardClick = () => {
    navigate(link);
  };

  return (
    <Col md={3} sm={4} className="mb-4">
      <Card
        className="dashboard-card"
        onClick={handleCardClick}
        style={{width: '100%', height: '300px', textAlign: 'center', cursor: 'pointer'}}
      >
        <Card.Body className="d-flex flex-column justify-content-center align-items-center">
          <FontAwesomeIcon icon={icon} size="3x" className="mb-3" />
          <Card.Title>{title}</Card.Title>
        </Card.Body>
      </Card>
    </Col>
  );
}
