package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import java.util.Set;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_list_version.
 * @author autogenerated
 */

public interface ITrackerListVersion {



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
     * Return the value associated with the column: listPosition.
	 * @return A Integer object (this.listPosition)
	 */
	Integer getListPosition();
	

  
    /**  
     * Set the value related to the column: listPosition.
	 * @param listPosition the listPosition value you wish to set
	 */
	void setListPosition(final Integer listPosition);

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
     * Return the value associated with the column: showInList.
	 * @return A Integer object (this.showInList)
	 */
	Integer getShowInList();
	

  
    /**  
     * Set the value related to the column: showInList.
	 * @param showInList the showInList value you wish to set
	 */
	void setShowInList(final Integer showInList);

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
     * Return the value associated with the column: version.
	 * @return A Integer object (this.version)
	 */
	Integer getVersion();
	

  
    /**  
     * Set the value related to the column: version.
	 * @param version the version value you wish to set
	 */
	void setVersion(final Integer version);

    /**
     * Return the value associated with the column: versionName.
	 * @return A String object (this.versionName)
	 */
	String getVersionName();
	

  
    /**  
     * Set the value related to the column: versionName.
	 * @param versionName the versionName value you wish to set
	 */
	void setVersionName(final String versionName);

    /**
     * Return the value associated with the column: versionTense.
	 * @return A Integer object (this.versionTense)
	 */
	Integer getVersionTense();
	

  
    /**  
     * Set the value related to the column: versionTense.
	 * @param versionTense the versionTense value you wish to set
	 */
	void setVersionTense(final Integer versionTense);

	// end of interface
}