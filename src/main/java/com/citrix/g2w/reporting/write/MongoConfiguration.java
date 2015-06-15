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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import lombok.Getter;
import lombok.Setter;

/**
 * Configuration class needed to initialize MongoDB.
 * @author ankit
 *
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Profile({ "ED", "RC", "STAGE", "LIVE" })
public class MongoConfiguration extends AbstractMongoConfiguration {

    private String database;
    private List<String> hosts;
    private String username;
    private String password;
    private int port;

    @Bean
    public MongoTemplate mongoTemplate(final Mongo mongo) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo,
                getDatabaseName());
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongoTemplate;
    }

    /**
     * Return the name of the database to connect to.
     *
     * @return must not be {@literal null}.
     */
    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws UnknownHostException {
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                username, database, password.toCharArray());
        List<ServerAddress> hostList = Lists.newArrayList();
        for (String aHost : hosts) {
            hostList.add(new ServerAddress(aHost, port));
        }
        Mongo mongo = new MongoClient(hostList, Arrays.asList(credential));
        mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongo;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        ValidatingMongoEventListener validatingMongoEventListener = 
        		new ValidatingMongoEventListener(validator());
        return validatingMongoEventListener;
    }

    protected void setDatabase(final String database) {
        this.database = database;
    }

    protected void setHosts(final List<String> hosts) {
        this.hosts = hosts;
    }

    protected void setPassword(final String password) {
        this.password = password;
    }

    protected void setUsername(final String username) {
        this.username = username;
    }

    protected void setPort(final int port) {
        this.port = port;
    }
}
