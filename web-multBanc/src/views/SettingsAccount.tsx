import { useState } from "react";
import { api } from "../service/api";
import { useUser } from "../context/authContext";
import { ResultModalErro } from "../components/Dialogs/resultModalErro";
import { ResultModal } from "../components/Dialogs/resultModal";

export default function SettingsAccount() {

  const [documento, setDocumento] = useState('');
  const [tipoPessoa, setTipoPessoa] = useState('');
  const [foto, setFoto] = useState('');
  const [openModalErr, setOpenModalErr] = useState(false);
  const [openModal, setOpenModal] = useState(false);
  const { user } = useUser();

  async function handleUpdateAccount() {


    try {
      const response = await api.patch('usuarios/atualizar/' + user?.user, {
        document: documento,
        imgUser: foto,
        typePerson: tipoPessoa,
      });

      if (response.status === 200) {
        setOpenModal(true);
      }

    } catch (error) {
      setOpenModalErr(true);
      alert('Erro ao atualizar os dados');
    }
  }

  function closeModalErr() {
    setOpenModalErr(false);
    (false)
  }

  function closeModal() {
    setOpenModal(false)
  }

  return (
    <div className="bg-primary rounded-lg shadow-lg p-8 relative">
      <h1 className="text-2xl text-white font-semibold mb-2">Configurações do usuário</h1>
      <div className="flex flex-col space-y-4 w-[430px] h-[350px] mt-5">
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Tipo de Pessoa:</label>
          <select
            className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none"
            value={tipoPessoa}
            onChange={(e) => setTipoPessoa(e.currentTarget.value)}
          >
            <option value="fisica">Pessoa Física</option>
            <option value="juridica">Pessoa Jurídica</option>
          </select>
        </div>
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Documento</label>
          <input
            className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none"
            type="text"
            placeholder="CPF/CNPJ"
            value={documento}
            onChange={(e) => setDocumento(e.target.value)}
          />
        </div>
        <div className="flex flex-col">
          <label className="text-lg text-white mb-2">Foto</label>
          <input
            className="border border-purple-500 font-roboto rounded-lg px-3 py-2 focus:outline-none"
            type="text"
            placeholder="URL Imagem"
            value={foto}
            onChange={(e) => setFoto(e.target.value)}
          />
        </div>
      </div>
      <div className="flex gap-5 absolute bottom-4 right-4">
        <button
          className="bg-button hover:bg-zinc-500 text-white font-semibold px-6 py-2 rounded"
          onClick={handleUpdateAccount}
        >
          Alterar
        </button>
        {openModalErr && <ResultModalErro onClose={closeModalErr} mensagem="CPF invalido, verifique!" />}
        {openModal && <ResultModal onClose={closeModal} mensagem="Alteração realizada com sucesso" />}
      </div>
    </div>
  );
}
