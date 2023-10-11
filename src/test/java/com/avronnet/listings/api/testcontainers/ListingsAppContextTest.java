package com.avronnet.listings.api.testcontainers;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class ListingsAppContextTest extends TestcontainersTestBase {
    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(ListingsAppContextTest.class);

    @Test
    void contextLoads() {
        String[] actives = env.getActiveProfiles();
        logger.info(() -> "Active profiles: " + Arrays.toString(actives));

        logger.info(() -> "PostgreSQL datasource URL: " + env.getProperty("spring.datasource.url"));
        logger.info(() -> "PostgreSQL username: " + env.getProperty("spring.datasource.username"));
        logger.info(() -> "PostgreSQL password: " + env.getProperty("spring.datasource.password"));
    }
}
