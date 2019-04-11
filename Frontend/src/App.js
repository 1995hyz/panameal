// --- Post bootstrap -----
import React, { Component } from 'react'
import Home from './Home';
import SignIn from './SignIn';
import SignUp from './SignUp';
import ForgotPassword from './ForgotPassword';
import Feed from './Feed';
import Profile from './Profile';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {PrivateRoute} from './modules/components/PrivateRoute';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route exact path="/" component={Home} />
                    <Route exact path="/signin" component={SignIn} />
                    <Route exact path="/signup" component={SignUp} />
                    <Route exact path="/forgotpassword" component={ForgotPassword} />
                    <Route exact path="/profile" component={Profile} />
                    <PrivateRoute exact path="/feed" component={Feed} />
                </Switch>
            </Router>
        );
    }
}

export default App;