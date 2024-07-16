import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import StudentDetail from './features/students/student-detail';
import UpdateStudentDetail from './features/students/update-student-detail';

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
    </Routes>
  );
}

export default App;
