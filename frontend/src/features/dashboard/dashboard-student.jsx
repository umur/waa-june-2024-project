import {Link} from 'react-router-dom';
import NavBar from '../../core/component/NavBar';

export default function StudentDashboard() {
  return (
    <>
      <NavBar />
      <h3>Student Dashboard</h3>
      <ul>
        <li>
          <Link to="/students">Student</Link>
        </li>
      </ul>
    </>
  );
}
