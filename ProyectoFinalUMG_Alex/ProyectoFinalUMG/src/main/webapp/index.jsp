<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Proyecto Final UMG</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-dark mb-4">
  <div class="container">
    <span class="navbar-brand mb-0 h1">Proyecto Final - Productos y Marcas</span>
  </div>
</nav>
<div class="container">
  <div class="row g-3">
    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Marcas (CRUD)</h5>
          <p class="card-text">Crea y administra marcas.</p>
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/marcas">Ir a Marcas</a>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Productos (CRUD + Imagen)</h5>
          <p class="card-text">Carga imágenes y vincula marcas.</p>
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/productos">Ir a Productos</a>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title">Reportes</h5>
          <p class="card-text">Inventario y productos por marca.</p>
          <a class="btn btn-outline-secondary me-2" target="_blank" href="${pageContext.request.contextPath}/report?type=inventario">Inventario</a>
          <a class="btn btn-outline-secondary" target="_blank" href="${pageContext.request.contextPath}/report?type=por_marca">Por Marca</a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>