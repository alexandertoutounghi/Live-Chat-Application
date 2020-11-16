package com.persistence;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;
import com.msgboard.DbAccess;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserData {
  List<String> userData = new ArrayList<>();

  /**
   * Download a specified user's username, password (hased MD5) and email. The file is readonly
   * @param username of the user whose data we want to download as JSON
   * @return JSON file that can be located in the ../../backend directory
   * @throws SQLException
   * @throws IOException
   */
  public File userDataToJson(String username) throws Exception {
    DbAccess access = new DbAccess();
    String getUserData = String.format(
      "SELECT * FROM member WHERE member.username='%s';",
      username
    );
    File userJsonFile = null;
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      userData = access.query(getUserData).get(0);
      UserInfo userInfo = new UserInfo(
        userData.get(0),
        userData.get(1),
        userData.get(2),
        userData.get(3)
      );

      // The first item in the list is the user's name
      userJsonFile = new File("./userData_" + userInfo.username + ".json");

      objectMapper.writeValue(userJsonFile, userInfo);
      //      boolean wasIsReadOnly = userJsonFile.setReadOnly();
      //      if (!wasIsReadOnly) throw new ReadOnlyFileSystemException();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userJsonFile;
  }

  /**
   * The purpose of this private class is to get the user's credentials to write to a JSON file
   */
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  private class UserInfo {
    String username;
    String passwordMD5;
    String fullName;
    String email;

    private UserInfo() {
      username = "";
      passwordMD5 = "";
      fullName = "";
      email = "";
    }

    private UserInfo(String username, String passwordMD5, String fullName, String email) {
      this.username = username;
      this.passwordMD5 = passwordMD5;
      this.fullName = fullName;
      this.email = email;
    }
  }
}
