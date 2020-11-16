import React from 'react';
import CustomDate from "./DropDown/Modal/CustomDate/CustomDate";

function Searchbar(props) {
    const {userList} = props;
    return (
        <div>
            {/*<input type="search"/>*/}
            <select name="users" id="" multiple>
                <option value="All Users" selected="selected">All Users</option>
                {userList.map((user) =>
                    <option values={user.name}>{user.name}</option>
                )}
            </select>
            <CustomDate/>
            <select name="" id=""></select>
        </div>
    );
}

export default Searchbar;