package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListCategory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotifications;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerReminders;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerSearches;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsersInGroups;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import java.util.Set;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_users.
 * @author autogenerated
 */

public interface ITrackerUsers {



    /**
     * Return the value associated with the column: accountEnabled.
	 * @return A Integer object (this.accountEnabled)
	 */
	Integer getAccountEnabled();
	

  
    /**  
     * Set the value related to the column: accountEnabled.
	 * @param accountEnabled the accountEnabled value you wish to set
	 */
	void setAccountEnabled(final Integer accountEnabled);

    /**
     * Return the value associated with the column: dateformat.
	 * @return A String object (this.dateformat)
	 */
	String getDateformat();
	

  
    /**  
     * Set the value related to the column: dateformat.
	 * @param dateformat the dateformat value you wish to set
	 */
	void setDateformat(final String dateformat);

    /**
     * Return the value associated with the column: dateformatExtended.
	 * @return A String object (this.dateformatExtended)
	 */
	String getDateformatExtended();
	

  
    /**  
     * Set the value related to the column: dateformatExtended.
	 * @param dateformatExtended the dateformatExtended value you wish to set
	 */
	void setDateformatExtended(final String dateformatExtended);

    /**
     * Return the value associated with the column: emailAddress.
	 * @return A String object (this.emailAddress)
	 */
	String getEmailAddress();
	

  
    /**  
     * Set the value related to the column: emailAddress.
	 * @param emailAddress the emailAddress value you wish to set
	 */
	void setEmailAddress(final String emailAddress);

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
     * Return the value associated with the column: jabberId.
	 * @return A String object (this.jabberId)
	 */
	String getJabberId();
	

  
    /**  
     * Set the value related to the column: jabberId.
	 * @param jabberId the jabberId value you wish to set
	 */
	void setJabberId(final String jabberId);

    /**
     * Return the value associated with the column: lockUntil.
	 * @return A Integer object (this.lockUntil)
	 */
	Integer getLockUntil();
	

  
    /**  
     * Set the value related to the column: lockUntil.
	 * @param lockUntil the lockUntil value you wish to set
	 */
	void setLockUntil(final Integer lockUntil);

    /**
     * Return the value associated with the column: loginAttempts.
	 * @return A Integer object (this.loginAttempts)
	 */
	Integer getLoginAttempts();
	

  
    /**  
     * Set the value related to the column: loginAttempts.
	 * @param loginAttempts the loginAttempts value you wish to set
	 */
	void setLoginAttempts(final Integer loginAttempts);

    /**
     * Return the value associated with the column: magicUrl.
	 * @return A String object (this.magicUrl)
	 */
	String getMagicUrl();
	

  
    /**  
     * Set the value related to the column: magicUrl.
	 * @param magicUrl the magicUrl value you wish to set
	 */
	void setMagicUrl(final String magicUrl);

    /**
     * Return the value associated with the column: notifyOwn.
	 * @return A Integer object (this.notifyOwn)
	 */
	Integer getNotifyOwn();
	

  
    /**  
     * Set the value related to the column: notifyOwn.
	 * @param notifyOwn the notifyOwn value you wish to set
	 */
	void setNotifyOwn(final Integer notifyOwn);

    /**
     * Return the value associated with the column: notifyType.
	 * @return A Integer object (this.notifyType)
	 */
	Integer getNotifyType();
	

  
    /**  
     * Set the value related to the column: notifyType.
	 * @param notifyType the notifyType value you wish to set
	 */
	void setNotifyType(final Integer notifyType);

    /**
     * Return the value associated with the column: realName.
	 * @return A String object (this.realName)
	 */
	String getRealName();
	

  
    /**  
     * Set the value related to the column: realName.
	 * @param realName the realName value you wish to set
	 */
	void setRealName(final String realName);

    /**
     * Return the value associated with the column: registerDate.
	 * @return A Integer object (this.registerDate)
	 */
	Integer getRegisterDate();
	

  
    /**  
     * Set the value related to the column: registerDate.
	 * @param registerDate the registerDate value you wish to set
	 */
	void setRegisterDate(final Integer registerDate);

    /**
     * Return the value associated with the column: tasksPerpage.
	 * @return A Integer object (this.tasksPerpage)
	 */
	Integer getTasksPerpage();
	

  
    /**  
     * Set the value related to the column: tasksPerpage.
	 * @param tasksPerpage the tasksPerpage value you wish to set
	 */
	void setTasksPerpage(final Integer tasksPerpage);

    /**
     * Return the value associated with the column: timeZone.
	 * @return A Integer object (this.timeZone)
	 */
	Integer getTimeZone();
	

  
    /**  
     * Set the value related to the column: timeZone.
	 * @param timeZone the timeZone value you wish to set
	 */
	void setTimeZone(final Integer timeZone);

