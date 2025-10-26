package com.umg.proyecto.repository.memory;

import com.umg.proyecto.model.Marca;
import com.umg.proyecto.repository.MarcaRepository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMarcaRepository implements MarcaRepository {
    private static final Map<Integer, Marca> data = new LinkedHashMap<>();
    private static final AtomicInteger seq = new AtomicInteger(0);
    static {
        saveStatic(new Marca(seq.incrementAndGet(), "Genérica"));
        saveStatic(new Marca(seq.incrementAndGet(), "Acme"));
        saveStatic(new Marca(seq.incrementAndGet(), "UMG Tech"));
    }
    private static void saveStatic(Marca m){ data.put(m.getId(), m); }
    @Override public List<Marca> findAll(){ return new ArrayList<>(data.values()); }
    @Override public Optional<Marca> findById(int id){ return Optional.ofNullable(data.get(id)); }
    @Override public Marca save(Marca m){
        if(m.getId()==null) m.setId(seq.incrementAndGet());
        data.put(m.getId(), m); return m;
    }
    @Override public void delete(int id){ data.remove(id); }
}