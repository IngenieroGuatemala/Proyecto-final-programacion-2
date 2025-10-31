package com.umg.app.service;
import com.umg.app.model.Proveedor;
import com.umg.app.repo.ProveedorRepository;
import java.util.*;

public class ProveedorService {
    private final ProveedorRepository repo;
    public ProveedorService(ProveedorRepository repo){ this.repo = repo; }

    public List<Proveedor> listar(){ return repo.findAll(); }
    public List<Proveedor> buscar(String q){ return repo.search(q); }
    public Optional<Proveedor> porId(int id){ return repo.findById(id); }

    public Proveedor guardar(Proveedor p){
        Validator.required(p.getNit(), "NIT");
        Validator.nit(p.getNit());
        Validator.required(p.getNombre(), "Nombre");
        if (p.getContacto()!=null && !p.getContacto().isBlank()) {
            // opcional extra
        }
        Validator.telefono(p.getTelefono());
        Validator.email(p.getCorreo());
        return repo.save(p);
    }

    public boolean eliminar(int id){ return repo.delete(id); }
}
