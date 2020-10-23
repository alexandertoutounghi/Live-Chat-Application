import React from 'react';
import "./Toggle.scss"

const Toggle = (props) => {
    return (
        <div className="center">
            <ul>
                <li> Format <strong>{props.format}</strong>
                </li>
                <li> <input onClick={props.toggle} className={"toggle"} type="checkbox"/></li>
                <li></li>
            </ul>
        </div>
    );
}

export default Toggle;