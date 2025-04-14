import React, { useEffect, useState } from 'react';
import { fetchAssessments } from '../services/selfHelpApi';
import './styles/SelfHelp.css';

const Assessment = ({ levelId }) => {
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    if (levelId)
      fetchAssessments(levelId).then(res => setQuestions(res.data));
  }, [levelId]);

  return (
    <div className="container my-3">
      <h5>Assessment</h5>
      {questions.map(q => (
        <div key={q.id}>
          <p><strong>{q.question}</strong></p>
          {q.options.map((opt, i) => (
            <div className="form-check" key={i}>
              <input className="form-check-input" type="radio" name={`q${q.id}`} id={`opt${i}`} />
              <label className="form-check-label" htmlFor={`opt${i}`}>{opt}</label>
            </div>
          ))}
        </div>
      ))}
    </div>
  );
};

export default Assessment;
