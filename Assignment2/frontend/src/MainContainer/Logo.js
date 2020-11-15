import React from 'react';
import {Div} from "../Utils/Utils";

const Logo = (props) => {
    const {errmsg} = props;
    console.log(errmsg);
    return (
        <Div c={`logo-container`}>
            <Div c="logo-wrapper">{errmsg===undefined?"Postit":errmsg}</Div>
        </Div>
    );
};


export default Logo;