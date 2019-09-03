import {AppHeader} from "./header";
import {connect} from 'react-redux';
import { authSelector } from "../../js/store/selector/auth-selector";

const mapStateToProps = state =>({
    isAuthenticated:authSelector(state).isAuthenticated
})
const header = connect(
    mapStateToProps,
    null)(AppHeader)
export default header ;