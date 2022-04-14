import React from "react";
import { Jumbotron } from "reactstrap";

class Welcome extends React.Component {
  render() {
    return (
      <Jumbotron className="bg-dark text-white">
        <h1>Добро пожаловать!</h1>
      </Jumbotron>
    );
  }
}

export default Welcome;
