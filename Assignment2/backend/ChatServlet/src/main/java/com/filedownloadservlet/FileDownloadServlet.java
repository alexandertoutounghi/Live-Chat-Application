package com.filedownloadservlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;


import com.msgboard.Msg;
import com.msgboard.UserAuth;
import com.persistence.Attachments;
import com.persistence.Posts;
import com.persistence.UserData;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@WebServlet(name="/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    Posts posts = new Posts();

    // TODO: List all posts on the front end
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("hello world");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("msg received");
        System.out.println(request.getParameter("username"));

        HttpSession session = request.getSession();

        // List all posts from user, change attribute to match frontend, change chat to match frontend param
        try {
            session.setAttribute("chat", posts.getAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Update these parameters to match the frontend paramaters
/*
        String addPost = request.getParameter("post");
        String updatePost = request.getParameter("update");
        String deletePost = request.getParameter("delete");
        String searchPost = request.getParameter("search");
        if (addPost != null) {

            posts.add();
        }

        else if (updatePost != null) {
            posts.update();
        }

        else if (deletePost != null) {
            posts.delete();
        }

        else if (searchPost != null) {
            posts.search();
        }
*/


        String servletPath = request.getServletPath();
        String auth = request.getParameter("authenticate");
        if (auth != null)
            try {
                checkUserAuth(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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
        }
    }

/*    private void fileview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        File afile = new File(); // need to match filexxx.java
        try {
            List filelist = afile.getFileList(); // get from filexxx.java
            request.setAttribute("filelist", filelist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/downloadfile.jsp");
        rd.forward(request, response);
    }

    private void filedownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileBean file = null;

        try {
            String filedir = getServletContext().getRealPath("/files");
            System.out.println(filedir + "//get file path");
            String downfilename = request.getParameter("downname");
            System.out.println(downfilename + "//get file name");

            File afile = new File();
            file = afile.searchFile(downfilename);
            afile.closed();
            if (file != null) {
                String filename = new String(file.getFileName().getBytes("gb2312"), "ISO-8859-1");
                LoadFile loadAFile = new LoadFile();
                loadAFile.init(request, response, filedir);
                loadAFile.download(downfilename, filename, file.getFileType());
            }
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Invalid file" + file.getFileName();
            request.setAttribute("message", message); // a error page
            RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
            rd.forward(request, response);
        }
    }*/
}


