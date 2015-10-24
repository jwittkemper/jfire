package biz.wittkemper.jfire.utils;

import org.hibernate.JDBCException;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;

public class ParameterUtils {

	public static boolean isMasterDB() throws Exception {
		boolean lreturn;
		boolean ltransactionstart = false;

		if (HibernateSession.getCurrentSession().getTransaction().getStatus() != TransactionStatus.ACTIVE ) {
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

	public static int getDBVersion() throws JDBCException, Exception {
		boolean ltransactionstart = false;

		if (HibernateSession.getCurrentSession().getTransaction().getStatus() != TransactionStatus.ACTIVE) {
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
