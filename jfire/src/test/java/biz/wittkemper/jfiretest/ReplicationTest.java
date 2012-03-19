package biz.wittkemper.jfiretest;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.junit.Ignore;
import org.junit.Test;

import biz.wittkemper.jfire.service.replication.ReplicationRead;
import biz.wittkemper.jfire.service.replication.ReplicationWrite;

public class ReplicationTest {

	@Test
	public void test() {
		ReplicationWrite replicationWrite = new ReplicationWrite();
		replicationWrite.WriteData("replout");
	}

	@Test
	public void ReadReplication(){
		ReplicationRead read = new ReplicationRead();
		read.ReadData("replout");
	}
}
