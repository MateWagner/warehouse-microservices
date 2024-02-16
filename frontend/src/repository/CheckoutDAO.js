import HttpService from "../services/HttpService";

const axiosClient = HttpService.getAxiosClient();
const CHECKOUT_BASE_URL = "/checkout/api/v1";

const addNewAddress = (payload) => {
  try {
    return axiosClient.post(`${CHECKOUT_BASE_URL}/address`, payload);
  } catch (error) {
    throw Error(error);
  }
};

const CheckoutDAO = {
  addNewAddress,
};

export default CheckoutDAO;
