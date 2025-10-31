package com.umg.app.web;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {
    protected void forward(HttpServletRequest req, HttpServletResponse resp, String view)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + view).forward(req, resp);
    }

    protected int paramInt(HttpServletRequest req, String name, int def){
        try { return Integer.parseInt(req.getParameter(name)); } catch(Exception e){ return def; }
    }
}
