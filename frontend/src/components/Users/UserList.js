import React from "react";
import { Component } from "react";
import axios from "axios";
import MyToast from "../MyToast";
import { Card } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faList } from "@fortawesome/free-solid-svg-icons";
import { InputGroup } from "react-bootstrap";
import { FormControl } from "react-bootstrap";
import { Button } from "react-bootstrap";
import { Table } from "react-bootstrap";
import { ButtonGroup } from "react-bootstrap";
import { Link } from "react-router-dom";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { faFastBackward } from "@fortawesome/free-solid-svg-icons";
import { faStepForward } from "@fortawesome/free-solid-svg-icons";
import { deleteUser, fetchUsers } from "../../services";
import { connect } from "react-redux";
import { faStepBackward } from "@fortawesome/free-solid-svg-icons";
import { faFastForward } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";

class UserList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      result: [],
      totalElements: 0,
      currentPage: 1,
      totalPages: 5,
    };
  }

  componentDidMount() {
    this.props.fetchUsers();
    this.findAllUsers(this.state.currentPage);
  }

  findAllUsers(currentPage) {
    currentPage -= 1;
    axios
      .get(
        "http://localhost:8081/api/v1/admin/users?pageNumber=" +
          currentPage +
          "&pageSize=" +
          this.state.totalPages
      )
      .then((response) => response.data)
      .then((data) => {
        this.setState({
          result: data.result,
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          currentPage: data.pageNumber + 1,
        });
      })
      .catch((error) => {
        console.log(error);
        localStorage.removeItem("jwtToken");
        return useNavigate("/");
      });
  }

  deleteUser = (userId) => {
    this.props.deleteUser(userId);
    setTimeout(() => {
      if (this.props.userObject != null) {
        this.setState({ show: true });
        setTimeout(() => this.setState({ show: false }), 3000);
        this.findAllUsers(this.state.currentPage);
      } else {
        this.setState({ show: false });
      }
    }, 1000);
  };

  changePage = (event) => {
    let targetPage = parseInt(event.target.value);
    this.findAllUsers(targetPage);
    this.setState({
      [event.target.name]: targetPage,
    });
  };

  firstPage = () => {
    let firstPage = 1;
    if (this.state.currentPage > firstPage) {
      this.findAllUsers(firstPage);
    }
  };

  prevPage = () => {
    let prevPage = 1;
    if (this.state.currentPage > prevPage) {
      this.findAllUsers(this.state.currentPage - prevPage);
    }
  };

  lastPage = () => {
    let condition = Math.ceil(this.state.totalElements / this.state.totalPages);
    if (this.state.currentPage < condition) {
      this.findAllUsers(condition);
    }
  };

  nextPage = () => {
    if (
      this.state.currentPage <
      Math.ceil(this.state.totalElements / this.state.totalPages)
    ) {
      this.findAllUsers(this.state.currentPage + 1);
    }
  };

  funcToSetId = (userId) => {
    this.props.userObject.user = userId;
  };

  render() {
    const { result, currentPage, totalPages } = this.state;
    return (
      <div>
        <div style={{ display: this.state.show ? "block" : "none" }}>
          <MyToast
            show={this.state.show}
            message={"Пользователь удален."}
            type={"danger"}
          />
        </div>
        <Card className={"border border-dark bg-dark text-white"}>
          <Card.Header>
            <div style={{ float: "left" }}>
              <FontAwesomeIcon icon={faList} /> Список пользователей
            </div>
          </Card.Header>
          <Card.Body>
            <Table bordered hover striped variant="dark">
              <thead>
                <tr>
                  <th>Никнейм</th>
                  <th>Почтовый адрес</th>
                  <th>Имя</th>
                  <th>Фамилия</th>
                  <th>Номер телефона</th>
                  <th>Роль</th>
                </tr>
              </thead>
              <tbody>
                {result?.length === 0 ? (
                  <tr align="center">
                    <td colSpan="7">Пользователи не найдены.</td>
                  </tr>
                ) : (
                  result?.map((user) => (
                    <tr key={user.id}>
                      <td>{user.userName}</td>
                      <td>{user.person.email}</td>
                      <td>{user.person.firstName}</td>
                      <td>{user.person.secondName}</td>
                      <td>{user.person.phoneNumber}</td>
                      <td>{user.authorities[0].roleCode}</td>
                      <td>
                        <ButtonGroup>
                          <Button onClick={() => this.funcToSetId(user.id)}>
                            <FontAwesomeIcon icon={faEdit} />
                          </Button>
                          <Link
                            to={"/admin/edit/" + user.id}
                            className="btn btn-sm btn-outline-primary"
                          >
                            <FontAwesomeIcon icon={faEdit} />
                          </Link>{" "}
                          <Button
                            size="sm"
                            variant="outline-danger"
                            onClick={() => this.deleteUser(user.id)}
                          >
                            <FontAwesomeIcon icon={faTrash} />
                          </Button>
                        </ButtonGroup>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </Table>
          </Card.Body>
          {result?.length > 0 ? (
            <Card.Footer>
              <div style={{ float: "left" }}>
                Текущая страница {currentPage} из {totalPages}
              </div>
              <div style={{ float: "right" }}>
                <InputGroup size="sm">
                  <InputGroup>
                    <Button
                      type="button"
                      variant="outline-info"
                      disabled={currentPage === 1 ? true : false}
                      onClick={this.firstPage}
                    >
                      <FontAwesomeIcon icon={faFastBackward} /> Первая
                    </Button>
                    <Button
                      type="button"
                      variant="outline-info"
                      disabled={currentPage === 1 ? true : false}
                      onClick={this.prevPage}
                    >
                      <FontAwesomeIcon icon={faStepBackward} />
                      Пред
                    </Button>
                  </InputGroup>
                  <FormControl
                    className={"page-num bg-dark"}
                    name="currentPage"
                    value={currentPage}
                    onChange={this.changePage}
                  />
                  <InputGroup>
                    <Button
                      type="button"
                      variant="outline-info"
                      disabled={currentPage === totalPages ? true : false}
                      onClick={this.nextPage}
                    >
                      <FontAwesomeIcon icon={faStepForward} /> След
                    </Button>
                    <Button
                      type="button"
                      variant="outline-info"
                      disabled={currentPage === totalPages ? true : false}
                      onClick={this.lastPage}
                    >
                      <FontAwesomeIcon icon={faFastForward} /> Последняя
                    </Button>
                  </InputGroup>
                </InputGroup>
              </div>
            </Card.Footer>
          ) : null}
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
    fetchUsers: () => dispatch(fetchUsers()),
    deleteUser: (userId) => dispatch(deleteUser(userId)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserList);
