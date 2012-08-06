package biz.wittkemper.jfiretest;

import static org.junit.Assert.*;

import org.junit.Test;

import biz.wittkemper.jfire.utils.SystemUtils;

public class ConfigTest {

	@Test
	public void test() {
		SystemUtils sUtils = new SystemUtils();
		assertTrue(sUtils.getDBAvailable());
	}

}
