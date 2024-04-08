import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import UserService from "./services/UserService";
import HttpService from "./services/HttpService.js";
import router from "./routes/router.jsx";
import theme from "./theme/theme";
import { ThemeProvider } from "@mui/material";
import { CssBaseline } from "@mui/material";

const renderApp = () =>
  ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <RouterProvider router={router} />
      </ThemeProvider>
    </React.StrictMode>
  );
UserService.initKeycloak(renderApp);
HttpService.config();
