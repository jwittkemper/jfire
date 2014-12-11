package biz.wittkemper.jfire.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.wittkemper.jfire.data.dao.HibernateSession;

public class DateUtils {
	static boolean wasrun = false;
	final static String format = "dd-MMM-yyyy";

	final static String formatlong = "dd.MMM.yyyy HH:mm";

	public static Date getDate(String date) throws ParseException {
		return new SimpleDateFormat(format).parse(date);
	}

	public static String getCurDateString() {
		Date date = new Date();
		return new SimpleDateFormat(formatlong).format(date);
	}

	public static String getCurDateString(Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static int getJahr() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	public void findGeburtstag(JFrame frame) throws ParseException {
		String sql = "";
		StringBuilder text = new StringBuilder();

		if (wasrun == false) {
			wasrun = true;
			GregorianCalendar now = new GregorianCalendar();

			int monat = 1 + now.get(Calendar.MONTH);

			if (monat == 12) {

				text = getDecemberData();

			} else {
				text = getBirthDayData(monat);

			}

			JOptionPane.showMessageDialog(frame, text.toString(),
					"Geburtstage in diesem und dem nächsten Monat:",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private StringBuilder getBirthDayData(int monat) {

		StringBuilder text = new StringBuilder();
		String sql = "";

		sql = "Select VORNAME , NAME , GEBDATUM , YEAR(CURRENT_DATE) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + monat
				+ ", " + monat + 1
				+ ") ORDER BY MONTH (GEBDATUM), DAY (GEBDATUM)";
		text.append(GetSQLData(sql));

		return text;
	}

	private StringBuilder getDecemberData() {

		StringBuilder text = new StringBuilder();
		String sql = "";

		sql = "Select VORNAME , NAME , GEBDATUM , YEAR(CURRENT_DATE) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + 12
				+ ") ORDER BY MONTH (GEBDATUM) ,DAY (GEBDATUM)";

		text.append(GetSQLData(sql));

		sql = "Select VORNAME , NAME , GEBDATUM , (YEAR(CURRENT_DATE)+1) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + 1
				+ ") ORDER BY MONTH (GEBDATUM) ,DAY (GEBDATUM)";

		text.append(GetSQLData(sql));

		return text;
	}

	private String GetSQLData(String sql) {

		StringBuilder text = new StringBuilder();

		HibernateSession.beginTransaction();
		Session session = HibernateSession.getCurrentSession();
		Query query = session.createSQLQuery(sql);

		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			text.append(row[1].toString() + ", " + row[0].toString() + " "
					+ "(" + getCurDateString((Date) row[2]) + ") wird: "
					+ row[3].toString() + "\n");
		}
		return text.toString();
	}

	public String getAlter(Calendar cal1, Calendar cal2) {
		if (cal1.get(Calendar.MONTH) >= 11) {
			return (cal2.get(Calendar.YEAR)) - cal1.get(Calendar.YEAR)
					+ " alt.";
		} else {
			return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + " alt.";
		}
	}
}
