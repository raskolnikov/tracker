package com.m2yazilim.tracker.dataaccess.model.obj.tracker;

import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface.ITrackerAssignedOld;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
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
 * Object mapping for hibernate-handled table: tracker_assigned_old.
 * @author autogenerated
 */

@Entity
@Table(name = "tracker_assigned_old")
public class TrackerAssignedOld implements Cloneable, Serializable, IPojoGenEntity, ITrackerAssignedOld {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558981858L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, Integer> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, Integer>());
	
	/** hashCode temporary storage. */
	private volatile Integer hashCode;
	

	/** Field mapping. */
	private TrackerAssigned assigned;
	/** Field mapping. */
	private Date createDate;
	/** Field mapping. */
	private Integer id = 0; // init for hibernate bug workaround
	/** Field mapping. */
	private Integer isSent;
	/** Field mapping. */
	private TrackerUsers oldUser;
	/** Field mapping. */
	private Date sentDate;
	/** Field mapping. */
	private TrackerTasks task;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public TrackerAssignedOld() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public TrackerAssignedOld(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param assigned TrackerAssigned object;
	 * @param id Integer object;
	 * @param oldUser TrackerUsers object;
	 * @param task TrackerTasks object;
	 */
	public TrackerAssignedOld(TrackerAssigned assigned, Integer id, TrackerUsers oldUser, 					
			TrackerTasks task) {

		this.assigned = assigned;
		this.id = id;
		this.oldUser = oldUser;
		this.task = task;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return TrackerAssignedOld.class;
	}
 

    /**
     * Return the value associated with the column: assigned.
	 * @return A TrackerAssigned object (this.assigned)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "assigned_id", nullable = false )
	public TrackerAssigned getAssigned() {
		return this.assigned;
		
	}
	

  
    /**  
     * Set the value related to the column: assigned.
	 * @param assigned the assigned value you wish to set
	 */
	public void setAssigned(final TrackerAssigned assigned) {
		this.assigned = assigned;
	}

    /**
     * Return the value associated with the column: createDate.
	 * @return A Date object (this.createDate)
	 */
	@Basic( optional = true )
	@Column( name = "create_date"  )
	public Date getCreateDate() {
		return this.createDate;
		
	}
	

  
    /**  
     * Set the value related to the column: createDate.
	 * @param createDate the createDate value you wish to set
	 */
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
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
     * Return the value associated with the column: isSent.
	 * @return A Integer object (this.isSent)
	 */
	@Basic( optional = true )
	@Column( name = "is_sent"  )
	public Integer getIsSent() {
		return this.isSent;
		
	}
	

  
    /**  
     * Set the value related to the column: isSent.
	 * @param isSent the isSent value you wish to set
	 */
	public void setIsSent(final Integer isSent) {
		this.isSent = isSent;
	}

    /**
     * Return the value associated with the column: oldUser.
	 * @return A TrackerUsers object (this.oldUser)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "old_user_id", nullable = false )
	public TrackerUsers getOldUser() {
		return this.oldUser;
		
	}
	

  
    /**  
     * Set the value related to the column: oldUser.
	 * @param oldUser the oldUser value you wish to set
	 */
	public void setOldUser(final TrackerUsers oldUser) {
		this.oldUser = oldUser;
	}

    /**
     * Return the value associated with the column: sentDate.
	 * @return A Date object (this.sentDate)
	 */
	@Basic( optional = true )
	@Column( name = "sent_date"  )
	public Date getSentDate() {
		return this.sentDate;
		
	}
	

  
    /**  
     * Set the value related to the column: sentDate.
	 * @param sentDate the sentDate value you wish to set
	 */
	public void setSentDate(final Date sentDate) {
		this.sentDate = sentDate;
	}

    /**
     * Return the value associated with the column: task.
	 * @return A TrackerTasks object (this.task)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "task_id", nullable = false )
	public TrackerTasks getTask() {
		return this.task;
		
	}
	

  
    /**  
     * Set the value related to the column: task.
	 * @param task the task value you wish to set
	 */
	public void setTask(final TrackerTasks task) {
		this.task = task;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public TrackerAssignedOld clone() throws CloneNotSupportedException {
		
        final TrackerAssignedOld copy = (TrackerAssignedOld)super.clone();

		copy.setAssigned(this.getAssigned());
		copy.setCreateDate(this.getCreateDate());
		copy.setId(this.getId());
		copy.setIsSent(this.getIsSent());
		copy.setOldUser(this.getOldUser());
		copy.setSentDate(this.getSentDate());
		copy.setTask(this.getTask());
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
		sb.append("id: " + this.getId() + ", ");
		sb.append("isSent: " + this.getIsSent() + ", ");
		sb.append("sentDate: " + this.getSentDate() + ", ");
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
		
		final TrackerAssignedOld that; 
		try {
			that = (TrackerAssignedOld) proxyThat;
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
		result = result && (((getAssigned() == null) && (that.getAssigned() == null)) || (getAssigned() != null && getAssigned().getId().equals(that.getAssigned().getId())));	
		result = result && (((getCreateDate() == null) && (that.getCreateDate() == null)) || (getCreateDate() != null && getCreateDate().equals(that.getCreateDate())));
		result = result && (((getIsSent() == null) && (that.getIsSent() == null)) || (getIsSent() != null && getIsSent().equals(that.getIsSent())));
		result = result && (((getOldUser() == null) && (that.getOldUser() == null)) || (getOldUser() != null && getOldUser().getId().equals(that.getOldUser().getId())));	
		result = result && (((getSentDate() == null) && (that.getSentDate() == null)) || (getSentDate() != null && getSentDate().equals(that.getSentDate())));
		result = result && (((getTask() == null) && (that.getTask() == null)) || (getTask() != null && getTask().getId().equals(that.getTask().getId())));	
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
