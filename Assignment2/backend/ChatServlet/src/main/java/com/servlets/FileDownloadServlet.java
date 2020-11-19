package com.servlets;

import com.msgboard.UserAuth;
import com.persistence.Attachments;
import com.persistence.Posts;
import com.persistence.UserData;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
  Posts posts = new Posts();

  // TODO: List all posts on the front end
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {}

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();

    System.out.println("Hellow woeijrlkdfj");
  }

  // Check if user is authenticated to allow for user data download
  public void checkUserAuth(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    // Retreive username and password from front-end

  }
}
