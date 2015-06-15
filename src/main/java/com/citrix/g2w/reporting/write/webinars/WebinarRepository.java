package com.citrix.g2w.reporting.write.webinars;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Webinar repository for mongo db.
 * @author ankit
 *
 */
public interface WebinarRepository extends CustomWebinarRepository, MongoRepository<Webinar, String> {

    /**
     * find webinars by key.
     * @param webinarkey
     * @return
     */
    @Query("{ 'webinarkey' : ?0}")
    List<Webinar> findByWebinarkey(long webinarkey);

}
