import React, { useState } from 'react';
import { Button, Table, Form, Row, Col } from 'react-bootstrap';

function UncookedRecipes() {
    const [recipes, setRecipes] = useState([]);
    const [newRecipe, setNewRecipe] = useState({ name: '', quantity: '', unit: '' });

    const handleAddRecipe = () => {
        if (newRecipe.name && newRecipe.quantity && newRecipe.unit) {
            setRecipes([...recipes, newRecipe]);
            setNewRecipe({ name: '', quantity: '', unit: '' });
        }
    };

    const handleChange = (e) => {
        setNewRecipe({ ...newRecipe, [e.target.name]: e.target.value });
    };

    return (
        <div>
            <Form>
                <Row>
                    <Col md={3}>
                        <Form.Control
                            placeholder="Recipe Name"
                            name="name"
                            value={newRecipe.name}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={2}>
                        <Form.Control
                            placeholder="Quantity"
                            name="quantity"
                            type="number"
                            value={newRecipe.quantity}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={3}>
                        <Form.Control
                            placeholder="Unit (e.g., pieces, crates)"
                            name="unit"
                            value={newRecipe.unit}
                            onChange={handleChange}
                        />
                    </Col>
                    <Col md={4}>
                        <Button onClick={handleAddRecipe} variant="primary">Add Recipe</Button>
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
                    {recipes.map((recipe, index) => (
                        <tr key={index}>
                            <td>{recipe.name}</td>
                            <td>{recipe.quantity}</td>
                            <td>{recipe.unit}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}

export default UncookedRecipes;
