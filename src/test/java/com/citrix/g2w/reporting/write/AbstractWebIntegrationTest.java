/*
 * Copyright (c) 1998-2015 Citrix Online LLC
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO CITRIX ONLINE
 * AND CONSTITUTES A VALUABLE TRADE SECRET.  Any unauthorized use,
 * reproduction, modification, or disclosure of this program is
 * strictly prohibited.  Any use of this program by an authorized
 * licensee is strictly subject to the terms and conditions,
 * including confidentiality obligations, set forth in the applicable
 * License and Co-Branding Agreement between Citrix Online LLC and
 * the licensee.
 */

package com.citrix.g2w.reporting.write;

import lombok.extern.java.Log;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MicroserviceMockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.citrix.g2w.microservice.testsupport.MicroserviceMockMvcBuilder;

/**
 * base web integration test class for common controller config 
 * @author ankit
 *
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = {AbstractWebIntegrationTest.TestConfig.class, ReportingWrite.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class AbstractWebIntegrationTest {

    @Autowired
    protected WebApplicationContext context;

    protected MicroserviceMockMvc mvc;

    @Before
    public void setUp() {
        mvc = MicroserviceMockMvcBuilder.webAppContextSetup(context).
                buildMvc();
    }

    @Configuration
    @PropertySource("classpath:/application-test.yml")
    public static class TestConfig {

    }
}
