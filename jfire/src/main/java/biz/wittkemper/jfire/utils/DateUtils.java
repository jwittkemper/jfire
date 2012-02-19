package biz.wittkemper.jfire.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;

public class DateUtils {
	static boolean wasrun = false;
	static SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
	static SimpleDateFormat formatlong = new SimpleDateFormat(
			"dd.MMM.yyyy HH:mm");

	public static Date getDate(String date) throws ParseException {
		return format.parse(date);
	}

	public static String getCurDateString() {
		Date date = new Date();
		return formatlong.format(date);
	}

	public static String getCurDateString(Date date) {
		return format.format(date);
	}

	public void findGeburtstag(JFrame frame) {

		if (wasrun == false) {
			wasrun=true;
			Calendar cal1 = new GregorianCalendar();
			Calendar cal2 = new GregorianCalendar();
			cal2.setTime(new Date());
			StringBuilder text = new StringBuilder();

			String hql = "From Mitglied m where m.status.id In (1,2)";
			hql += " and Month(m.gebDatum)=" + (cal2.get(Calendar.MONTH) + 1);
			hql += "Order by DAY(m.gebDatum)";

			List<Mitglied> liste = DAOFactory.getInstance().getMitgliedDAO()
					.findByQueryString(hql);

			if (liste.size() > 0) {
				for (Mitglied m : liste) {
					cal1.setTime(m.getGebDatum());
					text.append(m.getName()
							+ ", "
							+ m.getVorname()
							+ ", "
							+ m.getStatus().getBezeichnungLang()
							+ ", "
							+ getCurDateString(m.getGebDatum())
							+ ", wird: "
							+ (cal2.get(Calendar.YEAR)
									- cal1.get(Calendar.YEAR) + " alt.") + "\n");
				}

				JOptionPane.showMessageDialog(frame, text.toString(),
						"Geburtstage in diesem Monat:",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
