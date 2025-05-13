import React, { useState, useEffect } from "react";
import {
  TextField,
  Button,
  Paper,
  Typography,
  Box,
  Tabs,
  Tab,
  Alert,
} from "@mui/material";
import "./AuthForm.css";
import axios from "axios";

const AuthForm = () => {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const [isLogin, setIsLogin] = useState(true);
  const [error, setError] = useState("");
  const [alertInfo, setAlertInfo] = useState(null); // { type: 'success' | 'error', message: string }
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    name: "",
  });
  const [response, setResponse] = useState();

  const validate = () => {
    let err = "";
    if (!isLogin && formData.name.length < 3)
      err = "UserName must be at least 3 characters.";
    else if (!regex.test(formData.email))
      err = "Enter a valid email address.";
    else if (formData.password.length < 8)
      err = "Password must be at least 8 characters.";
    setError(err);
    return err === "";
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    try {
      if (isLogin) {
        const res = await axios.post(
          `http://localhost:8000/login/?userEmail=${formData.email}&password=${formData.password}`
        );
setAlertInfo({
  type: res.data === "Login Successful" ? "success" : "error",
  message: res.data,
});
        console.log("Login response:", res.data);
      } else {
        const res = await axios.post(`http://localhost:8000/adduser`, {
          userName: formData.name,
          userEmail: formData.email,
          password: formData.password,
        });
        setAlertInfo({ type: "success", message: "Signup successful!" });
        console.log("Signup response:", res.data);  
      }
    } catch (error) {
      const errMsg =
        error.response?.data?.message || "Something went wrong!";
      setAlertInfo({ type: "error", message: errMsg });
      console.error("Request failed:", errMsg);
    }
  };

  // Optional: auto-dismiss alert after 4 seconds
  useEffect(() => {
    if (alertInfo) {
      const timer = setTimeout(() => setAlertInfo(null), 4000);
      return () => clearTimeout(timer);
    }
  }, [alertInfo]);

  return (
    <>
      {alertInfo && (
        <Box m={2}>
          <Alert
            severity={alertInfo.type}
            onClose={() => setAlertInfo(null)}
          >
            {alertInfo.message}
          </Alert>
        </Box>
      )}
      <Box className="auth-container">
        <Paper
          elevation={3}
          className="auth-paper"
          style={{ borderRadius: "30px" }}
        >
          <Tabs
            value={isLogin ? 0 : 1}
            onChange={(e, val) => setIsLogin(val === 0)}
            centered
          >
            <Tab label="Login" />
            <Tab label="Sign Up" />
          </Tabs>
          <form onSubmit={handleSubmit} className="auth-form">
            {!isLogin && (
              <TextField
                label="Name"
                name="name"
                value={formData.name}
                onChange={handleChange}
                fullWidth
                margin="normal"
              />
            )}
            <TextField
              label="Email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              fullWidth
              margin="normal"
            />
            <TextField
              label="Password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              fullWidth
              margin="normal"
            />
            <Button
              type="submit"
              variant="contained"
              style={{ backgroundColor: "green", borderRadius: "30px" }}
              fullWidth
              className="auth-button"
            >
              {isLogin ? "Login" : "Sign Up"}
            </Button>
            {error && <Typography color="error">{error}</Typography>}
          </form>
        </Paper>
      </Box>
    </>
  );
};

export default AuthForm;
