<%-- 
    Document   : editarPuesto
    Created on : 31 oct 2025, 1:56:34
    Author     : Kemberly Donis
--%>

<%@page import="modelo.Puesto"%>
<%
    Puesto puesto = (Puesto) request.getAttribute("puesto");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Puesto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-light">
<div class="container mt-5">
    <h2>Editar Puesto</h2>
    <form action="ControladorPuesto?accion=actualizar" method="post" class="mt-4">
        <input type="hidden" name="idpuesto" value="<%=puesto.getIdpuesto()%>">
        <div class="mb-3">
            <label class="form-label">Nombre del Puesto:</label>
            <input type="text" name="puesto" value="<%=puesto.getPuesto()%>" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="ControladorPuesto?accion=listar" class="btn btn-secondary">Regresar</a>
    </form>
</div>
</body>
</html>
