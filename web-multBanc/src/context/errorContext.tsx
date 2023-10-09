/* eslint-disable react-refresh/only-export-components */
import React, { createContext, useContext, ReactNode } from 'react';
import { useErroStore } from '../hooks/errorHook';

export interface IAppError {
  mensagemErro: string;
}

interface IErrorContext {
  addErro: (mensagemErro: string) => void;
  stateErro: IAppError;
}

const ErrorContext = createContext<IErrorContext | undefined>(undefined);

export const ErrorProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const useErro = useErroStore(); 

  return (
    <ErrorContext.Provider value={useErro}>
      {children}
    </ErrorContext.Provider>
  );
};

export const useErrorContext = (): IErrorContext => {
  const context = useContext(ErrorContext);

  if (!context) {
    throw new Error('useErrorContext deve ser usado dentro de um ErrorProvider');
  }

  return context;
};
