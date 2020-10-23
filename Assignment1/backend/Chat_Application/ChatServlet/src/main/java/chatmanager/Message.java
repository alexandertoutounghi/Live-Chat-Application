package chatmanager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message implements Comparable<Message> {
    private String date;
    private String time;
    private String user;
    private String message;
    private ZonedDateTime dateTime;

    public Message(String date, String time, String user, String message) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.message = message;
    }

    public Message(ZonedDateTime dateTime, String user, String message) {
        this.dateTime = dateTime;
        this.user = user;
        this.message = message;
        this.date = dateTime.format(DateTimeFormatter.ofPattern("MM/dd/uuuu"));
        this.time = dateTime.format(DateTimeFormatter.ofPattern("h:mm:ss a"));

    }

    public Message(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
//        compareto returns if its less than ore
//        return ((this.dateTime.getYear() == message1.dateTime.getYear()) &&
//                (this.dateTime.getMonthValue()  == message1.dateTime.getMonthValue()) &&
//                (this.dateTime.getDayOfMonth() ==  message1.dateTime.getDayOfMonth()) &&
//                (this.dateTime.getHour() <= message1.dateTime.getHour()) &&
//                (this.dateTime.getMinute() <= message1.dateTime.getMinute()) &&
//                (this.dateTime.getSecond() <= message1.dateTime.getSecond()));
        int result = this.dateTime.compareTo(message1.dateTime);
        return (result < 1 || this.dateTime.equals(message1.dateTime));
    }

    @Override
    public int compareTo(Message o) {
        return o.dateTime.compareTo(this.dateTime);
    }

    @Override
    public String toString() {
        return date + ", " + time + " - " + user + ": " + message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

}
