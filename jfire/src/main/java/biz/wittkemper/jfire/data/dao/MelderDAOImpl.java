package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Melder;

public class MelderDAOImpl extends AbstractDAOImpl<Melder, Long> implements
		MelderDAO {

	@Override
	protected Class<Melder> getDomainClass() {
		return Melder.class;
	}
}
