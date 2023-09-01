import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css'
import Login from './pages/Login'
import Home from './pages/Home';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />}>
          <Route path=":movieId" element={<Login />} />
        </Route>
        <Route path="/create" element={<Login />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  )
}

