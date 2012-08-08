package biz.wittkemper.jfire.service.replication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.service.replication.SecurityTools.SECURITYTOOLS;

public class ReplicationWrite {

	public void WriteData(File fileName, Replication replication)
			throws JAXBException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeySpecException, InvalidAlgorithmParameterException,
			IOException {

		JAXBContext jc;

		jc = JAXBContext.newInstance("biz.wittkemper.jfire.data.entity");

		Marshaller ms = jc.createMarshaller();
		ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		if (fileName.exists() == false) {
			fileName.createNewFile();
		}
		OutputStream target = new FileOutputStream(fileName);

		target = new CipherOutputStream(target,
				SecurityTools.getCipher(SECURITYTOOLS.encrypt));
		ZipOutputStream zout = new ZipOutputStream(target);
		zout.setMethod(ZipOutputStream.DEFLATED);

		ZipEntry entry = new ZipEntry(fileName + ".xml");
		zout.putNextEntry(entry);

		ms.marshal(replication, zout);
		zout.flush();
		zout.closeEntry();
		zout.close();

	}

}
