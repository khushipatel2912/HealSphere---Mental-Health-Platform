import React, { useState } from 'react';
import { updateProgress } from '../services/selfHelpApi';
import './styles/SelfHelp.css';

const ProgressUpdater = ({ userId, toolkitId, levelId }) => {
  const [message, setMessage] = useState('');

  const handleUpdate = () => {
    const payload = {
      user_id: userId,
      toolkitId,
      levelId,
      isCompleted: true,
      score: 80
    };

    updateProgress(payload)
      .then(res => setMessage(res.data.message))
      .catch(err => setMessage('Update failed.'));
  };

  return (
    <div className="container my-3">
      <button className="btn btn-success" onClick={handleUpdate}>Mark Completed</button>
      {message && <div className="alert alert-info mt-2">{message}</div>}
    </div>
  );
};

export default ProgressUpdater;
