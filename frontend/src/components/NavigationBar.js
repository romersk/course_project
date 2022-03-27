import React from "react";
import { Navbar } from "react-bootstrap";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

class NavigationBar extends React.Component {
  render() {
    return (
      <Navbar bg="dark" variant="dark">
        <Navbar.Brand href="#home">
          <img
            src="https://cdn.iconscout.com/icon/free/png-256/iso-25-282768.png"
            width="50"
            height="50"
            hspace="10"
          ></img>
          Программная поддержка проведения сертификации
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="#home">Главная</Nav.Link>
          <Nav.Link href="#pricing">Контакты</Nav.Link>
        </Nav>
      </Navbar>
    );
  }
}

export default NavigationBar;
