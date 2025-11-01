<%-- 
    Document   : menu
    Created on : 31 oct 2025, 3:06:00
    Author     : Kemberly Donis
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,modelo.Menu,modeloDAO.MenuDAO" %>

<%
    MenuDAO daoMenu = new MenuDAO();
    List<Menu> listaMenu = daoMenu.listar();

    Map<Integer, List<Menu>> mapa = new HashMap<>();
    for (Menu m : listaMenu) {
        mapa.computeIfAbsent(m.getId_padre(), k -> new ArrayList<>()).add(m);
    }

    List<Menu> raices = mapa.get(null);
    if (raices == null) raices = new ArrayList<>();
%>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold text-light" href="index.jsp">
      <i class="bi bi-shop"></i> TiendaWeb
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <% for (Menu padre : raices) {
             List<Menu> hijos = mapa.get(padre.getId_menu());
             if (hijos != null) { %>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown"><%= padre.getNombre() %></a>
                  <ul class="dropdown-menu dropdown-menu-dark">
                    <% for (Menu hijo : hijos) {
                         List<Menu> nietos = mapa.get(hijo.getId_menu());
                         if (nietos != null) { %>
                            <li class="dropdown-submenu dropend">
                              <a class="dropdown-item dropdown-toggle" href="#"><%= hijo.getNombre() %></a>
                              <ul class="dropdown-menu dropdown-menu-dark">
                                <% for (Menu nieto : nietos) { %>
                                  <li><a class="dropdown-item" href="<%= (nieto.getUrl() != null && !nieto.getUrl().isEmpty()) ? nieto.getUrl() : "#" %>"><%= nieto.getNombre() %></a></li>
                                <% } %>
                              </ul>
                            </li>
                         <% } else { %>
                            <li><a class="dropdown-item" href="<%= (hijo.getUrl() != null && !hijo.getUrl().isEmpty()) ? hijo.getUrl() : "#" %>"><%= hijo.getNombre() %></a></li>
                         <% }
                       } %>
                  </ul>
                </li>
             <% } else { %>
                <li class="nav-item"><a class="nav-link" href="#"><%= padre.getNombre() %></a></li>
             <% }
           } %>
      </ul>
    </div>
  </div>
</nav>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script para habilitar submenús anidados -->
<script>
  document.addEventListener('DOMContentLoaded', function() {
    // Selecciona todos los elementos con clase 'dropdown-submenu'
    var dropdownSubmenus = document.querySelectorAll('.dropdown-submenu');

    dropdownSubmenus.forEach(function(submenu) {
      var toggle = submenu.querySelector('.dropdown-toggle');

      if (toggle) {
        toggle.addEventListener('click', function(e) {
          e.preventDefault();
          e.stopPropagation();

          // Cierra otros submenús abiertos
          submenu.parentElement.querySelectorAll('.dropdown-menu.show').forEach(function(openMenu) {
            if (openMenu !== submenu.querySelector('.dropdown-menu')) {
              openMenu.classList.remove('show');
            }
          });

          // Alterna la visibilidad del submenú actual
          submenu.querySelector('.dropdown-menu').classList.toggle('show');
        });
      }
    });

    // Cierra todos los submenús si se hace clic fuera
    document.addEventListener('click', function() {
      document.querySelectorAll('.dropdown-menu.show').forEach(function(openMenu) {
        openMenu.classList.remove('show');
      });
    });
  });
</script>
