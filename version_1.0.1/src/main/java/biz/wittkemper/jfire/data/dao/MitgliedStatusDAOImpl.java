package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.MitgliedStatus;

public class MitgliedStatusDAOImpl extends
		AbstractDAOImpl<MitgliedStatus, Integer> implements MitgliedStatusDAO{

	@Override
	protected Class<MitgliedStatus> getDomainClass() {
		return MitgliedStatus.class;
	}

}
