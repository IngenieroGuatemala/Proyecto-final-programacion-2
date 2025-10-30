<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Producto</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/productos">Productos</a>
  </div>
</nav>
<div class="container">
  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title">
        <c:choose>
          <c:when test="${empty producto.id}">Nuevo Producto</c:when>
          <c:otherwise>Editar Producto #${producto.id}</c:otherwise>
        </c:choose>
      </h5>

      <c:if test="${not empty sessionScope.msgError}">
        <div class="alert alert-danger" role="alert">${sessionScope.msgError}</div>
        <c:remove var="msgError" scope="session"/>
      </c:if>

      <form method="post" action="${pageContext.request.contextPath}/productos" enctype="multipart/form-data" class="row g-3">
        <input type="hidden" name="op" value="save"/>
        <input type="hidden" name="id" value="${producto.id}"/>

        <div class="col-md-6">
          <label class="form-label">Nombre</label>
          <input class="form-control" name="nombre" value="${producto.nombre}" required/>
        </div>
        <div class="col-md-6">
          <label class="form-label">Marca</label>
          <div class="input-group">
            <select class="form-select" name="marcaId" required>
              <c:forEach var="m" items="${marcas}">
                <option value="${m.id}" ${m.id==producto.marcaId ? "selected" : ""}>${m.nombre}</option>
              </c:forEach>
            </select>
            <a class="btn btn-outline-secondary" target="_blank" href="${pageContext.request.contextPath}/marcas">Marcas</a>
          </div>
        </div>
        <div class="col-md-4">
          <label class="form-label">Stock</label>
          <input class="form-control" type="number" name="stock" value="${producto.stock}" min="0" required/>
        </div>
        <div class="col-md-4">
          <label class="form-label">Precio (Q)</label>
          <input class="form-control" type="number" step="0.01" name="precio" value="${producto.precio}" min="0" required/>
        </div>
        <div class="col-md-4">
          <label class="form-label">Imagen (JPG/PNG/WEBP, máx 2MB)</label>
          <input class="form-control" type="file" name="imagen" accept=".jpg,.jpeg,.png,.webp"/>
        </div>
        <div class="col-12 d-flex gap-2">
          <button class="btn btn-primary" type="submit">Guardar</button>
          <a class="btn btn-secondary" href="${pageContext.request.contextPath}/productos">Cancelar</a>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>