package biz.wittkemper.jfire.utils;

import biz.wittkemper.jfire.data.dao.DAOFactory;

public class ParameterUtils {

	public static boolean isMasterDB() {
		boolean lreturn;

		String dbversion = DAOFactory.getInstance().getParameterDAO()
				.getParameter("DBTYP");
		if (dbversion.toLowerCase().equals("masterdb")) {
			lreturn = true;
		} else {
			lreturn = false;
		}
		return lreturn;

	}

	public static int getDBVersion() {
		String dbversion = DAOFactory.getInstance().getParameterDAO()
				.getParameter("DBVERSION");

		if (dbversion.length() > 0) {
			return Integer.parseInt(dbversion);
		} else {
			return -1;
		}
	}
}
