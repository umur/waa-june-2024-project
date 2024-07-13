import {useNavigate} from 'react-router';
import {useEffect} from 'react';
import {setTokens} from '../../core/setup/token';

export default function Dashboard() {
  var accessToken = localStorage.getItem('access-token');
  const navigate = useNavigate();

  useEffect(() => {
    console.log(accessToken === null || accessToken === undefined || accessToken === '');

    if (!accessToken) {
      navigate('/login');
    }
  }, [accessToken]);

  return (
    <>
      Dashboard Page
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
