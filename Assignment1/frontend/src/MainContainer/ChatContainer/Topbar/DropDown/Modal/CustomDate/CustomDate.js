import React, {useState} from 'react';
import "./CustomDate.scss";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

const CustomDate = (props) => {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date)
    const DateButton = ({Range, value, onClick}) => (
        <React.Fragment>
            <label htmlFor="date" className={"calendar"}>{Range}</label>
            <li className="date-picker-item" onClick={onClick}>
                <FontAwesomeIcon icon={['far', 'calendar-alt']} color={"#4c8bf5"} className={"calendar"}/>
                {value}
            </li>
        </React.Fragment>
    );


    return (
        <form className={`custom-form ${props.darkMode}`} method="post">
            <h2>Date Range</h2>
            <ul className={"date-list"}>
                <li className={"date-list-item"}><h3>Choose a Date...</h3></li>
                <ul className={"date-picker"}>
                    {<DatePicker selected={startDate}
                                 onChange={date => setStartDate(date)}
                                 customInput={<DateButton Range={"From"}/>}
                                 timeInputLabel="Time:"
                                 dateFormat="dd.MM.yyyy h:mm aa"
                                 showTimeInput
                                 selectsStart
                                 startDate={startDate}
                                 endDate={endDate}
                                 popperModifiers={{
                                     preventOverflow: {
                                         enabled: true,
                                     },
                                 }}

                    />}

                    <li>
                        <hr/>
                    </li>
                    {<DatePicker selected={endDate}
                                 onChange={date => setEndDate(date)}
                                 customInput={<DateButton Range={"To"}/>}
                                 timeInputLabel="Time:"
                                 dateFormat="dd.MM.yyyy h:mm aa"
                                 showTimeInput
                                 selectsEnd
                                 startDate={startDate}
                                 endDate={endDate}
                                 minDate={startDate}
                                 popperModifiers={{
                                     preventOverflow: {
                                         enabled: true,
                                     },
                                 }}

                    />}
                </ul>
                <li className={"date-list-item"}>
                    <button className={"custom-submit"}>Submit</button>
                </li>
            </ul>
        </form>
    );
}

export default CustomDate;