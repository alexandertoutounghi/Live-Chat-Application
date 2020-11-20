import React from "react";
import { useState, useRef } from "react";
import { Div } from "../../Utils/Utils";
import Chatbox from "./Chatbox/Chatbox";
import "../../Styles/Utils.scss";
import "./ChatContainer.scss";
import TopBar from "./Topbar/Topbar";

const ChatContainer = (props) => {
  const { user, setLoginPage } = props;

  const [darkMode, setDarkMode] = useState(false);
  const [count,setCount] = useState(Number(sessionStorage.getItem("NumbMsgs")));

  const handleIncrement = () => {
    console.log(count)

    // var numb = Number(sessionStorage.getItem("NumbMsgs"));
    // numb += 1;
    setCount(count+1);
    sessionStorage.setItem("NumbMsgs", count);
  }
  const handleDecrement = () => {
    if (count===0)
        return
    console.log(count)
    // var numb = Number(sessionStorage.getItem("NumbMsgs"));
    // numb -= 1;
    setCount(count-1);
    sessionStorage.setItem("NumbMsgs", count);
  }


  return (
    <div
      className={`chat-container ${darkMode ? "dark" : "light"}${
        darkMode ? " times-hidden" : ""
      }`}
    >
      <Chatbox {...{ user, count }} />
      <TopBar
        {...{
          count,
          user,
          setLoginPage,
          darkMode,
          setDarkMode,
          handleDecrement,
          handleIncrement,
        }}
      />
    </div>
  );
};

export default ChatContainer;
