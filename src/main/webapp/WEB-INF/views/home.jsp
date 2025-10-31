<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8"/>
  <title>Inicio</title>
  <%@ include file="fragments/head.jspf" %>
</head>
<body>
  <%@ include file="fragments/navbar.jspf" %>
  <div class="container">
    <div class="p-5 bg-light rounded-3">
      <h1 class="display-6">Módulo Clientes & Proveedores</h1>
      <p class="lead">CRUD + búsqueda + validaciones + 2 reportes PDF (sin BD).</p>
      <hr/>
      <p>
        • Clientes activos (PDF):
        <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/report?type=clientes">Generar</a>
        &nbsp;• Compras por proveedor (PDF):
        <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/report?type=proveedores">Generar</a>
      </p>
    </div>
  </div>
</body>
</html>
