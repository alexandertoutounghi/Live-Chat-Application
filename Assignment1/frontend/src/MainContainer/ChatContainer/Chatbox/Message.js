import React from "react";
import { useRef } from "react";
import { Div } from "../../../Utils/Utils";
import "./Message.scss";
import "../../../Styles/Utils.scss";

const Message = (props) => {
  const { type, content, username } = props;
  return (
    <Div c={`message ${type}`}>
      <Div c="message-container">
        <Div c="content-container">
          <Div c="content">{content}</Div>
        </Div>
        <Div c="username-container">
          <Div c="username">{username}</Div>
        </Div>
      </Div>
    </Div>
  );
};

export default Message;
