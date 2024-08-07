import React from 'react';
import { NavLink } from 'react-router-dom';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { FaHome, FaClipboardList, FaShoppingCart, FaMoneyBillWave, FaCommentDots, FaChartLine } from 'react-icons/fa';

function BottomNavBar() {
    return (
        <Navbar fixed="bottom" bg="dark" variant="dark" className="shadow-lg">
            <Container>
                <Nav className="w-100 justify-content-around">
                    <Nav.Item>
                        <NavLink to="/" className="nav-link" style={({ isActive }) => ({
                            color: isActive ? '#ffa500' : 'rgba(255, 255, 255, 0.5)'
                        })}>
                            <FaHome size={24} />
                        </NavLink>
                    </Nav.Item>
                    <Nav.Item>
                        <NavLink to="/inventory" className="nav-link" style={({ isActive }) => ({
                            color: isActive ? '#ffa500' : 'rgba(255, 255, 255, 0.5)'
                        })}>
                            <FaClipboardList size={24} />
                        </NavLink>
                    </Nav.Item>
                    <Nav.Item>
                        <NavLink to="/shopping-list" className="nav-link" style={({ isActive }) => ({
                            color: isActive ? '#ffa500' : 'rgba(255, 255, 255, 0.5)'
                        })}>
                            <FaShoppingCart size={24} />
                        </NavLink>
                    </Nav.Item>
                    <Nav.Item>
                        <NavLink to="/live-prices" className="nav-link" style={({ isActive }) => ({
                            color: isActive ? '#ffa500' : 'rgba(255, 255, 255, 0.5)'
                        })}>
                            <FaChartLine size={24} />
                        </NavLink>
                    </Nav.Item>
                    <Nav.Item>
                        <NavLink to="/feedback" className="nav-link" style={({ isActive }) => ({
                            color: isActive ? '#ffa500' : 'rgba(255, 255, 255, 0.5)'
                        })}>
                            <FaCommentDots size={24} />
                        </NavLink>
                    </Nav.Item>
                </Nav>
            </Container>
        </Navbar>
    );
}

export default BottomNavBar;
