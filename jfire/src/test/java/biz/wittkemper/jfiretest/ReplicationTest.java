package biz.wittkemper.jfiretest;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.JAXBException;

import org.junit.Ignore;
import org.junit.Test;

import biz.wittkemper.jfire.data.entity.Replication;
import biz.wittkemper.jfire.service.replication.ReplicationRead;
import biz.wittkemper.jfire.service.replication.ReplicationReadWorkFlow;
import biz.wittkemper.jfire.service.replication.ReplicationWrite;
import biz.wittkemper.jfire.service.replication.ReplicationWriteWorkflow;

public class ReplicationTest {

	@Ignore
	public void Workflow() {
		ReplicationWriteWorkflow workflow = new ReplicationWriteWorkflow();
		try {
			workflow.Excecute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testRead() {
		ReplicationReadWorkFlow readWorkFlow = new ReplicationReadWorkFlow();
		readWorkFlow.Excecute();
	}

	@Ignore
	public void test() {
		Replication replication = new Replication();

		ReplicationWrite replicationWrite = new ReplicationWrite();
		try {
			replicationWrite.WriteData("replout", replication);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	public void ReadReplication() {
		ReplicationRead read = new ReplicationRead();
		File file = new File("myreplication.jfire");
		try {
			Replication replication = read.ReadData(file);

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
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
}
