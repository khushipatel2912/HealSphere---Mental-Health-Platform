import React, { useState } from 'react';
import { postLetter } from '../services/letterService';
import '../styles/Letters.css';

const LetterForm = ({ token }) => {
    const [letter, setLetter] = useState({ heading: '', content: '', category: '', date: '' });
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await postLetter(letter, token);
            setMessage('Letter posted successfully.');
        } catch (error) {
            setMessage('Failed to post letter.');
        }
    };

    return (
        <div className="letter-form">
            <h2>Post a Letter</h2>
            {message && <p>{message}</p>}
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Heading" onChange={e => setLetter({ ...letter, heading: e.target.value })} required />
                <textarea placeholder="Content" onChange={e => setLetter({ ...letter, content: e.target.value })} required />
                <input type="text" placeholder="Category" onChange={e => setLetter({ ...letter, category: e.target.value })} required />
                <input type="date" onChange={e => setLetter({ ...letter, date: e.target.value })} required />
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default LetterForm;
