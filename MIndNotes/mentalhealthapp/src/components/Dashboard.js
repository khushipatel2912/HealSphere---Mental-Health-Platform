import { useEffect, useState, useContext } from 'react';
import { fetchUserDetails } from '../services/api';
import { AuthContext } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css'; 
import '../styles/App.css';

const Dashboard = () => {
    const { token } = useContext(AuthContext);
    const [user, setUser] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        if (token) {
            fetchUserDetails(token).then(response => setUser(response.data));
        }
    }, [token]);

    return (
        <div className="dashboard-container">
            <h2>Welcome to Your Dashboard</h2>
            {user ? (
                <div className="user-info">
                    <p><strong>Name:</strong> {user.firstName} {user.lastName}</p>
                    <p><strong>Email:</strong> {user.email}</p>
                </div>
            ) : (
                <p>Loading user details...</p>
            )}

            <h3 className="services-heading">Explore Our Services</h3>
            <div className="services-menu">
                <div className="service-card" onClick={() => navigate('/self-discovery')}>
                    <h4>Self Discovery</h4>
                    <p>Explore your inner strengths and growth.</p>
                </div>
                <div className="service-card" onClick={() => navigate('/letters-affirmations')}>
                    <h4>Letters & Affirmations</h4>
                    <p>Little acts of kindness to lift your spirit.</p>
                </div>
                <div className="service-card" onClick={() => navigate('/crisis-management')}>
                    <h4>Crisis Management</h4>
                    <p>Learn strategies to handle tough times.</p>
                </div>
                <div className="service-card" onClick={() => navigate('/self-help')}>
                    <h4>Self Help Resources</h4>
                    <p>Guides and tools to support your journey.</p>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
