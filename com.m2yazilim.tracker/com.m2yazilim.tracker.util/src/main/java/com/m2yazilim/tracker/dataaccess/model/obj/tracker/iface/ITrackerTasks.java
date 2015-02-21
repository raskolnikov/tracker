package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssignedOld;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerCity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerDependencies;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListCategory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListOs;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListResolution;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListTasktype;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListVersion;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotifications;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerRelated;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerReminders;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import java.util.Set;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_tasks.
 * @author autogenerated
 */

public interface ITrackerTasks {



    /**
     * Return the value associated with the column: address.
	 * @return A String object (this.address)
	 */
	String getAddress();
	

  
    /**  
     * Set the value related to the column: address.
	 * @param address the address value you wish to set
	 */
	void setAddress(final String address);

    /**
     * Return the value associated with the column: anonEmail.
	 * @return A String object (this.anonEmail)
	 */
	String getAnonEmail();
	

  
    /**  
     * Set the value related to the column: anonEmail.
	 * @param anonEmail the anonEmail value you wish to set
	 */
	void setAnonEmail(final String anonEmail);

    /**
     * Return the value associated with the column: city.
	 * @return A TrackerCity object (this.city)
	 */
	TrackerCity getCity();
	

  
    /**  
     * Set the value related to the column: city.
	 * @param city the city value you wish to set
	 */
	void setCity(final TrackerCity city);

    /**
     * Return the value associated with the column: closedbyVersion.
	 * @return A Integer object (this.closedbyVersion)
	 */
	Integer getClosedbyVersion();
	

  
    /**  
     * Set the value related to the column: closedbyVersion.
	 * @param closedbyVersion the closedbyVersion value you wish to set
	 */
	void setClosedbyVersion(final Integer closedbyVersion);

    /**
     * Return the value associated with the column: closedBy.
	 * @return A TrackerUsers object (this.closedBy)
	 */
	TrackerUsers getClosedBy();
	

  
    /**  
     * Set the value related to the column: closedBy.
	 * @param closedBy the closedBy value you wish to set
	 */
	void setClosedBy(final TrackerUsers closedBy);

    /**
     * Return the value associated with the column: closureComment.
	 * @return A String object (this.closureComment)
	 */
	String getClosureComment();
	

  
    /**  
     * Set the value related to the column: closureComment.
	 * @param closureComment the closureComment value you wish to set
	 */
	void setClosureComment(final String closureComment);

    /**
     * Return the value associated with the column: dateClosed.
	 * @return A Integer object (this.dateClosed)
	 */
	Integer getDateClosed();
	

  
    /**  
     * Set the value related to the column: dateClosed.
	 * @param dateClosed the dateClosed value you wish to set
	 */
	void setDateClosed(final Integer dateClosed);

    /**
     * Return the value associated with the column: dateOpened.
	 * @return A Integer object (this.dateOpened)
	 */
	Integer getDateOpened();
	

  
    /**  
     * Set the value related to the column: dateOpened.
	 * @param dateOpened the dateOpened value you wish to set
	 */
	void setDateOpened(final Integer dateOpened);

    /**
     * Return the value associated with the column: detailedDesc.
	 * @return A String object (this.detailedDesc)
	 */
	String getDetailedDesc();
	

  
    /**  
     * Set the value related to the column: detailedDesc.
	 * @param detailedDesc the detailedDesc value you wish to set
	 */
	void setDetailedDesc(final String detailedDesc);

    /**
     * Return the value associated with the column: dueDate.
	 * @return A Integer object (this.dueDate)
	 */
	Integer getDueDate();
	

  
    /**  
     * Set the value related to the column: dueDate.
	 * @param dueDate the dueDate value you wish to set
	 */
	void setDueDate(final Integer dueDate);

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
     * Return the value associated with the column: isClosed.
	 * @return A Integer object (this.isClosed)
	 */
	Integer getIsClosed();
	

  
    /**  
     * Set the value related to the column: isClosed.
	 * @param isClosed the isClosed value you wish to set
	 */
	void setIsClosed(final Integer isClosed);

