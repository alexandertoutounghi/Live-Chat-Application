package com.chatmanager;

// Use this package to get the date. More reliable than java.util.Date
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ChatManager {

    private String user;
    private String message;

    // This handles the UTC formatting
    ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
    String date = utc.format(DateTimeFormatter.ofPattern("MM/dd/uuuu h:mm:ss a"));

    public ChatManager() {
        user = "Anonymous";
        message = "";
    }

    public ChatManager(String user, String message) {
        this.user = user;
        this.message = message;
    }

    // Return a username with an associated message and date
    public String postMessage(String user, String message) {
        this.user = user;
        this.message = message;

        return date + " " + user + ":" + message;
    }

    // If no username is entered, set the name to anonymous
    public String postMessage(String message) {
        return postMessage("Anonymous", message);
    }


}
