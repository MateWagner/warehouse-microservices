import * as React from "react";
import { useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import { ACTIONS, useCart } from "../../context/CartContext";
import WarehouseDAO from "../../repository/WarehouseDAO";
import { useState } from "react";
import { useEffect } from "react";
import QuantityInput from "../../components/QuantityInput";
import { Button } from "@mui/material";
import { Link } from "react-router-dom";

const Cart = () => {
  const { state, dispatch } = useCart();

  return (
    <>
      {Object.entries(state.items).map(([id, initialAmount]) => (
        <CartItemCard key={id} id={id} initialAmount={initialAmount} />
      ))}
      <Button component={Link} to={"/order"}>
        Order
      </Button>
    </>
  );
};

function CartItemCard({ id, initialAmount }) {
  const { state, dispatch } = useCart();
  const [product, setProduct] = useState(null);
  const [inventory, setInventory] = useState(null);
  const [amount, setAmount] = useState(initialAmount);
  const theme = useTheme();

  const addItemToCart = () => {
    dispatch({ type: ACTIONS.ADD_ITEM, payload: { id, amount } });
  };

  useEffect(() => {
    WarehouseDAO.getProductById(id).then((response) => {
      setProduct(response.data);
      console.log(response.data);
    });
    WarehouseDAO.getInventoryInformation(id).then((response) => {
      setInventory(response.data);
    });
  }, []);

  if (!product && !inventory)
    return <Typography variant="h1">Loading...</Typography>;

  return (
    <Card sx={{ display: "flex" }}>
      <Box sx={{ display: "flex", flexDirection: "column" }}>
        <CardContent sx={{ flex: "1 0 auto" }}>
          <Typography component="div" variant="h5">
            {product.name}
          </Typography>
        </CardContent>
        <Box sx={{ display: "flex", alignItems: "center", pl: 1, pb: 1 }}>
          <QuantityInput
            min={1}
            max={inventory?.stockAmount}
            value={amount}
            onChange={(_, val) => setAmount(val)}
          />
          <Button onClick={() => addItemToCart()}>Update</Button>
        </Box>
      </Box>
      <CardMedia
        component="img"
        sx={{ width: 151 }}
        image="/Basketball.png"
        alt="Live from space album cover"
      />
    </Card>
  );
}
export default Cart;
