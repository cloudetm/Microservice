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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.citrix.g2w.reporting.events.webinars.WebinarReassignmentEvent;
import com.citrix.g2w.reporting.write.webinars.WebinarReassignmentEventListener;
import com.citrix.queue.support.PollingMessageListenerContainer;
import com.citrix.queue.support.QueueService;

/**
 * Tests for {@link QueueConfiguration}
 * 
 * @author ankit
 *
 */
public class QueueConfigurationTests {

	private QueueConfiguration queueConfiguration;

	@Before
	public void setup() {
		queueConfiguration = new QueueConfiguration();
		ReflectionTestUtils.setField(queueConfiguration, "prefix", "test");
		ReflectionTestUtils.setField(queueConfiguration, "listener", "dummy");
		ReflectionTestUtils.setField(queueConfiguration, "servers", "local");
		ReflectionTestUtils
		        .setField(queueConfiguration, "readTimeout", 10);
		ReflectionTestUtils
		        .setField(queueConfiguration, "connectTimeout", 10);
	}

	@Test
	public void testGetWebinarReassignmentEventContainer() {
		PollingMessageListenerContainer<WebinarReassignmentEvent> container = queueConfiguration.webinarReassignmentEventContainer();
		assertThat(container, is(notNullValue()));
	}
	
	@Test
	public void testGetWebinarReassignmentEventListener() throws UnknownHostException {
		WebinarReassignmentEventListener listener = queueConfiguration.webinarReassignmentEventListener();
		assertThat(listener, is(notNullValue()));
	}
	
	@Test
	public void testGetQueueService() {
		QueueService service = queueConfiguration.queueService();
		assertThat(service, is(notNullValue()));
	}
	
	@Test
	public void testGetHttpRequestFactory() throws UnknownHostException {
		SimpleClientHttpRequestFactory httpRequestFactory = queueConfiguration.httpRequestFactory();
		assertThat(httpRequestFactory, is(notNullValue()));
	}
	
	@Test
	public void testGetRestTemplate() throws UnknownHostException {
		RestTemplate restTemplate = queueConfiguration.jsonRestTemplate();
		assertThat(restTemplate, is(notNullValue()));
	}

}
