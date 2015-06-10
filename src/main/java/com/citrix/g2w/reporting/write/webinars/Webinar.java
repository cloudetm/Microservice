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
package com.citrix.g2w.reporting.write.webinars;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.rest.core.annotation.Description;

import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "webinars")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Webinar implements Serializable {
	
    @Description("Webinar key to which webinar is associated")
    @Indexed
    private Long webinarkey;

    @Description("Organizer key of the organizer who created the webinar")
    @CreatedBy
    @LastModifiedBy
    private Long organizerkey;

    @Description("Account key to which organizer belongs")
    @CreatedBy
    @LastModifiedBy
    private Long accountkey;


    @Description("Primary Key - Cloud storage reference key for a webinar")
    @Id
    @TextIndexed
    @Indexed(unique = true)
    @Field(value = "objectkey")
    private String objectkey;

    public String getAccountkey() {
        return StringUtils.defaultString(String.valueOf(accountkey));
    }

    public String getWebinarkey() {
        return StringUtils.defaultString(String.valueOf(webinarkey));
    }

    public String getOrganizerkey() {
        return StringUtils.defaultString(String.valueOf(organizerkey));
    }

}