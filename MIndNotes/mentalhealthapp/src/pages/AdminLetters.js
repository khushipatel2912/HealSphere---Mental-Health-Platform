import React from 'react';
import LetterForm from '../components/LetterForm';
import '../styles/Letters.css';

const AdminLetters = ({ token }) => {
    return (
        <div>
            <h1>Admin: Post Letters</h1>
            <LetterForm token={token} />
        </div>
    );
};

export default AdminLetters;
