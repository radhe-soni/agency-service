import { takeLatest, call, put } from "redux-saga/effects";
import { registerAPI } from "../../../APIs/registerAPI";

// watcher saga: watches for actions dispatched to the store, starts worker saga
export function*  registerSaga() {
  yield takeLatest("REGISTER_REQUEST", registerWorkerSaga);
}


// worker saga: makes the api call when watcher saga sees the action
function* registerWorkerSaga({ payload }) {
  try {
    const response = yield call(registerAPI(payload));
    // dispatch a success action to the store with the new message
    yield put({ type: "REGISTER_SUCCESS", response });
  
  } catch (error) {
    // dispatch a failure action to the store with the error
    yield put({ type: "REGISTER_FAILURE", error });
  }
}
