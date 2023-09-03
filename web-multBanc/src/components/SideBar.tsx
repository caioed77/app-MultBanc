export default function SideBar() {
  return (
    <div className="bg-black w-1/7 h-screen p-4">
      <h2 className="text-2xl text-white font-roboto m-8">Multbanc</h2>
      <ul className="m-10 flex flex-col gap-5">
        <li>
          <div className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Minha conta</div>
        </li>
        <li>
          <div className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Saque</div>
        </li>
        <li>
          <div className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Deposito</div>
        </li>
        <li>
          <div className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Transferencia</div>
        </li>
        <li>
          <div className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Relatorio</div>
        </li>
      </ul>
    </div>
  );
}
