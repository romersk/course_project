import { combineReducers } from "redux";
import processReducer from "./process/processReducer";
import reducer from "./users/auth/authReducer";
import userReducer from "./users/reg/userReducer";
import userListReducer from "./users/userList/userListReducer";

const RootReducer = combineReducers({
  auth: reducer,
  users: userReducer,
  user: userListReducer,
  process: processReducer,
});

export default RootReducer;
