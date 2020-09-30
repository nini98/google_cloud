import React, { Component } from 'react';
import { connect } from 'react-redux';
import SideNav from 'components/home/Sidenav';
import { bindActionCreators } from "redux";
import * as authActions from "store/modules/auth";

class SidenavContainer extends Component {



  componentDidUpdate() {
  
  }

  

  handleLogout = async () => {
    const { AuthActions } = this.props;

    try {
      await AuthActions.logout();
    } catch (e) {
      console.log("error log :" + e);
    }
  }


  render() {

    const { isAuthenticated } = this.props;

    return ( 
        <SideNav isAuthenticated={isAuthenticated} onLogout={this.handleLogout}/>
    );
  }
}

export default connect(
  state => ({
    
    loading: state.pender.pending["auth/LOGOUT"],
    logOUtError: state.pender.failure["auth/LOGOUT"],
    isAuthenticated: state.auth.get("isAuthenticated"),
  }),
  dispatch => ({
    AuthActions: bindActionCreators(authActions, dispatch)
  })
)(SidenavContainer);

