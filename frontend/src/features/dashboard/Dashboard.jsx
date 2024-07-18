import {useNavigate} from 'react-router';
import {useEffect} from 'react';
import {setTokens} from '../../core/setup/token';
import getCurrentProfile from '../../core/utils/current-profile';
import {Roles} from '../../core/constants';
import StudentDashboard from './dashboard-student';
import AdminDashboard from './dashboard-admin';

export default function Dashboard() {
  const navigate = useNavigate();

  const profile = getCurrentProfile();

  useEffect(() => {
    if (profile === null || profile === undefined) {
      navigate('/login');
    }
  }, [profile]);

  return <>{profile && profile.role === Roles.ADMIN ? <StudentDashboard /> : <AdminDashboard />}</>;
}
