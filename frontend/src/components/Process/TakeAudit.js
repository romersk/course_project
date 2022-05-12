import React, { Component } from "react";
import { fetchAuditByProcess, updateAudit } from "../../services/audit/auditAction";
import { updateProcess } from "../../services/process/processAction";
import MyToast from "../MyToast";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { connect } from "react-redux";
import { fetchProcessByStageTwo } from "../../services";

class TakeAudit extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
        processId: 0,
          auditId: 0,
          dateStart: "2022-05-08",
          dateEnd: "2022-05-08",
          process: [],
          show: false,
        };
      }

      updateProcess = {
          status : "Заключение аудита"
      }

    initialState = {
        processId: 0,
        auditId: 0,
        dateStart: "2022-05-08",
        dateEnd: "2022-05-08",
      };

      componentDidMount() {
        this.findAllAudit();
      }

      findAllAudit = () => {
        this.props.fetchAuditByStageTwo();
        setTimeout(() => {
          let process = this.props.processObject.process;
          if (process) {
            this.setState({
              process: [{ value: 0, display: "Выберите аудит проводимого процесса" }].concat(
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
        const audit = {
            dateStart: this.state.dateStart,
            dateEnd: this.state.dateEnd,
        };
        this.props.fetchAuditByProcess(parseInt(this.state.processId));
        let idAudit = this.props.auditObject.audit.id;
        this.props.updateAudit(idAudit, audit);
        this.props.updateProcess(parseInt(this.state.processId), this.updateProcess);
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
        const { processId, dateStart, dateEnd } = this.state;
        return (
          <div>
            <div style={{ display: this.state.show ? "block" : "none" }}>
              <MyToast
                show={this.state.show}
                message={
                  "Аудит успешно зарегистирован и проведет."
                }
                type={"success"}
              />
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
              <Card.Header>
                Организация аудита
              </Card.Header>
              <Form
                onReset={this.resetAudit}
                onSubmit={this.submitUser}
                id="userFormId"
              >
                <Card.Body>
                  <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Дата начало аудита</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="date"
                        min="2022-05-08" max="2030-12-31"
                        name="dateStart"
                        value={dateStart}
                        onChange={this.auditChange}
                        className={"bg-dark text-white"}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Дата завершения аудита</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="date"
                        min="2022-05-08" max="2030-12-31"
                        name="dateEnd"
                        value={dateEnd}
                        onChange={this.auditChange}
                        className={"bg-dark text-white"}
                      />
                    </Form.Group>
                  </Row>
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
                    {"Сохранить"}
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
      auditObject: state.audit,
      processObject: state.process,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      updateAudit: (auditId, audit) => dispatch(updateAudit(auditId, audit)),
      updateProcess: (processId, process) => dispatch(updateProcess(processId, process)),
      fetchAuditByStageTwo: () => dispatch(fetchProcessByStageTwo()),
      fetchAuditByProcess: (processId) => dispatch(fetchAuditByProcess(processId)),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(TakeAudit);