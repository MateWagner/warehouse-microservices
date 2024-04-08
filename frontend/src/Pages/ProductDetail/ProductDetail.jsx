import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import WarehouseDAO from "../../repository/WarehouseDAO";
import { Button, Typography } from "@mui/material";
import { ACTIONS, useCart } from "../../context/CartContext";
import QuantityInput from "../../components/QuantityInput";

const ProductDetail = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [inventory, setInventory] = useState(null);
  const [amount, setAmount] = useState(1);
  const { state, dispatch } = useCart();

  const addItemToCart = () => {
    dispatch({ type: ACTIONS.ADD_ITEM, payload: { id, amount } });
  };
  const rmItemToCart = () => {
    dispatch({ type: ACTIONS.REMOVE_ITEM, payload: { id, amount } });
  };

  useEffect(() => {
    WarehouseDAO.getProductById(id).then((response) => {
      setProduct(response.data);
    });
    WarehouseDAO.getInventoryInformation(id).then((response) => {
      setInventory(response.data);
    });
  }, []);

  if (!product && !inventory)
    return <Typography variant="h1">Loading...</Typography>;

  return (
    <div>
      Name: {product.name}
      <br />
      Description: {product.description}
      <br />
      Price: {product.price}
      <br />
      Available: {inventory?.stockAmount}
      <br />
      <QuantityInput
        min={1}
        max={inventory?.stockAmount}
        value={amount}
        onChange={(_, val) => setAmount(val)}
      />
      <br />
      <Button onClick={() => addItemToCart()}>Add to cart</Button>
      <br />
      <Button onClick={() => rmItemToCart()}>Rm to cart</Button>
    </div>
  );
};

export default ProductDetail;
