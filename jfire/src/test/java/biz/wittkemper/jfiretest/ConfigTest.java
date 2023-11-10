package biz.wittkemper.jfiretest;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import biz.wittkemper.jfire.utils.SystemUtils;

@RunWith(EasyMockRunner.class)
public class ConfigTest {

	@Mock
	SystemUtils sUtils;

	
	public void test() {

		EasyMock.expect(sUtils.getDBAvailable()).andReturn(true);
		EasyMock.replay(sUtils);

		Assert.assertTrue(sUtils.getDBAvailable());
	}

}
