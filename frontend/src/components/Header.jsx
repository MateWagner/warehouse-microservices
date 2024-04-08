import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import UserService from "../services/UserService";
import { Badge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";

const Header = () => {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            News
          </Typography>
          <CartBadge />
          <Button color="inherit" onClick={() => UserService.doLogin()}>
            Login
          </Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

const CartBadge = () => {
  const { state, dispatch } = useCart();
  return (
    <Badge
      component={Link}
      to={"/cart"}
      badgeContent={state.totalAmount()}
      color="error"
    >
      <ShoppingCartIcon color="action" />
    </Badge>
  );
};

export default Header;
