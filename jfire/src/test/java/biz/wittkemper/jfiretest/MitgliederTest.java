package biz.wittkemper.jfiretest;

import org.junit.Test;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.dao.HibernateSession;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Mitglied_Fuehrerschein;

public class MitgliederTest {

	@Test
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
}
