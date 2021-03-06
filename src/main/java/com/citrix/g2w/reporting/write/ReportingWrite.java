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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class for reporting write service.
 * @author ankit
 *
 */
@SpringBootApplication
public class ReportingWrite {

    /**
     * start the application.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ReportingWrite.class, args);
    }

}
