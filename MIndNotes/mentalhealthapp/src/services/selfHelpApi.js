import axios from 'axios';
import authHeader from '../utils/authHeader';

import './styles/SelfHelp.css';

const API_BASE = "http://localhost:9093/api/selfhelp";

export const fetchToolkits = () => axios.get(`${API_BASE}/toolkits`, { headers: authHeader() });

export const fetchLevels = (toolkitId) => axios.get(`${API_BASE}/levels/${toolkitId}`, { headers: authHeader() });

export const fetchContent = (levelId) => axios.get(`${API_BASE}/content/${levelId}`, { headers: authHeader() });

export const fetchAssessments = (levelId) => axios.get(`${API_BASE}/assessments/${levelId}`, { headers: authHeader() });

export const updateProgress = (progressData) =>
  axios.post(`${API_BASE}/progress`, progressData, { headers: authHeader() });
