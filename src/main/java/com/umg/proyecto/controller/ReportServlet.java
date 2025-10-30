package com.umg.proyecto.controller;

import com.umg.proyecto.model.Producto;
import com.umg.proyecto.repository.ProductoRepository;
import com.umg.proyecto.repository.memory.InMemoryProductoRepository;
import com.umg.proyecto.repository.MarcaRepository;
import com.umg.proyecto.repository.memory.InMemoryMarcaRepository;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Locale;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private final ProductoRepository pRepo = new InMemoryProductoRepository();
    private final MarcaRepository mRepo = new InMemoryMarcaRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = Optional.ofNullable(req.getParameter("type")).orElse("inventario"); // inventario | por_marca
        String format = Optional.ofNullable(req.getParameter("format")).orElse("html");   // html | csv

        switch (type) {
            case "por_marca": renderProductosPorMarca(format, resp); break;
            case "inventario":
            default: renderInventario(format, resp); break;
        }
    }

    private void renderInventario(String format, HttpServletResponse resp) throws IOException {
        List<Producto> list = pRepo.findAll();
        if ("csv".equalsIgnoreCase(format)) {
            resp.setContentType("text/csv; charset=UTF-8");
            resp.setHeader("Content-Disposition", "attachment; filename=inventario.csv");
            try (PrintWriter out = resp.getWriter()) {
                out.println("ID,Nombre,MarcaId,Stock,Precio,ImagenURL,TotalLinea");
                for (Producto p : list) {
                    double total = p.getStock() * p.getPrecio();
                    out.printf(Locale.US, "%d,%s,%d,%d,%.2f,%s,%.2f%n",
                            p.getId(), safe(p.getNombre()), p.getMarcaId(), p.getStock(), p.getPrecio(), safe(p.getImagenUrl()), total);
                }
            }
        } else {
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html><head><title>Reporte Inventario</title>");
                out.println(bootstrapHead());
                out.println("</head><body class='p-4'>");
                out.println("<div class='container'>");
                out.println("<h2 class='mb-3'>Inventario por Producto</h2>");
                out.println("<a class='btn btn-outline-secondary btn-sm mb-3' href='?type=inventario&format=csv'>Descargar CSV</a>");
                out.println("<div class='table-responsive'>");
                out.println("<table class='table table-striped table-hover align-middle'><thead class='table-dark'><tr><th>ID</th><th>Nombre</th><th>Marca</th><th>Stock</th><th>Precio</th><th>Total</th></tr></thead><tbody>");
                for (Producto p : list) {
                    double total = p.getStock() * p.getPrecio();
                    String marca = mRepo.findById(p.getMarcaId()).map(m -> m.getNombre()).orElse("-");
                    out.printf(Locale.US, "<tr><td>%d</td><td>%s</td><td>%s</td><td>%d</td><td>Q%.2f</td><td>Q%.2f</td></tr>",
                            p.getId(), esc(p.getNombre()), esc(marca), p.getStock(), p.getPrecio(), total);
                }
                out.println("</tbody></table></div></div></body></html>");
            }
        }
    }

    private void renderProductosPorMarca(String format, HttpServletResponse resp) throws IOException {
        Map<Integer, List<Producto>> grouped = pRepo.findAll().stream()
                .collect(Collectors.groupingBy(Producto::getMarcaId));
        if ("csv".equalsIgnoreCase(format)) {
            resp.setContentType("text/csv; charset=UTF-8");
            resp.setHeader("Content-Disposition", "attachment; filename=productos_por_marca.csv");
            try (PrintWriter out = resp.getWriter()) {
                out.println("Marca,Producto,Stock,Precio");
                for (var e : grouped.entrySet()) {
                    String marca = mRepo.findById(e.getKey()).map(m -> m.getNombre()).orElse("-");
                    for (Producto p : e.getValue()) {
                        out.printf(Locale.US, "%s,%s,%d,%.2f%n",
                                safe(marca), safe(p.getNombre()), p.getStock(), p.getPrecio());
                    }
                }
            }
        } else {
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html><head><title>Reporte Productos por Marca</title>");
                out.println(bootstrapHead());
                out.println("</head><body class='p-4'><div class='container'>");
                out.println("<h2 class='mb-3'>Productos por Marca</h2>");
                out.println("<a class='btn btn-outline-secondary btn-sm mb-3' href='?type=por_marca&format=csv'>Descargar CSV</a>");
                for (var e : grouped.entrySet()) {
                    String marca = mRepo.findById(e.getKey()).map(m -> m.getNombre()).orElse("-");
                    out.printf("<div class='card mb-3'><div class='card-header fw-bold'>%s</div><div class='card-body'>", esc(marca));
                    out.println("<div class='table-responsive'><table class='table table-sm table-striped align-middle'><thead class='table-light'><tr><th>ID</th><th>Producto</th><th>Stock</th><th>Precio</th></tr></thead><tbody>");
                    for (Producto p : e.getValue()) {
                        out.printf(Locale.US, "<tr><td>%d</td><td>%s</td><td>%d</td><td>Q%.2f</td></tr>",
                                p.getId(), esc(p.getNombre()), p.getStock(), p.getPrecio());
                    }
                    out.println("</tbody></table></div></div></div>");
                }
                out.println("</div></body></html>");
            }
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s.replaceAll("[\\r\\n,]", " ");
    }
    private static String esc(String s) {
        return s == null ? "" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    // <<< AQUÍ ESTABA EL PROBLEMA >>>
    private static String bootstrapHead() {
        return "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
               "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>";
    }
}
