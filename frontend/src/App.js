import React from "react";
import NavigationBar from "./components/NavigationBar";
import { BrowserRouter } from "react-router-dom";
import Footer from "./components/Footer";

function App() {
  return (
    <Router>
      <NavigationBar></NavigationBar>
      <Container>
        <Row>
          <Col lg={12} className={"margin-top"}>
            <Switch></Switch>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;
