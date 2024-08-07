import React from 'react';
import { Button, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function Missing() {
    const navigate = useNavigate();

    return (
        <Container className="mt-5 text-center">
            <h1>404 Not Found</h1>
            <p>The page you are looking for does not exist or has been moved.</p>
            <Button variant="primary" onClick={() => navigate('/')}>Go to Timetable Page</Button>
        </Container>
    );
}

export default Missing;
