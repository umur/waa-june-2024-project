import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import StudentDetail from './features/students/student-detail';
import UpdateStudentDetail from './features/students/update-student-detail';
import Discussion from './features/discussion-comment/Discussion';
import DiscussionCreate from './features/discussion-comment/DiscussionCreate';
import DiscussionEdit from './features/discussion-comment/DiscussionEdit';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />
      <Route path="/student/:username" element={<StudentDetail />} />
      <Route path="/profile/update" element={<UpdateStudentDetail />} />
      <Route path="/profile" element={<StudentDetail />} />

      <Route path="/discussions" element={<Discussion />} />
      <Route path="/discussion-create" element={<DiscussionCreate />} />
      <Route path="/discussion-edit/:id" element={<DiscussionEdit />} />
    </Routes>
  );
}

export default App;
