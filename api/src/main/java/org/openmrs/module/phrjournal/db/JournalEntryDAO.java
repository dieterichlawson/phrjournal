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
package org.openmrs.module.phrjournal.db;

import java.util.List;

import org.openmrs.Person;
import org.openmrs.module.phrjournal.domain.JournalEntry;

public interface JournalEntryDAO {

    public void deleteJournalEntry(JournalEntry entry);

    public List<JournalEntry> getAllJournalEntries();

    public JournalEntry getJournalEntry(Integer entryId);

    public void saveJournalEntry(JournalEntry entry);

    public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc);

    public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc);

}