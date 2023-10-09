import { useState } from 'react';

export interface IAppError {
  mensagemErro: string;
}

export const useErroStore = () => {
  const [stateErro, setStateErro] = useState<IAppError>({ mensagemErro: '' });

  function addErro(mensagemErro: string) {
    setStateErro({ mensagemErro });
  }

  return { stateErro, addErro };
};