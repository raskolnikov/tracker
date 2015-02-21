package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerSearches;
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
 * Object mapping for hibernate-handled table: tracker_searches.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_searches", catalog = "tracker")
public class TrackerSearches implements Cloneable, Serializable, IPojoGenEntity, ITrackerSearches {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981837L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private String name;
	/** Field mapping. */
	private String searchString;
	/** Field mapping. */
	private Integer time;
	/** Field mapping. */
	private TrackerUsers user;
	/** Field mapping. */
	private Integer version;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerSearches() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerSearches(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param id Integer object;
	 * @param name String object;
	 * @param time Integer object;
	 * @param user TrackerUsers object;
	 * @param version Integer object;
	 */
	public TrackerSearches(Integer id, String name, Integer time, 					
			TrackerUsers user, Integer version) {

		this.id = id;
		this.name = name;
		this.time = time;
		this.user = user;
		this.version = version;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerSearches.class;
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
     * Return the value associated with the column: name.
	 * @return A String object (this.name)
	 */
	@Basic( optional = false )
	@Column( nullable = false, length = 50  )
	public String getName() {
		return this.name;
		
	}
	

  
    /**  
     * Set the value related to the column: name.
	 * @param name the name value you wish to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

    /**
     * Return the value associated with the column: searchString.
	 * @return A String object (this.searchString)
	 */
	@Basic( optional = true )
	@Column( name = "search_string", length = 21845  )
	public String getSearchString() {
		return this.searchString;
		
	}
	

  
    /**  
     * Set the value related to the column: searchString.
	 * @param searchString the searchString value you wish to set
	 */
	public void setSearchString(final String searchString) {
		this.searchString = searchString;
	}

    /**
     * Return the value associated with the column: time.
	 * @return A Integer object (this.time)
	 */
	@Basic( optional = false )
	@Column( nullable = false  )
	public Integer getTime() {
		return this.time;
		
	}
	

  
    /**  
     * Set the value related to the column: time.
	 * @param time the time value you wish to set
	 */
	public void setTime(final Integer time) {
		this.time = time;
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
    public TrackerSearches clone() throws CloneNotSupportedException {
		
        final TrackerSearches copy = (TrackerSearches)super.clone();

		copy.setId(this.getId());
		copy.setName(this.getName());
		copy.setSearchString(this.getSearchString());
		copy.setTime(this.getTime());
		copy.setUser(this.getUser());
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
		sb.append("name: " + this.getName() + ", ");
		sb.append("searchString: " + this.getSearchString() + ", ");
		sb.append("time: " + this.getTime() + ", ");
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
		
		final TrackerSearches that; 
		try {
			that = (TrackerSearches) proxyThat;
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
		result = result && (((getName() == null) && (that.getName() == null)) || (getName() != null && getName().equals(that.getName())));
		result = result && (((getSearchString() == null) && (that.getSearchString() == null)) || (getSearchString() != null && getSearchString().equals(that.getSearchString())));
		result = result && (((getTime() == null) && (that.getTime() == null)) || (getTime() != null && getTime().equals(that.getTime())));
		result = result && (((getUser() == null) && (that.getUser() == null)) || (getUser() != null && getUser().getId().equals(that.getUser().getId())));	
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
