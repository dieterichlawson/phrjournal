/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.phrjournal.domain;

import java.util.Date;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Person;

/**
 * 
 */
public class JournalEntry extends BaseOpenmrsObject{

	private JournalEntry(){}
	public JournalEntry(String title, String content) {
	    super();
	    this.title = title;
	    this.content = content;
	    this.dateCreated = new Date();
    }

	private Integer entryId;
	
	private String title;
	private String content;
	private Person creator;
	private Date dateCreated;
	
	/**
     * @see org.openmrs.OpenmrsObject#getId()
     */
    public Integer getId() {
	    return getEntryId();
    }

	/**
     * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
     */
    public void setId(Integer id) {
	    this.setEntryId(id);
    }

	/**
     * @param entryId the entryId to set
     */
    public void setEntryId(Integer entryId) {
	    this.entryId = entryId;
    }

	/**
     * @return the entryId
     */
    public Integer getEntryId() {
	    return entryId;
    }

	/**
     * @param title the title to set
     */
    public void setTitle(String title) {
	    this.title = title;
    }

	/**
     * @return the title
     */
    public String getTitle() {
	    return title;
    }

	/**
     * @param content the content to set
     */
    public void setContent(String content) {
	    this.content = content;
    }

	/**
     * @return the content
     */
    public String getContent() {
	    return content;
    }

	/**
     * @param creator the creator to set
     */
    public void setCreator(Person creator) {
	    this.creator = creator;
    }

	/**
     * @return the creator
     */
    public Person getCreator() {
	    return creator;
    }

	/**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
	    this.dateCreated = dateCreated;
    }

	/**
     * @return the dateCreated
     */
    public Date getDateCreated() {
	    return dateCreated;
    }

}
