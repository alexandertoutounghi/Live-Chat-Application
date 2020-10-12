package chatmanager;

// Use this package to get the date. More reliable than java.util.Date

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatManager implements Serializable {

    private String user;
    private String message;
    private String userAndMsg;
    private  List<String> list = new ArrayList<>();

    // Used for singleton pattern
    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
       return instance;
    }

    private ChatManager() {}

    // Return a username with an associated message and date
    public void postMessage(String user, String message) {

        this.user = user;
        this.message = message;

        userAndMsg = getDate() + " " + user + ":" + message;
        list.add(userAndMsg);
    }

    // If no username is entered, set the name to anonymous
    public void postMessage(String message) {

        this.message = message;

        userAndMsg = getDate() + " " + "Anonymous" + ":" + message;
        list.add(userAndMsg);
    }

    // Print messages from a cetain date range, must enter date and time. This reads an array from the servlet that contains two strings, from and to.
    public List<String> listMessages(String[] dateRange) {

        // Both parameters are empty, so just return the whole list
        if (dateRange[0] == "" && dateRange[1] == "") {
            list = listMessages();
        }

        // The "to" parameter is empty, so only use the from parameter
        else if (dateRange[1] == "") {
            for (int i = 0; i < listMessages().size(); i++) {
                if (listMessages().get(i).contains(dateRange[0])) {
                    this.list = list.subList(i, listMessages().size() - 1);
                }
            }

        }

        // The "from" parameter is empty, so only use the to parameter
        else if (dateRange[0] == "") {
            for (int i = 0; i < listMessages().size(); i++) {
                if (listMessages().get(i).contains(dateRange[1])) {

                    // Since sublists exclude the paramter in the toindex, we must add 1 to the end
                    this.list = list.subList(0, i + 1);
                }
            }
        }

        // Both parameters are entered, so create a sublist of our list that lies within the date range
        else {
            for (int i = 0; i < listMessages().size(); i++) {

                // First iterate through and get the first date value and set the zero index to the "from" parameter
                if (listMessages().get(i).contains(dateRange[0])) {
                    this.list = list.subList(i, listMessages().size() - 1);
                }

                // With our new index being the "from" date, now set the "to" date
                if (listMessages().get(i).contains(dateRange[1])) {
                    this.list = list.subList(0, i + 1);
                }
            }
        }
        return this.list;
    }
    
    
    public List<String> clearChat(String[] dateRange) {

        int toIndex = 0;
        int fromIndex = 0;

        if (dateRange[0] == "" && dateRange[1] == "") {
            list.clear();
        }

        else if (dateRange[1] == "") {
            for (int i = 0; i < listMessages().size(); i++) {
                if (listMessages().get(i).contains(dateRange[0])) {
                    list.subList(i, listMessages().size()).clear();
                }

            }
        }

        else if (dateRange[0] == "") {
            for (int i = 0; i < listMessages().size(); i++) {
                if (listMessages().get(i).contains(dateRange[1])) {
                    list.subList(0, i + 1).clear();
                }
            }
        }

        else {
            for (int i = 0; i < listMessages().size(); i++) {

                if (listMessages().get(i).contains(dateRange[0])) {
                    fromIndex = i;
                }

                if (listMessages().get(i).contains(dateRange[1])) {
                    toIndex = i;
                }
            }
            list.subList(fromIndex, toIndex + 1).clear();
        }
        return this.list;
    }
    
    
    // Used to create the date. Create a new date object to update the time
    public String getDate() {
        ZonedDateTime utc = ZonedDateTime.of(LocalDateTime.now(),
                ZoneId.of("Canada/Central"));
        String date = utc.format(DateTimeFormatter.ofPattern("MM/dd/uuuu h:mm:ss a"));
        return date;
    }

    // Print the entire list
    public List<String> listMessages() {
        return this.list;
    }

    public int getListSize() {
        return list.size();
    }


}
