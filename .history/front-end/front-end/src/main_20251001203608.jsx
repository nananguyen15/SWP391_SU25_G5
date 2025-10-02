import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Home from 'src/home/Home.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Home />
  </StrictMode>,
)
