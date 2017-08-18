package it.unifi.ing.swam.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDao<E> {

    private final Class<E> typeClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseDao(Class<E> typeClass) {
        this.typeClass = typeClass;
    }

    public void save(E entity) {
        entityManager.persist(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

    public E findById(Long typeId) {
        return entityManager.find(typeClass, typeId);
    }

}
