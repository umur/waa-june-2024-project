import React from 'react';
import {Card, Row, Col} from 'react-bootstrap';
import './Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <Row>
        <Col xs={12} md={6} lg={3}>
          <Card bg="primary" text="white" className="mb-4">
            <Card.Body>
              <Card.Title>150</Card.Title>
              <Card.Text>New Orders</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col xs={12} md={6} lg={3}>
          <Card bg="success" text="white" className="mb-4">
            <Card.Body>
              <Card.Title>53%</Card.Title>
              <Card.Text>Bounce Rate</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col xs={12} md={6} lg={3}>
          <Card bg="warning" text="white" className="mb-4">
            <Card.Body>
              <Card.Title>44</Card.Title>
              <Card.Text>User Registrations</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col xs={12} md={6} lg={3}>
          <Card bg="danger" text="white" className="mb-4">
            <Card.Body>
              <Card.Title>65</Card.Title>
              <Card.Text>Unique Visitors</Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>
      <Row>
        <Col xs={12} lg={8}>
          <Card className="mb-4">
            <Card.Header>Sales</Card.Header>
            <Card.Body>
              <div className="chart-placeholder">Sales Chart</div>
            </Card.Body>
          </Card>
        </Col>
        <Col xs={12} lg={4}>
          <Card className="mb-4">
            <Card.Header>Visitors</Card.Header>
            <Card.Body>
              <div className="chart-placeholder">Visitors Map</div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Dashboard;
