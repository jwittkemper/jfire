package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.MelderSchleifen;

public class MelderSchleifenDAOImpl extends
		AbstractDAOImpl<MelderSchleifen, Long> implements MelderSchleifenDAO {

	@Override
	protected Class<MelderSchleifen> getDomainClass() {

		return MelderSchleifen.class;
	}

}
