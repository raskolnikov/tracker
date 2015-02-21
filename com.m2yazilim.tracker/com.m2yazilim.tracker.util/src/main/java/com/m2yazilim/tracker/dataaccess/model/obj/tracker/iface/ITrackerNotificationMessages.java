package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotificationRecipients;
import java.util.Set;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_notification_messages.
 * @author autogenerated
 */

public interface ITrackerNotificationMessages {



    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
	Integer getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Integer id);

    /**
     * Return the value associated with the column: messageBody.
	 * @return A String object (this.messageBody)
	 */
	String getMessageBody();
	

  
    /**  
     * Set the value related to the column: messageBody.
	 * @param messageBody the messageBody value you wish to set
	 */
	void setMessageBody(final String messageBody);

    /**
     * Return the value associated with the column: messageSubject.
	 * @return A String object (this.messageSubject)
	 */
	String getMessageSubject();
	

  
    /**  
     * Set the value related to the column: messageSubject.
	 * @param messageSubject the messageSubject value you wish to set
	 */
	void setMessageSubject(final String messageSubject);

    /**
     * Return the value associated with the column: timeCreated.
	 * @return A Integer object (this.timeCreated)
	 */
	Integer getTimeCreated();
	

  
    /**  
     * Set the value related to the column: timeCreated.
	 * @param timeCreated the timeCreated value you wish to set
	 */
	void setTimeCreated(final Integer timeCreated);

    /**
     * Return the value associated with the column: trackerNotificationRecipients.
	 * @return A Set&lt;TrackerNotificationRecipients&gt; object (this.trackerNotificationRecipients)
	 */
	Set<TrackerNotificationRecipients> getTrackerNotificationRecipientss();
	
	/**
	 * Adds a bi-directional link of type TrackerNotificationRecipients to the trackerNotificationRecipientss set.
	 * @param trackerNotificationRecipients item to add
	 */
	void addTrackerNotificationRecipients(TrackerNotificationRecipients trackerNotificationRecipients);

  
    /**  
     * Set the value related to the column: trackerNotificationRecipients.
	 * @param trackerNotificationRecipients the trackerNotificationRecipients value you wish to set
	 */
	void setTrackerNotificationRecipientss(final Set<TrackerNotificationRecipients> trackerNotificationRecipients);

    /**
     * Return the value associated with the column: version.
	 * @return A Integer object (this.version)
	 */
	Integer getVersion();
	

  
    /**  
     * Set the value related to the column: version.
	 * @param version the version value you wish to set
	 */
	void setVersion(final Integer version);

	// end of interface
}