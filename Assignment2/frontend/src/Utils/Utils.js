import React from "react";

export const Div = (props) => {
  const { c, ...rest } = props;
  return <div className={c} {...rest}></div>;
};

const url = "http://localhost:3000/ChatServlet_war/";
const paths = {
  login: "/LoginServlet",
  fileupload: "/FileUploadServlet",
};

export const sendData = async (subUrl, keyValuePairs) => {
  console.log(JSON.stringify(keyValuePairs));
  const req = await fetch(`${url}${paths[subUrl]}`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      contentType: "application/json",
      dataType: "text",
    },
    body: JSON.stringify(keyValuePairs),
  });
  console.log(req.toString());
  const res = await req.text();
  console.log("RESPONSE", res);
  return res;
};

export const getData = async (keyValuePairs) => {
  let queryString = "";
  Object.entries(keyValuePairs).forEach(([key, val], index) => {
    queryString += `${key}=${encodeURIComponent(val)}${
      index !== Object.keys(keyValuePairs).length - 1 ? "&" : ""
    }`;
  });
  console.log(queryString);
  const response = await fetch(`${url}?${queryString}`, {
    method: "GET",
    headers: {},
    url: "/download.jsp",
  });

  return response;
};
