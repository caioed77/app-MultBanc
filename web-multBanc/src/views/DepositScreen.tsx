import { useEffect, useState } from 'react'
import EditValue from "../components/EditValue";
import { useUser } from "../context/authContext";
import { TAccount } from '../@Types/Account';
import { api } from '../service/api';

export default function DepositScreen() {
    const user = useUser();
    const [account, setAccount] = useState<TAccount | undefined>(undefined);    

    useEffect(() => {
        api.get(`/conta/${user.user?.user}/buscarConta`)
          .then((response) => {
            setAccount(response.data);
          })
          .catch((error) => {
            console.error("Erro na chamada da API:", error);
          });
      }, [account, user.user?.user]);
  
  return (
    <div className="bg-primary p-7 rounded-md text-white flex flex-col">
      <div className="px-4 sm:px-0">
        <h3 className="text-2xl font-semibold leading-7">Deposito</h3>
      </div>     
      <div className="mt-6 p-5 border-t border-gray-100">        
        <EditValue displayname="Valor do deposito" />
      </div>      
      <div className="inline-flex justify-end items-end mt-10">
        <button className="bg-button hover:bg-zinc-500 text-white font-semibold px-6 py-2 rounded">Depositar</button>
      </div>
    </div>
  )
}