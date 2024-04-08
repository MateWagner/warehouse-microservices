import { createBrowserRouter } from "react-router-dom";
import RootLayout from "../layout/RootLayout";
import Products from "../Pages/Products";
import ProductDetail from "../Pages/ProductDetail";
import CartProvider from "../context/CartContext";
import Cart from "../Pages/Cart";
import Order from "../Pages/Order";
import RenderOnAuthenticated from "../components/RenderOnAuthenticated";

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <CartProvider>
        <RootLayout />
      </CartProvider>
    ),
    children: [
      {
        index: true,
        element: <Products />,
      },
      {
        path: "/product/:id",
        element: <ProductDetail />,
      },
      {
        path: "/cart",
        element: <Cart />,
      },
      {
        element: <RenderOnAuthenticated />,
        children: [
          {
            path: "/order",
            element: <Order />,
          },
        ],
      },
    ],
  },
]);

export default router;
