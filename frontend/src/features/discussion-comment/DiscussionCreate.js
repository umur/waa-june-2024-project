import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Button } from 'react-bootstrap';

const DiscussionCreate = () => {
    const [discussion, setDiscussion] = useState({
        title: '',
        category_id: '',
        body: ''
    });

    const token = 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJTVFVERU5UIl0sInN1YiI6InN0dWRlbnQxIiwiaWF0IjoxNzIxMTQxNzg0LCJleHAiOjE3MjExNDUzODR9.UQu1j2THQfE9POyhOXfUjuf8ipY3dSyNNHX-KrZImIc';

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    const [category, setCategory] = useState([]);
    const [error, setError] = useState(null);
    const [selectedId, setSelectedId] = useState('');

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
                const response = await axios.get(`http://localhost:8080/api/v1/discussion-category`, config);
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
            const response = await axios.post('http://localhost:8080/api/v1/students/discussion', discussion, config);

            if (response.status === 200) {
                console.log('Discussion created successfully');
                alert("Save Data Successfully!");
                setDiscussion({
                    title: '',
                    category_id: '',
                    body: ''
                });
                // Optionally, handle success state or redirect
            } else {
                console.error('Failed to create discussion');
                // Handle error state
            }
        } catch (error) {
            console.error('Error creating discussion:', error);
            // Handle error state
        }
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="exampleForm.SelectCustom">
                <Form.Label>Topic</Form.Label>
                <Form.Select value={selectedId} onChange={handleSelectChange}>
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
            <Button variant="success" type="submit">
                Create Discussion
            </Button>
        </Form>
    );
};

export default DiscussionCreate;
