import { useEffect, useState } from "react";
import WarehouseDAO from "../../repository/WarehouseDAO";
import CheckoutDAO from "../../repository/CheckoutDAO";
import { Button } from "@mui/material";
import UserService from "../../services/UserService";

// ${
//         import.meta.env.VITE_WAREHOUSE_URL_PREFIX
//       }

const Products = () => {
  const [first, setFirst] = useState({});
  const [secund, setSecund] = useState({});
  useEffect(() => {
    const address = {
      userID: "3fae8b1d-cd0b-4dae-8d7e-e3d234941058",
      postcode: "8080",
      city: "Budapest",
      street: "Buda",
      areaName: "Utca",
      houseNumber: "23/B",
      isPreferred: true,
    };
    CheckoutDAO.addNewAddress(address).then((res) => setFirst(res.data));
    WarehouseDAO.pageAndSort(0, 10, "name", "ASC").then((res) =>
      setSecund(res.data)
    );
  }, []);

  return (
    <>
      is token expired: {UserService.isTokenExpired().toString()}
      <br />
      <Button onClick={() => UserService.updateToken()}>Update token</Button>
    </>
  );
};

export default Products;
