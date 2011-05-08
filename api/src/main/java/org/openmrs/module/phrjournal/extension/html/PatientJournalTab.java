package org.openmrs.module.phrjournal.extension.html;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.PatientDashboardTabExt;

public class PatientJournalTab extends PatientDashboardTabExt {

	public Extension.MEDIA_TYPE getMediaType(){
		return Extension.MEDIA_TYPE.html;
	}
	
	@Override
	public String getPortletUrl() {
		return "journalTab";
	}

	@Override
	public String getTabId() {
		return "phrjournal";
	}

	@Override
	public String getTabName() {
		return "Journal";
	}

    @Override
    public String getRequiredPrivilege() {
	    return null;
    }

}
