import React from "react";
import { Div } from "../../Utils/Utils";
import TopBar from "./Topbar/Topbar";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";

const ChatContainer = (props) => {
  return (
    <Div c="chat-container">
      <TopBar />
    </Div>
  );
};

export default ChatContainer;
