import React, { useState } from 'react';
import { Table, Button, Modal, Form } from 'react-bootstrap';

function Timetable() {
  const [showModal, setShowModal] = useState(false);
  const [selectedDay, setSelectedDay] = useState(null);
  const [tempMealPlan, setTempMealPlan] = useState({ Breakfast: '', Lunch: '', Dinner: '' });
  const [mealPlan, setMealPlan] = useState({
    Monday: { Breakfast: '', Lunch: '', Dinner: '' },
    Tuesday: { Breakfast: '', Lunch: '', Dinner: '' },
    Wednesday: { Breakfast: '', Lunch: '', Dinner: '' },
    Thursday: { Breakfast: '', Lunch: '', Dinner: '' },
    Friday: { Breakfast: '', Lunch: '', Dinner: '' },
    Saturday: { Breakfast: '', Lunch: '', Dinner: '' },
    Sunday: { Breakfast: '', Lunch: '', Dinner: '' }
  });

  const handleOpenModal = (day) => {
    setSelectedDay(day);
    setTempMealPlan({...mealPlan[day]});
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedDay(null);
  };

  const handleSaveMeals = () => {
    if (selectedDay) {
      setMealPlan(prev => ({
        ...prev,
        [selectedDay]: {...tempMealPlan}
      }));
    }
    handleCloseModal();
  };

  const handleChange = (mealType, value) => {
    setTempMealPlan(prev => ({
      ...prev,
      [mealType]: value
    }));
  };

  return (
    <div className="container mt-3">
      <h2>Weekly Meal Timetable</h2>
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>Day</th>
            <th>Breakfast</th>
            <th>Lunch</th>
            <th>Dinner</th>
          </tr>
        </thead>
        <tbody>
          {Object.keys(mealPlan).map(day => (
            <tr key={day}>
              <td>{day}</td>
              {Object.keys(mealPlan[day]).map(mealType => (
                <td className="cell-content" key={mealType} onClick={() => handleOpenModal(day)} style={{ cursor: 'pointer' }}>
                  {/* {mealPlan[day][mealType] || 'Add meal'} */}
                  {(mealPlan[day][mealType]) ? 
                    ((mealPlan[day][mealType]).length <= 50 ? 
                      mealPlan[day][mealType] : 
                      `${(mealPlan[day][mealType]).slice(0, 50)}...`) : 
                    'Add meal'}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </Table>

      {selectedDay && (
        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header closeButton>
            <Modal.Title>Edit Meals for {selectedDay}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {['Breakfast', 'Lunch', 'Dinner'].map(mealType => (
              <Form.Group className="mb-3" key={mealType}>
                <Form.Label>{mealType}</Form.Label>
                <Form.Control
                  type="text"
                  placeholder={`Enter ${mealType}`}
                  value={tempMealPlan[mealType]}
                  onChange={(e) => handleChange(mealType, e.target.value)}
                />
              </Form.Group>
            ))}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="primary" onClick={handleSaveMeals}>
              Add
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </div>
  );
}

export default Timetable;
