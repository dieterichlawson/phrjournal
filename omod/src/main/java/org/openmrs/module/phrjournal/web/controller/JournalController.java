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
package org.openmrs.module.phrjournal.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.phrjournal.JournalEntryService;
import org.openmrs.module.phrjournal.domain.JournalEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JournalController {

	@RequestMapping(value="/module/phrjournal/journal", method=RequestMethod.GET)
	public void getEntry(@RequestParam(value="id",required=false) Integer id,
	                     @RequestParam(value="search",required=false) String searchText,
	                     HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("hasPermission",true);
		User user = Context.getAuthenticatedUser();
		
		if(id != null){
			List<JournalEntry> entries = new ArrayList<JournalEntry>();
			JournalEntry entry= Context.getService(JournalEntryService.class).getJournalEntry(id);
			entries.add(entry);
			if(hasPermission(user,entry)){
				session.setAttribute("entries", entries);
			}else{
				session.setAttribute("hasPermission",false);
			}
		}else if(searchText != null && !searchText.trim().equals("")){
			List<JournalEntry> entries = Context.getService(JournalEntryService.class).findEntries(searchText, user.getPerson(), true);
			session.setAttribute("entries", entries);
		}else{
			List<JournalEntry> entries = Context.getService(JournalEntryService.class).getJournalEntryForPerson(user.getPerson(),true);
			session.setAttribute("entries", entries);
		}
	}
	
	private boolean hasPermission(User u, JournalEntry entry){
		return true;
	}
}
