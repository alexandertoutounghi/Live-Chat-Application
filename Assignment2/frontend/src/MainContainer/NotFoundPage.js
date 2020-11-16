import React from 'react';
import Logo from "./Logo";
import "./NotFoundPage.scss"

function NotFoundPage(props) {
    const errmsg = "Postit 404";
    return (
        <div>
            <Logo/>
            <div className={"msg"}>
                <div className="description">
                    <h1>Page Not Found</h1>
                    <h3>We're sorry, the requested URL {props.location.pathname} could not be found on this server.</h3>
                </div>
            </div>

        </div>
    )
        ;
}

export default NotFoundPage;