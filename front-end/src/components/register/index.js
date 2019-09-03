import RegisterComponent from "./register-component";
import {connect} from 'react-redux';

// const mapStatetoProps = state => {};

// const mapDispatchtoProps = (dispatch) => ({
//     register: payload => { dispatch("REGISTER_REQUEST",payload);}
// });
const Register = connect(null, (dispatch) => ({
    register: (payload) => {
        return dispatch({type:"REGISTER_REQUEST",payload:payload});}
}))(RegisterComponent)
export default Register ;