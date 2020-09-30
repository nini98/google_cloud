import { createAction, handleActions } from 'redux-actions';

import { Map, fromJS } from 'immutable';
import { pender } from 'redux-pender';

import * as api from 'api/api';
import { Storage } from 'api/storage';

//action types
const LOGIN = 'auth/LOGIN'
//const SOCIAL_LOGIN = 'auth/SOCIAL_LOGIN'
const LOGOUT = 'auth/LOGOUT'
const GET_USER = 'auth/GET_USER';
const REGISTER = 'auth/REGISTER';

export const login = createAction(LOGIN, api.login);
//export const socialLogin = createAction(SOCIAL_LOGIN);
export const logout = createAction(LOGOUT, api.logout);
export const getUser = createAction(GET_USER, api.getMember);
export const register = createAction(REGISTER, api.register);

const initialState = Map({
  isAuthenticated: false,
  loginSuccess: false,
  loginError: false,
  ResponseMessage: '',
  currentUser: Map({})
});

export default handleActions({
  [LOGOUT]: (state, action) => {
    console.log("LOGOUT action")

    if (Storage.local.get("__AUTH__")) {
      Storage.local.remove("__AUTH__");
    }
    if (Storage.session.get("__AUTH__")) {
      Storage.session.remove("__AUTH__");
    }

    return state.set('isAuthenticated', false)
      .set('loginSuccess', false);
  },
  /*[SOCIAL_LOGIN]: (state, action) => {
    console.log("SOCIAL LOGIN onSuccess")
    const rememberMe = false;
    if (rememberMe) {
      Storage.local.set("__AUTH__", action.payload);
    } else {
      Storage.session.set("__AUTH__", action.payload);
    }
    return state.set('isAuthenticated', true);
  },*/
  ...pender({
    type: LOGIN,
    onSuccess: (state, action) => {
      console.log("LOGIN onSuccess")
      const { headers: content } = action.payload;
      const bearerToken = content.authorization;
      if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
        const jwt = bearerToken.slice(7, bearerToken.length);
        const rememberMe = false;
        if (rememberMe) {
          Storage.local.set("__AUTH__", jwt);
        } else {
          Storage.session.set("__AUTH__", jwt);
        }
      }
      return state.set('isAuthenticated', true);
    },
    onFailure: (state, action) => {
      console.log("LOGIN onFailure")
      return state.set('isAuthenticated', false);
    }
  }),
  ...pender({
    type: LOGOUT,
    onSuccess: (state, action) => {
      console.log("LOGOUT action")
  
      if (Storage.local.get("__AUTH__")) {
        Storage.local.remove("__AUTH__");
      }
      if (Storage.session.get("__AUTH__")) {
        Storage.session.remove("__AUTH__");
      }
  
      return state.set('isAuthenticated', false)
        .set('loginSuccess', false);
    },
    onFailure: (state, action) => {
      console.log("incorrect LOGOUT action")
  
      if (Storage.local.get("__AUTH__")) {
        Storage.local.remove("__AUTH__");
      }
      if (Storage.session.get("__AUTH__")) {
        Storage.session.remove("__AUTH__");
      }
  
      return state.set('isAuthenticated', false)
        .set('loginSuccess', false);
    },
  }),
  ...pender({
    type: REGISTER,
    onSuccess: (state, action) => {
      console.log("REGISTER onSuccess")

      return state.set('ResponseMessage',"회원 가입이 완료되었습니다.");;
    },
    onFailure: (state, action) => {
      console.log("REGISTER onFailure")
      
      const content = action.payload.response;

      if(content.status === 500)
      {
        return state.set('ResponseMessage',"서버 에러");
      }

      return state.set('ResponseMessage',content.data+"가 이미 사용중입니다.");
    }
  }),
  ...pender({
    type: GET_USER,
    onSuccess: (state, action) => {
      const { data: content } = action.payload;
      return state.set('isAuthenticated', true)
                  .set('currentUser', fromJS(content));
    },
    onFailure: (state, action) => {
      console.log("GET_USER onFailure")
      return state.set('isAuthenticated', false);
    }
  })
}, initialState);