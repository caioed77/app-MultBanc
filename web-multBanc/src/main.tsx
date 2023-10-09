/* eslint-disable react-refresh/only-export-components */
import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import "@radix-ui/themes/styles.css";
import { UserProvider } from "./context/authContext";
import { BrowserRouter as Router } from "react-router-dom";
import { useErroStore } from "./hooks/errorHook";
import AppRoutes from "./routes";
import { ResultModalErro } from "./components/Modals/resultModalErro";
import { ErrorProvider } from "./context/errorContext";

const MainApp = () => {
  const erroStore = useErroStore();
  const { stateErro } = erroStore;
  return (
    <>
      <ResultModalErro />
      <AppRoutes />
      <div>
        <h1>{stateErro.mensagemErro && <div>{stateErro.mensagemErro}</div>}</h1>
      </div>
    </>
  );
};

ReactDOM.createRoot(document.getElementById("root")!).render(
  <UserProvider>
    <ErrorProvider>
      <React.StrictMode>
        <Router>
          <MainApp />
        </Router>
      </React.StrictMode>
    </ErrorProvider>
  </UserProvider>
);
