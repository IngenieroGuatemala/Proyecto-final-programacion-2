<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Productos</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .thumb { max-height: 60px; border-radius: .25rem; }
  </style>
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Inicio</a>
  </div>
</nav>

<div class="container">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Productos</h3>
    <div>
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/productos?action=new">Nuevo</a>
      <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/marcas">Marcas</a>
      <a class="btn btn-outline-dark" target="_blank" href="${pageContext.request.contextPath}/report?type=inventario">Reporte Inventario</a>
      <a class="btn btn-outline-dark" target="_blank" href="${pageContext.request.contextPath}/report?type=por_marca">Reporte por Marca</a>
    </div>
  </div>

  <div class="card shadow-sm">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr><th>ID</th><th>Imagen</th><th>Nombre</th><th>Marca</th><th>Stock</th><th>Precio</th><th>Acciones</th></tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${productos}">
              <tr>
                <td>${p.id}</td>
                <td>
                  <c:if test="${not empty p.imagenUrl}">
                    <img class="thumb" src="${p.imagenUrl}" alt="img"/>
                  </c:if>
                </td>
                <td>${p.nombre}</td>
                <td>
                  <c:forEach var="m" items="${marcas}">
                    <c:if test="${m.id == p.marcaId}">${m.nombre}</c:if>
                  </c:forEach>
                </td>
                <td>${p.stock}</td>
                <td>Q${p.precio}</td>
                <td class="d-flex gap-2">
                  <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/productos?action=edit&id=${p.id}">Editar</a>
                  <form method="post" action="${pageContext.request.contextPath}/productos" onsubmit="return confirm('¿Eliminar?')" >
                    <input type="hidden" name="op" value="delete"/>
                    <input type="hidden" name="id" value="${p.id}"/>
                    <button class="btn btn-sm btn-outline-danger" type="submit">Eliminar</button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>