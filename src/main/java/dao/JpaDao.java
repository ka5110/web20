package dao;


import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<E , K> implements DAO<E, K> {
    protected Class entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public void persist(E entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public void remove(K id) {
       E entity = (E) this.entityManager.find(entityClass, id);
       this.entityManager.remove(entity);

    }

    @Override
    public E findById(K id) {
        return (E) this.entityManager.find(entityClass, id);
    }

    @Override
    public List<E> getAll() {
        return  (List<E>) this.entityManager.createQuery(
                "select e from " + entityClass.getSimpleName() + " e"
        ).getResultList();
    }
}
