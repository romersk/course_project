import * as BT from "./userListTypes";

const initialState = {
  user: "",
  error: "",
};

const userListReducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_USER_REQUEST:
    case BT.FETCH_USER_REQUEST:
    case BT.UPDATE_USER_REQUEST:
    case BT.DELETE_USER_REQUEST:
    case BT.USER_SUCCESS:
      return {
        user: action.payload,
        error: "",
      };
    case BT.USER_FAILURE:
      return {
        user: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default userListReducer;
