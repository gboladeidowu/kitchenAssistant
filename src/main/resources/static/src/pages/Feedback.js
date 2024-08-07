import React, { useState } from 'react';
import { Button, ListGroup, InputGroup, FormControl, Badge } from 'react-bootstrap';

function Feedback() {
    const [feedbackItems, setFeedbackItems] = useState([]);
    const [newFeedback, setNewFeedback] = useState('');

    const handleVote = (id, type) => {
        setFeedbackItems(feedbackItems.map(item => {
            if (item.id === id) {
                return { ...item, [type]: item[type] + 1 };
            }
            return item;
        }));
    };

    const handleAddFeedback = () => {
        if (newFeedback) {
            const nextId = feedbackItems.length ? Math.max(...feedbackItems.map(item => item.id)) + 1 : 1;
            setFeedbackItems([...feedbackItems, { 
                id: nextId, 
                text: newFeedback, 
                likes: 0, 
                dislikes: 0,
                timestamp: new Date().toLocaleString()  // Adding timestamp
            }]);
            setNewFeedback('');  // Reset input field
        }
    };

    const handleDeleteFeedback = (id) => {
        setFeedbackItems(feedbackItems.filter(item => item.id !== id));
    };

    // Sort feedback items based on likes descending
    const sortedFeedbackItems = [...feedbackItems].sort((a, b) => b.likes - a.likes);

    return (
        <div className="container mt-3" style={{ paddingBottom: '60px' }}>
            <h2>User Feedback Poll</h2>
            <InputGroup className="mb-3">
                <FormControl
                    placeholder="Type your feedback here..."
                    value={newFeedback}
                    onChange={(e) => setNewFeedback(e.target.value)}
                />
                <Button variant="primary" onClick={handleAddFeedback}>Add Feedback</Button>
            </InputGroup>
            <ListGroup>
                {sortedFeedbackItems.map(item => (
                    <ListGroup.Item key={item.id}>
                        {item.text}
                        <div style={{ float: 'right' }}>
                            <Badge bg="success" style={{ cursor: 'pointer', margin: '0 5px' }} onClick={() => handleVote(item.id, 'likes')}>👍 {item.likes}</Badge>
                            <Badge bg="danger" style={{ cursor: 'pointer', margin: '0 5px' }} onClick={() => handleVote(item.id, 'dislikes')}>👎 {item.dislikes}</Badge>
                            <Button variant="outline-danger" size="sm" onClick={() => handleDeleteFeedback(item.id)}>Delete</Button>
                        </div>
                        <div style={{ paddingTop: '5px', color: 'gray', fontSize: 'smaller' }}>
                            {item.timestamp}
                        </div>
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </div>
    );
}

export default Feedback;
