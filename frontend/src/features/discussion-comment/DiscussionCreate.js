import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {Modal, Form, Button } from 'react-bootstrap';
import apiClient from '../../core/setup/axios';

const DiscussionCreate = () => {
    const [discussion, setDiscussion] = useState({
        title: '',
        category_id: '',
        body: ''
    });

    const [category, setCategory] = useState([]);
    const [error, setError] = useState(null);
    const [selectedId, setSelectedId] = useState('');
    const [showModal, setShowModal] = useState(false);

    const onChangeData = (e) => {
        const { name, value } = e.target;
        setDiscussion(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSelectChange = (e) => {
        const id = e.target.value;
        setSelectedId(id);
        setDiscussion(prevState => ({
            ...prevState,
            category_id: id
        }));
    };


    useEffect(() => {
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
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // const response = await axios.post('http://localhost:8080/api/v1/students/discussion', discussion, config);
            const response = await apiClient.post(`/students/discussion`, discussion);

            if (response.status === 200) {
                console.log('Discussion created successfully');
                alert("Save Data Successfully!");
                setDiscussion({
                    title: '',
                    category_id: '',
                    body: ''
                });
                setShowModal(false);
                // Optionally, handle success state or redirect
            } 
        } catch (error) {
            console.error('Error creating discussion:', error);
            // Handle error state
        }
    };
    const handleShowModal = () => setShowModal(true);
    const handleCloseModal = () => setShowModal(false);

    return (
        // <Form onSubmit={handleSubmit}>
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
                <Form.Control
                    type="text"
                    name="title"
                    value={discussion.title}
                    onChange={onChangeData}
                />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Label>Body</Form.Label>
                <Form.Control
                    as="textarea"
                    rows={3}
                    name="body"
                    value={discussion.body}
                    onChange={onChangeData}
                />
            </Form.Group>
            <Button variant="success" onClick={handleShowModal}>
                Create Discussion
            </Button>

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

    );
};

export default DiscussionCreate;
