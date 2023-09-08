
import { Outlet } from "react-router-dom";
import SideBar from "../components/SideBar";
import { useUser } from "../context/authContext";

export default function Home() {
  const { user } = useUser();

  return (
    <div className="flex flex-row overflow-hidden">
      <SideBar />
      <div className="bg-zinc-700 p-4 w-screen">
        <div className="grid grid-cols-2 gap-2 mt-10 mb-10">
          <div className="bg-violet-600 p-8 rounded-lg shadow-md inline-flex justify-between gap-10">
            <p className="text-white font-semibold font-roboto text-3xl">Saldo</p>
            <p className="text-white font-semibold">R$1000</p>
          </div>
          <div className="bg-violet-600 p-10 rounded-lg shadow-md grid grid-cols-2">
            <div className="flex flex-col gap-2">
              <label className="text-white font-roboto text-2xl">Email</label>
              <p className="text-white">{user?.email}</p>
              <label className="text-white font-roboto text-2xl">{user?.typePerson === "fisica" ? "Cpf" : "Cnpj"}</label>
              <p className="text-white">{user?.document}</p>
            </div>
            <div className="flex flex-row-reverse">
              <img src={user?.imgUser} className="w-[7rem] h-[7rem] rounded-full">{ }</img >
            </div>
          </div>
        </div>
        <Outlet />
      </div>
    </div>
  );
}



