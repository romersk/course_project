import React, { Component } from "react";
import { fetchProcessByStageThree } from "../../services";
import MyToast from "../MyToast";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";
import { connect } from "react-redux";
import { updateProcess } from "../../services";

class EndAudit extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
          processId: 0,
          recommendations: "",
          
          process: [],
          show: false,
        };
      }

    initialState = {
        processId: 0,
        recommendations: "",
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
        const process = {
            recommendations: this.state.recommendations,
            status : "Оформление документов"
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
        const { processId, recommendations } = this.state;
        return (
          <div>
            <div style={{ display: this.state.show ? "block" : "none" }}>
              <MyToast
                show={this.state.show}
                message={
                  "Процесс завершен на этапе эксперта."
                }
                type={"success"}
              />
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
              <Card.Header>
                Создание и оформление заключений
              </Card.Header>
              <Form
                onReset={this.resetAudit}
                onSubmit={this.submitUser}
                id="userFormId"
              >
                <Card.Body>
                  <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Рекомендации</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="text"
                        name="recommendations"
                        value={recommendations}
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
                  processId === 0 || recommendations.length === 0
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
      processObject: state.process,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      updateProcess: (processId, process) => dispatch(updateProcess(processId, process)),
      fetchAuditByStageThree: () => dispatch(fetchProcessByStageThree()),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(EndAudit);