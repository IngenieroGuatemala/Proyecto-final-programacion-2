package com.umg.app.service;
import com.umg.app.model.Cliente;
import com.umg.app.repo.ClienteRepository;
import java.util.*;

public class ClienteService {
    private final ClienteRepository repo;
    public ClienteService(ClienteRepository repo){ this.repo = repo; }

    public List<Cliente> listar(){ return repo.findAll(); }
    public List<Cliente> buscar(String q){ return repo.search(q); }
    public Optional<Cliente> porId(int id){ return repo.findById(id); }

    public Cliente guardar(Cliente c){
        Validator.required(c.getNit(), "NIT");
        Validator.nit(c.getNit());
        Validator.required(c.getNombres(), "Nombres");
        Validator.required(c.getApellidos(), "Apellidos");
        Validator.telefono(c.getTelefono());
        Validator.email(c.getCorreo());
        return repo.save(c);
    }

    public boolean eliminar(int id){ return repo.delete(id); }
}
