//[IMPORTS]
import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import LoginContainer from "./LoginContainer/LoginContainer";
import ChatContainer from "./ChatContainer/ChatContainer";
import { Div } from "../Utils/Utils";
import "./MainContainer.scss";
import Topbar from "./ChatContainer/Topbar/Topbar";


//[FUNCTIONAL COMPONENTS]
const MainContainer = (props) => {
  const [loginPage, setLoginPage] = useState(true);
  const propGroup = { loginPage, setLoginPage };
  const Logo = (props) => {
    return (
      <Div c={`logo-container${!loginPage ? " chat" : ""}`}>
        <Div c="logo-wrapper">Postit</Div>
      </Div>
    );
  };
  return (
    // <Div c="main-container">
    //   {loginPage ? (
    //     <LoginContainer {...propGroup} />
    //   ) : (
    //     <ChatContainer {...propGroup} />
    //   )}
    //
    //   <Logo {...propGroup} />
    // </Div>
      <Topbar/>
  );
};

//[EXPORTS]
export default MainContainer;
