// import React, {useState} from "react";
import React, {useState, CSSProperties, useEffect, useRef} from "react";
// import {useRef} from "react";
import {Div} from "../../../Utils/Utils";
import "./Message.scss";
import "../../../Styles/Utils.scss";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import EditMessage from "./EditMessage";
import EditMessageBox from "./EditMessageBox";


const Message = (props) => {
    const {type, content, date, time, username, attachment} = props;
    //edited.
    const [editMode, setEditMode] = useState(false);
    const [isModified, setIsModified] = useState(false);
    const [Content, setContent] = useState(content);
    const [EditName, setEditName] = useState("Filename")
    const [EditSize, setEditSize] = useState("FileSize.kB");
    const [fileEdited, setfileEdited] = useState(false)
    const [hasafile,setHasAFile] = useState(true)
    const [focus,setFocus] = useState(false)

    // const hashcode,username,etc.

    const contentRef = useRef(null);

    const editor = () => {
        // console.log("hello");
        // props.edit(content);
        contentRef.current.focus();
        contentRef.current.classList.add("editing")
    }

    onkeydown = (e) => {
        console.log("focus",focus)

        if (e.key === "Escape") {
            setEditMode(false);
            setfileEdited(false);
            //TODO this would be a prop loaded from the server
            setEditName("Filename")
            //TODO this would be a prop loaded from the server
            setEditSize("FileSize.kB")

        }
        else if (e.key === "Enter" && !e.shiftKey) {
            if (!focus)
                return
            e.preventDefault()
            //if the text or the file has been changed
            if (editMode && ((e.target.value !== content) || (fileEdited===true)))  {
                //    send the content to the business layer
                //    modify the date
                console.log(e.target.value)
                console.log("hellooo")

                //TODO send to server the necessary info
                //if text edited only --> send only text
                //if file edited only --> only file
                //if both text and file edited -->send both

                setEditMode(false);
                //TODO get from server if modified passed down as a prop
                setIsModified(true);
                setContent(e.target.value);




            }
            else {
                setEditMode(false);
            }
            // console.log(isModified)
        }
    }


    //handler
    const handleClose = () => {
        // contentRef.current.blur();
        // contentRef.current.classList.remove("editing")
    }


    const handleEdit = () => {
        setEditMode(!editMode);
        // contentRef.current.classList.add("editing")
    }

    const handleDelete = () => {
        //file was already changed
        if (fileEdited) {
            setEditName("Filename")
            setEditSize("FileSize.kB");
        }
        // else

    }

    const MessageOptions = (props) => {
        return (
            <Div c="user-options">

                <ul className={"options-list"}>
                    {/*<li className={"options-list-item"} title="More Options">*/}
                    {/*    <FontAwesomeIcon icon={['fas', 'ellipsis-h']} size={"lg"} className={"message-options"}/>*/}
                    {/*</li>*/}
                    <li className={"options-list-item"} onClick={handleEdit} title={"Edit Message"}>
                        <FontAwesomeIcon icon={['fas', 'pencil-alt']} size={"lg"} className={"message-options"}/>
                    </li>
                    {props.edit ?
                        <li className={"options-list-item"}><FontAwesomeIcon icon={['fas', 'times']} size={"lg"}
                                                                             onClick={()=>setEditMode(false)} title="Cancel"/></li>
                        :
                        <li className={"options-list-item delete"} title={"Delete Message"}>
                            <FontAwesomeIcon icon={['fas', 'trash-alt']} size={"lg"} className={"message-options"} />
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
                    {isModified===true? <Div c={"edited"}>&nbsp; <a title={"hello"}>(edited)</a>&nbsp;</Div> : ""}
                </Div>

            </Div>
        );
    }


    const handleFileEdit = (e) => {
        setEditName(e.target.files[0].name)
        const size = e.target.files[0].size
        setfileEdited(true)

        if (size < 100)
            setEditSize(size+" bytes")
        // else if (Math.round(size/1024) < 1000)
        //     console.log(Math.round(size/1024)," MB")
        else if ((size >  100) && (Math.round(size/(1024)) < 100))
            setEditSize((size/1024).toFixed(1)+ " KB")
        else
           setEditSize((size/1024/1024).toFixed(1) + " MB")

        // console.log(Math.round(size/1024/1024),"MB")




        // console.log(e.target.files[0].size/1024/1024, "MB")
        //file has been edited
        // setfileEdited(true)
        // setEditName(e.target.File[0]);
        // setEd



    }


    return (

        <Div c={`message ${type}`}>

            {/*<EditMessageBox/>*/}
            {type === "others" ? "" : <MessageOptions {...editMode} edit={editMode}/>}

            <Div c="message-container">
                <Div c="content-container">
                    <div className={`${editMode ? "edit" : "content"}`}  contentEditable="true">
                        {editMode ? <EditMessageBox  {...{setFocus,content,handleEdit}}/> : Content}
                    </div>

                </Div>

                {editMode ? "" : <UserInfoContainer {...props} />}

                {hasafile &&
                <div className={"file-attachment"}>
                    <FontAwesomeIcon icon={['fa', 'file-alt']} size={"2x"}/>
                    <span>
                        <div className={"file-name"}>{EditName}</div>
                        <div className={"file-size"}>{EditSize}</div>
                    </span>
                    {!editMode &&
                    <FontAwesomeIcon icon={['fa', 'download']} className={"file-download-icon"} size={"2x"} title={"download..."}/>}

                    <div className={"edit-file-container"}>
                        {editMode &&
                        <div>
                            <label htmlFor="file-edit-attachment">
                                <FontAwesomeIcon icon={['fa', 'pencil-alt']} size={"md"} title="Edit Attachment"
                                                 className={"edit-attachment"}/>
                            </label>
                            <input id="file-edit-attachment" type="file" name={"File"}
                                   onChange={handleFileEdit}/>


                            <FontAwesomeIcon icon={['fa', 'trash-alt']} size={"md"} title="Delete Attachment"
                                             className="delete-attachment" onclick={handleDelete}/>
                        </div>
                        }
                    </div>



                </div>}

            </Div>

        </Div>
    );
};

export default Message;
