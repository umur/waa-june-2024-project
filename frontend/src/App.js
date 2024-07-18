import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes, useNavigate} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import Events from './features/events';
import { ProtectedRoute } from './features/authentication/protected-route/protectedRoute';
import EventDetails from './features/events/EventDetails';
import StudentDetail from './features/students/student-detail';
import UpdateStudentDetail from './features/students/update-student-detail';
import UserEvents from './features/events/UserEvents';
import EventAttendees from './features/events/EventAttendees';
import Discussion from './features/discussion-comment/Discussion';
import DiscussionCreate from './features/discussion-comment/DiscussionCreate';
import DiscussionEdit from './features/discussion-comment/DiscussionEdit';
import DiscussionComments from './features/discussion-comment/DiscussionComments';
import NavBar from './core/component/NavBar';
import AcademicResource from './features/academicResources/AcademicResource';

function App() {

  const navigate = useNavigate();
  const logout = () => {
    localStorage.clear();
    navigate('/login');
  }
  return (
    <>
    <NavBar onLogout={logout}/>
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />
      <Route path="/events" element={<ProtectedRoute><Events /></ProtectedRoute>} />
      <Route path="/events/:id" element={<ProtectedRoute><EventDetails /></ProtectedRoute>} />
      <Route path="/my-events" element={<ProtectedRoute><UserEvents /></ProtectedRoute>} />
      <Route path="/event-attendees/:id" element={<ProtectedRoute><EventAttendees /></ProtectedRoute>} />
      <Route path="/student/:username" element={<StudentDetail />} />
      <Route path="/profile/update" element={<UpdateStudentDetail />} />
      <Route path="/profile" element={<StudentDetail />} />
      <Route path="/academic-resouces" element={<AcademicResource />} />
      <Route path="/discussions" element={<Discussion />} />
      <Route path="/discussion-create" element={<DiscussionCreate />} />
      <Route path="/discussion-edit/:id" element={<DiscussionEdit />} />
      <Route path="/discussion-comments/:id" element={<DiscussionComments />} />
    </Routes>
    </>
  );
}

export default App;
