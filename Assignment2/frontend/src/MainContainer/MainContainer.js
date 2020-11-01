//[IMPORTS]
import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import LoginContainer from "./LoginContainer/LoginContainer";
import ChatContainer from "./ChatContainer/ChatContainer";

import { Div, sendData } from "../Utils/Utils";
import "./MainContainer.scss";
import Toggle from "./ChatContainer/Topbar/DropDown/Toggle/Toggle";
import Topbar from "./ChatContainer/Topbar/Topbar";

//[FUNCTIONAL COMPONENTS]
const MainContainer = (props) => {
  const [loginPage, setLoginPage] = useState(true);
  const user = useRef({ name: "Anonymous" });



  const handleLogin = async (username) => {
    if (username === "") {
      username = "Anonymous";
    }
    const response = await sendData({username: username});
    if(response==="found_account"){
      user.current.name = username;
      setLoginPage(false);
    }
    else{
      alert("Server rejected your login...")
    }
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
        <ChatContainer {...{ user, setLoginPage }} />
      )}

      <Logo />
    </Div>
  );
};

//[EXPORTS]
export default MainContainer;
