package com.msgboard;

import com.msgboard.DbAccess;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuth {
  public final String username;

  public UserAuth(String username) {
    this.username = username;
  }

  /**
   * Authenticates the user
   * @param reqPassword Password to be validated
   * @return True if a corresponding login was found in the database
   * @throws SQLException
   */
  public String authenticate(String reqPassword) throws SQLException, ClassNotFoundException {
    DbAccess access = new DbAccess();
    String userExists = String.format(
      "SELECT * FROM member WHERE username='%s';",
      this.username
    );
    if (access.query(userExists).size() == 0) {
      return "unknown_user";
    } else {
      String query = String.format(
        "SELECT username FROM member WHERE username='%s' AND password=MD5('%s');",
        this.username,
        reqPassword
      );
      return access.query(query).size() != 0 ? "success" : "wrong_credentials";
    }
  }
}
