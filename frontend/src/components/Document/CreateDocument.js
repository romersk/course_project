import React, { Component } from "react";
import { fetchProcessByStageFour, saveDocument } from "../../services";
import { connect } from "react-redux";
import MyToast from "../MyToast";
import { Card, Form, Button, Col, FormControl, Row } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faUndo,
} from "@fortawesome/free-solid-svg-icons";

class CreateDocument extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
        processId : 0,
        name: "",
        content: "",
        recommendations: "",
        process: [],
        show: false,
        };
      }

      initialState = {
        processId : 0,
        name: "",
        content: "",
        recommendations: "",
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
              process: [{ value: 0, display: "Выберите процесс", recommendations: "" }].concat(
                process.map((process) => {
                  return { value: process.id, display: process.name, recommendations: process.recommendations };
                })
              ),
            });
          }
        }, 100);
      };

      submitUser = (event) => {
        event.preventDefault();
        const document = {
            name: this.state.name,
            content: this.state.content,
            process : {
                id : parseInt(this.state.processId)
            },
        };
        this.props.saveDocument(document);
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
        if (event.target.name === "processId" ) {
          this.setState({
            ['recommendations']: this.state.process[event.target.value].recommendations,
          });
        }
        this.setState({
          [event.target.name]: event.target.value,
        });
      };
    
      resetAudit = () => {
        this.setState(() => this.initialState);
      };

      render() {
        const { processId, name, content, recommendations} = this.state;
        return (
          <div>
            <div style={{ display: this.state.show ? "block" : "none" }}>
              <MyToast
                show={this.state.show}
                message={
                  "Документ успешно оформлен."
                }
                type={"success"}
              />
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
              <Card.Header>
                Оформление документации
              </Card.Header>
              <Form
                onReset={this.resetAudit}
                onSubmit={this.submitUser}
                id="userFormId"
              >
                <Card.Body>
                <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Рекомендации по процессу</Form.Label>
                      <FormControl
                        autoComplete="off"
                        type="text"
                        name="recommendations"
                        value={recommendations}
                        onChange={this.auditChange}
                        disabled
                        className={"bg-dark text-white"}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Название документа</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="text"
                        name="name"
                        value={name}
                        onChange={this.auditChange}
                        className={"bg-dark text-white"}
                        placeholder="Введите название документа"
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} controlId="formGridUserName">
                      <Form.Label>Содержимое документа</Form.Label>
                      <FormControl
                        required
                        autoComplete="off"
                        type="text"
                        name="content"
                        value={content}
                        onChange={this.auditChange}
                        className={"bg-dark text-white"}
                        placeholder="Введите содержимое документа"
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
                  processId === 0 || name.length === 0 || content.length === 0
                }>
                    <FontAwesomeIcon icon={faSave} />{" "}
                    {"Оформить"}
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
      documentObject: state.document,
      processObject: state.process,
    };
  };
  
  const mapDispatchToProps = (dispatch) => {
    return {
      saveDocument: (document) => dispatch(saveDocument(document)),
      fetchAuditByStageOne: () => dispatch(fetchProcessByStageFour()),
    };
  };

  export default connect(mapStateToProps, mapDispatchToProps)(CreateDocument);