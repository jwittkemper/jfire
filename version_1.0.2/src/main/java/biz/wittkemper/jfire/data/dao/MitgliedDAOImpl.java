package biz.wittkemper.jfire.data.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.utils.ParameterUtils;

public class MitgliedDAOImpl extends AbstractDAOImpl<Mitglied, Long> implements
		MitgliedDAO {

	@Override
	public void merge(Mitglied mitglied) {
		mitglied.setLastChange(new Date());
		mitglied.setEdit(true);
		super.merge(mitglied);
	}

	@Override
	public void update(Mitglied mitglied) {
		mitglied.setLastChange(new Date());
		mitglied.setEdit(true);
		super.update(mitglied);
	}

	@Override
	public void save(Mitglied mitglied) {
		if (ParameterUtils.isMasterDB() == true) {
			mitglied.setMasterInsert(new Date());
		}
		mitglied.setLastChange(new Date());
		mitglied.setEdit(true);
		super.save(mitglied);
	}

	@Override
	protected Class<Mitglied> getDomainClass() {
		return Mitglied.class;
	}

	@Override
	public Mitglied findByNameVorname(String name, String vorname) {

		String hsql = "From Mitglied m ";
		hsql += " Where m.name = '" + name + "' ";
		hsql += "AND m.vorname = '" + vorname + "' ";
		hsql += " AND a.geloescht = 0";

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getAktive() {
		String hsql = "FROM Mitglied m where m.status.id = 1 ";
		hsql += " and m.geloescht = 0";

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public int getReserve() {
		String hsql = "FROM Mitglied m where m.status.id = 2 ";
		hsql += " and m.geloescht = 0";

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Mitglied getNext(long id) {
		String hql;

		hql = "";
		hql += " FROM Mitglied a where a.id >=" + id;
		hql += " and a.geloescht = 0";
		hql += " Order by id asc ";

		List<Mitglied> list = super.findByQueryString(hql);
		if (list.size() > 1 && id > 0) {
			return list.get(1);
		} else if (list.size() > 1 && id == 0) {
			return list.get(0);
		}
		return list.get(0);
	}

	@Override
	public Mitglied getPrev(long id) {
		String hql;

		hql = "";
		hql += " FROM Mitglied a where a.id <=" + id;
		hql += " and a.geloescht = 0";
		hql += " Order by id desc ";

		List<Mitglied> list = super.findByQueryString(hql);
		if (list.size() > 1) {
			return list.get(1);
		}
		if (list.size() == 1) {
			return list.get(0);
		} else {
			return new Mitglied();
		}
	}

	@Override
	public List<Mitglied> searchByName(String[] names, boolean onlyaktiv) {
		String hql;

		hql = "";
		hql += " FROM Mitglied a where ";
		if (names.length == 1) {
			hql += "( lower(a.name) like '" + names[0].trim().toLowerCase()
					+ "%' ";
			hql += " OR lower(a.vorname) like '"
					+ names[0].trim().toLowerCase() + "%')";
		} else if (names.length == 2) {
			hql += "( lower(a.name) like '" + names[1].trim().toLowerCase()
					+ "%' ";
			hql += " AND lower(a.vorname) like '"
					+ names[0].trim().toLowerCase() + "%') ";
		}
		if (onlyaktiv) {
			hql += " AND a.geloescht = 0";
		}
		hql += " Order by vorname, name desc ";

		List<Mitglied> list = super.findByQueryString(hql);

		return list;

	}

	@Override
	public Mitglied getByMasterID(long id) {
		String hql;

		hql = "";
		hql += " FROM Mitglied a WHERE a.masterId = " + id;

		List<Mitglied> list = super.findByQueryString(hql);

		if (list == null || list.size() <= 0 || list.size() > 1) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void resetEdit() {
		String hql = "";

		hql += "Update Mitglied set edit=0 where edit =1";

		Query query = HibernateSession.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
}
