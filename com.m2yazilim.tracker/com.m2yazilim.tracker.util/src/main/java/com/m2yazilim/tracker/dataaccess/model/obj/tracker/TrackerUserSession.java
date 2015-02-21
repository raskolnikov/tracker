package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerUserSession;
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
 * Object mapping for hibernate-handled table: tracker_user_session.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_user_session")
public class TrackerUserSession implements Cloneable, Serializable, IPojoGenEntity, ITrackerUserSession {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981828L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Integer createDate;
	/** Field mapping. */
	private String deviceRegisterKey;
	/** Field mapping. */
	private Integer expireDate;
	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private String token;
	/** Field mapping. */
	private Integer updateDate;
	/** Field mapping. */
	private TrackerUsers user;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerUserSession() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerUserSession(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param createDate Integer object;
	 * @param deviceRegisterKey String object;
	 * @param expireDate Integer object;
	 * @param id Integer object;
	 * @param token String object;
	 * @param user TrackerUsers object;
	 */
	public TrackerUserSession(Integer createDate, String deviceRegisterKey, Integer expireDate, 					
			Integer id, String token, TrackerUsers user) {

		this.createDate = createDate;
		this.deviceRegisterKey = deviceRegisterKey;
		this.expireDate = expireDate;
		this.id = id;
		this.token = token;
		this.user = user;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerUserSession.class;
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
     * Return the value associated with the column: deviceRegisterKey.
	 * @return A String object (this.deviceRegisterKey)
	 */
	@Basic( optional = false )
	@Column( name = "device_register_key", nullable = false, length = 200  )
	public String getDeviceRegisterKey() {
		return this.deviceRegisterKey;
		
	}
	

  
    /**  
     * Set the value related to the column: deviceRegisterKey.
	 * @param deviceRegisterKey the deviceRegisterKey value you wish to set
	 */
	public void setDeviceRegisterKey(final String deviceRegisterKey) {
		this.deviceRegisterKey = deviceRegisterKey;
	}

    /**
     * Return the value associated with the column: expireDate.
	 * @return A Integer object (this.expireDate)
	 */
	@Basic( optional = false )
	@Column( name = "expire_date", nullable = false  )
	public Integer getExpireDate() {
		return this.expireDate;
		
	}
	

  
    /**  
     * Set the value related to the column: expireDate.
	 * @param expireDate the expireDate value you wish to set
	 */
	public void setExpireDate(final Integer expireDate) {
		this.expireDate = expireDate;
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
     * Return the value associated with the column: token.
	 * @return A String object (this.token)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 200  )
	public String getToken() {
		return this.token;
		
	}
	

  
    /**  
     * Set the value related to the column: token.
	 * @param token the token value you wish to set
	 */
	public void setToken(final String token) {
		this.token = token;
	}

    /**
     * Return the value associated with the column: updateDate.
	 * @return A Integer object (this.updateDate)
	 */
	@Basic( optional = true )
	@Column( name = "update_date"  )
	public Integer getUpdateDate() {
		return this.updateDate;
		
	}
	

  
    /**  
     * Set the value related to the column: updateDate.
	 * @param updateDate the updateDate value you wish to set
	 */
	public void setUpdateDate(final Integer updateDate) {
		this.updateDate = updateDate;
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
    public TrackerUserSession clone() throws CloneNotSupportedException {
		
        final TrackerUserSession copy = (TrackerUserSession)super.clone();

		copy.setCreateDate(this.getCreateDate());
		copy.setDeviceRegisterKey(this.getDeviceRegisterKey());
		copy.setExpireDate(this.getExpireDate());
		copy.setId(this.getId());
		copy.setToken(this.getToken());
		copy.setUpdateDate(this.getUpdateDate());
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
		sb.append("deviceRegisterKey: " + this.getDeviceRegisterKey() + ", ");
		sb.append("expireDate: " + this.getExpireDate() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("token: " + this.getToken() + ", ");
		sb.append("updateDate: " + this.getUpdateDate() + ", ");
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
		
		final TrackerUserSession that; 
		try {
			that = (TrackerUserSession) proxyThat;
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
		result = result && (((getDeviceRegisterKey() == null) && (that.getDeviceRegisterKey() == null)) || (getDeviceRegisterKey() != null && getDeviceRegisterKey().equals(that.getDeviceRegisterKey())));
		result = result && (((getExpireDate() == null) && (that.getExpireDate() == null)) || (getExpireDate() != null && getExpireDate().equals(that.getExpireDate())));
		result = result && (((getToken() == null) && (that.getToken() == null)) || (getToken() != null && getToken().equals(that.getToken())));
		result = result && (((getUpdateDate() == null) && (that.getUpdateDate() == null)) || (getUpdateDate() != null && getUpdateDate().equals(that.getUpdateDate())));
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
