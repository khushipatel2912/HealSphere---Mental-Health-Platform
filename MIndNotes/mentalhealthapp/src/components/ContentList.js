import React, { useEffect, useState } from 'react';
import { fetchContent } from '../services/selfHelpApi';
import './styles/SelfHelp.css';

const ContentList = ({ levelId }) => {
  const [contents, setContents] = useState([]);

  useEffect(() => {
    if (levelId)
      fetchContent(levelId).then(res => setContents(res.data));
  }, [levelId]);

  return (
    <div className="container my-3">
      <h5>Level Content</h5>
      {contents.map(c => (
        <div key={c.id} className="card my-2">
          <div className="card-body">
            <h6>{c.contentType}</h6>
            <p>{c.content}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default ContentList;
