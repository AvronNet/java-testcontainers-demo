package com.avronnet.listings;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootTest
class ListingsApplicationTests {

	@Autowired
	private Environment env;

	Logger logger = LoggerFactory.getLogger(ListingsApplicationTests.class);

	@Test
	void contextLoads() {
		String[] actives = env.getActiveProfiles();
		logger.info(() -> "Active profiles: " + Arrays.toString(actives));
	}

}
