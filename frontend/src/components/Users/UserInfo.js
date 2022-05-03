import React, { Component } from "react";
import { Alert } from "react-bootstrap";

const UserInfo = (props) => {
  return (
    <Alert
      style={{ backgroundColor: "#343A40", color: "#ffffff80", fontSize: 18 }}
    >
      Добро пожаловать! <br></br>
      <br></br> Вы являетесь{" "}
      <b>
        <i>Администратором</i>
      </b>{" "}
      данной программной поддержки. <br></br>Список возможностей представлен
      снизу.
    </Alert>
  );
};

export default UserInfo;
