import React from "react";
import { useState, useContext, useEffect, useRef } from "react";
import { Div } from "../../Utils/Utils";
import "../../Styles/Utils.scss";

import "./LoginContainer.scss";

const LoginContainer = (props) => {
  const { setLoginPage } = props;
  const Login = (props) => {
    return (
      <Div c="login flex-row">
        <Div c="login-wrapper">
          <input
            className="text-input"
            type="text"
            placeholder="Enter a username!"
          />
          <Div c="login-button" onClick={() => setLoginPage(false)}>
            Go
          </Div>
        </Div>
      </Div>
    );
  };

  return (
    <Div c="login-container flex-col">
      <Login />
    </Div>
  );
};

export default LoginContainer;
