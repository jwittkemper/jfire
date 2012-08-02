package biz.wittkemper.jfire.service.replication;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.utils.DateUtils;
import biz.wittkemper.jfire.utils.ParameterUtils;
import biz.wittkemper.jfire.utils.SystemUtils;

public class ReplicationReadWorkFlow {

	SystemUtils systemUtils = new SystemUtils();

	public void Excecute(JFrame frame) {

		File file = new File(systemUtils.getOpenFileDialog(
				"Wo befindet sich die Datei?", false,
				new FileNameExtensionFilter("JFire Daten(*.jfire)", "jfire")));

		if (file != null) {
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

			frame.setCursor(hourglassCursor);

			ReplicationRead read = new ReplicationRead();
			try {
				Replication replication = read.ReadData(file);
				if (plausiReplication(replication)) {
					if (replication.getHerkunft().equals("MASTERDB")) {
						ReplicationSlaveImport si = new ReplicationSlaveImport();
						si.importReplication(replication);

						String newName = DateUtils.getCurDateString();
						newName = newName.replace(".", "-");
						newName = newName.replace(":", "-");
						newName = newName.replace(" ", "_");
						newName = file.getAbsolutePath() + "_imported"
								+ newName;
						File newFile = new File(newName);
						file.renameTo(newFile);

						frame.setCursor(defaultCursor);
						JOptionPane.showMessageDialog(frame,
								"Import erfolgreich durchgelaufen.",
								"Daten Import", JOptionPane.INFORMATION_MESSAGE
										+ JOptionPane.OK_OPTION);
					} else {
						ReplicationMasterImport mi = new ReplicationMasterImport();
						mi.importReplication(replication);

						String newName = DateUtils.getCurDateString();
						newName = newName.replace(".", "-");
						newName = newName.replace(":", "-");
						newName = newName.replace(" ", "_");
						newName = file.getAbsolutePath() + "_imported"
								+ newName;
						File newFile = new File(newName);
						file.renameTo(newFile);

						frame.setCursor(defaultCursor);
						JOptionPane.showMessageDialog(frame,
								"Import erfolgreich durchgelaufen.",
								"Daten Import", JOptionPane.INFORMATION_MESSAGE
										+ JOptionPane.OK_OPTION);
					}
				} else {
					frame.setCursor(defaultCursor);
					JOptionPane
							.showMessageDialog(
									frame,
									"Import kann nicht ausgeführt werden. Die Datendatei ist nicht plausibel!",
									"Import fehlgeschlagen.",
									JOptionPane.ERROR_MESSAGE
											+ JOptionPane.OK_OPTION);
				}
			} catch (InvalidKeyException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);

			} catch (NoSuchAlgorithmException e) {

				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} catch (NoSuchPaddingException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} catch (InvalidKeySpecException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} catch (InvalidAlgorithmParameterException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} catch (JAXBException e) {
				JOptionPane.showMessageDialog(
						frame,
						"Import kann nicht ausgeführt werden.\n"
								+ e.getMessage(), "Import fehlgeschlagen",
						JOptionPane.ERROR_MESSAGE + JOptionPane.OK_OPTION);
			} finally {
				frame.setCursor(defaultCursor);
			}
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
