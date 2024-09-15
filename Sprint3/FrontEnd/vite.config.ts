import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: '0.0.0.0', // Permite conexões externas
//port: 3401, // A porta que você deseja usar
    strictPort: true, // Se a porta estiver ocupada, o servidor não iniciará
  },
})