import React, {useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import styled, {useTheme} from "styled-components";
import {
    faSun,
    faMoon,
    faDownload,
    faTrash,
    faChevronCircleDown,
} from "@fortawesome/free-solid-svg-icons";
import Dropdown from "./DropDown/Dropdown";
import "./TopBar.scss";
import Searchbar from "./Searchbar";
import {Redirect} from "react-router-dom";
import CustomDate from "./DropDown/Modal/CustomDate/CustomDate";
import DateRange from "./DateRange";
import DatePicker from 'react-datepicker';

import {Controller, useForm} from "react-hook-form";

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
    padding:1.5rem;
    .chevron-down {
      width: 70px;
      height: 70px;
    }
    &:hover {
      opacity: 0.8;
    }
  }
  
  .dropdown-container {
    transition: 0.4s ease;
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
    justify-content:center;
    justify-items:center;
    padding-left:8rem;
    padding-right:1rem;
    
   

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
   @media screen and (max-width: 640px) {
    .dropdown-container {
      width: calc(100% - 48px) !important;
      min-height: calc(100% - 300px);
      max-height: calc(100% - 200px)
      height: fit-content;
      top: 120px;
      left: 20px;
      border: none;
    }
  }
  &.show {
    .icon-container {
      // transform: rotate(-180deg);
      background-color:black;
      border-radius:50%;
      transition: 0.15s ease-in-out;
    }
  }
`;

const CaretDropdown = (props) => {
    const {darkMode} = props;
    const [show, setShow] = useState(false);
    const [functionMenu, setFunctionMenu] = useState("download");
    const [numbMsg, setNumbMsg] = useState(10);
    const [startDate, setStartDate] = useState(new Date());
    const {register, handleSubmit, errors,control} = useForm();


    const handleClick = (type) => {
        setFunctionMenu(type);
    };

    React.useEffect(() => {
        console.log(sessionStorage.getItem("NumbMsgs"));
        setNumbMsg(sessionStorage.getItem("NumbMsgs"));
    }, []);

    const makeActionToggle = (type, icon) => {
        return (
            <div
                className={`action-toggle ${functionMenu === type ? "active" : ""}`}
                onClick={() => handleClick(type)}
            >
                <FontAwesomeIcon icon={icon}/>
                <span className={"md"}>
          {` ${type[0].toUpperCase()}${type.slice(1)} `}
                    Messages
        </span>
            </div>
        );
    };

    const handleIncrement = () => {
        var numb = Number(sessionStorage.getItem("NumbMsgs"));
        numb += 1;
        setNumbMsg(numb);
        sessionStorage.setItem("NumbMsgs", numb);
    }
    const handleDecrement = () => {
        var numb = Number(sessionStorage.getItem("NumbMsgs"));
        numb -= 1;
        setNumbMsg(numb);
        sessionStorage.setItem("NumbMsgs", numb);
    }


    const onSubmit = (data) => {
        console.log(data);
        // const response = await sendData(data);
        // if (response === "found_account") {
        //     localStorage.setItem("Username",data.username);
        // }
        // else {
        //     alert("Server rejected your login...")
        // }
        // sessionStorage.setItem("Username",data.username);
        // handleLogin(data);
        // redirect("/chat");
        // window.location.reload(true);


    }
    return (
        <div className={"left-menu"}>
            <StyledCaretDropdown
                className={`${darkMode ? "dark" : "light"} ${show ? "show" : "hide"}`}
                tabIndex={1}
                // onBlur={() => setShow(false)}
            >
                <div className={`icon-container`} onClick={() => setShow(!show)}>
                    <FontAwesomeIcon icon={['fas', 'search']} size="2x" color={"white"}/>
                </div>
                {show && (
                    <div className="dropdown-container">
                        {/*<form>*/}
                            <div className="dropdown-items"><h3>Search By...</h3></div>
                            <div className="dropdown-items">
                                <h3>Users</h3>
                                <select name="users" id="" ref={register}>
                                    <option value="" defaultValue={true}>Select an Option</option>
                                </select>
                            </div>
                            <div className="dropdown-items">
                                <h3>Date</h3>
                                {/*<DateRange register={register}/>*/}
                                <Controller
                                    as={DatePicker}
                                    control={control}
                                    selected={startDate}
                                    onChange={date => setStartDate(date)}
                                    timeInputLabel="Time:"
                                    dateFormat="dd.MM.yyyy h:mm aa"
                                    showTimeInput
                                    selectsStart
                                    startDate={startDate}
                                    popperModifiers={{
                                        preventOverflow: {
                                            enabled: true,
                                        },
                                    }}


                                    // valueName="selected" // DateSelect value's name is selected
                                    // onChange={([selected]) => selected}
                                    name="ReactDatepicker"
                                    // className="input"
                                />
                                {/*<Controller/>*/}
                            </div>

                            <div className="dropdown-items">
                                <h3>Hashtags</h3>
                                <select name="hashtag" id="" ref={register}>
                                    <option value="" defaultValue={true}>Select an Option</option>
                                </select>
                            </div>

                            <div className="dropdown-items">
                                <button type="submit" onClick={handleSubmit(onSubmit)}>Search</button>
                            </div>
                        {/*</form>*/}


                        {/*<div>*/}
                        {/*    <option value="">Users</option>*/}
                        {/*</div>*/}
                        {/*<div>*/}
                        {/*    <option value="">HashTags</option>*/}
                        {/*    <CustomDate/>*/}
                        {/*</div>*/}

                        {/*<div className={`actions-container`}>*/}
                        {/*  {makeActionToggle("download", faDownload)}*/}
                        {/*  {makeActionToggle("clear", faTrash)}*/}
                        {/*</div>*/}
                        {/*<div className="function-container">*/}
                        {/*  {functionMenu === "download" ? (*/}
                        {/*    <Dropdown*/}
                        {/*      darkMode={darkMode}*/}
                        {/*      active={true}*/}
                        {/*      type={"Download"}*/}
                        {/*      drop={"dropdown"}*/}
                        {/*    />*/}
                        {/*  ) : (*/}
                        {/*    <Dropdown*/}
                        {/*      active={true}*/}
                        {/*      darkMode={darkMode}*/}
                        {/*      type={"Delete"}*/}
                        {/*      drop={"dropdown"}*/}
                        {/*    />*/}
                        {/*  )}*/}
                        {/*</div>*/}
                    </div>
                )}
            </StyledCaretDropdown>
            <div className={"msg-container"}>
                <h6>Display Messages</h6>
                <div className={"message-val"}>
                    <FontAwesomeIcon icon={['fas', 'minus-square']} className={"dec"}
                                     size={"lg"} onClick={handleDecrement}/>
                    <input type="text" value={numbMsg}/>
                    {/*<span className="message-range">10</span>*/}

                    <FontAwesomeIcon icon={['fas', 'plus-square']} className="inc" color="gray"
                                     size={"lg"} onClick={handleIncrement}/>
                    {/*  <p>Display Messages</p>*/}

                </div>
            </div>
        </div>
    );
};

const Topbar = (props) => {
    const {darkMode, setDarkMode} = props;
    const handleClick = () => {
        setDarkMode(!darkMode);
    };

    console.log(props.username);
    const handleLogout = () => {
        sessionStorage.removeItem("Username");
        sessionStorage.removeItem("NumbMsgs");

        // <Redirect to="/"/>
        window.location.reload(true);
        props.setLoginPage("");
    };
    return (
        <div className={`topbar ${darkMode ? "dark" : ""}`}>
            <div className="navbar-nav">
                {/*<Searchbar/>*/}
                <CaretDropdown darkMode={darkMode}/>
                <div className="username">Hello, {sessionStorage.getItem("Username")}</div>
                <div style={{width: "fit-content"}}>
                    <ul className="nav-list right">
                        {/*<li>*/}
                        {/*  <Dropdown darkMode={darkMode} type={"Delete"} drop={"dropdown"} />*/}
                        {/*</li>*/}


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
