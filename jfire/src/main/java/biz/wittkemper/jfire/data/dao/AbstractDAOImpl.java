package biz.wittkemper.jfire.data.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;



public abstract class AbstractDAOImpl<DomainObject extends Serializable, KeyTyp extends Serializable> {
	
	private EntityManager entityManager;
	

	protected abstract Class<DomainObject> getDomainClass();

	protected EntityManager getEntityManager() {
            return JPAEntityManager.getInstance();
	}

	public DomainObject load(KeyTyp id) {
		Object t = getDomainClass();
		entityManager = this.getEntityManager();
		try {
//			t = entityManager.find( t, id);
		} catch (Exception ex) {
			t = null;
		}
		return (DomainObject) t;
	}

	public void update(DomainObject t) {
		entityManager = this.getEntityManager();
		entityManager.merge(t);
	}

	public void merge(DomainObject t) {
		this.update(t);
	}

	public void save(DomainObject t) {

		entityManager = this.getEntityManager();
		entityManager.persist(t);

	}

	public void delete(DomainObject object) {

		entityManager = this.getEntityManager();
		entityManager.remove(object);
	}

	public List<DomainObject> findByQueryString(String hqlString) {
		entityManager = this.getEntityManager();
        Query sq =  entityManager.createQuery(hqlString);
                
		//TypedQuery<DomainObject> query = session.createQuery(hqlString);
		List<DomainObject> list = sq.getResultList();

		return list;
	}
}
