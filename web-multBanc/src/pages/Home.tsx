
import { Outlet } from "react-router-dom";
import SideBar from "../components/SideBar";

export default function Home() {

  return (
    <div className="flex flex-row overflow-hidden">
      <SideBar />
      <div className="bg-gray-200 p-4 w-screen">
        <header className="bg-black p-4 text-white">
          <nav className="flex justify-end space-x-4">
            <a href="#" className="hover:underline">
              Configurações
            </a>
          </nav>
        </header>
        <div className="grid grid-cols-2 gap-2 mt-10 mb-10">
          <div className="bg-violet-600 p-8 rounded-lg shadow-md inline-flex justify-between gap-10">
            <p className="text-white font-semibold font-roboto text-3xl">Saldo</p>
            <p className="text-white font-semibold">Saldo</p>
          </div>
          <div className="bg-violet-600 p-10 rounded-lg shadow-md">
            <p className="text-white font-semibold">Gastos</p>
          </div>          
        </div>
        <Outlet />
      </div>
    </div>
  );
}