    /**
     * Return the value associated with the column: trackerAdminRequests.
	 * @return A Set&lt;TrackerAdminRequests&gt; object (this.trackerAdminRequests)
	 */
	Set<TrackerAdminRequests> getTrackerAdminRequestss();
	
	/**
	 * Adds a bi-directional link of type TrackerAdminRequests to the trackerAdminRequestss set.
	 * @param trackerAdminRequests item to add
	 */
	void addTrackerAdminRequests(TrackerAdminRequests trackerAdminRequests);

  
    /**  
     * Set the value related to the column: trackerAdminRequests.
	 * @param trackerAdminRequests the trackerAdminRequests value you wish to set
	 */
	void setTrackerAdminRequestss(final Set<TrackerAdminRequests> trackerAdminRequests);

    /**
     * Return the value associated with the column: trackerAssigned.
	 * @return A Set&lt;TrackerAssigned&gt; object (this.trackerAssigned)
	 */
	Set<TrackerAssigned> getTrackerAssigneds();
	
	/**
	 * Adds a bi-directional link of type TrackerAssigned to the trackerAssigneds set.
	 * @param trackerAssigned item to add
	 */
	void addTrackerAssigned(TrackerAssigned trackerAssigned);

  
    /**  
     * Set the value related to the column: trackerAssigned.
	 * @param trackerAssigned the trackerAssigned value you wish to set
	 */
	void setTrackerAssigneds(final Set<TrackerAssigned> trackerAssigned);

    /**
     * Return the value associated with the column: trackerAttachments.
	 * @return A Set&lt;TrackerAttachments&gt; object (this.trackerAttachments)
	 */
	Set<TrackerAttachments> getTrackerAttachmentss();
	
	/**
	 * Adds a bi-directional link of type TrackerAttachments to the trackerAttachmentss set.
	 * @param trackerAttachments item to add
	 */
	void addTrackerAttachments(TrackerAttachments trackerAttachments);

  
    /**  
     * Set the value related to the column: trackerAttachments.
	 * @param trackerAttachments the trackerAttachments value you wish to set
	 */
	void setTrackerAttachmentss(final Set<TrackerAttachments> trackerAttachments);

    /**
     * Return the value associated with the column: trackerComments.
	 * @return A Set&lt;TrackerComments&gt; object (this.trackerComments)
	 */
	Set<TrackerComments> getTrackerCommentss();
	
	/**
	 * Adds a bi-directional link of type TrackerComments to the trackerCommentss set.
	 * @param trackerComments item to add
	 */
	void addTrackerComments(TrackerComments trackerComments);

  
    /**  
     * Set the value related to the column: trackerComments.
	 * @param trackerComments the trackerComments value you wish to set
	 */
	void setTrackerCommentss(final Set<TrackerComments> trackerComments);

    /**
     * Return the value associated with the column: trackerHistory.
	 * @return A Set&lt;TrackerHistory&gt; object (this.trackerHistory)
	 */
	Set<TrackerHistory> getTrackerHistories();
	
	/**
	 * Adds a bi-directional link of type TrackerHistory to the trackerHistories set.
	 * @param trackerHistory item to add
	 */
	void addTrackerHistory(TrackerHistory trackerHistory);

  
    /**  
     * Set the value related to the column: trackerHistory.
	 * @param trackerHistory the trackerHistory value you wish to set
	 */
	void setTrackerHistories(final Set<TrackerHistory> trackerHistory);

    /**
     * Return the value associated with the column: trackerListCategory.
	 * @return A Set&lt;TrackerListCategory&gt; object (this.trackerListCategory)
	 */
	Set<TrackerListCategory> getTrackerListCategories();
	
	/**
	 * Adds a bi-directional link of type TrackerListCategory to the trackerListCategories set.
	 * @param trackerListCategory item to add
	 */
	void addTrackerListCategory(TrackerListCategory trackerListCategory);

  
    /**  
     * Set the value related to the column: trackerListCategory.
	 * @param trackerListCategory the trackerListCategory value you wish to set
	 */
	void setTrackerListCategories(final Set<TrackerListCategory> trackerListCategory);

    /**
     * Return the value associated with the column: trackerNotifications.
	 * @return A Set&lt;TrackerNotifications&gt; object (this.trackerNotifications)
	 */
	Set<TrackerNotifications> getTrackerNotificationss();
	
	/**
	 * Adds a bi-directional link of type TrackerNotifications to the trackerNotificationss set.
	 * @param trackerNotifications item to add
	 */
	void addTrackerNotifications(TrackerNotifications trackerNotifications);

  
    /**  
     * Set the value related to the column: trackerNotifications.
	 * @param trackerNotifications the trackerNotifications value you wish to set
	 */
	void setTrackerNotificationss(final Set<TrackerNotifications> trackerNotifications);

