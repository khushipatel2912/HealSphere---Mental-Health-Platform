import React, { useEffect, useState } from 'react';
import { fetchLevels } from '../services/selfHelpApi';
import './styles/SelfHelp.css';

const LevelList = ({ toolkitId, onSelectLevel }) => {
  const [levels, setLevels] = useState([]);

  useEffect(() => {
    if (toolkitId)
      fetchLevels(toolkitId).then(res => setLevels(res.data));
  }, [toolkitId]);

  return (
    <div className="container my-3">
      <h4>Levels</h4>
      <ul className="list-group">
        {levels.map(level => (
          <li
            key={level.id}
            className={`list-group-item ${level.unlocked ? '' : 'disabled'}`}
            onClick={() => level.unlocked && onSelectLevel(level.id)}
            style={{ cursor: level.unlocked ? 'pointer' : 'not-allowed' }}
          >
            {level.levelName} {level.unlocked ? '' : '(Locked)'}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LevelList;
