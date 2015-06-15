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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import lombok.Getter;
import lombok.Setter;

/**
 * Test Mongo cofiguration to enable all tests
 * to run against {@link com.mongodb.FongoDB}.
 * Refer https://github.com/fakemongo/fongo.
 * @author ankit
 *
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@Getter
@Setter
@Profile({"dev", "test"})
public class MongoConfigurationDev {

    @Bean
    public Mongo mongo() {
        Fongo fongo = new Fongo("fongo");
        fongo.getMongo().setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return fongo.getMongo();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), "fongo");
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongoTemplate;
    }
}
