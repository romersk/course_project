import * as BT from "./userListTypes";
import axios from "axios";

export const saveUser = (user) => {
  return (dispatch) => {
    dispatch({
      type: BT.SAVE_USER_REQUEST,
    });
    axios
      .post("http://localhost:8081/api/v1/admin/users", user)
      .then((response) => {
        dispatch(userSuccess(response.data));
      })
      .catch((error) => {
        dispatch(userFailure(error));
      });
  };
};

export const fetchUser = (userId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_USER_REQUEST,
    });
    axios
      .get("http://localhost:8081/api/v1/admin/users/" + userId)
      .then((response) => {
        dispatch(userSuccess(response.data));
      })
      .catch((error) => {
        dispatch(userFailure(error));
      });
  };
};

export const updateUser = (user) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_USER_REQUEST,
    });
    axios
      .put("http://localhost:8081/api/v1/admin/users", user)
      .then((response) => {
        dispatch(userSuccess(response.data));
      })
      .catch((error) => {
        dispatch(userFailure(error));
      });
  };
};

export const deleteUser = (userId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_USER_REQUEST,
    });
    axios
      .delete("http://localhost:8081/api/v1/admin/users/" + userId)
      .then((response) => {
        dispatch(userSuccess(response.data));
      })
      .catch((error) => {
        dispatch(userFailure(error));
      });
  };
};

const userSuccess = (user) => {
  return {
    type: BT.USER_SUCCESS,
    payload: user,
  };
};

const userFailure = (error) => {
  return {
    type: BT.USER_FAILURE,
    payload: error,
  };
};
