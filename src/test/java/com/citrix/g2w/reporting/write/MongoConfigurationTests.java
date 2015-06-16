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

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.Mongo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link MongoConfiguration}
 * @author ankit
 *
 */
public class MongoConfigurationTests {

    private MongoConfiguration mongoConfiguration;

    @Before
    public void setup() {
        mongoConfiguration = new MongoConfiguration();
        ReflectionTestUtils.setField(mongoConfiguration, "database", "test");
        ReflectionTestUtils.setField(mongoConfiguration, "hosts", Arrays.asList("localhost"));
        ReflectionTestUtils.setField(mongoConfiguration, "port", 8080);
        ReflectionTestUtils.setField(mongoConfiguration, "username", "username");
        ReflectionTestUtils.setField(mongoConfiguration, "password", "password");
    }

    @Test
    public void testGetMongoTemplate() throws UnknownHostException {
        MongoTemplate aTemplate = mongoConfiguration.mongoTemplate(mongoConfiguration.mongo());
        assertThat(aTemplate, is(notNullValue()));
    }

    @Test
    public void testGetLocalValidator() {
        LocalValidatorFactoryBean aBean = mongoConfiguration.validator();
        assertThat(aBean, is(notNullValue()));
    }

    @Test
    public void testGetValidatingMongoEventListener() {
        ValidatingMongoEventListener mongoEventListener = mongoConfiguration.validatingMongoEventListener();
        assertThat(mongoEventListener, is(notNullValue()));

    }

    @Test
    public void testGetSuccessfulMongoClient() throws UnknownHostException {
        Mongo aMongo = mongoConfiguration.mongo();
        assertThat(aMongo, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMongoTemplateWithNullDatabaseName() throws UnknownHostException {
    	ReflectionTestUtils.setField(mongoConfiguration, "database", null);
        mongoConfiguration.mongoTemplate(mongoConfiguration.mongo());
    }

    @Test(expected = NullPointerException.class)
    public void testGetMongoClientWithNullHost() throws UnknownHostException {
    	ReflectionTestUtils.setField(mongoConfiguration, "hosts", null);
        Mongo aMongo = mongoConfiguration.mongo();
        assertThat(aMongo, is(notNullValue()));
    }

    @Test
    public void testGetMongoClientWithNegativePort() throws UnknownHostException {
    	ReflectionTestUtils.setField(mongoConfiguration, "port", -1);
        Mongo aMongo = mongoConfiguration.mongo();
        assertThat(aMongo, is(notNullValue()));
    }

}
