import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import TextBox from "../ChatContainer/Chatbox/Textbox";
import { Div } from "../../Utils/Utils";
import "../../Styles/Utils.scss";
import "./LoginContainer.scss";

const LoginContainer = (props) => {
  const { handleLogin } = props;
  return (
    <Div c="login-container flex-col">
      <TextBox
        type="input"
        placeholder="Anonymous"
        buttonContent="Go"
        onClick={(e) => handleLogin(e)}
      />
    </Div>
  );
};

export default LoginContainer;
