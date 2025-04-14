import React, { useEffect, useState } from 'react';
import { fetchToolkits } from '../services/selfHelpApi';
import './styles/SelfHelp.css';

const ToolkitList = ({ onSelect }) => {
  const [toolkits, setToolkits] = useState([]);

  useEffect(() => {
    fetchToolkits().then(res => setToolkits(res.data));
  }, []);

  return (
    <div className="container my-3">
      <h3>Select a Toolkit</h3>
      <ul className="list-group">
        {toolkits.map(t => (
          <li key={t.id} className="list-group-item" onClick={() => onSelect(t.id)} style={{ cursor: 'pointer' }}>
            <strong>{t.name}</strong> - {t.description}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ToolkitList;
