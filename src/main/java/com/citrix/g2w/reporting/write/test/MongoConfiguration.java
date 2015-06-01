package com.citrix.g2w.reporting.write.test;

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

import java.net.UnknownHostException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * Configuration class needed to initialize MongoDB
 *
 * @author KK (Kaustubh Keshav)
 * @version 0.1 - 4/13/15
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@Getter
@Setter
public class MongoConfiguration  {

    private String database = "mydb";

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, getDatabaseName());
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongoTemplate;
    }

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    public Mongo mongo() throws UnknownHostException {
        Mongo mongo = new MongoClient();
        mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongo;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        ValidatingMongoEventListener validatingMongoEventListener = new ValidatingMongoEventListener(validator());
        return validatingMongoEventListener;
    }
}