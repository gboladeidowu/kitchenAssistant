import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';

function About() {
    return (
        <Container className="mt-5" style={{ paddingBottom: '60px' }}>
            <h1>About Us</h1>
            <Row className="mt-3">
                <Col md={6}>
                    <Card>
                        <Card.Body>
                            <Card.Title>Our Mission</Card.Title>
                            <Card.Text>
                                Our mission is to provide innovative solutions to everyday problems
                                through technology, enhancing the efficiency and quality of life for our users.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={6}>
                    <Card>
                        <Card.Body>
                            <Card.Title>Company History</Card.Title>
                            <Card.Text>
                            This meal planning app is a project to help solve my food and cooking challenges.
                              We are a small team of developers who have come together to create a platform that will help people.
                                Founded in 2021, our company has been at the forefront of developing
                                intuitive web applications designed to tackle personal and societal challenges.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row className="mt-3">
                <Col md={12}>
                    <Card>
                        <Card.Body>
                            <Card.Title>Privacy & User Agreement</Card.Title>
                            <Card.Text>
                                We take privacy seriously. Please read our Privacy Policy and User Agreement
                                carefully to understand how we collect, use, and protect your data and your rights.
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default About;
