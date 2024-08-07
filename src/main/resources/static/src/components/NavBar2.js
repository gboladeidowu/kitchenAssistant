import React, { useState } from 'react';
import { LinkContainer } from 'react-router-bootstrap';
import { Navbar, Nav, Container, Offcanvas, NavDropdown } from 'react-bootstrap';
import { FaHome, FaClipboardList, FaShoppingCart, FaMoneyBillWave, FaCommentDots, FaBox, FaChartLine, FaEnvelope, FaSignOutAlt } from 'react-icons/fa';

const NavBar2 = () => {
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return (
        <Navbar bg="dark" variant="dark" expand="lg" className="mb-3">
            <Container fluid>
                <LinkContainer to="/">
                    <Navbar.Brand onClick={handleClose}>Kitchen Secretary App</Navbar.Brand>
                </LinkContainer>
                <Navbar.Toggle aria-controls="offcanvasNavbar2" onClick={handleShow} />
                <Navbar.Offcanvas
                    id="offcanvasNavbar2"
                    aria-labelledby="offcanvasNavbar2Label"
                    placement="end"
                    show={show}
                    onHide={handleClose}
                >
                    <Offcanvas.Header closeButton>
                        <Offcanvas.Title id="offcanvasNavbar2Label">Kitchen Secretary App</Offcanvas.Title>
                    </Offcanvas.Header>
                    <Offcanvas.Body>
                        <Nav className="justify-content-end flex-grow-1 pe-3">
                            <LinkContainer to="/" onClick={handleClose}>
                                <Nav.Link><FaHome className="me-2" />Timetable</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/inventory" onClick={handleClose}>
                                <Nav.Link><FaClipboardList className="me-2" />Inventory</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/shopping-list" onClick={handleClose}>
                                <Nav.Link><FaShoppingCart className="me-2" />Shopping List</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/live-prices" onClick={handleClose}>
                                <Nav.Link><FaChartLine className="me-2" />Live Prices</Nav.Link>
                            </LinkContainer>
                            <LinkContainer to="/feedback" onClick={handleClose}>
                                <Nav.Link><FaCommentDots className="me-2" />Feedback</Nav.Link>
                            </LinkContainer>
                            <NavDropdown title="About Us" id="offcanvasNavbarDropdown">
                                <LinkContainer to="/privacy-policy" onClick={handleClose}>
                                    <NavDropdown.Item>Privacy Policy</NavDropdown.Item>
                                </LinkContainer>
                                <LinkContainer to="/terms-of-use" onClick={handleClose}>
                                    <NavDropdown.Item>Terms of Use</NavDropdown.Item>
                                </LinkContainer>
                                <LinkContainer to="/history" onClick={handleClose}>
                                    <NavDropdown.Item>History</NavDropdown.Item>
                                </LinkContainer>
                                <NavDropdown.Divider />
                                <LinkContainer to="/future-releases" onClick={handleClose}>
                                    <NavDropdown.Item>Future Releases</NavDropdown.Item>
                                </LinkContainer>
                                <LinkContainer to="/" onClick={handleClose}>
                                    <NavDropdown.Item>Logout</NavDropdown.Item>
                                </LinkContainer>
                            </NavDropdown>
                            <LinkContainer to="/logout" onClick={handleClose}>
                                <Nav.Link><FaSignOutAlt className="me-2" />Log Out</Nav.Link>
                            </LinkContainer>
                        </Nav>
                    </Offcanvas.Body>
                </Navbar.Offcanvas>
            </Container>
        </Navbar>
    );
}

export default NavBar2;
