import React from 'react';
import compose from "recompose/compose";
import {withStyles, Card, CardHeader, Avatar, CardActions, CardContent, Typography} from "@material-ui/core";
import IconButton from '@material-ui/core/IconButton';
import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import {orange} from '@material-ui/core/colors';
import url from '../../modules/url';
import LayoutBody from "../components/LayoutBody";
import Paper from "../components/Paper";

const styles = theme => ({
    card: {
        marginTop: '2.5vh',
        marginBottom: '2.5vh',
        marginLeft: 'auto',
        marginRight: 'auto',
        maxWidth: '50vw',
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },
    actions: {
        display: 'flex',
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
        }),
    },
    avatar: {
        backgroundColor: orange[500],
    },
    root: {
        display: 'flex',
        backgroundRepeat: 'no-repeat',
    },
    paper: {
        padding: `${theme.spacing.unit * 4}px ${theme.spacing.unit * 3}px`,
        [theme.breakpoints.up('md')]: {
            padding: `${theme.spacing.unit * 10}px ${theme.spacing.unit * 8}px`,
        },
        marginTop: '-10vh',
    },
    title: {
        variant: 'h6',
        textAlign: 'center',
        marginLeft: 'auto',
        marginRight: 'auto',
        marginBottom: '5vh',
        marginTop: '-8vh',
        fontSize: 34,
    },
});



class FeedData extends React.Component {
    state = {
        col: 0,
        data: [],
    };
    getPost = () => {
        fetch(url + '/view_post', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: localStorage.getItem('authToken'),
                amount: 6,
            }),
        })
            .then(response => {
                if (response.status === 200)
                    return response.json();
            })
            .then(myJson =>{
                this.setState({data: myJson});
            });
    };
    render() {
        const { classes } = this.props;
        this.getPost();
        var elements = [];
        for (var i = 0; i < this.state.data.length; i++) {
            // console.log(this.state.data[i].content);
            elements.push(
                <Card className={classes.card}>
                    <CardHeader
                        avatar={
                            <Avatar aria-label="Recipe" className={classes.avatar}>
                                P
                            </Avatar>
                        }
                        title={this.state.data[i].user_id}
                        subheader={this.state.data[i].post_time}
                    />
                    <CardContent>
                        <Typography component="p">
                            {this.state.data[i].content}
                        </Typography>
                    </CardContent>
                    <CardActions className={classes.actions} disableActionSpacing>
                        <IconButton aria-label="Like">
                            <FavoriteIcon/>
                        </IconButton>
                        <IconButton aria-label="Share">
                            <ShareIcon/>
                        </IconButton>
                    </CardActions>
                </Card>
            );
        }
        return(
            <div className={classes.root}>
                <LayoutBody margin marginBottom width="medium">
                    <Paper className={classes.paper}>
                        <Typography className={classes.title} variant="h6" color="inherit">
                            Feed
                        </Typography>
                        {elements}
                    </Paper>
                </LayoutBody>
            </div>
        )
    }
}


export default withStyles(styles)(FeedData);