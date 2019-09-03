// import { AuthActions } from "../actions/auth-actions";
// action types
const LOGIN_REQUEST = "LOGIN_REQUEST";
const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGIN_FAILURE = "LOGIN_FAILURE";
const initialState = {
    articles: []
  };
const authReducer =(state = initialState, action)=> {
    switch (action.type) {
        case LOGIN_REQUEST:
          return { ...state, fetching: true, error: null };
        case LOGIN_SUCCESS:
          return { ...state, fetching: false, dog: action.dog };
        case LOGIN_FAILURE:
          return { ...state, fetching: false, dog: null, error: action.error };
        default:
          return state;
      }
  };
  export default authReducer;