import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar2 from './components/NavBar2';
import Timetable from './pages/Timetable';
import Inventory from './pages/Inventory';
import ShoppingList from './pages/ShoppingList';
import LivePrices from './pages/LivePrices';
import Feedback from './pages/Feedback';
import About from './pages/About';
import Missing from './pages/Missing';
import BottomNavBar from './components/BottomNavBar';

function App() {
  return (
    <Router>
      <div className="App">
        <NavBar2 />
        <Routes>
          <Route path='/' element={<Timetable />} />
          <Route path='/inventory' element={<Inventory />} />
          <Route path='/shopping-list' element={<ShoppingList />} />
          <Route path='/live-prices' element={<LivePrices />} />
          <Route path='/feedback' element={<Feedback />} />
          <Route path='/about' element={<About />} />
          <Route path='*' element={<Missing />} />
        </Routes>
        <BottomNavBar />
      </div>
    </Router>
  );
}

export default App;