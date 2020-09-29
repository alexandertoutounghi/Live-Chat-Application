import React from "react";
import { useState } from "react";
import Textbox from "./Textbox";
import Message from "./Message";
import { Div } from "../../../Utils/Utils";
import { v4 as uuid } from "uuid";

import "./Chatbox.scss";

const createMessage = (username, content, type = "others") => (
  <Message key={uuid()} {...{ username, content, type }} />
);

const Chatbox = (props) => {
  const { user } = props;

  const [messages, setMessages] = useState([
    createMessage(
      "Elon",
      "Hello James, my name is Elon. I work 40 hours a day!"
    ),
  ]);
  const handleMessage = (type, content, username) => {
    const arr = [...messages];
    arr.push(createMessage(username, content, type));
    // if (content.toLowerCase().includes("cybertruck")) {
    //   arr.push(
    //     createMessage("Elon", "No cybertruck for you James.")
    //   );
    // }
    setMessages(arr);
  };
  const sendMessage = (message) => {
    handleMessage("self", message, user.current.name);
  };

  // if (
  //   messages[messages.length - 1].props.content.toLowerCase().includes("kanye")
  // ) {
  //   window.setTimeout(() => {
  //     handleMessage(
  //       "others",
  //       "James, I will buy you a Blue Ocean rocket.",
  //       "Jeff Be$oz"
  //     );
  //   }, 1500);
  // }

  // if (
  //   messages[messages.length - 1].props.content.toLowerCase().includes("jeff")
  // ) {
  //   window.setTimeout(() => {
  //     handleMessage("others", "np bro", "Jeff Be$oz");
  //   }, 1000);
  // }

  return (
    <Div c="chatbox">
      <Div c="messagebox-container">
        <Div c="messagebox">{messages}</Div>
      </Div>
      <Div c="textbox-container">
        <Textbox
          type="textarea"
          placeholder="Send a message!"
          buttonContent="Send"
          onClick={(e) => sendMessage(e)}
        />
      </Div>
    </Div>
  );
};

export default Chatbox;
