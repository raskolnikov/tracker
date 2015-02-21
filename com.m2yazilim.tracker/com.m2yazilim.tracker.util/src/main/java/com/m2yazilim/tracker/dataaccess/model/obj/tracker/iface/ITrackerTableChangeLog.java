package com.m2yazilim.tracker.dataaccess.model.obj.tracker.iface;
import java.util.Date;
import javax.persistence.Basic;


/** 
 * Object interface mapping for hibernate-handled table: tracker_table_change_log.
 * @author autogenerated
 */

public interface ITrackerTableChangeLog {



    /**
     * Return the value associated with the column: createDate.
	 * @return A Date object (this.createDate)
	 */
	Date getCreateDate();
	

  
    /**  
     * Set the value related to the column: createDate.
	 * @param createDate the createDate value you wish to set
	 */
	void setCreateDate(final Date createDate);

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
     * Return the value associated with the column: isSent.
	 * @return A Integer object (this.isSent)
	 */
	Integer getIsSent();
	

  
    /**  
     * Set the value related to the column: isSent.
	 * @param isSent the isSent value you wish to set
	 */
	void setIsSent(final Integer isSent);

    /**
     * Return the value associated with the column: sentDate.
	 * @return A Date object (this.sentDate)
	 */
	Date getSentDate();
	

  
    /**  
     * Set the value related to the column: sentDate.
	 * @param sentDate the sentDate value you wish to set
	 */
	void setSentDate(final Date sentDate);

    /**
     * Return the value associated with the column: tableName.
	 * @return A String object (this.tableName)
	 */
	String getTableName();
	

  
    /**  
     * Set the value related to the column: tableName.
	 * @param tableName the tableName value you wish to set
	 */
	void setTableName(final String tableName);

    /**
     * Return the value associated with the column: tableRowId.
	 * @return A Integer object (this.tableRowId)
	 */
	Integer getTableRowId();
	

  
    /**  
     * Set the value related to the column: tableRowId.
	 * @param tableRowId the tableRowId value you wish to set
	 */
	void setTableRowId(final Integer tableRowId);

	// end of interface
}