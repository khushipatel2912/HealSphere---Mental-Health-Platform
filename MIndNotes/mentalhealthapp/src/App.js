import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import SelfHelpHome from './pages/SelfHelpHome'; 
import SelfDiscovery from './components/SelfDiscovery';
import ReportPage from './components/ReportPage';
import { AuthProvider } from './context/AuthContext';
import './styles/App.css';

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <div className="app-container">
                    <Routes>
                        <Route path="/" element={<Login />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/dashboard" element={<Dashboard />} />
                        <Route path="/self-discovery" element={<SelfDiscovery />} />
                        <Route path="/report" element={<ReportPage />} />
                        <Route path="/self-help" element={<SelfHelpHome />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
};

export default App; 