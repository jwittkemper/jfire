package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Fuehrerschein;

public class FuehrerscheinDAOImpl extends AbstractDAOImpl<Fuehrerschein, Long>
		implements FuehrerscheinDAO {

	@Override
	protected Class<Fuehrerschein> getDomainClass() {
		return Fuehrerschein.class;
	}

}
