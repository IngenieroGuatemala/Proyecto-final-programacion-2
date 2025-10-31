package com.umg.app.repo;
import java.util.*;
import com.umg.app.model.Proveedor;

public interface ProveedorRepository {
    List<Proveedor> findAll();
    Optional<Proveedor> findById(int id);
    List<Proveedor> search(String q);
    Proveedor save(Proveedor p);
    boolean delete(int id);
}
