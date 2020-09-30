import React from 'react';
import { BrowserRouter,Route } from 'react-router-dom';
import App from 'components/App';
import { Provider } from 'react-redux';
import config from 'store/config'

const store = config();

const Root = () => {
  return (
    <Provider store={store} >
      <BrowserRouter>
        <Route path="/" component={App} />
      </BrowserRouter>
    </Provider>
  );
}

export default Root;