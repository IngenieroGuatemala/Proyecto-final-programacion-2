<%-- 
    Document   : listarPuestos
    Created on : 31 oct 2025, 1:56:15
    Author     : Kemberly Donis
--%>

<%@page import="modelo.Puesto"%>
<%@page import="java.util.List"%>
<%@page import="modeloDAO.PuestoDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Puestos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-dark text-light">

<div class="container mt-5">
    <h2 class="mb-4 text-center">Lista de Puestos</h2>
    <div class="d-flex justify-content-end mb-3">
        <a href="ControladorPuesto?accion=agregar" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Agregar Puesto
        </a>
    </div>
    <table class="table table-dark table-striped text-center align-middle">
        <thead>
        <tr>
            <th>ID</th>
            <th>Puesto</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            PuestoDAO dao = new PuestoDAO();
            List<Puesto> lista = dao.listar();
            for (Puesto p : lista) {
        %>
        <tr>
            <td><%= p.getIdpuesto() %></td>
            <td><%= p.getPuesto() %></td>
            <td>
                <a href="ControladorPuesto?accion=editar&id=<%=p.getIdpuesto()%>" class="btn btn-primary btn-sm me-1">
                    <i class="bi bi-pencil-square"></i>
                </a>
                <a href="ControladorPuesto?accion=eliminar&id=<%=p.getIdpuesto()%>" class="btn btn-danger btn-sm">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
