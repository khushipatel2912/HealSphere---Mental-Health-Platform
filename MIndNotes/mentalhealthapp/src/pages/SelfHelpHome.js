import React, { useState } from 'react';
import ToolkitList from '../components/ToolkitList';
import LevelList from '../components/LevelList';
import ContentList from '../components/ContentList';
import Assessment from '../components/Assessment';
import ProgressUpdater from '../components/ProgressUpdater';
import './styles/SelfHelp.css';

const SelfHelpHome = () => {
  const [toolkitId, setToolkitId] = useState(null);
  const [levelId, setLevelId] = useState(null);

  const userId = 1; // Ideally fetched after login

  return (
    <div className="container">
      <h2 className="my-4">Self Help Module</h2>
      <ToolkitList onSelect={setToolkitId} />
      {toolkitId && <LevelList toolkitId={toolkitId} onSelectLevel={setLevelId} />}
      {levelId && (
        <>
          <ContentList levelId={levelId} />
          <Assessment levelId={levelId} />
          <ProgressUpdater userId={userId} toolkitId={toolkitId} levelId={levelId} />
        </>
      )}
    </div>
  );
};

export default SelfHelpHome;
