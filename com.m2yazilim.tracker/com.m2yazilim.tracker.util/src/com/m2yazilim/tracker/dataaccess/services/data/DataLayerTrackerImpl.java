package com.m2yazilim.tracker.dataaccess.services.data;

import com.m2yazilim.tracker.dataaccess.factories.tracker.*;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAdminRequests;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAssigned;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerAttachments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerCache;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerCity;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerComments;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerDependencies;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerGroups;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerHistory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListCategory;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListOs;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListResolution;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListStatus;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListTasktype;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerListVersion;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotificationMessages;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotificationRecipients;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerNotifications;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerPrefs;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerProjects;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerRegistrations;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerRelated;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerReminders;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerSearches;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerTasks;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsers;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerUsersInGroups;
import com.m2yazilim.tracker.dataaccess.model.obj.tracker.TrackerVotes;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import java.util.Collection;
import com.felees.hbnpojogen.persistence.IPojoGenEntity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.felees.hbnpojogen.persistence.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
/** 
 * Data layer.
 * @author autogenerated
 */
@org.springframework.stereotype.Component
public class DataLayerTrackerImpl implements DataLayerTracker {
	/** Singleton reference to this class. */
	private static DataLayerTracker instance;
	/** map lock. */
	private static Object daoMapLock = new Object();

	/** Internal handle. */
	private static Map<Class<?>, GenericDAO<?, ?>> daoMap = null; 
	
		/** Inner handle. */
	private static ApplicationContext context;
 /** Sessionfactory in use. Filled in by Spring. */ 
    private SessionFactory sessionFactory = null;
	
	/** Handle to get back ourselves and perform correct injection. 
	 * @param ctxt filled by spring
	 */
	@Autowired
	public void setApplicationContext(ApplicationContext ctxt) {
		DataLayerTrackerImpl.context = ctxt;
	}
	
	
		
 	/**
     * Set session factory.
     * @param sessionFactory sessionfactory to use. 
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /** 
	* Returns a DAO instance of the given type.
	* @param <T> Type
	* @param persistentObject to get
	* @return GenericDAO<T, ?> object
     */
    @SuppressWarnings("unchecked")
    private <T> GenericDAO<T, ?> getDAO(final T persistentObject) {
		T persistent = persistentObject;

		synchronized (daoMapLock) {
    		if (daoMap == null) {
    			daoMap = new ConcurrentHashMap<Class<?>, GenericDAO<?, ?>>(); 
	 	   		daoMap.put(TrackerAdminRequests.class, HibernateTrackerDaoFactory.getTrackerAdminRequestsDao());
	 	   		daoMap.put(TrackerAssigned.class, HibernateTrackerDaoFactory.getTrackerAssignedDao());
	 	   		daoMap.put(TrackerAttachments.class, HibernateTrackerDaoFactory.getTrackerAttachmentsDao());
	 	   		daoMap.put(TrackerCache.class, HibernateTrackerDaoFactory.getTrackerCacheDao());
	 	   		daoMap.put(TrackerCity.class, HibernateTrackerDaoFactory.getTrackerCityDao());
	 	   		daoMap.put(TrackerComments.class, HibernateTrackerDaoFactory.getTrackerCommentsDao());
	 	   		daoMap.put(TrackerDependencies.class, HibernateTrackerDaoFactory.getTrackerDependenciesDao());
	 	   		daoMap.put(TrackerGroups.class, HibernateTrackerDaoFactory.getTrackerGroupsDao());
	 	   		daoMap.put(TrackerHistory.class, HibernateTrackerDaoFactory.getTrackerHistoryDao());
	 	   		daoMap.put(TrackerListCategory.class, HibernateTrackerDaoFactory.getTrackerListCategoryDao());
	 	   		daoMap.put(TrackerListOs.class, HibernateTrackerDaoFactory.getTrackerListOsDao());
	 	   		daoMap.put(TrackerListResolution.class, HibernateTrackerDaoFactory.getTrackerListResolutionDao());
	 	   		daoMap.put(TrackerListStatus.class, HibernateTrackerDaoFactory.getTrackerListStatusDao());
	 	   		daoMap.put(TrackerListTasktype.class, HibernateTrackerDaoFactory.getTrackerListTasktypeDao());
	 	   		daoMap.put(TrackerListVersion.class, HibernateTrackerDaoFactory.getTrackerListVersionDao());
	 	   		daoMap.put(TrackerNotifications.class, HibernateTrackerDaoFactory.getTrackerNotificationsDao());
	 	   		daoMap.put(TrackerNotificationMessages.class, HibernateTrackerDaoFactory.getTrackerNotificationMessagesDao());
	 	   		daoMap.put(TrackerNotificationRecipients.class, HibernateTrackerDaoFactory.getTrackerNotificationRecipientsDao());
	 	   		daoMap.put(TrackerPrefs.class, HibernateTrackerDaoFactory.getTrackerPrefsDao());
	 	   		daoMap.put(TrackerProjects.class, HibernateTrackerDaoFactory.getTrackerProjectsDao());
	 	   		daoMap.put(TrackerRegistrations.class, HibernateTrackerDaoFactory.getTrackerRegistrationsDao());
	 	   		daoMap.put(TrackerRelated.class, HibernateTrackerDaoFactory.getTrackerRelatedDao());
	 	   		daoMap.put(TrackerReminders.class, HibernateTrackerDaoFactory.getTrackerRemindersDao());
	 	   		daoMap.put(TrackerSearches.class, HibernateTrackerDaoFactory.getTrackerSearchesDao());
	 	   		daoMap.put(TrackerTasks.class, HibernateTrackerDaoFactory.getTrackerTasksDao());
	 	   		daoMap.put(TrackerUsers.class, HibernateTrackerDaoFactory.getTrackerUsersDao());
	 	   		daoMap.put(TrackerUsersInGroups.class, HibernateTrackerDaoFactory.getTrackerUsersInGroupsDao());
	 	   		daoMap.put(TrackerVotes.class, HibernateTrackerDaoFactory.getTrackerVotesDao());
    		}
		 }
		if (persistentObject instanceof HibernateProxy) {
			persistent = (T) ((HibernateProxy) persistentObject).getHibernateLazyInitializer().getImplementation();
		} 
		
		GenericDAO<T, ?> result = (GenericDAO<T, ?>) daoMap.get(persistent.getClass());
		if (result == null) {
			throw new IllegalAccessError("The given object is of an incorrect type. ");
		}
		return result;
    }

