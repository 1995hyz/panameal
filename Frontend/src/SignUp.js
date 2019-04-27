import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import React from 'react';
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import {withStyles} from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import {Field, Form, FormSpy} from 'react-final-form';
import Typography from './modules/components/Typography';
import AppFooter from './modules/views/AppFooter';
import AppAppBar from './modules/views/AppAppBar';
import AppForm from './modules/views/AppForm';
import {email, number, required} from './modules/form/validation';
import RFTextField from './modules/form/RFTextField';
import FormButton from './modules/form/FormButton';
import FormFeedback from './modules/form/FormFeedback';
import {Link as LinkRouter, Redirect} from 'react-router-dom';
import url from './modules/url';

const styles = theme => ({
  form: {
    marginTop: theme.spacing.unit * 6,
  },
  button: {
    marginTop: theme.spacing.unit * 3,
    marginBottom: theme.spacing.unit * 2,
  },
  feedback: {
    marginTop: theme.spacing.unit * 2,
  },
});


class SignUp extends React.Component {
  state = {
    sent: false,
    redirect: false,
  };

  validate = values => {
    const errors = required(['username', 'email', 'password', 'number'], values, this.props);

    if (!errors.email) {
      const emailError = email(values.email, values, this.props);
      if (emailError) {
        errors.email = email(values.email, values, this.props);
      }
    }
    if (!errors.number) {
      const numError = number(values.number, values, this.props);
      if (numError) {
        errors.number = number(values.number, values, this.props);
      }
    }
    return errors;
  };

  handleSubmit = values => {
    console.log("SUCCESSS")
    values.submitting = true;
    fetch(url + '/signup', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: values.email,
        passwordHash: values.password,
        username: values.username,
        phoneNumber: values.number,
        firstname: values.fname,
        lastname: values.lname,
      }),
    }).then(res => {
      console.log(res);
      if(res.status === 200)
        this.setState({redirect: true});
      else
        this.setState({redirect: false});
    });

    console.log(values.email)

  };

  render() {
    const { classes } = this.props;
    const { sent } = this.state;
    if (this.state.redirect)
      return <Redirect push to="/signin"/>;
    return (
        <React.Fragment>
          <AppAppBar />
          <AppForm>
            <React.Fragment>
              <Typography variant="h3" gutterBottom marked="center" align="center">
                Sign Up
              </Typography>
              <Typography variant="body2" align="center">
                <Link
                    underline="always"
                    component={LinkRouter}
                    to={"/signin"}
                >
                  Already have an account?
                </Link>
              </Typography>
            </React.Fragment>
            <Form
                onSubmit={this.handleSubmit}
                subscription={{ submitting: true }}
                validate={this.validate}
            >
              {({ handleSubmit, submitting }) => (
                  <form onSubmit={handleSubmit} className={classes.form} noValidate>
                    <Grid container spacing={16}>
                      <Grid item xs={12} sm={6}>
                        <Field
                            autoFocus
                            component={RFTextField}
                            autoComplete="fname"
                            fullWidth
                            label="First name"
                            name="firstName"
                        />
                      </Grid>
                      <Grid item xs={12} sm={6}>
                        <Field
                            component={RFTextField}
                            autoComplete="lname"
                            fullWidth
                            label="Last name"
                            name="lastName"
                        />
                      </Grid>
                    </Grid>
                    <Field
                        autoComplete="username"
                        component={RFTextField}
                        disabled={submitting || sent}
                        fullWidth
                        required
                        label="Username"
                        margin="normal"
                        name="username"
                    />
                    <Field
                        autoComplete="number"
                        component={RFTextField}
                        disabled={submitting || sent}
                        fullWidth
                        required
                        label="Number"
                        margin="normal"
                        name="number"
                    />
                    <Field
                        autoComplete="email"
                        component={RFTextField}
                        disabled={submitting || sent}
                        fullWidth
                        label="Email"
                        margin="normal"
                        name="email"
                        required
                    />
                    <Field
                        fullWidth
                        component={RFTextField}
                        disabled={submitting || sent}
                        required
                        name="password"
                        autoComplete="current-password"
                        label="Password"
                        type="password"
                        margin="normal"
                    />
                    <FormSpy subscription={{ submitError: true }}>
                      {({ submitError }) =>
                          submitError ? (
                              <FormFeedback className={classes.feedback} error>
                                {submitError}
                              </FormFeedback>
                          ) : null
                      }
                    </FormSpy>
                    <FormButton
                        className={classes.button}
                        disabled={submitting || sent}
                        color="secondary"
                        fullWidth
                    >
                      {submitting || sent ? 'In progress…' : 'Sign Up'}
                    </FormButton>
                  </form>
              )}
            </Form>
          </AppForm>
          <AppFooter />
        </React.Fragment>
    );
  }
}

SignUp.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default compose(
    withRoot,
    withStyles(styles),
)(SignUp);
