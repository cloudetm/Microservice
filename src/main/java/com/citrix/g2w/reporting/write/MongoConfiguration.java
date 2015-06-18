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

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * Configuration class needed to initialize MongoDB.
 * @author ankit
 *
 */
@Configuration
@EnableMongoRepositories
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
