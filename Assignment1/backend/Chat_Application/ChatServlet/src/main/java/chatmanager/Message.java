package chatmanager;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Message {
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
    }
    public Message(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return this.dateTime.equals(message1.dateTime);
    }

    @Override
    public String toString() {
        return "Message{" + date + ", " + time +  " - "+ user + ": " + message;
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
