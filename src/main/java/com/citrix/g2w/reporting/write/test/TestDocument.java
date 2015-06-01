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

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.rest.core.annotation.Description;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "test")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDocument implements Serializable {

    @Description("Name of the testDoc file 255 + 4 (file extension)")
    private String filename;

    @Description("MIME Type of the testDoc file")
    private String filetype;

    @Description("Webinar key to which testDoc is associated")
    @Indexed
    private Long webinarkey;

    @Description("Organizer key of the organizer who uploaded the testDoc")
    @CreatedBy
    @LastModifiedBy
    private Long organizerkey;

    @Description("Account key to which organizer belongs")
    @CreatedBy
    @LastModifiedBy
    private Long accountkey;

    @Description("File size in bytes")
    private Long bytes;

    @Description("Primary Key - Cloud storage reference key for a testDoc")
    @Id
    @TextIndexed
    @Indexed(unique = true)
    @Field(value = "objectkey")
    private String objectkey;

    @Description("Actual download url of the file in cloud")
    @JsonIgnore
    private String downloadurl;

    @Transient
    private String path;

    //marks the constructor to use when instantiating the object from the database.
    //Constructor arguments are mapped by name to the key values in the retrieved DBObject.
    //also refer - http://stackoverflow.com/a/13834796/1334148
    //This is also used by builder for tests
    @PersistenceConstructor
    public TestDocument(@Value("#root.filename") String fileName,
                   @Value("#root.filetype") String fileType,
                   @Value("#root.webinarkey") Long webinarKey,
                   @Value("#root.organizerkey") Long organizerKey,
                   @Value("#root.accountkey") Long accountKey,
                   @Value("#root.bytes") Long bytes,
                   @Value("#root.objectkey") String objectkey,
                   @Value("#root.downloadurl") String downloadurl) {
        this.filename = fileName;
        this.filetype = fileType;
        this.webinarkey = webinarKey;
        this.organizerkey = organizerKey;
        this.accountkey = accountKey;
        this.bytes = bytes;
        this.objectkey = objectkey;
        this.downloadurl = downloadurl;
    }

    //Always return Long values as String
    public String getAccountkey() {
        return StringUtils.defaultString(String.valueOf(accountkey));
    }

    public String getWebinarkey() {
        return StringUtils.defaultString(String.valueOf(webinarkey));
    }

    public String getOrganizerkey() {
        return StringUtils.defaultString(String.valueOf(organizerkey));
    }

    public String getBytes() {
        return StringUtils.defaultString(String.valueOf(bytes));
    }

}