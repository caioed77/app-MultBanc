
import { Outlet } from "react-router-dom";
import SideBar from "../components/SideBar";
import { useUser } from "../context/authContext";

export default function Home() {
  const { user } = useUser();

  return (
    <div className="flex flex-row overflow-hidden">
      <SideBar />
      <div className="bg-zinc-700 p-6 w-screen">
        <div className="grid grid-cols-2 gap-2 mt-6 mb-6">
          <div className="bg-violet-600 p-8 rounded-lg shadow-md inline-flex justify-between gap-10">
            <p className="text-white font-semibold font-roboto text-3xl">Saldo</p>
            <p className="text-white font-semibold">R$1000</p>
          </div>
          <div className="bg-violet-600 p-4 rounded-lg shadow-md flex flex-row justify-between">
            <div className="px-3">
              <label className="text-white font-semibold font-roboto text-1xl">Email</label>
              <p className="text-white">{user?.email}</p>
              <label className="text-white font-semibold font-roboto text-1xl">{user?.typePerson === "fisica" ? "Cpf" : "Cnpj"}</label>
              <p className="text-white">{user?.document}</p>             
            </div>
            <img src={user?.imgUser} className="w-[100px] h-[100px] rounded-full" />
          </div>
        </div>
        <Outlet />
      </div>
    </div>
  );
}



