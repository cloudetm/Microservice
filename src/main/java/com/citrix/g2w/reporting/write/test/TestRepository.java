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
package com.citrix.g2w.reporting.write.test;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TestRepository extends MongoRepository<TestDocument, String> {

    @Query("{ 'webinarkey' : ?0}")
    Page<TestDocument> findByWebinarkey(Long webinarkey, Pageable page);

    @Query("{ 'webinarkey' : ?0}")
    List<TestDocument> findByWebinarkey(long webinarkey);

    @Query("{ 'webinarkey' : ?0, '_id' : ?1 }")
    TestDocument findByWebinarkeyAndObjectkey(long webinarkey, String objectkey);


}
