import React, { Component } from "react";
import { connect } from "react-redux";
import { fetchProcessByStageFour, updateProcess } from "../../services";
import MyToast from "../MyToast";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";

class EndProcess extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
          processId: 0,
          process: [],
          show: false,
        };
      }

      initialState = {
        processId: 0,
      };

      componentDidMount() {
        this.findAllAudit();
      }

      findAllAudit = () => {
        this.props.fetchAuditByStageThree();
        setTimeout(() => {
          let process = this.props.processObject.process;
          if (process) {
            this.setState({
              process: [{ value: 0, display: "Выберите процесс для завершения" }].concat(
                process.map((process) => {
                  return { value: process.id, display: process.name };
                })
              ),
            });
          }
        }, 100);
      };

      submitUser = (event) => {
        event.preventDefault();
        const process = {
            status : "Завершен"
        };
        
        this.props.updateProcess(parseInt(this.state.processId), process);
        this.setState({ show: true, method: "post" });
        setTimeout(() => {
            if (this.props.auditObject != null) {
              this.setState({ show: true, method: "post" });
              setTimeout(() => this.setState({ show: false }), 3000);
            } else {
              this.setState({ show: false });
            }
          }, 2000);
          this.setState(this.initialState);
      }

      auditChange = (event) => {
        this.setState({
          [event.target.name]: event.target.value,
        });
      };

      resetAudit = () => {
        this.setState(() => this.initialState);
      };

      render() {
        const { processId} = this.state;
        return (
          <div>
            <div style={{ display: this.state.show ? "block" : "none" }}>
              <MyToast
                show={this.state.show}
                message={
                  "Процесс завершен полностью."
                }
                type={"success"}
              />
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
              <Card.Header>
                Завершение процесса
              </Card.Header>
              <Form
                onReset={this.resetAudit}
                onSubmit={this.submitUser}
                id="userFormId"
              >
                <Card.Body>
                  <Row>
                  <Form.Group as={Col} controlId="formGridLanguage">
                  <Form.Label>Процесс сертификации</Form.Label>
                  <Form.Control
                    required
                    as="select"
                    onChange={this.auditChange}
                    name="processId"
                    value={processId}
                    className={"bg-dark text-white"}
                  >
                    {this.state.process.map((process) => (
                      <option key={process.value} value={process.value}>
                        {process.display}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
                  </Row>
                </Card.Body>
                <Card.Footer style={{ textAlign: "right" }}>
                  <Button size="sm" variant="success" type="submit" disabled={
                  processId === 0
                }>
                    <FontAwesomeIcon icon={faSave} />{" "}
                    {"Завершить"}
                  </Button>{" "}
                  <Button size="sm" variant="info" type="reset">
                    <FontAwesomeIcon icon={faUndo} /> Сбросить
                  </Button>{" "}
                </Card.Footer>
              </Form>
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
      updateProcess: (processId, process) => dispatch(updateProcess(processId, process)),
      fetchAuditByStageThree: () => dispatch(fetchProcessByStageFour()),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(EndProcess);