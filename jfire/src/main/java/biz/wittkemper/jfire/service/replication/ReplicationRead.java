package biz.wittkemper.jfire.service.replication;

import java.io.File;
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

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.service.replication.SecurityTools.SECURITYTOOLS;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class ReplicationRead {

	public Replication ReadData(File file) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeySpecException, InvalidAlgorithmParameterException,
			IOException, JAXBException {

		InputStream target = new FileInputStream(file);

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
