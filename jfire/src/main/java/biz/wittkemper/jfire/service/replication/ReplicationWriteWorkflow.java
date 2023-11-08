package biz.wittkemper.jfire.service.replication;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.utils.ParameterUtils;

public class ReplicationWriteWorkflow {

	public void Excecute(File file, JFrame view) throws Exception {
		Replication replication = new Replication();

	
		try {
			getdReplicationProperty(replication);
			getMitglieder(replication);
			getFoederMitglieder(replication);

			ReplicationWrite replicationWrite = new ReplicationWrite();

			replicationWrite.WriteData(file, replication);
			
			JOptionPane.showMessageDialog(view, "Daten erfolgreich exportiert",
					"Datenexport", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(view, "Datenexport fehlgeschlagen!\n"
					+ e.getMessage(), "Datenexport", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void getFoederMitglieder(Replication replication) {
		List<FoerderMitglied> foerderMitglieds = DAOFactory.getInstance()
				.getFoerderMitgliedDAO()
				.findByQueryString("From FoerderMitglied where 1=1", null);

		replication.setFoerdermitglieder(foerderMitglieds);
	}

	private void getMitglieder(Replication replication) throws Exception {
		List<Mitglied> mitglieds;
		if (ParameterUtils.isMasterDB() != true) {
			mitglieds = DAOFactory.getInstance().getMitgliedDAO()
					.findByQueryString("From Mitglied where edit=1",null);

		} else {

			mitglieds = DAOFactory.getInstance().getMitgliedDAO()
					.findByQueryString("From Mitglied where 1=1",null);

		}
		replication.setMitglied(mitglieds);
		DAOFactory.getInstance().getMitgliedDAO().resetEdit();
	}

	private void getdReplicationProperty(Replication replication)
			throws Exception {
		if (ParameterUtils.isMasterDB()) {
			replication.setHerkunft("MASTERDB");
		} else {
			replication.setHerkunft("SLAVEDB");
		}
		replication.setDbVersion(ParameterUtils.getDBVersion());
	}

}
