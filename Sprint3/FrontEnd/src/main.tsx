import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import GlobalConfig from './styled-globalConfig.ts'

import { createBrowserRouter, RouterProvider } from "react-router-dom"

import Home from "./routes/Home/index.tsx"
import ChatBot from "./routes/ChatBot/index.tsx"
import Login from "./routes/Login/index.tsx"
import Colaboradores from "./routes/Colaboradores/index.tsx"
import Error from './routes/Error/index.tsx'
import CadastroUsuario from './routes/CadastroUsuario/index.tsx'
import CadastroAutomovel from './routes/CadastroAutomovel/index.tsx'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <Error />,
    children: [
      {
        path: "/",
        element: <Home />
      },
      {
        path: "/chatbot",
        element: <ChatBot />
      },
      {
        path: "/login",
        element: <Login />
      },
      {
        path: "/colaboradores",
        element: <Colaboradores />
      },
      {
        path: "/cadastro/usuario",
        element: <CadastroUsuario />
      },
      {
        path: "/cadastro/automovel",
        element: <CadastroAutomovel />
      }
    ]

  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
    <GlobalConfig/>
  </StrictMode>,
)
