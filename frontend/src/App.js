import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import FeedbackCategory from './features/feedbackCategories/FeedbackCategory';
import Feedback from './features/feedbacks/Feedback';
import AcademicResourceType from './features/academicResourceTypes/AcademicResourceType';
import AcademicResource from './features/academicResources/AcademicResource';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />

      <Route path="/feedback-categories" element={<FeedbackCategory />} />
      <Route path="/feedbacks" element={<Feedback />} />
      <Route path="/academic-resouce-type" element={<AcademicResourceType />} />
      <Route path="/academic-resouces" element={<AcademicResource />} />
    </Routes>
  );
}

export default App;
