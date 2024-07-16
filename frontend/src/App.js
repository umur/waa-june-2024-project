import './App.css';
import Dashboard from './features/dashboard/Dashboard';
import {Route, Routes} from 'react-router';
import Login from './features/authentication/login/Login';
import Register from './features/authentication/register/Register';
import Students from './features/students/Students';
import Discussion from './features/discussion-comment/Discussion';
import DiscussionEdit from './features/discussion-comment/DiscussionEdit';
import DiscussionCreate from './features/discussion-comment/DiscussionCreate';

function App() {
  return (
    <Routes>
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/" element={<Discussion />} />
      <Route path="/discussion-edit/:id" element={<DiscussionEdit />} />
      <Route path="/discussion-create" element={<DiscussionCreate />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />
    </Routes>
  );
}

export default App;
