package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Parameter;

public class ParameterDAOImpl extends AbstractDAOImpl<Parameter, Long>
		implements ParameterDAO {

	@Override
	protected Class<Parameter> getDomainClass() {
		return Parameter.class;
	}

}
