import React, {useEffect, useState} from 'react';
import {faDownload, faCaretDown, faCaretUp} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import "./Dropdown.scss"
import CustomDate from "./Modal/CustomDate/CustomDate";
import Modal from "./Modal/Modal";
import {getData, sendData} from "../../../../Utils/Utils";

const Dropdown = (props) => {
    const [dropdown, setDropdown] = useState(false)
    const [custom, setCustom] = useState(false)
    const [format, setFormat] = useState("text")
    const [to, setTo] = useState()
    const [from, setFrom] = useState()
    const handleClick = () => {
        setDropdown(!dropdown)
    }

    const handleClose = () => {
        setCustom(false);
    }

    const handleOpen = () => {
        setCustom(true);
        handleClick();
    }

    const customYesterday = async () => {
        var yesterdayTo = new Date() - 1;
        var yesterdayFrom = new Date() - 1;
        yesterdayFrom.setHours(0, 0, 0, 0);
        yesterdayTo.setHours(23, 59, 59, 99);
        getMessage(yesterdayFrom.toUTCString(), yesterdayTo.toUTCString());
    };

    const customToday = async () => {
        var todayTo = new Date();
        var todayFrom = new Date();
        todayFrom.setHours(0, 0, 0, 0);
        todayTo.setHours(23, 59, 59, 99);
        console.log(todayFrom.toLocaleString());
        getMessage(todayFrom.toLocaleString(),todayTo.toLocaleString());
    }

    const getMessage = async (from, to) => {
        console.log(from);
        console.log(to);
        const response = await getData({format: format, from: from, to: to});
        if (response !== "message_accepted") {
            alert("Your message couldn't be sent!")
        }
    };


    return (
        <div className={`dropdown-container ${props.darkMode}`}>
            <div className={dropdown ? "dropdown-icon dropdown-enabled" : "dropdown-icon"} onClick={handleClick}>
                <FontAwesomeIcon icon={props.type==="download"?faDownload:['fas', 'trash']}/> <span className={"md"}>{props.type} Messages</span>
                <FontAwesomeIcon icon={dropdown ? faCaretUp : faCaretDown} className={"md"}/>
                <FontAwesomeIcon className={"xs-right-caret-icon"} icon={['fas', 'caret-right']}/>
            </div>
            {dropdown ?
                <div className={`dropdown-menu ${props.drop}`}>
                    <ul className="dropdown-menu-list">
                        <li className={"xs bars"} onClick={handleClick}><FontAwesomeIcon icon={['fas', 'bars']}/></li>
                        <li className="dropdown-menu-item" onClick={customToday}>Today</li>
                        <li className="dropdown-menu-item" onClick={customYesterday}>Yesterday</li>
                        <li className="dropdown-menu-item" onClick={handleOpen}>Custom...</li>
                    </ul>
                </div> : ""}
            {custom ? <Modal onClose={handleClose} darkMode={props.darkMode}/> : ""}

        </div>
    );
}

export default Dropdown;

