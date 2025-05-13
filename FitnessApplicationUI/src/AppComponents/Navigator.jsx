import React from 'react'
import { Route, Routes } from 'react-router-dom'
import AuthForm from './SignUp/AuthForm'
import Topbar from './Topbar'

const Navigator = () => {
  return (
    <Routes>
        <Route path='/auth'element= <AuthForm/> ></Route>
        {/* <Route path='/'element=<Topbar/>  ></Route> */}
    </Routes>
  )
}

export default Navigator