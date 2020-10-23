import React from "react";
import {useState, useRef} from "react";
import { Div } from "../../Utils/Utils";
import Chatbox from "./Chatbox/Chatbox";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";
import TopBar from "./Topbar/Topbar";

const ChatContainer = (props) => {
  const { user, setLoginPage } = props;
  let hideTimes = false;
  const chatContainerRef = useRef(null);
  const setHideTimes = ()=>{
    hideTimes = !hideTimes;
    chatContainerRef.current.className = `chat-container${hideTimes ? ' times-hidden':''}`
  }
  return (
    <div ref={chatContainerRef} className={`chat-container`}>
      <Chatbox {...{ user }} />
      <TopBar {...{username: user.current.name, setLoginPage, setHideTimes}}/>
    </div>
  );
};

export default ChatContainer;
