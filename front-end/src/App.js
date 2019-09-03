import React, { Component } from "react";
import { BrowserRouter as Router, Route } from 'react-router-dom'
import "./App.css";
import { HelloComponent } from "./components/hello-component";
import  AppHeader  from "./components/Header/";
import { DashboardComponent } from "./components/dashboard-component/dashboardComponent";
import ProductConfigComponent from "./components/product-config/productConfigComponent";
import LoginComponent from "./components/login/";
import RegisterComponent from "./components/register/";
import { ErrorComponent } from "./components/error/error-component";

class App extends Component {
  
  render() {
    console.log('react app')
    return (
      <div className="App">
      <Router>
      <React.Fragment>
      <AppHeader/>
        <Route exact path={"/"} component={LoginComponent} />
        <Route exact path={"/login"} component={LoginComponent} />
        <Route path="/hello" component={HelloComponent} />
        <Route path="/register" component={RegisterComponent} />
        <Route path="/dashboard" component={DashboardComponent} />
        <Route path="/supplier/product-config" component={ProductConfigComponent} />
        {/* <Route path='/*' component={ErrorComponent} />*/}
        </React.Fragment>
        </Router>
      </div>
    );
  }
}

export default App;
