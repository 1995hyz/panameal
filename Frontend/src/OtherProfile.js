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
import {Link as LinkRouter, Redirect} from 'react-router-dom';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Dialog from '@material-ui/core/Dialog';
import FormButton from "./modules/form/FormButton";
import {Form, withRouter} from "react-final-form";
import {email, number, required} from "./modules/form/validation";
import TextField from '@material-ui/core/TextField';
import Link from "@material-ui/core/Link";

function TabContainer({ children}) {
    return (
        <Typography component="div" style={{ padding: 4 * 3 }}>
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
        fetch(url + '/user',  {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body:
                JSON.stringify({
                    email: localStorage.getItem('authToken'),
                    username: this.state.username,
                }),
        })
            .then(response => {
                this.state.validUser = (response.status === 200);
                console.log(this.state);
                console.log((response.status === 200));
                if(response.status === 200)
                    return response.json();
                else
                    return response;
            })
            .then(myJson => {
                console.log(myJson);
                if(myJson.status === 400)
                    return;
                if(this.state.validUser) {
                    this.setState({
                        followFlag: myJson.followFlag,
                        follow: !!myJson.followFlag,
                        user: myJson.user,
                        firstname: myJson.user.firstname,
                        lastname: myJson.user.lastname,
                        phone: myJson.user.phone,
                        bio: myJson.user.bio,
                        posts: myJson.post,
                    });
                    if(this.state.user.username === localStorage.getItem('username'))
                        this.setState({hideFollow: true})
                }
                else
                    this.setState({temp: myJson})
            });
    };
    getFollowing = () => {
        fetch(url + '/following_list',  {
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
                    return response.json();
                }
            })
            .then(myJSON => {
                console.log(myJSON);
                if(this.state.validUser)
                    this.setState({following: myJSON});
                else
                    this.setState({following: ["test", "test"]});
            })
    };
    getFollowers = () => {
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
                    return response.json();
                }
            })
            .then(myJSON => {
                if(this.state.validUser)
                    this.setState({followers: myJSON});
                else
                    this.setState({followers: ["test", "test"]});
            })
    };

    componentWillMount() {
        this.getUser();
        if (this.state.validUser) {
            this.getFollowers();
            this.getFollowing();
        }
    }
    handleLike = index => {
        console.log(index);
    };
    handleFollow = (followString) => {
        console.log(followString);
        fetch(url + '/' + followString,  {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body:
                JSON.stringify({
                    email: localStorage.getItem('authToken'),
                    usernameFollowing: this.state.username,
                }),
        })
            .then(response => {
                if (response.status === 200) {
                    this.setState({follow: !this.state.follow});
                    this.getFollowers();
                }
            })
    };

    handleRedirect = (username) => {
        this.setState({
            redirect: true,
            userRedirect: username,
        });
    };

    validate = values => {
        const errors = required(['password'], values, this.props);
        if (!errors.email) {
            const emailError = email(values.email, values, this.props);
            if (emailError) {
                errors.email = email(values.email, values, this.props);
            }
        }
        if (!errors.phone) {
            const numError = number(values.phone, values, this.props);
            if (numError) {
                errors.phone = number(values.phone, values, this.props);
            }
        }
        return errors;
    };

    openUpdate = () => {
        this.setState({open: true});
    };
    handleClose = () => {
        this.setState({open: false})
    };
    handleSubmit = values => {
        console.log('submit');
        fetch(url + '/update/user', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: this.state.user.email,
                username: this.state.user.username,
                firstname: this.state.firstname,
                lastname: this.state.lastname,
                phone: this.state.phone,
                bio: this.state.bio,
                password: this.state.password,
            }),
        }).then(res => {
            console.log(res);
            if(res.status === 200) {
                this.getUser();
                this.setState({open: false});
            }
        });
    };

    handleChangeIndex = index => {
        this.setState({ value: index });
    };

    handleChange = (event, value) => {
        this.setState({ value });
    };

    handleChangeValue = name => event => {
        this.setState({ [name]: event.target.value });
    };

    componentDidCatch(error, info) {
        this.setState({validUser: false});
    }
    state = {
        sent: false,
        redirect: false,
        userRedirect: "",
        useron: !!localStorage.getItem('authToken'),
        username: this.props.match.params.username,
        user: "",
        posts: [],
        cuseremail: token,
        validUser: true,
        follow: false,
        followers: ["test", "test"],
        following: ["test", "test"],
        followRoute: ['following', 'unfollowing'],
        followText: ["Follow", "Unfollow"],
        hideFollow: false,
        value: 0,
        open: false,
        firstname: "",
        lastname: "",
        phone: "",
        password: "",
        temp: "",
    };

    render() {
        const { classes } = this.props;
        const { sent } = this.state;
        if (this.state.redirect) {
            return (
                <Redirect push to={"/u/" + this.state.userRedirect}/>
            );
        }
        if (!this.state.validUser) {
            return (
                <Redirect push to="/"/>
            );
        }
        var bar;
        if (localStorage.getItem("authToken")) {
            bar = <FeedBar/>;
        }
        else {
            bar = <AppAppBar/>;
        }
        if(!this.state.hideFollow) {
            var followButton = (
                <Button
                    variant="outlined"
                    color="secondary"
                    disabled={!this.state.useron}
                    className={classes.button}
                    onClick={() => this.handleFollow(this.state.followRoute[this.state.follow ? 1 : 0])}>
                    {this.state.followText[this.state.follow ? 1 : 0]}
                </Button>
            )
        }
        else {
            var updateButton = (
                <div>
                    <FormButton
                        className={classes.button}
                        size="large"
                        color="secondary"
                        fullWidth
                        component={Button}
                        onClick={()=>this.openUpdate()}
                    >
                        Update Profile
                    </FormButton>
                </div>
            )
        }

        return (
            <React.Fragment>
                {bar}
                <Dialog
                    open={this.state.open}
                    onClose={this.handleClose}
                    aria-labelledby="Update Profile"
                >
                    <DialogTitle id="Update Profile">Update Profile</DialogTitle>
                    <DialogContent>
                        <Form
                            onSubmit={this.handleSubmit}
                            validate={this.validate}
                        >
                            {({ handleSubmit, submitting }) => (
                                <form onSubmit={handleSubmit} className={classes.container} noValidate autoComplete="off">
                                    <Grid container spacing={16}>
                                        <Grid item xs={12} sm={6}>
                                            <TextField
                                                id="First Name"
                                                label="First Name"
                                                className={classes.textField}
                                                value={this.state.firstname}
                                                onChange={this.handleChangeValue('firstname')}
                                                margin="normal"
                                                name="firstName"
                                            />
                                        </Grid>
                                        <Grid item xs={12} sm={6}>
                                            <TextField
                                                id="Last Name"
                                                label="Last Name"
                                                className={classes.textField}
                                                value={this.state.lastname}
                                                onChange={this.handleChangeValue('lastname')}
                                                margin="normal"
                                                name="lastName"
                                            />
                                        </Grid>
                                    </Grid>
                                    <TextField
                                        id="Phone Number"
                                        label="Phone Number"
                                        className={classes.textField}
                                        value={this.state.phone}
                                        onChange={this.handleChangeValue('phone')}
                                        margin="normal"
                                        fullWidth
                                        name="phone"
                                    />
                                    <TextField
                                        id="Bio"
                                        label="Bio"
                                        className={classes.textField}
                                        value={this.state.bio}
                                        onChange={this.handleChangeValue('bio')}
                                        margin="normal"
                                        fullWidth
                                        name="bio"
                                    />
                                    <TextField
                                        id="Password"
                                        label="Password"
                                        className={classes.textField}
                                        onChange={this.handleChangeValue('password')}
                                        margin="normal"
                                        fullWidth
                                        name="phone"
                                        type="password"
                                    />
                                    <FormButton
                                        className={classes.button}
                                        disabled={submitting || sent}
                                        size="large"
                                        color="secondary"
                                        fullWidth
                                        component={Button}
                                        onClick={()=>this.handleSubmit()}
                                    >
                                        {submitting || sent ? 'In progress…' : 'Update'}
                                    </FormButton>
                                </form>
                            )}
                        </Form>
                    </DialogContent>
                </Dialog>
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
                            </Grid>
                            <Typography variant={'subtitle1'} className={classes.biotitle}>
                                {'Biography: '}
                            </Typography>
                            <Typography variant={'subtitle2'} className={classes.bio}>
                                {this.state.user.bio}
                            </Typography>
                            {followButton}
                            {updateButton}
                        </TabContainer>
                        <TabContainer>
                            {this.state.followers.map( (user, index) => (
                                    <Typography
                                        variant={'subtitle2'}
                                        className={classes.bio}
                                    >
                                        {<Link
                                            underline="none"
                                            color="inherit"
                                            component={LinkRouter}
                                            to={"/u/" + user}
                                        >
                                            {index + 1 + ' : ' + user}
                                        </Link>}
                                    </Typography>
                                )
                            )}
                        </TabContainer>
                        <TabContainer>
                            {this.state.following.map( (user, index) => (
                                <Typography
                                    variant={'subtitle2'}
                                    className={classes.bio}
                                >
                                    {<Link
                                        underline="none"
                                        color="inherit"
                                        component={LinkRouter}
                                        to={"/u/" + user}
                                    >
                                        {index + 1 + ' : ' + user}
                                    </Link>}
                                </Typography>
                                )
                            )}
                        </TabContainer>
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
