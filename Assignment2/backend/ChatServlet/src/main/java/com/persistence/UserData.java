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
  UserInfo userInfo;

  public UserData(String username) throws SQLException {
    this.initilizeUserInfo(username);
  }

  /**
   * Initializes the user info object member
   * @param username of the user whose data we want to download as JSON
   * @throws SQLException
   */
  private void initilizeUserInfo(String username) throws SQLException {
    DbAccess access = new DbAccess();
    String getUserData = String.format(
      "SELECT * FROM member WHERE member.username='%s';",
      username
    );
    try {
      List<String> userData = access.query(getUserData).get(0);
      this.userInfo =
        new UserInfo(userData.get(0), userData.get(1), userData.get(2), userData.get(3));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Generates a specified user's username, password (hased MD5) and email. The file is readonly
   * @param username of the user whose data we want to download as JSON
   * @return JSON file that can be located in the ../../backend directory
   * @throws IOException
   */
  public File getJsonFile() throws IOException {
    File userJsonFile = null;
    try {
      // Intitialize object mapper
      ObjectMapper objectMapper = new ObjectMapper();

      // The first item in the list is the user's name
      userJsonFile = new File("./userData_" + this.userInfo.username + ".json");

      objectMapper.writeValue(userJsonFile, this.userInfo);
      //      boolean wasIsReadOnly = userJsonFile.setReadOnly();
      //      if (!wasIsReadOnly) throw new ReadOnlyFileSystemException();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userJsonFile;
  }

  /**
   * Generates user info as a json string
   * @return JSON string
   * @throws IOException
   */
  public String getJsonString() {
    try {
      // Intitialize object mapper
      ObjectMapper objectMapper = new ObjectMapper();

      return objectMapper.writeValueAsString(this.userInfo);
    } catch (Exception e) {
      e.printStackTrace();
      return e.toString();
    }
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
