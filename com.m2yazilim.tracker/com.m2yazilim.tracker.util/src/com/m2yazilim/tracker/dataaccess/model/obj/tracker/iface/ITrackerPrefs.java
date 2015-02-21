package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_prefs.
 * @author autogenerated
 */

public interface ITrackerPrefs {



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
     * Return the value associated with the column: prefName.
	 * @return A String object (this.prefName)
	 */
	String getPrefName();
	

  
    /**  
     * Set the value related to the column: prefName.
	 * @param prefName the prefName value you wish to set
	 */
	void setPrefName(final String prefName);

    /**
     * Return the value associated with the column: prefValue.
	 * @return A String object (this.prefValue)
	 */
	String getPrefValue();
	

  
    /**  
     * Set the value related to the column: prefValue.
	 * @param prefValue the prefValue value you wish to set
	 */
	void setPrefValue(final String prefValue);

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