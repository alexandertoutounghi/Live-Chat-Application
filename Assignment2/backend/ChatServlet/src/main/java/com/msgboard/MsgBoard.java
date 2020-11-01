package com.msgboard;

import java.sql.*;
import java.util.List;

public class MsgBoard {

  /**
   * Posts a message to the database
   * @param msg The message to be posted
   * @throws SQLException
   */
  public void postMsg(Msg msg) throws SQLException {
    DbAccess access = new DbAccess();
    String insertMsg = String.format(
      "INSERT INTO msg (username, content, created, modified) VALUES ('%s', '%s', '%s', '%s');",
      msg.username,
      msg.content,
      msg.created,
      msg.modified
    );

    var a = access.query(insertMsg);

  }

  /**
   * Returns an ArrayList of all messages
   * @throws SQLException
   */
  public List<List<String>> getMessages() throws SQLException {
    DbAccess access = new DbAccess();
    String getMessages = "SELECT * FROM msg;";
    return access.query(getMessages);
  }

  /**
   * Returns an ArrayList of all messages by a certain username
   * @param username user for which to retrieve the messages
   * @throws SQLException
   */
  public List<List<String>> getMessages(String username) throws SQLException {
    DbAccess access = new DbAccess();
    String getMessages = String.format(
      "SELECT msg.* FROM msg JOIN member WHERE msg.username='%s';",
      username
    );
    return access.query(getMessages);
  }
}
