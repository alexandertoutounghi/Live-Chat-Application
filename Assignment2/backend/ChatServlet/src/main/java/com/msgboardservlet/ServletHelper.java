/*
 * This class contains a lot of the code to provide the functionality of the servlet.
 * Use these functions in the ChatServlet class.
 * */

package com.msgboardservlet;

import com.chatmanager.ChatManager;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ListIterator;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletHelper extends HttpServlet {
  private ChatManager chatmanager = ChatManager.getInstance();
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
    "MM/dd/yyyy, hh:mm:ss a",
    Locale.ENGLISH
  );
  private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern(
    "MM/dd/yyyy, h:mm:ss a",
    Locale.ENGLISH
  );

  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();

    ZonedDateTime fromTime = stringToZonedDate(request.getParameter("from"));
    ZonedDateTime toTime = stringToZonedDate(request.getParameter("to"));

    session.setAttribute("chat", chatmanager.clearMessages(fromTime, toTime));

    response.setContentType("text/plain");
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Origin", "GET, POST, DELETE, PUT, OPTIONS");
    response.addHeader("Access-Control-Allow-Origin", "X-PINGOTHER, Content-Type");
  }

  protected ZonedDateTime stringToZonedDate(String date) {
    LocalDateTime ld;
    try {
      ld = LocalDateTime.parse(date, FORMATTER);
    } catch (Exception e) {
      ld = LocalDateTime.parse(date, FORMATTER_2);
    }
    ZonedDateTime zt = ZonedDateTime.of(ld, ZoneId.of("Canada/Central"));
    return zt;
  }

  protected void downloadChat(
    HttpServletResponse response,
    HttpServletRequest request,
    ChatManager chat
  )
    throws IOException, ServletException {
    String format = request.getParameter("format");
    String fileType;

    HttpSession session = request.getSession();
    boolean isXml;

    // Return xml or plaintext
    if (format.equals("xml")) {
      response.setContentType("text/xml");
      fileType = "ChatLogs.xml";
      isXml = true;
    } else {
      response.setContentType("text/plain");
      fileType = "ChatLogs.txt";
      isXml = false;
    }

    session.setAttribute("isXml", isXml);

    ZonedDateTime fromTime = stringToZonedDate(request.getParameter("from"));
    ZonedDateTime toTime = stringToZonedDate(request.getParameter("to"));

    if (isXml) session.setAttribute(
      "download",
      chatmanager.listMessageXml(fromTime, toTime)
    ); else session.setAttribute("download", chatmanager.listMessages(fromTime, toTime));

    response.setHeader("Content-Disposition", "attachment;filename=" + fileType);
    response.setHeader("Content-Transfer-Encoding", "binary");

    ServletContext ctx = getServletContext();
    String relativePath = ctx.getRealPath("/" + fileType);

    ListIterator<String> iterator = chat.listMessages(fromTime, toTime).listIterator();
    try (PrintWriter pw = new PrintWriter(new FileOutputStream(relativePath))) {
      //            while (iterator.hasNext()) {
      //               pw.println(iterator.next());
      //            }
    }
    //        request.getRequestDispatcher("/com/chatservlet/ChatServlet.java").forward(request, response);
    request.getRequestDispatcher("/download.jsp").forward(request, response);
    //        request.getRequestDispatcher("localhost").forward(request, response);

  }

  protected void doList(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession();

    ZonedDateTime fromTime = stringToZonedDate(request.getParameter("from"));
    ZonedDateTime toTime = stringToZonedDate(request.getParameter("to"));

    session.setAttribute("chat", chatmanager.listMessages(fromTime, toTime));

    session.setAttribute("chat", chatmanager.listMessages());
    request.getRequestDispatcher("/index.jsp").forward(request, response);
  }

  // Make sure user doesn't cache the data
  protected void clearCache(HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
    response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
  }
  /*    protected void checkHeader(HttpServletRequest request) throws ServletException {
        String refererHeader = request.getHeader("referer");
        // no need to continue if the header is missing
        if (refererHeader == null) {
            // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            // return;
            throw new ServletException("Referer Header is null");
        }
    }*/
}
