// --- Post bootstrap -----
import React, {Component} from 'react'
import Home from './Home';
import SignIn from './SignIn';
import SignUp from './SignUp';
import ForgotPassword from './ForgotPassword';
import Feed from './Feed';
import Profile from './Profile';
import OtherProfile from './OtherProfile';
import User from './User';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {PrivateRoute} from './modules/components/PrivateRoute';

class App extends Component {

    render() {
        if (localStorage.getItem('authToken')) {
            return (
                <Router>
                    <Switch>
                        <Route exact path="/" component={Feed} />
                        <Route exact path="/home" component={Home} />
                        <Route exact path="/user/:username" component={OtherProfile} />
                        <Route exact path="/u/:username" component={User}/>
                        <PrivateRoute exact path="/profile" component={Profile} />
                        <PrivateRoute exact path="/feed" component={Feed} />
                        <Route component={Feed} />
                    </Switch>
                </Router>
            )
        }
        else {
            return (
                <Router>
                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/signin" component={SignIn} />
                        <Route exact path="/signup" component={SignUp} />
                        <Route exact path="/forgotpassword" component={ForgotPassword} />
                        <PrivateRoute exact path="/profile" component={Profile} />
                        <Route exact path="/user/:username" component={OtherProfile} />
                        <PrivateRoute exact path="/feed" component={Feed} />
                        <Route component={Home} />
                    </Switch>
                </Router>
            )
        }
    }
}

export default App;