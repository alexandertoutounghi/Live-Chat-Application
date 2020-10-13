import React, {useEffect, useState} from 'react';
import CustomDate from "./CustomDate/CustomDate";
import "./Modal.scss";

const Modal = (props) => {

    return (
        <div className={`bg-modal ${props.darkMode}`}>
            <div className="modal-content">
                <div className="close" onClick={props.onClose}>+</div>
                <CustomDate darkMode={props.darkMode}/>
            </div>
        </div>
    );
}

export default Modal;