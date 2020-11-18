package com.filedownloadservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;


import com.google.gson.Gson;
import com.msgboard.Attachment;
import com.msgboard.Msg;
import com.msgboard.UserAuth;
import com.persistence.Attachments;
import com.persistence.Posts;
import com.persistence.UserData;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FileDownloadServlet", urlPatterns = "/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    boolean isLoggedIn;
    Posts posts = new Posts();
    private static Gson gson = new Gson();

    private static void printParameters (HttpServletRequest request) throws IOException {
        System.out.println("Printing the parameters");
        Enumeration<String> params = request.getParameterNames();
        System.out.println("Params" + params.toString());
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    }

    // TODO: List all posts on the front end
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isLoggedIn == true)
            System.out.println("Logged in!");
        if (isLoggedIn == false)
            System.out.println("I'm not logged in with valid username");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        printParameters(request);
        String username = request.getParameter("username");
        String message = request.getParameter("message");
        String upload = request.getParameter("upload");
        String returnMessages = request.getParameter("returnMessages");
        String success = "success";
        // System.out.println(username);
        UserAuth user = new UserAuth(username);

        // A username was entered at the login screen, check if it exists in db
        if (username != null) {
            try {
                if (user.authenticate(user.username).equals(success)) {
                    System.out.println("Success!!");
                    isLoggedIn = true;
                }
                else {
                    System.out.println("Bad login");
                    isLoggedIn = false;
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }

        // If the user that is logged in is in the member table, add their message to the msg table in the db
        if (message != null) {
            if (isLoggedIn) {
                Msg msg = new Msg(username, message, null);
                try {
                    posts.add(msg);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Check if user is authenticated to allow for user data download
    // TODO: Set Content-Type to match the file type and Content-Disposition
    public void checkUserAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        } else {
            System.out.println("Rejected!");
        }
    }
}


