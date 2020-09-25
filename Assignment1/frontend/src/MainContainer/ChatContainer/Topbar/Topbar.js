import React from "react";
import { Div } from "../../../Utils/Utils";
import "../../../Styles/Utils.scss";
import "./TopBar.scss";
import xbutton from "../../../Icons/xbutton.png";

const Topbar = (props) => {
  const { setLoginPage } = props;
  return (
    <Div c="topbar-container">
      <Div c="left-container"></Div>
      <Div c="right-container">
        <Div c=" button-container nightmode">
          {/* <img src="../../../../Icons/xbutton.jpg" alt='xbutton'/> */}
        </Div>
        <Div
          c="button-container close flex-col"
          onClick={() => setLoginPage(true)}
        >
          <img src={xbutton} alt="xbutton" />
        </Div>
      </Div>
    </Div>
  );
};

export default Topbar;
