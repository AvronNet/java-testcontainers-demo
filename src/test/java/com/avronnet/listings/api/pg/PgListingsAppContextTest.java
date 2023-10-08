package com.avronnet.listings.api.pg;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles({"test", "pg"})
public class PgListingsAppContextTest {
    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(PgListingsAppContextTest.class);

    @Test
    void contextLoads() {
        String[] actives = env.getActiveProfiles();
        logger.info(() -> "Active profiles: " + Arrays.toString(actives));
    }
}
