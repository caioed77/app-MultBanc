import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../service/api";

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
    <div className="min-h-screen flex items-center justify-center relative">
      <div className="bg-primary p-10 rounded-lg shadow-lg">
        <h2 className="text-2xl text-white font-roboto font-semibold mb-3">Cadastro de Usuário</h2>
        <form>
          <div className="mb-4">
            <label htmlFor="nome" className="block text-white font-semibold">
              Nome
            </label>
            <input
              type="text"
              id="nome"
              name="nome"
              className="w-full border border-purple-600 p-2 rounded focus:outline-none focus:border-purple-700"
              value={user}
              onChange={(e) => setUser(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="nome" className="block text-white font-semibold">
              Senha
            </label>
            <input
              type="password"
              id="nome"
              name="nome"
              className="w-full border border-violet-600 p-2 rounded focus:outline-none focus:border-violet-700"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="nome" className="block text-white font-semibold">
              Confirme sua senha
            </label>
            <input
              type="password"
              id="nome"
              name="nome"
              className="w-full border border-violet-600 p-2 rounded focus:outline-none focus:border-violet-700"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
          </div>
          <div className="flex flex-row gap-5 justify-center items-center">
            <button
              onClick={handleCreateUser}
              type="submit"
              className="bg-button text-white font-semibold px-4 py-2 rounded hover:bg-green-600"
            >
              Cadastrar
            </button>
            <button
              onClick={handleBackPage}
              type="reset"
              className="bg-button text-white font-semibold px-4 py-2 rounded hover:bg-green-600"
            >
              Voltar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}