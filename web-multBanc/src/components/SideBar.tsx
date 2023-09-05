import { useNavigate } from "react-router-dom";

export default function SideBar() {
  const navigate = useNavigate();
  
  
  function handleAccesAccount() {
    navigate("minhaConta");
  }
  
  return (
    <div className="bg-black w-1/7 h-screen p-4">
      <h2 className="text-2xl text-white font-roboto m-8">Multbanc</h2>
      <ul className="m-10 flex flex-col gap-5">
        <li>
          <button onClick={handleAccesAccount} className="text-white py-2 hover:rounded-md hover:bg-purple-400 cursor-pointer transition-colors">Minha conta</button>
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