import React, { useState } from 'react';
import { Button, Table, Form, Row, Col } from 'react-bootstrap';

function CookedFood() {
    const [foods, setFoods] = useState([]);
    const [newFood, setNewFood] = useState({ name: '', quantity: '', unit: '' });

    const handleAddFood = () => {
        if (newFood.name && newFood.quantity && newFood.unit) {
            setFoods([...foods, newFood]);
            setNewFood({ name: '', quantity: '', unit: '' });
        }
    };

    const handleChange = (e) => {
        setNewFood({ ...newFood, [e.target.name]: e.target.value });
    };

    return (
        <div>
            <Form>
                <Row>
                    <Col md={3}>
                        <Form.Control
                            placeholder="Food Name"
                            name="name"
                            value={newFood.name}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={2}>
                        <Form.Control
                            placeholder="Quantity"
                            name="quantity"
                            type="number"
                            value={newFood.quantity}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={3}>
                        <Form.Control
                            placeholder="Unit (e.g., pieces, servings)"
                            name="unit"
                            value={newFood.unit}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={4}>
                        <Button onClick={handleAddFood} variant="primary">Add Food</Button>
                    </Col>
                </Row>
            </Form>
            <Table striped bordered hover responsive className="mt-3">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Unit</th>
                    </tr>
                </thead>
                <tbody>
                    {foods.map((food, index) => (
                        <tr key={index}>
                            <td>{food.name}</td>
                            <td>{food.quantity}</td>
                            <td>{food.unit}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}

export default CookedFood;
