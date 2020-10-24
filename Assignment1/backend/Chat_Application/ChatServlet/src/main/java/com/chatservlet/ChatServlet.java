package com.chatservlet;

import chatmanager.ChatManager;
import chatmanager.Message;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.MessageDigest;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import com.chatservlet.ServletHelper;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends ServletHelper {
    private ChatManager chatmanager = ChatManager.getInstance();
    private static Gson gson = new Gson();
    private static final int BYTES_DOWNLOAD = 1024;

    private static void printParameters (HttpServletRequest request) throws IOException {
        System.out.println("Printing the parameters");
        Enumeration<String> params = request.getParameterNames();
        System.out.println("Params" + params.toString());
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    };

    private static String encodeJson (String str){
        return "\"" + str + "\"";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Print parameters
        printParameters(request);

        // Retrieving the message
        String user = request.getParameter("username");;
        String msg = request.getParameter("message");
        String returnMessages = request.getParameter("returnMessages");

//         Add chat message to message list, if user field is empty string or null, set username to anonymous
        String type = "";
        String userResponse = "";
        if(returnMessages!=null){
            type = "outputMessages";
            StringBuilder encodedContent = new StringBuilder();
            List<Message> ls = chatmanager.listMessages();
            for(int i = 0; i< ls.size(); i++){
                encodedContent.append(encodeJson(String.format("m%d", i))).append(":").append(gson.toJson(ls.get(i)));
                if(i!=ls.size()-1) encodedContent.append(",");
            }
            userResponse = String.format("{%s}", encodedContent.toString());
            System.out.println(userResponse);
        }
        else if (msg != null) {
            type = "inputMessages";
            userResponse = "message_accepted";
            chatmanager.postMessage(user, msg);
            System.out.println(chatmanager.listMessages());
        }
        else if(user!= null){
            type = "login";
            userResponse = "found_account";
        }
//         Print out the chat messages
        session.setAttribute("chat", chatmanager.listMessages());

        response.setContentType("text/plain");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Origin", "GET, POST, DELETE, PUT, OPTIONS");
        response.addHeader("Access-Control-Allow-Origin", "X-PINGOTHER, Content-Type");

        response.getWriter().write(userResponse);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
