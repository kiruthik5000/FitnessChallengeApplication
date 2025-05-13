import { Button } from '@mui/material'
import React from 'react'
import './Topbar.css'
const Topbar = () => {
  return (
    <>
    <div className="bar">
        <div className="left">
        <div className="logo"></div>
        <span style={{fontSize:'40px', fontFamily:'cursive'}}>Hop in to get Fit!!</span>
        </div>
        <div>
        <Button>Home</Button>
        <Button>SignUp</Button>
        <Button>About</Button>
        <div/>
    </div>
    </div>
    </>
)
}

export default Topbar