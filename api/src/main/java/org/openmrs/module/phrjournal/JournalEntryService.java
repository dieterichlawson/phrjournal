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
package org.openmrs.module.phrjournal;

import java.util.List;

import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.phrjournal.domain.JournalEntry;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface JournalEntryService extends OpenmrsService {

	/**
	 * @return all journal entries
	 * @should return all journal entries
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> getAllJournalEntries();

	/**
	 * @param entryId
	 * @return the journal entry with the corresponding entryId
	 * @should return entry with given id
	 */
	@Transactional(readOnly = true)
	public JournalEntry getJournalEntry(Integer entryId);
	
	/**
	 * @param p the person who wrote the journal entries
	 * @return all journal entries written by person p
	 * @should return entries written by supplied person
	 * @should return entries ordered by date
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc);
	
	/**
	 * @param p the person who wrote the journal entries
	 * @param searchText the text to searc hfor
	 * @return all journal entries written by person p
	 * @should return entries written by supplied person
	 * @should return entries ordered by date
	 * @should return entries with searchString in title or body
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc);
	
	/**
	 * Create or update journal entry
	 */
	@Transactional
	public void saveJournalEntry(JournalEntry entry) throws APIException;

	/**
	 * Delete journal entry
	 */
	@Transactional
	public void deleteJournalEntry(JournalEntry entry) throws APIException;
}
