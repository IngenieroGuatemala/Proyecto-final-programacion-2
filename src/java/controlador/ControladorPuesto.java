/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Puesto;
import modeloDAO.PuestoDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ControladorPuesto")
public class ControladorPuesto extends HttpServlet {
    PuestoDAO dao = new PuestoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                request.setAttribute("puestos", dao.listar());
                request.getRequestDispatcher("vistas/listarPuestos.jsp").forward(request, response);
                break;

            case "agregar":
                request.getRequestDispatcher("vistas/agregarPuesto.jsp").forward(request, response);
                break;

            case "insertar":
                String nombrePuesto = request.getParameter("puesto");
                Puesto nuevo = new Puesto();
                nuevo.setPuesto(nombrePuesto);
                dao.agregar(nuevo);
                response.sendRedirect("ControladorPuesto?accion=listar");
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("puesto", dao.obtenerPorId(idEditar));
                request.getRequestDispatcher("vistas/editarPuesto.jsp").forward(request, response);
                break;

            case "actualizar":
                int idP = Integer.parseInt(request.getParameter("idpuesto"));
                String nomP = request.getParameter("puesto");
                Puesto pEdit = new Puesto(idP, nomP);
                dao.actualizar(pEdit);
                response.sendRedirect("ControladorPuesto?accion=listar");
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("ControladorPuesto?accion=listar");
                break;
        }
    }

    // 🔽 Aquí agregas este método nuevo:
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige las solicitudes POST para que usen la misma lógica del doGet
        doGet(request, response);
    }
}
