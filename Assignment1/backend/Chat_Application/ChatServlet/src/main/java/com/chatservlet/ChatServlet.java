package com.chatservlet;

import chatmanager.ChatManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends ServletHelper {
    private ChatManager chatmanager = ChatManager.getInstance();
    private static final int BYTES_DOWNLOAD = 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkHeader(request);
        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String msg = request.getParameter("msg");

        // Add chat message to message list, if user field is empty string or null, set username to anonymous
        if (user == null || user == "")
            chatmanager.postMessage(msg);
        else
            chatmanager.postMessage(user, msg);

        // Print out the chat messages
        session.setAttribute("chat", chatmanager.listMessages());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkHeader(request);
        HttpSession session = request.getSession();

        // List messages
        if (request.getParameter("list") != null) {
            doList(request, response);

        } else if (request.getParameter("delete") != null) { // Delete messages
            doDelete(request, response);

        } else if (request.getParameter("download") != null) {
            downloadChat(response, request, chatmanager);
        }

        clearCache(request, response);
    }
}
