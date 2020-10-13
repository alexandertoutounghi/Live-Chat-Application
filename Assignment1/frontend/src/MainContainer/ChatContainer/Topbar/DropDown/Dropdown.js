import React, {useState} from 'react';
import {faDownload, faCaretDown, faCaretUp} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import "./Dropdown.scss"
import CustomDate from "./Modal/CustomDate/CustomDate";
import Modal from "./Modal/Modal";

const Dropdown = (props) => {
    const [dropdown, setDropdown] = useState(false)
    const [custom, setCustom] = useState(false)

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
    return (
        <div className={`dropdown-container ${props.darkMode}`}>
            <div className={dropdown ? "dropdown-icon dropdown-enabled" : "dropdown-icon"} onClick={handleClick}>
                <FontAwesomeIcon icon={faDownload}/> <span className={"md"}>Chat Messages</span>
                <FontAwesomeIcon icon={dropdown ? faCaretUp : faCaretDown} className={"md"}/>
                <FontAwesomeIcon className={"xs-right-caret-icon"} icon={['fas','caret-right']}/>
            </div>
            {dropdown ?
                <div className="dropdown-menu">
                    <ul className="dropdown-menu-list">
                        <li className={"xs bars"} onClick={handleClick}><FontAwesomeIcon icon={['fas','bars']}/></li>
                        <li className="dropdown-menu-item">Today</li>
                        <li className="dropdown-menu-item">Yesterday</li>
                        <li className="dropdown-menu-item" onClick={handleOpen}>Custom...</li>
                    </ul>
                </div> : ""}
            {custom ? <Modal onClose={handleClose} darkMode={props.darkMode}/> : ""}

        </div>
    );
}

export default Dropdown;
