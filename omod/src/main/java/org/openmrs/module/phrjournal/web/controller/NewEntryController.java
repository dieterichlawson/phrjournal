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

import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.module.phrjournal.JournalEntryService;
import org.openmrs.module.phrjournal.domain.JournalEntry;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class NewEntryController {

	boolean shouldReset = true;
	@RequestMapping(value = "/module/phrjournal/new_entry")
	public void populateModel(HttpServletRequest request){
		if(shouldReset){
			request.getSession().setAttribute("content", "");
			request.getSession().setAttribute("title", "");
		}else{
			shouldReset=true;
		}
	}
	
	@RequestMapping(value="/module/phrjournal/create_entry", method=RequestMethod.POST)
	public ModelAndView saveEntry(@RequestParam("title") String title,
								   @RequestParam("content") String content,
								   HttpServletRequest request){
		if(title.trim().equals("")|| title ==null){
			request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Please choose a title.");
			request.getSession().setAttribute("content", content);
			shouldReset=false;
			return new ModelAndView(new RedirectView("new_entry.form"));
		}else if(content.trim().equals("")|| content==null){
			request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "There was no text in your journal entry.");
			request.getSession().setAttribute("title", title);
			shouldReset=false;
			return new ModelAndView(new RedirectView("new_entry.form"));
		}
		JournalEntry entry = new JournalEntry(title,content);
		entry.setCreator(Context.getAuthenticatedUser().getPerson());
		Context.getService(JournalEntryService.class).saveJournalEntry(entry);
		return new ModelAndView(new RedirectView("journal.form"));

	}
}
