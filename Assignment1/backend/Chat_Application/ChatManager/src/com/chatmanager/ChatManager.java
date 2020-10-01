package com.chatmanager;

// Use this package to get the date. More reliable than java.util.Date
import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatManager {

    private String user;
    private String message;
    private String userAndMsg;
    private List<String> list = new ArrayList<String>();

    // Used to keep count of position in list when iterating through the lists date
    private int dateRangeCount = 0;

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
    public void postMessage(String user, String message) {
        this.user = user;
        this.message = message;

        userAndMsg = date + " " + user + ": " + message;
        list.add(userAndMsg);
    }

    // If no username is entered, set the name to anonymous
    public void postMessage(String message) {
        this.message = message;

        userAndMsg = date + " " + "Anonymous" + ": " + message;
        list.add(userAndMsg);
    }

    // Print messages from a cetain date range, must enter date and time
    public void listMessages(String dateRange) {
        for (String msgDate : list) {
            dateRangeCount++;

            // If found a match, print all messages from the first message to the specified message date
            if (msgDate.contains(dateRange)) {
                System.out.println(list.subList(0, dateRangeCount));
                break;
            }
            else {
                System.out.println("No matching date string found, please enter another");
            }

        }
    }

    // Print the entire list
    public void listMessages() {
        for(String message:list) {
            System.out.println(message);
        }
    }

    public int getListSize() {
        return list.size();
    }


}
