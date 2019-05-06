import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { fade } from '@material-ui/core/styles/colorManipulator';
import {withStyles} from '@material-ui/core/styles';
import Link from '@material-ui/core/Link';
import InputBase from '@material-ui/core/InputBase';
import AppBar from '../components/AppBar';
import Toolbar, {styles as toolbarStyles} from '../components/Toolbar';
import {Link as LinkRouter, Redirect} from 'react-router-dom';
import SearchIcon from '@material-ui/icons/Search';

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
        marginLeft: -theme.spacing.unit * 35,
        marginRight: 'auto',
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
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginRight: theme.spacing.unit * 2,
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            marginLeft: theme.spacing.unit * 3,
            width: 'auto',
        },
    },
    searchIcon: {
        width: theme.spacing.unit * 9,
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        marginLeft: -theme.spacing.unit * 2,
    },
    inputInput: {
        paddingTop: theme.spacing.unit,
        paddingRight: theme.spacing.unit,
        paddingBottom: theme.spacing.unit,
        paddingLeft: theme.spacing.unit * 4,
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('md')]: {
            width: 200,
        },
    },

});


class AppAppBar extends React.Component {

    state = {
        searchStr: "",
        redirect: false,
    };

    handleChange = (event) => {
        this.setState({ searchStr: event.target.value });
    };
    handleKeyPress = (event) => {
        if(event.key == 'Enter'){
            this.setState({redirect: true})
        }
    };

    render() {
        const { classes } = this.props;
        if (this.state.redirect) {
            return (
                <Redirect push to={"/u/" + this.state.searchStr}/>
            )
        }
        return (
            <div>
                <AppBar position="fixed">
                    <Toolbar className={classes.toolbar}>
                        <div className={classes.search} onKeyPress={this.handleKeyPress}>
                            <div className={classes.searchIcon}>
                                <SearchIcon/>
                            </div>
                            <InputBase
                                placeholder="Search…"
                                classes={{
                                    root: classes.inputRoot,
                                    input: classes.inputInput,
                                }}
                                onChange={this.handleChange}
                                value={this.state.searchStr}
                            />
                        </div>
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
                                component={LinkRouter}
                                to={"/signin"}
                            >
                                {'Sign In'}
                            </Link>
                            <Link
                                variant="h6"
                                underline="none"
                                className={classNames(classes.rightLink, classes.linkSecondary)}
                                component={LinkRouter}
                                to={"/signup"}
                            >
                                {'Sign Up'}
                            </Link>
                        </div>
                    </Toolbar>
                </AppBar>
                <div className={classes.placeholder}/>
            </div>
        );
    }
}

AppAppBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(AppAppBar);
