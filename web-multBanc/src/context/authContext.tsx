/* eslint-disable @typescript-eslint/no-unused-vars */
/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState, ReactNode, useEffect } from "react";
import { TAccountItens } from "../@Types/AccountItens";

type UserContextType = {
    user: TAccountItens | null;
    loginUser: (userData: TAccountItens) => void;
    logout: () => void;
};

const UserContext = createContext<UserContextType | null>(null);

type UserProviderProps = {
    children: ReactNode;
};
export const UserProvider: React.FC<UserProviderProps> = ({ children }) => {
    const [user, setUser] = useState<TAccountItens | null>(null);
  

    useEffect(() => {
        const userStorage = localStorage.getItem("user");
    
        if (userStorage) {
          setUser(JSON.parse(userStorage));
        }
      }, []);

    function loginUser(userData: TAccountItens) {
      setUser(userData);
    }
  
    function logout() {
      setUser(null);
    }
  
    return (
      <UserContext.Provider value={{ user, loginUser, logout }}>
        {children}
      </UserContext.Provider>
    );
  };

  export function useUser(): UserContextType {
    const contexto = useContext(UserContext);
  
    if (!contexto) {
      throw new Error("useUser deve ser usado dentro de um UserProvider");
    }
  
    return contexto;
  }