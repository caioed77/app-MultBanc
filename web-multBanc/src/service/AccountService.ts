import { IAccount } from "../@Types/Account";
import { api } from "../service/api";

export async function handleCreateAccount(_account: IAccount): Promise<boolean>{
  try {
    await api.post<IAccount>("conta/cadastrar", _account)
    return true
  } catch (error) {
    
    console.error("Erro ao cadastrada os dados:", error);
  
    return false
  }  
}
