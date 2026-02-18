package com.sena.controlador;

import com.sena.entity.Unidad;
import com.sena.service.UnidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadController {

    private final UnidadService service;

    public UnidadController(UnidadService service){
        this.service = service;
    }

    //ENDPOINT
    //GET http://localhost:8090/unidades
    @GetMapping
    public ResponseEntity<List<Unidad>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    //ENDPOINT
    //GET http://localhost:8090/unidades/cod
    @GetMapping("/{id}")
    public ResponseEntity<Unidad> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Unidad> create(@Valid @RequestBody Unidad obj){
        return new ResponseEntity<>(service.create(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidad> update(@PathVariable String id, @Valid @RequestBody Unidad obj){
        return ResponseEntity.ok(service.update(id, obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
