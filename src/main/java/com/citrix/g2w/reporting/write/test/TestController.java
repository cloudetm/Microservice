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

package com.citrix.g2w.reporting.write.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Setter
@Slf4j
public class TestController extends BaseController {

	public static final String BASE_URL = "/webinars/test";

	@Autowired
	private TestRepository testRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(value = BASE_URL, method = RequestMethod.POST, produces = {
			"application/hal+json;charset=UTF-8",
			"application/json;charset=UTF-8" })
	public ResponseEntity<Resource<TestDocument>> getTestDoc(
			HttpServletRequest request) {
		TestDocument testDoc = new TestDocument("12", "type", 23L,
				34L, 45L, 56L, "67", "78");

		testDoc = testRepository.save(testDoc);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("bytes").is(56L));

		Update update = new Update();
		update.set("webinarkey", 3);
		mongoTemplate.upsert(query, update, TestDocument.class);
		List<TestDocument> docs1 = testRepository.findByWebinarkey(23);
		
		List<TestDocument> docs2 = testRepository.findByWebinarkey(3);
		
		List<TestDocument> docs3 = testRepository.findAll();
		if (testDoc == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Resource testDocResource = new Resource<>(testDoc);

		return new ResponseEntity<>(testDocResource, HttpStatus.OK);
	}

}
