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

package com.citrix.g2w.reporting.write.webinars;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller for webinar collection.
 * @author ankit
 *
 */
@RestController
@RequestMapping("/webinars")
@Slf4j
public class WebinarsController {

    @Autowired
    private WebinarRepository webinarRepository;
    
    @Autowired 
    private EntityLinks entityLinks;

    /**
     * get webinars by webinar key.
     * @param webinarKey
     * @return
     */
    @RequestMapping(value = "/{webinarKey}", method = RequestMethod.GET, 
            produces = {"application/hal+json;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Resource<Webinar>> getWebinar(
            @PathVariable final long webinarKey) {
    	Webinar dummy  = new Webinar();
    	dummy.setWebinarkey(webinarKey);
    	webinarRepository.insert(dummy);
        List<Webinar> webinars = webinarRepository.findByWebinarkey(webinarKey);
        if (CollectionUtils.isEmpty(webinars)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("getting resorce");
        Webinar webinar = webinars.get(0);
        Link link = entityLinks.linkToSingleResource(Webinar.class, webinarKey);
        Resource<Webinar> resource = new Resource<Webinar>(webinar, link);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
}
