package com.avronnet.listings.api.infobip.testcontainers;

import com.avronnet.listings.IntegrationTestBase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@ActiveProfiles({"pg", "test"})
public abstract class InfobipTestcontainersTestBase extends IntegrationTestBase {

}
