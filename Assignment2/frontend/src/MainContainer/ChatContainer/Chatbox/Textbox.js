import React, {useState} from "react";
import {useRef, useEffect} from "react";
import {Div} from "../../../Utils/Utils";
import "./Textbox.scss";
import "../../../Styles/Utils.scss";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useForm} from "react-hook-form";

const Textbox = (props) => {
    const {register, handleSubmit, errors} = useForm();
    const [file, setFile] = useState(null);
    const [inputKey, setInputKey] = useState(new Date());
    // const [isFileWaiti]
    const onSubmit = (data) => {
        // if (data.File[0] !== undefined)


        console.log(data)
        console.log(data.File[0]);
        // setFile(data.File[0]);

        // var mystring = 'huehue #arebaba,saas #ole #cool asdsad #aaa',
        //     match;
        // var regexp = /#(\w+)/g;
        // while (match = regexp.exec( mystring))
        //     console.log("#"+match[1]);
        var my_list = getHashTag(data.message)
        console.log(my_list)


    }

    const getHashTag = (text) => {
        var list= []
        var match
        var regexp = /#(\w+)/g;
        while (match = regexp.exec(text))
            list.push("#"+match[1]);
        return list
    }

    const handleFileChange = (e) => {
        // console.log("hello");
        // console.log(e.target.value);
        // e.target.value = ""
        // console.log(e.target.files[0].name);
        setFile(e.target.files[0].name);

    }


    // useEffect(() => {
    //  window.addEventListener("onEdit",console.log("hello world"))
    //
    //   return () => {
    //     window.removeEventListener('onEdit');
    //   };
    // },[])

    const {type, placeholder, buttonContent, onClick} = props;
    const refs = {
        input: useRef(null),
        button: useRef(null),
    };
    const getRef = (refName) => refs[refName].current;
    const textarea = type === "textarea";

    const setClass = (str) =>
        (getRef("button").className = `textbox-button ${str}`);
    const handlers = {
        previousVal: "",
        isShiftActive: false,
        onSend: (val = getRef("input").value) => {
            console.log(val, type);
            if (textarea && val === "") {
                return;
            }
            refs.input.current.value = "";
            setClass("unavailable");
            onClick(val);
        },
        onKeyDown: (e, k = e.key) => {
            console.log(k);
            if (k === "Shift") handlers.isShiftActive = true;
            else if (k === "Enter") {
                e.preventDefault();
                handlers.onSend();
            }
        },
        onKeyUp: (e, k = e.key) => {
            if (k === "Shift") handlers.isShiftActive = false;
        },
        onChange: (e, val = e.target.value) => {
            console.log("changed");
            textarea && setClass(val === "" ? "unavailable" : "");
        },
    };

    useEffect(() => {
        console.log("mounted");
        // refs.input.current.focus();
    }, []);

    return (
        <Div c="textbox flex-row">
            <div className="uploader">
                <label htmlFor="file-upload" className="upload" title={"Upload a File"} ref={register}>
                    <FontAwesomeIcon icon={['fas', 'file-upload']} color="black" size="2x"/>
                </label>
                <input id="file-upload" key={file} type="file" ref={register} name={"File"}
                       onChange={handleFileChange}/>
                {<h6>{file}</h6>}
            </div>

            {file !== null &&
                <div className="remove-upload">

                    <FontAwesomeIcon icon={['fas', 'minus-circle']} color="black" size="md" onClick={() => setFile(null)} title={"remove document"}/>
                </div>

            }

            <Div c="textbox-wrapper">
                {((
                    h = handlers,
                    props = {
                        // ref: refs.input,
                        className: "text-input",
                        onKeyDown: h.onKeyDown,
                        onKeyUp: h.onKeyUp,
                        placeholder,
                    }
                ) =>
                    type === "input" ? (
                        <input type="text" {...props} name="someothertext" ref={register}/>
                    ) : (
                        <textarea name="message" ref={register} onChange={handlers.onChange} {...props} />
                    ))()}

                <div
                    ref={refs.button}
                    // ref={register}
                    className={`textbox-button${
                        type === "textarea" ? " unavailable" : ""
                    }`}
                    onClick={handleSubmit(onSubmit)}

                    // onClick={() => handlers.onSend()}
                >
                    {buttonContent}
                </div>

            </Div>
        </Div>
    );
};

export default Textbox;
