package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.FoerderMitglied;

public interface FoerderMitgliedDAO extends AbstractDAO<FoerderMitglied, Long> {

	int getAll();

}
