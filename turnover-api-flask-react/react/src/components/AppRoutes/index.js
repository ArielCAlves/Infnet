import { BrowserRouter, Route, Routes } from "react-router-dom";
import Usuarios from "../../Pages/Usuarios";
import Home from "../../Pages/Home";
import Turnover from "../../Pages/Turnover";
import Configuracoes from "../../Pages/Configuracoes";

function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Home />}></Route>
      <Route path="/turnover" element={<Turnover />}></Route>      
      <Route path="/usuarios" element={<Usuarios />}></Route>
      <Route path="/configuracoes" element={<Configuracoes />}></Route>
    </Routes>
  );
}
export default AppRoutes;
