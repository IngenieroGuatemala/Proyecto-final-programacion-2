<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="es"><head>
  <meta charset="UTF-8"/>
  <title>Proveedor</title>
  <%@ include file="../fragments/head.jspf" %>
</head><body>
<%@ include file="../fragments/navbar.jspf" %>
<div class="container">
  <h2>${item.id == null ? 'Nuevo Proveedor' : 'Editar Proveedor'}</h2>
  <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
  <form method="post" class="row g-3">
    <input type="hidden" name="id" value="${item.id}"/>
    <div class="col-md-3"><label class="form-label">NIT</label><input name="nit" value="${item.nit}" class="form-control" required></div>
    <div class="col-md-6"><label class="form-label">Nombre</label><input name="nombre" value="${item.nombre}" class="form-control" required></div>
    <div class="col-md-3"><label class="form-label">Contacto</label><input name="contacto" value="${item.contacto}" class="form-control"></div>
    <div class="col-md-4"><label class="form-label">Teléfono</label><input name="telefono" value="${item.telefono}" class="form-control" placeholder="+502########" required></div>
    <div class="col-md-4"><label class="form-label">Correo</label><input type="email" name="correo" value="${item.correo}" class="form-control" required></div>
    <div class="col-md-4"><label class="form-label">Dirección</label><input name="direccion" value="${item.direccion}" class="form-control"></div>
    <div class="col-md-12 form-check">
      <input type="checkbox" class="form-check-input" id="activo" name="activo" ${item.activo? 'checked':''}>
      <label class="form-check-label" for="activo">Activo</label>
    </div>
    <div class="col-12">
      <button class="btn btn-primary">Guardar</button>
      <a class="btn btn-secondary" href="${pageContext.request.contextPath}/proveedores">Cancelar</a>
    </div>
  </form>
</div>
</body></html>
