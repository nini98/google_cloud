import React from "react";
import {
  Route,
  Switch,
} from 'react-router-dom';
import {HomePage,NotFoundPage,Blog_MainPage} from 'pages';
import LoginContainer from 'container/LoginContainer';
import RegisterContainer from 'container/RegisterContainer';
import * as authActions from "store/modules/auth";
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import { Storage } from 'api/storage';

class App extends React.Component {


  

  componentDidMount() {
    const { AuthActions } = this.props;    
    AuthActions.getUser();
    
    console.log(Storage.session.get("__AUTH__"));
  }

  render() {

    return (
      <div>
        
        {/*<Route path="/register" component={RegisterContainer} />*/}

        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route path="/blog/:id" component={Blog_MainPage} /> 
          <Route path="/login" component={LoginContainer} />
          <Route path="/signup" component={RegisterContainer} />
          <Route component={NotFoundPage} />
        </Switch>
      </div>
    )
  }
}

export default connect(
  state => ({   
  }),
  dispatch => ({
    AuthActions: bindActionCreators(authActions, dispatch)
  })
)(App);