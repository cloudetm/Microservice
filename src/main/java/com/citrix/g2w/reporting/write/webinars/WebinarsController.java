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

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citrix.g2w.reporting.write.BaseController;

/**
 * controller for webinar collection.
 * 
 * @author ankit
 *
 */
@RestController
@Getter
@Setter
@Slf4j
public class WebinarsController extends BaseController {

    public static final String BASE_URL = "/webinars";

    @Autowired
    private WebinarRepository webinarRepository;

    /**
     * get webinars by webinar key.
     * 
     * @param webinarKey
     * @return
     */
    @RequestMapping(value = BASE_URL + "/{webinarKey}", method = RequestMethod.GET, produces = {
            "application/hal+json;charset=UTF-8",
            "application/json;charset=UTF-8" })
    public ResponseEntity<Resources<Webinar>> getWebinar(
            @PathVariable long webinarKey) {

        List<Webinar> webinars = webinarRepository.findByWebinarkey(webinarKey);
        if (CollectionUtils.isEmpty(webinars)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resources<Webinar> webinarResource = new Resources<Webinar>(webinars);
        return new ResponseEntity<>(webinarResource, HttpStatus.OK);
    }

}
