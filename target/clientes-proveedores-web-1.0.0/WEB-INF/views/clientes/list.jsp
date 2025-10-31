<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="es"><head>
  <meta charset="UTF-8"/>
  <title>Clientes</title>
  <%@ include file="../fragments/head.jspf" %>
</head><body>
<%@ include file="../fragments/navbar.jspf" %>
<div class="container">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Clientes</h2>
    <div>
      <a class="btn btn-success" href="${pageContext.request.contextPath}/clientes?action=new">Nuevo</a>
      <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/report?type=clientes">Generar PDF</a>
    </div>
  </div>

  <form class="row g-2 mb-3" method="get">
    <input type="hidden" name="action" value="list"/>
    <div class="col-auto"><input name="q" value="${q}" class="form-control" placeholder="Buscar (NIT, nombre, correo)"/></div>
    <div class="col-auto"><button class="btn btn-outline-secondary">Buscar</button></div>
  </form>

  <c:if test="${empty items}">
    <div class="alert alert-warning">No hay resultados.</div>
  </c:if>

  <c:if test="${not empty items}">
  <div class="table-responsive">
    <table class="table table-striped">
      <thead><tr>
        <th>ID</th><th>NIT</th><th>Nombres</th><th>Apellidos</th><th>Teléfono</th><th>Correo</th><th>Activo</th><th></th>
      </tr></thead>
      <tbody>
      <c:forEach var="c" items="${items}">
        <tr>
          <td>${c.id}</td><td>${c.nit}</td><td>${c.nombres}</td><td>${c.apellidos}</td><td>${c.telefono}</td><td>${c.correo}</td>
          <td><span class="badge ${c.activo? 'bg-success':'bg-secondary'}">${c.activo? 'Sí':'No'}</span></td>
          <td>
            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/clientes?action=edit&id=${c.id}">Editar</a>
            <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/clientes?action=delete&id=${c.id}" onclick="return confirm('¿Eliminar?');">Eliminar</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  </c:if>
</div>
</body></html>
