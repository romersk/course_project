import React from "react";
import {
  Row,
  Col,
  Card,
  Form,
  InputGroup,
  FormControl,
  Button,
} from "react-bootstrap";
import { useState } from "react";

const Calculator = () => {
  const initialState = {
    name: "",
    count: 0,
    result: 0,
  };
  const [user, setUser] = useState(initialState);

  const userChange = (event) => {
    const { name, value } = event.target;
    if (name === "name") {
      setUser({ ...user, [name]: value });
    } else {
      setUser({ ...user, [name]: parseInt(value) });
    }
  };

  const check = () => {
    console.log(user);
    let result = parseInt(user.name) * (6 / parseInt(user.count));
    setUser({ ...user, ["result"]: Math.ceil(result * 100) / 100 });
  };

  return (
    <div>
      <Row className="justify-content-md-center">
        <Col xs={5}>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              Калькулятор стоимости проведения сертификации
            </Card.Header>
            <Card.Body>
              <Form>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>
                      К-во месяцев выполнения сертификации
                    </InputGroup.Text>
                    <FormControl
                      required
                      autoComplete="off"
                      type="number"
                      name="count"
                      value={user.count}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                    />
                  </InputGroup>
                </Form.Group>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>Название сертификации</InputGroup.Text>
                    <Form.Select
                      autoComplete="off"
                      name="name"
                      value={user.name}
                      onChange={userChange}
                      className={"bg-dark text-white"}
                    >
                      <option value="0">-</option>
                      <option value="2500">ISO-20001</option>
                      <option value="3000">ISO-9001</option>
                      <option value="2750">ISO-27001</option>
                      <option value="2800">ISO-45001</option>
                    </Form.Select>
                  </InputGroup>
                </Form.Group>
                <br></br>
                <Button
                  size="sm"
                  type="button"
                  variant="success"
                  onClick={check}
                >
                  Рассчитать
                </Button>
                <br></br>
                <br></br>
                <Form.Group as={Col}>
                  <InputGroup>
                    <InputGroup.Text>Сумма, BYN</InputGroup.Text>
                    <FormControl
                      autoComplete="off"
                      type="number"
                      name="result"
                      value={user.result}
                      className={"bg-dark text-white"}
                      disabled
                    />
                  </InputGroup>
                </Form.Group>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default Calculator;
