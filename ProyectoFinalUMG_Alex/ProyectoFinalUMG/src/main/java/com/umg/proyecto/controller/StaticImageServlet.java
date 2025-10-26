package com.umg.proyecto.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;

@WebServlet("/static-img/*")
public class StaticImageServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getPathInfo(); // /archivo.jpg
        if(name==null || name.contains("..")) { resp.sendError(404); return; }
        String root = req.getServletContext().getRealPath("/");
        Path file = Paths.get(root, "uploads", "products", name.substring(1));
        if(!Files.exists(file)){ resp.sendError(404); return; }
        String ct = Files.probeContentType(file);
        resp.setContentType(ct!=null? ct: "application/octet-stream");
        Files.copy(file, resp.getOutputStream());
    }
}