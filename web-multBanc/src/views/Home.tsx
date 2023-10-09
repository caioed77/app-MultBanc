import { useEffect, useState } from 'react'
import { useUser } from '../context/authContext'
import { Outlet } from 'react-router-dom'
import { IAccount } from '../@Types/Account'
import { api } from '../service/api'
import NavBar from '../components/NavBar'

export default function Home() {

  const user = useUser();
  const [account, setAccount] = useState<IAccount | undefined>(undefined);

  useEffect(() => {
    api.get(`/conta/${user.user?.user}/buscarConta`)
      .then((response) => {
        setAccount(response.data);
      })
      .catch((error) => {
        console.error("Erro na chamada da API:", error);
      });     
  }, [user.user]);

  return (
    <>
      <div className="min-h-full">
        <NavBar />
        <header className="shadow-sm bg-white border">
          <div className="grid grid-cols-2 gap-5 items-start mx-auto max-w-6xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 className="bg-black text-2xl rounded-xl px-3 py-3 font-bold tracking-tigh text-white">Saldo : R${account ? account?.balance.toFixed(2) : "0,00"}</h1>
            <h1 className="bg-black text-2xl rounded-xl px-3 py-3 font-bold tracking-tight text-white">Rendimento : {account ? account?.performace.toFixed(1) : "0,0"}%</h1>
          </div>
        </header>
        <main>
          <div className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8 mt-12">
            <Outlet />
          </div>
        </main>
      </div>
    </>

  );
}



