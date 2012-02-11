package biz.wittkemper.jfire.service;

import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;

public class ReportDataSource {
	public static List<FoerderMitglied> getFoerderMitglieder(){
		return DAOFactory.getInstance().getFoerderMitgliedDAO().getAllList();
	}
}
