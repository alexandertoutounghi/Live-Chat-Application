import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import styled, { useTheme } from "styled-components";
import {
  faSun,
  faMoon,
  faDownload,
  faTrash,
  faChevronCircleDown,
} from "@fortawesome/free-solid-svg-icons";
import Dropdown from "./DropDown/Dropdown";
import "./TopBar.scss";

const StyledCaretDropdown = styled.div`
//   position: absolute;
//   top: 0;
//   left: 0;
  height: 100px;
  width: 100px;
  display: flex;
  justify-content: center;
  outline: none;
  .icon-container {
    transition: 0.25s ease-in-out;
    cursor: pointer;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .chevron-down {
      width: 70px;
      height: 70px;
    }
    &:hover {
      opacity: 0.8;
    }
  }
  
  .dropdown-container {
    transition: 0.4 ease;
    position: absolute;
    top: 90px;
    left: 20px;
    width: 600px;
    height: fit-content;
    border-radius: 8px;
    background-color: white;
    border: white 2px solid;
    padding: 4px;
    display: flex;
    flex-direction: column;

    .actions-container{
        width 100%;
        height: fit-content;
        display: flex;
        .action-toggle{
            height: 40px;
            width: 50%;
            margin: 5px;
            border-radius: 5px;
            text-align: center;
            font-size: 24px;
            line-height: 40px;
        }

    }
    .function-container{
        width: 100%;
        height: fit-content;
        padding: 8px;
        .dropdown{
            width: calc(100% - 16px);
            height: fit-content;
            position: relative;
            top: 0;
            .dropdown-menu-list{
                margin-top:0;
                margin-bottom:0;
            }
        }

    }
  }
  &.show {
    .icon-container {
      transform: rotate(-180deg);
      transition: 0.15s ease-in-out;
    }
  }
`;

const CaretDropdown = (props) => {
  const { darkMode } = props;
  const [show, setShow] = useState(false);
  const [functionMenu, setFunctionMenu] = useState("download");

  const handleClick = (type) => {
    setFunctionMenu(type);
  };

  React.useEffect(() => {}, []);

  const makeActionToggle = (type, icon) => {
    return (
      <div
        className={`action-toggle ${functionMenu === type ? "active" : ""}`}
        onClick={() => handleClick(type)}
      >
        <FontAwesomeIcon icon={icon} />
        <span className={"md"}>
          {` ${type[0].toUpperCase()}${type.slice(1)} `}
          Messages
        </span>
      </div>
    );
  };

  return (
    <StyledCaretDropdown
      className={`${darkMode ? "dark" : "light"} ${show ? "show" : "hide"}`}
      tabIndex={1}
      // onBlur={() => setShow(false)}
    >
      <div className={`icon-container`} onClick={() => setShow(!show)}>
        <FontAwesomeIcon icon={faChevronCircleDown} size="2x" color={"white"} />
      </div>
      {show && (
        <div className="dropdown-container">
          <div className={`actions-container`}>
            {makeActionToggle("download", faDownload)}
            {makeActionToggle("clear", faTrash)}
          </div>
          <div className="function-container">
            {functionMenu === "download" ? (
              <Dropdown
                darkMode={darkMode}
                active={true}
                type={"Download"}
                drop={"dropdown"}
              />
            ) : (
              <Dropdown
                active={true}
                darkMode={darkMode}
                type={"Delete"}
                drop={"dropdown"}
              />
            )}
          </div>
        </div>
      )}
    </StyledCaretDropdown>
  );
};

const Topbar = (props) => {
  const { darkMode, setDarkMode } = props;
  const handleClick = () => {
    setDarkMode(!darkMode);
  };
  const handleLogout = () => {
    props.setLoginPage(true);
  };
  return (
    <div className={`topbar ${darkMode ? "dark" : ""}`}>
      <div className="navbar-nav">
        <CaretDropdown darkMode={darkMode} />
        <div className="username">Hello, {props.username}</div>
        <div style={{ width: "fit-content" }}>
          <ul className="nav-list right">
            <li>
              <Dropdown darkMode={darkMode} type={"Delete"} drop={"dropdown"} />
            </li>
            <li onClick={handleClick} className="toggle-darkmode">
              {darkMode ? (
                <FontAwesomeIcon
                  icon={["fas", "sun"]}
                  title={"Light Mode"}
                  className="light-icon"
                  color="white"
                />
              ) : (
                <FontAwesomeIcon
                  icon={["fas", "moon"]}
                  title={"Dark Mode"}
                  className="dark-icon"
                  color="white"
                />
              )}
            </li>
            <li onClick={handleLogout}>
              <button className="logout">Logout</button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Topbar;
