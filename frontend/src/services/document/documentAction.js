import * as BT from "./documentType";
import axios from "axios";

export const saveDocument = (document) => {
    return (dispatch) => {
      dispatch({
        type: BT.SAVE_DOCUMENT_REQUEST,
      });
      axios
        .post("http://localhost:8081/api/v1/document", document)
        .then((response) => {
          dispatch(documentSuccess(response.data));
        })
        .catch((error) => {
          dispatch(documentFailure(error));
        });
    };
  };

  export const fetchDocumentByProcess = (processId) => {
    return (dispatch) => {
      dispatch({
        type: BT.FETCH_DOCUMENT_REQUEST,
      });
      axios
        .get("http://localhost:8081/api/v1/audit/documents/" + processId)
        .then((response) => {
          dispatch(documentSuccess(response.data));
        })
        .catch((error) => {
          dispatch(documentFailure(error));
        });
    };
  };

  const documentSuccess = (audit) => {
    return {
      type: BT.DOCUMENT_SUCCESS,
      payload: audit,
    };
  };
  
  const documentFailure = (error) => {
    return {
      type: BT.DOCUMENT_FAILURE,
      payload: error,
    };
  };
  
  const documentRequest = () => {
    return {
      type: BT.DOCUMENT_REQUEST,
    };
  };