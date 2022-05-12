import React from "react";
import { useSelector } from "react-redux";
import authToken from "../utils/authToken";
import { Alert, CardGroup, Row } from "react-bootstrap";
import { Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const HomeUser = () => {
  if (localStorage.jwtToken) {
    authToken(localStorage.jwtToken);
  }

  const navigate = useNavigate();
  const auth = useSelector((state) => state.auth);

  const clickButton = (event) => {
    if (event.target.name === "about") {
      return navigate("/about");
    }
    if (event.target.name === "certificate") {
      return navigate("/certificate");
    }
    if (event.target.name === "contacts") {
      return navigate("/contacts");
    }
    if (event.target.name === "calculator") {
      return navigate("/calculator");
    }
    if (event.target.name === "addProcess") {
      return navigate("/addProcess");
    }
    if (event.target.name === "list") {
      return navigate("/user/list");
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
          <i>Пользователем</i>
        </b>{" "}
        данной программной поддержки. <br></br>Список возможностей представлен
        снизу.
      </Alert>
      <Row>
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
              <Button
                variant="secondary"
                type="button"
                name="about"
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
              <Card.Title>Ознакомиться с сертификатами</Card.Title>
              <Card.Text>
                Получить информацию об актуальных сертификатов, которые
                программная поддержка поддерживает на данный момент.
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="certificate"
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
              <Card.Title>Просмотр контактов</Card.Title>
              <Card.Text>
                Вся необходимая контактная информация для быстрой связи и при
                возникновении вопросов.
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="contacts"
                onClick={clickButton}
              >
                Перейти
              </Button>
            </Card.Body>
          </Card>
        </CardGroup>
      </Row>
      <Row>
        <CardGroup>
          <Card
            style={{
              width: "18rem",
              backgroundColor: "#92A7BB",
            }}
          >
            <Card.Body>
              <Card.Title>Онлайн-калькулятор</Card.Title>
              <Card.Text>
                Подсчет стоимости услуг исходя выбранной сертификации и степени
                срочности выполнения.
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="calculator"
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
              <Card.Title>Контроль процессов сертификации</Card.Title>
              <Card.Text>
                Контролирование состояния запрашиваемых процессов ранее
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
              <Card.Title>Заполнить заявку</Card.Title>
              <Card.Text>
                Заполнение заявки на предоставление услуг программной поддержки
                о проведение сертификации.
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="addProcess"
                onClick={clickButton}
              >
                Перейти
              </Button>
            </Card.Body>
          
          </Card>
        </CardGroup>
      </Row>
    </div>
  );
};

export default HomeUser;
