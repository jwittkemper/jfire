package biz.wittkemper.jfire.service.replication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.service.replication.SecurityTools.SECURITYTOOLS;

public class ReplicationRead {

	public Replication ReadData(String filename) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeySpecException, InvalidAlgorithmParameterException,
			IOException, JAXBException {

		InputStream target = new FileInputStream(filename + ".zip");

		target = new CipherInputStream(target,
				SecurityTools.getCipher(SECURITYTOOLS.decrypt));

		ZipInputStream input = new ZipInputStream(target);

		ZipEntry entry = input.getNextEntry();

		Unmarshaller unmarshaller = JAXBContext.newInstance(
				"biz.wittkemper.jfire.data.entity").createUnmarshaller();

		JAXBElement<Replication> rep = (JAXBElement<Replication>) unmarshaller
				.unmarshal(input);

		Replication replication = rep.getValue();

		return replication;

	}

}
