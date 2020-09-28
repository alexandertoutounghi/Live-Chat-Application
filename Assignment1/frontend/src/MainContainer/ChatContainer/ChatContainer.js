import React from "react";
import { Div } from "../../Utils/Utils";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";
import TopBar from "./Topbar/Topbar";

const ChatContainer = (props) => {
  return (
    <Div c="chat-container">
      <TopBar />
    </Div>
  );
};

export default ChatContainer;
