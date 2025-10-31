package com.umg.app.repo;
import com.umg.app.model.Cliente;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryClienteRepository implements ClienteRepository {
    private final Map<Integer, Cliente> data = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public InMemoryClienteRepository() {
        save(new Cliente(null, "1234567", "Ana", "García", "+50255551234", "ana@example.com", "Zona 1", true));
        save(new Cliente(null, "5678901", "Luis", "Pérez", "55551235", "luis@example.com", "Mixco", true));
        save(new Cliente(null, "9999999", "María", "López", "+50255550000", "maria@example.com", "Villa Nueva", false));
    }

    @Override public List<Cliente> findAll() { return new ArrayList<>(data.values()); }
    @Override public Optional<Cliente> findById(int id) { return Optional.ofNullable(data.get(id)); }

    @Override public List<Cliente> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        String s = q.toLowerCase(Locale.ROOT);
        return data.values().stream().filter(c ->
            (c.getNit()!=null && c.getNit().toLowerCase().contains(s)) ||
            (c.getNombres()!=null && c.getNombres().toLowerCase().contains(s)) ||
            (c.getApellidos()!=null && c.getApellidos().toLowerCase().contains(s)) ||
            (c.getCorreo()!=null && c.getCorreo().toLowerCase().contains(s))
        ).collect(Collectors.toList());
    }

    @Override public Cliente save(Cliente c) {
        if (c.getId() == null) c.setId(seq.incrementAndGet());
        data.put(c.getId(), c);
        return c;
    }

    @Override public boolean delete(int id) { return data.remove(id) != null; }
}
