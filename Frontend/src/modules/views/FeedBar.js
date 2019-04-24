import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import {withStyles} from '@material-ui/core/styles';
import Link from '@material-ui/core/Link';
import AppBar from '../components/AppBar';
import Toolbar, {styles as toolbarStyles} from '../components/Toolbar';
import {Link as LinkRouter, Redirect} from 'react-router-dom';
import Button from '@material-ui/core/Button';

const styles = theme => ({
    title: {
        fontSize: 24,
    },
    placeholder: toolbarStyles(theme).root,
    toolbar: {
        justifyContent: 'space-between',
    },
    left: {
        flex: 1,
    },
    leftLinkActive: {
        color: theme.palette.common.white,
    },
    right: {
        flex: 1,
        display: 'flex',
        justifyContent: 'flex-end',
    },
    rightLink: {
        fontSize: 16,
        color: theme.palette.common.white,
        marginLeft: theme.spacing.unit * 3,
    },
    linkSecondary: {
        color: theme.palette.secondary.main,
        marginTop: 6,
    },
});


class FeedBar extends React.Component{
    state = {
        redirect: false,
    };

    handleLogOut = () => {
        this.setState({redirect: true})
    };
    render() {
        const {classes} = this.props;
        if (this.state.redirect) {
            localStorage.removeItem('authToken');
            window.location.reload();
            //return <Redirect push to="/home" refresh={true}/>;
        }
        return (
        <div>
            <AppBar position="fixed">
                <Toolbar className={classes.toolbar}>
                    <div className={classes.left}/>
                    <Link
                        variant="h6"
                        underline="none"
                        color="inherit"
                        className={classes.title}
                        component={LinkRouter}
                        to={"/"}
                    >
                        {'Panameal'}
                    </Link>
                    <div className={classes.right}>
                        <Link
                            color="inherit"
                            variant="h6"
                            underline="none"
                            className={classes.rightLink}
                            component={Button}
                            onClick={this.handleLogOut}
                        >
                            {'Sign Out'}
                        </Link>
                        <Link
                            variant="h6"
                            underline="none"
                            className={classNames(classes.rightLink, classes.linkSecondary)}
                            component={LinkRouter}
                            to={"/profile"}
                        >
                            {'Profile'}
                        </Link>
                    </div>
                </Toolbar>
            </AppBar>
            <div className={classes.placeholder}/>
        </div>
        );
    };
}

FeedBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(FeedBar);
