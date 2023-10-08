package com.avronnet.listings.api.pg;

import com.avronnet.listings.IntegrationTestBase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test", "pg"})
public class PostgresTestBase extends IntegrationTestBase {
}
