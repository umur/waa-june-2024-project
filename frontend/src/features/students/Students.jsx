import {useNavigate} from 'react-router';
import getCurrentProfile from '../../core/utils/current-profile';
import {useEffect} from 'react';

export default function Students() {
  const navigate = useNavigate();
  const profile = getCurrentProfile();

  useEffect(() => {
    if (profile === null || profile === undefined) {
      navigate('/login');
    }
  }, [profile, navigate]);

  return (
    <>
      <h3>Here lies list of students</h3>
    </>
  );
}
