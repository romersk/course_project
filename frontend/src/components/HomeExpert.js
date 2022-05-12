import React from "react";
import { useSelector } from "react-redux";
import authToken from "../utils/authToken";
import { Alert, CardGroup, Row } from "react-bootstrap";
import { Card, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const HomeExpert = () => {
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
            return navigate("/audit/create");
        }
        if (event.target.name === "take") {
            return navigate("/audit/take");
        }
        if (event.target.name === "end") {
            return navigate("/audit/end");
        }
        if (event.target.name === "list") {
            return navigate("/audit/list");
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
                    <i>Экспертом</i>
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
                            <Card.Title>Составить программу аудита</Card.Title>
                            <Card.Text>
                                Разработка плана программы аудита, назначение дат вывполнения.
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
                            <Card.Title>Провести аудит</Card.Title>
                            <Card.Text>
                                Процесс сбора информации во время аудита, обновление статуса процесса.
                            </Card.Text>
                            <Button
                                variant="secondary"
                                type="button"
                                name="take"
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
                            <Card.Title>Создание заключений аудита</Card.Title>
                            <Card.Text>
                                Подвести итоги аудита, записать необходимые рекомендации для оформления документации.
                            </Card.Text>
                            <Button
                                variant="secondary"
                                type="button"
                                name="end"
                                onClick={clickButton}
                            >
                                Перейти
                            </Button>
                        </Card.Body>

                        <Card.Body>
                            <Card.Title>Администрирование аудитов</Card.Title>
                            <Card.Text>
                                Просмотр и проверка корректности проводимых аудитов.
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
                </CardGroup>
            </Row>
        </div>
    );
}

export default HomeExpert;