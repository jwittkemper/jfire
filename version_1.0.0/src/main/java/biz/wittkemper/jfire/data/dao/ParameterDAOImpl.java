package biz.wittkemper.jfire.data.dao;

import java.util.List;

import biz.wittkemper.jfire.data.entity.Parameter;

public class ParameterDAOImpl extends AbstractDAOImpl<Parameter, Long>
		implements ParameterDAO {

	@Override
	protected Class<Parameter> getDomainClass() {
		return Parameter.class;
	}

	@Override
	public String getParameter(String name) {
		String sql = "From Parameter a Where a.bezeichnung = '" + name + "' ";
		List<Parameter> parameter = super.findByQueryString(sql);
		
		if (parameter!=null && parameter.size()>0){
			
			if (parameter.size()== 1){
			return parameter.get(0).getValue().toString();
			}else{
				return "";
			}
		}else{
			return "";	
		}
		
	}

}
