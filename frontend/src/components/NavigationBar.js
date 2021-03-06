import {
  faSignInAlt,
  faSignOutAlt,
  faUserPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Navbar } from "react-bootstrap";
import { Nav } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { logoutUser } from "../services/index";

const NavigationBar = () => {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const logout = () => {
    dispatch(logoutUser());
  };

  const guestLinks = (
    <>
      <div className="mr-auto"></div>
      <Nav className="navbar-right">
        <Link to={"register"} className="nav-link">
          <FontAwesomeIcon
            icon={faUserPlus}
            style={{ marginRight: "5px" }}
          ></FontAwesomeIcon>
          Регистрация
        </Link>
        <Link to={"login"} className="nav-link">
          <FontAwesomeIcon
            icon={faSignInAlt}
            style={{ marginRight: "5px" }}
          ></FontAwesomeIcon>
          Войти
        </Link>
      </Nav>
    </>
  );

  const userLinks = (
    <>
      <Nav className="me-auto">
        <Link to={""} className="nav-link">
          Главная
        </Link>
        <Link to={"contacts"} className="nav-link">
          Контакты
        </Link>
      </Nav>
      <Nav className="navbar-right">
        <Link to={"logout"} className="nav-link" onClick={logout}>
          <FontAwesomeIcon icon={faSignOutAlt} /> Выйти
        </Link>
      </Nav>
    </>
  );

  return (
    <Navbar bg="dark" variant="dark">
      <Link to={""} className="navbar-brand">
        <img
          src="https://cdn.iconscout.com/icon/free/png-256/iso-25-282768.png"
          width="50"
          height="50"
          hspace="10"
          alt="sign"
        ></img>
        Программная поддержка проведения сертификации
      </Link>
      {auth.isLoggedIn ? userLinks : guestLinks}
    </Navbar>
  );
};

export default NavigationBar;
