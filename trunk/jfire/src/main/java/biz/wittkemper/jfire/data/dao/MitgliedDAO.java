package biz.wittkemper.jfire.data.dao;

import java.util.List;

import biz.wittkemper.jfire.data.entity.Mitglied;

public interface MitgliedDAO extends
		AbstractDAO<biz.wittkemper.jfire.data.entity.Mitglied, Long> {

	Mitglied findByNameVorname(String name, String vorname);

	int getAktive();

	int getReserve();
	
	Mitglied getNext(long id);
	
	Mitglied getPrev(long id);

	List<Mitglied> searchByName(String[] names, boolean onlyaktiv);
	
	public Mitglied getByMasterID(long id);
}
