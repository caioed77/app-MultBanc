import { Dialog, Transition } from "@headlessui/react";
import { useErrorContext } from "../../context/errorContext";

export const ResultModalErro = () => {
  const erroStore = useErrorContext();
  const { stateErro, addErro } = erroStore;

  const closeModal = () => {
    addErro("");
  };

  return (
    <Transition.Root show={stateErro.mensagemErro !== ""} as="div">
      <Dialog as="div" onClose={closeModal}>
        <div className="fixed inset-0">
          <Transition.Child
            as="div"
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-gray-500 bg-opacity-75" />
          </Transition.Child>

          <Transition.Child
            as="div"
            enter="ease-out duration-300"
            enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            enterTo="opacity-100 translate-y-0 sm:scale-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100 translate-y-0 sm:scale-100"
            leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
          >
            <div className="flex items-center justify-center min-h-screen">
              <Dialog.Panel
                as="div"
                className="bg-white p-4 rounded-lg shadow-xl text-center"
              >
                <div className="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-red-100"></div>
                <Dialog.Title as="h3" className="text-lg font-semibold mt-4">
                  Error
                </Dialog.Title>
                <p className="text-sm text-gray-500 mt-2">
                  {stateErro.mensagemErro}
                </p>
                <div className="mt-4">
                  <button
                    type="button"
                    className="inline-flex justify-center px-3 py-2 text-sm font-semibold bg-blue-500 text-white rounded-md focus:outline-none hover:bg-blue-600"
                    onClick={closeModal}
                  >
                    Fechar
                  </button>
                </div>
              </Dialog.Panel>
            </div>
          </Transition.Child>
        </div>
      </Dialog>
    </Transition.Root>
  );
};
