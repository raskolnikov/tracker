package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsersInGroups;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerGroups;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: tracker_groups.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_groups")
public class TrackerGroups implements Cloneable, Serializable, IPojoGenEntity, ITrackerGroups {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981852L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Integer addComments;
	/** Field mapping. */
	private Integer addToAssignees;
	/** Field mapping. */
	private Integer addVotes;
	/** Field mapping. */
	private Integer assignOthersToSelf;
	/** Field mapping. */
	private Integer assignToSelf;
	/** Field mapping. */
	private Integer closeOtherTasks;
	/** Field mapping. */
	private Integer closeOwnTasks;
	/** Field mapping. */
	private Integer createAttachments;
	/** Field mapping. */
	private Integer deleteAttachments;
	/** Field mapping. */
	private Integer deleteComments;
	/** Field mapping. */
	private Integer editAssignments;
	/** Field mapping. */
	private Integer editComments;
	/** Field mapping. */
	private Integer editOwnComments;
	/** Field mapping. */
	private String groupDesc;
	/** Field mapping. */
	private String groupName;
	/** Field mapping. */
	private Integer groupOpen;
	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private Integer isAdmin;
	/** Field mapping. */
	private Integer manageProject;
	/** Field mapping. */
	private Integer modifyAllTasks;
	/** Field mapping. */
	private Integer modifyOwnTasks;
	/** Field mapping. */
	private Integer openNewTasks;
	/** Field mapping. */
	private TrackerProjects project;
	/** Field mapping. */
	private Integer showAsAssignees;
	/** Field mapping. */
	private Set<TrackerUsersInGroups> trackerUsersInGroupss = new HashSet<TrackerUsersInGroups>();

