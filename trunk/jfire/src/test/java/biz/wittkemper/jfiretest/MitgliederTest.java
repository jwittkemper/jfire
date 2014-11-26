package biz.wittkemper.jfiretest;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Mitglied_Fuehrerschein;
import biz.wittkemper.jfire.utils.DateUtils;

public class MitgliederTest {

	@Ignore
	public void test() {
		HibernateSession.beginTransaction();
		// Fuehrerschein fs = DAOFactory.getInstance().getFuehrerscheinDAO()
		// .load(4l);
		Mitglied mitglied = DAOFactory.getInstance().getMitgliedDAO().load(2l);

		for (Mitglied_Fuehrerschein mf : mitglied.getFuehrerscheins()) {
			System.out.println(mf.getMitglied().getName() + " "
					+ mf.getFuehrerschein().getBezeichnung());
		}
		// Mitglied_Fuehrerschein mf = new Mitglied_Fuehrerschein();
		// mf.setMitglied(mitglied);
		// mf.setFuehrerschein(fs);
		//
		// mitglied.getFuehrerscheins().add(mf);
		// DAOFactory.getInstance().getMitgliedDAO().update(mitglied);
		HibernateSession.commitTransaction();

	}

	@Test
	public void cheBirthday() {
		DateUtils dateUtils = new DateUtils();
		Calendar cal1 = new GregorianCalendar();
		cal1.set(2013, Calendar.DECEMBER, 10);

		Calendar cal2 = new GregorianCalendar();
		cal2.set(1964, 12, 21);

		System.out.println(dateUtils.getAlter(cal2, cal1));
	}
}
