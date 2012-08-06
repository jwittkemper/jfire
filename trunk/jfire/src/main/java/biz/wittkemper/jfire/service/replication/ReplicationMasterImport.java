package biz.wittkemper.jfire.service.replication;

import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Replication;

public class ReplicationMasterImport {

	public void importReplication(Replication replication) {
		if (replication.getMitglied() != null) {
			imporMitglieder(replication.getMitglied());
		}
		if (replication.getFoerdermitglieder() != null) {
			importFoerderMitglieder(replication.getFoerdermitglieder());
		}

	}

	private void importFoerderMitglieder(List<FoerderMitglied> foerdermitglieder) {
		List<FoerderMitglied> flist = DAOFactory.getInstance()
				.getFoerderMitgliedDAO().getAllList();
		for (FoerderMitglied fm : flist) {
			DAOFactory.getInstance().getFoerderMitgliedDAO().delete(fm);
		}

		for (FoerderMitglied fmitglied : foerdermitglieder) {
			Mitglied mg = DAOFactory.getInstance().getMitgliedDAO()
					.load(fmitglied.getMitglied().getMasterId());
			fmitglied.setMitglied(mg);
			DAOFactory.getInstance().getFoerderMitgliedDAO().save(fmitglied);
		}

	}

	private void imporMitglieder(List<Mitglied> mitglieder) {
		for (Mitglied mitglied : mitglieder) {
			if (mitgliedAvailable(mitglied)) {
				updateMitglied(mitglied);
			} else {
				// saveMitglied(mitglied);
			}
		}
		DAOFactory.getInstance().getMitgliedDAO().resetEdit();

	}

	private void updateMitglied(Mitglied mitglied) {
		Mitglied mg = DAOFactory.getInstance().getMitgliedDAO()
				.load(mitglied.getMasterId());

		if (mg != null) {
			mg.mergeMitglied(mitglied);

			DAOFactory.getInstance().getMitgliedDAO().merge(mg);
		}
	}

	private boolean mitgliedAvailable(Mitglied mitglied) {
		boolean lreturn = false;
		Mitglied mg = DAOFactory.getInstance().getMitgliedDAO()
				.load(mitglied.getMasterId());
		if (mg == null) {
			lreturn = false;
		} else {
			lreturn = true;
		}

		return lreturn;
	}
}
