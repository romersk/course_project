import React from "react";
import { Col, Container, Navbar } from "react-bootstrap";

class Footer extends React.Component {
  render() {
    let fullYear = new Date().getFullYear();

    return (
      <Navbar fixed="bottom" bg="dark" variant="dark">
        <Container>
          <Col lg={12} className="text-center text-muted">
            <div>{fullYear}, Все права защищены</div>
          </Col>
        </Container>
      </Navbar>
    );
  }
}

export default Footer;
