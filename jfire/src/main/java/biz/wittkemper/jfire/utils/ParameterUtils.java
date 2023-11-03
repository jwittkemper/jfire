package biz.wittkemper.jfire.utils;

import org.hibernate.JDBCException;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import biz.wittkemper.jfire.data.dao.DAOFactory;

public class ParameterUtils {

	public static boolean isMasterDB() throws Exception {
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

	public static int getDBVersion() throws JDBCException, Exception {
		
		String dbversion = DAOFactory.getInstance().getParameterDAO()
				.getParameter("DBVERSION");

		if (dbversion.length() > 0) {
			return Integer.parseInt(dbversion);
		} else {
			return -1;
		}

	}
}
