import React, { useEffect, useState } from "react";
import {
  faDownload,
  faCaretDown,
  faCaretUp,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "./Dropdown.scss";
import Modal from "./Modal/Modal";
import { getData, sendData, getMessage } from "../../../../Utils/Utils";
import Toggle from "./Toggle/Toggle";

const Dropdown = (props) => {
  const [dropdown, setDropdown] = useState(props?.active || false);
  const [custom, setCustom] = useState(false);
  const [format, setFormat] = useState("text");
  const [to, setTo] = useState();
  const [from, setFrom] = useState();
  const handleClick = () => {
    setDropdown(!dropdown);
  };

  const handleClose = () => {
    setCustom(false);
  };

  const handleOpen = () => {
    setCustom(true);
    handleClick();
  };

  const customYesterday = async () => {
    var yesterdayTo = new Date();
    yesterdayTo.setDate(yesterdayTo.getDate() -1);
    var yesterdayFrom = new Date();
    yesterdayFrom.setDate(yesterdayFrom.getDate() -1);
    yesterdayFrom.setHours(0, 0, 0, 0);
    yesterdayTo.setHours(23, 59, 59, 99);
    yesterdayFrom = yesterdayFrom.toLocaleString();
    yesterdayTo = yesterdayTo.toLocaleString();
    console.log(yesterdayTo);
    console.log(yesterdayFrom);
    if (props.type === "Download") getMessage(yesterdayFrom, yesterdayTo);
    else deleteMessage(yesterdayFrom, yesterdayTo);
  };

  const customToday = async () => {
    var todayTo = new Date();
    var todayFrom = new Date();
    todayFrom.setHours(0, 0, 0, 0);
    todayTo.setHours(23, 59, 59, 99);
    todayFrom = todayFrom.toLocaleString();
    todayTo = todayTo.toLocaleString();
    if (props.type === "Download") getMessage(todayFrom, todayTo);
    else deleteMessage(todayFrom, todayTo);
  };

  const deleteMessage = async () => {
    const response = await getData({
      format: format,
      from: from,
      to: to,
      delete: "delete",
    });
  };

  const getMessage = async (from, to) => {
    const response = await getData({
      format: format,
      from: from,
      to: to,
      download: "download",
    });
    // console.log(from.toLocaleDateString());
    // console.log(to);
    // console.log(response);
    const filename = response.headers
      .get("Content-Disposition")
      .split("filename=")[1];
    response.blob().then((blob) => {
      let url = window.URL.createObjectURL(blob);
      let a = document.createElement("a");
      a.href = url;
      a.download = filename;
      a.click();
    });
  };

  const toggleFormat = () => {
    if (format === "text")
      setFormat("xml");
    else
      setFormat("text");
    console.log(format);
  };

  return (
    <div className={`dropdown ${props.darkMode}`}>
      {dropdown ? (
        <div className={`dropdown-menu ${props.drop}`}>
          <ul className="dropdown-menu-list">
            <li className={"xs bars"} onClick={handleClick}>
              <FontAwesomeIcon icon={["fas", "bars"]} />
            </li>
            {props.type === "Download" ? (
              <li>
                <Toggle toggle={toggleFormat} format={format} />
              </li>
            ) : (
              ""
            )}
            <li className="dropdown-menu-item" onClick={customToday} >
              Today
            </li>
            <li className="dropdown-menu-item" onClick={customYesterday}>
              Yesterday
            </li>
            <li className="dropdown-menu-item" onClick={handleOpen}>
              Custom...
            </li>
          </ul>
        </div>
      ) : (
        ""
      )}
      {custom ? (
        <Modal
          type={props.type}
          format={format}
          onClose={handleClose}
          darkMode={props.darkMode}
        />
      ) : (
        ""
      )}
    </div>
  );
};

export default Dropdown;
