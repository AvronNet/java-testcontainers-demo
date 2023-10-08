package com.avronnet.listings.api.h2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class H2ListingsAppContextTest extends H2TestBase {
    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(H2ListingsAppContextTest.class);

    @Test
    void contextLoads() {
        String[] actives = env.getActiveProfiles();
        logger.info(() -> "Active profiles: " + Arrays.toString(actives));
    }
}
