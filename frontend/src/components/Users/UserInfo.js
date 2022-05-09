import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faPlusSquare,
  faUndo,
  faList,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import { saveUser, fetchUser, updateUser } from "../../services";
import { connect } from "react-redux";

class UserInfo extends Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    id: 0,
    userName: "",
    person: {
      id: 0,
      email: "",
      firstName: "",
      secondName: "",
      phoneNumber: "",
    },
    authorities: [
      {
        id: 0,
        roleCode: "",
      },
    ],
  };

  componentDidMount() {
    const userId = this.props.userObject.user;
    if (userId) {
      this.findUserById(userId);
    }
  }

  findUserById = (userId) => {
    this.props.fetchUser(userId);
    setTimeout(() => {
      let user = this.props.userObject.user;
      if (user != null) {
        this.setState({
          id: user.id,
          userName: user.userName,
          person: {
            id: user.person.id,
            email: user.person.email,
            firstName: user.person.firstName,
            secondName: user.person.secondName,
            phoneNumber: user.person.phoneNumber,
          },
          authorities: [
            {
              id: user.authorities[0].id,
              roleCode: user.authorities[0].roleCode,
            },
          ],
        });
      }
    }, 1000);
  };

  resetUser = () => {
    this.setState(() => this.initialState);
  };

  submitUser = (event) => {
    event.preventDefault();

    const user = {
      id: this.state.id,
      userName: this.state.userName,
      person: {
        id: this.state.person.id,
        email: this.state.person.email,
        firstName: this.state.person.firstName,
        secondName: this.state.person.secondName,
        phoneNumber: this.state.person.phoneNumber,
      },
      authorities: [
        {
          id: this.state.authorities[0].id,
          roleCode: this.state.authorities[0].roleCode,
        },
      ],
    };

    this.props.saveUser(user);
    setTimeout(() => {
      if (this.props.userObject.user != null) {
        this.setState({ show: true, method: "post" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  updateUser = (event) => {
    event.preventDefault();

    const user = {
      id: this.state.id,
      userName: this.state.userName,
      person: {
        id: this.state.person.id,
        email: this.state.person.email,
        firstName: this.state.person.firstName,
        secondName: this.state.person.secondName,
        phoneNumber: this.state.person.phoneNumber,
      },
      authorities: [
        {
          id: this.state.authorities[0].id,
          roleCode: this.state.authorities[0].roleCode,
        },
      ],
    };

    this.props.updateUser(user);
    setTimeout(() => {
      if (this.props.userObject.user != null) {
        this.setState({ show: true, method: "put" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  userChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  render() {
    const { userName, person, authorities } = this.state;
    return (
      <div>
        <div style={{ display: this.state.show ? "block" : "none" }}>
          <MyToast
            show={this.state.show}
            message={
              this.state.method === "put"
                ? "Пользователь изменён."
                : "Пользователь сохранён успешно."
            }
            type={"success"}
          />
        </div>
        <Card className={"border border-dark bg-dark text-white"}>
          <Card.Header>
            <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} />{" "}
            {this.state.id
              ? "Изменить пользователя"
              : "Добавить нового пользователя"}
          </Card.Header>
          <Form
            onReset={this.resetUser}
            onSubmit={this.state.id ? this.updateUser : this.submitUser}
            id="userFormId"
          >
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
                    placeholder="Введите никнейм"
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
                    placeholder="Введите почтовый адрес"
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
                    placeholder="Введите имя"
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
                    placeholder="Введите фамилию"
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
                    placeholder="Введите номер телефона"
                  />
                </Form.Group>
              </Row>
              <Row>
                <Form.Label>Роль</Form.Label>
                <Form.Select
                  autoComplete="off"
                  name="roleCode"
                  value={authorities[0].roleCode}
                  onChange={this.userChange}
                  className={"bg-dark text-white"}
                >
                  <option value="USER">Роль в программной поддержке</option>
                  <option value="USER">Пользователь</option>
                  <option value="EXPERT">Эксперт</option>
                  <option value="LAWYER">Юрист</option>
                </Form.Select>
              </Row>
            </Card.Body>
            <Card.Footer style={{ textAlign: "right" }}>
              <Button size="sm" variant="success" type="submit">
                <FontAwesomeIcon icon={faSave} />{" "}
                {this.state.id > 0 ? "Обновить" : "Сохранить"}
              </Button>{" "}
              <Button size="sm" variant="info" type="reset">
                <FontAwesomeIcon icon={faUndo} /> Сбросить
              </Button>{" "}
              <Button size="sm" variant="info" type="button">
                <Link to={"/admin/users"}>
                  <FontAwesomeIcon icon={faList} /> Список пользователей
                </Link>
              </Button>
            </Card.Footer>
          </Form>
        </Card>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    userObject: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    saveUser: (user) => dispatch(saveUser(user)),
    fetchUser: (userId) => dispatch(fetchUser(userId)),
    updateUser: (user) => dispatch(updateUser(user)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserInfo);
