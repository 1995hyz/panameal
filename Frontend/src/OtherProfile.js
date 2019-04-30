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
import FeedTile from './modules/components/FeedTile';
import Button from '@material-ui/core/Button';
import AppAppBar from "./modules/views/AppAppBar";
import {Card, CardActions, CardContent, CardHeader} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import ShareIcon from '@material-ui/icons/Share';
import FavoriteIcon from '@material-ui/icons/Favorite';

const styles = theme => ({
    card: {
        marginTop: '2.5vh',
        marginBottom: '2.5vh',
        marginLeft: 'auto',
        marginRight: 'auto',
        maxWidth: '50vw',
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
    bigAvatar: {
        margin: 5,
        width: 200,
        height: 200,
    },
    avatarText: {
        fontSize: '120px',
    },
    desText: {
        fontSize: '14px',
        marginBottom: '-10px'
    },
    profText: {
        fontSize: '28px',
        marginBottom: '2vh',
    },
});

var token = localStorage.getItem('authToken') ? localStorage.getItem('authToken') : "";

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
                JSON.stringify({
                    email: this.state.cuseremail,
                    username: this.state.username,
                }),
        })
            .then(response => {
                console.log(this.state);
                if (response.status === 200) {
                    return response.json();
                }
            })
            .then(myJson =>{
                console.log(myJson);
                this.setState({
                    posts: myJson.post,
                    fname: myJson.user.firstname,
                    lname: myJson.user.lastname,
                    follow: !!myJson.user.followFlag,
                });
            });
    };
    state = {
        sent: false,
        redirect: false,
        useron: !!localStorage.getItem('authToken'),
        username: this.props.match.params.username,
        email: "",
        fname: "",
        lname: "",
        posts: [],
        cuseremail: token,
        follow: false,
        followText: ["Follow", "Unfollow"],
    };
    componentWillMount() {
        //this.getUser();
    }
    handleLike = index => {
        console.log(index);
    };
    handleFollow = () => {
        this.setState({follow: !this.state.follow});
    };


    render() {
        if (localStorage.getItem("authToken")) {
            var bar = <FeedBar/>;
        }
        else {
            var bar = <AppAppBar/>;
        }
        const { classes } = this.props;
        return (
            <React.Fragment>
                {bar}
                <AppForm>
                    <Grid container spacing={24}>
                        <Grid item xs={12} sm={6} >
                            <Avatar aria-label={this.state.username} className={classes.bigAvatar}>
                                <Typography variant={'h1'} className={classes.avatarText}>
                                    {this.state.username.charAt(0)}
                                </Typography>
                            </Avatar>
                        </Grid>
                        <Grid item xs={12} sm={6}>
                            <Typography variant={'subtitle1'} className={classes.desText}>
                                {'Username:'}
                            </Typography>
                            <Typography variant={'h6'} className={classes.profText}>
                                {this.state.username}
                            </Typography>
                            <Typography variant={'subtitle1'} className={classes.desText}>
                                {'Full Name:'}
                            </Typography>
                            <Typography variant={'h6'} className={classes.profText}>
                                {this.state.fname + ' ' +this.state.lname}
                            </Typography>
                            <Typography variant={'subtitle1'} className={classes.desText}>
                                {'Phone Number:'}
                            </Typography>
                            <Typography variant={'h6'} className={classes.profText}>
                                {this.state.number}
                            </Typography>
                            <Button
                                variant="outlined"
                                color="secondary"
                                disabled={!this.state.useron}
                                className={classes.button}
                                onClick={this.handleFollow}>
                                {this.state.followText[this.state.follow ? 1 : 0]}
                            </Button>
                        </Grid>

                        {/*<Grid item xs={12} sm={6}>
                            <Button
                                variant="outlined"
                                color="secondary"
                                disabled={!this.state.useron}
                                className={classes.button}
                                onClick={this.handleFollow}>
                                {this.state.followText[this.state.follow ? 1 : 0]}
                            </Button>
                        </Grid>*/}

                    </Grid>
                    {this.state.posts.map((post, index) => (
                            <FeedTile
                                className={classes}
                                username={this.state.username}
                                content={post.post.content}
                                postTime={post.post.post_time}
                                postID={post.post.post_id}
                                handleLikeButton={this.handleLike}
                                key={{index}}
                            />
                        )
                    )}
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
