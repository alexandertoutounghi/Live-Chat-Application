//[IMPORTS]
import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import LoginContainer from "./LoginContainer/LoginContainer";
import ChatContainer from "./ChatContainer/ChatContainer";

import { Div } from "../Utils/Utils";
import "./MainContainer.scss";

//[FUNCTIONAL COMPONENTS]
const MainContainer = (props) => {
  const [loginPage, setLoginPage] = useState(true);
  const user = useRef({ name: "Anonymous" });

  const handleLogin = (username) => {
    if (username) user.current.name = username;
    setLoginPage(false);
  };

  const Logo = () => {
    return (
      <Div c={`logo-container${!loginPage ? " chat" : ""}`}>
        <Div c="logo-wrapper">Postit</Div>
      </Div>
    );
  };

  return (
    <Div c="main-container">
      <Div c=""></Div>
      {loginPage ? (
        <LoginContainer {...{ handleLogin }} />
      ) : (
        <ChatContainer {...{ user }} />
      )}

      <Logo />
    </Div>
  );
};

//[EXPORTS]
export default MainContainer;