    /**
     * Return the value associated with the column: itemStatus.
	 * @return A TrackerListStatus object (this.itemStatus)
	 */
	TrackerListStatus getItemStatus();
	

  
    /**  
     * Set the value related to the column: itemStatus.
	 * @param itemStatus the itemStatus value you wish to set
	 */
	void setItemStatus(final TrackerListStatus itemStatus);

    /**
     * Return the value associated with the column: itemSummary.
	 * @return A String object (this.itemSummary)
	 */
	String getItemSummary();
	

  
    /**  
     * Set the value related to the column: itemSummary.
	 * @param itemSummary the itemSummary value you wish to set
	 */
	void setItemSummary(final String itemSummary);

    /**
     * Return the value associated with the column: lastEditedBy.
	 * @return A TrackerUsers object (this.lastEditedBy)
	 */
	TrackerUsers getLastEditedBy();
	

  
    /**  
     * Set the value related to the column: lastEditedBy.
	 * @param lastEditedBy the lastEditedBy value you wish to set
	 */
	void setLastEditedBy(final TrackerUsers lastEditedBy);

    /**
     * Return the value associated with the column: lastEditedTime.
	 * @return A Integer object (this.lastEditedTime)
	 */
	Integer getLastEditedTime();
	

  
    /**  
     * Set the value related to the column: lastEditedTime.
	 * @param lastEditedTime the lastEditedTime value you wish to set
	 */
	void setLastEditedTime(final Integer lastEditedTime);

    /**
     * Return the value associated with the column: latitude.
	 * @return A String object (this.latitude)
	 */
	String getLatitude();
	

  
    /**  
     * Set the value related to the column: latitude.
	 * @param latitude the latitude value you wish to set
	 */
	void setLatitude(final String latitude);

    /**
     * Return the value associated with the column: longitude.
	 * @return A String object (this.longitude)
	 */
	String getLongitude();
	

  
    /**  
     * Set the value related to the column: longitude.
	 * @param longitude the longitude value you wish to set
	 */
	void setLongitude(final String longitude);

    /**
     * Return the value associated with the column: markPrivate.
	 * @return A Integer object (this.markPrivate)
	 */
	Integer getMarkPrivate();
	

  
    /**  
     * Set the value related to the column: markPrivate.
	 * @param markPrivate the markPrivate value you wish to set
	 */
	void setMarkPrivate(final Integer markPrivate);

    /**
     * Return the value associated with the column: openedBy.
	 * @return A TrackerUsers object (this.openedBy)
	 */
	TrackerUsers getOpenedBy();
	

  
    /**  
     * Set the value related to the column: openedBy.
	 * @param openedBy the openedBy value you wish to set
	 */
	void setOpenedBy(final TrackerUsers openedBy);

    /**
     * Return the value associated with the column: operatingSystem.
	 * @return A TrackerListOs object (this.operatingSystem)
	 */
	TrackerListOs getOperatingSystem();
	

  
    /**  
     * Set the value related to the column: operatingSystem.
	 * @param operatingSystem the operatingSystem value you wish to set
	 */
	void setOperatingSystem(final TrackerListOs operatingSystem);

    /**
     * Return the value associated with the column: percentComplete.
	 * @return A Integer object (this.percentComplete)
	 */
	Integer getPercentComplete();
	

  
    /**  
     * Set the value related to the column: percentComplete.
	 * @param percentComplete the percentComplete value you wish to set
	 */
	void setPercentComplete(final Integer percentComplete);

    /**
     * Return the value associated with the column: productCategory.
	 * @return A TrackerListCategory object (this.productCategory)
	 */
	TrackerListCategory getProductCategory();
	

  
    /**  
     * Set the value related to the column: productCategory.
	 * @param productCategory the productCategory value you wish to set
	 */
	void setProductCategory(final TrackerListCategory productCategory);

    /**
     * Return the value associated with the column: productVersion.
	 * @return A TrackerListVersion object (this.productVersion)
	 */
	TrackerListVersion getProductVersion();
	

  
    /**  
     * Set the value related to the column: productVersion.
	 * @param productVersion the productVersion value you wish to set
	 */
	void setProductVersion(final TrackerListVersion productVersion);

