package com.chatservlet;

import chatmanager.ChatManager;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;

@WebServlet(name = "ChatServlet", urlPatterns = {"/ChatServlet"})
public class ChatServlet extends ServletHelper {
    private ChatManager chatmanager = ChatManager.getInstance();
    private static Gson gson = new Gson();
    private static final int BYTES_DOWNLOAD = 1024;

    private static String getJSONValue (ServletInputStream stream) throws IOException {
        String tempReader;
        StringBuilder sbuild = new StringBuilder(new String(""));
        BufferedReader br =
                new BufferedReader(new InputStreamReader(stream));

        while ((tempReader = br.readLine()) != null) {
            sbuild.append(tempReader);
        }
        String finalRes = sbuild.toString();
        System.out.println(finalRes);

        try
        {
            String[] jsonRes = gson.fromJson(finalRes, String[].class);
            //this one is printing so that means it actually succeed to parse?
            System.out.println(Arrays.toString(jsonRes));
        }
        catch( Exception e )
        {
            // just want to test out if it really failed
            System.out.println( "failed" );
            System.out.println( "Unable to get parse request" + e );
        }
        return finalRes;
    };

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        checkHeader(request);
        HttpSession session = request.getSession();


        try {
            String json = request.getParameter("jsondata");
            System.out.println(json.toString());
        } catch(Exception e){
            System.out.println("Error" + e.toString());
        }

        String user = " ";
        String msg = request.getParameter("msg");

        System.out.println(user);

//         Add chat message to message list, if user field is empty string or null, set username to anonymous
        if (user == null || user == "")
            chatmanager.postMessage(msg);
        else
            chatmanager.postMessage(user, msg);

//         Print out the chat messages
        session.setAttribute("chat", chatmanager.listMessages());

        response.setContentType("text/plain");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Origin", "GET, POST, DELETE, PUT, OPTIONS");
        response.addHeader("Access-Control-Allow-Origin", "X-PINGOTHER, Content-Type");
        response.getWriter().write("Account found!");
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("heheajkhlfkawehjsfh");

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
