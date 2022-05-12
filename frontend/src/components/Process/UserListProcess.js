import React, { Component } from "react";
import { connect } from "react-redux";
import { fetchProcessByIdUser } from "../../services";
import {
    Card,
    Button,
    FormControl,
    ButtonGroup,
    InputGroup,
    Table,
  } from "react-bootstrap";
  import axios from "axios";
  import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
  import { useNavigate } from "react-router-dom";
  import { faFastBackward, faList, faStepBackward, faStepForward, faFastForward } from "@fortawesome/free-solid-svg-icons";

class UserListProcess extends Component {
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
        console.log(this.props.authObject);
        this.props.fetchProcess(this.props.authObject.id);
        this.findAllProcess(this.state.currentPage);
      }

      findAllProcess(currentPage) {
        currentPage -= 1;
        axios
          .get(
            "http://localhost:8081/api/v1/process/userId/" + this.props.authObject.id + "?pageNumber=" +
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
    
      changePage = (event) => {
        let targetPage = parseInt(event.target.value);
        this.findAllProcess(targetPage);
        this.setState({
          [event.target.name]: targetPage,
        });
      };
    
      firstPage = () => {
        let firstPage = 1;
        if (this.state.currentPage > firstPage) {
          this.findAllProcess(firstPage);
        }
      };
    
      prevPage = () => {
        let prevPage = 1;
        if (this.state.currentPage > prevPage) {
          this.findAllProcess(this.state.currentPage - prevPage);
        }
      };
    
      lastPage = () => {
        let condition = Math.ceil(this.state.totalElements / this.state.totalPages);
        if (this.state.currentPage < condition) {
          this.findAllProcess(condition);
        }
      };
    
      nextPage = () => {
        if (
          this.state.currentPage <
          Math.ceil(this.state.totalElements / this.state.totalPages)
        ) {
          this.findAllProcess(this.state.currentPage + 1);
        }
      };

      render() {
        const { result, currentPage, totalPages } = this.state;
        return (
          <div>
            <Card className={"border border-dark bg-dark text-white"}>
              <Card.Header>
                <div style={{ float: "left" }}>
                  <FontAwesomeIcon icon={faList} /> Список текущих процессов пользователя
                </div>
              </Card.Header>
              <Card.Body>
                <Table bordered hover striped variant="dark">
                  <thead>
                    <tr>
                      <th>Наименование</th>
                      <th>Статус</th>
                    </tr>
                  </thead>
                  <tbody>
                    {result?.length === 0 ? (
                      <tr align="center">
                        <td colSpan="7">Процессы не найдены.</td>
                      </tr>
                    ) : (
                      result?.map((process) => (
                        <tr key={process.id}>
                          <td>{process.name}</td>
                          <td>{process.status}</td>
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
      processObject: state.process,
      authObject: state.auth,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      fetchProcess: (userId) => dispatch(fetchProcessByIdUser(userId)),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(UserListProcess);