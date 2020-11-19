package com.servlets;

import com.msgboard.UserAuth;
import com.persistence.Attachments;
import com.persistence.Posts;
import com.persistence.UserData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="/LoginServlet")
public class LoginServlet extends HttpServlet {
    Posts posts = new Posts();

    // TODO: List all posts on the front end
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();


        System.out.println("Hellow login drain gang bladee");


        String servletPath = request.getServletPath();
        String auth = request.getParameter("authenticate");
        if (auth != null) try {
            checkUserAuth(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if user is authenticated to allow for user data download
    // TODO: Set Content-Type to match the file type and Content-Disposition
    public void checkUserAuth(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Retreive username and password from front-end
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String success = "success";

        UserAuth user = new UserAuth(username);

        // Check if user logged in successfully
        // Maybe put password in here instead of getUserName?
        if (user.authenticate(user.authenticate(password)).equals(success)) {
            // Change parameters to match frontend once it's available
            if (request.getParameter("downloadJson") != null) {
                UserData downloadJson = new UserData();
                downloadJson.userDataToJson(username);
            }
            else if (request.getParameter("downloadAttachment") != null) {
                String messageId = request.getParameter("messageid");
                Attachments attachment = new Attachments();
                attachment.get(messageId);
            }
        }
    }
}
