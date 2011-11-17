package biz.wittkemper.jfire.data.dao;

import biz.wittkemper.jfire.data.entity.Anrede;

public interface AnredeDAO extends AbstractDAO<Anrede, Integer> {

	Anrede getAnredeByText(String text);

}
