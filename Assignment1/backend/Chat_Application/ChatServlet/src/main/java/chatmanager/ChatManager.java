package chatmanager;

// Use this package to get the date. More reliable than java.util.Date

import org.ietf.jgss.MessageProp;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ChatManager implements Serializable {

    private String user;
    private String message;
    private String userAndMsg;
    private List<Message> list = new ArrayList<>();

    // Used for singleton pattern
    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
        return instance;
    }

    private ChatManager() {
        list.add(new Message(getDateTime(), "Postit Team", "Welcome to Postit!"));
    }

    // Return a username with an associated message and date
    public void postMessage(String user, String message) {
        list.add(new Message(getDateTime(), user, message));
    }

    // Print messages from a cetain date range, must enter date and time. This reads an array from the servlet that contains two strings, from and to.
    public List<String> listMessages(ZonedDateTime from, ZonedDateTime to) {
        int fromIndex = list.indexOf(new Message(from));
        int toIndex = list.indexOf(new Message(to));
        List<Message> result;
        if (fromIndex == -1 && toIndex == -1)
            return new ArrayList<String>();
        else if (fromIndex == -1)
            result = list.subList(0, toIndex);
        else if (toIndex == -1)
            result = list.subList(fromIndex, list.size() - 1);
        else
            result = list.subList(fromIndex, toIndex);

        ArrayList<String> resultMessages = new ArrayList<>(list.size());
        for (Message m : result)
            resultMessages.add(m.toString());

        return resultMessages;
    }

    public List<Message> clearMessages(ZonedDateTime from, ZonedDateTime to) {
        int deleteFromIndex = list.indexOf(new Message(from));
        int deleteToIndex = list.indexOf(new Message(to));
        if (deleteFromIndex == -1 && deleteToIndex == -1)
            return new ArrayList<Message>();
        else if (deleteFromIndex == -1)
            list.subList(0, deleteToIndex).clear();
        else if (deleteToIndex == -1)
            list.subList(deleteFromIndex, list.size()).clear();
        else
            list.subList(deleteFromIndex, deleteToIndex).clear();

        return list;
    }

    // Used to create the date. Create a new date object to update the time
    public ZonedDateTime getDateTime() {
       return ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Canada/Central"));
    }

    // Print the entire list
    public List<Message> listMessages() {
        return this.list;
    }
}
