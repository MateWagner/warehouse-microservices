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

const WarehouseDAO = {
  pageAndSort,
};

export default WarehouseDAO;
