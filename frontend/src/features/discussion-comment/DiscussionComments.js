import { Route, Routes, useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { Modal, Card } from 'react-bootstrap';
import { Container } from 'react-bootstrap';
import apiClient from '../../core/setup/axios';
import { API } from '../../core/constants';
import { useParams } from "react-router";
import SubComment from './SubComment';
// import { format } from 'date-fns';

const DiscussionComments = () => {

    const params = useParams();

    const [discussion, setDiscussion] = useState({});
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [editingCommentId, setEditingCommentId] = useState(null);
    const [editedComment, setEditedComment] = useState('');
    const [selectedCommentId, setSelectedCommentId] = useState(null);
    const [subComments, setSubComments] = useState([]);

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

    const handleShowModal = (id) => {
        setSelectedCommentId(id);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedCommentId(null);
    };


    const handleEditClick = (comment) => {
        setEditingCommentId(comment.id);
        setEditedComment(comment.comment);
    };

    const handleSaveClick = async (commentId, discussionId) => {
        try {
            const response = await apiClient.put(`/students/comments/${commentId}`, { comment: editedComment, discussionId: discussionId });
            // Update comments state with the updated comment
            setComments(comments.map(comment => comment.id === commentId ? response.data : comment));
            setEditingCommentId(null);
            window.location.reload();
        } catch (error) {
            console.error('Error updating comment:', error);
        }
    };

    const fetchComments = async () => {
        try {
            const response = await apiClient.get(`/students/discussion`);
            setComments(response.data.content);
        } catch (error) {
            console.error('Error fetching comments:', error);
        }
    };

    const handleViewCommentsClick = async (commentId) => {
        setSelectedCommentId(commentId);
        try {
            const response = await apiClient.get(`/students/${commentId}/sub-comments`);
            setSubComments(response.data.content);
        } catch (error) {
            console.error('Error fetching subcomments:', error);
        }
    };

    return (
        <>
            <Container>
                <Card className="mt-3">
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
                                                    {/* <Card.Title className="mb-1" style={{ fontSize: '0.9rem' }}>{comment.student.firstName} {comment.student.lastName}</Card.Title>
                                                    <Card.Text style={{ fontSize: '0.8rem' }}>{comment.comment}</Card.Text> */}

                                                    <div style={{ flex: 1 }}>
                                                        {editingCommentId === comment.id ? (
                                                            <>
                                                                <Form.Control
                                                                    type="text"
                                                                    value={editedComment}
                                                                    onChange={(e) => setEditedComment(e.target.value)}
                                                                    style={{ fontSize: '0.8rem', marginBottom: '10px' }}
                                                                />

                                                                <Button
                                                                    variant="secondary"
                                                                    size="sm"
                                                                    onClick={() => setEditingCommentId(null)}
                                                                    className="me-2"
                                                                >
                                                                    Cancel
                                                                </Button>
                                                                <Button
                                                                    variant="primary"
                                                                    size="sm"
                                                                    onClick={() => handleSaveClick(comment.id, comment.discussionId)}
                                                                    className="me-2"
                                                                >
                                                                    Save
                                                                </Button>
                                                            </>
                                                        ) : (
                                                            <>
                                                                <Card.Title className="mb-1" style={{ fontSize: '0.9rem' }}>{comment.student.firstName} {comment.student.lastName}</Card.Title>
                                                                <Card.Text style={{ fontSize: '0.8rem' }}>{comment.comment}</Card.Text>
                                                            </>
                                                        )}

                                                        <Card.Text
                                                            className="text-primary me-2"
                                                            style={{ fontSize: '0.8rem', cursor: 'pointer' }}
                                                            onClick={() => handleViewCommentsClick(comment.id)}
                                                        >
                                                            view all comments
                                                        </Card.Text>
                                                        <div className="ms-auto d-flex">

                                                            <Card.Text
                                                                className="text-primary me-2"
                                                                style={{ fontSize: '0.8rem', cursor: 'pointer' }}
                                                            >
                                                                Reply
                                                            </Card.Text>
                                                            {comment.own && (

                                                                // editingCommentId !== comment.id && (
                                                                    <>
                                                                        <Card.Text
                                                                            className="text-primary me-2"
                                                                            style={{ fontSize: '0.8rem', cursor: 'pointer' }}
                                                                            onClick={() => handleEditClick(comment)}
                                                                        >
                                                                            Edit
                                                                        </Card.Text>
                                                                        <Card.Text
                                                                            className="text-danger"
                                                                            style={{ fontSize: '0.8rem', cursor: 'pointer' }}
                                                                            onClick={() => handleShowModal(comment.id)}
                                                                        >
                                                                            Delete
                                                                        </Card.Text>

                                                                    </>
                                                                // )

                                                            )}
                                                        </div>
                                                        {selectedCommentId === comment.id && <SubComment subComments={subComments} />}

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