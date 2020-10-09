package com.chatservlet;

import chatmanager.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {
    private ChatManager chatmanager = new ChatManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String msg = request.getParameter("msg");

        // Add chat message to message list, if user field is empty string or null, set username to anonymous
        if(user == null || user == "")
            chatmanager.postMessage(msg);
        else
            chatmanager.postMessage(user, msg);

        // Print out the chat messages
        session.setAttribute("chat", chatmanager.listMessages());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String[] dateRange = new String[2];
        dateRange[0] = from;
        dateRange[1] = to;

        session.setAttribute("chat", chatmanager.listMessages(dateRange));

        String format = request.getParameter("format");

        // Return xml or plaintext
        if(format == "xml")
            response.setContentType("text/xml");
        else
            response.setContentType("text/plain");



        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
