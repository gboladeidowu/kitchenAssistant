// components/AuthForm.js

import React, { useState } from 'react';
import axios from 'axios';

const AuthForm = ({ endpoint }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`/api/auth/${endpoint}`, { email, password });
      console.log(response.data);
      // Redirect or display success message
    } catch (error) {
      console.error(error.response.data);
      // Display error message
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button type="submit">{endpoint === 'login' ? 'Login' : 'Register'}</button>
    </form>
  );
};

export default AuthForm;