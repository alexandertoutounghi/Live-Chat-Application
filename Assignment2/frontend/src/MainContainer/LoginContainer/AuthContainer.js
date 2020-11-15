import React, {useEffect,useState} from 'react';
import "./AuthContainer.scss"
import Login from "./Login";
import RegisterContainer from "./RegisterContainer";
import LoginContainer from "./LoginContainer";

function AuthContainer(props) {
    const [route, setRoute] = useState("");
    const {handleLogin, user, setLoginPage, loginPage} = props;

    return (
        // <Router>
        <div className="auth-container">
            {(route === "") &&
            <ul className="auth-options-list">
                <li className="auth-options-list-item" onClick={() => setRoute("login")}>Login</li>
                <li className="auth-options-list-item" onClick={() => setRoute("register")}>Register</li>
            </ul>
            }
            {
                (route === "login") && <LoginContainer {...{handleLogin,user,setLoginPage,loginPage}}/>
            }
            {
                (route === "register") && <RegisterContainer/>
            }
        </div>
        // </Router>
    );
}

export default AuthContainer;