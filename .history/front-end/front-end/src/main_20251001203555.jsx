import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from 'front-end/front-end/src/home\Home.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Home />
  </StrictMode>,
)
