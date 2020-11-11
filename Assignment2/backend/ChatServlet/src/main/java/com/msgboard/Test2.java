package com.msgboard;

import java.io.IOException;
import java.sql.SQLException;

public class Test2 {

    public static void main(String[] args) throws Exception {
        RetrieveUserData download = new RetrieveUserData();

        for (var user : new User[] {
                new User("antoine"),
                new User("zayn"),
                new User("alex"),
                new User("wen"),
                new User("james")
        }) {
            String authStatus = user.authenticate(user.getUsername());
            System.out.println(user.getUsername() + ": " + authStatus);
        }

        download.userDataToJson("antoine");
        download.userDataToJson("zayn");
        download.userDataToJson("alex");
        download.userDataToJson("wen");
        download.userDataToJson("james");

    }
}
