import { combineReducers } from "redux";
import reducer from "./users/auth/authReducer";
import userReducer from "./users/reg/userReducer";
import userListReducer from "./users/userList/userListReducer";

const RootReducer = combineReducers({
  auth: reducer,
  user: userReducer,
  userList: userListReducer,
});

export default RootReducer;
