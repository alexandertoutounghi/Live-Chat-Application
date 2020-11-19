//[IMPORTS]
import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import Login from "./LoginContainer/Login";
import ChatContainer from "./ChatContainer/ChatContainer";

import { Div, sendData } from "../Utils/Utils";
import "./MainContainer.scss";
import AuthContainer from "./LoginContainer/AuthContainer";
import { BrowserRouter, Route, Switch, Link } from "react-router-dom";
import NotFoundPage from "./NotFoundPage";
import Logo from "./Logo";
// import Route from "react-router-dom";

//[FUNCTIONAL COMPONENTS]
const MainContainer = (props) => {
  const [loginPage, setLoginPage] = useState("");
  // const user = useRef({name: ""});
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (sessionStorage.getItem("Username") !== null) setLoginPage("chat");
  }, []);

  const handleLogin = async (data) => {
    const response = await sendData("login", data);
    console.log(response);
    if (response) {
      // console.log(response);
      // user.current.name = data.username;
      // setLoginPage("chat");
      // sessionStorage.setItem("Username", data.username);
    } else {
      // alert("Server rejected your login...");
    }
  };

  // const handleLogin = (data) => {
  //     sessionStorage.setItem("Username", data.username);
  //     sessionStorage.setItem("NumbMsgs", 10);

  //     // user.current.name = data.username;
  //     // console.log(user.current.name);
  //     // setUser(data.username);
  //     setLoginPage("chat");
  // };

  const OptionsList = (props) => {
    return (
      <Div>
        <Logo />
        <Div c="auth-container">
          <ul className="auth-options-list">
            <li
              className="auth-options-list-item"
              onClick={() => setLoginPage("login")}
            >
              <Link to="/login">Login</Link>
            </li>
            <li
              className="auth-options-list-item"
              onClick={() => setLoginPage("chat")}
            >
              <Link to="/register">Register</Link>
            </li>
          </ul>
        </Div>
      </Div>
    );
  };

  // const Logo = () => {
  //     return (
  //         <Div c={`logo-container${loginPage !== "chat" ? "" : " chat"}`}>
  //             <Div c="logo-wrapper">Postit</Div>
  //         </Div>
  //     );
  // };

  return (
    <Div c="main-container">
      <BrowserRouter>
        <Switch>
          {sessionStorage.getItem("Username") === null && (
            <Route path="/" render={OptionsList} exact />
          )}
          {sessionStorage.getItem("Username") !== null && (
            <Route
              path="/"
              render={(props) => <ChatContainer {...{ user, setLoginPage }} />}
              exact
            />
          )}
          {/*<Route path="/login" render={<Login {...{handleLogin}}/>}/>*/}
          <Route
            path="/login"
            render={(props) => <Login {...{ handleLogin }} />}
            exact
          />
          {/*<Route path="/chat" render={(props) => <ChatContainer {...{user, setLoginPage}} />} exact/>*/}
          <Route component={NotFoundPage} />
        </Switch>
      </BrowserRouter>

      {/*            {loginPage==="" &&
                <Div c="auth-container">
                    <ul className="auth-options-list">
                        <li className="auth-options-list-item" onClick={() => setLoginPage("login")}>Login</li>
                        <li className="auth-options-list-item" onClick={() => setLoginPage("chat")}>Register</li>
                    </ul>
                </Div>
            }
            {
                loginPage==="login" &&
                <Login {...{handleLogin}} />
            }
            {
                loginPage==="chat" &&
                <ChatContainer {...{user, setLoginPage}} />

            }*/}

      {/*{loginPage ? (*/}
      {/*<Login {...{handleLogin}} />*/}
      {/*) : (*/}
      {/*    <ChatContainer {...{user, setLoginPage}} />*/}
      {/*)}*/}
      {/*<AuthContainer {...{handleLogin, user, setLoginPage, loginPage}}/>*/}
      {/*<Logo/>*/}
    </Div>
  );
};

//[EXPORTS]
export default MainContainer;
