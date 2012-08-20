package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.MelderTyp;

public class MelderTypDAOImpl extends AbstractDAOImpl<MelderTyp, Long>
		implements MelderTypDAO {

	@Override
	protected Class<MelderTyp> getDomainClass() {
		return MelderTyp.class;
	}

}
