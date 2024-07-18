import {Container, Nav, Navbar, NavDropdown} from 'react-bootstrap';
import getCurrentProfile from '../utils/current-profile';
import {Roles} from '../constants';

export default function NavBar() {
  const profile = getCurrentProfile();

  return (
    <Navbar expand="lg" className="bg-primary">
      <Container>
        <Navbar.Brand href="/" className="text-white">
          University Connect
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/" className="text-white">
              Home
            </Nav.Link>
            <Nav.Link href="/students" className="text-white">
              Students
            </Nav.Link>
            <Nav.Link href="/events" className="text-white">
              Events
            </Nav.Link>
            <Nav.Link href="/resources" className="text-white">
              Resources
            </Nav.Link>

            <Nav.Link href="/feedbacks" className="text-white">
              Feedback
            </Nav.Link>

            <Nav.Link href="/academic-resouces" className="text-white">
              Academic Resources
            </Nav.Link>
          </Nav>
          <Nav>
            <NavDropdown title="Profile" id="profile-dropdown" align="end" className="text-white">
              {profile && profile.role !== Roles.ADMIN && (
                <NavDropdown.Item href="/profile">View Profile</NavDropdown.Item>
              )}
              <NavDropdown.Item href="/login">Logout</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
