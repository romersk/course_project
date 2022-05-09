import React, { Component } from "react";
import {
  Card,
  Button,
  FormControl,
  ButtonGroup,
  InputGroup,
  Table,
} from "react-bootstrap";
import { connect } from "react-redux";
import { faStepBackward } from "@fortawesome/free-solid-svg-icons";
import { faFastForward } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import { faFastBackward } from "@fortawesome/free-solid-svg-icons";
import { faStepForward } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faList } from "@fortawesome/free-solid-svg-icons";
import { fetchProcess } from "../../services";

class ProcessList extends Component {
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
    this.props.fetchProcess();
    this.findAllProcess(this.state.currentPage);
  }

  findAllProcess(currentPage) {
    currentPage -= 1;
    axios
      .get(
        "http://localhost:8081/api/v1/process?pageNumber=" +
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
              <FontAwesomeIcon icon={faList} /> Список текущих процессов
            </div>
          </Card.Header>
          <Card.Body>
            <Table bordered hover striped variant="dark">
              <thead>
                <tr>
                  <th>Наименование</th>
                  <th>Статус</th>
                  <th>Текущие рекомендации</th>
                </tr>
              </thead>
              <tbody>
                {result?.length === 0 ? (
                  <tr align="center">
                    <td colSpan="7">Пользователи не найдены.</td>
                  </tr>
                ) : (
                  result?.map((process) => (
                    <tr key={process.id}>
                      <td>{process.name}</td>
                      <td>{process.status}</td>
                      <td>{process.recommendations}</td>
                      <td>
                        <ButtonGroup>
                          <Button onClick={() => this.funcToSetId(process.id)}>
                            <FontAwesomeIcon icon={faEdit} />
                          </Button>
                          <Link
                            to={"/process/" + process.id}
                            className="btn btn-sm btn-outline-primary"
                          >
                            <FontAwesomeIcon icon={faEdit} />
                          </Link>{" "}
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
                  <InputGroup>
                    <Button onClick={this.downloadFile}>
                      Графический отчет
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
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    fetchProcess: () => dispatch(fetchProcess()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(ProcessList);
