import {Link} from 'react-router-dom';

export default function StudentDashboard() {
  return (
    <>
      <h3>Student Dashboard</h3>
      <ul>
        <li>
          <Link to="/students">Student</Link>
        </li>
      </ul>
    </>
  );
}
