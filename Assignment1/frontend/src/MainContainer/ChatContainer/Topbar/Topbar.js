import React, {useEffect, useState} from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
// import { faSun, faMoon } from '@fortawesome/free-regular-svg-icons'
import Dropdown from "./DropDown/Dropdown";
import "./TopBar.scss"



const Topbar = (props) => {
    const [darkMode, setDarkMode] = useState(false);

   const handleClick = () => {
       props.setHideTimes()
       setDarkMode(!darkMode);
   }
   const handleLogout = () => {
        props.setLoginPage(true);
   }
    return (
        <div className={`topbar${darkMode ? ' dark': ''}`}>
            <nav className="navbar-nav">
                <ul className="nav-item">
                    <li className="nav-list left laptop">
                        <Dropdown darkMode={darkMode} type={"download"} drop={"dropdown"}/>
                    </li>
                    <li className="nav-list center username">Welcome {props.username}</li>
                    <ul className="nav-list right">
                        <li><Dropdown darkMode={darkMode} type={"clear"} drop={"dropdown"}/></li>
                        <li onClick={handleClick} className="toggle-darkmode">{darkMode ? <FontAwesomeIcon icon={['fas', 'sun']} title={"Light Mode"} className="light-icon" color="white"/>: <FontAwesomeIcon icon={['fas', 'moon']} title={"Dark Mode"} className="dark-icon" color="white"/> }</li>
                        <li onClick={handleLogout}><button className="logout">Logout</button></li>
                    </ul>
                </ul>
            </nav>
        </div>);
};

export default Topbar;
