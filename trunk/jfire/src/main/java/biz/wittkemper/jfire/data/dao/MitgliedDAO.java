package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Mitglied;

public interface MitgliedDAO extends
		AbstractDAO<biz.wittkemper.jfire.data.entity.Mitglied, Long> {

	Mitglied findByNameVorname(String name, String vorname);

	int getAktive();

	int getReserve();
	
	Mitglied getNext(long id);
	
	Mitglied getPrev(long id);

}
