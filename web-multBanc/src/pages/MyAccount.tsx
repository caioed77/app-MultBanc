export default function MyAccount() {
  return (
    <div className="bg-black rounded-lg shadow-lg p-10 relative">
      <h1 className="text-4xl text-white font-semibold mb-4">Minha Conta</h1>
      <div className="flex flex-col space-y-4 w-[430px] h-[350px] mt-10">
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Tipo de Pessoa:</label>
          <select className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none">
            <option value="FISICA">Pessoa Física</option>
            <option value="JURIDICA">Pessoa Jurídica</option>
          </select>
        </div>
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Documento</label>
          <input className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none" type="text" placeholder="CPF/CNPJ"/>
        </div>
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Foto</label>
          <input className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none" type="text" placeholder="URL Imagem"/>
        </div>
      </div>
      <div className="flex gap-5 absolute bottom-4 right-4">
      <button className="bg-violet-600 hover:bg-violet-700 text-white font-semibold px-6 py-2 rounded">
        Editar
      </button>
      <button className="bg-violet-600 hover:bg-violet-700 text-white font-semibold px-6 py-2 rounded">
        Salvar
      </button>
      </div>
    </div>
  );
}