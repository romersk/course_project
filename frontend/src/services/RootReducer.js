import { combineReducers } from "redux";
import reducer from "./users/auth/authReducer";

const RootReducer = combineReducers({
  auth: reducer,
});

export default RootReducer;
