package com.chatservlet;
import com.chatmanager.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChatManager chat = new ChatManager();
        String user = request.getParameter("user");
        String msg = request.getParameter("msg");

        chat.postMessage(user, msg);
        // request
        String value = "hello";
        String test = "username";

        // request.setAttribute("chat", chat);
        request.getRequestDispatcher("/index.jsp").forward(request, response);






    }


    private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
