package com.umg.app.web;

import com.umg.app.model.Cliente;
import com.umg.app.repo.InMemoryClienteRepository;
import com.umg.app.service.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name="ClienteServlet", urlPatterns={"/clientes"})
public class ClienteServlet extends BaseServlet {
    private ClienteService service;

    @Override public void init(){
        service = new ClienteService(new InMemoryClienteRepository());
        getServletContext().setAttribute("clienteService", service);
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("list");
        switch (action) {
            case "new":
                req.setAttribute("item", new Cliente());
                forward(req, resp, "clientes/form.jsp");
                break;
            case "edit":
                int id = paramInt(req, "id", 0);
                service.porId(id).ifPresentOrElse(c -> req.setAttribute("item", c),
                        () -> req.setAttribute("error", "Cliente no encontrado"));
                forward(req, resp, "clientes/form.jsp");
                break;
            case "delete":
                service.eliminar(paramInt(req, "id", 0));
                resp.sendRedirect(req.getContextPath()+"/clientes");
                break;
            default:
                String q = req.getParameter("q");
                req.setAttribute("items", (q==null? service.listar(): service.buscar(q)));
                req.setAttribute("q", q);
                forward(req, resp, "clientes/list.jsp");
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Cliente c = new Cliente();
            String idStr = req.getParameter("id");
            if (idStr!=null && !idStr.isBlank()) c.setId(Integer.parseInt(idStr));
            c.setNit(req.getParameter("nit"));
            c.setNombres(req.getParameter("nombres"));
            c.setApellidos(req.getParameter("apellidos"));
            c.setTelefono(req.getParameter("telefono"));
            c.setCorreo(req.getParameter("correo"));
            c.setDireccion(req.getParameter("direccion"));
            c.setActivo("on".equals(req.getParameter("activo")));
            service.guardar(c);
            resp.sendRedirect(req.getContextPath()+"/clientes");
        } catch (ValidationException ve){
            req.setAttribute("error", ve.getMessage());
            req.setAttribute("item", new Cliente());
            forward(req, resp, "clientes/form.jsp");
        }
    }
}
