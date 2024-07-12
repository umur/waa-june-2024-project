import "./App.css";
import Dashboard from "./features/dashboard/Dashboard";
import {Route, Routes} from "react-router";
import Login from "./features/authentication/login/Login";
import Register from "./features/authentication/register/Register";
import Students from "./features/students/Students";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/students" element={<Students />} />
    </Routes>
  );
}

export default App;