    /**
     * Return the value associated with the column: trackerProjects.
	 * @return A Set&lt;TrackerProjects&gt; object (this.trackerProjects)
	 */
	Set<TrackerProjects> getTrackerProjectss();
	
	/**
	 * Adds a bi-directional link of type TrackerProjects to the trackerProjectss set.
	 * @param trackerProjects item to add
	 */
	void addTrackerProjects(TrackerProjects trackerProjects);

  
    /**  
     * Set the value related to the column: trackerProjects.
	 * @param trackerProjects the trackerProjects value you wish to set
	 */
	void setTrackerProjectss(final Set<TrackerProjects> trackerProjects);

    /**
     * Return the value associated with the column: trackerReminders.
	 * @return A Set&lt;TrackerReminders&gt; object (this.trackerReminders)
	 */
	Set<TrackerReminders> getTrackerReminderss();
	
	/**
	 * Adds a bi-directional link of type TrackerReminders to the trackerReminderss set.
	 * @param trackerReminders item to add
	 */
	void addTrackerReminders(TrackerReminders trackerReminders);

  
    /**  
     * Set the value related to the column: trackerReminders.
	 * @param trackerReminders the trackerReminders value you wish to set
	 */
	void setTrackerReminderss(final Set<TrackerReminders> trackerReminders);

    /**
     * Return the value associated with the column: trackerSearches.
	 * @return A Set&lt;TrackerSearches&gt; object (this.trackerSearches)
	 */
	Set<TrackerSearches> getTrackerSearcheses();
	
	/**
	 * Adds a bi-directional link of type TrackerSearches to the trackerSearcheses set.
	 * @param trackerSearches item to add
	 */
	void addTrackerSearches(TrackerSearches trackerSearches);

  
    /**  
     * Set the value related to the column: trackerSearches.
	 * @param trackerSearches the trackerSearches value you wish to set
	 */
	void setTrackerSearcheses(final Set<TrackerSearches> trackerSearches);

    /**
     * Return the value associated with the column: trackerTasks.
	 * @return A Set&lt;TrackerTasks&gt; object (this.trackerTasks)
	 */
	Set<TrackerTasks> getTrackerTaskss();
	
	/**
	 * Adds a bi-directional link of type TrackerTasks to the trackerTaskss set.
	 * @param trackerTasks item to add
	 */
	void addTrackerTasks(TrackerTasks trackerTasks);

  
    /**  
     * Set the value related to the column: trackerTasks.
	 * @param trackerTasks the trackerTasks value you wish to set
	 */
	void setTrackerTaskss(final Set<TrackerTasks> trackerTasks);

    /**
     * Return the value associated with the column: trackerUsersInGroups.
	 * @return A Set&lt;TrackerUsersInGroups&gt; object (this.trackerUsersInGroups)
	 */
	Set<TrackerUsersInGroups> getTrackerUsersInGroupss();
	
	/**
	 * Adds a bi-directional link of type TrackerUsersInGroups to the trackerUsersInGroupss set.
	 * @param trackerUsersInGroups item to add
	 */
	void addTrackerUsersInGroups(TrackerUsersInGroups trackerUsersInGroups);

  
    /**  
     * Set the value related to the column: trackerUsersInGroups.
	 * @param trackerUsersInGroups the trackerUsersInGroups value you wish to set
	 */
	void setTrackerUsersInGroupss(final Set<TrackerUsersInGroups> trackerUsersInGroups);

    /**
     * Return the value associated with the column: trackerVotes.
	 * @return A Set&lt;TrackerVotes&gt; object (this.trackerVotes)
	 */
	Set<TrackerVotes> getTrackerVoteses();
	
	/**
	 * Adds a bi-directional link of type TrackerVotes to the trackerVoteses set.
	 * @param trackerVotes item to add
	 */
	void addTrackerVotes(TrackerVotes trackerVotes);

  
    /**  
     * Set the value related to the column: trackerVotes.
	 * @param trackerVotes the trackerVotes value you wish to set
	 */
	void setTrackerVoteses(final Set<TrackerVotes> trackerVotes);

    /**
     * Return the value associated with the column: userName.
	 * @return A String object (this.userName)
	 */
	String getUserName();
	

  
    /**  
     * Set the value related to the column: userName.
	 * @param userName the userName value you wish to set
	 */
	void setUserName(final String userName);

    /**
     * Return the value associated with the column: userPass.
	 * @return A String object (this.userPass)
	 */
	String getUserPass();
	

  
    /**  
     * Set the value related to the column: userPass.
	 * @param userPass the userPass value you wish to set
	 */
	void setUserPass(final String userPass);

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