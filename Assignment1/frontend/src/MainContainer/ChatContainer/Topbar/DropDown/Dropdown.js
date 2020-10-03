import React, {useState} from 'react';
import {faDownload,faCaretDown, faCaretUp} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import "./Dropdown.scss"
const Dropdown = (props) => {
    const [dropdown,setDropdown] = useState(false)

    const handleClick = () => {
        setDropdown(!dropdown)
    }

    return (
        <div className={`dropdown-container `}>
            {/*<ul>*/}
            {/*    <li></li>*/}
            {/*</ul>*/}
            <div className="dropdown-icon" onClick={handleClick}><FontAwesomeIcon icon={faDownload}/> Chat Messages <FontAwesomeIcon icon={dropdown?faCaretUp:faCaretDown}/></div>
            {dropdown?<div className="dropdown-menu">
                <ul className="dropdown-menu-list">
                    <li className="dropdown-menu-item">Today</li>
                    <li className="dropdown-menu-item">Yesterday</li>
                    <li className="dropdown-menu-item">Custom...</li>
                </ul>
            </div>:<div></div>}
        </div>
    );
}

export default Dropdown;
