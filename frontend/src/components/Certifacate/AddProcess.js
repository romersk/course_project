import React from "react";
import { Form, InputGroup, Card, Col, Button, Row } from "react-bootstrap";
import { useState } from "react";
import MyToast from "../MyToast";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUndo } from "@fortawesome/free-solid-svg-icons";
import { saveProcess } from "../../services/index";

const AddProcess = (props) => {
  const [show, setShow] = useState(false);
  const [message, setMessage] = useState("");
  const initialState = {
    startId: useSelector((state) => state.auth).id,
    user: {
      id: 3,
    },
    name: "",
  };
  const [user, setUser] = useState(initialState);

  const userChange = (event) => {
    const { name, value } = event.target;
    setUser({ ...user, [name]: value });
  };

  const dispatch = useDispatch();

  const saveUser = () => {
    dispatch(saveProcess(user));
    setShow({ ...show, show: true });
    setMessage({ ...message, message: "Процесс добавлен успешно" });
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
              <FontAwesomeIcon icon={faUserPlus} /> Добавления процесса
            </Card.Header>
            <Card.Body>
              <Form>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>Наименование сертификации</InputGroup.Text>
                    <Form.Select
                      autoComplete="off"
                      name="name"
                      value={user.name}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                    >
                      <option value="ISO-20001">ISO-20001</option>
                      <option value="ISO-9001">ISO-9001</option>
                      <option value="ISO-27001">ISO-27001</option>
                      <option value="ISO-45001">ISO-45001</option>
                    </Form.Select>
                  </InputGroup>
                </Form.Group>
              </Form>
            </Card.Body>
            <Card.Footer style={{ textAlign: "right" }}>
              <Button
                size="sm"
                type="button"
                variant="success"
                onClick={saveUser}
                disabled={user.name.length === 0}
              >
                <FontAwesomeIcon icon={faUserPlus} /> Зарегистировать заявку
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

export default AddProcess;
