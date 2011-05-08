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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.phrjournal.JournalEntryService;
import org.openmrs.module.phrjournal.domain.JournalEntry;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

@Controller
public class JournalTabController extends PortletController{

	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
		User u = Context.getAuthenticatedUser();
		if(hasPermission(u)){
			Patient p = (Patient) model.get("patient");
			List<JournalEntry> entries = Context.getService(JournalEntryService.class).getJournalEntryForPerson(p, true);
			request.setAttribute("entries", entries);
			request.setAttribute("hasPermission", true);
		}else{
			request.setAttribute("hasPermission", false);
		}
	}
	
	private boolean hasPermission(User u){
		return true;
	}
}