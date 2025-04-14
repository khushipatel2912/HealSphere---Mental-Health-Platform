import React from 'react';
import SelfDiscoveryForm from '../components/QuestionCard';
import '../styles/SelfDiscovery.css';

const SelfDiscovery = ({ token }) => {
    return (
        <div className="self-discovery-page">
            <SelfDiscoveryForm token={token} />
        </div>
    );
};

export default SelfDiscovery;
