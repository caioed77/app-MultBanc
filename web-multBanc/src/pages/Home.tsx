import SearchBar from "../components/SearchBar";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SideBar from "../components/SideBar";
import Login from "./Login";

export default function Home() {

  return (
    <div className="flex">
      <SideBar />
      <div className="flex-1">
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
      </div>
    </div>
  );
}