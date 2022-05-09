import React, { Component } from "react";
import { Card, Form, Col, FormControl, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";

class Personal extends Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    userName: "",
    person: {
      email: "",
      firstName: "",
      secondName: "",
      phoneNumber: "",
    },
    authorities: [
      {
        roleCode: "",
      },
    ],
  };

  componentDidMount() {
    this.getUser();
  }

  getUser() {
    axios
      .get("http://localhost:8081/api/v1/auth/userinfo")
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          userName: data.userName,
          person: {
            email: data.person.email,
            firstName: data.person.firstName,
            secondName: data.person.secondName,
            phoneNumber: data.person.phoneNumber,
          },
          authorities: [
            {
              roleCode: data.authorities[0].roleCode,
            },
          ],
        });
      })
      .catch((error) => {
        console.log(error);
        localStorage.removeItem("jwtToken");
        return useNavigate("/");
      });
  }

  userChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const { userName, person, authorities } = this.state;
    return (
      <div>
        <Card className={"border border-dark bg-dark text-white"}>
          <Form>
            <Card.Body>
              <Row>
                <Form.Group as={Col} controlId="formGridUserName">
                  <Form.Label>Никнейм</Form.Label>
                  <FormControl
                    required
                    autoComplete="off"
                    type="text"
                    name="userName"
                    value={userName}
                    onChange={this.userChange}
                    className={"bg-dark text-white"}
                    placeholder="Никнейм"
                    disabled
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Group as={Col} controlId="formGridEmail">
                  <Form.Label>Почтовый адрес</Form.Label>
                  <FormControl
                    required
                    autoComplete="off"
                    type="text"
                    name="email"
                    value={person.email}
                    onChange={this.userChange}
                    className={"bg-dark text-white"}
                    placeholder="Почтовый адрес"
                    disabled
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Group as={Col} controlId="formGridFirstName">
                  <Form.Label>Имя</Form.Label>
                  <FormControl
                    required
                    autoComplete="off"
                    type="text"
                    name="firstName"
                    value={person.firstName}
                    onChange={this.userChange}
                    className={"bg-dark text-white"}
                    placeholder="Имя"
                    disabled
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Group as={Col} controlId="formGridSecondName">
                  <Form.Label>Фамилия</Form.Label>
                  <FormControl
                    autoComplete="off"
                    type="text"
                    name="secondName"
                    value={person.secondName}
                    onChange={this.userChange}
                    className={"bg-dark text-white"}
                    placeholder="Фамилия"
                    disabled
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Group as={Col} controlId="formGridPhoneNumber">
                  <Form.Label>Номер телефона</Form.Label>
                  <FormControl
                    autoComplete="off"
                    type="text"
                    name="phoneNumber"
                    value={person.phoneNumber}
                    onChange={this.userChange}
                    className={"bg-dark text-white"}
                    placeholder="Номер телефона"
                    disabled
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Label>Роль</Form.Label>
                <FormControl
                  autoComplete="off"
                  name="roleCode"
                  value={authorities[0].roleCode}
                  onChange={this.userChange}
                  className={"bg-dark text-white"}
                  disabled
                ></FormControl>
              </Row>
            </Card.Body>
          </Form>
        </Card>
      </div>
    );
  }
}

export default Personal;
