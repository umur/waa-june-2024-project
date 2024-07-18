import React from 'react';
import { API } from '../../core/constants';
import { Card, Container } from 'react-bootstrap';

const SubComment = ({ subComments }) => {
    return (
        <>
            {subComments.map((subComment, index) => (
                <Card key={index} className="mt-2" >
                    <Card.Body className="d-flex align-items-start">
                        <Card.Img
                            src={`${API.baseURL}${subComment.student.picture}`}
                            alt={subComment.student.picture}
                            style={{
                                width: '30px',
                                height: '30px',
                                objectFit: 'cover',
                                borderRadius: '50%',
                                marginRight: '10px'
                            }}
                        />
                        <div style={{ flex: 1 }}>
                            <Card.Title className="mb-1" style={{ fontSize: '0.9rem' }}>{subComment.student.firstName} {subComment.student.lastName}</Card.Title>
                            <Card.Text style={{ fontSize: '0.8rem' }}>{subComment.comment}</Card.Text>
                        </div>
                    </Card.Body>
                </Card>
            ))}
        </>
    );
};

export default SubComment;
