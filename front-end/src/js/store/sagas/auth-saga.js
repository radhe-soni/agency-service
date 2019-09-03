import { takeLatest, call, put } from "redux-saga/effects";
import { loginAPI } from "../../../APIs/loginAPI";
import { push } from 'react-router-redux';
// watcher saga: watches for actions dispatched to the store, starts worker saga
export function*  authSaga() {
  yield takeLatest("LOGIN_REQUEST", authWorkerSaga);
}


// worker saga: makes the api call when watcher saga sees the action
function* authWorkerSaga({ payload }) {
  try {
    const response = yield call(loginAPI(payload));
    // dispatch a success action to the store with the new message
    yield [put({ type: "LOGIN_SUCCESS", response }),push('/dashboard')];
  
  } catch (error) {
    // dispatch a failure action to the store with the error
    yield put({ type: "LOGIN_FAILURE", error });
  }
}
