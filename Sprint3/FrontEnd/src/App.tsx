import { Outlet } from "react-router-dom"
import Cabecalho from "./components/Cabecalho"
import { AppStyle } from "./styledConfig"

function App() {

  return (
      <AppStyle>
        <Cabecalho />
        <Outlet />
      </AppStyle>
  )
}

export default App
