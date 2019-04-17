import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import React from 'react';
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import { withStyles } from '@material-ui/core/styles';
import AppFooter from './modules/views/AppFooter';
import FeedBar from './modules/views/FeedBar';
import AppForm from './modules/views/AppForm';
import FormButton from "./modules/form/FormButton";
import {Field, Form} from "react-final-form";
import {Typography} from "@material-ui/core";
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField'
import RFTextField from "./modules/form/RFTextField";

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
    bigAvatar: {
        margin: 5,
        width: 200,
        height: 200,
    },
});


const url = 'http://localhost:8080';

class Profile extends React.Component {
    state = {
        sent: false,
        redirect: false,
        username: "Kevinlin6543",
        email: "kevin.lin6543@gmail.com",
        name: "Kevin Lin",
        bio: "amazing front end developer"
    };
    handleChange = name => event => {
        this.setState({ [name]: event.target.value });
    };

    render() {
        const { classes } = this.props;
        const { sent } = this.props;
        return (
            <React.Fragment>
                <FeedBar />
                <AppForm>
                    <Grid container spacing={24}>
                        <Grid item xs={12} sm={6} key={22}>
                            <Avatar alt="Stock image" src={process.env.PUBLIC_URL+"dude.jpg"} className={classes.bigAvatar} />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <form className={classes.container} noValidate autoComplete="off">
                                <TextField
                                    id="name"
                                    label="Name"
                                    className={classes.textField}
                                    value={this.state.name}
                                    onChange={this.handleChange('name')}
                                    margin="normal"
                                />
                                <TextField
                                    id="username"
                                    label="Username"
                                    className={classes.textField}
                                    value={this.state.username}
                                    onChange={this.handleChange('username')}
                                    margin="normal"
                                />
                                <TextField
                                    id="email"
                                    label="Email"
                                    className={classes.textField}
                                    value={this.state.email}
                                    onChange={this.handleChange('email')}
                                    margin="normal"
                                />
                            </form>
                        </Grid>
                    </Grid>
                    <Form
                        onSubmit={this.handleSubmit}
                        subscription={{ submitting: true }}
                        validate={this.validate}
                    >
                        {({ handleSubmit, submitting }) => (
                            <FormButton
                                className={classes.button}
                                disabled={submitting || sent}
                                size="large"
                                color="secondary"
                                fullWidth
                            >
                                {submitting || sent ? 'In progress…' : 'Update'}
                            </FormButton>
                            )}
                            </Form>
                </AppForm>
                <AppFooter />
            </React.Fragment>
        );
    }
}

Profile.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default compose(
    withRoot,
    withStyles(styles),
)(Profile);
