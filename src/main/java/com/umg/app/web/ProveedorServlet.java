package com.umg.app.web;

import com.umg.app.model.Proveedor;
import com.umg.app.repo.InMemoryProveedorRepository;
import com.umg.app.service.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name="ProveedorServlet", urlPatterns={"/proveedores"})
public class ProveedorServlet extends BaseServlet {
    private ProveedorService service;

    @Override public void init(){
        service = new ProveedorService(new InMemoryProveedorRepository());
        getServletContext().setAttribute("proveedorService", service);
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = Optional.ofNullable(req.getParameter("action")).orElse("list");
        switch (action) {
            case "new":
                req.setAttribute("item", new Proveedor());
                forward(req, resp, "proveedores/form.jsp");
                break;
            case "edit":
                int id = paramInt(req, "id", 0);
                service.porId(id).ifPresentOrElse(p -> req.setAttribute("item", p),
                        () -> req.setAttribute("error", "Proveedor no encontrado"));
                forward(req, resp, "proveedores/form.jsp");
                break;
            case "delete":
                service.eliminar(paramInt(req, "id", 0));
                resp.sendRedirect(req.getContextPath()+"/proveedores");
                break;
            default:
                String q = req.getParameter("q");
                req.setAttribute("items", (q==null? service.listar(): service.buscar(q)));
                req.setAttribute("q", q);
                forward(req, resp, "proveedores/list.jsp");
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Proveedor p = new Proveedor();
            String idStr = req.getParameter("id");
            if (idStr!=null && !idStr.isBlank()) p.setId(Integer.parseInt(idStr));
            p.setNit(req.getParameter("nit"));
            p.setNombre(req.getParameter("nombre"));
            p.setContacto(req.getParameter("contacto"));
            p.setTelefono(req.getParameter("telefono"));
            p.setCorreo(req.getParameter("correo"));
            p.setDireccion(req.getParameter("direccion"));
            p.setActivo("on".equals(req.getParameter("activo")));
            service.guardar(p);
            resp.sendRedirect(req.getContextPath()+"/proveedores");
        } catch (ValidationException ve){
            req.setAttribute("error", ve.getMessage());
            req.setAttribute("item", new Proveedor());
            forward(req, resp, "proveedores/form.jsp");
        }
    }
}
