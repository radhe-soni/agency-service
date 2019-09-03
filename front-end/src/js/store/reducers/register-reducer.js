// import { AuthActions } from "../actions/auth-actions";
// action types
const REGISTER_REQUEST = "REGISTER_REQUEST";
const REGISTER_SUCCESS = "REGISTER_SUCCESS";
const REGISTER_FAILURE = "REGISTER_FAILURE";
const initialState = {
    articles: []
  };
const registerReducer =(state = initialState, action)=> {
    switch (action.type) {
        case REGISTER_REQUEST:
          return { ...state, fetching: true, error: null };
        case REGISTER_SUCCESS:
          return { ...state, fetching: false, user: action.payload };
        case REGISTER_FAILURE:
          return { ...state, fetching: false, user: null, error: action.error };
        default:
          return state;
      }
  };
  export default registerReducer;