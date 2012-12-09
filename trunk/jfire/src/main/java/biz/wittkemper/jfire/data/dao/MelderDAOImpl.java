package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Melder;

public class MelderDAOImpl extends AbstractDAOImpl<Melder, Long> implements
		MelderDAO {

	Melder melderOrginal;

	@Override
	public Melder load(Long id) {
		melderOrginal = super.load(id);
		return melderOrginal;
	}

	@Override
	public void update(Melder melder) {
		if (melderOrginal != null) {

		}
		super.update(melder);
	}

	@Override
	protected Class<Melder> getDomainClass() {
		return Melder.class;
	}
}
