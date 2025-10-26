<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Marcas</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Inicio</a>
  </div>
</nav>
<div class="container">
  <div class="card shadow-sm mb-3">
    <div class="card-body">
      <h5 class="card-title">Nueva / Editar Marca</h5>
      <form method="post" action="${pageContext.request.contextPath}/marcas" class="row g-2">
        <input type="hidden" name="op" value="save"/>
        <input type="hidden" name="id" value="${marca.id}"/>
        <div class="col-md-8">
          <label class="form-label">Nombre</label>
          <input class="form-control" name="nombre" value="${marca.nombre}" required/>
        </div>
        <div class="col-md-4 d-flex align-items-end gap-2">
          <button class="btn btn-primary" type="submit">Guardar</button>
          <a class="btn btn-secondary" href="${pageContext.request.contextPath}/marcas">Limpiar</a>
        </div>
      </form>
    </div>
  </div>

  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title">Listado de Marcas</h5>
      <div class="table-responsive">
        <table class="table table-striped align-middle">
          <thead class="table-dark">
            <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
          </thead>
          <tbody>
          <c:forEach var="m" items="${marcas}">
            <tr>
              <td>${m.id}</td><td>${m.nombre}</td>
              <td class="d-flex gap-2">
                <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/marcas?action=edit&id=${m.id}">Editar</a>
                <form method="post" action="${pageContext.request.contextPath}/marcas" onsubmit="return confirm('¿Eliminar?')">
                  <input type="hidden" name="op" value="delete"/>
                  <input type="hidden" name="id" value="${m.id}"/>
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