package chatmanager;

public class Message {
    private String date;
    private String time;
    private String user;
    private String message;

    public Message(String date, String time, String user, String message) {
        this.date = date;
        this.time = time;
        this.user = user;
        this.message = message;
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
