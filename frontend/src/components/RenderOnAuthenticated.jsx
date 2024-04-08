import UserService from "../services/UserService";
import { Outlet } from "react-router-dom";

const RenderOnAuthenticated = () =>
  UserService.isLoggedIn() ? <Outlet /> : null;

export default RenderOnAuthenticated;
