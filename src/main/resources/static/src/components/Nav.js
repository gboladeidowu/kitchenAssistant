import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Navbar, Nav, Container } from 'react-bootstrap';
import './Navigation.css';

function Navigation() {
    const [isNavExpanded, setIsNavExpanded] = useState(false);

    return (
        <div>
            <Navbar bg="dark" variant="dark" expand="lg" expanded={isNavExpanded}>
                <Container fluid>
                    <Navbar.Brand as={Link} to="/">Kitchen Secretary App</Navbar.Brand>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" onClick={() => setIsNavExpanded(!isNavExpanded)} />
                    <Navbar.Collapse id="responsive-navbar-nav" style={{zIndex:"940"}}>
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/" onClick={() => setIsNavExpanded(false)}>Timetable</Nav.Link>
                            <Nav.Link as={Link} to="/inventory" onClick={() => setIsNavExpanded(false)}>Inventory</Nav.Link>
                            <Nav.Link as={Link} to="/shopping-list" onClick={() => setIsNavExpanded(false)}>Shopping List</Nav.Link>
                            <Nav.Link as={Link} to="/live-prices" onClick={() => setIsNavExpanded(false)}>Live Prices</Nav.Link>
                            <Nav.Link as={Link} to="/about" onClick={() => setIsNavExpanded(false)}>About</Nav.Link>
                            <Nav.Link as={Link} to="/feedback" onClick={() => setIsNavExpanded(false)}>Feedback</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            {/* Overlay when sidebar is expanded */}
            {isNavExpanded && <div className="nav-backdrop" onClick={() => setIsNavExpanded(false)} />}
        </div>
    );
}

export default Navigation;
