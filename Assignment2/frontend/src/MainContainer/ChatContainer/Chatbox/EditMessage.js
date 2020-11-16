import React, {useState} from "react";

const EditMessage = (props) => {
    // const {content} = props;
    const [editedText, setEditedText] = useState(props.content);

    const handleChange = (e) => {
        // if ()
        setEditedText(e.target.value);
        // console.log("hello");

    }


    return (
        <div>
            <textarea name="" id="" cols="1" rows="1" onChange={handleChange}>
               {props.content}
            </textarea>
            <p className={"edit-menu"}>Escape to <span onClick={props.handleEdit} className={"edit-options cancel"}>cancel</span> &bull; enter
                to <span className="edit-options">save</span></p>
        </div>)
}

export default EditMessage;