import * as AT from "./authTypes";
import axios from "axios";

export const authenticateUser = (userName, password) => async (dispatch) => {
  const credentials = {
    userName: userName,
    password: password,
  };
  dispatch(loginRequest());
  try {
    const response = await axios.post(
      "http://localhost:8081/api/v1/auth/login",
      credentials
    );
    localStorage.setItem("jwtToken", response.data.token);
    dispatch(
      success({
        username: response.data.fio,
        isLoggedIn: true,
        id: response.data.id,
      })
    );
    return Promise.resolve(response.data);
  } catch (error) {
    dispatch(failure());
    return Promise.reject(error);
  }
};

export const logoutUser = () => {
  return (dispatch) => {
    dispatch(logoutRequest());
    localStorage.removeItem("jwtToken");
    dispatch(success({ username: "", isLoggedIn: false, role: "" }));
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
