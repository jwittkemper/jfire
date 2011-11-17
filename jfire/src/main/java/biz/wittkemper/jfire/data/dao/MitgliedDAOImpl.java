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

}
