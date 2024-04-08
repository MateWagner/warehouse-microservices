import {
  createContext,
  useReducer,
  useContext,
  useEffect,
  useState,
} from "react";

const CART_STORAGE_KEY = "cart";

let initialState = {
  items: {},
  totalAmount: function () {
    return Object.values(this.items).reduce((acc, curr) => (acc += curr), 0);
  },
};

export const ACTIONS = {
  ADD_ITEM: "add-item",
  REMOVE_ITEM: "remove-item",
  CLEAR_CART: "clear-cart",
  SET_CART: "set-cart",
};

const getTotal = (items) => {
  return Object.values(items).reduce((acc, curr) => (acc += curr));
};
const cartReducer = (state, action) => {
  let copyItems = structuredClone(state.items);
  switch (action.type) {
    case ACTIONS.ADD_ITEM:
      copyItems[action.payload.id] = action.payload.amount;
      return {
        ...state,
        items: { ...copyItems },
      };

    case ACTIONS.REMOVE_ITEM:
      const modifiedItems = Object.keys(state.items).reduce((acc, key) => {
        if (key !== action.payload.id) acc[key] = state.items[key];
        return acc;
      }, {});
      return {
        ...state,
        items: { ...modifiedItems },
      };

    case ACTIONS.CLEAR_CART:
      return {
        ...state,
        items: {},
      };
    case ACTIONS.SET_CART:
      return {
        ...state,
        items: action.payload,
      };

    default:
      return state;
  }
};
const CartContext = createContext();

export const useCart = () => {
  return useContext(CartContext);
};

const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const storedCart = localStorage.getItem(CART_STORAGE_KEY);
    if (storedCart) {
      dispatch({ type: ACTIONS.SET_CART, payload: JSON.parse(storedCart) });
    }
    setIsLoading(false);
  }, []);

  useEffect(() => {
    if (!isLoading) {
      localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(state.items));
    }
  }, [state, isLoading]);

  return (
    <CartContext.Provider value={{ state, dispatch }}>
      {children}
    </CartContext.Provider>
  );
};

export default CartProvider;
