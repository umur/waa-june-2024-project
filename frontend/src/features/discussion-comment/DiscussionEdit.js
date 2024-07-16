import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form';
import { useParams } from "react-router";
import { getTokens } from '../../core/setup/token';
import Button from 'react-bootstrap/Button';

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

    const handleSelectChange = (e) => {
        const id = e.target.value;
        setSelectedId(id);
        setDiscussion(prevState => ({
            ...prevState,
            category_id: id
        }));
    };

    const token = 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJTVFVERU5UIl0sInN1YiI6InN0dWRlbnQxIiwiaWF0IjoxNzIxMTQxNzg0LCJleHAiOjE3MjExNDUzODR9.UQu1j2THQfE9POyhOXfUjuf8ipY3dSyNNHX-KrZImIc';

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    useEffect(() => {
        const fetchDiscussions = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/v1/students/discussion/${params.id}`, config);
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
    }, [discussion.id]);

    const onChangeData = (event) => {

        setDiscussion({ ...discussion, [event.target.name]: event.target.value });

    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Example of sending data to backend using fetch or axios
            const response = await axios.put(`http://localhost:8080/api/v1/students/discussion/${params.id}`, discussion, config);

            if (response.status === 200) {
                console.log('Discussion updated successfully');
                // Optionally, handle success state or redirect
            } else {
                console.error('Failed to update discussion');
                // Handle error state
            }
        } catch (error) {
            console.error('Error updating discussion:', error);
            // Handle error state
        }
    };

    return (
        <div>
            <h1>Discussions Edit</h1>

            <Form onSubmit={handleSubmit}>

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
                <Button variant="success" type="submit">Save</Button>{' '}
            </Form>

        </div>
    );
};

export default DiscussionEdit;
