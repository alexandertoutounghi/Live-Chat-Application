import React from "react";
import {useState, useContext, useEffect, useRef} from "react";
import TextBox from "../ChatContainer/Chatbox/Textbox";
import {Div, sendData} from "../../Utils/Utils";
import "../../Styles/Utils.scss";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import "./Login.scss";
import {useForm} from "react-hook-form";
import {Redirect, Link} from "react-router-dom";
import Logo from "../Logo";


const Login = (props) => {
    const {handleLogin} = props;
    const {register, handleSubmit, errors} = useForm();

    // const onSubmit = async (data) => {
    const onSubmit = (data) => {
        // const response = await sendData(data);
        // if (response === "found_account") {
        //     localStorage.setItem("Username",data.username);
        // }
        // else {
        //     alert("Server rejected your login...")
        // }
        // sessionStorage.setItem("Username",data.username);
        handleLogin(data);
        // redirect("/chat");
        // window.location.reload(true);


    }


    return (
        <Div>
            {sessionStorage.getItem("Username") !== null && <Redirect to={"/"}/>}
            <Logo/>
            <Div c="login-container flex-col">
                <div class="login-items">
                    <h1>Member Login</h1>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <div className={"welcome-msg"}>Please Sign in to Continue...</div>
                        <input id={"username"} type="email" placeholder="Email" name={"username"}
                               ref={register({required: true})}/>
                        {/*<input id={"username"} type="email" placeholder="Email"  name={"username"} />*/}
                        <input type="password" placeholder={"Password"} name={"password"}
                               ref={register({required: true})}/>
                        {(errors.Password || errors.Email) && <p>Invalid username or password</p>}
                        <button type="submit" className={"login-btn"}>Log In</button>
                    </form>
                </div>
            </Div>
        </Div>
    );
};

export default Login;
