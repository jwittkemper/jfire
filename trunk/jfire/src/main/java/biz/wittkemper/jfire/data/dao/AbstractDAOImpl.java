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
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		try {
			t = session.load(getDomainClass(), id);
		} catch (Exception ex) {
			t = null;
		}
		// trx.commit();
		return (DomainObject) t;
	}

	public void update(DomainObject t) {
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		session.update(t);
		// trx.commit();
	}

	public void merge(DomainObject t) {
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		session.merge(t);
		// trx.commit();
	}

	public void save(DomainObject t) {
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		session.save(t);
		// trx.commit();
	}

	public void delete(DomainObject object) {
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		session.delete(object);
		// trx.commit();
	}

	public List<DomainObject> findByQueryString(String hqlString) {
		// session = SessionFactotyUtil.getInstance().getCurrentSession();
		// trx = session.beginTransaction();
		Session session = this.getSession();
		Query query = session.createQuery(hqlString);
		List<DomainObject> list = query.list();
		// trx.commit();
		return list;
	}
}
