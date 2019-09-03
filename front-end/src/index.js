import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";

import { createStore, applyMiddleware, compose, combineReducers } from "redux";
import createSagaMiddleware from "redux-saga";
import { Provider } from "react-redux";

import rootSaga  from "./js/store/sagas/sagas";
import authReducer from "./js/store/reducers/auth-reducer";
import {register} from './serviceWorker';
import '../node_modules/bootstrap/scss/bootstrap.scss';
import registerReducer from "./js/store/reducers/register-reducer";
// create the saga middleware
const sagaMiddleware = createSagaMiddleware();

// dev tools middleware
const reduxDevTools =
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();

// create a redux store with our reducer above and middleware
const rootReducer = combineReducers(
    {auth: authReducer,
    register: registerReducer}
    ) 
let store = createStore(
  rootReducer,
  compose(applyMiddleware(sagaMiddleware), reduxDevTools)
);

// run the saga
sagaMiddleware.run(rootSaga);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
register();