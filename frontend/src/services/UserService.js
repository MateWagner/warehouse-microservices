import Keycloak from "keycloak-js";

const _kc = new Keycloak({
  url: "/auth",
  realm: "warehouse-microservices",
  "ssl-required": "external",
  clientId: "frontend",
});

/**
 * Initializes Keycloak instance and calls the provided callback function if successfully authenticated.
 *
 * @param onAuthenticatedCallback
 */
const initKeycloak = (onAuthenticatedCallback) => {
  _kc
    .init({
      onLoad: "check-sso",
      silentCheckSsoRedirectUri:
        window.location.origin + "/silent-check-sso.html",
      pkceMethod: "S256",
      checkLoginIframe: false,
    })
    .then((authenticated) => {
      if (!authenticated) {
        console.log("user is not authenticated..!");
      }
      onAuthenticatedCallback();
    })
    .catch((err) => {
      console.error(err);
    });
};

const doLogin = _kc.login;

const doLogout = _kc.logout;

const getToken = () => _kc.token;

const isTokenExpired = () => _kc.isTokenExpired();

const getTokenParsed = () => _kc.tokenParsed;

const isLoggedIn = () => !!_kc.token;

const updateToken = async (successCallback) =>
  _kc.updateToken(5).then(successCallback).catch(doLogin);

const getUsername = () => _kc.tokenParsed?.preferred_username;

const hasRole = (roles) => roles.some((role) => _kc.hasRealmRole(role));

const UserService = {
  initKeycloak,
  doLogin,
  doLogout,
  isLoggedIn,
  isTokenExpired,
  getToken,
  getTokenParsed,
  updateToken,
  getUsername,
  hasRole,
};

export default UserService;
