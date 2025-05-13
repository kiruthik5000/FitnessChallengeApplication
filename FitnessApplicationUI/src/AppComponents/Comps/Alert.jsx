// components/AlertMessage.jsx
import React from 'react';
import { Alert, AlertTitle } from '@mui/material';

const AlertMessage = ({ type = 'success', title, message, ...props }) => {
  return (
    <Alert severity={type} {...props}>
      {title && <AlertTitle>{title}</AlertTitle>}
      {message}
    </Alert>
  );
};

export default AlertMessage;
