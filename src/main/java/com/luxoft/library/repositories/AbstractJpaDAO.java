package com.luxoft.library.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.luxoft.library.entities.AbstractEntity;

@Transactional(readOnly = true)
public abstract class AbstractJpaDAO<T extends AbstractEntity, ID> {

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
    public ID save(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

        return (ID) entity.getId();

    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public long deleteById(ID id) {
        T entity = this.findById(id).orElseThrow();
        this.delete(entity);
        return entity.getId();
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM " + clazz.getName());
    }

}
