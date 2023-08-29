/* eslint-disable @next/next/no-img-element */
'use client'
import NavBar from "@/components/NavBar";
import { useRouter } from "next/navigation";

export default function Home() {
  
  const router = useRouter()

  const handleEntrarClick = () => {
    router.push('/login');
  };

  return (
    <>
      <NavBar />
      <div className="flex items-center justify-center min-h-screen overflow-hidden">
        <div className="bg-[#2C0D46] text-white rounded-xl flex flex-col md:flex-row items-center justify-center space-y-6 md:space-y-0 md:space-x-4 p-4 md:p-10 max-w-screen-lg mx-auto">
          <div className="md:w-1/2">
            <img
              src="https://images.prismic.io/statrys/c37ac3cf-fd0e-4d28-a354-66e6ce80a302__ENG_neobanks-vs-challenger-banks.png?auto=compress%2Cformat"
              alt="Imagem"
              className="rounded-xl w-full"
            />
          </div>

          <div className="md:w-1/2 bg-[#2C0D46] text-white rounded-xl p-6">
            <label className="text-4xl font-extrabold font-mono mb-6">
              Realizar Login
            </label>

            <div className="flex flex-col gap-2 mt-10">
              <input
                type="text"
                placeholder="Email"
                className="border rounded text-lg font-bold text-black py-3 px-3 focus:outline-none focus:ring-2 focus:ring-purple-500"
              />
              <input
                type="password"
                placeholder="Senha"
                className="border rounded text-lg font-bold text-black py-3 px-3 mt-2 focus:outline-none focus:ring-2 focus:ring-purple-500"
              />
              <a               
                className="group bg-green-600 rounded-lg border border-white px-3 py-2 hover:bg-green-700"
                target="_blank"
                rel="noopener noreferrer"
              >
                <h2 className={`mb-2 text-2xl font-semibold`} onClick={handleEntrarClick}>
                  Entrar{' '}                
                  <span className="inline-block transition-transform group-hover:bg-green-700 motion-reduce:transform-none">
                    -&gt;
                  </span>
                </h2>                
              </a>
              <div className="inline-flex gap-3 justify-center">
                <button className="mt-5 w-36 text-white px-2 py-2">
                  Cadastrar-me
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
