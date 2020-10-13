import React from "react";

export const Div = (props) => {
  const { c, ...rest } = props;
  return <div className={c} {...rest}></div>;
};
