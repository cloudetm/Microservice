package com.citrix.g2w.reporting.write.webinars;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WebinarRepository extends MongoRepository<Webinar, String> {

    @Query("{ 'webinarkey' : ?0}")
    List<Webinar> findByWebinarkey(long webinarkey);

}
