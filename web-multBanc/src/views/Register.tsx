import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../service/api";
import { ArrowLeft } from "@phosphor-icons/react";

export default function Register() {
  const [user, setUser] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  function handleBackPage() {
    navigate("/")
  }

  async function handleCreateUser() {
    try {

      if (senha !== confirmPassword) {
        alert("As senhas não coincidem.");
        return;
      }

      const response = await api.post('/login/novoUsuario', {
        email: user,
        password: senha,
      });

      if (response.status === 201) {
        alert("Usuário cadastrado com sucesso!");
      } else {
        alert("Erro ao cadastrar o usuário.");
      }
    } catch (error) {
      console.error("Erro ao cadastrar o usuário:", error);
      alert("Ocorreu um erro ao cadastrar o usuário.");
    }
  }
  return (

    <div className="relative flex min-h-screen flex-col justify-center overflow-hidden bg-zinc-200 py-6 sm:py-12">
      <div className="relative bg-primary px-6 pt-10 pb-8 shadow-xl mx-auto w-full sm:max-w-md sm:rounded-lg sm:px-10 mt-2">
        <button onClick={handleBackPage}>{<ArrowLeft size={32}/>}</button>
        <div className="mx-auto max-w-md">
          <div className="space-y-6 py-5 text-base leading-7 text-gray-600">
            <form>
              <div className="mb-6">                
                <input
                  type="Email"
                  id="Email"
                  name="Email"
                  className="w-full border-0 border-purple-600 p-2 rounded focus:outline-none focus:border-purple-700"
                  placeholder="Email"
                  value={user}
                  onChange={(e) => setUser(e.target.value)}
                  required
                />
              </div>
              <div className="mb-6">                
                <input
                  type="password"
                  id="nome"
                  name="nome"
                  className="w-full border-0 border-violet-600 p-2 rounded focus:outline-none focus:border-violet-700"
                  placeholder="Senha"
                  value={senha}
                  onChange={(e) => setSenha(e.target.value)}
                  required
                />
              </div>
              <div className="mb-6">                
                <input
                  type="password"
                  id="nome"
                  name="nome"
                  className="w-full border-0 border-violet-600 p-2 rounded focus:outline-none focus:border-violet-700"
                  placeholder="Confirmar senha"
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  required
                />
              </div>
              <button
                onClick={handleCreateUser}                
                className="bg-black text-white w-full font-semibold px-4 py-3 mt-5 rounded hover:bg-zinc-800"
              >
                Cadastrar
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

  );
}