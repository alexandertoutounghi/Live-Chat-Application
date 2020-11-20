package com.persistence;

import com.msgboard.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Posts {
  MsgBoard msgBoard;

  public Posts() {
    this.msgBoard = new MsgBoard();
  }

  /**
   * 1. Post message
   * @param msg to be posted
   * @throws SQLException
   */
  public void add(Msg msg) throws SQLException, IOException, ClassNotFoundException {
    this.msgBoard.postMsg(msg);
  }

  /**
   * 2. Delete post
   * @param messageid messageid of the post to delete
   * @param username username of the post to delete
   * @throws SQLException
   */
  public void delete(String messageid, String username) throws SQLException, ClassNotFoundException {
    this.msgBoard.deleteMsg(messageid, username);
  }

  /**
   * 3. Update post
   * @param messageid messageid of the post to delete
   * @param msg msg that will replace the current one
   * @throws SQLException
   */
  public void update(String messageid, Msg msg) throws SQLException, ClassNotFoundException {
    this.msgBoard.updateMsg(messageid, msg);
  }

  /**
   * [Added] Returns all messages
   * @return List of all messages
   * @throws SQLException
   */
  public List<List<String>> getAll() throws SQLException, ClassNotFoundException {
    return this.msgBoard.getMsg();
  }

  /**
   * 4. Returns x number of messages
   * @param numberOfResults number of results to return
   * @return List of all messages
   * @throws SQLException
   */
  public List<List<String>> search(String numberOfResults) throws SQLException, ClassNotFoundException {
    return this.msgBoard.getMsg(numberOfResults);
  }

  /**
   * 5. Searches for messages matching the passed parameters
   * @param username username of message author
   * @param fromDateTime string representing minimum date time of message
   * @param toDateTime string representing minimum date time of message
   * @param hashtags array of hashtags that need to be present in message
   * @return List of corresponding messages
   * @throws SQLException
   */
  public List<List<String>> search(
    String username,
    String fromDateTime,
    String toDateTime,
    String[] hashtags
  )
          throws SQLException, ClassNotFoundException {
    return this.msgBoard.getMsg(username, fromDateTime, toDateTime, hashtags);
  }
}
