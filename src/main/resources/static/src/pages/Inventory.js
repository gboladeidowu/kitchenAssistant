import React, { useState } from 'react';
import { Tabs, Tab, InputGroup, FormControl, Button } from 'react-bootstrap';
import UncookedRecipes from '../components/UncookedRecipes';
import CookedFood from '../components/CookedFood';

function Inventory() {
    const [key, setKey] = useState('uncooked');
    const [searchTerm, setSearchTerm] = useState('');

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    return (
        <div className="container mt-3" style={{ paddingBottom: '60px' }}>
          <h2>Inventory</h2>
            <InputGroup className="mb-3">
                <FormControl
                    placeholder="Search..."
                    onChange={handleSearchChange}
                />
                <Button variant="outline-secondary">Search</Button>
            </InputGroup>
            <Tabs activeKey={key} onSelect={(k) => setKey(k)} className="mb-3">
                <Tab eventKey="uncooked" title="Uncooked Recipes">
                    <UncookedRecipes searchTerm={searchTerm} />
                </Tab>
                <Tab eventKey="cooked" title="Cooked Food">
                    <CookedFood searchTerm={searchTerm} />
                </Tab>
            </Tabs>
        </div>
    );
}

export default Inventory;
