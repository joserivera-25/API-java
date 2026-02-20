package com.sena.service.implementation;

import com.sena.dto.ProductoDTO;
import com.sena.entity.Producto;
import com.sena.mapper.ProductoMapper;
import com.sena.repository.ProductoRepository;
import com.sena.service.ProductoService;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImplementation implements ProductoService {


    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public ProductoServiceImplementation(ProductoRepository repository, ProductoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductoDTO> findAll(Pageable pageable, String search) {
        Page<Producto> productos;
        if (search == null || search.trim().isEmpty()){
            productos = repository.findAll(pageable);
        }else{
            productos = repository.findByNombreContainingIgnoreCase(pageable, search);
        }
        return new PageImpl<>(
                productos.getContent().stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList()),
                pageable,
                productos.getTotalElements()

        );
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO findById(Long id) {
        Producto entidad = repository.findById(id).orElseThrow();
        return mapper.toDTO(entidad);
    }

    @Override
    public ProductoDTO create(ProductoDTO obj) {
        Producto entidad = mapper.toEntity(obj);
        Producto saved= repository.save(entidad);
        return mapper.toDTO(saved);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO obj) {
        Producto entidad = mapper.toEntity(obj);
        if (repository.existsById(id)){
            entidad.setId(id);
            Producto saved = repository.save(entidad);
            return mapper.toDTO(saved);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
