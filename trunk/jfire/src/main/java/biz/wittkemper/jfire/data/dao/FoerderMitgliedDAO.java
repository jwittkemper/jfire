package biz.wittkemper.jfire.data.dao;

import java.util.List;

import biz.wittkemper.jfire.data.entity.FoerderMitglied;

public interface FoerderMitgliedDAO extends AbstractDAO<FoerderMitglied, Long> {

	int getAll();
	public List<FoerderMitglied> getAllList();
}
