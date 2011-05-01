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
package org.openmrs.module.journaling.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openmrs.api.context.Context;
import org.openmrs.module.journaling.JournalEntryService;
import org.openmrs.module.journaling.domain.JournalEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JournalController {
	
	@RequestMapping(value = "/module/journaling/journal")
	public void populateModel(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<JournalEntry> entries = Context.getService(JournalEntryService.class).getJournalEntryForPerson(Context.getAuthenticatedUser().getPerson());
		session.setAttribute("entries", entries);
	}
}
