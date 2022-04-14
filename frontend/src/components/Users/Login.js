import React from "react";
import {
  Card,
  Row,
  Col,
  Form,
  InputGroup,
  FormControl,
  Button,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCircleUser,
  faLock,
  faSignInAlt,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { authenticateUser } from "../../services/index";
import { connect } from "react-redux";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    email: "",
    password: "",
  };

  validateUser = () => {
    this.props.authenticateUser(this.state.email, this.state.password);
  };

  resetLogin = () => {
    this.setState(() => this.initialState);
  };

  credChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const { email, password } = this.state;

    return (
      <Row className="justify-content-md-center">
        <Col xs={5}>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              <FontAwesomeIcon icon={faSignInAlt} /> Вход
            </Card.Header>
            <Card.Body>
              <Form>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faCircleUser} />
                    </InputGroup.Text>
                    <FormControl
                      required
                      autoComplete="off"
                      type="text"
                      name="email"
                      value={email}
                      onChange={this.credChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите никнейм"
                    />
                  </InputGroup>
                </Form.Group>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faLock} />
                    </InputGroup.Text>
                    <FormControl
                      required
                      autoComplete="off"
                      type="password"
                      name="password"
                      value={password}
                      onChange={this.credChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите пароль"
                    />
                  </InputGroup>
                </Form.Group>
              </Form>
            </Card.Body>
            <Card.Footer style={{ textAlign: "center" }}>
              <Button
                size="sm"
                type="button"
                variant="success"
                onClick={this.validateUser}
                disabled={
                  this.state.email.length === 0 ||
                  this.state.password.length === 0
                }
                style={{ margin: "10px" }}
              >
                <FontAwesomeIcon icon={faSignInAlt} /> Войти
              </Button>
              <Button
                size="sm"
                type="button"
                variant="info"
                onClick={this.resetLogin}
                disabled={
                  this.state.email.length === 0 &&
                  this.state.password.length === 0
                }
                style={{ margin: "10px" }}
              >
                <FontAwesomeIcon icon={faUndo} /> Сброс
              </Button>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    );
  }
}

export default Login;
