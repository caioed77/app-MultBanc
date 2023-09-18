import { useEffect, useState } from 'react'
import { useUser } from "../context/authContext";
import { TAccount } from '../@Types/Account';
import { api } from '../service/api';

export default function DepositScreen() {
  const user = useUser();
  const [account, setAccount] = useState<TAccount | undefined>(undefined);
  const [deposit, setDeposit] = useState(0);

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
        <label htmlFor="price" className="block text-sm font-semibold text-white leading-6">
          Valor
        </label>
        <div className="relative mt-2 rounded-md shadow-sm ">
          <div className="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3">
            <span className="text-gray-500 sm:text-sm">$</span>
          </div>
          <input
            type="text"
            name="price"
            id="price"
            className="block w-full rounded-md border-0 py-1.5 pl-7 text-gray-900 focus:outline-none sm:text-sm sm:leading-5"
            placeholder="0.00"
            value={deposit}
            onChange={(e) => setDeposit(e.target.valueAsNumber)}
          />
        </div>
      </div>
      <div className="inline-flex justify-end items-end mt-10">
        <button className="bg-button hover:bg-zinc-500 text-white font-semibold px-6 py-2 rounded">Depositar</button>
      </div>
    </div>
  )
}