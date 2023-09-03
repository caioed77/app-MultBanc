import SideBar from "../components/SideBar";

export default function Home() {
  return (
    <div className="flex flex-row overflow-hidden">
      <SideBar />
      <div className="bg-gray-200 min-h-screen p-4 w-screen overflow-hidden">
        <header className="bg-black p-4 text-white">
          <nav className="flex justify-end space-x-4">
            <a href="#" className="hover:underline">Configurações</a>
          </nav>
        </header>
        <main className="bg-white p-4 shadow-md rounded-md overflow-hidden">
          <div>
            <h1 className="text-2xl font-semibold mb-4">Formulário</h1>
            <form>
              <div className="mb-4 h-screen">
                <label htmlFor="nome" className="block font-semibold mb-2">Nome:</label>
                <input type="text" id="nome" name="nome" className="border rounded-md p-2 w-full" />
              </div>
              <div className="text-right">
                <button type="submit" className="bg-purple-600 text-white px-4 py-2 rounded-md hover:bg-purple-700">Enviar</button>
              </div>
            </form>
          </div>
        </main>
      </div>
    </div>
  );
}