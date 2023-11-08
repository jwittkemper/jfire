package biz.wittkemper.jfire.data.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

abstract class AbstractDAOImpl<DomainObject extends Serializable, KeyTyp extends Serializable> {

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

	public List<DomainObject> findByQueryString(String hqlString, HashMap<String, Boolean> map) {
		entityManager = this.getEntityManager();
		Query sq = entityManager.createQuery(hqlString, T.class);

		if (map != null) {
			Map<String, Boolean> map1 = map;
			Map.Entry<String, Boolean> entry = map1.entrySet().iterator().next();

			sq.setParameter(entry.getKey(), entry.getValue());
		}

		// TypedQuery<DomainObject> query = session.createQuery(hqlString);
		List<DomainObject> list = sq.getResultList();

		return list;
	}
}
