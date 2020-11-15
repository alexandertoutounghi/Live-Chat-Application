package com.filedownloadservlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;

import com.chatmanager.ChatManager;
import com.chatmanager.Message;
import com.google.gson.Gson;
import net.sourceforge.stripes.action.FileBean;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FileDownloadServlet extends HttpServlet {

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doPost(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String servletPath = request.getServletPath();
//
//        if ("/fileview".equals(servletPath))
//            fileview(request, response);
//        else if ("/filedownload".equals(servletPath))
//            filedownload(request, response);
//    }
//
//    private void fileview(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        File afile = new File(); // need to match filexxx.java
//        try {
//            List filelist = afile.getFileList(); // get from filexxx.java
//            request.setAttribute("filelist", filelist);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        RequestDispatcher rd = request.getRequestDispatcher("/downloadfile.jsp");
//        rd.forward(request, response);
//    }
//
//    private void filedownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FileBean file = null;
//
//        try {
//            String filedir = getServletContext().getRealPath("/files");
//            System.out.println(filedir + "//get file path");
//            String downfilename = request.getParameter("downname");
//            System.out.println(downfilename + "//get file name");
//
//            File afile = new File();
//            file = afile.searchFile(downfilename);
//            afile.closed();
//            if (file != null) {
//                String filename = new String(file.getFileName().getBytes("gb2312"), "ISO-8859-1");
//                LoadFile loadAFile = new LoadFile();
//                loadAFile.init(request, response, filedir);
//                loadAFile.download(downfilename, filename, file.getFileType());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            String message = "Invalid file" + file.getFileName();
//            request.setAttribute("message", message); // a error page
//            RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
//            rd.forward(request, response);
//        }
//    }
}
