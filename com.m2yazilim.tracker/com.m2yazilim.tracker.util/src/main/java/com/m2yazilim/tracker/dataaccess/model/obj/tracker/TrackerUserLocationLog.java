package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerUserLocationLog;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;


/** 
 * Object mapping for hibernate-handled table: tracker_user_location_log.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_user_location_log")
public class TrackerUserLocationLog implements Cloneable, Serializable, IPojoGenEntity, ITrackerUserLocationLog {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981830L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Integer createDate;
	/** Field mapping. */
	private String deviationShare;
	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private String latitude;
	/** Field mapping. */
	private TrackerLocationGetType locationGetType;
	/** Field mapping. */
	private String longitude;
	/** Field mapping. */
	private TrackerUsers user;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerUserLocationLog() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerUserLocationLog(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param createDate Integer object;
	 * @param id Integer object;
	 * @param latitude String object;
	 * @param locationGetType TrackerLocationGetType object;
	 * @param longitude String object;
	 * @param user TrackerUsers object;
	 */
	public TrackerUserLocationLog(Integer createDate, Integer id, String latitude, 					
			TrackerLocationGetType locationGetType, String longitude, TrackerUsers user) {

		this.createDate = createDate;
		this.id = id;
		this.latitude = latitude;
		this.locationGetType = locationGetType;
		this.longitude = longitude;
		this.user = user;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerUserLocationLog.class;
	}
 

    /**
     * Return the value associated with the column: createDate.
	 * @return A Integer object (this.createDate)
	 */
	@Basic( optional = false )
	@Column( name = "create_date", nullable = false  )
	public Integer getCreateDate() {
		return this.createDate;
		
	}
	

  
    /**  
     * Set the value related to the column: createDate.
	 * @param createDate the createDate value you wish to set
	 */
	public void setCreateDate(final Integer createDate) {
		this.createDate = createDate;
	}

    /**
     * Return the value associated with the column: deviationShare.
	 * @return A String object (this.deviationShare)
	 */
	@Basic( optional = true )
	@Column( name = "deviation_share", length = 100  )
	public String getDeviationShare() {
		return this.deviationShare;
		
	}
	

  
    /**  
     * Set the value related to the column: deviationShare.
	 * @param deviationShare the deviationShare value you wish to set
	 */
	public void setDeviationShare(final String deviationShare) {
		this.deviationShare = deviationShare;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Integer object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "id", nullable = false  )
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
     * Return the value associated with the column: latitude.
	 * @return A String object (this.latitude)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 200  )
	public String getLatitude() {
		return this.latitude;
		
	}
	

  
    /**  
     * Set the value related to the column: latitude.
	 * @param latitude the latitude value you wish to set
	 */
	public void setLatitude(final String latitude) {
		this.latitude = latitude;
	}

    /**
     * Return the value associated with the column: locationGetType.
	 * @return A TrackerLocationGetType object (this.locationGetType)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "location_get_type_id", nullable = false )
	public TrackerLocationGetType getLocationGetType() {
		return this.locationGetType;
		
	}
	

  
    /**  
     * Set the value related to the column: locationGetType.
	 * @param locationGetType the locationGetType value you wish to set
	 */
	public void setLocationGetType(final TrackerLocationGetType locationGetType) {
		this.locationGetType = locationGetType;
	}

    /**
     * Return the value associated with the column: longitude.
	 * @return A String object (this.longitude)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 200  )
	public String getLongitude() {
		return this.longitude;
		
	}
	

  
    /**  
     * Set the value related to the column: longitude.
	 * @param longitude the longitude value you wish to set
	 */
	public void setLongitude(final String longitude) {
		this.longitude = longitude;
	}

    /**
     * Return the value associated with the column: user.
	 * @return A TrackerUsers object (this.user)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "user_id", nullable = false )
	public TrackerUsers getUser() {
		return this.user;
		
	}
	

  
    /**  
     * Set the value related to the column: user.
	 * @param user the user value you wish to set
	 */
	public void setUser(final TrackerUsers user) {
		this.user = user;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public TrackerUserLocationLog clone() throws CloneNotSupportedException {
		
        final TrackerUserLocationLog copy = (TrackerUserLocationLog)super.clone();

		copy.setCreateDate(this.getCreateDate());
		copy.setDeviationShare(this.getDeviationShare());
		copy.setId(this.getId());
		copy.setLatitude(this.getLatitude());
		copy.setLocationGetType(this.getLocationGetType());
		copy.setLongitude(this.getLongitude());
		copy.setUser(this.getUser());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("createDate: " + this.getCreateDate() + ", ");
		sb.append("deviationShare: " + this.getDeviationShare() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("latitude: " + this.getLatitude() + ", ");
		sb.append("longitude: " + this.getLongitude() + ", ");
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
		
		final TrackerUserLocationLog that; 
		try {
			that = (TrackerUserLocationLog) proxyThat;
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
		result = result && (((getCreateDate() == null) && (that.getCreateDate() == null)) || (getCreateDate() != null && getCreateDate().equals(that.getCreateDate())));
		result = result && (((getDeviationShare() == null) && (that.getDeviationShare() == null)) || (getDeviationShare() != null && getDeviationShare().equals(that.getDeviationShare())));
		result = result && (((getLatitude() == null) && (that.getLatitude() == null)) || (getLatitude() != null && getLatitude().equals(that.getLatitude())));
		result = result && (((getLocationGetType() == null) && (that.getLocationGetType() == null)) || (getLocationGetType() != null && getLocationGetType().getId().equals(that.getLocationGetType().getId())));	
		result = result && (((getLongitude() == null) && (that.getLongitude() == null)) || (getLongitude() != null && getLongitude().equals(that.getLongitude())));
		result = result && (((getUser() == null) && (that.getUser() == null)) || (getUser() != null && getUser().getId().equals(that.getUser().getId())));	
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