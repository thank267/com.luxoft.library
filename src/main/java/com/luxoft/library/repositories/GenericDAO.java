package com.luxoft.library.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {

    public T getById(ID id);

    public Optional<T> findById(ID id);

    public List<T> findAll();

    
    public void save(T entity);

   
    public T update(T entity);

   
    public void delete(T entity);
   
    public void deleteById(ID id);
    
}
