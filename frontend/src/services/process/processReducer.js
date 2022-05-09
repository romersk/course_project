import * as BT from "./processTypes";

const initialState = {
  process: "",
  error: "",
};

const processReducer = (state = initialState, action) => {
  switch (action.type) {
    case BT.SAVE_PROCESS_REQUEST:
    case BT.FETCH_PROCESS_REQUEST:
    case BT.UPDATE_PROCESS_REQUEST:
    case BT.DELETE_PROCESS_REQUEST:
    case BT.PROCESS_SUCCESS:
      return {
        process: action.payload,
        error: "",
      };
    case BT.PROCESS_FAILURE:
      return {
        process: "",
        error: action.payload,
      };
    case BT.PROCESS_REQUEST:
      return {
        ...state,
      };
    default:
      return state;
  }
};

export default processReducer;
