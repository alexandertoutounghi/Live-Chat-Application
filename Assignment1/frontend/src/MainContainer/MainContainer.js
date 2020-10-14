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
    console.log("Sent", username);
    fetch("http://localhost:3000/ChatServlet_war/ChatServlet",{
      method: 'POST',
      dataType: 'json',
      contentType: 'application/json',

      data: {jsondata: JSON.stringify({user: username})},
      url : "/ChatServlet"
    }
    )
    .then(res => {
            return res.text();
    }).then(con=>
      {console.log(con)}
      
    )
    .catch(error => {
      console.error('MyError: ', error);
    });
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
