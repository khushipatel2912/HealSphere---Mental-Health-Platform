import axios from 'axios';

// const API_BASE_URL = 'http://localhost:8080/api/letters';
// const API_BASE_URL = process.env.REACT_APP_LETTERS_API;

const API_BASE_URL = 'http://localhost:9095/api/letters';
export const fetchAllLetters = async () => {
    const response = await axios.get(`${API_BASE_URL}/all`);
    return response.data;
};

export const fetchLettersByCategory = async (category) => {
    const response = await axios.get(`${API_BASE_URL}/category/${category}`);
    return response.data;
};

export const postLetter = async (letter, token) => {
    const response = await axios.post(`${API_BASE_URL}/post`, letter, {
        headers: { Authorization: `Bearer ${token}` }
    });
    return response.data;
};
