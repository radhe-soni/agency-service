import LoginComponent from "./login-component";
import {connect} from 'react-redux';

// const mapStatetoProps = state => {};

// const mapDispatchtoProps = (dispatch) => ({
//     login: payload => { dispatch("LOGIN_REQUEST",payload);}
// });
const Login = connect(null, (dispatch) => ({
    login: (payload) => {
        console.log("payload is" , payload)
        return dispatch({type:"LOGIN_REQUEST",payload:payload});}
}))(LoginComponent)
export default Login ;