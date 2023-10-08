package com.avronnet.listings.api.h2;

import com.avronnet.listings.IntegrationTestBase;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"test", "h2"})
public class H2TestBase extends IntegrationTestBase {
}
