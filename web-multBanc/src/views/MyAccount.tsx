import { useUser } from "../context/authContext";
import { useState } from "react";
import { handleCreateAccount } from "../service/AccountService";
import { IAccount } from "../@Types/Account";
import { ResultModal } from "../components/Modals/resultModal";

export default function MyAccount() {
  const [numConta, setNumConta] = useState(0);
  const [agencia, setAgencia] = useState(0);
  const [tipoConta, setTipoConta] = useState('');
  const [saldo, setSaldo] = useState(0);
  const [rendimento, setRendimento] = useState(0);
  const [openModal, setOpenModal] = useState(false);
  const { user } = useUser();

  async function createAccount() { 
    const dados: IAccount = {
      agency: agencia,
      number: numConta,
      holder: user?.user,
      typeAccount: tipoConta,
      balance: saldo,
      performace: rendimento,
    }
    
    const result = await handleCreateAccount(dados)
    if (result) {
      setOpenModal(true)
    }
  }

  function closeModal() {
    setOpenModal(false)
  }
  
  return (
    <form className="bg-primary rounded-lg shadow-lg p-10 relative">
      <div className="border-b border-gray-900/10 pb-6 w-[450px]">
        <h2 className="text-2xl font-semibold leading-7 text-white">Cadastrar minha conta</h2>
        <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
          <div className="sm:col-span-3">
            <label htmlFor="first-name" className="block text-sm font-medium leading-6 text-white">
              Numero da conta
            </label>
            <div className="mt-2">
              <input
                type="number"
                name="first-name"
                id="first-name"
                autoComplete="given-name"
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 px-3 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:outline-none sm:text-sm sm:leading-6"
                value={numConta}
                onChange={(e) => setNumConta(e.target.valueAsNumber)}
              />
            </div>
          </div>
          <div className="sm:col-span-3">
            <label htmlFor="last-name" className="block text-md font-medium leading-6 text-white">
              Agência
            </label>
            <div className="mt-2">
              <input
                type="number"
                name="last-name"
                id="last-name"
                autoComplete="family-name"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm focus:outline-none sm:text-sm sm:leading-6"
                value={agencia}
                onChange={(e) => setAgencia(e.target.valueAsNumber)}
              />
            </div>
          </div>
          <div className="sm:col-span-3">
            <label htmlFor="country" className="block text-md font-medium leading-6 text-white">
              Tipo da conta
            </label>
            <div className="mt-2">
              <select
                id="country"
                name="country"
                autoComplete="country-name"
                className="h-full rounded-md border-0 py-2 px-3 pr-7 focus:outline-none sm:text-sm"
                value={tipoConta}
                onChange={(e) => setTipoConta(e.currentTarget.value)}
              >
                <option value="P">Poupança</option>
                <option value="C">Corrente</option>
              </select>
            </div>
          </div>
          <div className="sm:col-span-2">
            <label htmlFor="region" className="block text-md font-medium leading-6 text-white">
              Saldo Inicial
            </label>
            <div className="mt-2">
              <input
                type="number"
                name="price"
                id="price"
                placeholder="0.00"
                autoComplete="address-level1"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm focus:outline-none sm:text-sm sm:leading-6"
                value={saldo}
                onChange={(e) => setSaldo(e.target.valueAsNumber)}
              />
            </div>
          </div>
          <div className="sm:col-span-2">
            <label htmlFor="postal-code" className="block text-md font-medium leading-6 text-white">
              Rendimento
            </label>
            <div className="mt-2">
              <input
                type="number"
                name="postal-code"
                id="postal-code"
                autoComplete="postal-code"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm focus:outline-none sm:text-sm sm:leading-6"
                value={rendimento}
                onChange={(e) => setRendimento(e.target.valueAsNumber)}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="flex gap-5 absolute bottom-4 right-4">
        <button
          className="bg-button hover:bg-zinc-500 text-white font-semibold px-6 py-2 rounded"
          onClick={createAccount}
        >
          Cadastrar
        </button>
        {openModal && <ResultModal onClose={closeModal} mensagem="Conta cadastrada com sucesso" />}
      </div>
    </form>
  );
}