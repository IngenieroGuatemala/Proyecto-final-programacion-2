package com.umg.proyecto.repository;
import com.umg.proyecto.model.Marca;
import java.util.*;
public interface MarcaRepository {
    List<Marca> findAll();
    Optional<Marca> findById(int id);
    Marca save(Marca m);
    void delete(int id);
}