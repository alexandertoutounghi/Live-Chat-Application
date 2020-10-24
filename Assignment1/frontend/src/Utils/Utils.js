import React from "react";

export const Div = (props) => {
    const {c, ...rest} = props;
    return <div className={c} {...rest}></div>;
};
const url = "http://localhost:3000/ChatServlet_war/ChatServlet";
// const download = "http://localhost:3000/ChatServlet_war/download.jsp";


export const sendData = async (keyValuePairs) => {
    let queryString = "";
    Object.entries(keyValuePairs).forEach((([key, val], index) => {
        queryString += `${key}=${encodeURIComponent(val)}${index !== Object.keys(keyValuePairs).length - 1 ? '&' : ''}`
    }));
    console.log(queryString);
    const req = await fetch(`${url}?${queryString}`, {
            method: 'POST',
            headers: {
                'Accept': 'application/x-www-form-urlencoded',
                'contentType': 'application/x-www-form-urlencoded',
            },
            url: "/ChatServlet"
        }
    );
    const res = await req.text();
    return res;
}

export const getData = async (keyValuePairs) => {
    let queryString = "";
    Object.entries(keyValuePairs).forEach((([key, val], index) => {
        queryString += `${key}=${encodeURIComponent(val)}${index !== Object.keys(keyValuePairs).length - 1 ? '&' : ''}`
    }));
    console.log(queryString);
    const response = await fetch(`${url}?${queryString}`, {
            method: 'GET',
            headers: {},
            url: "/download.jsp"
        }
    )

    return response;
}

// export const getMessage = async (format, from, to) => {
//     console.log(from);
//     console.log(to);
//     const response = await getData({format: format, from: from, to: to, download: "download"});
//     console.log(response);
//
//     const filename = response.headers.get('Content-Disposition').split('filename=')[1];
//     response.blob().then(blob => {
//         let url = window.URL.createObjectURL(blob);
//         let a = document.createElement('a');
//         a.href = url;
//         a.download = filename;
//         a.click();
//     });
// }
