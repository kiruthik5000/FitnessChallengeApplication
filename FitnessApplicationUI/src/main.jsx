import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Navigator from './AppComponents/Navigator'
import { BrowserRouter } from 'react-router-dom'
import Topbar from './AppComponents/Topbar'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
      <Topbar/>
      <Navigator/>
    </BrowserRouter>
  </StrictMode>,
)
