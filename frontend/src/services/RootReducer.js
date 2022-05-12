import { combineReducers } from "redux";
import processReducer from "./process/processReducer";
import reducer from "./users/auth/authReducer";
import userReducer from "./users/reg/userReducer";
import userListReducer from "./users/userList/userListReducer";
import auditReducer from "./audit/auditReducer"
import documentReducer from "./document/documentReducer";

const RootReducer = combineReducers({
  auth: reducer,
  users: userReducer,
  user: userListReducer,
  process: processReducer,
  audit: auditReducer,
  document: documentReducer,
});

export default RootReducer;
