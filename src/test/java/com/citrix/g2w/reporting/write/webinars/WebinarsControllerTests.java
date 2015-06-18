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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.google.common.collect.Lists;

/**
 * unit test for webinar controller
 * @author ankit
 *
 */
@RunWith(PowerMockRunner.class)
public class WebinarsControllerTests {

    private WebinarsController controller;
    private WebinarRepository webinarRepository;

    @Before
    public void setup(){
        controller = new WebinarsController();
        webinarRepository = EasyMock.createMock(WebinarRepository.class);
        ReflectionTestUtils.setField(controller, "webinarRepository", webinarRepository);
    }
    
    public void doReplay() {
        EasyMock.replay(webinarRepository);
    }
    
    public void doVerify() {
        EasyMock.verify(webinarRepository);
    }
    
    @Test
    public void testGetWebinarNotFound() {
        List<Webinar> webinars = Lists.newArrayList();
        Webinar webinar = new Webinar();
        EasyMock.expect(webinarRepository.insert(EasyMock.anyObject(Webinar.class))).andReturn(webinar);
        EasyMock.expect(webinarRepository.findByWebinarkey(123L)).andReturn(webinars);
        doReplay();
        ResponseEntity<Webinar> webinarResource = controller.getWebinar(123L);
        assertEquals(HttpStatus.NOT_FOUND, webinarResource.getStatusCode());
        doVerify();
    }
    
    @Test
    public void testGetWebinar() {
        List<Webinar> webinars = Lists.newArrayList();
        Webinar webinar = new Webinar();
        webinars.add(webinar);
        EasyMock.expect(webinarRepository.insert(EasyMock.anyObject(Webinar.class))).andReturn(webinar);
        EasyMock.expect(webinarRepository.findByWebinarkey(123L)).andReturn(webinars);
        doReplay();
        ResponseEntity<Webinar> webinarResource = controller.getWebinar(123L);
        assertEquals(HttpStatus.OK, webinarResource.getStatusCode());
        Webinar webinarsResult = webinarResource.getBody();
        assertNotNull(webinarsResult);
        assertEquals(webinar.getWebinarkey(), webinarsResult.getWebinarkey());
        doVerify();
    }
}
