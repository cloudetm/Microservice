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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citrix.g2w.reporting.events.webinars.WebinarReassignmentEvent;
import com.citrix.g2w.reporting.write.AbstractIntegrationTest;

/**
 * integration test for webinarreassignment listener.
 * @author ankit
 *
 */
public class WebinarReassignmentListenerIntegrationTests extends AbstractIntegrationTest {

	@Autowired
	private WebinarReassignmentEventListener eventListener;
	
    @Test
    public void testConsumeEvent() throws Exception {
    	WebinarReassignmentEvent event = new WebinarReassignmentEvent();
    	eventListener.onMessage(event);
    	//TODO assert read apis
    }
}