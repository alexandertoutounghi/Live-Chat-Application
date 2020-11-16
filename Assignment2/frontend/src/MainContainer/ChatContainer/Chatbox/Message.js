// import React, {useState} from "react";
import React, { useState, CSSProperties, useEffect, useRef } from "react";
// import {useRef} from "react";
import {Div} from "../../../Utils/Utils";
import "./Message.scss";
import "../../../Styles/Utils.scss";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import EditMessage from "./EditMessage";
import EditMessageBox from "./EditMessageBox";


const Message = (props) => {
    const {type, content, date, time, username} = props;
    //edited.
    const [editMode, setEditMode] = useState(false);
    const [isModified, setIsModified] =useState(false);
    const [Content,setContent] = useState(content);

    // const hashcode,username,etc.

    const contentRef = useRef(null);

    const editor = () => {
        // console.log("hello");
        // props.edit(content);
        contentRef.current.focus();
        contentRef.current.classList.add("editing")
    }

    // useEffect(() => {
    //     if ()
    //
    // },[])

    onkeyup = (e) => {
        // console.log(e.key);
        if (e.key === "Escape")
            setEditMode(false);
        else if (e.key === "Enter") {
            if (e.target.value !== content) {
            //    send the content to the business layer
            //    modify the date

                // TODO send the content to the business layer of the edited stuff with a new date and everything
                setEditMode(false);
                setIsModified(true);
                setContent(e.target.value);


            }
        }
    }




    //handler
    const handleClose = () => {
        contentRef.current.blur();
        contentRef.current.classList.remove("editing")
    }


    const handleEdit = () => {
        setEditMode(!editMode);
        // contentRef.current.classList.add("editing")
    }

    const MessageOptions = (props) => {
        return (
            <Div c="user-options"  >

                <ul className={"options-list"}>
                    <li className={"options-list-item"} title="More Options">
                        <FontAwesomeIcon icon={['fas', 'ellipsis-h']} size={"lg"} className={"message-options"}/>
                    </li>
                    <li className={"options-list-item"} onClick={handleEdit} title={"Edit Message"}>
                        <FontAwesomeIcon icon={['fas', 'pencil-alt']} size={"lg"} className={"message-options"}/>
                    </li>
                    {props.edit ?
                        <li className={"options-list-item"}><FontAwesomeIcon icon={['fas', 'times']} size={"lg"}
                                                                             onClick={handleEdit}/></li>
                        :
                        <li className={"options-list-item delete"} title={"Delete Message"}>
                            <FontAwesomeIcon icon={['fas', 'trash-alt']} size={"lg"} className={"message-options"}/>
                        </li>
                    }

                </ul>
            </Div>
        );
    }


    const UserInfoContainer = (props) => {
        return (
            <Div c="username-container">
                <Div c="bottom-container">
                    <Div c="username">{username}</Div>
                    <Div c="kw">&nbsp;at&nbsp;</Div>
                    <Div c="time">{time}</Div>
                    <Div c="kw">&nbsp;on&nbsp;</Div>
                    <Div c="date">{date}</Div>
                    {/*TODO check if the date is modified header to display it. */}
                    {isModified?<Div c={"edited"}>&nbsp; <a title={"hello"}>(edited)</a>&nbsp;</Div>:""}
                </Div>
            </Div>
        );
    }

    return (

        <Div c={`message ${type}`}>

            {/*<EditMessageBox/>*/}
            {type === "others" ? "" : <MessageOptions {...editMode} edit={editMode}/>}

            <Div c="message-container">
                <Div c="content-container">
                    <div className={`${editMode ? "edit" : "content"}`} ref={contentRef} contentEditable="true">
                        {editMode ? <EditMessageBox handleEdit={handleEdit} content={props.content}/> : Content}
                    </div>
                </Div>
                {editMode ? "" : <UserInfoContainer {...props} />}

            </Div>
        </Div>
    );
};

export default Message;
