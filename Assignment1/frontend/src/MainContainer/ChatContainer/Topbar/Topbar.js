import React, {useDebugValue, useState} from "react";
import "./TopBar.scss"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSun, faMoon } from '@fortawesome/free-regular-svg-icons'
import Dropdown from "./DropDown/Dropdown";

const Topbar = (props) => {
    const [darkMode,setDarkMode] = useState(true)
   const handleClick = () => {
        setDarkMode(!darkMode);
   }
    return (
        <div className={darkMode ? "topbar dark": "topbar light" }>
            <nav className="navbar-nav">
                <ul className="nav-item">
                    <li className="nav-list left">
                        <Dropdown/>
                    </li>
                    <li className="nav-list left"><h1>{props.username}</h1></li>
                    <ul className="nav-list right">
                        <li onClick={handleClick} className="">{darkMode ? <FontAwesomeIcon icon={faSun} title={"Light Mode"} className="light-icon" color="white"/> :<FontAwesomeIcon icon={faMoon} />}</li>
                        <li className="logout"><button>Logout</button></li>
                    </ul>
                </ul>
            </nav>
        </div>);
};

export default Topbar;
