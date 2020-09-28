import React from "react";
import "./TopBar.scss"

const Topbar = (props) => {
    return (
        <div className="topbar">
            <nav className="navbar-nav">
                <ul className="nav-item">
                    <li className="nav-list left">Dropdown</li>
                    <ul className="nav-list right">
                        <li className="">Dark Mode</li>
                        <li className="logout"><button>Logout</button></li>
                    </ul>
                </ul>
            </nav>
        </div>);
};

export default Topbar;
