import React, {
    useState,
    useEffect,
    useRef,
    TextareaHTMLAttributes,
} from "react";

const EditMessageBox = (props: TextareaHTMLAttributes<HTMLTextAreaElement>) => {
    const { setFocus ,content, handleEdit } = props
    const textAreaRef = useRef(null);
    const [text, setText] = useState("");
    const [textAreaHeight, setTextAreaHeight] = useState("auto");
    const [parentHeight, setParentHeight] = useState("auto");

    useEffect(() => {
        setParentHeight(`${textAreaRef.current.scrollHeight}px`);
        setTextAreaHeight(`${textAreaRef.current.scrollHeight}px`);

    }, [text]);

    const onChangeHandler = (event: React.ChangeEvent<HTMLTextAreaElement>) => {

        setTextAreaHeight("auto");

        setParentHeight(`${textAreaRef.current.scrollHeight}px`);
        setText(event.target.value);

        if (props.onChange) {
            props.onChange(event);
        }
    };


    return (
        <div
            style={{
                minHeight: parentHeight,
            }}
        >
			<textarea
                maxLength={"1000"}
                {...props}
                ref={textAreaRef}
                rows={1}
                style={{
                    height: textAreaHeight
                }}
                onChange={onChangeHandler}
                onKeyDown={props.onKeyDown}
                onFocus={() => setFocus(true) }
                onBlur={() =>  setFocus(false)}
                // onKeyUp={props.onKeyUp}
            >

                               {content}

            </textarea>
            <p className={"edit-menu"}>Escape to <span onClick={handleEdit}  className={"edit-options cancel"}>cancel</span> &bull; enter
                to <span className="edit-options">save</span></p>
        </div>
    );

};

export default EditMessageBox;