    /**
     * Return the value associated with the column: project.
	 * @return A TrackerProjects object (this.project)
	 */
	TrackerProjects getProject();
	

  
    /**  
     * Set the value related to the column: project.
	 * @param project the project value you wish to set
	 */
	void setProject(final TrackerProjects project);

    /**
     * Return the value associated with the column: resolutionReason.
	 * @return A TrackerListResolution object (this.resolutionReason)
	 */
	TrackerListResolution getResolutionReason();
	

  
    /**  
     * Set the value related to the column: resolutionReason.
	 * @param resolutionReason the resolutionReason value you wish to set
	 */
	void setResolutionReason(final TrackerListResolution resolutionReason);

    /**
     * Return the value associated with the column: taskPriority.
	 * @return A Integer object (this.taskPriority)
	 */
	Integer getTaskPriority();
	

  
    /**  
     * Set the value related to the column: taskPriority.
	 * @param taskPriority the taskPriority value you wish to set
	 */
	void setTaskPriority(final Integer taskPriority);

    /**
     * Return the value associated with the column: taskSeverity.
	 * @return A Integer object (this.taskSeverity)
	 */
	Integer getTaskSeverity();
	

  
    /**  
     * Set the value related to the column: taskSeverity.
	 * @param taskSeverity the taskSeverity value you wish to set
	 */
	void setTaskSeverity(final Integer taskSeverity);

    /**
     * Return the value associated with the column: taskToken.
	 * @return A String object (this.taskToken)
	 */
	String getTaskToken();
	

  
    /**  
     * Set the value related to the column: taskToken.
	 * @param taskToken the taskToken value you wish to set
	 */
	void setTaskToken(final String taskToken);

    /**
     * Return the value associated with the column: taskType.
	 * @return A TrackerListTasktype object (this.taskType)
	 */
	TrackerListTasktype getTaskType();
	

  
    /**  
     * Set the value related to the column: taskType.
	 * @param taskType the taskType value you wish to set
	 */
	void setTaskType(final TrackerListTasktype taskType);

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
     * Return the value associated with the column: trackerAssignedOld.
	 * @return A Set&lt;TrackerAssignedOld&gt; object (this.trackerAssignedOld)
	 */
	Set<TrackerAssignedOld> getTrackerAssignedOlds();
	
	/**
	 * Adds a bi-directional link of type TrackerAssignedOld to the trackerAssignedOlds set.
	 * @param trackerAssignedOld item to add
	 */
	void addTrackerAssignedOld(TrackerAssignedOld trackerAssignedOld);

  
    /**  
     * Set the value related to the column: trackerAssignedOld.
	 * @param trackerAssignedOld the trackerAssignedOld value you wish to set
	 */
	void setTrackerAssignedOlds(final Set<TrackerAssignedOld> trackerAssignedOld);

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
     * Return the value associated with the column: trackerDependencies.
	 * @return A Set&lt;TrackerDependencies&gt; object (this.trackerDependencies)
	 */
	Set<TrackerDependencies> getTrackerDependencieses();
	
	/**
	 * Adds a bi-directional link of type TrackerDependencies to the trackerDependencieses set.
	 * @param trackerDependencies item to add
	 */
	void addTrackerDependencies(TrackerDependencies trackerDependencies);

  
    /**  
     * Set the value related to the column: trackerDependencies.
	 * @param trackerDependencies the trackerDependencies value you wish to set
	 */
	void setTrackerDependencieses(final Set<TrackerDependencies> trackerDependencies);

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
     * Return the value associated with the column: trackerRelated.
	 * @return A Set&lt;TrackerRelated&gt; object (this.trackerRelated)
	 */
	Set<TrackerRelated> getTrackerRelateds();
	
	/**
	 * Adds a bi-directional link of type TrackerRelated to the trackerRelateds set.
	 * @param trackerRelated item to add
	 */
	void addTrackerRelated(TrackerRelated trackerRelated);

  
    /**  
     * Set the value related to the column: trackerRelated.
	 * @param trackerRelated the trackerRelated value you wish to set
	 */
	void setTrackerRelateds(final Set<TrackerRelated> trackerRelated);

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