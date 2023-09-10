import { useUser } from "../context/authContext";
import { api } from "../service/api";

export default function MyAccount() {

  const { user } = useUser();

  async function handleUpdateAccount() {
    try {
      const response = await api.patch('usuarios/atualizar/' + user?.user, {

      });

      console.log('Dados atualizados com sucesso!', response.data);

    } catch (error) {
      console.error('Erro ao atualizar os dados:', error);
    }
  }
  return (
    <form className="bg-primary rounded-lg shadow-lg p-10 relative">
      <div className="border-b text-5xl border-gray-900/10 pb-6 w-[450px]">
        <h2 className="text-base font-semibold leading-7 text-white">Minha Conta</h2>
        <p className="mt-1 text-sm leading-6 text-white">Cadastro da conta</p>

        <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
          <div className="sm:col-span-3">
            <label htmlFor="first-name" className="block text-sm font-medium leading-6 text-white">
              Numero da conta
            </label>
            <div className="mt-2">
              <input
                type="text"
                name="first-name"
                id="first-name"
                autoComplete="given-name"
                className="block w-full rounded-md border-0 py-1.5 text-gray-900 px-3 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>

          <div className="sm:col-span-3">
            <label htmlFor="last-name" className="block text-sm font-medium leading-6 text-white">
              Agência
            </label>
            <div className="mt-2">
              <input
                type="text"
                name="last-name"
                id="last-name"
                autoComplete="family-name"
                className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 px-3 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>

          <div className="sm:col-span-3">
            <label htmlFor="country" className="block text-sm font-medium leading-6 text-white">
              Tipo da conta
            </label>
            <div className="mt-2">
              <select
                id="country"
                name="country"
                autoComplete="country-name"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6"
              >
                <option>Poupança</option>
                <option>Corrente</option>                
              </select>
            </div>
          </div>
          
          <div className="sm:col-span-2">
            <label htmlFor="region" className="block text-sm font-medium leading-6 text-white">
              Saldo Inicial
            </label>
            <div className="mt-2">
              <input
                type="text"
                name="region"
                id="region"
                autoComplete="address-level1"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>

          <div className="sm:col-span-2">
            <label htmlFor="postal-code" className="block text-sm font-medium leading-6 text-white">
              Rendimento
            </label>
            <div className="mt-2">
              <input
                type="text"
                name="postal-code"
                id="postal-code"
                autoComplete="postal-code"
                className="block w-full rounded-md border-0 py-1.5 px-3 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
              />
            </div>
          </div>          
        </div>
      </div>


      <div className="flex gap-5 absolute bottom-4 right-4">
        <button
          className="bg-button hover:bg-green-600 text-white font-semibold px-6 py-2 rounded"
          onClick={handleUpdateAccount}
        >
          Editar
        </button>
        <button
          className="bg-button hover:bg-green-600 text-white font-semibold px-6 py-2 rounded"
          onClick={handleUpdateAccount}
        >
          Salvar
        </button>
      </div>
      
    </form>
  );


}