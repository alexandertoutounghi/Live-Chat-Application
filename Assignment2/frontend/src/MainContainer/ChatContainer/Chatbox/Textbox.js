import React from "react";
import { useRef, useEffect } from "react";
import { Div } from "../../../Utils/Utils";
import "./Textbox.scss";
import "../../../Styles/Utils.scss";

const Textbox = (props) => {
  // useEffect(() => {
  //  window.addEventListener("onEdit",console.log("hello world"))
  //
  //   return () => {
  //     window.removeEventListener('onEdit');
  //   };
  // },[])

  const { type, placeholder, buttonContent, onClick } = props;
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
    refs.input.current.focus();
  }, []);

  return (
    <Div c="textbox flex-row">
      <Div c="textbox-wrapper">
        {((
          h = handlers,
          props = {
            ref: refs.input,
            className: "text-input",
            onKeyDown: h.onKeyDown,
            onKeyUp: h.onKeyUp,
            placeholder,
          }
        ) =>
          type === "input" ? (
            <input type="text" {...props} />
          ) : (
            <textarea onChange={handlers.onChange} {...props} />
          ))()}
        <div
          ref={refs.button}
          className={`textbox-button${
            type === "textarea" ? " unavailable" : ""
          }`}
          onClick={() => handlers.onSend()}
        >
          {buttonContent}
        </div>
      </Div>
    </Div>
  );
};

export default Textbox;
