package com.avronnet.listings.api.h2;

import com.avronnet.listings.IntegrationTestBase;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"h2"})
public class H2TestBase extends IntegrationTestBase {
}
