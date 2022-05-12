import React, { useState } from "react";
import {
  Card,
  Row,
  Col,
  Form,
  InputGroup,
  FormControl,
  Button,
  Alert,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCircleUser,
  faLock,
  faSignInAlt,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { authenticateUser } from "../../services/index";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

const Login = (props) => {
  const [error, setError] = useState();
  const [show, setShow] = useState(true);

  const initialState = {
    userName: "",
    password: "",
  };

  const [user, setUser] = useState(initialState);

  const credChange = (event) => {
    const { name, value } = event.target;
    setUser({ ...user, [name]: value });
  };

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const validateUser = () => {
    dispatch(authenticateUser(user.userName, user.password))
      .then((response) => {
        if (response.role === "Администратор") {
          return navigate("/admin/home");
        }
        if (response.role === "Пользователь") {
          return navigate("/user/home");
        }
        if (response.role === "Эксперт") {
          return navigate("/expert/home");
        }
        if (response.role === "Юрист") {
          return navigate("/lawyer/home");
        }
      })
      .catch((error) => {
        console.log(error.message);
        setShow(true);
        resetLoginForm();
        setError("Логин или пароль неверный");
      });
  };

  const resetLoginForm = () => {
    setUser(initialState);
  };
  return (
    <Row className="justify-content-md-center">
      <Col xs={5}>
        {show && props.message && (
          <Alert variant="success" onClose={() => setShow(false)} dismissible>
            {props.message}
          </Alert>
        )}
        {show && error && (
          <Alert variant="danger" onClose={() => setShow(false)} dismissible>
            {error}
          </Alert>
        )}
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
                    name="userName"
                    value={user.userName}
                    onChange={credChange}
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
                    value={user.password}
                    onChange={credChange}
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
              onClick={validateUser}
              disabled={
                user.userName.length === 0 || user.password.length === 0
              }
              style={{ margin: "10px" }}
            >
              <FontAwesomeIcon icon={faSignInAlt} /> Войти
            </Button>
            <Button
              size="sm"
              type="button"
              variant="info"
              onClick={resetLoginForm}
              disabled={
                user.userName.length === 0 && user.password.length === 0
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
};

export default Login;
