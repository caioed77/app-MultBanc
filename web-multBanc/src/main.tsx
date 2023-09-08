import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import Login from './pages/Login';
import Home from './pages/Home';
import Register from './pages/Register';
import MyAccount from './pages/MyAccount';
import '@radix-ui/themes/styles.css';
import CashMachine from './pages/CashMachine';
import { UserProvider } from './context/authContext';


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
