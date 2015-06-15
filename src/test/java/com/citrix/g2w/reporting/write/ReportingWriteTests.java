package com.citrix.g2w.reporting.write;/*
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

import static org.powermock.api.easymock.PowerMock.mockStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;

/**
 * test for {@link ReportingWrite}
 * @author ankit
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SpringApplication.class)
@Profile("test")
public class ReportingWriteTests {

    private SpringApplication application;
    @Autowired
    protected ConfigurableApplicationContext context;

    @Before
    public void setup() {
        application = EasyMock.createMock(SpringApplication.class);
    }

    @Test
    public void testLoadMain() {
        String[] args = getArgs();
        mockStatic(SpringApplication.class);
        EasyMock.expect(application.run(ReportingWrite.class, args)).andReturn(context);
        PowerMock.replay(SpringApplication.class);
        ReportingWrite.main(args);
        PowerMock.verify(SpringApplication.class);
    }

    private String[] getArgs() {
        List<String> list = new ArrayList<String>(Arrays.asList(
                "--spring.main.webEnvironment=false", "--spring.main.showBanner=false"));
        return list.toArray(new String[list.size()]);
    }
}
