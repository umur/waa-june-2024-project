import {useNavigate} from 'react-router';
import {setTokens} from '../../core/setup/token';
import getCurrentProfile from '../../core/utils/current-profile';
import {Roles} from '../../core/constants';
import StudentDashboard from './dashboard-student';
import AdminDashboard from './dashboard-admin';

export default function Dashboard() {
  const navigate = useNavigate();
  const profile = getCurrentProfile();

  if (profile === null || profile === undefined) {
    navigate('/login');
  }

  return (
    <>
      {profile && profile.role === Roles.ADMIN ? <AdminDashboard /> :  <StudentDashboard />}
      <p>{JSON.stringify(getCurrentProfile())}</p>
      <button
        onClick={() => {
          setTokens();

          navigate('/login');
        }}
      >
        Logout
      </button>
    </>
  );
}
