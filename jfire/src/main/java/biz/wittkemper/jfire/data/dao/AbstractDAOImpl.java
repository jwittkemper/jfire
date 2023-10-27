package biz.wittkemper.jfire.data.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

public abstract class AbstractDAOImpl<DomainObject extends Serializable, KeyTyp extends Serializable> {
	private Session session;
	

	protected abstract Class<DomainObject> getDomainClass();

	protected Session getSession() {
		return HibernateSession.getCurrentSession();
	}

	public DomainObject load(KeyTyp id) {
		Object t = null;
		session = this.getSession();
		try {
			t = session.load(getDomainClass(), id);
		} catch (Exception ex) {
			t = null;
		}
		return (DomainObject) t;
	}

	public void update(DomainObject t) {
		session = this.getSession();
		session.update(t);
	}

	public void merge(DomainObject t) {
		session = this.getSession();
		session.merge(t);
	}

	public void save(DomainObject t) {

		session = this.getSession();
		session.save(t);

	}

	public void delete(DomainObject object) {

		session = this.getSession();
		session.delete(object);

	}

	public List<DomainObject> findByQueryString(String hqlString) {

		session = this.getSession();
		TypedQuery<DomainObject> query = session.createQuery(hqlString);
		List<DomainObject> list = query.getResultList();

		return list;
	}
}
