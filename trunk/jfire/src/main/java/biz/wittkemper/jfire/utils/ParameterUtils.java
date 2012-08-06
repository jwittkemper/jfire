package biz.wittkemper.jfire.utils;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;

public class ParameterUtils {

	public static boolean isMasterDB() {
		boolean lreturn;
		boolean ltransactionstart = false;

		if (!HibernateSession.getCurrentSession().getTransaction().isActive()) {
			HibernateSession.beginTransaction();
			ltransactionstart = true;
		}

		String dbversion = DAOFactory.getInstance().getParameterDAO()
				.getParameter("DBTYP");
		if (ltransactionstart) {
			HibernateSession.commitTransaction();
		}
		if (dbversion.toLowerCase().equals("masterdb")) {
			lreturn = true;
		} else {
			lreturn = false;
		}
		return lreturn;

	}

	public static int getDBVersion() {
		boolean ltransactionstart = false;

		if (!HibernateSession.getCurrentSession().getTransaction().isActive()) {
			HibernateSession.beginTransaction();
			ltransactionstart = true;
		}
		String dbversion = DAOFactory.getInstance().getParameterDAO()
				.getParameter("DBVERSION");
		if (ltransactionstart) {
			HibernateSession.commitTransaction();
		}
		if (dbversion.length() > 0) {
			return Integer.parseInt(dbversion);
		} else {
			return -1;
		}
	}
}
