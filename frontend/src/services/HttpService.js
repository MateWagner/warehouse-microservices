import client from "axios";
import UserService from "./UserService";

const _axios = client.create();

const config = () => {
  _axios.interceptors.request.use((config) => {
    if (UserService.isLoggedIn()) {
      const cb = () => {
        config.headers.Authorization = `Bearer ${UserService.getToken()}`;
        return config;
      };
      return UserService.updateToken(cb);
    }
    return config;
  });
};

const getAxiosClient = () => _axios;

const HttpService = {
  config,
  getAxiosClient,
};

export default HttpService;
