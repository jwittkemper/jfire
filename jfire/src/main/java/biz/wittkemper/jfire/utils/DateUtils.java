package biz.wittkemper.jfire.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biz.wittkemper.jfire.data.dao.JPAEntityManager;
import jakarta.persistence.EntityManager;

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

	public void findGeburtstag(JFrame frame) throws Exception {
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

			JOptionPane.showMessageDialog(frame, text.toString(), "Geburtstage in diesem und dem nächsten Monat:",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private StringBuilder getBirthDayData(int monat) throws Exception {

		int monat1 = monat;
		int monat2 = monat1 + 1;

		StringBuilder text = new StringBuilder();
		String sql = "";

		sql = "Select VORNAME , NAME , GEBDATUM , YEAR(CURRENT_DATE) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + monat1 + ", " + monat2
				+ ") AND m.geloescht = :geloescht ORDER BY MONTH (GEBDATUM), DAY (GEBDATUM)";
		text.append(GetSQLData(sql, false));

		return text;
	}

	private StringBuilder getDecemberData() throws Exception {

		StringBuilder text = new StringBuilder();
		String sql = "";

		sql = "Select VORNAME , NAME , GEBDATUM , YEAR(CURRENT_DATE) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + 12
				+ ") AND m.geloescht = :geloescht ORDER BY MONTH (GEBDATUM) ,DAY (GEBDATUM)";

		text.append(GetSQLData(sql, false));

		sql = "Select VORNAME , NAME , GEBDATUM , (YEAR(CURRENT_DATE)+1) - YEAR(GEBDATUM) From Mitglied m ";
		sql += "where m.STATUS_ID IN (1,2) AND MONTH(GEBDATUM) IN (" + 1
				+ ") AND m.geloescht = :geloescht ORDER BY MONTH (GEBDATUM) ,DAY (GEBDATUM)";

		text.append(GetSQLData(sql, false));

		return text;
	}

	private String GetSQLData(String sql, boolean geloescht) throws Exception {

		StringBuilder text = new StringBuilder();
		HashMap<String, Boolean> map = new HashMap<String, Boolean>(); 
		map.put("geloescht", geloescht);
		
		EntityManager em = JPAEntityManager.getCurrentEntityManager();
		
		List<Object[]> results = (List<Object[]>) em.createNativeQuery(sql).setParameter("geloescht", geloescht) .getResultList();

		results.stream().forEach((record) -> {
			String o0 = record[0].toString();
			String o1 = record[1].toString();
			Date o2 = (Date) record[2];
			String o3 = record[3].toString();

			text.append(o1 + ", " + o0 + " (" + getCurDateString(o2) + " ) wird: " + o3 + "\n");
		});
		return text.toString();
	}

	public String getAlter(Calendar cal1, Calendar cal2) {
		if (cal1.get(Calendar.MONTH) >= 11) {
			return (cal2.get(Calendar.YEAR)) - cal1.get(Calendar.YEAR) + " alt.";
		} else {
			return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + " alt.";
		}
	}
}
