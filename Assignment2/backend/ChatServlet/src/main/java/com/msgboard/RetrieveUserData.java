package com.msgboard;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveUserData {
    List <String> userData = new ArrayList<>();

    /**
     * Download a specified user's username, password (hased MD5) and email. The file is readonly
     * @param username of the user whose data we want to download as JSON
     * @return JSON file that can be located in the ../../backend directory
     * @throws SQLException
     * @throws IOException
     */
    public void userDataToJson(String username) throws Exception {
        DbAccess access = new DbAccess();
        String getUserData = String.format(
                "SELECT * FROM member WHERE member.username='%s';",
                username
        );

        if (access.query((getUserData)).size() == 0) {
            System.out.println("User does not exist");

        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                userData = access.query(getUserData).get(0);
                UserInfo userInfo = new UserInfo(userData.get(0), userData.get(1));
                User user = new User(userData.get(0));
                File userJsonFile = new File("userData_" + user.getUsername() + ".json");

                objectMapper.writeValue(userJsonFile, userInfo);
                userJsonFile.setReadOnly();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The purpose of this private class is to get the user's credentials to write to a JSON file
     */
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private class UserInfo {
        String username;
        String passwordMD5;

        private UserInfo() {
            username = "";
            passwordMD5 = "";
        }

        private UserInfo(String username, String passwordMD5) {
            this.username = username;
            this.passwordMD5 = passwordMD5;
        }
    }
}
