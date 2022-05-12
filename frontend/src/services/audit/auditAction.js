import * as BT from "./auditType";
import axios from "axios";

export const saveAudit = (audit) => {
    return (dispatch) => {
      dispatch({
        type: BT.SAVE_AUDIT_REQUEST,
      });
      axios
        .post("http://localhost:8081/api/v1/audit", audit)
        .then((response) => {
          dispatch(auditSuccess(response.data));
        })
        .catch((error) => {
          dispatch(auditFailure(error));
        });
    };
  };
  
  export const updateAudit = (auditId, audit) => {
    return (dispatch) => {
      dispatch({
        type: BT.UPDATE_AUDIT_REQUEST,
      });
      axios
        .put("http://localhost:8081/api/v1/audit/" + auditId, audit)
        .then((response) => {
          dispatch(auditSuccess(response.data));
        })
        .catch((error) => {
          dispatch(auditFailure(error));
        });
    };
  };

  export const fetchAuditByProcess = (processId) => {
    return (dispatch) => {
      dispatch({
        type: BT.FETCH_AUDIT_REQUEST,
      });
      axios
        .get("http://localhost:8081/api/v1/audit/process/" + processId)
        .then((response) => {
          dispatch(auditSuccess(response.data));
        })
        .catch((error) => {
          dispatch(auditFailure(error));
        });
    };
  };

  export const fetchAudit = () => {
    return (dispatch) => {
      dispatch({
        type: BT.FETCH_AUDIT_REQUEST,
      });
      axios
        .get("http://localhost:8081/api/v1/audit")
        .then((response) => {
          dispatch(auditSuccess(response.data));
        })
        .catch((error) => {
          dispatch(auditFailure(error));
        });
    };
  };

  const auditSuccess = (audit) => {
    return {
      type: BT.AUDIT_SUCCESS,
      payload: audit,
    };
  };
  
  const auditFailure = (error) => {
    return {
      type: BT.AUDIT_FAILURE,
      payload: error,
    };
  };
  
  const auditRequest = () => {
    return {
      type: BT.AUDIT_REQUEST,
    };
  };