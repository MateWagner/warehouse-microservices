import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import UserService from "./services/UserService";
import HttpService from "./services/HttpService.js";

// createRoot(document.getElementById("root")).render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );
const renderApp = () =>
  createRoot(document.getElementById("root")).render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
UserService.initKeycloak(renderApp);
HttpService.config();
