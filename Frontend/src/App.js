import withRoot from './modules/withRoot';
// --- Post bootstrap -----
import React, { Component } from 'react'
import Home from './Home';
import SignIn from './SignIn';
import SignUp from './SignUp';
import Terms from './Terms';
import Privacy from './Privacy';
import {BrowserRouter, Route} from 'react-router-dom';

const url = 'http://localhost:8080';


class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div>
                    <Route exact={true} path='/' render={() => (
                        <div className="App">
                            <Home />
                        </div>
                    )}/>
                    <Route exact={true} path='/Signin' render={() => (
                        <div className="App">
                            <SignIn />
                        </div>
                    )}/>
                    <Route exact={true} path='/Signup' render={() => (
                        <div className="App">
                            <SignUp />
                        </div>
                    )}/>
                    <Route exact={true} path='/Terms' render={() => (
                        <div className="App">
                            <Terms />
                        </div>
                    )}/>
                    <Route exact={true} path='/Privacy' render={() => (
                        <div className="App">
                            <Privacy />
                        </div>
                    )}/>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;