import React, { useEffect, useState } from 'react';
import { fetchAllLetters } from '../services/letterService';
import '../styles/Letters.css';

const LetterList = () => {
    const [letters, setLetters] = useState([]);

    useEffect(() => {
        fetchAllLetters().then(setLetters).catch(console.error);
    }, []);

    return (
        <div className="letters-container">
            <h2>All Letters</h2>
            <ul>
                {letters.map(letter => (
                    <li key={letter.id}>
                        <h4>{letter.heading}</h4>
                        <p>{letter.content}</p>
                        <small>Category: {letter.category} | Date: {letter.date}</small>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default LetterList;