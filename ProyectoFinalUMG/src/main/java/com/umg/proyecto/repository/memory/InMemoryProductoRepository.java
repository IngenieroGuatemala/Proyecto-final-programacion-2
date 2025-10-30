package com.umg.proyecto.repository.memory;

import com.umg.proyecto.model.Producto;
import com.umg.proyecto.repository.ProductoRepository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryProductoRepository implements ProductoRepository {
    private static final Map<Integer, Producto> data = new LinkedHashMap<>();
    private static final AtomicInteger seq = new AtomicInteger(0);
    static {
        data.put(seq.incrementAndGet(), new Producto(seq.get(), "Laptop 14\"", 1, 10, 5500.00, null));
        data.put(seq.incrementAndGet(), new Producto(seq.get(), "Mouse Óptico", 2, 50, 75.00, null));
        data.put(seq.incrementAndGet(), new Producto(seq.get(), "Teclado Mecánico", 3, 20, 350.00, null));
    }
    @Override public List<Producto> findAll(){ return new ArrayList<>(data.values()); }
    @Override public Optional<Producto> findById(int id){ return Optional.ofNullable(data.get(id)); }
    @Override public Producto save(Producto p){
        if(p.getId()==null) p.setId(seq.incrementAndGet());
        data.put(p.getId(), p); return p;
    }
    @Override public void delete(int id){ data.remove(id); }
    @Override public List<Producto> findByMarcaId(int marcaId){
        List<Producto> res = new ArrayList<>();
        for(Producto p: data.values()) if(Objects.equals(p.getMarcaId(), marcaId)) res.add(p);
        return res;
    }
}