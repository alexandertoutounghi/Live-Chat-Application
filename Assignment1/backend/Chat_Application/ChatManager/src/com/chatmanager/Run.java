// This is just a class to test the functionality of ChatManager
// Delete this class before submitting

package com.chatmanager;
import java.util.concurrent.TimeUnit;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        ChatManager chat = new ChatManager();
        String msg = "Hello world";
        String user = "test123";

        String msg2 = "Yo, what's up?";
        String user2 = "Yeee boi";

        String msg3 = "Testing anon";

        String dateRange = "10/01/2020 7:54:20 a.m.";

        chat.postMessage(user, msg);
        chat.postMessage(user2, msg2);
        chat.postMessage(msg3);
        // chat.listMessages();
        chat.listMessages(dateRange);



    }
}
