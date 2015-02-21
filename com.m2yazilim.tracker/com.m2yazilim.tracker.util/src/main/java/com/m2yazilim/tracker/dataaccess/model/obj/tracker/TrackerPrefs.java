package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerPrefs;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: tracker_prefs.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_prefs")
public class TrackerPrefs implements Cloneable, Serializable, IPojoGenEntity, ITrackerPrefs {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981840L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private String prefName;
	/** Field mapping. */
	private String prefValue;
	/** Field mapping. */
	private Integer version;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerPrefs() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerPrefs(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param id Integer object;
	 * @param prefName String object;
	 * @param prefValue String object;
	 * @param version Integer object;
	 */
	public TrackerPrefs(Integer id, String prefName, String prefValue, 					
			Integer version) {

		this.id = id;
		this.prefName = prefName;
		this.prefValue = prefValue;
		this.version = version;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerPrefs.class;
	}
 

    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "pref_id", nullable = false  )
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
     * Return the value associated with the column: prefName.
	 * @return A String object (this.prefName)
	 */
	@Basic( optional = false )
	@Column( name = "pref_name", nullable = false, length = 20  )
	public String getPrefName() {
		return this.prefName;
		
	}
	

  
    /**  
     * Set the value related to the column: prefName.
	 * @param prefName the prefName value you wish to set
	 */
	public void setPrefName(final String prefName) {
		this.prefName = prefName;
	}

    /**
     * Return the value associated with the column: prefValue.
	 * @return A String object (this.prefValue)
	 */
	@Basic( optional = false )
	@Column( name = "pref_value", nullable = false, length = 250  )
	public String getPrefValue() {
		return this.prefValue;
		
	}
	

  
    /**  
     * Set the value related to the column: prefValue.
	 * @param prefValue the prefValue value you wish to set
	 */
	public void setPrefValue(final String prefValue) {
		this.prefValue = prefValue;
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
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public TrackerPrefs clone() throws CloneNotSupportedException {
		
        final TrackerPrefs copy = (TrackerPrefs)super.clone();

		copy.setId(this.getId());
		copy.setPrefName(this.getPrefName());
		copy.setPrefValue(this.getPrefValue());
		copy.setVersion(this.getVersion());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("id: " + this.getId() + ", ");
		sb.append("prefName: " + this.getPrefName() + ", ");
		sb.append("prefValue: " + this.getPrefValue() + ", ");
		sb.append("version: " + this.getVersion());
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
		
		final TrackerPrefs that; 
		try {
			that = (TrackerPrefs) proxyThat;
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
		result = result && (((getPrefName() == null) && (that.getPrefName() == null)) || (getPrefName() != null && getPrefName().equals(that.getPrefName())));
		result = result && (((getPrefValue() == null) && (that.getPrefValue() == null)) || (getPrefValue() != null && getPrefValue().equals(that.getPrefValue())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
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