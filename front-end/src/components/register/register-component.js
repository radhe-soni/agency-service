import React from "react";
import {
  Form,
  Card,
  FormGroup,
  Label,
  Input,
  CardBody,
  Button
} from "reactstrap";
import { Link } from "react-router-dom";
import { FormErrors } from "../form-errors/form-error";
export default class RegisterComponent extends React.Component {
  state = {
    userName: "",
    password: "",
    confirm_password: "",
    formErrors: { userName: "", password: "" },
    userNameValid: false,
    passwordValid: false,
    formValid: true,
  };
  onChange = event => {
    event.preventDefault();
    const name = event.target.name;
    const value = event.target.value;
    this.setState(
      {
        [name]: value
      },
      () => {
        this.validateField(name, value);
      }
    );
  };
  onSubmit = event => {
    event.preventDefault();
    if (this.state.password !== this.state.confirm_password) {
      console.log("password do not match")
      this.setState({ errorMsg: "Passwords do not match" })
    }
    else {
      this.props.register({
        userName: this.state.userName,
        password: this.state.password
      });
    }
  };
  validateField = (fieldName, value) => {
    let fieldValidationErrors = this.state.formErrors;
    let userNameValid = this.state.userNameValid;
    let passwordValid = this.state.passwordValid;

    switch (fieldName) {
      case "userName":
        userNameValid = value ? true : false;
        fieldValidationErrors.userName = userNameValid ? "" : " is invalid";
        break;
      case "password":
        passwordValid = value.length >= 6;
        fieldValidationErrors.password = passwordValid ? "" : " is too short";
        break;
      default:
        break;
    }
    this.setState(
      {
        formErrors: fieldValidationErrors,
        userNameValid: userNameValid,
        passwordValid: passwordValid
      },
      this.validateForm
    );
  };

  validateForm = () => {
    this.setState({
      formValid: this.state.userNameValid && this.state.passwordValid
    });
  }
  render() {
    return (
      <div className="container-fluid d-flex justify-content-center bg-light h-100">
        <Card className="shadow mt-5 py-5 w-25">
          <h1 className="text-center text-secondary ">Register</h1>
          <CardBody>
            <Form onSubmit={this.onSubmit}>
              {this.state.errorMsg && <div className="alert alert-danger">
                {this.state.errorMsg}
                <FormErrors formErrors={this.state.formErrors} />
              </div>}
              <FormGroup tag="fieldset">
                <legend>You are a -</legend>
              <FormGroup>
                <Label >Buyer
                <Input
                    type="radio"
                    name="role"
                    id="role_buyer"
                    value="Buyer"
                    defaultChecked
                    onChange={this.onChange}
                  /></Label>
              </FormGroup>
              <FormGroup>
                <Label >Supplier
                <Input
                    type="radio"
                    name="role"
                    id="role_supplier"
                    value="Supplier"
                    onChange={this.onChange}
                  /></Label>
              </FormGroup>
              </FormGroup>
              <FormGroup>
                <Label className="text-muted">UserName</Label>
                <Input
                  type="text"
                  name="userName"
                  id="userName"
                  onChange={this.onChange}
                />
              </FormGroup>
              <FormGroup>
                <Label className="text-muted">Password</Label>
                <Input
                  type="password"
                  name="password"
                  id="password"
                  onChange={this.onChange}
                />
              </FormGroup>
              <FormGroup>
                <Label className="text-muted">Confirm Password</Label>
                <Input
                  type="password"
                  name="confirm_password"
                  id="confirm_password"
                  onChange={this.onChange}
                />
              </FormGroup>
              <FormGroup>
                <Button className="mar-l-6" color="primary" type="submit" disabled={!this.state.formValid}>
                  Submit
                </Button>
              </FormGroup>
            </Form>
            <Link className="text-secondary mt-3" to={"login"}>
              Already registered? Click here to login!
            </Link>
          </CardBody>
        </Card>
      </div>
    );
  }
}
