import React, { Component } from "react";
import { updateProcess } from "../../services/process/processAction";
import MyToast from "../MyToast";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { saveAudit } from "../../services/audit/auditAction";
import { connect } from "react-redux";
import { fetchProcessByStageOne } from "../../services";

class AuditCreate extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
          processId: 0,
          plan: "",
          process: [],
          show: false,
        };
      }

      updateProcess = {
          status : "Проведение аудита"
      }

    initialState = {
        processId: 0,
        plan: "",
      };

      componentDidMount() {
        this.findAllAudit();
      }

      findAllAudit = () => {
        this.props.fetchAuditByStageOne();
        console.log(this.props.processObject);
        setTimeout(() => {
          let process = this.props.processObject.process;
          if (process) {
            this.setState({
              process: [{ value: 0, display: "Выберите процесс" }].concat(
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
            processId: parseInt(this.state.processId),
            plan: this.state.plan,
        };
        this.props.saveAudit(audit);
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
        const { processId, plan, process } = this.state;
        return (
          <div>
            <div style={{ display: this.state.show ? "block" : "none" }}>
              <MyToast
                show={this.state.show}
                message={
                  "Аудит успешно организован."
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
                      <Form.Label>План аудита</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="text"
                        name="plan"
                        value={plan}
                        onChange={this.auditChange}
                        className={"bg-dark text-white"}
                        placeholder="Введите план аудита"
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
                  processId === 0 || plan.length === 0
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
      saveAudit: (audit) => dispatch(saveAudit(audit)),
      updateProcess: (processId, process) => dispatch(updateProcess(processId, process)),
      fetchAuditByStageOne: () => dispatch(fetchProcessByStageOne()),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(AuditCreate);