	/** Field mapping. */
	private Integer version;
	/** Field mapping. */
	private Integer viewComments;
	/** Field mapping. */
	private Integer viewHistory;
	/** Field mapping. */
	private Integer viewReports;
	/** Field mapping. */
	private Integer viewTasks;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerGroups() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerGroups(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param addComments Integer object;
	 * @param addToAssignees Integer object;
	 * @param addVotes Integer object;
	 * @param assignOthersToSelf Integer object;
	 * @param assignToSelf Integer object;
	 * @param closeOtherTasks Integer object;
	 * @param closeOwnTasks Integer object;
	 * @param createAttachments Integer object;
	 * @param deleteAttachments Integer object;
	 * @param deleteComments Integer object;
	 * @param editAssignments Integer object;
	 * @param editComments Integer object;
	 * @param editOwnComments Integer object;
	 * @param groupDesc String object;
	 * @param groupName String object;
	 * @param groupOpen Integer object;
	 * @param id Integer object;
	 * @param isAdmin Integer object;
	 * @param manageProject Integer object;
	 * @param modifyAllTasks Integer object;
	 * @param modifyOwnTasks Integer object;
	 * @param openNewTasks Integer object;
	 * @param project TrackerProjects object;
	 * @param showAsAssignees Integer object;
	 * @param version Integer object;
	 * @param viewComments Integer object;
	 * @param viewHistory Integer object;
	 * @param viewReports Integer object;
	 * @param viewTasks Integer object;
	 */
	public TrackerGroups(Integer addComments, Integer addToAssignees, Integer addVotes, 					
			Integer assignOthersToSelf, Integer assignToSelf, Integer closeOtherTasks, 					
			Integer closeOwnTasks, Integer createAttachments, Integer deleteAttachments, 					
			Integer deleteComments, Integer editAssignments, Integer editComments, 					
			Integer editOwnComments, String groupDesc, String groupName, 					
			Integer groupOpen, Integer id, Integer isAdmin, 					
			Integer manageProject, Integer modifyAllTasks, Integer modifyOwnTasks, 					
			Integer openNewTasks, TrackerProjects project, Integer showAsAssignees, 					
			Integer version, Integer viewComments, Integer viewHistory, 					
			Integer viewReports, Integer viewTasks) {

		this.addComments = addComments;
		this.addToAssignees = addToAssignees;
		this.addVotes = addVotes;
		this.assignOthersToSelf = assignOthersToSelf;
		this.assignToSelf = assignToSelf;
		this.closeOtherTasks = closeOtherTasks;
		this.closeOwnTasks = closeOwnTasks;
		this.createAttachments = createAttachments;
		this.deleteAttachments = deleteAttachments;
		this.deleteComments = deleteComments;
		this.editAssignments = editAssignments;
		this.editComments = editComments;
		this.editOwnComments = editOwnComments;
		this.groupDesc = groupDesc;
		this.groupName = groupName;
		this.groupOpen = groupOpen;
		this.id = id;
		this.isAdmin = isAdmin;
		this.manageProject = manageProject;
		this.modifyAllTasks = modifyAllTasks;
		this.modifyOwnTasks = modifyOwnTasks;
		this.openNewTasks = openNewTasks;
		this.project = project;
		this.showAsAssignees = showAsAssignees;
		this.version = version;
		this.viewComments = viewComments;
		this.viewHistory = viewHistory;
		this.viewReports = viewReports;
		this.viewTasks = viewTasks;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerGroups.class;
	}
 

    /**
     * Return the value associated with the column: addComments.
	 * @return A Integer object (this.addComments)
	 */
	@Basic( optional = false )
	@Column( name = "add_comments", nullable = false  )
	public Integer getAddComments() {
		return this.addComments;
		
	}
	

  
    /**  
     * Set the value related to the column: addComments.
	 * @param addComments the addComments value you wish to set
	 */
	public void setAddComments(final Integer addComments) {
		this.addComments = addComments;
	}

    /**
     * Return the value associated with the column: addToAssignees.
	 * @return A Integer object (this.addToAssignees)
	 */
	@Basic( optional = false )
	@Column( name = "add_to_assignees", nullable = false  )
	public Integer getAddToAssignees() {
		return this.addToAssignees;
		
	}
	

  
    /**  
     * Set the value related to the column: addToAssignees.
	 * @param addToAssignees the addToAssignees value you wish to set
	 */
	public void setAddToAssignees(final Integer addToAssignees) {
		this.addToAssignees = addToAssignees;
	}

    /**
     * Return the value associated with the column: addVotes.
	 * @return A Integer object (this.addVotes)
	 */
	@Basic( optional = false )
	@Column( name = "add_votes", nullable = false  )
	public Integer getAddVotes() {
		return this.addVotes;
		
	}
	

  
    /**  
     * Set the value related to the column: addVotes.
	 * @param addVotes the addVotes value you wish to set
	 */
	public void setAddVotes(final Integer addVotes) {
		this.addVotes = addVotes;
	}

    /**
     * Return the value associated with the column: assignOthersToSelf.
	 * @return A Integer object (this.assignOthersToSelf)
	 */
	@Basic( optional = false )
	@Column( name = "assign_others_to_self", nullable = false  )
	public Integer getAssignOthersToSelf() {
		return this.assignOthersToSelf;
		
	}
	

  
    /**  
     * Set the value related to the column: assignOthersToSelf.
	 * @param assignOthersToSelf the assignOthersToSelf value you wish to set
	 */
	public void setAssignOthersToSelf(final Integer assignOthersToSelf) {
		this.assignOthersToSelf = assignOthersToSelf;
	}

    /**
     * Return the value associated with the column: assignToSelf.
	 * @return A Integer object (this.assignToSelf)
	 */
	@Basic( optional = false )
	@Column( name = "assign_to_self", nullable = false  )
	public Integer getAssignToSelf() {
		return this.assignToSelf;
		
	}
	

  
    /**  
     * Set the value related to the column: assignToSelf.
	 * @param assignToSelf the assignToSelf value you wish to set
	 */
	public void setAssignToSelf(final Integer assignToSelf) {
		this.assignToSelf = assignToSelf;
	}

    /**
     * Return the value associated with the column: closeOtherTasks.
	 * @return A Integer object (this.closeOtherTasks)
	 */
	@Basic( optional = false )
	@Column( name = "close_other_tasks", nullable = false  )
	public Integer getCloseOtherTasks() {
		return this.closeOtherTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: closeOtherTasks.
	 * @param closeOtherTasks the closeOtherTasks value you wish to set
	 */
	public void setCloseOtherTasks(final Integer closeOtherTasks) {
		this.closeOtherTasks = closeOtherTasks;
	}

    /**
     * Return the value associated with the column: closeOwnTasks.
	 * @return A Integer object (this.closeOwnTasks)
	 */
	@Basic( optional = false )
	@Column( name = "close_own_tasks", nullable = false  )
	public Integer getCloseOwnTasks() {
		return this.closeOwnTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: closeOwnTasks.
	 * @param closeOwnTasks the closeOwnTasks value you wish to set
	 */
	public void setCloseOwnTasks(final Integer closeOwnTasks) {
		this.closeOwnTasks = closeOwnTasks;
	}

    /**
     * Return the value associated with the column: createAttachments.
	 * @return A Integer object (this.createAttachments)
	 */
	@Basic( optional = false )
	@Column( name = "create_attachments", nullable = false  )
	public Integer getCreateAttachments() {
		return this.createAttachments;
		
	}
	

  
    /**  
     * Set the value related to the column: createAttachments.
	 * @param createAttachments the createAttachments value you wish to set
	 */
	public void setCreateAttachments(final Integer createAttachments) {
		this.createAttachments = createAttachments;
	}

    /**
     * Return the value associated with the column: deleteAttachments.
	 * @return A Integer object (this.deleteAttachments)
	 */
	@Basic( optional = false )
	@Column( name = "delete_attachments", nullable = false  )
	public Integer getDeleteAttachments() {
		return this.deleteAttachments;
		
	}
	

  
    /**  
     * Set the value related to the column: deleteAttachments.
	 * @param deleteAttachments the deleteAttachments value you wish to set
	 */
	public void setDeleteAttachments(final Integer deleteAttachments) {
		this.deleteAttachments = deleteAttachments;
	}

    /**
     * Return the value associated with the column: deleteComments.
	 * @return A Integer object (this.deleteComments)
	 */
	@Basic( optional = false )
	@Column( name = "delete_comments", nullable = false  )
	public Integer getDeleteComments() {
		return this.deleteComments;
		
	}
	

  
    /**  
     * Set the value related to the column: deleteComments.
	 * @param deleteComments the deleteComments value you wish to set
	 */
	public void setDeleteComments(final Integer deleteComments) {
		this.deleteComments = deleteComments;
	}

    /**
     * Return the value associated with the column: editAssignments.
	 * @return A Integer object (this.editAssignments)
	 */
	@Basic( optional = false )
	@Column( name = "edit_assignments", nullable = false  )
	public Integer getEditAssignments() {
		return this.editAssignments;
		
	}
	

  
    /**  
     * Set the value related to the column: editAssignments.
	 * @param editAssignments the editAssignments value you wish to set
	 */
	public void setEditAssignments(final Integer editAssignments) {
		this.editAssignments = editAssignments;
	}

    /**
     * Return the value associated with the column: editComments.
	 * @return A Integer object (this.editComments)
	 */
	@Basic( optional = false )
	@Column( name = "edit_comments", nullable = false  )
	public Integer getEditComments() {
		return this.editComments;
		
	}
	

  
    /**  
     * Set the value related to the column: editComments.
	 * @param editComments the editComments value you wish to set
	 */
	public void setEditComments(final Integer editComments) {
		this.editComments = editComments;
	}

    /**
     * Return the value associated with the column: editOwnComments.
	 * @return A Integer object (this.editOwnComments)
	 */
	@Basic( optional = false )
	@Column( name = "edit_own_comments", nullable = false  )
	public Integer getEditOwnComments() {
		return this.editOwnComments;
		
	}
	

  
    /**  
     * Set the value related to the column: editOwnComments.
	 * @param editOwnComments the editOwnComments value you wish to set
	 */
	public void setEditOwnComments(final Integer editOwnComments) {
		this.editOwnComments = editOwnComments;
	}

    /**
     * Return the value associated with the column: groupDesc.
	 * @return A String object (this.groupDesc)
	 */
	@Basic( optional = false )
	@Column( name = "group_desc", nullable = false, length = 150  )
	public String getGroupDesc() {
		return this.groupDesc;
		
	}
	

  
    /**  
     * Set the value related to the column: groupDesc.
	 * @param groupDesc the groupDesc value you wish to set
	 */
	public void setGroupDesc(final String groupDesc) {
		this.groupDesc = groupDesc;
	}

    /**
     * Return the value associated with the column: groupName.
	 * @return A String object (this.groupName)
	 */
	@Basic( optional = false )
	@Column( name = "group_name", nullable = false, length = 20  )
	public String getGroupName() {
		return this.groupName;
		
	}
	

  
    /**  
     * Set the value related to the column: groupName.
	 * @param groupName the groupName value you wish to set
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

    /**
     * Return the value associated with the column: groupOpen.
	 * @return A Integer object (this.groupOpen)
	 */
	@Basic( optional = false )
	@Column( name = "group_open", nullable = false  )
	public Integer getGroupOpen() {
		return this.groupOpen;
		
	}
	

  
    /**  
     * Set the value related to the column: groupOpen.
	 * @param groupOpen the groupOpen value you wish to set
	 */
	public void setGroupOpen(final Integer groupOpen) {
		this.groupOpen = groupOpen;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "group_id", nullable = false  )
	public Integer getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Integer id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null || this.id == 0) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

    /**
     * Return the value associated with the column: isAdmin.
	 * @return A Integer object (this.isAdmin)
	 */
	@Basic( optional = false )
	@Column( name = "is_admin", nullable = false  )
	public Integer getIsAdmin() {
		return this.isAdmin;
		
	}
	

  
    /**  
     * Set the value related to the column: isAdmin.
	 * @param isAdmin the isAdmin value you wish to set
	 */
	public void setIsAdmin(final Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

    /**
     * Return the value associated with the column: manageProject.
	 * @return A Integer object (this.manageProject)
	 */
	@Basic( optional = false )
	@Column( name = "manage_project", nullable = false  )
	public Integer getManageProject() {
		return this.manageProject;
		
	}
	

  
    /**  
     * Set the value related to the column: manageProject.
	 * @param manageProject the manageProject value you wish to set
	 */
	public void setManageProject(final Integer manageProject) {
		this.manageProject = manageProject;
	}

    /**
     * Return the value associated with the column: modifyAllTasks.
	 * @return A Integer object (this.modifyAllTasks)
	 */
	@Basic( optional = false )
	@Column( name = "modify_all_tasks", nullable = false  )
	public Integer getModifyAllTasks() {
		return this.modifyAllTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: modifyAllTasks.
	 * @param modifyAllTasks the modifyAllTasks value you wish to set
	 */
	public void setModifyAllTasks(final Integer modifyAllTasks) {
		this.modifyAllTasks = modifyAllTasks;
	}

    /**
     * Return the value associated with the column: modifyOwnTasks.
	 * @return A Integer object (this.modifyOwnTasks)
	 */
	@Basic( optional = false )
	@Column( name = "modify_own_tasks", nullable = false  )
	public Integer getModifyOwnTasks() {
		return this.modifyOwnTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: modifyOwnTasks.
	 * @param modifyOwnTasks the modifyOwnTasks value you wish to set
	 */
	public void setModifyOwnTasks(final Integer modifyOwnTasks) {
		this.modifyOwnTasks = modifyOwnTasks;
	}

    /**
     * Return the value associated with the column: openNewTasks.
	 * @return A Integer object (this.openNewTasks)
	 */
	@Basic( optional = false )
	@Column( name = "open_new_tasks", nullable = false  )
	public Integer getOpenNewTasks() {
		return this.openNewTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: openNewTasks.
	 * @param openNewTasks the openNewTasks value you wish to set
	 */
	public void setOpenNewTasks(final Integer openNewTasks) {
		this.openNewTasks = openNewTasks;
	}

    /**
     * Return the value associated with the column: project.
	 * @return A TrackerProjects object (this.project)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "project_id", nullable = false )
	public TrackerProjects getProject() {
		return this.project;
		
	}
	

  
    /**  
     * Set the value related to the column: project.
	 * @param project the project value you wish to set
	 */
	public void setProject(final TrackerProjects project) {
		this.project = project;
	}

    /**
     * Return the value associated with the column: showAsAssignees.
	 * @return A Integer object (this.showAsAssignees)
	 */
	@Basic( optional = false )
	@Column( name = "show_as_assignees", nullable = false  )
	public Integer getShowAsAssignees() {
		return this.showAsAssignees;
		
	}
	

  
    /**  
     * Set the value related to the column: showAsAssignees.
	 * @param showAsAssignees the showAsAssignees value you wish to set
	 */
	public void setShowAsAssignees(final Integer showAsAssignees) {
		this.showAsAssignees = showAsAssignees;
	}

    /**
     * Return the value associated with the column: trackerUsersInGroups.
	 * @return A Set&lt;TrackerUsersInGroups&gt; object (this.trackerUsersInGroups)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "group"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( name = "group_id", nullable = false  )
	public Set<TrackerUsersInGroups> getTrackerUsersInGroupss() {
		return this.trackerUsersInGroupss;
		
	}
	
	/**
	 * Adds a bi-directional link of type TrackerUsersInGroups to the trackerUsersInGroupss set.
	 * @param trackerUsersInGroups item to add
	 */
	public void addTrackerUsersInGroups(TrackerUsersInGroups trackerUsersInGroups) {
		trackerUsersInGroups.setGroup(this);
		this.trackerUsersInGroupss.add(trackerUsersInGroups);
	}

  
    /**  
     * Set the value related to the column: trackerUsersInGroups.
	 * @param trackerUsersInGroups the trackerUsersInGroups value you wish to set
	 */
	public void setTrackerUsersInGroupss(final Set<TrackerUsersInGroups> trackerUsersInGroups) {
		this.trackerUsersInGroupss = trackerUsersInGroups;
	}

    /**
     * Return the value associated with the column: version.
	 * @return A Integer object (this.version)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Integer getVersion() {
		return this.version;
		
	}
	

  
    /**  
     * Set the value related to the column: version.
	 * @param version the version value you wish to set
	 */
	public void setVersion(final Integer version) {
		this.version = version;
	}

    /**
     * Return the value associated with the column: viewComments.
	 * @return A Integer object (this.viewComments)
	 */
	@Basic( optional = false )
	@Column( name = "view_comments", nullable = false  )
	public Integer getViewComments() {
		return this.viewComments;
		
	}
	

  
    /**  
     * Set the value related to the column: viewComments.
	 * @param viewComments the viewComments value you wish to set
	 */
	public void setViewComments(final Integer viewComments) {
		this.viewComments = viewComments;
	}

    /**
     * Return the value associated with the column: viewHistory.
	 * @return A Integer object (this.viewHistory)
	 */
	@Basic( optional = false )
	@Column( name = "view_history", nullable = false  )
	public Integer getViewHistory() {
		return this.viewHistory;
		
	}
	

  
    /**  
     * Set the value related to the column: viewHistory.
	 * @param viewHistory the viewHistory value you wish to set
	 */
	public void setViewHistory(final Integer viewHistory) {
		this.viewHistory = viewHistory;
	}

    /**
     * Return the value associated with the column: viewReports.
	 * @return A Integer object (this.viewReports)
	 */
	@Basic( optional = false )
	@Column( name = "view_reports", nullable = false  )
	public Integer getViewReports() {
		return this.viewReports;
		
	}
	

  
    /**  
     * Set the value related to the column: viewReports.
	 * @param viewReports the viewReports value you wish to set
	 */
	public void setViewReports(final Integer viewReports) {
		this.viewReports = viewReports;
	}

    /**
     * Return the value associated with the column: viewTasks.
	 * @return A Integer object (this.viewTasks)
	 */
	@Basic( optional = false )
	@Column( name = "view_tasks", nullable = false  )
	public Integer getViewTasks() {
		return this.viewTasks;
		
	}
	

  
    /**  
     * Set the value related to the column: viewTasks.
	 * @param viewTasks the viewTasks value you wish to set
	 */
	public void setViewTasks(final Integer viewTasks) {
		this.viewTasks = viewTasks;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public TrackerGroups clone() throws CloneNotSupportedException {
		
        final TrackerGroups copy = (TrackerGroups)super.clone();

		copy.setAddComments(this.getAddComments());
		copy.setAddToAssignees(this.getAddToAssignees());
		copy.setAddVotes(this.getAddVotes());
		copy.setAssignOthersToSelf(this.getAssignOthersToSelf());
		copy.setAssignToSelf(this.getAssignToSelf());
		copy.setCloseOtherTasks(this.getCloseOtherTasks());
		copy.setCloseOwnTasks(this.getCloseOwnTasks());
		copy.setCreateAttachments(this.getCreateAttachments());
		copy.setDeleteAttachments(this.getDeleteAttachments());
		copy.setDeleteComments(this.getDeleteComments());
		copy.setEditAssignments(this.getEditAssignments());
		copy.setEditComments(this.getEditComments());
		copy.setEditOwnComments(this.getEditOwnComments());
		copy.setGroupDesc(this.getGroupDesc());
		copy.setGroupName(this.getGroupName());
		copy.setGroupOpen(this.getGroupOpen());
		copy.setId(this.getId());
		copy.setIsAdmin(this.getIsAdmin());
		copy.setManageProject(this.getManageProject());
		copy.setModifyAllTasks(this.getModifyAllTasks());
		copy.setModifyOwnTasks(this.getModifyOwnTasks());
		copy.setOpenNewTasks(this.getOpenNewTasks());
		copy.setProject(this.getProject());
		copy.setShowAsAssignees(this.getShowAsAssignees());
		if (this.getTrackerUsersInGroupss() != null) {
			copy.getTrackerUsersInGroupss().addAll(this.getTrackerUsersInGroupss());
		}
		copy.setVersion(this.getVersion());
		copy.setViewComments(this.getViewComments());
		copy.setViewHistory(this.getViewHistory());
		copy.setViewReports(this.getViewReports());
		copy.setViewTasks(this.getViewTasks());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("addComments: " + this.getAddComments() + ", ");
		sb.append("addToAssignees: " + this.getAddToAssignees() + ", ");
		sb.append("addVotes: " + this.getAddVotes() + ", ");
		sb.append("assignOthersToSelf: " + this.getAssignOthersToSelf() + ", ");
		sb.append("assignToSelf: " + this.getAssignToSelf() + ", ");
		sb.append("closeOtherTasks: " + this.getCloseOtherTasks() + ", ");
		sb.append("closeOwnTasks: " + this.getCloseOwnTasks() + ", ");
		sb.append("createAttachments: " + this.getCreateAttachments() + ", ");
		sb.append("deleteAttachments: " + this.getDeleteAttachments() + ", ");
		sb.append("deleteComments: " + this.getDeleteComments() + ", ");
		sb.append("editAssignments: " + this.getEditAssignments() + ", ");
		sb.append("editComments: " + this.getEditComments() + ", ");
		sb.append("editOwnComments: " + this.getEditOwnComments() + ", ");
		sb.append("groupDesc: " + this.getGroupDesc() + ", ");
		sb.append("groupName: " + this.getGroupName() + ", ");
		sb.append("groupOpen: " + this.getGroupOpen() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("isAdmin: " + this.getIsAdmin() + ", ");
		sb.append("manageProject: " + this.getManageProject() + ", ");
		sb.append("modifyAllTasks: " + this.getModifyAllTasks() + ", ");
		sb.append("modifyOwnTasks: " + this.getModifyOwnTasks() + ", ");
		sb.append("openNewTasks: " + this.getOpenNewTasks() + ", ");
		sb.append("showAsAssignees: " + this.getShowAsAssignees() + ", ");
		sb.append("version: " + this.getVersion() + ", ");
		sb.append("viewComments: " + this.getViewComments() + ", ");
		sb.append("viewHistory: " + this.getViewHistory() + ", ");
		sb.append("viewReports: " + this.getViewReports() + ", ");
		sb.append("viewTasks: " + this.getViewTasks());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final TrackerGroups that; 
		try {
			that = (TrackerGroups) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getAddComments() == null) && (that.getAddComments() == null)) || (getAddComments() != null && getAddComments().equals(that.getAddComments())));
		result = result && (((getAddToAssignees() == null) && (that.getAddToAssignees() == null)) || (getAddToAssignees() != null && getAddToAssignees().equals(that.getAddToAssignees())));
		result = result && (((getAddVotes() == null) && (that.getAddVotes() == null)) || (getAddVotes() != null && getAddVotes().equals(that.getAddVotes())));
		result = result && (((getAssignOthersToSelf() == null) && (that.getAssignOthersToSelf() == null)) || (getAssignOthersToSelf() != null && getAssignOthersToSelf().equals(that.getAssignOthersToSelf())));
		result = result && (((getAssignToSelf() == null) && (that.getAssignToSelf() == null)) || (getAssignToSelf() != null && getAssignToSelf().equals(that.getAssignToSelf())));
		result = result && (((getCloseOtherTasks() == null) && (that.getCloseOtherTasks() == null)) || (getCloseOtherTasks() != null && getCloseOtherTasks().equals(that.getCloseOtherTasks())));
		result = result && (((getCloseOwnTasks() == null) && (that.getCloseOwnTasks() == null)) || (getCloseOwnTasks() != null && getCloseOwnTasks().equals(that.getCloseOwnTasks())));
		result = result && (((getCreateAttachments() == null) && (that.getCreateAttachments() == null)) || (getCreateAttachments() != null && getCreateAttachments().equals(that.getCreateAttachments())));
		result = result && (((getDeleteAttachments() == null) && (that.getDeleteAttachments() == null)) || (getDeleteAttachments() != null && getDeleteAttachments().equals(that.getDeleteAttachments())));
		result = result && (((getDeleteComments() == null) && (that.getDeleteComments() == null)) || (getDeleteComments() != null && getDeleteComments().equals(that.getDeleteComments())));
		result = result && (((getEditAssignments() == null) && (that.getEditAssignments() == null)) || (getEditAssignments() != null && getEditAssignments().equals(that.getEditAssignments())));
		result = result && (((getEditComments() == null) && (that.getEditComments() == null)) || (getEditComments() != null && getEditComments().equals(that.getEditComments())));
		result = result && (((getEditOwnComments() == null) && (that.getEditOwnComments() == null)) || (getEditOwnComments() != null && getEditOwnComments().equals(that.getEditOwnComments())));
		result = result && (((getGroupDesc() == null) && (that.getGroupDesc() == null)) || (getGroupDesc() != null && getGroupDesc().equals(that.getGroupDesc())));
		result = result && (((getGroupName() == null) && (that.getGroupName() == null)) || (getGroupName() != null && getGroupName().equals(that.getGroupName())));
		result = result && (((getGroupOpen() == null) && (that.getGroupOpen() == null)) || (getGroupOpen() != null && getGroupOpen().equals(that.getGroupOpen())));
		result = result && (((getIsAdmin() == null) && (that.getIsAdmin() == null)) || (getIsAdmin() != null && getIsAdmin().equals(that.getIsAdmin())));
		result = result && (((getManageProject() == null) && (that.getManageProject() == null)) || (getManageProject() != null && getManageProject().equals(that.getManageProject())));
		result = result && (((getModifyAllTasks() == null) && (that.getModifyAllTasks() == null)) || (getModifyAllTasks() != null && getModifyAllTasks().equals(that.getModifyAllTasks())));
		result = result && (((getModifyOwnTasks() == null) && (that.getModifyOwnTasks() == null)) || (getModifyOwnTasks() != null && getModifyOwnTasks().equals(that.getModifyOwnTasks())));
		result = result && (((getOpenNewTasks() == null) && (that.getOpenNewTasks() == null)) || (getOpenNewTasks() != null && getOpenNewTasks().equals(that.getOpenNewTasks())));
		result = result && (((getProject() == null) && (that.getProject() == null)) || (getProject() != null && getProject().getId().equals(that.getProject().getId())));	
		result = result && (((getShowAsAssignees() == null) && (that.getShowAsAssignees() == null)) || (getShowAsAssignees() != null && getShowAsAssignees().equals(that.getShowAsAssignees())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
		result = result && (((getViewComments() == null) && (that.getViewComments() == null)) || (getViewComments() != null && getViewComments().equals(that.getViewComments())));
		result = result && (((getViewHistory() == null) && (that.getViewHistory() == null)) || (getViewHistory() != null && getViewHistory().equals(that.getViewHistory())));
		result = result && (((getViewReports() == null) && (that.getViewReports() == null)) || (getViewReports() != null && getViewReports().equals(that.getViewReports())));
		result = result && (((getViewTasks() == null) && (that.getViewTasks() == null)) || (getViewTasks() != null && getViewTasks().equals(that.getViewTasks())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					Integer newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null && getId() != 0) {
							newHashCode = getId();
						} else {

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
	return this.hashCode;
	}
	

	
}
