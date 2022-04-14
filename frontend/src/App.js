import React from "react";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import Welcome from "./components/Welcome";
import Login from "./components/Users/Login";

function App() {
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
            </Routes>
          </Col>
        </Row>
      </Container>
      <Footer></Footer>
    </Router>
  );
}

export default App;
