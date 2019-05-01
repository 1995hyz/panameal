import {Redirect} from 'react-router-dom';
import React from "react";
import compose from "recompose/compose";
import withRoot from "./modules/withRoot";

class User extends React.Component {
    render() {
        return <Redirect push to={"/user/"+this.props.match.params.username}/>
    }
}


export default compose(
    withRoot,
)(User);