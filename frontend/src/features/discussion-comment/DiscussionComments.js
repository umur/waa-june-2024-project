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
import { useParams } from "react-router";
// import { format } from 'date-fns';

const DiscussionComments = () => {

    const params = useParams();

    const [discussion, setDiscussion] = useState({});
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);

    const getDisucssion = async () => {

        try {
            const response = await apiClient.get(`/students/discussion/${params.id}`);
            console.log(response.data); // Log the entire response
            if (response && response.data) {
                setDiscussion(response.data);
            }
        } catch (err) {
            setError('Error fetching the discussions');
            console.error('Error fetching the discussions:', err);
        }
    }

    const getComments = async () => {

        try {
            const response = await apiClient.get(`/students/${params.id}/comments`);
            console.log(response.data); // Log the entire response
            if (response && response.data) {
                setComments(response.data.content);
            }
        } catch (err) {
            setError('Error fetching the discussions');
            console.error('Error fetching the discussions:', err);
        }
    }

    useEffect(() => {
        getDisucssion();
        getComments();
    }, [discussion.id]);

    const handleAddComment = async () => {
        if (!newComment.trim()) return;

        try {
            const response = await apiClient.post(`/students/${params.id}/comments`, { comment: newComment });
            setComments([...comments, response.data]);
            setNewComment('');
            window.location.reload();
        } catch (err) {
            console.error('Error adding comment:', err);
        }
    };

    const handleDelete = async (id) => {

        // console.log(id)

        try {
            const response = await apiClient.delete(`/students/comments/${selectedCommentId}`);
            window.location.reload();
        } catch (err) {
            console.error('Error adding comment:', err);
        }
    };

    const [selectedCommentId, setSelectedCommentId] = useState(null);

    const handleShowModal = (id) => {
        setSelectedCommentId(id);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedCommentId(null);
    };

    return (
        <>
            <Container>
                <Card>
                    <Card.Header as="h3" style={{ display: 'flex', justifyContent: 'space-between' }}>
                        <span style={{ display: 'flex', alignItems: 'center' }}>
                            {discussion.student && (
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
                                        marginRight: '10px',
                                    }}
                                />
                            )}
                            {discussion.student && `${discussion.student.firstName}, ${discussion.student.lastName}`}
                        </span>
                    </Card.Header>
                    <Card.Body>
                        <Card.Title>{discussion.title}</Card.Title>
                        <Card.Subtitle className="mb-2 text-muted small">{discussion.category?.name}</Card.Subtitle>
                        <Card.Text>{discussion.body}</Card.Text>
                        <Card.Link href="">Comments</Card.Link>
                        <hr />

                        <Container>


                            <Form className="mt-3">
                                <Form.Group controlId="comment" className="d-flex align-items-center">
                                    <Form.Control
                                        type="text"
                                        value={newComment}
                                        onChange={(e) => setNewComment(e.target.value)}
                                        placeholder='Add a comment'
                                        className="me-2"
                                    />
                                    <Button variant="primary" onClick={handleAddComment}>
                                        Submit
                                    </Button>
                                </Form.Group>

                            </Form>
                            <div className="mt-4">
                                {comments.length > 0 ? (
                                    comments.map((comment, index) => (
                                        // <Card key={index} className="mb-2">
                                        //     <Card.Body>
                                        //         <Card.Text>{comment.comment}</Card.Text>
                                        //     </Card.Body>
                                        // </Card>


                                        <Card key={index} className="mb-2">
                                            <Card.Body className="d-flex align-items-start">
                                                <Card.Img
                                                    src={`${API.baseURL}${comment.student.picture}`}
                                                    alt={comment.student.picture}
                                                    style={{
                                                        width: '30px',
                                                        height: '30px',
                                                        objectFit: 'cover',
                                                        borderRadius: '50%',
                                                        marginRight: '10px'
                                                    }}
                                                />
                                                <div>
                                                    <Card.Title className="mb-1" style={{ fontSize: '0.9rem' }}>{comment.student.firstName} {comment.student.lastName}</Card.Title>
                                                    <Card.Text style={{ fontSize: '0.8rem' }}>{comment.comment}</Card.Text>

                                                    <div className="ms-auto d-flex"> {/* Align to the right */}
                                                        <Card.Text className="text-primary me-2" style={{ fontSize: '0.8rem' }}>Edit</Card.Text>
                                                        <Card.Text className="text-danger" style={{ fontSize: '0.8rem' }} onClick={() => handleShowModal(comment.id)}>Delete</Card.Text>
                                                    </div>
                                                </div>
                                            </Card.Body>
                                        </Card>
                                    ))
                                ) : (
                                    <p>No comments yet.</p>
                                )}
                            </div>
                            <Modal show={showModal} onHide={handleCloseModal}>
                                <Modal.Header closeButton>
                                    <Modal.Title>Confirm Delete</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>Are you sure you want to delete this comment?</Modal.Body>
                                <Modal.Footer>
                                    <Button variant="secondary" onClick={handleCloseModal}>
                                        Cancel
                                    </Button>
                                    <Button variant="primary" onClick={handleDelete}>
                                        Okay
                                    </Button>
                                </Modal.Footer>
                            </Modal>
                        </Container>
                    </Card.Body>
                </Card>
            </Container>
        </>
    )
}

export default DiscussionComments;