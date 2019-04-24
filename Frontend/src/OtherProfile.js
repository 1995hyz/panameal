import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import React from 'react';
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import {withStyles} from '@material-ui/core/styles';
import AppFooter from './modules/views/AppFooter';
import FeedBar from './modules/views/FeedBar';
import AppForm from './modules/views/AppForm';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import url from "./modules/url";
import Typography from "./modules/components/Typography";

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

class OtherProfile extends React.Component {

    getUser = () => {
        console.log(this.props.match.params.username);
        fetch(url + '/user',  {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body:
                this.props.match.params.username,
        })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                }
            })
            .then(myJson =>{
                console.log(myJson);
                this.setState({data: myJson});
            });
    };
    state = {
        sent: false,
        redirect: false,
        username: this.props.match.params.username,
        email: "",
        name: "",
        bio: ""
    };
    componentWillMount() {
        this.getUser();
    }


    render() {
        const { classes } = this.props;
        const { sent } = this.props;
        return (
            <React.Fragment>
                <FeedBar />
                <AppForm>
                    <Grid container spacing={24}>
                        <Grid item xs={12} sm={6} >
                            <Avatar alt="Stock image" src={process.env.PUBLIC_URL+"dude.jpg"} className={classes.bigAvatar} />
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <Typography >
                                {this.state.username}
                            </Typography>
                        </Grid>
                    </Grid>
                </AppForm>
                <AppFooter />
            </React.Fragment>
        );
    }
}

OtherProfile.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default compose(
    withRoot,
    withStyles(styles),
)(OtherProfile);
