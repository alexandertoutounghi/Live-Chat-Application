package com.chatservlet;

import chatmanager.ChatManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends HttpServlet {
    private ChatManager chatmanager = ChatManager.getInstance();
    private static final int BYTES_DOWNLOAD = 1024;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        HttpSession session = request.getSession();

        // List messages
        if (request.getParameter("list") != null){

            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String[] dateRange = new String[2];
            dateRange[0] = from;
            dateRange[1] = to;

            session.setAttribute("chat", chatmanager.listMessages(dateRange));

            String format = request.getParameter("format");

            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } else if (request.getParameter("delete") != null) { // Delete messages
            doDelete(response, request);

        } else if (request.getParameter("download") != null) {

        }

        // Make sure user doesn't cache the data
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility

        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
    }

    protected void doDelete(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String deleteFrom = request.getParameter("deletefrom");
        String deleteTo = request.getParameter("deleteto");
        String[] deleteDateRange = new String[2];
        deleteDateRange[0] = deleteFrom;
        deleteDateRange[1] = deleteTo;

        session.setAttribute("chat", chatmanager.clearChat(deleteDateRange));

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void downloadChat(HttpServletResponse response, HttpServletRequest request, List<String> list) throws IOException {
        String format = request.getParameter("format");
        // Return xml or plaintext
        if (format == "xml") {
            response.setContentType("text/xml");
        }
        else {
            response.setContentType("text/plain");
        }
        response.setHeader("Content-Disposition",
                "attachment;filename=chatLogs.txt");
        ServletContext ctx = getServletContext();

        try(InputStream in = ctx.getResourceAsStream("/chatLogs.txt");
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[BYTES_DOWNLOAD];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }

/*    protected void checkHeader(HttpServletRequest request, HttpServletRequest response) {
        String refererHeader = request.getHeader("referer");
        // no need to continue if the header is missing
        if (refererHeader == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }*/

}
