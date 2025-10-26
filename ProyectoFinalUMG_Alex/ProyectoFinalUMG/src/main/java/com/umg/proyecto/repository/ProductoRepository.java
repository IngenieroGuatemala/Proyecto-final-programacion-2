package com.umg.proyecto.repository;
import com.umg.proyecto.model.Producto;
import java.util.*;
public interface ProductoRepository {
    List<Producto> findAll();
    Optional<Producto> findById(int id);
    Producto save(Producto p);
    void delete(int id);
    List<Producto> findByMarcaId(int marcaId);
}