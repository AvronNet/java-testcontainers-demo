package com.avronnet.listings.api.testcontainers;

import com.avronnet.listings.IntegrationTestBase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles({"pg"})
public abstract class TestcontainersTestBase extends IntegrationTestBase {
    static final PostgreSQLContainer<?> postgreSQLContainer;
    static final GenericContainer redis;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");
        postgreSQLContainer.start();
        redis = new GenericContainer(DockerImageName.parse("redis:6.2.6-alpine"))
                .withExposedPorts(6379);
        redis.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.data.redis.url", () -> "redis://%s:%s".formatted(redis.getHost(), redis.getMappedPort(6379).toString()));
    }

}
