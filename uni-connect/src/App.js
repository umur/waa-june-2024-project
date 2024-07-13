import './App.css';
import { useContext, useEffect } from 'react';
import Home from './page/home';
import { AuthContext } from './context/AuthContext';
import Login from './page/auth/Login';

function App() {
  const { isLoggedIn } = useContext(AuthContext);

  useEffect(() => {

  }, []);

  return (
    <>
      {isLoggedIn ? (
        <Home />
      ) : (
        <>
          <Login />
        </>
      )}
    </>
  );
}

export default App;
