import React from 'react';
import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import {withStyles} from '@material-ui/core/styles';
import FeedBar from './modules/views/FeedBar';
import AppFooter from "./modules/views/AppFooter";
import LayoutBody from "./modules/components/LayoutBody";
import Tooltip from '@material-ui/core/Tooltip';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Dialog from '@material-ui/core/Dialog';
import {Field, Form, FormSpy} from "react-final-form";
import RFTextField from "./modules/form/RFTextField";
import FormFeedback from "./modules/form/FormFeedback";
import FormButton from "./modules/form/FormButton";
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import {Radio} from 'final-form-material-ui';
import FeedData from './modules/views/FeedData';
import {FormControl, FormControlLabel, FormLabel, RadioGroup} from '@material-ui/core';
import url from './modules/url';



const styles = theme => ({
    div: {
        top: '84vh',
        left: '86vw',
        margin: theme.spacing.unit * 2,
        position: 'fixed',
        zIndex: 1,
    },
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
    paper: {
        marginTop: theme.spacing.unit * 2,
    },
});

class Feed extends React.Component {
    state = {
        open: false,
        sent: false,
        privacy: '1',
    };

    handleChange = event => {
        this.setState({ privacy: event.target.value });
    };
    handleAddPost = () => {
        this.setState({open: true});
    };
    handleClose = () => {
        this.setState({open: false})
    };
    handleSubmit = values => {
        values.submitting = true;
        fetch(url + '/create/post', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: localStorage.getItem('authToken'),
                content: values.post,
                privacy_level: Number(values.privacy),
            }),
        }).then(res => {console.log(res);
            if (res.status === 200) {
                this.setState({open: false})
            }
        })
    };

    render() {
        const { classes } = this.props;
        const { sent } = this.state;
        return (
            <LayoutBody margin marginBottom width="xlarge">
                <FeedBar />
                    <div className={classes.div}>
                        <Tooltip title="Create Post" aria-label="Create Post" onClick={this.handleAddPost}>
                            <Fab color="secondary">
                                <AddIcon />
                            </Fab>
                        </Tooltip>
                    </div>
                    <Dialog
                        open={this.state.open}
                        onClose={this.handleClose}
                        aria-labelledby="Create New Post"
                    >
                        <DialogTitle id="Create New Post">Create Post</DialogTitle>
                        <DialogContent>
                            <Form
                                onSubmit={this.handleSubmit}
                                initialValues={{ privacy: '1' }}
                                subscription={{ submitting: true }}
                            >
                                {({ handleSubmit, submitting}) => (
                                    <form onSubmit={handleSubmit} noValidate>
                                        <Field
                                            autoFocus
                                            component={RFTextField}
                                            disabled={submitting || sent}
                                            fullWidth
                                            label="Title"
                                            margin="normal"
                                            name="title"
                                            size="large"
                                        />
                                        <Field
                                            autoFocus
                                            component={RFTextField}
                                            disabled={submitting || sent}
                                            fullWidth
                                            multiline
                                            label="Post"
                                            margin="normal"
                                            name="post"
                                            required
                                            size="large"
                                        />
                                        <FormControl component="fieldset">
                                            <FormLabel component="legend">Privacy Level</FormLabel>
                                            <RadioGroup row>
                                                <FormControlLabel
                                                    label="Global"
                                                    control={
                                                        <Field
                                                            name="privacy"
                                                            component={Radio}
                                                            type="radio"
                                                            value="1"
                                                        />
                                                    }
                                                />
                                                <FormControlLabel
                                                    label="Followers"
                                                    control={
                                                        <Field
                                                            name="privacy"
                                                            component={Radio}
                                                            type="radio"
                                                            value="2"
                                                        />
                                                    }
                                                />
                                                <FormControlLabel
                                                    label="Only you"
                                                    control={
                                                        <Field
                                                            name="privacy"
                                                            component={Radio}
                                                            type="radio"
                                                            value="3"
                                                        />
                                                    }
                                                />
                                            </RadioGroup>
                                        </FormControl>
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
                                            {submitting || sent ? 'In progress…' : 'CREATE!'}
                                        </FormButton>
                                    </form>
                                )}
                            </Form>
                        </DialogContent>
                    </Dialog>
                <FeedData/>
                <AppFooter />
            </LayoutBody>
        );
    }
}


Feed.propTypes = {
    classes: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired
};

export default compose(
    withRoot,
    withStyles(styles),
)(Feed);