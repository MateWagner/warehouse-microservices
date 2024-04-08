import HttpService from "../services/HttpService";

const axiosClient = HttpService.getAxiosClient();
const WAREHOUSE_BASE_URL = "/warehouse/api/v1/";

const pageAndSort = async (
  pageNumber,
  pageSize,
  sortProperties,
  sortDirection
) => {
  try {
    return axiosClient.get(
      `${WAREHOUSE_BASE_URL}item/pageAndSort?pageNumber=${pageNumber}&pageSize=${pageSize}&sortProperties=${sortProperties}&sortDirection=${sortDirection}`
    );
  } catch (error) {
    throw Error("Can't fetch Items");
  }
};

const getProductById = async (id) => {
  try {
    return axiosClient.get(`${WAREHOUSE_BASE_URL}item/${id}`);
  } catch (error) {
    throw Error("Can't fetch Item details");
  }
};

const getInventoryInformation = (id) => {
  try {
    return axiosClient.get(`${WAREHOUSE_BASE_URL}inventory/${id}`);
  } catch (error) {
    throw Error("Can't fetch Item inventory information");
  }
};

const WarehouseDAO = {
  pageAndSort,
  getProductById,
  getInventoryInformation,
};

export default WarehouseDAO;
