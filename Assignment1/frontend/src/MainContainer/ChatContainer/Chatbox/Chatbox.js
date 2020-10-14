import React from "react";
import { useState, useRef } from "react";
import Textbox from "./Textbox";
import Message from "./Message";
import { Div, sendData } from "../../../Utils/Utils";
import { v4 as uuid } from "uuid";
import fetchToCurl from 'fetch-to-curl';


import "./Chatbox.scss";

const createMessage = (date, time, username, content, type = "others") => {
  return <Message key={uuid()} {...{ date, time, content, username, type }} />

};

const Chatbox = (props) => {
  const { user } = props;
  const intervalId = useRef(0);
  const [messages, setMessages] = useState([
  ]);

  const sendMessage = async (message, username = user.current.name) => {
    const response = await sendData({ username: username, message: message });
    if (response !== "message_accepted") {
      alert("Your message couldn't be sent!")
    }
  };

  React.useEffect(() => {
    intervalId.current = window.setInterval(async () => {
      try {
        const serverMessages = await sendData({ returnMessages: "true" });
        const jsonObj = JSON.parse(serverMessages);
        console.log(jsonObj);
        const newMessages = Object.values(jsonObj).map(msg => createMessage(msg.date, msg.time, msg.user, msg.message, msg.user===user.current.name ? "self": "others"))
        if(messages.length!==newMessages){
          setMessages(newMessages);
        }
      }
      catch (e) {
        console.log(`Error: ${e}`)
      }

    }, 250)
    return() => {
      window.clearInterval(intervalId.current);
    }
  }, [messages])


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
