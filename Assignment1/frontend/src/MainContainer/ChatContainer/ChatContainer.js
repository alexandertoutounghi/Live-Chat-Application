import React from "react";
import { Div } from "../../Utils/Utils";
import Chatbox from "./Chatbox/Chatbox";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";
import TopBar from "./Topbar/Topbar";

const ChatContainer = (props) => {
  const { user } = props;
  return (
    <Div c="chat-container">
      <Chatbox {...{ user }} />
    </Div>
  );
};

export default ChatContainer;
