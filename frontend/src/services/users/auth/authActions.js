import * as AT from "./authTypes";

export const authenticateUser = (email, password) => {
  return (dispatch) => {
    dispatch(loginRequest());
    if (email === "test" && password === "test") {
      dispatch(success());
    } else {
      dispatch(failure());
    }
  };
};

const loginRequest = () => {
  return {
    type: AT.LOGIN_REQUEST,
  };
};

const logoutRequest = () => {
  return {
    type: AT.LOGOUT_REQUEST,
  };
};

const success = (isLoggedIn) => {
  return {
    type: AT.SUCCESS,
    payload: isLoggedIn,
  };
};

const failure = () => {
  return {
    type: AT.FAILURE,
    payload: false,
  };
};
