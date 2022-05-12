import * as BT from "./processTypes";
import axios from "axios";

export const saveProcess = (process) => {
  return (dispatch) => {
    dispatch({
      type: BT.SAVE_PROCESS_REQUEST,
    });
    axios
      .post("http://localhost:8081/api/v1/process", process)
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error));
      });
  };
};

export const fetchProcessById = (processId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_PROCESS_REQUEST,
    });
    axios
      .get("http://localhost:8081/api/v1/process/" + processId)
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error));
      });
  };
};

export const updateProcess = (processId, process) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_PROCESS_REQUEST,
    });
    axios
      .put("http://localhost:8081/api/v1/process/" + processId, process)
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error));
      });
  };
};

export const deleteProcess = (processId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_PROCESS_REQUEST,
    });
    axios
      .delete("http://localhost:8081/api/v1/process/" + processId)
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error));
      });
  };
};

export const fetchProcess = () => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process")
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

export const fetchProcessByStageOne = () => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process/stage/1")
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

export const fetchProcessByStageTwo = () => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process/stage/2")
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

export const fetchProcessByStageThree = () => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process/stage/3")
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

export const fetchProcessByStageFour = () => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process/stage/4")
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

export const fetchProcessByIdUser = (userId) => {
  return (dispatch) => {
    dispatch(processRequest());
    axios
      .get("http://localhost:8081/api/v1/process/userId/" + userId)
      .then((response) => {
        dispatch(processSuccess(response.data));
      })
      .catch((error) => {
        dispatch(processFailure(error.message));
      });
  };
};

const processSuccess = (process) => {
  return {
    type: BT.PROCESS_SUCCESS,
    payload: process,
  };
};

const processFailure = (error) => {
  return {
    type: BT.PROCESS_FAILURE,
    payload: error,
  };
};

const processRequest = () => {
  return {
    type: BT.PROCESS_REQUEST,
  };
};
