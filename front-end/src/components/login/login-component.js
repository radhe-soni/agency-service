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
import {Link} from 'react-router-dom';
export default class LoginComponent extends React.Component {
  state = {
      userName:'',
      password:''
  }
  onChange = event =>{
      event.preventDefault();
      const name = event.target.name;
      const value = event.target.value;
      this.setState({
          [name]:value
      })
  }
  onSubmit =event=>{
      event.preventDefault();
      this.props.login({
          userName: this.state.userName,
          password: this.state.password,
      })
  }
  render() {
    return (
      <div className="container-fluid d-flex justify-content-center bg-light h-100">
      <Card className = "shadow mt-5 py-5 w-25">
      <h1 className = "text-center text-secondary ">Login</h1>
          <CardBody>
            <Form onSubmit = {this.onSubmit}>
              <FormGroup>
                <Label className="text-muted">UserName</Label>
                <Input
                  type="text"
                  name="userName"
                  id="userName"
                  onChange = {this.onChange}
                />
              </FormGroup>
              <FormGroup>
                <Label className="text-muted">Password</Label>
                <Input
                  type="password"
                  name="password"
                  id="password"
                  onChange = {this.onChange}

                />
              </FormGroup>
              <Button className = "mar-l-6" color = "primary" type = "submit">Submit</Button>
            </Form>
            <Link className = "text-center text-secondary mt-3" to = {'register'}>Register</Link>
          </CardBody>
        </Card>
      </div>
    );
  }
}
