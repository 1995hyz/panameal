import React from 'react';
import PropTypes from 'prop-types';
import {Avatar, Card, CardActions, CardContent, CardHeader, Typography, ButtonBase } from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import {Link as LinkRouter} from 'react-router-dom';
import Link from "@material-ui/core/Link";


function FeedTile(props) {
    const {className, username, content, postTime, postID, handleLikeButton, handleRedirect} = props;
    return (
        <Card className={className.card} key={postID}>
            <CardHeader
                avatar={
                    <Avatar aria-label={username} className={className.avatar}>
                        {username.charAt(0)}
                    </Avatar>
                }
                title={
                    <Link
                        variant="body1"
                        underline="none"
                        color="inherit"
                        className={className.username}
                        component={LinkRouter}
                        to={"/u/" + username}
                    >
                        {username}
                    </Link>
                }
                subheader={postTime}
            />
            <CardContent>
                <Typography component="p">
                    {content}
                </Typography>
            </CardContent>
            <CardActions className={className.actions} disableActionSpacing>
                <IconButton aria-label="Like" onClick={()=>handleLikeButton(postID)}>
                    <FavoriteIcon />
                </IconButton>
                <IconButton aria-label="Share">
                    <ShareIcon/>
                </IconButton>
            </CardActions>
        </Card>
    )
}
FeedTile.propTypes = {
    className: PropTypes.object,
    username: PropTypes.string,
    content: PropTypes.string,
    postTime: PropTypes.string,
    handleLikeButton: PropTypes.func,
};

export default (FeedTile);