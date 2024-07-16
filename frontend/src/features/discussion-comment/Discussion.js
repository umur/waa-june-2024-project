import { Route, Routes, useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
// import Nav from 'react-bootstrap/Nav';
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import axios, { get } from "axios";
import Card from 'react-bootstrap/Card';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { Container } from 'react-bootstrap';
// import { format } from 'date-fns';

const Discussion = () => {
    const token = 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJTVFVERU5UIl0sInN1YiI6InN0dWRlbnQxIiwiaWF0IjoxNzIxMTQxNzg0LCJleHAiOjE3MjExNDUzODR9.UQu1j2THQfE9POyhOXfUjuf8ipY3dSyNNHX-KrZImIc';

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    const [discussions, setDiscussions] = useState([]);
    const [error, setError] = useState(null);

    const getList = async () => {

        try {
            const response = await axios.get("http://localhost:8080/api/v1/students/discussion", config)
            console.log('API Response:', response); // Log the entire response
            if (response && response.data && response.data.content) {
                setDiscussions(response.data.content);
            } else {
                setError('No data available');
                console.error('No data available in response:', response);
            }
        } catch (err) {
            setError('Error fetching the discussions');
            console.error('Error fetching the discussions:', err);
        }
        // const result = axios.get("http://localhost:8080/api/v1/students/discussion", config)
        //     .then(res => console.log(res.data))
        //     .catch((error) => console.log("Error Handing ..........................." + error));
        // setDiscussions(result.data);
    }

    useEffect(() => {
        getList();
    }, []);

    return (
        <>

            <Navbar className="bg-body-tertiary justify-content-between">
                <Form inline>
                    <Row>
                        <Col xs="auto">
                            <Form.Control
                                type="text"
                                placeholder="Search"
                                className=" mr-lg-1"
                            />
                        </Col>
                        <Col xs="auto">
                            <Button type="submit">Search</Button>
                        </Col>
                    </Row>
                </Form>
                <Form inline>
                    <InputGroup>
                        <Nav activeKey="/home">
                            <Nav.Item>
                                <Nav.Link href="/home">Feed</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link href="/post">Post</Nav.Link>
                            </Nav.Item>
                            <Nav.Item>
                                <Nav.Link href="/profile">Pofile</Nav.Link>
                            </Nav.Item>
                        </Nav>
                    </InputGroup>
                </Form>
            </Navbar>
            <Container>
            <Card>

                {error ? (
                    <p>{error}</p>
                ) : (
                    discussions.length > 0 ? (
                        discussions.map((discussion, index) => (
                            <>
                                <Card.Header as="h3" style={{ display: 'flex', justifyContent: 'space-between' }}>
                                    {discussion.student.username}
                                    <DropdownButton id="dropdown-basic-button" title="...">
                                        <Dropdown.Item href={`/discussion-edit/${discussion.id}`}>Edit</Dropdown.Item>
                                        <Dropdown.Item href="/discussion-delete">Delete</Dropdown.Item>
                                    </DropdownButton>
                                </Card.Header>
                                <Card.Body>
                                    <Card.Title>{discussion.title}</Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">{discussion.category.name}</Card.Subtitle>
                                    <Card.Text>{discussion.body}</Card.Text>
                                    <Card.Link href="#">Comments</Card.Link>
                                    {/* <Card.Link href="#">Another Link</Card.Link> */}
                                </Card.Body>

                                <hr />
                            </>
                        ))
                    ) : (
                        <p>Loading discussions...</p>
                    )
                )}
            </Card >
            </Container>

        </>
    )
}

export default Discussion