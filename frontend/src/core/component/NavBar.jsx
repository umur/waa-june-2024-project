import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import getCurrentProfile from '../utils/current-profile';
import { Roles } from '../constants';

export default function NavBar({onLogout}) {
  const profile = getCurrentProfile();

  return (
    <Navbar expand="lg" className="bg-primary">
      <Container>
        {!profile ? (
          <>
           <Navbar.Brand href="/login" className="text-white">
           University Connect
         </Navbar.Brand>
           <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/login" className="text-white">
              Login
            </Nav.Link>
            <Nav.Link href="/register" className="text-white">
              Register
            </Nav.Link>
            </Nav>
            </Navbar.Collapse>
          
          </>
          
         ):(
          <>
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
            <Nav.Link href="/academic-resouces" className="text-white">
              Resources
            </Nav.Link>
            <Nav.Link href="/discussions" className="text-white">
              Discussions
            </Nav.Link>
            <Nav.Link href="/discussion-create" className="text-white">
              Post
            </Nav.Link>
            
          </Nav>
          <Nav>
            <NavDropdown title="Profile" id="profile-dropdown" align="end" className="text-white">
              {profile && profile.role !== Roles.ADMIN && (
                <NavDropdown.Item href="/profile">View Profile</NavDropdown.Item>
              )}
              <NavDropdown.Item href="/login" onClick={onLogout}>Logout</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
        </>
        )
}
      </Container>
    </Navbar>
  );
}
