import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Login from './views/Login';
import Home from './views/Home';
import Register from './views/Register';
import MyAccount from './views/MyAccount';
import '@radix-ui/themes/styles.css';
import CashMachine from './views/CashMachine';
import { UserProvider } from './context/authContext';
import SettingsAccount from './views/SettingsAccount';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/home",
    element: <Home />,
    children: [
      {
        path: "minhaConta",
        element: <MyAccount />,
      },
      {
        path: "saqueDeposito",
        element: <CashMachine />,
      },
      {
        path: "configuracaoAccount",
        element: <SettingsAccount />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <UserProvider>
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>,
  </UserProvider>
)
