import React, { useState } from "react";
import { useDispatch } from "react-redux";
import {
  Row,
  Col,
  Card,
  Form,
  InputGroup,
  FormControl,
  Button,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faPhone,
  faEnvelope,
  faLock,
  faUndo,
  faUserPlus,
  faUser,
  faPassport,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import { registerUser } from "../../services/index";

const Register = (props) => {
  const [show, setShow] = useState(false);
  const [message, setMessage] = useState("");
  const initialState = {
    userName: "",
    password: "",
    authorities: [
      {
        roleCode: "",
      },
    ],
    person: {
      email: "",
      firstName: "",
      secondName: "",
      phoneNumber: "",
    },
  };
  const [user, setUser] = useState(initialState);

  const userChange = (event) => {
    const { name, value } = event.target;
    if (
      event.target.name === "email" ||
      event.target.name === "phoneNumber" ||
      event.target.name === "firstName" ||
      event.target.name === "secondName"
    ) {
      let temp = { ...user };
      temp.person[event.target.name] = event.target.value;
      setUser({ ...user, temp });
    } else if (event.target.name === "roleCode") {
      let temp = { ...user };
      temp.authorities[0][event.target.name] = event.target.value;
      setUser({ ...user, temp });
    } else {
      setUser({ ...user, [name]: value });
    }
  };

  const dispatch = useDispatch();

  const saveUser = () => {
    dispatch(registerUser(user))
      .then((response) => {
        setShow(true);
        setMessage(response.message);
        resetRegisterForm();
        setTimeout(() => {
          setShow(false);
          props.history.push("/login");
        }, 2000);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const resetRegisterForm = () => {
    setUser(initialState);
  };

  return (
    <div>
      <div style={{ display: show ? "block" : "none" }}>
        <MyToast show={show} message={message} type={"success"} />
      </div>
      <Row className="justify-content-md-center">
        <Col xs={5}>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              <FontAwesomeIcon icon={faUserPlus} /> Регистрация
            </Card.Header>
            <Card.Body>
              <Form>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faUser} />
                    </InputGroup.Text>
                    <FormControl
                      autoComplete="off"
                      type="text"
                      name="userName"
                      value={user.userName}
                      onChange={userChange}
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
                      onChange={userChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите пароль"
                    />
                  </InputGroup>
                </Form.Group>
                <br></br>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faEnvelope} />
                    </InputGroup.Text>
                    <FormControl
                      required
                      autoComplete="off"
                      type="text"
                      name="email"
                      value={user.person.email}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите почтовый адрес"
                    />
                  </InputGroup>
                </Form.Group>

                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faPassport} />
                    </InputGroup.Text>
                    <FormControl
                      autoComplete="off"
                      type="text"
                      name="firstName"
                      value={user.person.firstName}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите имя"
                    />
                  </InputGroup>
                </Form.Group>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faPassport} />
                    </InputGroup.Text>
                    <FormControl
                      autoComplete="off"
                      type="text"
                      name="secondName"
                      value={user.person.secondName}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите фамилию"
                    />
                  </InputGroup>
                </Form.Group>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      <FontAwesomeIcon icon={faPhone} />
                    </InputGroup.Text>
                    <FormControl
                      autoComplete="off"
                      type="text"
                      name="phoneNumber"
                      value={user.person.phoneNumber}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                      placeholder="Введите номер телефона"
                    />
                  </InputGroup>
                </Form.Group>
                <br></br>
                <Form.Select
                  autoComplete="off"
                  name="roleCode"
                  value={user.authorities[0].roleCode}
                  onChange={userChange}
                  className={"bg-dark text-white"}
                >
                  <option value="USER">Роль в программной поддержке</option>
                  <option value="USER">Пользователь</option>
                  <option value="EXPERT">Эксперт</option>
                  <option value="LAWYER">Юрист</option>
                </Form.Select>
              </Form>
            </Card.Body>
            <Card.Footer style={{ textAlign: "right" }}>
              <Button
                size="sm"
                type="button"
                variant="success"
                onClick={saveUser}
                disabled={
                  user.userName.length === 0 || user.password.length === 0
                }
              >
                <FontAwesomeIcon icon={faUserPlus} /> Зарегистрироваться
              </Button>{" "}
              <Button
                size="sm"
                type="button"
                variant="info"
                onClick={resetRegisterForm}
              >
                <FontAwesomeIcon icon={faUndo} /> Сбросить
              </Button>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Register;
