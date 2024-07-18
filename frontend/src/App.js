import './App.css';
// import Dashboard from './features/dashboard/Dashboard';

import {Route, Router, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import FeedbackCategory from './features/feedbackCategories/FeedbackCategory';
import Feedback from './features/feedbacks/Feedback';
import AcademicResourceType from './features/academicResourceTypes/AcademicResourceType';
import AcademicResource from './features/academicResources/AcademicResource';
import Sidebar from './layout/Sidear';
import Dashboard from './layout/Dashboard';
import Header from './layout/Header';
import getCurrentProfile from './core/utils/current-profile';

function App() {
  const profile = getCurrentProfile();

  return (
    <div className="app">
      <Sidebar profile={profile} />
      <div className="content">
        <Header profile={profile} />
        <div className="main-content">
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
        </div>
      </div>
    </div>
  );
}

export default App;
