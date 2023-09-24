import { XSquare } from '@phosphor-icons/react'

interface ErrResultProps {
  onClose: () => void;
  mensagem: string;
}

export function ResultModalErro({ onClose, mensagem }: ErrResultProps) {
  return (
    <div className="fixed top-0 left-0 w-full h-full flex justify-center items-center z-50">
      <div className="bg-black rounded-2xl shadow-md m-10 py-8 px-6">
        <div className="flex justify-center items-center mb-3">
          <XSquare size={60} fill="red" />
        </div>
        <p className="text-white font-roboto font-semibold">{mensagem}</p>
        <button className="mt-8 bg-button text-white hover:bg-zinc-500 rounded px-2 py-1" onClick={onClose}>
          Fechar
        </button>
      </div>
    </div>
  );
}