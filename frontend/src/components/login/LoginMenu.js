import React, { Fragment } from 'react';
import { Button } from '@material-ui/core';

const loginMenuitem = (
    <>
        <Button variant="contained" color="primary" href="/login">
            SignIn
        </Button>
        <Button variant="contained" color="secondary" href="/signup">
            SignUp
        </Button>  
    </>
);

const accountMenuItem = (logout) => {
    return (
    <>
        <Button variant="contained" color="primary">
            MyPage
        </Button>
        <Button variant="contained" color="secondary" onClick={logout}>
            LogOut
        </Button>          
    </>
    )
};

const LoginMenu = ({isAuthenticated, onLogout}) => {

    return (
        <Fragment>
            {console.log(isAuthenticated)}
            {isAuthenticated ? accountMenuItem(onLogout) : loginMenuitem}
        </Fragment>

    );
};

export default LoginMenu;