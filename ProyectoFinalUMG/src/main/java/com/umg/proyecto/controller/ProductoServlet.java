package com.umg.proyecto.controller;

import com.umg.proyecto.model.Producto;
import com.umg.proyecto.repository.ProductoRepository;
import com.umg.proyecto.repository.memory.InMemoryProductoRepository;
import com.umg.proyecto.repository.MarcaRepository;
import com.umg.proyecto.repository.memory.InMemoryMarcaRepository;
import com.umg.proyecto.util.ImageValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.*;

@WebServlet("/productos")
@MultipartConfig
public class ProductoServlet extends HttpServlet {
    private final ProductoRepository repo = new InMemoryProductoRepository();
    private final MarcaRepository marcaRepo = new InMemoryMarcaRepository();

    private Path ensureUploadDir(HttpServletRequest req) throws IOException {
        String root = req.getServletContext().getRealPath("/");
        Path upload = Paths.get(root, "uploads", "products");
        if(!Files.exists(upload)) Files.createDirectories(upload);
        return upload;
    }

    private String savePart(HttpServletRequest req, Part part) throws IOException {
        if(part==null || part.getSize()==0) return null;
        Path dir = ensureUploadDir(req);
        String submitted = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String safe = System.currentTimeMillis() + "_" + submitted.replaceAll("[^a-zA-Z0-9._-]","_");
        Path target = dir.resolve(safe);
        try(InputStream in = part.getInputStream()){
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return req.getContextPath() + "/uploads/products/" + safe;
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null) action="list";

        switch(action){
            case "edit": {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("producto", repo.findById(id).orElse(new Producto()));
                req.setAttribute("marcas", marcaRepo.findAll());
                req.getRequestDispatcher("/WEB-INF/views/productos.jsp").forward(req, resp);
                break;
            }
            case "new": {
                req.setAttribute("producto", new Producto());
                req.setAttribute("marcas", marcaRepo.findAll());
                req.getRequestDispatcher("/WEB-INF/views/productos.jsp").forward(req, resp);
                break;
            }
            case "detail": {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("producto", repo.findById(id).orElse(null));
                req.getRequestDispatcher("/WEB-INF/views/productos_list.jsp").forward(req, resp);
                break;
            }
            case "list":
            default: {
                req.setAttribute("productos", repo.findAll());
                req.setAttribute("marcas", marcaRepo.findAll());
                req.getRequestDispatcher("/WEB-INF/views/productos_list.jsp").forward(req, resp);
            }
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String op = req.getParameter("op");
        if("save".equals(op)){
            String idStr = req.getParameter("id");
            String nombre = req.getParameter("nombre");
            Integer marcaId = Integer.valueOf(req.getParameter("marcaId"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            double precio = Double.parseDouble(req.getParameter("precio"));

            Part img = req.getPart("imagen");
            String error = ImageValidator.validate(img);
            if(error!=null){
                req.getSession().setAttribute("msgError", error);
                resp.sendRedirect(req.getContextPath()+"/productos?action="+(idStr==null||idStr.isBlank()?"new":"edit&id="+idStr));
                return;
            }
            String url = savePart(req, img);

            Producto p;
            if(idStr==null||idStr.isBlank()){
                p = new Producto(null, nombre, marcaId, stock, precio, url);
            } else {
                p = repo.findById(Integer.parseInt(idStr)).orElse(new Producto());
                p.setNombre(nombre); p.setMarcaId(marcaId); p.setStock(stock); p.setPrecio(precio);
                if(url!=null) p.setImagenUrl(url);
            }
            repo.save(p);
        } else if("delete".equals(op)){
            int id = Integer.parseInt(req.getParameter("id"));
            repo.delete(id);
        }
        resp.sendRedirect(req.getContextPath()+"/productos");
    }
}