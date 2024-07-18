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
import { Modal, Card } from 'react-bootstrap';
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { Container } from 'react-bootstrap';
import apiClient from '../../core/setup/axios';
import { API } from '../../core/constants';
import NavBar from '../../core/component/NavBar';
// import { format } from 'date-fns';

const Discussion = () => {

    const [discussions, setDiscussions] = useState({
        content: [],
        page: {
            size: 1,
            number: 0,
            totalElements: 0,
            totalPages: 0
        }
    });
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');

    const getList = async () => {

        try {
            const response = await apiClient.get(`/students/discussion?page=${discussions.page.number}`);
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
    }

    useEffect(() => {
        getList();
    }, []);

    const [selectedDiscussionId, setSelectedDiscussionId] = useState(null);

    const handleShowModal = (id) => {
        setSelectedDiscussionId(id);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedDiscussionId(null);
    };

    const handleDelete = async () => {
        try {
            // const response = await apiClient.get(`/students/discussion?page=${discussions.page.number}`);
            await apiClient.delete(`/students/discussion/${selectedDiscussionId}`);
            // Optionally, refresh the discussion list here or update the state to remove the deleted item
            setShowModal(false);
            setSelectedDiscussionId(null);
            window.location.reload();
            // Code to update the discussion list state, if needed
        } catch (error) {
            console.error('Delete failed:', error);
        }
    };

    const handleSearch = async () => {
        try {
            const response = await apiClient.get(`/students/discussion/search?text=${searchTerm}`);
            console.log('Search API Response:', response); // Log search response
            if (response && response.data && response.data.content) {
                setDiscussions(response.data.content);
            } else {
                setDiscussions([]);
                setError('No results found');
            }
        } catch (err) {
            setError('Error searching discussions');
            console.error('Error searching discussions:', err);
        }
    };

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            handleSearch();
        }
    };

    const handleClearSearch = () => {
        setSearchTerm('');
        setError(null); // Clear any previous errors
        window.location.reload();
    };


    return (
        <>

            <NavBar />
            <Container>
                <Col xs="auto" className="mt-3">
                    <InputGroup>
                        <Form.Control
                            type="text"
                            placeholder="Search"
                            className="me-1" // Small margin to the right
                            style={{ borderRadius: '10px' }} // Curly borders
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            onKeyPress={handleKeyPress}
                        />
                        {searchTerm && ( // Show clear icon only if there's something to clear
                            <Button variant="link" onClick={handleClearSearch}>
                                <p>clear</p>
                            </Button>
                        )}
                    </InputGroup>
                </Col>

                <Card className="mt-3">
                    {error ? (
                        <p>{error}</p>
                    ) : (
                        discussions.length > 0 ? (
                            discussions.map((discussion, index) => (
                                <>
                                    <Card.Header as="h3" style={{ display: 'flex', justifyContent: 'space-between' }}>
                                        <span>
                                            <Card.Img
                                                variant="top"
                                                src={`${API.baseURL}${discussion.student.picture}`}
                                                alt={discussion.student.picture}
                                                style={{
                                                    width: '50px', // Small icon size
                                                    height: '50px', // Small icon size
                                                    objectFit: 'cover',
                                                    borderRadius: '50%', // Rounded shape
                                                    overflow: 'hidden',
                                                    margin: '10px auto' // Center the image
                                                }}
                                            />
                                            {discussion.student.firstName}, {discussion.student.lastName}
                                        </span>
                                        {discussion.own && (
                                        <DropdownButton id="dropdown-basic-button" title="...">
                                            <Dropdown.Item href={`/discussion-edit/${discussion.id}`}>Edit</Dropdown.Item>
                                            <Dropdown.Item onClick={() => handleShowModal(discussion.id)}>Delete</Dropdown.Item>
                                        </DropdownButton>
                                        )}
                                    </Card.Header>
                                    <Card.Body>
                                        <Card.Title>{discussion.title}</Card.Title>
                                        <Card.Subtitle className="mb-2 text-muted small">{discussion.category.name}</Card.Subtitle>
                                        <Card.Text>{discussion.body}</Card.Text>
                                        <Card.Link href={`/discussion-comments/${discussion.id}`}>Comments</Card.Link>
                                        {/* <Card.Link href="#">Another Link</Card.Link> */}
                                    </Card.Body>

                                    <hr />
                                </>
                            ))
                        ) : (
                            <p>No discussions...</p>
                        )
                    )}
                </Card >
                <Modal show={showModal} onHide={handleCloseModal}>
                    <Modal.Header closeButton>
                        <Modal.Title>Confirm Delete</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Are you sure you want to delete this discussion?</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleCloseModal}>
                            Cancel
                        </Button>
                        <Button variant="primary" onClick={handleDelete}>
                            Okay
                        </Button>
                    </Modal.Footer>
                </Modal>
            </Container >

        </>
    )
}

export default Discussion