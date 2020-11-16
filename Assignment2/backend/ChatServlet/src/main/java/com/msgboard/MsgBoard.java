package com.msgboard;

import static java.util.stream.Collectors.joining;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgBoard {

  // Add. Add hashtags
  public void postHashtags(Msg msg, String messageid) throws SQLException {
    // Connect to database
    DbAccess access = new DbAccess();

    // If there are hashtags insert them into the database
    String hashtagsInsertQuery = String.format(
      "INSERT INTO hashtag (messageid, content) VALUES %s;",
      Arrays
        .stream(msg.hashtags)
        .map(elem -> String.format("(%s, '%s')", messageid, elem))
        .collect(joining(", "))
    );
    access.query(hashtagsInsertQuery);
  }

  // Add. Add attachment
  public void postAttachment(Msg msg, String messageid) {
    // Insert attachmetn into database
    String sql =
      "INSERT INTO attachment (filename, filesize, filetype, messageid, content) VALUES (?, ?, ?, ?, ?);";
    try (
      Connection conn = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/assignment2",
        "root",
        "root"
      );
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setString(1, msg.attachment.filename);
      pstmt.setLong(2, msg.attachment.filesize);
      pstmt.setString(3, msg.attachment.filetype);
      pstmt.setInt(4, Integer.parseInt(messageid));

      FileInputStream fis = new FileInputStream(msg.attachment.file);
      pstmt.setBinaryStream(5, fis);
      pstmt.executeUpdate();
      fis.close();
    } catch (SQLException | IOException e) {
      System.out.println(e.getMessage());
    }
  }

  // 1. Create a post
  /**
   * Posts a message to the database (THREAD SAFE)
   * @param msg The message to be posted
   * @throws SQLException
   */
  public void postMsg(Msg msg) throws SQLException, IOException {
    // Connect to database
    DbAccess access = new DbAccess();

    // Insert the message and return the generated id
    String formatDate = msg.created.format(
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );
    String msgInsertQuery = String.format(
      "INSERT INTO msg (username, content, modified, created) VALUES ('%s', '%s', '%s', '%s') RETURNING messageid;",
      msg.username,
      msg.content,
      formatDate,
      formatDate
    );
    String messageid = access.query(msgInsertQuery).get(0).get(0);

    // If there are hashtags insert them into the database
    if (msg.hashtags.length != 0) {
      this.postHashtags(msg, messageid);
    }
    if (msg.attachment != null) {
      this.postAttachment(msg, messageid);
    }
  }

  // 2. Delete a post
  /**
   * Deletes a message
   * @throws SQLException
   */
  public void deleteMsg(String messageid, String username) throws SQLException {
    DbAccess access = new DbAccess();
    String deleteMsgQuery = String.format(
      "DELETE FROM msg WHERE messageid=%s AND username='%s';",
      messageid,
      username
    );
    String deleteCorrespondingHashtagsQuery = String.format(
      "DELETE FROM hashtag WHERE messageid=%s;",
      messageid
    );
    access.query(deleteMsgQuery);
    access.query(deleteCorrespondingHashtagsQuery);
  }

  // 3. Update a post
  /**
   * Updates a message
   * @throws SQLException
   */
  public void updateMsg(String messageid, Msg msg) throws SQLException {
    DbAccess access = new DbAccess();
    String updateMsgQuery = String.format(
      "UPDATE msg SET content='%s', modified='%s' WHERE messageid=%s AND username='%s';",
      msg.content,
      msg.modified,
      messageid,
      msg.username
    );
    access.query(updateMsgQuery);

    // Delete old hashtags
    String hashtagsDeleteQuery = String.format(
      "DELETE FROM hashtag WHERE messageid=%s;",
      messageid
    );
    access.query(hashtagsDeleteQuery);

    // If there are hashtags insert them into the database
    if (msg.hashtags.length != 0) {
      this.postHashtags(msg, messageid);
    }

    // Delete old attachment
    String attachmentDeleteQuery = String.format(
      "DELETE FROM attachment WHERE messageid=%s;",
      messageid
    );
    access.query(hashtagsDeleteQuery);

    // Add new attachment if available
    if (msg.attachment != null) {
      this.postAttachment(msg, messageid);
    }
  }

  // Add. Return all posts
  /**
   * Returns all messages
   * @return ArrayList of all the messages sorted by latest modified
   * @throws SQLException
   */
  public List<List<String>> getMsg() throws SQLException {
    DbAccess access = new DbAccess();
    String getMsgQuery = "SELECT * FROM msg ORDER BY modified DESC;";
    return access.query(getMsgQuery);
  }

  // 4. Search for posts
  /**
   * Returns x number of messages
   * @param numberOfResults String representing the number of posts to return
   * @return ArrayList of all the messages sorted by latest modified
   * @throws SQLException
   */
  public List<List<String>> getMsg(String numberOfResults) throws SQLException {
    DbAccess access = new DbAccess();
    String getMsg = String.format(
      "SELECT * FROM msg ORDER BY modified DESC LIMIT %s;",
      numberOfResults
    );
    return access.query(getMsg);
  }

  // 5. View recent posts
  /**
   * Returns all messages that match all parameters
   * @param username author of the message(s)
   * @param fromDateTime minimum datetime range at which the message(s) was posted
   * @param toDateTime maximum datetime range at which the message(s) was posted
   * @param hashtags array of hashtags the message(s) need to contain
   * @return ArrayList of the matching messages sorted by latest modified
   * @throws SQLException
   */
  public List<List<String>> getMsg(
    String username,
    String fromDateTime,
    String toDateTime,
    String[] hashtags
  )
    throws SQLException {
    DbAccess access = new DbAccess();
    HashMap<String, String> queries = new HashMap<>();

    // SUB-QUERY: USERNAME
    if (username != null && !username.equals("")) {
      queries.put("username", String.format("msg.username='%s'", username));
    }

    // SUB-QUERY: MODIFIED
    queries.put(
      "datetimerange",
      String.format("msg.modified BETWEEN '%s' AND '%s'", fromDateTime, toDateTime)
    );

    // SUB-QUERY: HASHTAGS
    if (hashtags.length != 0) {
      queries.put(
        "hashtags",
        Arrays
          .stream(hashtags)
          .map(
            elem ->
              String.format(
                "msg.messageid=hashtag.messageid AND hashtag.content='#%s'",
                elem
              )
          )
          .collect(joining(" OR "))
      );
    }

    // Query string to return all the message matching the hashmap conditions
    String getAllMsgQuery = String.format(
      "SELECT DISTINCT msg.* FROM msg JOIN member%sWHERE %s ORDER BY modified DESC;",
      queries.containsKey("hashtags") ? " JOIN hashtag " : " ",
      String.join(" AND ", (new ArrayList<>(queries.values())))
    );

    return access.query(getAllMsgQuery);
  }
}
