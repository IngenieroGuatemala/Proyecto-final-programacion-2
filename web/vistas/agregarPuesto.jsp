<%-- 
    Document   : agregarPuesto
    Created on : 31 oct 2025, 1:56:25
    Author     : Kemberly Donis
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Puesto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-light">
<div class="container mt-5">
    <h2>Agregar Nuevo Puesto</h2>
    <form action="ControladorPuesto?accion=insertar" method="post" class="mt-4">
        <div class="mb-3">
            <label for="puesto" class="form-label">Nombre del Puesto:</label>
            <input type="text" id="puesto" name="puesto" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Guardar</button>
        <a href="ControladorPuesto?accion=listar" class="btn btn-secondary">Regresar</a>
    </form>
</div>
</body>
</html>
