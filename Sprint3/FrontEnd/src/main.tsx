import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import GlobalConfig from './styled-globalConfig.ts'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
    <GlobalConfig/>
  </StrictMode>,
)
