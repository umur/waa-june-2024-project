import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import Events from './features/events';
import {ProtectedRoute} from './features/authentication/protected-route/protectedRoute';
import EventDetails from './core/component/events/EventDetails';
import StudentDetail from './features/students/student-detail';
import UpdateStudentDetail from './features/students/update-student-detail';
import Feedback from './features/feedbacks/Feedback';
import AcademicResource from './features/academicResources/AcademicResource';
import AcademicResourceType from './features/academicResourceTypes/AcademicResourceType';
import FeedbackCategory from './features/feedbackCategories/FeedbackCategory';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />
      <Route
        path="/events"
        element={
          <ProtectedRoute>
            <Events />
          </ProtectedRoute>
        }
      />
      <Route
        path="/events/:id"
        element={
          <ProtectedRoute>
            <EventDetails />
          </ProtectedRoute>
        }
      />
      <Route path="/student/:username" element={<StudentDetail />} />
      <Route path="/profile/update" element={<UpdateStudentDetail />} />
      <Route path="/profile" element={<StudentDetail />} />
      <Route path="/login" element={<Login />} />
      <Route path="/feedbacks" element={<Feedback />} />
      <Route path="/feedback-categories" element={<FeedbackCategory />} />
      <Route path="/academic-resouce-type" element={<AcademicResourceType />} />
      <Route path="/academic-resouces" element={<AcademicResource />} />
    </Routes>
  );
}

export default App;
