import React from "react";
import {useState, useRef} from "react";
import Textbox from "./Textbox";
import Message from "./Message";
import {Div, sendData} from "../../../Utils/Utils";
import {v4 as uuid} from "uuid";
import fetchToCurl from "fetch-to-curl";

import "./Chatbox.scss";
import Dropdown from "../Topbar/DropDown/Dropdown";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const handleMessage = (message) => {
    console.log(message);
}

const handleEdit = (msg) => {
    console.log(msg);
    window.dispatchEvent("onEdit");
    // console.log("hello");
    // setEdit(true);
}


const createMessage = (date, time, username, content, type = "others") => {
    return <Message key={uuid()} {...{date, time, content, username, type,}} />;
};

const Chatbox = (props) => {
    const {user, count} = props;
    const intervalId = useRef(0);
    const [messages, setMessages] = useState([]);



    const sendMessage = async (message, username = sessionStorage.getItem("Username")) => {
        const response = await sendData({username: username, message: message});
        if (response !== "message_accepted") {
            alert("Your message couldn't be sent!");
        }
        getMessages();
    };

    const getMessages = async () => {
        try {
            const serverMessages = await sendData({returnMessages: "true"});
            const jsonObj = JSON.parse(serverMessages);
            console.log(jsonObj);
            console.log(Object.keys(jsonObj).length);
            const newMessages = Object.values(jsonObj).map((msg) =>
                createMessage(
                    msg.date,
                    msg.time,
                    msg.user,
                    msg.message,
                    msg.user === sessionStorage.getItem("Username") ? "self" : "others"
                )
            );
            if (messages.length !== newMessages) {
                setMessages(newMessages);
            }
        } catch (e) {
            console.log(`Error: ${e}`);
        }


    }
    React.useEffect(() => {
        const date =  new Date()
        setMessages([createMessage(date.getDate(),date.getTime(),"Oranges","cry me a river"),
        createMessage(date.getDate(),date.getTime(),"peaches","quack","self")
        ])
        // createMessage(date.getDate(),date.getTime(),"Oranges","others")
        // createMessage(date.getDate(),date.getTime(),"peaches","self")
        // getMessages()
        // if (messages.length < count)
        //     (messages
        console.log(count)
        console.log(messages)
        console.log((messages.slice(Math.max(messages.length - count, 0))))

        if (messages.length >= 1)
            setMessages(messages.slice(Math.max(messages.length - count, 0)))

    },[]);

    const messageFilter = () => {
        console.log(messages.length);
        console.log("hello");
        if (messages.length < count)
            return messages
        else
            return messages.slice(Math.max(messages.length - 5, 0))

    }

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

                {/*<form className={"send-msg-txtbox"}>*/}
                {/*    <input type="text" placeholder="Send a Message"/>*/}

                {/*    <label htmlFor="file-upload" className="upload" title={"Upload a File"}>*/}
                {/*        <FontAwesomeIcon icon={['fas', 'file-upload']} color="black" size="2x"/>*/}
                {/*    </label>*/}
                {/*    <input id="file-upload" type="file" />*/}
                {/*    <input type="submit"/>*/}
                {/*</form>*/}

            </Div>
        </Div>
    );
};

export default Chatbox;
