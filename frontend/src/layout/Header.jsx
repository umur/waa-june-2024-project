import React from 'react';
import {Navbar, Nav, Form, FormControl, Button} from 'react-bootstrap';
import './Header.css';
import {setTokens} from '../core/setup/token';
import {useNavigate} from 'react-router';

const Header = ({profile}) => {
  const navigate = useNavigate();

  return (
    <Navbar bg="light" expand="lg" className="header">
      <Navbar.Brand href="#home"></Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="mr-auto">
          <Nav.Link href="#home">Home</Nav.Link>
          <Nav.Link to="/feedback-categories">Contact</Nav.Link>
        </Nav>
        <Nav>
          <Form className="mr-sm-2">
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            {/* <Button variant="outline-success">Search</Button> */}
          </Form>
        </Nav>
        <span className="navbar-text mr-sm-2 m-10"> {profile && <> Welcome {profile.user} </>}</span>

        <Button
          variant="outline-success"
          onClick={() => {
            setTokens();
            navigate('/login');
          }}
        >
          {profile ? 'Logout' : 'Login'}
        </Button>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
