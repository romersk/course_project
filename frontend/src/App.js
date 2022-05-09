import React from "react";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import Welcome from "./components/Welcome";
import Login from "./components/Users/Login";
import Register from "./components/Users/Register";
import HomeAdmin from "./components/HomeAdmin";
import UserInfo from "./components/Users/UserInfo";
import UserList from "./components/Users/UserList";

const App = () => {
  window.onbeforeunload = (event) => {
    const e = event || window.event;
    e.preventDefault();
    if (e) {
      e.returnValue = "";
    }
    return "";
  };

  const marginTop = {
    marginTop: "20px",
  };

  return (
    <Router>
      <NavigationBar></NavigationBar>
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Routes>
              <Route path="/" element={<Welcome></Welcome>}></Route>
              <Route path="/login" element={<Login></Login>}></Route>
              <Route path="/register" element={<Register></Register>}></Route>
              <Route path="/admin/home" element={<HomeAdmin></HomeAdmin>} />
              <Route path="/admin/users" element={<UserList></UserList>} />
              <Route
                path="/admin/edit/:id"
                element={<UserInfo></UserInfo>}
              ></Route>
              <Route
                path="/admin/userinfo"
                element={<UserInfo></UserInfo>}
              ></Route>
              <Route
                path="/logout"
                element={<Login message="Пользователь вышел" />}
              />
            </Routes>
          </Col>
        </Row>
      </Container>
      <Footer></Footer>
    </Router>
  );
};

export default App;
