export default function MyAccount() {
  return (
    <div className="bg-white rounded-lg shadow-lg p-6">
      <h1 className="text-2xl font-semibold mb-4">Minha Conta</h1>
      <div className="flex items-center mb-4">
        <img src="" alt="Imagem do Usuário" className="w-16 h-16 rounded-full mr-4" />
        <div>
          <p className="text-lg">Tipo conta</p>
          <p className="text-gray-600">Documento: </p>
        </div>
      </div>
    </div>
  );
}