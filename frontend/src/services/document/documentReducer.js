import * as BT from "./documentType";

const initialState = {
  document: "",
  error: "",
};

const documentReducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_DOCUMENT_REQUEST:
    case BT.FETCH_DOCUMENT_REQUEST:
    case BT.UPDATE_DOCUMENT_REQUEST:
    case BT.DELETE_DOCUMENT_REQUEST:
    case BT.DOCUMENT_SUCCESS:
      return {
        document: action.payload,
        error: "",
      };
    case BT.DOCUMENT_FAILURE:
      return {
        document: "",
        error: action.payload,
      };
    case BT.DOCUMENT_REQUEST:
      return {
        ...state,
      };
    default:
      return state;
  }
};

export default documentReducer;