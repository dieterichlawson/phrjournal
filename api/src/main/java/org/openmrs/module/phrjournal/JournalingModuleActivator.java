package org.openmrs.module.phrjournal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class JournalingModuleActivator implements Activator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		log.info("Starting PHR Journal Module");
	}
	
	/**
	 * @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		log.info("Shutting down PHR Journal Module");
	}
	
}
