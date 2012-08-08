package biz.wittkemper.jfire.service.replication;

import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Replication;

public class ReplicationSlaveImport {

	public void importReplication(Replication replication) {

		imporMitglieder(replication.getMitglied());
		importFoerderMitglieder(replication.getFoerdermitglieder());

	}

	private void importFoerderMitglieder(List<FoerderMitglied> foerdermitglieder) {

		List<FoerderMitglied> flist = DAOFactory.getInstance()
				.getFoerderMitgliedDAO().getAllList();
		for (FoerderMitglied fm : flist) {
			DAOFactory.getInstance().getFoerderMitgliedDAO().delete(fm);
		}
		for (FoerderMitglied fmitglied : foerdermitglieder) {
			Mitglied mg = DAOFactory.getInstance().getMitgliedDAO()
					.getByMasterID(fmitglied.getMitglied().getId());
			fmitglied.setMitglied(mg);
			DAOFactory.getInstance().getFoerderMitgliedDAO().save(fmitglied);
		}

	}

	private void imporMitglieder(List<Mitglied> mitglieder) {
		for (Mitglied mitglied : mitglieder) {
			if (mitgliedAvailable(mitglied)) {
				updateMitglied(mitglied);
			} else {
				saveMitglied(mitglied);
			}
		}
		DAOFactory.getInstance().getMitgliedDAO().resetEdit();

	}

	private void saveMitglied(Mitglied mitglied) {
		mitglied.setMasterId(mitglied.getId());
		mitglied.setId(null);
		DAOFactory.getInstance().getMitgliedDAO().save(mitglied);

	}

	private void updateMitglied(Mitglied mitglied) {
		Mitglied mg = DAOFactory.getInstance().getMitgliedDAO()
				.getByMasterID(mitglied.getId());

		if (mg != null) {
			mg.mergeMitglied(mitglied);

			DAOFactory.getInstance().getMitgliedDAO().merge(mg);
		}
	}

	private boolean mitgliedAvailable(Mitglied mitglied) {
		boolean lreturn = false;
		List<Mitglied> mg = DAOFactory
				.getInstance()
				.getMitgliedDAO()
				.findByQueryString(
						" FROM Mitglied m WHERE m.masterId = "
								+ mitglied.getId());
		if (mg == null || mg.size() == 0 || mg.size() > 1) {

		} else {
			lreturn = true;
		}

		return lreturn;
	}

}
