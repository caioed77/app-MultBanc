/* eslint-disable @typescript-eslint/no-unused-vars */
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { api } from "../service/api";
import { ResultModalErro } from "../components/Modals/resultModalErro";
import { useUser } from "../context/authContext";
import { Bank } from "@phosphor-icons/react";
export default function Login() {

  const [openModal, setOpenModal] = useState(false);
  const [user, setUser] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const { loginUser } = useUser();

  function closeModal() {
    setOpenModal(false)
  }

  function validateLogin() {
    try {
      api.get(`/login/autenticar?email=${user}&senha=${password}`)
        .then((response) => {

          if (response.status === 202) {
            loginUser(response.data)
            localStorage.setItem("user", JSON.stringify(response.data));
            navigate("/home")
          }      
        });

    } catch (error) {
      setOpenModal(true);
      console.error("Erro na requisição", error);
    }
  }

  return (
    <div className="relative flex min-h-screen flex-col justify-center overflow-hidden bg-zinc-200 py-6 sm:py-12">
      <div className="relative bg-primary px-6 pt-10 pb-8 shadow-xl mx-auto w-full sm:max-w-md sm:rounded-lg sm:px-10">
        <div className="mx-auto max-w-md">
          <div className="flex gap-5 items-center">
            <Bank size={52} fill="black" />
            <p className="font-semibold text-2xl">MultBancApp</p>
          </div>

          <div className="space-y-4 py-2 text-base leading-7 text-gray-600">
            <div className="inline-grid gap-4 mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
              <input
                id="email"
                name="email"
                type="email"
                autoComplete="email"
                placeholder="Email"
                required
                className="block w-full rounded-md border-0 px-2 py-3 text-black shadow-sm placeholder:text-gray-400 focus:outline-none sm:text-sm sm:leading-6"
                value={user}
                onChange={(e) => setUser(e.target.value)}
              />
              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                placeholder="Senha"
                required
                className="block w-full rounded-md border-0 px-2 py-3 text-black shadow-sm placeholder:text-gray-400 focus:outline-none sm:text-sm sm:leading-6"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <button
                type="submit"
                className="flex w-full justify-center mt-5 rounded-md bg-black px-3 py-3 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-gray-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-gray-500"
                onClick={validateLogin}
              >
                Entrar
              </button>
            </div>
          </div>
          {openModal && <ResultModalErro onClose={closeModal} mensagem="Usuário ou senha invalido!" />}

          <div className="pt-8 text-base font-semibold leading-7">
            <p className="text-black">Não possui uma conta?</p>
            <p>
              <a href="/register" className="text-white hover:text-zinc-200">Cadastre-se &rarr;</a>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}