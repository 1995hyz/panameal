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
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import SwipeableViews from 'react-swipeable-views';
import {Redirect} from 'react-router-dom';
import {Card, CardActions, CardContent, CardHeader} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import ShareIcon from '@material-ui/icons/Share';
import FavoriteIcon from '@material-ui/icons/Favorite';

function TabContainer({ children, dir }) {
    return (
        <Typography component="div" dir={dir} style={{ padding: 4 * 3 }}>
            {children}
        </Typography>
    );
}

TabContainer.propTypes = {
    children: PropTypes.node.isRequired,
    dir: PropTypes.string.isRequired,
};

const styles = theme => ({
    card: {
        //marginTop: -theme.spacing.unit * 4,
        marginBottom: '2.5vh',
        marginLeft: 'auto',
        marginRight: 'auto',
        maxWidth: '50vw',
    },
    tabs: {
        width: 660,
        marginLeft: 'auto',
        marginRight: 'auto',
        marginTop: theme.spacing.unit * 5,
        marginBottom: -theme.spacing.unit * 13,
    },
    form: {
        marginTop: theme.spacing.unit * 6,
    },
    button: {
        marginTop: theme.spacing.unit * 4,
        marginBottom: theme.spacing.unit * 2,
        //marginRight: theme.spacing.unit * 5,
        //marginLeft: theme.spacing.unit * 5,
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
    followGrid: {
        marginTop: '2vh',
    }
});

var token = localStorage.getItem('authToken') ? localStorage.getItem('authToken') : "dsfasdf";

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
                    var myJson = response.json();
                    console.log(myJson);
                    this.setState({
                        user: myJson.user,
                        posts: myJson.post,
                        fname: myJson.user.firstname,
                        lname: myJson.user.lastname,
                        follow: !!myJson.user.followFlag,
                    });
                }
                else {
                    this.setState({validUser: false})
                }
            })
    };

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
                    user: myJson.user,
                    posts: myJson.post,
                    fname: myJson.user.firstname,
                    lname: myJson.user.lastname,
                    follow: !!myJson.followFlag,
                });
            });
    };

    getFollowing = () => {
        console.log(this.props.match.params.username);
        fetch(url + '/follower_list',  {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body:
                this.state.username
        })
            .then(response => {
                if (response.status === 200) {
                    console.log(response.json());
                }
            })
    };
    state = {
        sent: false,
        redirect: false,
        useron: !!localStorage.getItem('authToken'),
        username: this.props.match.params.username,
        user: "",
        posts: [],
        cuseremail: token,
        validUser: true,
        follow: false,
        followText: ["Follow", "Unfollow"],
        value: 0,
    };

    componentDidCatch(error, info) {
        this.setState({validUser: false});
    }

    componentWillMount() {
        this.getUser();
        this.getFollowing();
    }
    handleLike = index => {
        console.log(index);
    };
    handleFollow = () => {
        this.setState({follow: !this.state.follow});
    };

    handleChange = (event, value) => {
        this.setState({ value });
    };

    handleChangeIndex = index => {
        this.setState({ value: index });
    };

    render() {
        if (!this.state.validUser) {
            return (
                <Redirect push to="/"/>
            );
        }
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
                <Tabs
                    value={this.state.value}
                    className={classes.tabs}
                    onChange={this.handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                    variant="fullWidth"
                >
                    <Tab label="Profile" />
                    <Tab label="Followers" />
                    <Tab label="Following" />
                    <Tab label="Posts" />
                </Tabs>
                <AppForm>
                    <SwipeableViews
                        index={this.state.value}
                        onChangeIndex={this.handleChangeIndex}
                    >
                        <TabContainer>
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
                                        {this.state.user.username}
                                    </Typography>
                                    <Typography variant={'subtitle1'} className={classes.desText}>
                                        {'Full Name:'}
                                    </Typography>
                                    <Typography variant={'h6'} className={classes.profText}>
                                        {this.state.user.firstname + ' ' + this.state.user.lastname}
                                    </Typography>
                                    <Typography variant={'subtitle1'} className={classes.desText}>
                                        {'Phone Number:'}
                                    </Typography>
                                    <Typography variant={'h6'} className={classes.profText}>
                                        {this.state.user.phone}
                                    </Typography>
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
                            <Typography variant={'subtitle1'} className={classes.biotitle}>
                                {'Biography: '}
                            </Typography>
                            <Typography variant={'subtitle2'} className={classes.bio}>
                                {this.state.user.bio}
                            </Typography>
                                <Button
                                    variant="outlined"
                                    color="secondary"
                                    disabled={!this.state.useron}
                                    className={classes.button}
                                    onClick={this.handleFollow}>
                                    {this.state.followText[this.state.follow ? 1 : 0]}
                                </Button>
                        </TabContainer>
                        <TabContainer>Item Two</TabContainer>
                        <TabContainer>Item Two</TabContainer>
                        <TabContainer>
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
                        </TabContainer>
                    </SwipeableViews>
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
