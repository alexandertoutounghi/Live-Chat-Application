import React, {useState} from 'react';
import "./CustomDate.scss";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {getData} from "../../../../../../Utils/Utils";
// import {getMessage} from "../../../../../../Utils/Utils";


const CustomDate = (props) => {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const DateButton = ({Range, value, onClick}) => (
        <React.Fragment>
            <label htmlFor="date" className={"calendar"}>{Range}</label>
            <li className="date-picker-item" onClick={onClick}>
                <FontAwesomeIcon icon={['far', 'calendar-alt']} color={"#4c8bf5"} className={"calendar"}/>
                {value}
            </li>
        </React.Fragment>
    );


    const getMessage = async () => {
        const from = startDate.toLocaleString();
        const to = endDate.toLocaleString();
        console.log(startDate.toLocaleString());
        console.log(endDate.toLocaleString());
        console.log("type",props.type);
        console.log(props.format);
        if (props.type === "Download") {
            const response = await getData({format: props.format, from: from, to: to, download: "download"});
            console.log(response);
            const filename = response.headers.get('Content-Disposition').split('filename=')[1];
            response.blob().then(blob => {
                let url = window.URL.createObjectURL(blob);
                let a = document.createElement('a');
                a.href = url;
                a.download = filename;
                a.click();
            });
        }
        else {
            deleteMessage(from,to)
        }

    };

    const deleteMessage = async (from,to) => {
        const response = await getData({
            from: from,
            to: to,
            delete: "delete",
        });
    };

    return (
        <div className={`custom-form ${props.darkMode}`}>
            <h3>Date Range</h3>
            <ul className={"date-list"}>
                {/*<li className={"date-list-item"}><h3>Choose a Date...</h3></li>*/}
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
                {/*<li className={"date-list-item"}>*/}
                {/*    <button className={"custom-submit"} onClick={getMessage}>Submit</button>*/}
                {/*</li>*/}
            </ul>
        </div>
    );
}

export default CustomDate;