package com.luxoft.library.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractJpaDAO<T, ID> implements GenericDAO<T, ID>{

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T getById(ID id) {
        return entityManager.find(clazz, id);
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Transactional
    public void deleteById(ID id) {
        findById(id).ifPresent(entity -> delete(entity));
        
    }
    
}
