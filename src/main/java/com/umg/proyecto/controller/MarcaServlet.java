package com.umg.proyecto.controller;

import com.umg.proyecto.model.Marca;
import com.umg.proyecto.repository.MarcaRepository;
import com.umg.proyecto.repository.memory.InMemoryMarcaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/marcas")
public class MarcaServlet extends HttpServlet {
    private final MarcaRepository repo = new InMemoryMarcaRepository();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null) action="list";
        switch(action){
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("marca", repo.findById(id).orElse(new Marca()));
            case "list":
            default:
                req.setAttribute("marcas", repo.findAll());
                req.getRequestDispatcher("/WEB-INF/views/marcas.jsp").forward(req, resp);
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String op = req.getParameter("op");
        if("save".equals(op)){
            String idStr = req.getParameter("id");
            String nombre = req.getParameter("nombre");
            Marca m = new Marca(idStr==null||idStr.isBlank()?null:Integer.valueOf(idStr), nombre);
            repo.save(m);
        } else if("delete".equals(op)){
            int id = Integer.parseInt(req.getParameter("id"));
            repo.delete(id);
        }
        resp.sendRedirect(req.getContextPath()+"/marcas");
    }
}