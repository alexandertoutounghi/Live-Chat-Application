// This is just a class to test the functionality of ChatManager
// Delete this class before submitting

package com.chatmanager;

import com.chatmanager.ChatManager;

public class Run {

    public static void main(String[] args) {
        ChatManager chat = new ChatManager();
        String msg = "Hello world";
        String user = "test123";

        System.out.println(chat.postMessage(user, msg));
        System.out.println(chat.postMessage(msg));
    }
}
