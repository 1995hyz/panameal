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
                this.setState({
                    posts: myJson.post,
                    fname: myJson.user.firstname,
                    lname: myJson.user.lastname,
                });
            });
    };
    state = {
        sent: false,
        redirect: false,
        username: this.props.match.params.username,
        email: "",
        fname: "",
        lname: "",
        posts: [],
    };
    componentWillMount() {
        this.getUser();
    }
    handleLike = index => {
        console.log(index);
    };

    render() {
        const { classes } = this.props;
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
                        <Grid item xs={12} sm={6}>
                            <Typography >
                                {this.state.fname + this.state.lname}
                            </Typography>
                        </Grid>

                    </Grid>
                    {this.state.posts.map((post, index) => (
                        <FeedTile
                            className={classes}
                            username={this.state.username}
                            content={post.content}
                            postTime={post.post_time}
                            postID={post.post_id}
                            handleLikeButton={this.handleLike}
                        />
                        /*<Card className={classes.card} key={index}>
                            <CardHeader
                                avatar={
                                    <Avatar aria-label="Recipe" className={classes.avatar}>
                                        {this.state.username.charAt(0)}
                                    </Avatar>
                                }
                                title={this.state.username}
                                subheader={post.post_time}
                            />
                            <CardContent>
                                <Typography component="p">
                                    {post.content}
                                </Typography>
                            </CardContent>
                            <CardActions className={classes.actions} disableActionSpacing>
                                <IconButton aria-label="Like">
                                    <FavoriteIcon />
                                </IconButton>
                                <IconButton aria-label="Share">
                                    <ShareIcon/>
                                </IconButton>
                            </CardActions>
                        </Card>*/
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
