package biz.wittkemper.jfire.service.replication;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityTools {

	public enum SECURITYTOOLS {
		encrypt, decrypt
	}

	public static Cipher getCipher(SECURITYTOOLS sectools)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidKeySpecException,
			InvalidAlgorithmParameterException {

		String alg = "PBEWithSHA1AndDESede";
		PBEKeySpec keySpec = new PBEKeySpec("wache14".toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance("PBEWithSHA1AndDESede");

		switch (sectools) {
		case decrypt:
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new PBEParameterSpec(
					"saltsalt".getBytes(), 2000));

			break;
		case encrypt:
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, new PBEParameterSpec(
					"saltsalt".getBytes(), 2000));
			break;
		}
		return cipher;
	}
}
