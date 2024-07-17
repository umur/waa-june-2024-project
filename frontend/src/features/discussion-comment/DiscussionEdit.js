import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import { useParams } from "react-router";
import { getTokens } from '../../core/setup/token';
import {Modal,Button} from 'react-bootstrap';
import { Container } from 'react-bootstrap';
import apiClient from '../../core/setup/axios';

const DiscussionEdit = (props) => {

    const params = useParams();

    const [discussion, setDiscussion] = useState({
        title: '',
        category_id: '',
        body: ''
    });
    const [selectedId, setSelectedId] = useState('');
    const [category, setCategory] = useState([]);
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);

    const handleSelectChange = (e) => {
        const id = e.target.value;
        setSelectedId(id);
        setDiscussion(prevState => ({
            ...prevState,
            category_id: id
        }));
    };

    useEffect(() => {
        const fetchDiscussions = async () => {
            try {
                // const response = await axios.get(`http://localhost:8080/api/v1/students/discussion/${params.id}`, config);
                const response = await apiClient.get(`/students/discussion/${params.id}`);
                console.log(response.data);
                if (response.data) {
                    setDiscussion({
                        title: response.data.title,
                        category_id: response.data.category.id,
                        body: response.data.body
                    });
                } else {
                    setError('No data available');
                }
            } catch (err) {
                setError('Error fetching the discussions');
                console.error('Error fetching the discussions:', err);
            }
        };

        fetchDiscussions();

        const fetchCategory = async () => {
            try {
                // const response = await axios.get(`http://localhost:8080/api/v1/discussion-category`, config);
                const response = await apiClient.get(`/discussion-category`);
                console.log(response.data);
                if (response.data) {
                    setCategory(response.data);
                } else {
                    setError('No data available');
                }
            } catch (err) {
                setError('Error fetching the discussions');
                console.error('Error fetching the discussions:', err);
            }
        };

        fetchCategory();
    }, [discussion.id]);

    const onChangeData = (event) => {

        setDiscussion({ ...discussion, [event.target.name]: event.target.value });

    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Example of sending data to backend using fetch or axios
            // const response = await axios.put(`http://localhost:8080/api/v1/students/discussion/${params.id}`, discussion, config);
            const response = await apiClient.put(`/students/discussion/${params.id}`, discussion);

            if (response.status === 200) {
                console.log('Discussion updated successfully');
                setShowModal(false);
            } 
        } catch (error) {
            console.error('Error updating discussion:', error);
        }
    };

    const handleShowModal = () => setShowModal(true);
    const handleCloseModal = () => setShowModal(false);

    return (
        <Container>
            <div className="mt-3">
                <h1>Editing</h1>


                <Form>

                    <Form.Group className="mb-3" controlId="exampleForm.SelectCustom">
                        <Form.Label>Topic</Form.Label>
                        <Form.Select value={discussion.category_id} onChange={handleSelectChange}>
                            <option value="">Select...</option>
                            {category.map((c) => (
                                <option key={c.id} value={c.id}>
                                    {c.name}
                                </option>
                            ))}
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Title</Form.Label>
                        <Form.Control type="text" name='title' value={discussion.title} onChange={onChangeData} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Body</Form.Label>
                        <Form.Control as="textarea" rows={3} name='body' value={discussion.body} onChange={onChangeData} />
                    </Form.Group>
                    <Button variant="success" onClick={handleShowModal}>Save</Button>{' '}

                    <Modal show={showModal} onHide={handleCloseModal}>
                        <Modal.Header closeButton>
                            <Modal.Title>Confirm Save</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>Are you sure you want to save?</Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={handleCloseModal}>
                                Cancel
                            </Button>
                            <Button variant="primary" onClick={handleSubmit}>
                                Okay
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </Form>


            </div >
        </Container>
    );
};

export default DiscussionEdit;
