package com.sena.mapper;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class GenericMapper <E, D>{
    public abstract D toDTO(E entity);
    public abstract E toEntity(D dto);

    public List<D> toDTO(List<E> entities){
      return entities.stream().map(e-> toDTO(e))
              .collect((Collectors.toList()));
    }


        public List<E> toEntity(List<D> entities){
            return entities.stream().map(e-> toEntity(e))
                    .collect((Collectors.toList()));
        
}
