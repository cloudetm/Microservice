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

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citrix.g2w.reporting.write.webinars.WebinarsController;

/**
 * base controller for all resources.
 * @author ankit
 *
 */
@RestController
@RequestMapping("/")
@Getter
@Setter
@Slf4j
public class BaseController implements
        ResourceProcessor<RepositoryLinksResource> {

    @NotNull
    private String baseURL;

    /**
     * add resources.
     */
    @Override
    public RepositoryLinksResource process(
    		final RepositoryLinksResource resource) {
        resource.add((new Link(baseURL + WebinarsController.BASE_URL
                + "/{webinarkey}")));
        return resource;
    }
}
