package com.servlets;

import com.msgboard.UserAuth;
import com.persistence.Posts;
import com.persistence.UserData;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.JSONTokener;

@WebServlet(name = "/LoginServlet")
public class LoginServlet extends HttpServlet {
  Posts posts = new Posts();

  // TODO: List all posts on the front end
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    System.out.println("hello");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();
    System.out.println("LOGIN INITIATED");

    JSONObject json = new JSONObject(new JSONTokener(request.getReader()));

    System.out.println("input JSON Object: " + json.toString());

    // Get user authentication status
    String userAuthStatus =
      this.getUserAuthStatus(json.getString("username"), json.getString("password"));
    System.out.println(
      userAuthStatus + json.getString("username") + json.getString("password")
    );

    // Get appropriate json response content
    String jsonResponse =
      this.getJsonResponse(userAuthStatus, json.getString("username"));
  }

  // Check if user is authenticated to allow for user data download
  public String getUserAuthStatus(String username, String password) {
    UserAuth user = new UserAuth(username);
    try {
      return user.authenticate(password);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return "server_error";
    }
  }

  public String getJsonResponse(String authResponse, String username) {
    JSONObject jsonResponse = new JSONObject();

    // Does it authorize user
    jsonResponse.put("authStatus", authResponse.equals("success") ? "true" : "false");

    String onSuccessContent = "server_error";
    if (authResponse.equals("success")) {
      try {
        onSuccessContent = (new UserData(username)).getJsonString();
      } catch (SQLException e) {
        onSuccessContent = "server_error";
      }
    } else {
      onSuccessContent = authResponse;
    }

    jsonResponse.put("authStatus", authResponse.equals("success") ? "true" : "false");
    jsonResponse.put("content", onSuccessContent);

    System.out.println(jsonResponse.toString());
    return jsonResponse.toString();
  }
}
