import React from 'react';
import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import PropTypes from 'prop-types';
import compose from 'recompose/compose';
import { withStyles } from '@material-ui/core/styles';
import FeedBar from './modules/views/FeedBar';
import ImageGridList from "./modules/views/ImageGridList";
import AppFooter from "./modules/views/AppFooter";
import LayoutBody from "./modules/components/LayoutBody";


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
    paper: {
        padding: `${theme.spacing.unit * 4}px ${theme.spacing.unit * 3}px`,
        [theme.breakpoints.up('md')]: {
            padding: `${theme.spacing.unit * 10}px ${theme.spacing.unit * 8}px`,
        },
    },
});

class Feed extends React.Component {
    render() {
        return (
            <LayoutBody margin marginBottom width="xlarge">
                <FeedBar />
                    <ImageGridList/>
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