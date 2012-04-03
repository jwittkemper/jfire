package biz.wittkemper.jfire.service.replication;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBException;

import org.apache.derby.iapi.services.daemon.DaemonFactory;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.utils.ParameterUtils;

public class ReplicationWriteWorkflow {

	public void Excecute() throws Exception{
		Replication replication = new Replication();

		getdReplicationProperty(replication);
		getMitglieder(replication);
		getFoederMitglieder(replication);
		ReplicationWrite replicationWrite = new ReplicationWrite();

		replicationWrite.WriteData("myreplication", replication);

	}

	private void getFoederMitglieder(Replication replication) {
		List<FoerderMitglied> foerderMitglieds = DAOFactory.getInstance()
				.getFoerderMitgliedDAO()
				.findByQueryString("From FoerderMitglied where 1=1");

		replication.setFoerdermitglieder(foerderMitglieds);
	}

	private void getMitglieder(Replication replication) {
		List<Mitglied> mitglieds = DAOFactory.getInstance().getMitgliedDAO()
				.findByQueryString("From Mitglied where 1=1");

		replication.setMitglied(mitglieds);

	}

	private void getdReplicationProperty(Replication replication) {
		if (ParameterUtils.isMasterDB()) {
			replication.setHerkunft("MASTERDB");
		} else {
			replication.setHerkunft("SLAVEDB");
		}
		replication.setDbVersion(ParameterUtils.getDBVersion());
	}

}
