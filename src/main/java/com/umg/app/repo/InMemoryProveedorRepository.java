package com.umg.app.repo;
import com.umg.app.model.Proveedor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryProveedorRepository implements ProveedorRepository {
    private final Map<Integer, Proveedor> data = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public InMemoryProveedorRepository() {
        save(new Proveedor(null, "1122334", "TecnoCentro", "Karen Díaz", "+50255551111", "ventas@tecnocentro.com", "Zona 4", true));
        save(new Proveedor(null, "2233445", "Papelería XYZ", "Mario Ruiz", "55552222", "contacto@papxyz.com", "Amatitlán", true));
    }

    @Override public List<Proveedor> findAll() { return new ArrayList<>(data.values()); }
    @Override public Optional<Proveedor> findById(int id) { return Optional.ofNullable(data.get(id)); }

    @Override public List<Proveedor> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        String s = q.toLowerCase(Locale.ROOT);
        return data.values().stream().filter(p ->
            (p.getNit()!=null && p.getNit().toLowerCase().contains(s)) ||
            (p.getNombre()!=null && p.getNombre().toLowerCase().contains(s)) ||
            (p.getContacto()!=null && p.getContacto().toLowerCase().contains(s)) ||
            (p.getCorreo()!=null && p.getCorreo().toLowerCase().contains(s))
        ).collect(Collectors.toList());
    }

    @Override public Proveedor save(Proveedor p) {
        if (p.getId() == null) p.setId(seq.incrementAndGet());
        data.put(p.getId(), p);
        return p;
    }

    @Override public boolean delete(int id) { return data.remove(id) != null; }
}
