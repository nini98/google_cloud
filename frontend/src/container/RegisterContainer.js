import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import { Redirect } from 'react-router-dom';
import * as authActions from "store/modules/auth";
import RegisterForm from 'components/register/RegisterForm';
import {Button, Dialog, DialogTitle, DialogActions} from '@material-ui/core';

class RegisterContainer extends Component {


  state = {
    open: false,
    isSuccess: false,
  }

  handleResiger = async (loginid,name, password,email) => {
    const { AuthActions } = this.props;
    const member = {
      "loginid":loginid,
      "name":name,
      "password":password,
      "email":email
    }

    try {
      await AuthActions.register(member);
      this.handleOpen();

      setTimeout(() => {
        this.setState({
          isSuccess: true,
        })
      }, 2000);
      
    } catch (e) {
      console.log("error log :" + e);

      this.handleOpen();
    }
  };
  
  handleOpen = () => {
    this.setState({open:true});
  }

  handleClose = () => {
    this.setState({open:false});
  };

  componentDidUpdate() {
  }

  render() {

    const { location } = this.props;
    const { from } = location.state || { from: { pathname: '/', search: location.search } };
    var {ResponseMessage} = this.props;

    if (this.state.isSuccess) {
      return <Redirect to={from} />;
    }



    return (
      <div>
        <Dialog
            open={this.state.open}
            onClose={this.handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
          >   
          <DialogTitle id="alert-dialog-title">
            {ResponseMessage}
          </DialogTitle>
          <DialogActions>
            <Button onClick={this.handleClose} color="primary" autoFocus>
              OK
            </Button>
          </DialogActions>
        </Dialog>
        <RegisterForm handleResiger={this.handleResiger.bind(this)} />
      </div>
    );
  }
}

export default connect(
  state => ({
    Registersuccess: state.pender.success["auth/REGISTER"],
    ResponseMessage: state.auth.get("ResponseMessage"),
  }),
  dispatch => ({
    AuthActions: bindActionCreators(authActions, dispatch)
  })
)(RegisterContainer);

