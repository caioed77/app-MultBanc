import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Login from '../views/Login';
import Register from '../views/Register';
import Home from '../views/Home';
import MyAccount from '../views/MyAccount';
import LootScreen from '../views/LootScreen';
import SettingsAccount from '../views/SettingsAccount';
import DepositScreen from '../views/DepositScreen';

const AppRoutes: React.FC = () => {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/home" element={<Home />}>
        <Route path="minhaConta" element={<MyAccount />} />
        <Route path="saque" element={<LootScreen />} />
        <Route path="configuracaoAccount" element={<SettingsAccount />} />
        <Route path="deposito" element={<DepositScreen />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;