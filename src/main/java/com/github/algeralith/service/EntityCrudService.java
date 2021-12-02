package com.github.algeralith.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import io.quarkus.logging.Log;

public abstract class EntityCrudService<T> {

    private Class<T> type;

    @Inject
    protected EntityManager entityManager;

    // The class type of the entity is needed for using the entityManager.
    // There appears to be no way to grab a class type from the generic.
    // Which means the class type needs to be passed through the constructor.
    public EntityCrudService(Class<T> type) {
        this.type = type;
    }

    @Transactional
    public T createEntity(T entity) {
        Log.infof("createEntity() : %s ", entity != null ? entity.toString() : "Null entity.");

        // Just return null if we received null. No point in letting it go to persist and erroring out there.
        if (entity == null)
            return null;

        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            Log.errorf("createEntity() : Failed to persisty entity.");
            Log.error(e);

            return null;
        }

        return entity;
    }

    public T getEntity(long id) {
        Log.infof("getEntity() : %d", id);
        return entityManager.find(type, id);
    }

    @Transactional
    public T updateEntity(T entity) {
        try {
            return (T)entityManager.merge(entity);
        } catch (Exception e) {
            Log.errorf("updateEntity() : Failed to update Entity.");
            Log.error(e);
        }

        return null;
    }

    @Transactional
    public boolean deleteEntity(long id) {
        try {
            Log.infof("deleteEntity() : %d", id);

            // Query for entity.
            T entity = getEntity(id);

            Log.debugf("deleteEntity() : %s ", entity != null ? entity.toString() : "Null entity.");

            // Return false if not found. After all, you can not delete something that does not exist.
            if (entity == null) {
                return false;
            }

            entityManager.remove(entity);

            // In the past, I've had issues with JPA caching. I've read examples that flush the cache, so it is what I'll do here.
            entityManager.flush();
            entityManager.clear();

            return true;
        } catch (Exception e) {
            Log.errorf("deleteEntity() : %d : failed to delete entity.", id);
            Log.error(e);
        }

        return false;
    }

}
