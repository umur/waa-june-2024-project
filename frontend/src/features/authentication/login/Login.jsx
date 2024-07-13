import React, {useState} from 'react';
import {Button, ButtonGroup, Col, Container, Form, Row, Spinner} from 'react-bootstrap';
import {useNavigate} from 'react-router';
import {State} from '../../../core/constants';
import axios from 'axios';
import {setTokens} from '../../../core/setup/token';
import ErrorDialog from '../../../core/component/dialogs/ErrorDialog';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  setTokens();

  const navigate = useNavigate();

  const [loginState, setLoginState] = useState({
    status: State.IDLE,
    error: null
  });

  const login = async () => {
    setLoginState({...loginState, status: State.LOADING});

    axios
      .post('/auth/login', {
        username: username,
        password: password
      })
      .then(res => {
        if (res.status === 200) {
          setLoginState({...loginState, status: State.SUCCEEDED});

          setTokens(res.data.accessToken, res.data.refreshToken);

          navigate('/');
        } else if (res.status === 401) {
          setLoginState({
            status: State.FAILED,
            error: res.data.message
          });
        } else {
          setLoginState({
            status: State.FAILED,
            error: 'Something went wrong. Please try again later.'
          });
        }
      })
      .catch(err => {
        if (err.response.status === 401) {
          setLoginState({
            status: State.FAILED,
            error: err.response.data.message || 'Something went wrong. Please try again later.'
          });
        } else {
          setLoginState({
            status: State.FAILED,
            error: 'Something went wrong. Please try again later.'
          });
        }
      });
  };

  const handleSubmit = e => {
    e.preventDefault();
    // Handle login logic here
    console.log('Email:', username);
    console.log('Password:', password);

    login();
  };

  return (
    <Container className="justify-content-center align-items-center">
      <Row className="justify-content-center min-vh-100 m-4">
        <Col md="4">
          <h2 className="text-center">Login</h2>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formBasicUsername">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter username"
                value={username}
                onChange={e => setUsername(e.target.value)}
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={e => setPassword(e.target.value)}
              />
            </Form.Group>

            <ButtonGroup className="d-flex mt-2">
              <Button
                variant="primary"
                type="submit"
                className="flex-fill me-2 rounded"
                disabled={loginState.status === State.LOADING}
              >
                {loginState.status === State.LOADING ? (
                  <Spinner animation="border" size="sm" role="status" aria-hidden="true" />
                ) : (
                  'Login'
                )}
              </Button>

              <Button
                variant="outline-primary"
                type="button"
                className="flex-fill ms-2 rounded"
                onClick={() => {
                  navigate('/register');
                }}
                disabled={loginState.status === State.LOADING}
              >
                Register
              </Button>
            </ButtonGroup>
          </Form>
        </Col>
      </Row>
      <ErrorDialog
        show={loginState.status === State.FAILED}
        errorMessage={loginState.error}
        handleClose={() => {
          setLoginState({...loginState, status: State.IDLE});
        }}
      />
    </Container>
  );
};

export default Login;
