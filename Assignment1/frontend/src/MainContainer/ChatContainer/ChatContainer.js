import React from "react";
import { Div } from "../../Utils/Utils";
import Chatbox from "./Chatbox/Chatbox";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";
import TopBar from "./Topbar/Topbar";

const ChatContainer = (props) => {
  const { user, setLoginPage } = props;
  return (
    <Div c="chat-container">
      <Chatbox {...{ user }} />
      <TopBar username={user.current.name} setLoginPage={setLoginPage}/>
    </Div>
  );
};

export default ChatContainer;
