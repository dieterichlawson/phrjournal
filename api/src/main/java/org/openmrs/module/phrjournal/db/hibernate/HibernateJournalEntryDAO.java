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
package org.openmrs.module.phrjournal.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Person;
import org.openmrs.module.phrjournal.db.JournalEntryDAO;
import org.openmrs.module.phrjournal.domain.JournalEntry;

/**
 *
 */
public class HibernateJournalEntryDAO implements JournalEntryDAO {
	
	protected Log log = LogFactory.getLog(getClass());
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) { 
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.db.JournalEntryDAO#getAllJournalEntries()
	 */
	public List<JournalEntry> getAllJournalEntries() {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(JournalEntry.class);
		return c.list();
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.db.JournalEntryDAO#getJournalEntry(java.lang.Integer)
	 */
	public JournalEntry getJournalEntry(Integer entryId) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(JournalEntry.class);
		c.add(Restrictions.eq("entryId", entryId));
		return (JournalEntry) c.uniqueResult();
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.db.JournalEntryDAO#saveJournalEntry(org.openmrs.module.phrjournal.domain.JournalEntry)
	 */
	public void saveJournalEntry(JournalEntry entry) {
		sessionFactory.getCurrentSession().saveOrUpdate(entry);
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.db.JournalEntryDAO#deleteJournalEntry(org.openmrs.module.phrjournal.domain.JournalEntry)
	 */
	public void deleteJournalEntry(JournalEntry entry) {
		sessionFactory.getCurrentSession().delete(entry);
	}

	/**
     * @see org.openmrs.module.phrjournal.db.JournalEntryDAO#getJournalEntryForPerson(org.openmrs.Person)
     */
    public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc) {
	    Criteria c = sessionFactory.getCurrentSession().createCriteria(JournalEntry.class);
	    if(orderByDateDesc != null){
	    	if(orderByDateDesc){
	    		c.addOrder(Order.desc("dateCreated"));
	    	}else{
	    		c.addOrder(Order.asc("dateCreated"));
	    	}
	    }
	    c.add(Restrictions.eq("creator",p));
	    return c.list();
    }

    public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc) {
    	Criteria c = sessionFactory.getCurrentSession().createCriteria(JournalEntry.class);
    	if(searchText != null && !searchText.trim().equals("")){
    		c.add(Restrictions.or(Restrictions.like("title", searchText,MatchMode.ANYWHERE),
    							  Restrictions.like("content", searchText,MatchMode.ANYWHERE)));
    		
    	}
    	if(p != null){
    		c.add(Restrictions.eq("creator",p));
    	}
    	if(orderByDateDesc != null){
    		if(orderByDateDesc){
	    		c.addOrder(Order.desc("dateCreated"));
	    	}else{
	    		c.addOrder(Order.asc("dateCreated"));
	    	}
    	}
    	return c.list();
    }
}