    /**
     * Deletes the given object from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to delete
     */
    public <T> void delete(T persistentObject) {
    	    	getDAO(persistentObject).delete(persistentObject);
    }
    /**
     * Refresh the object $class.className from disk.
     * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to reload
     */
    public <T> void refresh(T persistentObject) {
	    	getDAO(persistentObject).refresh(persistentObject);
    }

    /**
     * Saves the given object to disk.
     * @param persistentObject Object to save
	 * @param <T> A DataLayerObject-derived type
     * @return Identifier of saved object 
     */
    public <T> Serializable save(T persistentObject) {
        return getDAO(persistentObject).save(persistentObject);
    }
    /**
     * Saves or updates the given $class.className object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to save 
     */
    public <T> void saveOrUpdate(T persistentObject) {
        getDAO(persistentObject).saveOrUpdate(persistentObject);
    }
    /**
     * Updates the given object to disk.
	 * @param <T> A DataLayerObject-derived type
     * @param persistentObject Object to update 
     */
    public <T> void update(T persistentObject) {
        getDAO(persistentObject).update(persistentObject);
    }


    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerAdminRequests obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerAdminRequests(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerAdminRequestsDao().delete(loadTrackerAdminRequests(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerAdminRequests object
     */
    public TrackerAdminRequests loadTrackerAdminRequests(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAdminRequestsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerAdminRequests getTrackerAdminRequests(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAdminRequestsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerAssigned obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerAssigned(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerAssignedDao().delete(loadTrackerAssigned(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerAssigned object
     */
    public TrackerAssigned loadTrackerAssigned(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAssignedDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerAssigned getTrackerAssigned(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAssignedDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerAttachments obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerAttachments(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerAttachmentsDao().delete(loadTrackerAttachments(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerAttachments object
     */
    public TrackerAttachments loadTrackerAttachments(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAttachmentsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerAttachments getTrackerAttachments(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerAttachmentsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerCache obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerCache(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerCacheDao().delete(loadTrackerCache(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerCache object
     */
    public TrackerCache loadTrackerCache(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCacheDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerCache getTrackerCache(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCacheDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerCity obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerCity(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerCityDao().delete(loadTrackerCity(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerCity object
     */
    public TrackerCity loadTrackerCity(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCityDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerCity getTrackerCity(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCityDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerComments obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerComments(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerCommentsDao().delete(loadTrackerComments(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerComments object
     */
    public TrackerComments loadTrackerComments(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCommentsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerComments getTrackerComments(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerCommentsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerDependencies obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerDependencies(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerDependenciesDao().delete(loadTrackerDependencies(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerDependencies object
     */
    public TrackerDependencies loadTrackerDependencies(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerDependenciesDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerDependencies getTrackerDependencies(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerDependenciesDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerGroups obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerGroups(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerGroupsDao().delete(loadTrackerGroups(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerGroups object
     */
    public TrackerGroups loadTrackerGroups(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerGroupsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerGroups getTrackerGroups(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerGroupsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerHistory obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerHistory(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerHistoryDao().delete(loadTrackerHistory(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerHistory object
     */
    public TrackerHistory loadTrackerHistory(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerHistoryDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerHistory getTrackerHistory(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerHistoryDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListCategory obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListCategory(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListCategoryDao().delete(loadTrackerListCategory(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListCategory object
     */
    public TrackerListCategory loadTrackerListCategory(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListCategoryDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListCategory getTrackerListCategory(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListCategoryDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListOs obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListOs(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListOsDao().delete(loadTrackerListOs(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListOs object
     */
    public TrackerListOs loadTrackerListOs(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListOsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListOs getTrackerListOs(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListOsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListResolution obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListResolution(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListResolutionDao().delete(loadTrackerListResolution(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListResolution object
     */
    public TrackerListResolution loadTrackerListResolution(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListResolutionDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListResolution getTrackerListResolution(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListResolutionDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListStatus obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListStatus(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListStatusDao().delete(loadTrackerListStatus(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListStatus object
     */
    public TrackerListStatus loadTrackerListStatus(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListStatusDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListStatus getTrackerListStatus(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListStatusDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListTasktype obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListTasktype(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListTasktypeDao().delete(loadTrackerListTasktype(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListTasktype object
     */
    public TrackerListTasktype loadTrackerListTasktype(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListTasktypeDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListTasktype getTrackerListTasktype(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListTasktypeDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerListVersion obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerListVersion(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerListVersionDao().delete(loadTrackerListVersion(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerListVersion object
     */
    public TrackerListVersion loadTrackerListVersion(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListVersionDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerListVersion getTrackerListVersion(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerListVersionDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerNotifications obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerNotifications(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerNotificationsDao().delete(loadTrackerNotifications(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerNotifications object
     */
    public TrackerNotifications loadTrackerNotifications(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerNotifications getTrackerNotifications(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerNotificationMessages obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerNotificationMessages(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerNotificationMessagesDao().delete(loadTrackerNotificationMessages(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerNotificationMessages object
     */
    public TrackerNotificationMessages loadTrackerNotificationMessages(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationMessagesDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerNotificationMessages getTrackerNotificationMessages(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationMessagesDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerNotificationRecipients obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerNotificationRecipients(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerNotificationRecipientsDao().delete(loadTrackerNotificationRecipients(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerNotificationRecipients object
     */
    public TrackerNotificationRecipients loadTrackerNotificationRecipients(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationRecipientsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerNotificationRecipients getTrackerNotificationRecipients(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerNotificationRecipientsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerPrefs obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerPrefs(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerPrefsDao().delete(loadTrackerPrefs(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerPrefs object
     */
    public TrackerPrefs loadTrackerPrefs(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerPrefsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerPrefs getTrackerPrefs(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerPrefsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerProjects obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerProjects(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerProjectsDao().delete(loadTrackerProjects(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerProjects object
     */
    public TrackerProjects loadTrackerProjects(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerProjectsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerProjects getTrackerProjects(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerProjectsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerRegistrations obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerRegistrations(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerRegistrationsDao().delete(loadTrackerRegistrations(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerRegistrations object
     */
    public TrackerRegistrations loadTrackerRegistrations(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRegistrationsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerRegistrations getTrackerRegistrations(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRegistrationsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerRelated obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerRelated(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerRelatedDao().delete(loadTrackerRelated(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerRelated object
     */
    public TrackerRelated loadTrackerRelated(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRelatedDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerRelated getTrackerRelated(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRelatedDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerReminders obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerReminders(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerRemindersDao().delete(loadTrackerReminders(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerReminders object
     */
    public TrackerReminders loadTrackerReminders(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRemindersDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerReminders getTrackerReminders(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerRemindersDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerSearches obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerSearches(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerSearchesDao().delete(loadTrackerSearches(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerSearches object
     */
    public TrackerSearches loadTrackerSearches(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerSearchesDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerSearches getTrackerSearches(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerSearchesDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerTasks obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerTasks(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerTasksDao().delete(loadTrackerTasks(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerTasks object
     */
    public TrackerTasks loadTrackerTasks(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerTasksDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerTasks getTrackerTasks(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerTasksDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerUsers obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerUsers(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerUsersDao().delete(loadTrackerUsers(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerUsers object
     */
    public TrackerUsers loadTrackerUsers(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerUsersDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerUsers getTrackerUsers(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerUsersDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerUsersInGroups obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerUsersInGroups(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerUsersInGroupsDao().delete(loadTrackerUsersInGroups(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerUsersInGroups object
     */
    public TrackerUsersInGroups loadTrackerUsersInGroups(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerUsersInGroupsDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerUsersInGroups getTrackerUsersInGroups(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerUsersInGroupsDao().get(id);
    }  

    /** Deletes an object of a given Id. 
     * Will load the object internally so consider using delete (TrackerVotes obj) directly
     * @param id Identifier to delete
     */
    public void deleteTrackerVotes(final Integer id)  {
        HibernateTrackerDaoFactory.getTrackerVotesDao().delete(loadTrackerVotes(id));
    }
	
    /**
     * Loads the given Object.
     * @param id Identifier to load
     * @return a TrackerVotes object
     */
    public TrackerVotes loadTrackerVotes(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerVotesDao().load(id);
    }
    /**
     * Loads the given Object.
     * @param id Id to load
     * @return An object of type T
     */
     public TrackerVotes getTrackerVotes(final Integer id) {
        return HibernateTrackerDaoFactory.getTrackerVotesDao().get(id);
    }  
    /** Returns a singleton instance of this class.
     * @return an singleton instance
     */
    public static synchronized DataLayerTracker getInstance() {
        
        if (instance == null) {
        	if (context == null) {
        		throw new IllegalStateException("Context is null. Make sure Spring is initialized.");
        	}
     		instance = (DataLayerTracker) context.getBean("dataLayerTrackerImpl");
        }
        
        return instance; 
    }
    /** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public Query createQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createQuery(query);
    }
    /** Returns a criteria handle.
     * @param criteria Criteria to use
     * @return A criteria instance
     */
     public Criteria createCriteria(final String criteria) {
        return this.sessionFactory.getCurrentSession().createCriteria(criteria);
    }
    /** Returns a Query handle based on your package-level named query.
     * @param query Query to use
     * @return A query instance
     */
     public Query getNamedQuery(final String query) {
        return this.sessionFactory.getCurrentSession().getNamedQuery(query);
    }
    /** Create a new Criteria instance, for the given entity class, or a superclass of an entity class.
	* @param persistentObject a class, which is persistent, or has persistent subclasses 
	* @return Criteria instance
	*/
	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class persistentObject) {
        return this.sessionFactory.getCurrentSession().createCriteria(persistentObject);
    }
    /** Flushes the currently open session.
	*/
	public void flushSession() {
        this.sessionFactory.getCurrentSession().flush();
    }
    /** Clears the currently open session.
	*/
	public void clearSession() {
        this.sessionFactory.getCurrentSession().clear();
    }
    /** Flushes and clears the currently open session.
	*/
	public void flushAndClearSession() {
		flushSession();
		clearSession();
    }
	/** Call currentSession.replicate.
	 * @param obj to replicate
	 * @param replicationMode mode
	 */ 
	public void replicate(Object obj, ReplicationMode replicationMode) {
		this.sessionFactory.getCurrentSession().replicate(obj, replicationMode);
	}
	/** Hibernate Merge. 
	 * @param obj to merge
	 * @return obj merged.
	 */
	public Object merge(Object obj) {
		return this.sessionFactory.getCurrentSession().merge(obj);
	}
	/** Returns the current session.
	 * @return the currently active session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	/** Returns a query handle.
     * @param query Query to use
     * @return A query instance
     */
     public SQLQuery createSQLQuery(final String query) {
        return this.sessionFactory.getCurrentSession().createSQLQuery(query);
    }
    /** Remove this instance from the session cache. 
	 * Changes to the instance will not be synchronized with the database
	 * @param obj object to evict
	 */
	public void evict(Object obj) {
        this.sessionFactory.getCurrentSession().evict(obj);
    }
    /**
     * Return the persistent instance of the given entity class with the given 
     * identifier, or null if there is no such persistent instance. 
     * (If the instance, or a proxy for the instance, is already 
     * associated with the session, return that instance or proxy)
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return a persistent instance or null
     * @throws HibernateException
     */
	public Object get(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().get(clazz, id);
    }	


    /**
     * Return the persistent instance of the given entity class with the given identifier, assuming that the instance exists.
     *You should not use this method to determine if an instance exists (use get() instead). Use this only to retrieve an instance that you assume exists, where non-existence would be an actual error.
     *
     * @param clazz a persistent class
     * @param id a valid identifier of an existing persistent instance of the class
     * @return the persistent instance or proxy
     * @throws HibernateException
     */
	public Object load(Class<?> clazz, Serializable id) throws HibernateException {
        return this.sessionFactory.getCurrentSession().load(clazz, id);  
    }
	/**
	 * Reattaches the given entity to the current session using LockMode.NONE
	 *
	 * @param entity to reattach
	 */
	public void reattachEntityWithNoLock(IPojoGenEntity entity) {
		if (entity != null) {
        		this.sessionFactory.getCurrentSession().lock(entity, LockMode.NONE);
    		}
	}
	/**
	 * Reattaches the given entities to the current session.
	 *
	 * @param entities to attach
	 */
	public void reattachEntitiesWithNoLock(Collection<? extends IPojoGenEntity> entities) {
   		if (entities != null) {
		       for (IPojoGenEntity entity : entities) {
            		this.sessionFactory.getCurrentSession().lock(entity, LockMode.NONE);
        		 }
    		}
	}}
