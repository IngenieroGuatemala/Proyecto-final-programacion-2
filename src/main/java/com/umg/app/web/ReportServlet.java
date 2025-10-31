package com.umg.app.web;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.umg.app.model.*;
import com.umg.app.service.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name="ReportServlet", urlPatterns={"/report"})
public class ReportServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type = req.getParameter("type");
        resp.setContentType("application/pdf");
        String filename = ("clientes".equals(type)? "clientes_activos.pdf" : "compras_por_proveedor.pdf");
        resp.setHeader("Content-Disposition", "inline; filename=" + filename);

        try (var out = resp.getOutputStream()) {
            Document doc = new Document(PageSize.LETTER.rotate());
            PdfWriter.getInstance(doc, out);
            doc.open();

            if ("clientes".equals(type)) {
                doc.add(new Paragraph("Listado de Clientes Activos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
                doc.add(new Paragraph("\n"));
                ClienteService svc = (ClienteService) getServletContext().getAttribute("clienteService");
                List<Cliente> data = svc.listar().stream().filter(Cliente::isActivo).toList();
                PdfPTable table = new PdfPTable(new float[]{2,3,3,3,4});
                table.setWidthPercentage(100);
                addHeader(table, "NIT","Nombres","Apellidos","Teléfono","Correo");
                for (Cliente c : data) {
                    table.addCell(c.getNit());
                    table.addCell(c.getNombres());
                    table.addCell(c.getApellidos());
                    table.addCell(c.getTelefono());
                    table.addCell(c.getCorreo());
                }
                doc.add(table);
            } else {
                doc.add(new Paragraph("Compras por Proveedor (simulado)", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
                doc.add(new Paragraph("*Datos de ejemplo para validación de exportación PDF*\n\n"));
                ProveedorService svc = (ProveedorService) getServletContext().getAttribute("proveedorService");
                List<Proveedor> data = svc.listar().stream().filter(Proveedor::isActivo).toList();
                PdfPTable table = new PdfPTable(new float[]{3,3,3});
                table.setWidthPercentage(100);
                addHeader(table, "Proveedor","Contacto","Total Compras (Q)");
                int i = 1;
                for (Proveedor p : data) {
                    table.addCell(p.getNombre());
                    table.addCell(p.getContacto());
                    table.addCell(String.format("%,d", 1000 * i++));
                }
                doc.add(table);
            }

            doc.close();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addHeader(PdfPTable table, String... headers){
        for (String h : headers){
            PdfPCell cell = new PdfPCell(new Phrase(h, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }
}
