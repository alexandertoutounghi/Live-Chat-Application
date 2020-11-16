package com.persistence;

import com.msgboard.DbAccess;
import java.io.*;
import java.sql.*;
import java.util.List;

public class Attachments {

  // TODO: Need to find a way to con
  /**
   * Returns the attachment of a certain post
   * @param messageid id of the message for which to return the attachment
   * @return ArrayList of all the messages sorted by latest modified
   * @throws SQLException
   */
  public List<List<String>> get(String messageid) throws SQLException {
    //    DbAccess access = new DbAccess();
    //    String getMsgQuery = String.format(
    //      "SELECT filename, filetype, filesize, content FROM attachment WHERE messageid='%s';",
    //      messageid
    //    );
    //
    //    return access.query(getMsgQuery);
    ResultSet rs = null;

    String sql = "SELECT filename, filesize, content FROM attachment WHERE messageid=?;";
    try (
      Connection conn = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/assignment2",
        "root",
        "root"
      );
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setInt(1, Integer.parseInt(messageid));
      rs = pstmt.executeQuery();

      // write binary stream into file
      while (rs.next()) {
        String filename = rs.getString("filename");
        int filesize = rs.getInt("filesize");
        System.out.println(filename + "  " + filesize);
        InputStream input = rs.getBinaryStream("content");
        FileOutputStream output = new FileOutputStream("hellozzz_" + filename);

        byte[] buffer = new byte[filesize];
        while (input.read(buffer) > 0) {
          output.write(buffer);
        }
      }
    } catch (SQLException | FileNotFoundException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
