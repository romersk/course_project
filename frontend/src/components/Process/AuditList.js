import React from "react";
import { connect } from "react-redux";
import { fetchAudit } from "../../services/audit/auditAction";
import {
    Card,
    Button,
    FormControl,
    ButtonGroup,
    InputGroup,
    Table,
  } from "react-bootstrap";
  import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
  import axios from "axios";
  import { useNavigate } from "react-router-dom";
  import { faList, faFastForward, faStepForward, faStepBackward, faFastBackward } from "@fortawesome/free-solid-svg-icons";
  import { Component } from "react";

class AuditList extends Component {
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
            "http://localhost:8081/api/v1/audit?pageNumber=" +
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
                  <FontAwesomeIcon icon={faList} /> Список текущих аудитов
                </div>
              </Card.Header>
              <Card.Body>
                <Table bordered hover striped variant="dark">
                  <thead>
                    <tr>
                      <th>Id процесса</th>
                      <th>Дата начала</th>
                      <th>Дата конца</th>
                      <th>План аудита</th>
                    </tr>
                  </thead>
                  <tbody>
                    {result?.length === 0 ? (
                      <tr align="center">
                        <td colSpan="7">Аудиты не найдены.</td>
                      </tr>
                    ) : (
                      result?.map((process) => (
                        <tr key={process.id}>
                          <td>{process.processId}</td>
                          <td>{process.dateStart}</td>
                          <td>{process.dateEnd}</td>
                          <td>{process.plan}</td>
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
      auditObject: state.audit,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      fetchProcess: () => dispatch(fetchAudit()),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(AuditList);