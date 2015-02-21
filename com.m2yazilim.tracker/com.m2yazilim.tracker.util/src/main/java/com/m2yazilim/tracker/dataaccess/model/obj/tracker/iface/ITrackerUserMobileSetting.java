package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerLocationGetType;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_user_mobile_setting.
 * @author autogenerated
 */

public interface ITrackerUserMobileSetting {



    /**
     * Return the value associated with the column: coordinatPostInterval.
	 * @return A Integer object (this.coordinatPostInterval)
	 */
	Integer getCoordinatPostInterval();
	

  
    /**  
     * Set the value related to the column: coordinatPostInterval.
	 * @param coordinatPostInterval the coordinatPostInterval value you wish to set
	 */
	void setCoordinatPostInterval(final Integer coordinatPostInterval);

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
     * Return the value associated with the column: isSentLocation.
	 * @return A Integer object (this.isSentLocation)
	 */
	Integer getIsSentLocation();
	

  
    /**  
     * Set the value related to the column: isSentLocation.
	 * @param isSentLocation the isSentLocation value you wish to set
	 */
	void setIsSentLocation(final Integer isSentLocation);

    /**
     * Return the value associated with the column: locationGetType.
	 * @return A TrackerLocationGetType object (this.locationGetType)
	 */
	TrackerLocationGetType getLocationGetType();
	

  
    /**  
     * Set the value related to the column: locationGetType.
	 * @param locationGetType the locationGetType value you wish to set
	 */
	void setLocationGetType(final TrackerLocationGetType locationGetType);

    /**
     * Return the value associated with the column: user.
	 * @return A TrackerUsers object (this.user)
	 */
	TrackerUsers getUser();
	

  
    /**  
     * Set the value related to the column: user.
	 * @param user the user value you wish to set
	 */
	void setUser(final TrackerUsers user);

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