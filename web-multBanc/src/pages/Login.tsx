
import { useNavigate } from "react-router-dom";
import NavBar from "../components/NavBar";
import { useState } from "react";
import { api } from "../service/api";

export default function Login() {

  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  async function validateLogin(){
    try {
      const url = `/login/autenticar?email=${user}&senha=${password}`;
      const response = await api.get(url);

      if (response.status === 202) {
        navigate("/home");
      } else {
        console.error("Erro de autenticação");
      }
    } catch (error) {
      console.error("Erro na requisição", error);
    }
  }

  return (
    <>
      <NavBar />
      <div className="flex items-center justify-center min-h-screen overflow-hidden">
        <div className="bg-white text-white rounded-xl flex flex-col md:flex-row items-center justify-center space-y-6 md:space-y-0 md:space-x-4 p-4 md:p-10 max-w-screen-lg mx-auto">
          <div className="md:w-1/2">
            <img
              src="https://prints.ultracoloringpages.com/40838ecd57308ce200f1d06275fd32b4.png"
              alt="Imagem"
              className="rounded-xl w-full "
            />
          </div>
        </div>
        <div className="md:w-[630px] bg-black text-white rounded-xl m-16 p-10">
          <label className="text-4xl font-extrabold font-mono mb-6">Realizar Login</label>
          <div className="flex flex-col gap-2 mt-8">
            <input
              className="border rounded text-lg font-bold text-black py-3 px-3 focus:outline-none focus:ring-2 focus:ring-purple-500"
              type="text"
              placeholder="Email"
              value={user}
              onChange={(e) => setUser(e.target.value)}
            />
            <input
              className="border rounded text-lg Pfont-bold text-black py-3 px-3 mt-2 focus:outline-none focus:ring-2 focus:ring-purple-500"
              type="password"
              placeholder="Senha"              
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <div className="flex flex-col gap-3 justify-center">

                <div onClick={validateLogin} className="w-full inline-flex items-center justify-center bg-violet-600 group rounded-lg border border-transparent px-10 py-4 transition-colors hover:border-violet-400 hover:bg-violet-400 hover:dark:border-violet-300 hover:dark:bg-neutral-800/30">
                   Entrar
                </div>
            
              <button className="mt-5 w-36 text-white px-2 py-2">
                Cadastrar-me
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}