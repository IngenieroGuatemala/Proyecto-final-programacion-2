package com.umg.app.repo;
import java.util.*;
import com.umg.app.model.Cliente;

public interface ClienteRepository {
    List<Cliente> findAll();
    Optional<Cliente> findById(int id);
    List<Cliente> search(String q);
    Cliente save(Cliente c);
    boolean delete(int id);
}
