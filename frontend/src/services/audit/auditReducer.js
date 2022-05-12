import * as BT from "./auditType";

const initialState = {
  audit: "",
  error: "",
};

const auditReducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_AUDIT_REQUEST:
    case BT.FETCH_AUDIT_REQUEST:
    case BT.UPDATE_AUDIT_REQUEST:
    case BT.DELETE_AUDIT_REQUEST:
    case BT.AUDIT_SUCCESS:
      return {
        audit: action.payload,
        error: "",
      };
    case BT.AUDIT_FAILURE:
      return {
        audit: "",
        error: action.payload,
      };
    case BT.AUDIT_REQUEST:
      return {
        ...state,
      };
    default:
      return state;
  }
};

export default auditReducer;