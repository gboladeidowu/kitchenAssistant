import React, { useState, useEffect } from 'react';
import { Table, Button, FormControl, InputGroup } from 'react-bootstrap';

function LivePrices() {
    const [prices, setPrices] = useState([
        { id: 1, product: 'Eggs', price: 2.99, category: 'Dairy' },
        { id: 2, product: 'Milk', price: 1.49, category: 'Dairy' },
        { id: 3, product: 'Bread', price: 1.20, category: 'Bakery' }
    ]);
    const [searchTerm, setSearchTerm] = useState('');

    const refreshPrices = () => {
        // This would be an API call in a real app
        setPrices(prices.map(item => ({
            ...item,
            price: (Math.random() * (item.price * 1.1)).toFixed(2)  // Randomize price for demonstration
        })));
    };

    const handleSearch = (event) => {
        setSearchTerm(event.target.value.toLowerCase());
    };

    const filteredPrices = prices.filter(price => 
        price.product.toLowerCase().includes(searchTerm)
    );

    return (
        <div className="container mt-3" style={{ paddingBottom: '60px' }}>
            <h2>Live Market Prices</h2>
            <InputGroup className="mb-3">
                <FormControl
                    placeholder="Search products..."
                    onChange={handleSearch}
                />
                <Button variant="outline-secondary" onClick={refreshPrices}>Refresh Prices</Button>
            </InputGroup>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredPrices.map((item) => (
                        <tr key={item.id}>
                            <td>{item.product}</td>
                            <td>${item.price}</td>
                            <td>{item.category}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
}

export default LivePrices;
