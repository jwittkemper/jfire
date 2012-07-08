package biz.wittkemper.jfire.service.replication;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBException;

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.utils.ParameterUtils;
import biz.wittkemper.jfire.utils.SystemUtils;

public class ReplicationReadWorkFlow {

	SystemUtils systemUtils = new SystemUtils();

	public void Excecute() {

		File file = new File(systemUtils.getOpenFileDialog(
				"Wo befindet sich die Datei?", false));
		ReplicationRead read = new ReplicationRead();
		try {
			Replication replication = read.ReadData(file);
			if (plausiReplication(replication)) {
				if (replication.getHerkunft().equals("MASTERDB")) {
					ReplicationSlaveImport si = new ReplicationSlaveImport();
					si.importReplication(replication);
				}
			} else {
				System.out.println("File passt nicht zur Datenbank.....");
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean plausiReplication(Replication replication) {
		boolean lplausiOK = true;
		if (replication.getDbVersion() != ParameterUtils.getDBVersion()) {
			lplausiOK = false;
		}
		if ((replication.getHerkunft().equals("MASTERDB") == true)
				&& (ParameterUtils.isMasterDB() == true)) {
			lplausiOK = false;
		}
		if ((replication.getHerkunft().equals("SLAVEDB") == true)
				&& (ParameterUtils.isMasterDB() == false)) {
			lplausiOK = false;
		}
		return lplausiOK;
	}
}
