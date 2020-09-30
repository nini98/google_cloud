import React, { useState } from 'react';
import {Avatar, Button, CssBaseline, TextField, Link, Grid, Typography,Container, Dialog, DialogTitle, DialogActions} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';

const useStyles = makeStyles((theme) => ({
    paper: {
      marginTop: theme.spacing(8),
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
    },
    avatar: {
      margin: theme.spacing(1),
      backgroundColor: theme.palette.secondary.main,
    },
    form: {
      width: '100%', // Fix IE 11 issue.
      marginTop: theme.spacing(1),
    },
    submit: {
      margin: theme.spacing(3, 0, 2),
    },
  }));
  


  export default function RegisterForm(props) {
    const classes = useStyles();

    const handleSubmit = e => {
      const {handleResiger} = props;
      e.preventDefault();
      const {LoginID,name,password,email} = e.target;

      if(matchingpassword === true)
      {
        setText("비밀번호가 서로 일치하지 않음.");
        handleClickOpen();
      }
      else
      {
        handleResiger(
          LoginID.value, 
          name.value,
          password.value,
          email.value
        );
      }
    };

    const [text, setText] = useState('');
    const [open, setOpen] = useState(false);
    const handleClickOpen = () => {
      setOpen(true);
    };
  
    const handleClose = () => {
      setOpen(false);
    };


    const [tpassword, setPassword] = useState('');
    const [matchingpassword,setboolean] = useState(false);

    const handleOnPassword = (e) => {
      setPassword(e.target.value);
    };

    
    const handleOnCheckPassword = (e) => {
      confirmPassword(e.target.value);
    };

    const confirmPassword = (checkpassword) => {

      if(tpassword === checkpassword) {
        setboolean(false);
      }
      else{
        setboolean(true);
      }
    };

    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
          >   
          <DialogTitle id="alert-dialog-title">
            {text}
          </DialogTitle>
          <DialogActions>
            <Button onClick={handleClose} color="primary" autoFocus>
              OK
            </Button>
          </DialogActions>
        </Dialog>
        <div className={classes.paper}>
          <Avatar className={classes.avatar}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign Up
          </Typography>
          <form className={classes.form} onSubmit={handleSubmit} >
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="LoginID"
              label="Login ID"
              name="LoginID"
              autoComplete="LoginID"
              autoFocus
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="name"
              label="name"
              type="name"
              id="name"
              autoComplete="name"
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="email"
              label="email"
              type="email"
              id="email"
              autoComplete="email"
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange={handleOnPassword}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="checkpassword"
              label="checkpassword"
              type="password"
              id="checkpassword"
              autoComplete="checkpassword"
              onChange={handleOnCheckPassword}
              error={matchingpassword}
              helperText={matchingpassword?"비밀번호가 틀림":""}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
            >
              Sign Up
            </Button>
            <Grid container>
              <Grid item>
                <Link href="/login" variant="body2">
                  {"Do you have an account? Sign In"}
                </Link>
              </Grid>
            </Grid>
          </form>
        </div>
      </Container>
    );
  }