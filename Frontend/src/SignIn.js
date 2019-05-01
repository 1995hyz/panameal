import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import React from 'react';
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import {withStyles} from '@material-ui/core/styles';
import Link from '@material-ui/core/Link';
import {Field, Form, FormSpy} from 'react-final-form';
import Typography from './modules/components/Typography';
import AppFooter from './modules/views/AppFooter';
import AppAppBar from './modules/views/AppAppBar';
import AppForm from './modules/views/AppForm';
import {email, required} from './modules/form/validation';
import RFTextField from './modules/form/RFTextField';
import FormButton from './modules/form/FormButton';
import FormFeedback from './modules/form/FormFeedback';
import {Link as LinkRouter, Redirect} from 'react-router-dom';
import url from './modules/url';

const fakeAuth = {
    isAuthenticated: false,
    authenticate(cb) {
        this.isAuthenticated = true;
        setTimeout(cb, 100); // fake async
    },
    signout(cb) {
        this.isAuthenticated = false;
        setTimeout(cb, 100);
    }
};

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
    text: {
        color: theme.palette.warning.main,
    },
});

class SignIn extends React.Component {
    state = {
        sent: false,
        redirect: false,
        incorrect: false,
        validUser: "",
    };

    validate = values => {
        const errors = required(['email', 'password'], values, this.props);

        if (!errors.email) {
            const emailError = email(values.email, values, this.props);
            if (emailError) {
                errors.email = email(values.email, values, this.props);
            }
        }
        return errors;
    };

    handleSubmit = values => {
        values.submitting = true;
        fetch(url + '/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: values.email,
                passwordHash: values.password,
            }),
        })
        .then(res => {
            this.setState({validUser: res.status === 200})
            if (this.state.validUser) {
                fakeAuth.authenticate(() => {
                    this.setState({redirect: true, incorrect: false});
                });
                localStorage.setItem('authToken', values.email);
                return res.json();
            }
            else
                this.setState({redirect: false, incorrect: true});
        })
        .then(myJSON => {
            localStorage.setItem('username', myJSON.username);
        })
    };

    render() {
        const { classes } = this.props;
        const { sent } = this.state;
        let text;

        if (this.state.redirect)
            return <Redirect push to="/Feed"/>;
        if (this.state.incorrect)
            text = <Typography variant="body2" align="center" className={classes.text}>
                {'Incorrect Login. '}
                <Link
                    align="center"
                    underline="always"
                    className={classes.text}
                    component={LinkRouter}
                    to={"/forgotpassword"}
                >
                    Forgot Password?
                </Link>
            </Typography>;
        else
            text = <Typography variant="body2" align="center">
                {'Not a member yet? '}
                <Link
                    align="center"
                    underline="always"
                    component={LinkRouter}
                    to={"/signup"}
                >
                    Sign Up here
                </Link>
            </Typography>;

        return (
            <React.Fragment>
                <AppAppBar />
                <AppForm>
                    <React.Fragment>
                        <Typography variant="h3" gutterBottom marked="center" align="center">
                            Sign In
                        </Typography>
                        {text}
                    </React.Fragment>
                    <Form
                        onSubmit={this.handleSubmit}
                        subscription={{ submitting: true }}
                        validate={this.validate}
                    >
                        {({ handleSubmit, submitting }) => (
                            <form onSubmit={handleSubmit} className={classes.form} noValidate>
                                <Field
                                    autoComplete="email"
                                    autoFocus
                                    component={RFTextField}
                                    disabled={submitting || sent}
                                    fullWidth
                                    label="Email"
                                    margin="normal"
                                    name="email"
                                    required
                                    size="large"
                                />
                                <Field
                                    fullWidth
                                    size="large"
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
                                    size="large"
                                    color="secondary"
                                    fullWidth
                                >
                                    {submitting || sent ? 'In progress…' : 'Sign In'}
                                </FormButton>
                            </form>
                        )}
                    </Form>
                    <Typography align="center">
                        <Link
                            underline="always"
                            component={LinkRouter}
                            to={"/forgotpassword"}
                        >
                            Forgot password?
                        </Link>
                    </Typography>
                </AppForm>
                <AppFooter />
            </React.Fragment>
        );
    }
}

SignIn.propTypes = {
    classes: PropTypes.object.isRequired
};

export default compose(
    withRoot,
    withStyles(styles),
)(SignIn);
