import {all} from 'redux-saga/effects';
import { authSaga } from './auth-saga';
import { registerSaga } from './register-saga';

export default function* rootSaga() {
    yield all([
        authSaga(),
        registerSaga()
    ])
  }