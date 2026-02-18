package com.sena.service.implementation;

import com.sena.entity.Producto;
import com.sena.repository.ProductoRepository;
import com.sena.service.ProductoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductoServiceImplementation implements ProductoService {


    private final ProductoRepository repository;

    public ProductoServiceImplementation(ProductoRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> findAll(Pageable pageable, String search) {
        if (search == null || search.trim().isEmpty()){
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCase(pageable, search);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Producto create(Producto obj) {
        return repository.save(obj);
    }

    @Override
    public Producto update(Long id, Producto obj) {
        if (repository.existsById(id)){
            obj.setId(id);
            return repository.save(obj);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
