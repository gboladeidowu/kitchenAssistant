import React, { useState } from 'react';
import { Button, ListGroup, InputGroup, FormControl } from 'react-bootstrap';

function ShoppingList() {
    const [items, setItems] = useState([
        { id: 1, name: 'Eggs', quantity: 12, unit: 'pieces', purchased: false },
        { id: 2, name: 'Milk', quantity: 2, unit: 'liters', purchased: false }
    ]);
    const [newItem, setNewItem] = useState({ name: '', quantity: '', unit: '' });

    const handleAddItem = () => {
        if (newItem.name && newItem.quantity && newItem.unit) {
            const nextId = items.length ? Math.max(...items.map(item => item.id)) + 1 : 1;
            setItems([...items, { ...newItem, id: nextId, purchased: false }]);
            setNewItem({ name: '', quantity: '', unit: '' });
        }
    };

    const handleDeleteItem = (id) => {
        setItems(items.filter(item => item.id !== id));
    };

    const handlePurchaseToggle = (id) => {
        setItems(items.map(item => item.id === id ? { ...item, purchased: !item.purchased } : item));
    };

    const handleChange = (event) => {
        setNewItem({ ...newItem, [event.target.name]: event.target.value });
    };

    return (
        <div className="container mt-3" style={{ paddingBottom: '60px' }}>
            <h2>Shopping List</h2>
            <InputGroup className="mb-3">
                <FormControl
                    placeholder="Item name"
                    name="name"
                    value={newItem.name}
                    onChange={handleChange}
                />
                <FormControl
                    placeholder="Quantity"
                    name="quantity"
                    type="number"
                    value={newItem.quantity}
                    onChange={handleChange}
                />
                <FormControl
                    placeholder="Unit"
                    name="unit"
                    value={newItem.unit}
                    onChange={handleChange}
                />
                <Button variant="primary" onClick={handleAddItem}>Add Item</Button>
            </InputGroup>
            <ListGroup>
                {items.map(item => (
                    <ListGroup.Item key={item.id} variant={item.purchased ? 'success' : ''}>
                        {item.name} - {item.quantity} {item.unit}
                        <Button variant="outline-success" size="sm" onClick={() => handlePurchaseToggle(item.id)} style={{ margin: '0 5px' }}>
                            {item.purchased ? 'Unmark' : 'Mark as Purchased'}
                        </Button>
                        <Button variant="outline-danger" size="sm" onClick={() => handleDeleteItem(item.id)}>Delete</Button>
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </div>
    );
}

export default ShoppingList;
