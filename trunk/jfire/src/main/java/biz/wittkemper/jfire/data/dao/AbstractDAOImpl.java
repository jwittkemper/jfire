package biz.wittkemper.jfire.data.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class AbstractDAOImpl<DomainObject extends Serializable, KeyTyp extends Serializable> {
	private Session session;
	private Transaction trx;

	protected abstract Class<DomainObject> getDomainClass();

	protected Session getSession() {
		return HibernateSession.getCurrentSession();
	}

	public DomainObject load(KeyTyp id) {
		Object t = null;
		Session session = this.getSession();
		try {
			t = session.load(getDomainClass(), id);
		} catch (Exception ex) {
			t = null;
		}
		return (DomainObject) t;
	}

	public void update(DomainObject t) {
		Session session = this.getSession();
		session.update(t);
	}

	public void merge(DomainObject t) {
		Session session = this.getSession();
		session.merge(t);
	}

	public void save(DomainObject t) {

		Session session = this.getSession();
		session.save(t);

	}

	public void delete(DomainObject object) {

		Session session = this.getSession();
		session.delete(object);

	}

	public List<DomainObject> findByQueryString(String hqlString) {

		Session session = this.getSession();
		Query query = session.createQuery(hqlString);
		List<DomainObject> list = query.list();

		return list;
	}
}
