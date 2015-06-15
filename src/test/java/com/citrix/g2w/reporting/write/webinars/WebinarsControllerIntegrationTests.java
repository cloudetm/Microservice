/*
 * Copyright (c) 2015 Citrix Online LLC
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
package com.citrix.g2w.reporting.write.webinars;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MicroserviceMockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.citrix.g2w.microservice.testsupport.MicroserviceMockMvcBuilder;
import com.citrix.g2w.reporting.write.AbstractIntegrationTest;

/**
 * integration test for webinar resource
 * @author ankit
 *
 */
public class WebinarsControllerIntegrationTests extends AbstractIntegrationTest {

    protected MicroserviceMockMvc mvc;
    @Autowired
    protected WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MicroserviceMockMvcBuilder.webAppContextSetup(context).
                buildMvc();
    }

    /**
     * For a sanity check we only want to test that the annotation is in play, getting a 403 will verify that
     */
    @Test
    public void getWebinarNotFound() throws Exception {
        mvc.perform(get("/webinars/1234")).
                andDo(MockMvcResultHandlers.print()).
                andExpect(status().isNotFound());
    }
}