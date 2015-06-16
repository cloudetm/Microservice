package com.citrix.g2w.reporting.write.webinars;

import com.citrix.g2w.reporting.events.webinars.WebinarReassignmentEvent;
import com.citrix.queue.support.MessageListener;

/**
 * consume webinarReaasignmentListener
 * @author ankit
 *
 */
public class WebinarReassignmentEventListener implements MessageListener<WebinarReassignmentEvent> {

	/**
	 * process event
	 */
	@Override
    public void onMessage(WebinarReassignmentEvent event) throws Exception {
	    // TODO Auto-generated method stub
    }

}
