package com.msgboard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbAccess {

  public DbAccess() throws SQLException {}

  public List<List<String>> query(String query) throws SQLException {
    List<List<String>> rows = new ArrayList<>();
    List<String> temp;

    try (
      // Connecting to the database
      Connection conn = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/assignment2",
        "root",
        "root"
      )
    ) {
      // Prepares statement object for sending query
      try (Statement stmt = conn.createStatement()) {
        try {
          // Sending query
          ResultSet rs = stmt.executeQuery(query);
          int numberColumns = rs.getMetaData().getColumnCount(); // Number of columns in response
          // Iterating through rows and adding to the list
          while (rs.next()) {
            temp = new ArrayList<>();
            // Iterating through columns and adding to the inner list
            for (int i = 1; i <= numberColumns; i++) {
              System.out.println(rs.getString(i));
              temp.add(rs.getString(i));
            }
            rows.add(temp);
          }
        } finally {
          // Closing the connection
          stmt.close();
        }
      }
    }
    return rows;
  }
}
