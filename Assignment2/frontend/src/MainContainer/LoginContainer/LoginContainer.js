import React from 'react';
import AuthContainer from "./AuthContainer";
import {Div} from "../../Utils/Utils";
import Login from "./Login";
import ChatContainer from "../ChatContainer/ChatContainer";

function LoginContainer(props) {
    const {handleLogin, user, loginPage, setLoginPage} = props;

    return (
        <React.Fragment>
            {loginPage ? (
                <Login {...{handleLogin}} />
            ) : (
                <ChatContainer {...{user, setLoginPage}} />
            )}
            {/*<AuthContainer {...{handleLogin, user, setLoginPage}}/>*/}


        </React.Fragment>
    );
}

export default LoginContainer;
