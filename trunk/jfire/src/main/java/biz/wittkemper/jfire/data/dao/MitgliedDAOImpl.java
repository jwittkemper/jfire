package biz.wittkemper.jfire.data.dao;

import java.util.Date;
import java.util.List;

import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.utils.ParameterUtils;

public class MitgliedDAOImpl extends AbstractDAOImpl<Mitglied, Long> implements
		MitgliedDAO {

	@Override
	public void merge(Mitglied mitglied) {
		mitglied.setLastChange(new Date());
		super.merge(mitglied);
	}

	@Override
	public void update(Mitglied mitglied) {
		mitglied.setLastChange(new Date());
		super.update(mitglied);
	}

	@Override
	public void save(Mitglied mitglied) {
		if (ParameterUtils.isMasterDB() == true) {
			mitglied.setMasterInsert(new Date());
		}
		mitglied.setLastChange(new Date());
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

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int getAktive() {
		String hsql = "FROM Mitglied m where m.status.id = 1 ";

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public int getReserve() {
		String hsql = "FROM Mitglied m where m.status.id = 2 ";

		List<Mitglied> list = super.findByQueryString(hsql);

		if (list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Mitglied getNext(long id) {
		String hql;
		
		hql="";
		hql += " FROM Mitglied a where a.id >=" + id;
		hql += " Order by id asc ";	
		
		List<Mitglied> list = super.findByQueryString(hql);
		if (list.size()>1 && id >0){
			return list.get(1);
		}else if (list.size()>1 && id ==0){
			return list.get(0);
		}
		return list.get(0);
	}

	@Override
	public Mitglied getPrev(long id) {
		String hql;
		
		hql="";
		hql += " FROM Mitglied a where a.id <=" + id;
		hql += " Order by id desc ";	
		
		List<Mitglied> list = super.findByQueryString(hql);
		if (list.size()> 1){
			return list.get(1);
		}
		return list.get(0);
	}

	@Override
	public List<Mitglied> searchByName(String[] names) {
		String hql;
		
		hql="";
		hql += " FROM Mitglied a where ";
		if (names.length==1){
			hql += " lower(a.name) like '" + names[0].trim().toLowerCase() +"%' ";
			hql +=" OR lower(a.vorname) like '"+ names[0].trim().toLowerCase() +"%' ";
		}else if (names.length==2){
			hql += " lower(a.name) like '" + names[1].trim().toLowerCase() +"%' ";
			hql +=" AND lower(a.vorname) like '"+ names[0].trim().toLowerCase() +"%' ";
		}
		
		hql += " Order by vorname, name desc ";	
		
		List<Mitglied> list = super.findByQueryString(hql);
		
		return list;

	}

}
