import React from "react";
import { useSelector } from "react-redux";
import authToken from "../utils/authToken";
import { Alert, CardGroup } from "react-bootstrap";
import { Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const HomeAdmin = () => {
  if (localStorage.jwtToken) {
    authToken(localStorage.jwtToken);
  }

  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);

  const clickButton = (event) => {
    if (event.target.name === "list") {
      return navigate("/admin/users");
    }
  };

  return (
    <div>
      <Alert
        style={{ backgroundColor: "#343A40", color: "#ffffff80", fontSize: 18 }}
      >
        Добро пожаловать, {auth.username}! <br></br>
        <br></br> Вы являетесь{" "}
        <b>
          <i>Администратором</i>
        </b>{" "}
        данной программной поддержки. <br></br>Список возможностей представлен
        снизу.
      </Alert>
      <CardGroup>
        <Card
          style={{
            width: "18rem",
            backgroundColor: "#92A7BB",
          }}
        >
          <Card.Body>
            <Card.Title>Просмотр личной информации</Card.Title>
            <Card.Text>
              Возможность просмотра и редактирования персональной информации о
              пользователе.
            </Card.Text>
            <Button variant="secondary">Перейти</Button>
          </Card.Body>
        </Card>
        <Card
          style={{
            width: "18rem",
            backgroundColor: "#92A7BB",
          }}
        >
          <Card.Body>
            <Card.Title>Управление пользователями</Card.Title>
            <Card.Text>
              Просмотр, редактирование, удаление пользователей. Также создание
              текстового отчета
            </Card.Text>
            <Button
              variant="secondary"
              type="button"
              name="list"
              onClick={clickButton}
            >
              Перейти
            </Button>
          </Card.Body>
        </Card>
        <Card
          style={{
            width: "18rem",
            backgroundColor: "#92A7BB",
          }}
        >
          <Card.Body>
            <Card.Title>Администрирование текущих процессов</Card.Title>
            <Card.Text>
              Просмотр, редактирование, контроль процессов, зарегистрированных в
              программной поддержке.
            </Card.Text>
            <Button variant="secondary" href="#">
              Перейти
            </Button>
          </Card.Body>
        </Card>
      </CardGroup>
    </div>
  );
};

export default HomeAdmin;
