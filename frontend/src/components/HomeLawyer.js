import React from "react";
import { Alert, CardGroup, Row } from "react-bootstrap";
import { Card, Button } from "react-bootstrap";
import authToken from "../utils/authToken";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const HomeLawyer = () => {
    if (localStorage.jwtToken) {
      authToken(localStorage.jwtToken);
    }
  
    const navigate = useNavigate();
    const auth = useSelector((state) => state.auth);
  
    const clickButton = (event) => {
      if (event.target.name === "about") {
        return navigate("/about");
      }
      if (event.target.name === "create") {
        return navigate("/document/create");
      }
      if (event.target.name === "dogovor") {
        return navigate("/document/dogovor");
      }
      if (event.target.name === "process") {
        return navigate("/document/end");
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
            <i>Юристом</i>
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
              <Card.Title>Оформление документации</Card.Title>
              <Card.Text>
                Оформление документации для процесса сертификации, согласно аудиторским рекомендациям.
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="create"
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
              <Card.Title>Заключение договора на окозание услуг</Card.Title>
              <Card.Text>
                Оформление и заключение довогора между пользователем по окозанию услуг от программной поддержки
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="dogovor"
                onClick={clickButton}
              >
                Перейти
              </Button>
            </Card.Body>
            <Card.Body>
              <Card.Title>Завершить процесс оформления документов</Card.Title>
              <Card.Text>
                Завершение выбранного процесса
              </Card.Text>
              <Button
                variant="secondary"
                type="button"
                name="process"
                onClick={clickButton}
              >
                Перейти
              </Button>
            </Card.Body>
          </Card>
        </CardGroup>
      </div>
    );
  };
  
  export default HomeLawyer;