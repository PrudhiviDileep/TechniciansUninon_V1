
package com.org.telugucineandtvoutdoorunittechniciansunion.init;


 import java.io.Serializable;
 import java.lang.reflect.Method;
 import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;

 import javax.persistence.Table;
import javax.transaction.Transactional;

 import org.apache.log4j.Logger;
 import org.hibernate.Query;
 import org.hibernate.SQLQuery;
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
 import org.hibernate.exception.ConstraintViolationException;
 import org.hibernate.internal.SessionFactoryImpl;
 import org.hibernate.metadata.ClassMetadata;
 import org.hibernate.persister.entity.AbstractEntityPersister;
 import org.hibernate.type.ComponentType;
 import org.hibernate.type.Type;
 import org.json.simple.JSONArray;
 import org.json.simple.JSONObject;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
// import org.springframework.dao.DataIntegrityViolationException;
 import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.config.AppConfigurations;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.ConfigUtility;






 @Repository
 @Transactional
 public class DataAccess
 {
	/* 41 */ private static Logger logger = Logger.getLogger(DataAccess.class);
	
	
	 @Autowired
	 private SessionFactory sessionFactory;


	@Autowired
	public  AppConfigurations  appConfigurations;
	
	 public SessionFactory getSessionFactory() {
		/* 53 */ return this.sessionFactory;
		 }

	
	 public void setSessionFactory(SessionFactory sessionFactory) {
		/* 57 */ this.sessionFactory = sessionFactory;
		 }

	
	
	 private Session getCurrentSession() {
		/* 62 */ return this.sessionFactory.getCurrentSession();
		 }

	
	 public DataAccess(LocalSessionFactoryBean localSessionFactoryBean) {
		/* 66 */ this.sessionFactory = (SessionFactory) localSessionFactoryBean;
		 }

	
	
	
	
	
	
	 public DataAccess() {
	}

	
	
	
	
	
	 public int executeUpdateSQL(String query) throws Exception {
		/* 81 */ int result = 0;
		 try {
			/* 83 */ JSONObject queryDetails = getDetailsOfQuery(query, "SQL");
			/* 84 */ Session session = getCurrentSession();
			/* 85 */ SQLQuery sQLQuery = session.createSQLQuery(query);
			
			/* 87 */ result = sQLQuery.executeUpdate();
			/* 88 */ } catch (Exception e) {
			/* 89 */ logger.error("Error while executing save/update", e);
			/* 90 */ throw new Exception(e);
			 }
		/* 92 */ return result;
		 }

	
	 public int executeUpdateSQL(String query, Map<String, Object> parameters) throws Exception {
		/* 96 */ int result = 0;
		
		 try {
			/* 99 */ Session session = getCurrentSession();
			/* 100 */ SQLQuery sQLQuery = session.createSQLQuery(query);
			/* 101 */ sQLQuery.setProperties(parameters);
			/* 102 */ result = sQLQuery.executeUpdate();
			
			
			 }
		/* 106 */ catch (Exception e) {
			/* 107 */ logger.error("Error while executing save/update", e);
			/* 108 */ throw new Exception(e);
			 }
		/* 110 */ return result;
		 }

	
	 public int executeUpdateSQL(String query, Map<String, Object> parameters, Object entity)
			throws Exception {
		/* 114 */ int result = 0;
		
		 try {
			/* 117 */ Session session = getCurrentSession();
			/* 118 */ SQLQuery sQLQuery = session.createSQLQuery(query);
			/* 119 */ sQLQuery.setProperties(parameters);
			/* 120 */ result = sQLQuery.executeUpdate();
			
			
			 }
		/* 124 */ catch (Exception e) {
			/* 125 */ logger.error("Error while executing save/update", e);
			/* 126 */ throw new Exception(e);
			 }
		/* 128 */ return result;
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public List queryWithParamsWithLimit(String query, Map<String, Object> parameters, int limit, int start)
			throws Exception {
		/* 152 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		/* 153 */ Session session = getCurrentSession();
		/* 154 */ List list = null;
		 try {
			/* 156 */ Query queryObj = session.createQuery(query).setMaxResults(limit).setFirstResult(start);
			
			/* 158 */ if (parameters != null && !parameters.isEmpty()) {
				/* 159 */ for (String param : parameters.keySet()) {
					/* 160 */ if (parameters.get(param) instanceof Collection) {
						/* 161 */ queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 163 */ queryObj.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 168 */ list = queryObj.list();
			 }
		/* 170 */ catch (Exception e) {
			/* 171 */ logger.error("error in query() , ", e);
			/* 172 */ throw new Exception(e);
			 }
		/* 174 */ return list;
		 }

	
	 public int queryCount(String query, Map<String, Object> parameters) throws Exception {
		/* 178 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		/* 179 */ Session session = getCurrentSession();
		/* 180 */ int count = 0;
		 try {
			/* 182 */ Query queryObj = session.createQuery(query);
			
			/* 184 */ if (parameters != null && !parameters.isEmpty()) {
				/* 185 */ for (String param : parameters.keySet()) {
					/* 186 */ if (parameters.get(param) instanceof Collection) {
						/* 187 */ queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 189 */ queryObj.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 194 */ count = ((Long) queryObj.uniqueResult()).intValue();
			 }
		/* 196 */ catch (Exception e) {
			/* 197 */ logger.error("error in query() , ", e);
			/* 198 */ throw new Exception(e);
			 }
		/* 200 */ return count;
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 @Transactional
	 public List queryWithParams(String query, Map<String, Object> parameters) throws Exception {
		/* 225 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		
		/* 227 */ Session session = getCurrentSession();
		
		
		/* 230 */ List list = null;
		 try {
			/* 232 */ Query queryObj = session.createQuery(query);
			/* 233 */ if (parameters != null && !parameters.isEmpty()) {
				/* 234 */ for (String param : parameters.keySet()) {
					/* 235 */ if (parameters.get(param) instanceof Collection) {
						/* 236 */ queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 238 */ queryObj.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 243 */ list = queryObj.list();
			 }
		/* 245 */ catch (Exception e) {
			/* 246 */ e.printStackTrace();
			/* 247 */ logger.error("error in query() , ", e);
			/* 248 */ throw new Exception(e);
			 }
		/* 250 */ return list;
		 }

	
	
	
	 public List sqlqueryWithParams(String query, Map<String, Object> parameters) throws Exception {
		/* 256 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		/* 257 */ Session session = getCurrentSession();
		/* 258 */ List list = null;
		 try {
			/* 260 */ SQLQuery sQLQuery = session.createSQLQuery(query);
			
			/* 262 */ if (parameters != null && !parameters.isEmpty()) {
				/* 263 */ for (String param : parameters.keySet()) {
					/* 264 */ if (parameters.get(param) instanceof Collection) {
						/* 265 */ sQLQuery.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 267 */ sQLQuery.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 272 */ list = sQLQuery.list();
			 }
		/* 274 */ catch (Exception e) {
			/* 275 */ logger.error("error in query() , ", e);
			/* 276 */ throw new Exception(e);
			 }
		/* 278 */ return list;
		 }

	
	
	
	 public List sqlqueryWithParamsLimit(String query, Map<String, Object> parameters, int limit, int start)
			throws Exception {
		/* 284 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		/* 285 */ Session session = getCurrentSession();
		/* 286 */ List list = null;
		 try {
			/* 288 */ SQLQuery sQLQuery = null;
			/* 289 */ if (limit != 0) {
				/* 290 */ sQLQuery = (SQLQuery) session.createSQLQuery(query).setMaxResults(limit)
						.setFirstResult(start);
				 } else {
				/* 292 */ sQLQuery = session.createSQLQuery(query);
				 }
			
			/* 295 */ if (parameters != null && !parameters.isEmpty()) {
				/* 296 */ for (String param : parameters.keySet()) {
					/* 297 */ if (parameters.get(param) instanceof Collection) {
						/* 298 */ sQLQuery.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 300 */ sQLQuery.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 305 */ list = sQLQuery.list();
			 }
		/* 307 */ catch (Exception e) {
			/* 308 */ logger.error("error in query() , ", e);
			/* 309 */ throw new Exception(e);
			 }
		/* 311 */ return list;
		 }

	
	
	 public void updateQuery(String queryString, Map<String, Object> map) throws Exception {
		/* 316 */ Session session = getCurrentSession();
		/* 317 */ int result = 0;
		
		
		 try {
			/* 321 */ Query query = session.createQuery(queryString);
			/* 322 */ query.setProperties(map);
			/* 323 */ result = query.executeUpdate();
			
			
			
			 }
		/* 328 */ catch (Exception e) {
			/* 329 */ logger.error("error in query() , ", e);
			/* 330 */ throw new Exception(e);
			 }
		 }

	
	
	
	 public int updateQueryByCount(String queryString, Map<String, Object> parameters) throws Exception {
		/* 337 */ Session session = getCurrentSession();
		/* 338 */ int result = 0;
		
		
		 try {
			/* 342 */ Query queryObj = session.createQuery(queryString);
			/* 343 */ queryObj.setProperties(parameters);
			/* 344 */ result = queryObj.executeUpdate();
			
			 }
		/*
		 * 347 catch (DataIntegrityViolationException die) { 348 result = -1; 349
		 * logger.error("error in query() , ", (Throwable) die); 350 }
		 */catch (ConstraintViolationException e) {
			/* 351 */ logger.error("error in query()-2 , ", (Throwable) e);
			/* 352 */ result = -2;
			/* 353 */ } catch (Exception e) {
			/* 354 */ logger.error("error in query() , ", e);
			/* 355 */ throw new Exception(e);
			 }
		/* 357 */ return result;
		 }

	
	 public int updateQueryReturn(String queryString, Map<String, Object> map) throws Exception {
		/* 361 */ int result = 0;
		/* 362 */ Session session = getCurrentSession();
		
		 try {
			/* 365 */ Query query = session.createQuery(queryString);
			/* 366 */ query.setProperties(map);
			/* 367 */ result = query.executeUpdate();
			
			
			
			 }
		/* 372 */ catch (Exception e) {
			/* 373 */ logger.error("error in query() , ", e);
			/* 374 */ throw new Exception(e);
			 }
		/* 376 */ return result;
		 }

	
	
	
	
	
	
	
	
	 public <T> T save(T t) throws Exception {
		 try {
			/* 388 */ Session session = getCurrentSession();
			/* 389 */ session.save(t);
			 }
		/*
		 * 391 catch (DataIntegrityViolationException die) { 392
		 * logger.error("Unable to save entity", (Throwable) die); 393
		 * die.printStackTrace(); 394 throw new Exception(die); }
		 */
		/* 396 */ catch (ConstraintViolationException cve) {
			/* 397 */ logger.error("Unable to save entity", (Throwable) cve);
			/* 398 */ cve.printStackTrace();
			/* 399 */ throw new Exception(cve);
			 }
		/* 401 */ catch (Exception e) {
			/* 402 */ logger.error("Unable to save entity", e);
			/* 403 */ throw new Exception(e);
			 }
		/* 405 */ return t;
		 }

	
	
	 public <T> T saveObj(T t) throws Exception {
		 try {
			/* 411 */ Session session = getCurrentSession();
			/* 412 */ session.save(t);
			 }
		/*
		 * 414 catch (DataIntegrityViolationException die) { 415
		 * logger.error("Unable to save entity", (Throwable)die); 416
		 * die.printStackTrace(); 417 throw new Exception(die); }
		 */
		/* 419 */ catch (ConstraintViolationException cve) {
			/* 420 */ logger.error("Unable to save entity", (Throwable) cve);
			/* 421 */ cve.printStackTrace();
			/* 422 */ throw new Exception(cve);
			 }
		/* 424 */ catch (Exception e) {
			/* 425 */ logger.error("Unable to save entity", e);
			/* 426 */ throw new Exception(e);
			 }
		/* 428 */ return t;
		 }

	
	
	 public <T> T persist(T t) throws Exception {
		 try {
			/* 434 */ Session session = getCurrentSession();
			/* 435 */ session.persist(t);
			 }
		/* 437 */ catch (Exception e) {
			/* 438 */ logger.error("Unable to save entity", e);
			/* 439 */ throw new Exception(e);
			 }
		/* 441 */ return t;
		 }

	
	 public <T> List<T> save(List<T> list) throws Exception {
		 try {
			/* 446 */ Session session = getCurrentSession();
			/* 447 */ for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
				T entity = iterator.next();
				/* 448 */ session.save(entity);
			}
			
			
			/* 451 */ } catch (Exception e) {
			/* 452 */ logger.error("Unable to save entity", e);
			/* 453 */ throw new Exception(e);
			 }
		/* 455 */ return list;
		 }

	
	
	
	 public List<Object> updateList(List<Object> list) throws Exception {
		 try {
			/* 462 */ Session session = getCurrentSession();
			/* 463 */ for (Object entity : list)
			 {
				/* 465 */ session.update(entity);
				
				 }
			 }
		/* 469 */ catch (Exception e) {
			/* 470 */ logger.error("Unable to update list of entities entity", e);
			/* 471 */ throw new Exception(e);
			 }
		/* 473 */ return list;
		 }

	
	
	
	
	
	
	 public <T> T saveOrUpdate(T t) throws Exception {
		 try {
			/* 483 */ Session session = getCurrentSession();
			/* 484 */ session.saveOrUpdate(t);
			 }
		/* 486 */ catch (Exception e) {
			/* 487 */ logger.error("Unable to save/update entity", e);
			/* 488 */ throw new Exception(e);
			 }
		/* 490 */ return t;
		 }

	
	
	
	
	
	
	
	 public <T> T update(T t) throws Exception {
		 try {
			/* 501 */ if (t instanceof List) {
				/* 502 */ List<Object> list = (List) t;
				/* 503 */ updateList(list);
				 } else {
				/* 505 */ Session session = getCurrentSession();
				
				/* 507 */ session.update(t);
				
				 }
			
			 }
		/* 512 */ catch (Exception e) {
			/* 513 */ logger.error("Unable to update entity", e);
			/* 514 */ throw new Exception(e);
			 }
		/* 516 */ return t;
		 }

	
	
	 public <T> T updateObj(T t) throws Exception {
		 try {
			/* 522 */ if (t instanceof List) {
				/* 523 */ List<Object> list = (List) t;
				/* 524 */ updateList(list);
				 } else {
				/* 526 */ Session session = getCurrentSession();
				
				/* 528 */ session.update(t);
				
				 }
			
			 }
		/* 533 */ catch (Exception e) {
			/* 534 */ logger.error("Unable to update entity", e);
			/* 535 */ throw new Exception(e);
			 }
		/* 537 */ return t;
		 }

	
	
	
	
	
	
	 public <T> T merge(T t) throws Exception {
		 try {
			/* 547 */ Session session = getCurrentSession();
			/* 548 */ session.merge(t);
			 }
		/* 550 */ catch (Exception e) {
			/* 551 */ logger.error("Unable to save/update entity", e);
			/* 552 */ throw new Exception(e);
			 }
		/* 554 */ return t;
		 }
	

	
	
	
	
	
	
	
	
	
	
	 public <T> void delete(T t) throws Exception {
		 try {
			/* 569 */ Session session = getCurrentSession();
			/* 570 */ session.delete(t);
			 }
		/* 572 */ catch (Exception e) {
			/* 573 */ logger.error("Unable to save/update entity", e);
			/* 574 */ throw new Exception(e);
			 }
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	 public Object loadById(Class t, String identifier) throws Exception {
		 Object obj;
		/* 591 */ Session session = getCurrentSession();
		
		 try {
			/* 594 */ obj = session.get(t, identifier);
			/* 595 */ } catch (Exception e) {
			/* 596 */ logger.error("Error while getting object by id", e);
			/* 597 */ throw new Exception(e);
			 }
		
		/* 600 */ return obj;
		 }

	
	
	 public Object loadById(Class t, BigDecimal identifier) throws Exception {
		 Object obj;
		/* 606 */ Session session = getCurrentSession();
		
		 try {
			/* 609 */ obj = session.get(t, identifier);
			/* 610 */ } catch (Exception e) {
			/* 611 */ logger.error("Error while getting object by id", e);
			/* 612 */ throw new Exception(e);
			 }
		
		/* 615 */ return obj;
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	 public Object loadById(Class t, int identifier) throws Exception {
		 Object obj;
		/* 631 */ Session session = getCurrentSession();
		
		 try {
			/* 634 */ obj = session.get(t, Integer.valueOf(identifier));
			/* 635 */ } catch (Exception e) {
			/* 636 */ logger.error("Error while getting object by id", e);
			/* 637 */ throw new Exception(e);
			 }
		/* 639 */ return obj;
		 }

	
	
	
	
	
	
	
	
	
	
	
	 public Object loadById(Class t, Long identifier) throws Exception {
		 Object obj;
		/* 654 */ Session session = getCurrentSession();
		
		 try {
			/* 657 */ obj = session.get(t, identifier);
			/* 658 */ } catch (Exception e) {
			/* 659 */ logger.error("Error while getting object by id", e);
			/* 660 */ throw new Exception(e);
			 }
		
		/* 663 */ return obj;
		 }

	
	
	
	
	
	
	
	
	
	
	
	 public List executeNativeSQL(String query, Map<Integer, Object> parameters) throws Exception {
		/* 677 */ List list = new ArrayList();
		 try {
			/* 679 */ Session session = getCurrentSession();
			/* 680 */ SQLQuery sQLQuery = session.createSQLQuery(query);
			/* 681 */ if (parameters != null) {
				/* 682 */ for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					/* 683 */ sQLQuery.setParameter(param, parameters.get(Integer.valueOf(param)));
				}
				
				 }
			/* 686 */ list = sQLQuery.list();
			/* 687 */ } catch (Exception e) {
			/* 688 */ logger.error("Error on select query", e);
			/* 689 */ throw new Exception(e);
			 }
		/* 691 */ return list;
		 }

	
	
	
	
	
	
	
	
	 public void executeNativeUpdateSQLWithSimpleParams(String query, Map<Integer, Object> parameters)
			throws Exception {
		 try {
			/* 703 */ Session session = getCurrentSession();
			
			/* 705 */ SQLQuery sQLQuery = session.createSQLQuery(query.toString());
			/* 706 */ if (parameters != null) {
				/* 707 */ for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					/* 708 */ sQLQuery.setParameter(param, parameters.get(Integer.valueOf(param)));
				}
				
				 }
			/* 711 */ sQLQuery.executeUpdate();
			 }
		/* 713 */ catch (Exception e) {
			/* 714 */ logger.error("Error on update / Save", e);
			/* 715 */ throw new Exception(e);
			 }
		 }

	
	
	
	 public List executeNativeSQLWithEntity(String query, Map<Integer, Object> parameters, Class t)
			throws Exception {
		/* 722 */ List list = new ArrayList();
		 try {
			/* 724 */ Session session = getCurrentSession();
			
			/* 726 */ SQLQuery q = session.createSQLQuery(query);
			/* 727 */ q.addEntity(t);
			/* 728 */ if (parameters != null) {
				/* 729 */ for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					/* 730 */ q.setParameter(param, parameters.get(Integer.valueOf(param)));
				}
				
				 }
			/* 733 */ list = q.list();
			/* 734 */ } catch (Exception e) {
			/* 735 */ logger.error("Error on select query", e);
			/* 736 */ throw new Exception(e);
			 }
		/* 738 */ return list;
		 }

	
	
	
	 public List executeSQLWithEntity(String query, Map<String, Object> parameters, Class t)
			throws Exception {
		/* 744 */ List list = new ArrayList();
		 try {
			/* 746 */ Session session = getCurrentSession();
			
			/* 748 */ SQLQuery q = session.createSQLQuery(query);
			/* 749 */ q.addEntity(t);
			/* 750 */ if (parameters != null) {
				/* 751 */ q.setProperties(parameters);
				 }
			
			/* 754 */ list = q.list();
			/* 755 */ } catch (Exception e) {
			/* 756 */ logger.error("Error on select query", e);
			/* 757 */ throw new Exception(e);
			 }
		/* 759 */ return list;
		 }

	
	
	 public List queryWithMaxLimit(String query, Map<String, Object> parameters, int limit) throws Exception {
		/* 764 */ logger.debug(String.valueOf(String.valueOf(query)) + " : params - " + parameters);
		/* 765 */ Session session = getCurrentSession();
		/* 766 */ List list = null;
		 try {
			/* 768 */ Query queryObj = session.createQuery(query).setMaxResults(limit);
			
			/* 770 */ if (parameters != null && !parameters.isEmpty()) {
				/* 771 */ for (String param : parameters.keySet()) {
					/* 772 */ if (parameters.get(param) instanceof Collection) {
						/* 773 */ queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
						 }
					/* 775 */ queryObj.setParameter(param, parameters.get(param));
					 }
				 }
			
			
			/* 780 */ list = queryObj.list();
			 }
		/* 782 */ catch (Exception e) {
			/* 783 */ logger.error("error in query() , ", e);
			/* 784 */ throw new Exception(e);
			 }
		/* 786 */ return list;
		 }

	
	
	 public Object get(Class<?> clazz, Serializable identifier) throws Exception {
		/* 791 */ Object object = null;
		/* 792 */ Session session = getCurrentSession();
		 try {
			/* 794 */ object = session.get(clazz, identifier);
			 }
		/* 796 */ catch (Exception e) {
			/* 797 */ e.printStackTrace();
			 }
		/* 799 */ return object;
		 }

	
	
	 public String[] getPropertiesByClass(Class classname) {
		/* 804 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
		/* 805 */ return classMetadata.getPropertyNames();
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public JSONObject getColumnByPropertyName(String propertyName, Class pojoClass) {
		/* 872 */ JSONObject columnObject = new JSONObject();
		
		 try {
			/* 875 */ JSONArray propertyArray = getColsByPropertiesNClass(pojoClass);
			/* 876 */ if (propertyArray != null && !propertyArray.isEmpty())
			 {
				/* 878 */ for (int i = 0; i < propertyArray.size(); i++) {
					/* 879 */ JSONObject propertyObject = (JSONObject) propertyArray.get(i);
					/* 880 */ if (propertyObject != null && !propertyObject.isEmpty()) {
						/* 881 */ String pojoProperty = (String) propertyObject.get("property");
						/* 882 */ if (pojoProperty != null && !"".equalsIgnoreCase(pojoProperty) && propertyName != null
								&&
								/* 883 */ propertyName.equalsIgnoreCase(pojoProperty)) {
							
							/* 885 */ columnObject.put(pojoProperty, propertyObject.get("columnname"));
							
							
							
							 break;
							 }
						 }
					 }
				 }
			/* 894 */ } catch (Exception e) {
			/* 895 */ e.printStackTrace();
			 }
		
		/* 898 */ return columnObject;
		 }

	
	
	 public JSONObject getEntityByTable(String TableName) {
		/* 903 */ JSONArray jsArray = new JSONArray();
		/* 904 */ JSONObject jsObject = null;
		/* 905 */ String tableName = "";
		
		 try {
			/* 908 */ Map<String, ClassMetadata> map = this.sessionFactory.getAllClassMetadata();
			/* 909 */ for (String entityName : map.keySet()) {
				/* 910 */ SessionFactoryImpl sfImpl = (SessionFactoryImpl) this.sessionFactory;
				/* 911 */ tableName = ((AbstractEntityPersister) sfImpl.getEntityPersister(entityName)).getTableName();
				
				
				/* 914 */ if (tableName != null && tableName.contains(".")) {
					/* 915 */ tableName = tableName.substring(tableName.lastIndexOf(".") + 1, tableName.length());
					 }
				
				/* 918 */ TableName = TableName.trim();
				/* 919 */ tableName = tableName.trim();
				/* 920 */ if (tableName.equalsIgnoreCase(TableName)) {
					/* 921 */ jsObject = new JSONObject();
					/* 922 */ jsObject.put("entityname", entityName);
					/* 923 */ jsObject.put("tablename", tableName);
					/* 924 */ jsArray.add(jsObject);
					
					 break;
					 }
				 }
			/* 929 */ } catch (Exception e) {
			/* 930 */ e.printStackTrace();
			 }
		
		/* 933 */ return jsObject;
		 }

	
	
	 public JSONArray getColsByPropertiesNClass(Class classname) {
		/* 938 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
		
		/* 940 */ String[] propertyNames = classMetadata.getPropertyNames();
		/* 941 */ AbstractEntityPersister as = null;
		/* 942 */ JSONArray jsArray = null;
		/* 943 */ JSONObject jsObject = null;
		 try {
			/* 945 */ List<String> identifierPropertyList = new ArrayList();
			/* 946 */ jsArray = new JSONArray();
			/* 947 */ as = (AbstractEntityPersister) classMetadata;
			/* 948 */ Type type = as.getPropertyType(as.getIdentifierPropertyName());
			/* 949 */ ComponentType cType = null;
			/* 950 */ if (type.isComponentType()) {
				/* 951 */ cType = (ComponentType) type;
				/* 952 */ identifierPropertyList = Arrays.asList(cType.getPropertyNames());
				
				
				
				
				 }
			/* 958 */ else if (type.getName() == "string") {
				
				/* 960 */ identifierPropertyList.add(as.getIdentifierPropertyName());
				 }
			
			/* 963 */ List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());
			
			/* 965 */ if (identifierColsList != null && !identifierColsList.isEmpty() &&
			/* 966 */ identifierPropertyList != null && identifierPropertyList.size() == identifierColsList.size() &&
			/* 967 */ identifierColsList.size() != 0)
			 {
				/* 969 */ for (int j = 0; j < identifierColsList.size(); j++) {
					
					/* 971 */ Object get = identifierColsList.get(j);
					/* 972 */ jsObject = new JSONObject();
					/* 973 */ jsObject.put("property", identifierPropertyList.get(j));
					/* 974 */ jsObject.put("columnname", identifierColsList.get(j));
					/* 975 */ jsArray.add(jsObject);
					 }
				 }
			
			
			
			/* 981 */ for (int i = 0; i < propertyNames.length; i++) {
				/* 982 */ jsObject = new JSONObject();
				
				
				
				/* 986 */ jsObject.put("property", propertyNames[i]);
				/* 987 */ jsObject.put("columnname", as.getPropertyColumnNames(propertyNames[i])[0]);
				/* 988 */ jsArray.add(jsObject);
				
				
				 }
			
			
			 }
		/* 995 */ catch (Exception exception) {
		}
		
		/* 997 */ return jsArray;
		 }

	
	
	 public JSONObject getAllColsByPropertiesNClass(Class classname) {
		/* 1002 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
		
		
		/* 1005 */ String[] propertyNames = classMetadata.getPropertyNames();
		
		
		
		/* 1009 */ AbstractEntityPersister as = null;
		/* 1010 */ JSONArray jsArray = null;
		/* 1011 */ JSONObject POJOObject = new JSONObject();
		
		/* 1013 */ JSONObject jsObject = new JSONObject();
		
		 try {
			/* 1016 */ jsArray = new JSONArray();
			/* 1017 */ as = (AbstractEntityPersister) classMetadata;
			
			/* 1019 */ String[] asProperyNames = as.getPropertyNames();
			/* 1020 */ List<String> list = Arrays.asList(propertyNames);
			/* 1021 */ List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());
			/* 1022 */ List<String> identifierPropertyList = new ArrayList();
			
			
			
			
			
			/* 1028 */ Type type = as.getPropertyType(as.getIdentifierPropertyName());
			/* 1029 */ ComponentType cType = null;
			/* 1030 */ if (type.isComponentType()) {
				/* 1031 */ cType = (ComponentType) type;
				/* 1032 */ identifierPropertyList = Arrays.asList(cType.getPropertyNames());
				
				
				 }
			/* 1036 */ else if (type.getName() == "string") {
				
				/* 1038 */ identifierPropertyList.add(as.getIdentifierPropertyName());
				 }
			
			/* 1041 */ Type Itype = as.getIdentifierType();
			
			
			
			/* 1045 */ if (identifierColsList.size() == identifierPropertyList.size()
					&& identifierPropertyList.size() != 0) {
				/* 1046 */ JSONObject idJSONObject = new JSONObject();
				/* 1047 */ for (int j = 0; j < identifierColsList.size(); j++) {
					
					/* 1049 */ Object propertyName = identifierPropertyList.get(j);
					/* 1050 */ Object columnName = identifierColsList.get(j);
					
					/* 1052 */ idJSONObject.put(String.valueOf(columnName).toUpperCase(), propertyName);
					 }
				/* 1054 */ POJOObject.put("id", idJSONObject);
				 }
			
			/* 1057 */ for (int i = 0; i < list.size(); i++) {
				
				/* 1059 */ String[] allColumnNames = as.getPropertyColumnNames(list.get(i));
				/* 1060 */ if (!identifierColsList.contains(allColumnNames[0]))
				 {
					/* 1062 */ jsObject.put(as.getPropertyColumnNames((String) list.get(i))[0].toUpperCase(),
							/* 1063 */ list.get(i));
					 }
				 }
			
			
			
			
			/* 1070 */ POJOObject.put("nonId", jsObject);
			
			 }
		/* 1073 */ catch (Exception e) {
			/* 1074 */ e.printStackTrace();
			 }
		
		
		
		/* 1079 */ return POJOObject;
		 }

	
	
	 public JSONObject getAllColsByProperties(Class classname) {
		/* 1084 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
		
		
		/* 1087 */ String[] propertyNames = classMetadata.getPropertyNames();
		
		
		
		/* 1091 */ AbstractEntityPersister as = null;
		/* 1092 */ JSONArray jsArray = null;
		/* 1093 */ JSONObject POJOObject = new JSONObject();
		
		/* 1095 */ JSONObject jsObject = new JSONObject();
		
		 try {
			/* 1098 */ jsArray = new JSONArray();
			/* 1099 */ as = (AbstractEntityPersister) classMetadata;
			
			/* 1101 */ String[] asProperyNames = as.getPropertyNames();
			/* 1102 */ List<String> list = Arrays.asList(propertyNames);
			/* 1103 */ List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());
			/* 1104 */ List<String> identifierPropertyList = new ArrayList();
			
			
			
			
			
			/* 1110 */ Type type = as.getPropertyType(as.getIdentifierPropertyName());
			/* 1111 */ ComponentType cType = null;
			/* 1112 */ if (type.isComponentType()) {
				/* 1113 */ cType = (ComponentType) type;
				/* 1114 */ identifierPropertyList = Arrays.asList(cType.getPropertyNames());
				
				
				 }
			/* 1118 */ else if (type.getName() == "string") {
				
				/* 1120 */ identifierPropertyList.add(as.getIdentifierPropertyName());
				 }
			
			/* 1123 */ Type Itype = as.getIdentifierType();
			
			
			
			/* 1127 */ if (identifierColsList.size() == identifierPropertyList.size()
					&& identifierPropertyList.size() != 0) {
				/* 1128 */ JSONObject idJSONObject = new JSONObject();
				/* 1129 */ for (int j = 0; j < identifierColsList.size(); j++) {
					
					/* 1131 */ Object propertyName = identifierPropertyList.get(j);
					/* 1132 */ Object columnName = identifierColsList.get(j);
					
					/* 1134 */ idJSONObject.put(propertyName, columnName);
					 }
				
				
				/* 1138 */ POJOObject.put("id", idJSONObject);
				 }
			
			/* 1141 */ for (int i = 0; i < list.size(); i++) {
				
				/* 1143 */ String[] allColumnNames = as.getPropertyColumnNames(list.get(i));
				/* 1144 */ if (!identifierColsList.contains(allColumnNames[0]))
				 {
					/* 1146 */ jsObject.put(list.get(i), as.getPropertyColumnNames((String) list.get(i))[0]);
					 }
				 }
			
			
			
			
			
			
			
			
			
			
			
			/* 1160 */ POJOObject.put("nonId", jsObject);
			
			 }
		/* 1163 */ catch (Exception e) {
			/* 1164 */ e.printStackTrace();
			 }
		
		
		
		/* 1169 */ return POJOObject;
		 }

	
	
	
	 public String getColByPropertyNClass(Class classname, String propertyname) {
		/* 1175 */ ClassMetadata classMetadata = null;
		/* 1176 */ AbstractEntityPersister as = null;
		/* 1177 */ String columnname = "";
		 try {
			/* 1179 */ classMetadata = this.sessionFactory.getClassMetadata(classname);
			/* 1180 */ as = (AbstractEntityPersister) classMetadata;
			/* 1181 */ columnname = as.getPropertyColumnNames(propertyname)[0];
			/* 1182 */ } catch (Exception exception) {
		}
		
		/* 1184 */ return columnname;
		 }

	
	 public JSONArray createjsonObjByjavaObj(Class classname, Object classobj) throws Exception {
		/* 1188 */ JSONObject jSONObject = null;
		/* 1189 */ JSONObject jsProperty = null;
		/* 1190 */ String className = "com.pilog.mdm.pojo.ORecordText";
		/* 1191 */ Class<?> c = Class.forName(getFullQualifiedNameOfPOJO(className.trim()));
		/* 1192 */ JSONArray jsResultsArray = new JSONArray();
		
		 try {
			/* 1195 */ Method method = c.getDeclaredMethod("method name", new Class[0]);
			/* 1196 */ method.invoke(classobj, new Object[0]);
			
			/* 1198 */ Method[] methods = classname.getMethods();
			
			/* 1200 */ JSONArray jsArray = getColsByPropertiesNClass(classname);
			/* 1201 */ for (int i = 0; i < jsArray.size(); i++) {
				/* 1202 */ jsProperty = (JSONObject) jsArray.get(i);
				/* 1203 */ jSONObject = new JSONObject();
				/* 1204 */ jSONObject.put("property", jsProperty.get("property"));
				
				/* 1206 */ jSONObject.put("value", methods[i].invoke(classobj, new Object[0]));
				/* 1207 */ jsResultsArray.add(jSONObject);
				
				 }
			
			 }
		/* 1212 */ catch (Exception exception) {
		}
		
		
		/* 1215 */ return jsResultsArray;
		 }

	
	
	
	 public void getTypeOfProperty(Class classname, Object propertyValue) {
		 try {
			/* 1222 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
			/* 1223 */ AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
			 }
		/* 1225 */ catch (Exception e) {
			/* 1226 */ e.printStackTrace();
			 }
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public boolean getIdentifierType(Class classname) {
		/* 1278 */ boolean object = false;
		 try {
			/* 1280 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
			/* 1281 */ AbstractEntityPersister as = (AbstractEntityPersister) classMetadata;
			
			
			
			/* 1285 */ Type type = classMetadata.getPropertyType(classMetadata.getIdentifierPropertyName());
			
			/* 1287 */ if (classMetadata.getIdentifierPropertyName() != null &&
			/* 1288 */ !"".equalsIgnoreCase(classMetadata.getIdentifierPropertyName()) &&
			/* 1289 */ "id".equalsIgnoreCase(classMetadata.getIdentifierPropertyName())) {
				/* 1290 */ object = true;
				 }
			 }
		/* 1293 */ catch (Exception e) {
			/* 1294 */ e.printStackTrace();
			 }
		/* 1296 */ return object;
		 }

	
	
	 public String getTableByEntity(Object entity) {
		/* 1301 */ String tableName = "";
		 try {
			/* 1303 */ Class<?> clazz = entity.getClass();
			/* 1304 */ Table table = clazz.<Table>getAnnotation(Table.class);
			/* 1305 */ tableName = table.name();
			/* 1306 */ } catch (Exception e) {
			/* 1307 */ e.printStackTrace();
			 }
		/* 1309 */ return tableName;
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public JSONObject getDetailsOfQuery(String query, String typeOfQuery) {
		/* 2322 */ JSONObject queryDetails = new JSONObject();
		 try {
			/* 2324 */ query = query.trim();
			/* 2325 */ query = query.replaceAll("\\s+", " ");
			/* 2326 */ String operationName = "";
			/* 2327 */ String tableName = "";
			/* 2328 */ String pojoName = "";
			/* 2329 */ String pojoClassName = "";
			/* 2330 */ String columnsString = "";
			/* 2331 */ String conditionString = "";
			/* 2332 */ String selectQuery = "";
			/* 2333 */ if (query.startsWith("UPDATE") || query.startsWith("update")) {
				/* 2334 */ operationName = "UPDATE";
				/* 2335 */ } else if (query.startsWith("DELETE") || query.startsWith("delete")) {
				/* 2336 */ operationName = "DELETE";
				/* 2337 */ } else if (query.startsWith("INSERT") || query.startsWith("insert")) {
				/* 2338 */ operationName = "INSERT";
				/* 2339 */ } else if (query.startsWith("SELECT") || query.startsWith("select")) {
				/* 2340 */ operationName = "SELECT";
				 }
			
			
			
			/* 2345 */ if (typeOfQuery != null && !"".equalsIgnoreCase(typeOfQuery)
					&& typeOfQuery.equalsIgnoreCase("SQL")) {
				/* 2346 */ query = query.toUpperCase().trim();
				/* 2347 */ if (operationName != null && !"".equalsIgnoreCase(operationName) && (
				/* 2348 */ "DELETE".equalsIgnoreCase(operationName) || "SELECT".equalsIgnoreCase(operationName))) {
					
					/* 2350 */ conditionString = query.substring(query.indexOf("WHERE") + 5);
					/* 2351 */ tableName = query.substring(query.indexOf("FROM") + 4, query.indexOf("WHERE"));
					/* 2352 */ selectQuery = " select * from " + tableName + " where " + conditionString;
					
					
					 }
				/* 2356 */ else if (operationName != null && !"".equalsIgnoreCase(operationName) &&
				/* 2357 */ "UPDATE".equalsIgnoreCase(operationName)) {
					/* 2358 */ if (query.contains(")=(") || query.contains(") = (")) {
						/* 2359 */ tableName = query.substring(query.indexOf("UPDATE") + 6, query.indexOf("SET"));
						/* 2360 */ selectQuery = query.substring(query.indexOf("=") + 1);
						/* 2361 */ selectQuery = selectQuery.substring(selectQuery.indexOf("(") + 1,
								selectQuery.indexOf(")"));
						/* 2362 */ columnsString = query.substring(query.indexOf("SET") + 3, query.indexOf(")"));
						/* 2363 */ conditionString = selectQuery.substring(selectQuery.indexOf("WHERE") + 5);
						 }
					 else {
						
						/* 2367 */ columnsString = query.substring(query.indexOf("SET") + 3, query.indexOf("WHERE"));
						/* 2368 */ conditionString = query.substring(query.indexOf("WHERE") + 5);
						/* 2369 */ tableName = query.substring(query.indexOf("UPDATE") + 6, query.indexOf("SET"));
						
						/* 2371 */ selectQuery = " select * from " + tableName + " where " + conditionString;
						
						
						 }
					
					
					 }
				/* 2378 */ else if (operationName != null && !"".equalsIgnoreCase(operationName) &&
				/* 2379 */ "INSERT".equalsIgnoreCase(operationName)) {
					
					/* 2382 */ tableName = query.substring(query.indexOf("INTO") + 4, query.indexOf("("));
					/* 2383 */ conditionString = query.substring(query.indexOf("WHERE") + 5);
					
					
					
					
					
					/* 2389 */ columnsString = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
					/* 2390 */ selectQuery = query.substring(query.indexOf(")") + 1);
					
					
					
					
					 }
				
				
				
				
				 }
			/* 2401 */ else if (typeOfQuery != null && !"".equalsIgnoreCase(typeOfQuery) &&
			/* 2402 */ typeOfQuery.equalsIgnoreCase("HQL")) {
				/* 2403 */ if (operationName != null && !"".equalsIgnoreCase(operationName) &&
				/* 2404 */ "DELETE".equalsIgnoreCase(operationName)) {
					/* 2405 */ String setQuery = "";
					
					/* 2408 */ if (query.contains("from") || query.contains("FROM")) {
						/* 2409 */ pojoName = query.substring(query.indexOf("from") + 4, query.indexOf("where"));
						 }
					/* 2411 */ else if (query.contains("FROM")) {
						/* 2412 */ pojoName = query.substring(query.indexOf("FROM") + 4, query.indexOf("WHERE"));
						/* 2413 */ } else if (query.trim().startsWith("delete")) {
						/* 2414 */ pojoName = query.substring(query.indexOf("delete") + 6, query.indexOf("where"));
						/* 2415 */ } else if (query.trim().startsWith("DELETE")) {
						/* 2416 */ pojoName = query.substring(query.indexOf("DELETE") + 6, query.indexOf("WHERE"));
						 }
					
					/* 2419 */ if (pojoName != null && !"".equalsIgnoreCase(pojoName)) {
						/* 2420 */ pojoName = pojoName.trim();
						 }
					/* 2422 */ conditionString = "";
					/* 2423 */ if (query.contains("WHERE")) {
						/* 2424 */ conditionString = query.substring(query.indexOf("WHERE") + 5);
						
						 }
					/* 2427 */ else if (query.contains("where")) {
						/* 2428 */ conditionString = query.substring(query.indexOf("where") + 5);
						 }
					
					
					/* 2432 */ selectQuery = "  from " + pojoName + " where " + conditionString;
					/* 2433 */ tableName = getTableByPojo(pojoName);
					
					
					 }
				/* 2437 */ else if (operationName != null && !"".equalsIgnoreCase(operationName) &&
				/* 2438 */ "UPDATE".equalsIgnoreCase(operationName)) {
					/* 2439 */ String setQuery = "";
					/* 2440 */ String setQuery1 = "";
					/* 2441 */ if (query.contains("set")) {
						
						/* 2443 */ setQuery = query.substring(query.indexOf("set") + 3);
						/* 2444 */ setQuery1 = query.substring(0, query.indexOf("set"));
						 } else {
						/* 2446 */ setQuery = query.substring(query.indexOf("SET") + 3);
						/* 2447 */ setQuery1 = query.substring(0, query.indexOf("SET"));
						 }
					
					/* 2450 */ if (query.contains("WHERE")) {
						/* 2451 */ columnsString = setQuery.substring(0, setQuery.indexOf("WHERE"));
						/* 2452 */ conditionString = setQuery.substring(setQuery.indexOf("WHERE") + 5);
						 } else {
						/* 2454 */ columnsString = setQuery.substring(0, setQuery.indexOf("where"));
						/* 2455 */ conditionString = setQuery.substring(setQuery.indexOf("where") + 5);
						 }
					/* 2457 */ if (query.contains("UPDATE")) {
						/* 2458 */ pojoName = setQuery1.substring(setQuery1.indexOf("UPDATE") + 6);
						 } else {
						/* 2460 */ pojoName = setQuery1.substring(setQuery1.indexOf("update") + 6);
						 }
					
					/* 2463 */ if (pojoName != null && !"".equalsIgnoreCase(pojoName)) {
						/* 2464 */ pojoName = pojoName.trim();
						 }
					/* 2466 */ tableName = getTableByPojo(pojoName.trim());
					 }
				 }
			
			
			
			
			/* 2473 */ if (tableName != null && !"".equalsIgnoreCase(tableName)) {
				/* 2474 */ tableName = tableName.replaceAll("\\s+", "").trim();
				/* 2475 */ tableName = tableName.replaceAll("\\r|\\n", "").trim();
				 }
			
			/* 2478 */ queryDetails.put("operationName", operationName);
			/* 2479 */ queryDetails.put("pojoName", pojoName);
			/* 2480 */ queryDetails.put("tableName", tableName);
			/* 2481 */ queryDetails.put("conditionString", conditionString);
			/* 2482 */ queryDetails.put("columnsString", columnsString);
			/* 2483 */ queryDetails.put("typeOfQuery", typeOfQuery);
			/* 2484 */ queryDetails.put("selectQuery", selectQuery.trim());
			 }
		/* 2486 */ catch (Exception e) {
			/* 2487 */ e.printStackTrace();
			 }
		/* 2489 */ return queryDetails;
		 }

	
	 public String getTableByPojo(String pojoname) throws Exception {
		/* 2493 */ String tablename = "";
		
		/* 2495 */ pojoname = pojoname.trim();
		/* 2496 */ Class<?> clazz = Class.forName(getFullQualifiedNameOfPOJO(pojoname.trim()));
		
		/* 2498 */ Table table = clazz.<Table>getAnnotation(Table.class);
		/* 2499 */ tablename = table.name();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/* 2522 */ return tablename.toUpperCase();
		 }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 public String getTableByView(String viewName) throws Exception {
		/* 2556 */ String tableName = "";
		 try {
			/* 2558 */ String query = " SELECT  NAME, TYPE, REFERENCED_NAME, REFERENCED_TYPE  FROM DBA_DEPENDENCIES WHERE NAME =:NAME AND TYPE =:TYPE  AND REFERENCED_TYPE =:REFERENCED_TYPE";
			
			
			
			
			
			/* 2564 */ Map<String, Object> map = new HashMap<String, Object>();
			/* 2565 */ map.put("NAME", viewName);
			/* 2566 */ map.put("TYPE", "VIEW");
			/* 2567 */ map.put("REFERENCED_TYPE", "TABLE");
			
			
			/* 2570 */ List<Object[]> list = sqlqueryWithParams(query, map);
			/* 2571 */ if (list != null && !list.isEmpty() && list.size() == 1) {
				/* 2572 */ Object[] objects = list.get(0);
				/* 2573 */ tableName = (objects[2] != null) ? (String) objects[2] : "";
				 }
			
			 }
		/* 2577 */ catch (Exception e) {
			/* 2578 */ e.printStackTrace();
			 }
		/* 2580 */ return tableName;
		 }

	
	 public List getAuditTables(String orgnId) {
		/* 2584 */ List tableList = new ArrayList();
		
		 try {
			/* 2587 */ String query = " select distinct tableName from CAuditRecord where id.orgnId =:orgnId";
			/* 2588 */ Map<String, Object> map = new HashMap<String, Object>();
			/* 2589 */ map.put("orgnId", orgnId);
			/* 2590 */ tableList = queryWithParams(query, map);
			/* 2591 */ System.err.println("tableList:::" + tableList);
			/* 2592 */ } catch (Exception exception) {
		}
		
		/* 2594 */ return tableList;
		 }

	
	 public String stripValue(String value) throws Exception {
		/* 2598 */ if (value != null && !"".equalsIgnoreCase(value)) {
			/* 2599 */ if (value.startsWith("'")) {
				/* 2600 */ value = value.substring(1);
				 }
			/* 2602 */ System.err.println("::2197::::value:::::" + value);
			/* 2603 */ if (value.endsWith("'")) {
				/* 2604 */ value = value.substring(0, value.length() - 1);
				 }
			/* 2606 */ System.err.println(":::2201:::value:::::" + value);
			 }
		/* 2608 */ return value;
		 }

	
	 public String[] splitAnd(String value) {
		/* 2612 */ String[] valueArray = null;
		 try {
			/* 2614 */ if (value != null && !"".equalsIgnoreCase(value)) {
				/* 2615 */ if (value.contains(" and ")) {
					/* 2616 */ valueArray = value.split(" and ");
					/* 2617 */ } else if (value.contains(" AND ")) {
					/* 2618 */ valueArray = value.split(" AND ");
					 } else {
					/* 2620 */ valueArray = new String[1];
					/* 2621 */ valueArray[0] = value;
					 }
				 }
			/* 2624 */ } catch (Exception e) {
			/* 2625 */ e.printStackTrace();
			 }
		/* 2627 */ return valueArray;
		 }

	
	 public void evict2ndLevelCache(String pojoName) {
		 try {
			/* 2632 */ Map<String, ClassMetadata> classesMetadata = this.sessionFactory.getAllClassMetadata();
			/* 2633 */ for (String entityName : classesMetadata.keySet()) {
				
				
				/* 2636 */ if (entityName != null && !"".equalsIgnoreCase(entityName)
						&& entityName.contains(pojoName)) {
					
					/* 2638 */ logger.info("Evicting Entity from 2nd level cache: " + entityName);
					
					 break;
					 }
				 }
			/* 2643 */ } catch (Exception e) {
			/* 2644 */ e.printStackTrace();
			 }
		 }

	
	
	
	
	 public String getFullQualifiedNameOfPOJO(String pojoName) throws Exception {
		 try {
			/* 2653 */ Map<String, ClassMetadata> classesMetadata = this.sessionFactory.getAllClassMetadata();
			/* 2654 */ for (String entityName : classesMetadata.keySet()) {
				
				
				/* 2657 */ if (entityName != null && !"".equalsIgnoreCase(entityName)
						&& entityName.endsWith(pojoName)) {
					/* 2658 */ pojoName = entityName.trim();
					 break;
					 }
				 }
			/* 2662 */ } catch (Exception e) {
			/* 2663 */ e.printStackTrace();
			 }
		
		
		
		/* 2668 */ return pojoName;
		 }

	
	 private void removeEnityFromSession(Object entity) {
		 try {
			/* 2673 */ Session session = getCurrentSession();
			/* 2674 */ if (entity != null)
			 {
				
				/* 2677 */ session.evict(entity);
				 }
			/* 2679 */ } catch (Exception exception) {
		}
		 }

	
	
	 public boolean isEntityProperty(String colname, String tableName) {
		/* 2684 */ JSONObject jsEntityobject = null;
		
		/* 2686 */ AbstractEntityPersister as = null;
		
		
		/* 2689 */ String propertyname = "";
		/* 2690 */ boolean entityType = false;
		 try {
			/* 2692 */ List identifierPropertyList = new ArrayList();
			/* 2693 */ jsEntityobject = getEntityByTable(tableName);
			/* 2694 */ String entity = (String) jsEntityobject.get("entityname");
			/* 2695 */ Class<?> classname = Class.forName(getFullQualifiedNameOfPOJO(entity.trim()));
			/* 2696 */ ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
			/* 2697 */ String[] propertyNames = classMetadata.getPropertyNames();
			/* 2698 */ as = (AbstractEntityPersister) classMetadata;
			
			/* 2700 */ Type type = as.getPropertyType(as.getIdentifierPropertyName());
			
			/* 2702 */ if (type.isComponentType()) {
				/* 2703 */ entityType = true;
				 } else {
				/* 2705 */ entityType = false;
				 }
			/* 2707 */ } catch (Exception e) {
			/* 2708 */ e.printStackTrace();
			 }
		/* 2710 */ return entityType;
		 }

	
	 public Object getPersEntityObj(String tableName, JSONArray jsColVals) {
		/* 2714 */ Session session = getCurrentSession();
		/* 2715 */ String query = "select * from " + tableName;
		
		/* 2717 */ Object returnObj = null;
		 try {
			/* 2719 */ for (int i = 0; i < jsColVals.size(); i++) {
				/* 2720 */ JSONObject jsColValObj = (JSONObject) jsColVals.get(i);
				/* 2721 */ if (i == 0) {
					/* 2722 */ query = String.valueOf(String.valueOf(query)) + " where " + jsColValObj.get("colName")
							+ " = '" + jsColValObj.get("colVal") + "'";
					 } else {
					/* 2724 */ query = String.valueOf(String.valueOf(query)) + " and " + jsColValObj.get("colName")
							+ " = '" + jsColValObj.get("colVal") + "'";
					 }
				 }
			
			/* 2728 */ JSONObject entityObj = getEntityByTable(tableName);
			
			/* 2730 */ String entityClass = (String) entityObj.get("entityname");
			/* 2731 */ List list = session.createSQLQuery(query).addEntity(entityClass).list();
			/* 2732 */ if (!list.isEmpty()) {
				/* 2733 */ returnObj = list.get(0);
				 }
			/* 2735 */ } catch (Exception e) {
			/* 2736 */ e.printStackTrace();
			 }
		/* 2738 */ return returnObj;
		 }

	public Connection getConnection() {

		Connection conn = null;
		try {
			conn = this.sessionFactory.getSessionFactoryOptions().getServiceRegistry()
					.getService(ConnectionProvider.class).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	

	public String getPropertyValue(String propKey) {

		String proPValue = "";

		if (propKey != null && !"".contentEquals(propKey))
			 proPValue=proPValue;
			//proPValue = configUtil.getProperty(propKey);
		else
			return proPValue;

		if (proPValue == null)
			proPValue = "";

		return proPValue;
	}

}

/*
 * Location:
 * E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\
 * telugucineandtvoutdoorunittechniciansunion\init\DataAccess.class Java
 * compiler version: 6 (50.0) JD-Core Version: 1.1.3
 */