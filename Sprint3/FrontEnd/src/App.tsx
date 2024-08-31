import { Outlet } from "react-router-dom"
import Cabecalho from "./components/Cabecalho"

function App() {

  return (
      <div>
        <Cabecalho />
        <Outlet />
      </div>
  )
}

export default App
