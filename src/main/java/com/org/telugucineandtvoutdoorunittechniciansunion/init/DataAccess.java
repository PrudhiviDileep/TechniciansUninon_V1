
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
// import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.config.AppConfigurations;

@Repository
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DataAccess {
	private static Logger logger = Logger.getLogger(DataAccess.class);
	public static String ERROR_MESSAGE_WHILE_EXCECUTING = "Error while executing save/update";
	public static String ERROR_ON_SELECT_QUERY = "Error on select query";
	public static String UNABLE_TO_SAVE_ENTITY = "Unable to save entity";
	public static String ERROR_IN_QUERY="error in query() , ";
	public static String UNABLE_TO_UPDATE="Unable to update entity";
	public static String PARAMS=" : params - ";
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public AppConfigurations appConfigurations;

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public DataAccess(LocalSessionFactoryBean localSessionFactoryBean) {
		this.sessionFactory = (SessionFactory) localSessionFactoryBean;
	}

	public DataAccess() {
	}

	public int executeUpdateSQL(String query) throws Exception {
		int result = 0;
		try {
			Session session = getCurrentSession();
			SQLQuery sQLQuery = session.createSQLQuery(query);

			result = sQLQuery.executeUpdate();
		} catch (Exception e) {
			logger.error(ERROR_MESSAGE_WHILE_EXCECUTING, e);
			throw new Exception(e);
		}
		return result;
	}

	public int executeUpdateSQL(String query, Map<String, Object> parameters) throws Exception {
		int result = 0;

		try {
			Session session = getCurrentSession();
			SQLQuery sQLQuery = session.createSQLQuery(query);
			sQLQuery.setProperties(parameters);
			result = sQLQuery.executeUpdate();

		} catch (Exception e) {
			logger.error(ERROR_MESSAGE_WHILE_EXCECUTING, e);
			throw new Exception(e);
		}
		return result;
	}

	public int executeUpdateSQL(String query, Map<String, Object> parameters, Object entity) throws Exception {
		int result = 0;

		try {
			Session session = getCurrentSession();
			SQLQuery sQLQuery = session.createSQLQuery(query);
			sQLQuery.setProperties(parameters);
			result = sQLQuery.executeUpdate();

		} catch (Exception e) {
			logger.error(ERROR_MESSAGE_WHILE_EXCECUTING, e);
			throw new Exception(e);
		}
		return result;
	}

	public List queryWithParamsWithLimit(String query, Map<String, Object> parameters, int limit, int start)
			throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);
		Session session = getCurrentSession();
		List list = null;
		try {
			Query queryObj = session.createQuery(query).setMaxResults(limit).setFirstResult(start);

			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					queryObj.setParameter(param, parameters.get(param));
				}
			}

			list = queryObj.list();
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public int queryCount(String query, Map<String, Object> parameters) throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);
		Session session = getCurrentSession();
		int count = 0;
		try {
			Query queryObj = session.createQuery(query);

			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					queryObj.setParameter(param, parameters.get(param));
				}
			}

			count = ((Long) queryObj.uniqueResult()).intValue();
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return count;
	}

	@Transactional
	public List queryWithParams(String query, Map<String, Object> parameters) throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);

		Session session = getCurrentSession();

		List list = null;
		try {
			Query queryObj = session.createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					queryObj.setParameter(param, parameters.get(param));
				}
			}

			list = queryObj.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public List sqlqueryWithParams(String query, Map<String, Object> parameters) throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);
		Session session = getCurrentSession();
		List list = null;
		try {
			SQLQuery sQLQuery = session.createSQLQuery(query);

			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						sQLQuery.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					sQLQuery.setParameter(param, parameters.get(param));
				}
			}

			list = sQLQuery.list();
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public List sqlqueryWithParamsLimit(String query, Map<String, Object> parameters, int limit, int start)
			throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);
		Session session = getCurrentSession();
		List list = null;
		try {
			SQLQuery sQLQuery = null;
			if (limit != 0) {
				sQLQuery = (SQLQuery) session.createSQLQuery(query).setMaxResults(limit).setFirstResult(start);
			} else {
				sQLQuery = session.createSQLQuery(query);
			}

			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						sQLQuery.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					sQLQuery.setParameter(param, parameters.get(param));
				}
			}

			list = sQLQuery.list();
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public void updateQuery(String queryString, Map<String, Object> map) throws Exception {
		Session session = getCurrentSession();

		try {
			Query query = session.createQuery(queryString);
			query.setProperties(map);
			query.executeUpdate();

		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
	}

	public int updateQueryByCount(String queryString, Map<String, Object> parameters) throws Exception {
		Session session = getCurrentSession();
		int result = 0;

		try {
			Query queryObj = session.createQuery(queryString);
			queryObj.setProperties(parameters);
			result = queryObj.executeUpdate();

		}

		catch (DataIntegrityViolationException die) {
			result = -1;
			logger.error(ERROR_IN_QUERY, (Throwable) die);
		} catch (ConstraintViolationException e) {
			logger.error("error in query()-2 , ", (Throwable) e);
			result = -2;
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return result;
	}

	public int updateQueryReturn(String queryString, Map<String, Object> map) throws Exception {
		int result = 0;
		Session session = getCurrentSession();

		try {
			Query query = session.createQuery(queryString);
			query.setProperties(map);
			result = query.executeUpdate();

		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return result;
	}

	public <T> T save(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.save(t);
		}

		catch (DataIntegrityViolationException die) {
			logger.error(UNABLE_TO_SAVE_ENTITY, (Throwable) die);
			die.printStackTrace();
			throw new Exception(die);
		}

		catch (ConstraintViolationException cve) {
			logger.error(UNABLE_TO_SAVE_ENTITY, (Throwable) cve);
			cve.printStackTrace();
			throw new Exception(cve);
		} catch (Exception e) {
			logger.error(UNABLE_TO_SAVE_ENTITY, e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> T saveObj(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.save(t);
		}

		catch (DataIntegrityViolationException die) {
			logger.error(UNABLE_TO_SAVE_ENTITY, (Throwable) die);
			die.printStackTrace();
			throw new Exception(die);
		}

		catch (ConstraintViolationException cve) {
			logger.error(UNABLE_TO_SAVE_ENTITY, (Throwable) cve);
			cve.printStackTrace();
			throw new Exception(cve);
		} catch (Exception e) {
			logger.error(UNABLE_TO_SAVE_ENTITY, e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> T persist(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.persist(t);
		} catch (Exception e) {
			logger.error(UNABLE_TO_SAVE_ENTITY, e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> List<T> save(List<T> list) throws Exception {
		try {
			Session session = getCurrentSession();
			for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
				T entity = iterator.next();
				session.save(entity);
			}

		} catch (Exception e) {
			logger.error(UNABLE_TO_SAVE_ENTITY, e);
			throw new Exception(e);
		}
		return list;
	}

	public List<Object> updateList(List<Object> list) throws Exception {
		try {
			Session session = getCurrentSession();
			for (Object entity : list) {
				session.update(entity);

			}
		} catch (Exception e) {
			logger.error("Unable to update list of entities entity", e);
			throw new Exception(e);
		}
		return list;
	}

	public <T> T saveOrUpdate(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.saveOrUpdate(t);
		} catch (Exception e) {
			logger.error("Unable to save/update entity", e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> T update(T t) throws Exception {
		try {
			if (t instanceof List) {
				List<Object> list = (List) t;
				updateList(list);
			} else {
				Session session = getCurrentSession();

				session.update(t);

			}

		} catch (Exception e) {
			logger.error("Unable to update entity", e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> T updateObj(T t) throws Exception {
		try {
			if (t instanceof List) {
				List<Object> list = (List) t;
				updateList(list);
			} else {
				Session session = getCurrentSession();

				session.update(t);

			}

		} catch (Exception e) {
			logger.error("Unable to update entity", e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> T merge(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.merge(t);
		} catch (Exception e) {
			logger.error("Unable to save/update entity", e);
			throw new Exception(e);
		}
		return t;
	}

	public <T> void delete(T t) throws Exception {
		try {
			Session session = getCurrentSession();
			session.delete(t);
		} catch (Exception e) {
			logger.error("Unable to save/update entity", e);
			throw new Exception(e);
		}
	}

	public Object loadById(Class t, String identifier) throws Exception {
		Object obj;
		Session session = getCurrentSession();

		try {
			obj = session.get(t, identifier);
		} catch (Exception e) {
			logger.error("Error while getting object by id", e);
			throw new Exception(e);
		}

		return obj;
	}

	public Object loadById(Class t, BigDecimal identifier) throws Exception {
		Object obj;
		Session session = getCurrentSession();

		try {
			obj = session.get(t, identifier);
		} catch (Exception e) {
			logger.error("Error while getting object by id", e);
			throw new Exception(e);
		}

		return obj;
	}

	public Object loadById(Class t, int identifier) throws Exception {
		Object obj;
		Session session = getCurrentSession();

		try {
			obj = session.get(t, Integer.valueOf(identifier));
		} catch (Exception e) {
			logger.error("Error while getting object by id", e);
			throw new Exception(e);
		}
		return obj;
	}

	public Object loadById(Class t, Long identifier) throws Exception {
		Object obj;
		Session session = getCurrentSession();

		try {
			obj = session.get(t, identifier);
		} catch (Exception e) {
			logger.error("Error while getting object by id", e);
			throw new Exception(e);
		}

		return obj;
	}

	public List executeNativeSQL(String query, Map<Integer, Object> parameters) throws Exception {
		List list = new ArrayList();
		try {
			Session session = getCurrentSession();
			SQLQuery sQLQuery = session.createSQLQuery(query);
			if (parameters != null) {
				for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					sQLQuery.setParameter(param, parameters.get(Integer.valueOf(param)));
				}

			}
			list = sQLQuery.list();
		} catch (Exception e) {
			logger.error(ERROR_ON_SELECT_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public void executeNativeUpdateSQLWithSimpleParams(String query, Map<Integer, Object> parameters) throws Exception {
		try {
			Session session = getCurrentSession();

			SQLQuery sQLQuery = session.createSQLQuery(query.toString());
			if (parameters != null) {
				for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					sQLQuery.setParameter(param, parameters.get(Integer.valueOf(param)));
				}

			}
			sQLQuery.executeUpdate();
		} catch (Exception e) {
			logger.error("Error on update / Save", e);
			throw new Exception(e);
		}
	}

	public List executeNativeSQLWithEntity(String query, Map<Integer, Object> parameters, Class t) throws Exception {
		List list = new ArrayList();
		try {
			Session session = getCurrentSession();

			SQLQuery q = session.createSQLQuery(query);
			q.addEntity(t);
			if (parameters != null) {
				for (Iterator<Integer> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					int param = ((Integer) iterator.next()).intValue();
					q.setParameter(param, parameters.get(Integer.valueOf(param)));
				}

			}
			list = q.list();
		} catch (Exception e) {
			logger.error(ERROR_ON_SELECT_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public List executeSQLWithEntity(String query, Map<String, Object> parameters, Class t) throws Exception {
		List list = new ArrayList();
		try {
			Session session = getCurrentSession();

			SQLQuery q = session.createSQLQuery(query);
			q.addEntity(t);
			if (parameters != null) {
				q.setProperties(parameters);
			}

			list = q.list();
		} catch (Exception e) {
			logger.error(ERROR_ON_SELECT_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public List queryWithMaxLimit(String query, Map<String, Object> parameters, int limit) throws Exception {
		logger.debug(String.valueOf(String.valueOf(query)) + PARAMS + parameters);
		Session session = getCurrentSession();
		List list = null;
		try {
			Query queryObj = session.createQuery(query).setMaxResults(limit);

			if (parameters != null && !parameters.isEmpty()) {
				for (String param : parameters.keySet()) {
					if (parameters.get(param) instanceof Collection) {
						queryObj.setParameterList(param, (Collection) parameters.get(param));
						continue;
					}
					queryObj.setParameter(param, parameters.get(param));
				}
			}

			list = queryObj.list();
		} catch (Exception e) {
			logger.error(ERROR_IN_QUERY, e);
			throw new Exception(e);
		}
		return list;
	}

	public Object get(Class<?> clazz, Serializable identifier) throws Exception {
		Object object = null;
		Session session = getCurrentSession();
		try {
			object = session.get(clazz, identifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public String[] getPropertiesByClass(Class classname) {
		ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);
		return classMetadata.getPropertyNames();
	}

	public JSONObject getColumnByPropertyName(String propertyName, Class pojoClass) {
		JSONObject columnObject = new JSONObject();

		try {
			JSONArray propertyArray = getColsByPropertiesNClass(pojoClass);
			if (propertyArray != null && !propertyArray.isEmpty()) {
				for (int i = 0; i < propertyArray.size(); i++) {
					JSONObject propertyObject = (JSONObject) propertyArray.get(i);
					if (propertyObject != null && !propertyObject.isEmpty()) {
						String pojoProperty = (String) propertyObject.get("property");
						if (pojoProperty != null && !"".equalsIgnoreCase(pojoProperty) && propertyName != null
								&& propertyName.equalsIgnoreCase(pojoProperty)) {

							columnObject.put(pojoProperty, propertyObject.get("columnname"));

							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return columnObject;
	}

	public JSONObject getEntityByTable(String TableName) {
		JSONArray jsArray = new JSONArray();
		JSONObject jsObject = null;
		String tableName = "";

		try {
			Map<String, ClassMetadata> map = this.sessionFactory.getAllClassMetadata();
			for (String entityName : map.keySet()) {
				SessionFactoryImpl sfImpl = (SessionFactoryImpl) this.sessionFactory;
				tableName = ((AbstractEntityPersister) sfImpl.getEntityPersister(entityName)).getTableName();

				if (tableName != null && tableName.contains(".")) {
					tableName = tableName.substring(tableName.lastIndexOf(".") + 1, tableName.length());
				}

				TableName = TableName.trim();
				tableName = tableName.trim();
				if (tableName.equalsIgnoreCase(TableName)) {
					jsObject = new JSONObject();
					jsObject.put("entityname", entityName);
					jsObject.put("tablename", tableName);
					jsArray.add(jsObject);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsObject;
	}

	public JSONArray getColsByPropertiesNClass(Class classname) {
		ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);

		String[] propertyNames = classMetadata.getPropertyNames();
		AbstractEntityPersister as = null;
		JSONArray jsArray = null;
		JSONObject jsObject = null;
		try {
			List<String> identifierPropertyList = new ArrayList<>();
			jsArray = new JSONArray();
			as = (AbstractEntityPersister) classMetadata;
			Type type = as.getPropertyType(as.getIdentifierPropertyName());
			ComponentType cType = null;
			if (type.isComponentType()) {
				cType = (ComponentType) type;
				identifierPropertyList = Arrays.asList(cType.getPropertyNames());

			} else if (type.getName() == "string") {

				identifierPropertyList.add(as.getIdentifierPropertyName());
			}

			List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());

			if (identifierColsList != null && !identifierColsList.isEmpty() && identifierPropertyList != null
					&& identifierPropertyList.size() == identifierColsList.size() && identifierColsList.size() != 0) {
				for (int j = 0; j < identifierColsList.size(); j++) {

					jsObject = new JSONObject();
					jsObject.put("property", identifierPropertyList.get(j));
					jsObject.put("columnname", identifierColsList.get(j));
					jsArray.add(jsObject);
				}
			}

			for (int i = 0; i < propertyNames.length; i++) {
				jsObject = new JSONObject();

				jsObject.put("property", propertyNames[i]);
				jsObject.put("columnname", as.getPropertyColumnNames(propertyNames[i])[0]);
				jsArray.add(jsObject);

			}

		} catch (Exception exception) {
		}

		return jsArray;
	}

	public JSONObject getAllColsByPropertiesNClass(Class classname) {
		ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);

		String[] propertyNames = classMetadata.getPropertyNames();

		AbstractEntityPersister as = null;
		JSONObject POJOObject = new JSONObject();

		JSONObject jsObject = new JSONObject();

		try {
			as = (AbstractEntityPersister) classMetadata;

			List<String> list = Arrays.asList(propertyNames);
			List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());
			List<String> identifierPropertyList = new ArrayList<>();

			Type type = as.getPropertyType(as.getIdentifierPropertyName());
			ComponentType cType = null;
			if (type.isComponentType()) {
				cType = (ComponentType) type;
				identifierPropertyList = Arrays.asList(cType.getPropertyNames());

			} else if (type.getName() == "string") {

				identifierPropertyList.add(as.getIdentifierPropertyName());
			}

			if (identifierColsList.size() == identifierPropertyList.size() && identifierPropertyList.size() != 0) {
				JSONObject idJSONObject = new JSONObject();
				for (int j = 0; j < identifierColsList.size(); j++) {

					Object propertyName = identifierPropertyList.get(j);
					Object columnName = identifierColsList.get(j);

					idJSONObject.put(String.valueOf(columnName).toUpperCase(), propertyName);
				}
				POJOObject.put("id", idJSONObject);
			}

			for (int i = 0; i < list.size(); i++) {

				String[] allColumnNames = as.getPropertyColumnNames(list.get(i));
				if (!identifierColsList.contains(allColumnNames[0])) {
					jsObject.put(as.getPropertyColumnNames((String) list.get(i))[0].toUpperCase(), list.get(i));
				}
			}

			POJOObject.put("nonId", jsObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return POJOObject;
	}

	public JSONObject getAllColsByProperties(Class classname) {
		ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);

		String[] propertyNames = classMetadata.getPropertyNames();

		AbstractEntityPersister as = null;
		JSONObject POJOObject = new JSONObject();

		JSONObject jsObject = new JSONObject();

		try {
			as = (AbstractEntityPersister) classMetadata;

			List<String> list = Arrays.asList(propertyNames);
			List<String> identifierColsList = Arrays.asList(as.getIdentifierColumnNames());
			List<String> identifierPropertyList = new ArrayList<>();

			Type type = as.getPropertyType(as.getIdentifierPropertyName());
			ComponentType cType = null;
			if (type.isComponentType()) {
				cType = (ComponentType) type;
				identifierPropertyList = Arrays.asList(cType.getPropertyNames());

			} else if (type.getName() == "string") {

				identifierPropertyList.add(as.getIdentifierPropertyName());
			}

			if (identifierColsList.size() == identifierPropertyList.size() && identifierPropertyList.size() != 0) {
				JSONObject idJSONObject = new JSONObject();
				for (int j = 0; j < identifierColsList.size(); j++) {

					Object propertyName = identifierPropertyList.get(j);
					Object columnName = identifierColsList.get(j);

					idJSONObject.put(propertyName, columnName);
				}

				POJOObject.put("id", idJSONObject);
			}

			for (int i = 0; i < list.size(); i++) {

				String[] allColumnNames = as.getPropertyColumnNames(list.get(i));
				if (!identifierColsList.contains(allColumnNames[0])) {
					jsObject.put(list.get(i), as.getPropertyColumnNames((String) list.get(i))[0]);
				}
			}

			POJOObject.put("nonId", jsObject);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return POJOObject;
	}

	public String getColByPropertyNClass(Class classname, String propertyname) {
		ClassMetadata classMetadata = null;
		AbstractEntityPersister as = null;
		String columnname = "";
		try {
			classMetadata = this.sessionFactory.getClassMetadata(classname);
			as = (AbstractEntityPersister) classMetadata;
			columnname = as.getPropertyColumnNames(propertyname)[0];
		} catch (Exception exception) {
		}

		return columnname;
	}

	public JSONArray createjsonObjByjavaObj(Class classname, Object classobj) throws Exception {
		JSONObject jSONObject = null;
		JSONObject jsProperty = null;
		String className = "com.pilog.mdm.pojo.ORecordText";
		Class<?> c = Class.forName(getFullQualifiedNameOfPOJO(className.trim()));
		JSONArray jsResultsArray = new JSONArray();

		try {
			Method method = c.getDeclaredMethod("method name", new Class[0]);
			method.invoke(classobj, new Object[0]);

			Method[] methods = classname.getMethods();

			JSONArray jsArray = getColsByPropertiesNClass(classname);
			for (int i = 0; i < jsArray.size(); i++) {
				jsProperty = (JSONObject) jsArray.get(i);
				jSONObject = new JSONObject();
				jSONObject.put("property", jsProperty.get("property"));

				jSONObject.put("value", methods[i].invoke(classobj, new Object[0]));
				jsResultsArray.add(jSONObject);

			}

		} catch (Exception exception) {
		}

		return jsResultsArray;
	}

	public boolean getIdentifierType(Class classname) {
		boolean object = false;
		try {
			ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);

			classMetadata.getPropertyType(classMetadata.getIdentifierPropertyName());

			if (classMetadata.getIdentifierPropertyName() != null
					&& !"".equalsIgnoreCase(classMetadata.getIdentifierPropertyName())
					&& "id".equalsIgnoreCase(classMetadata.getIdentifierPropertyName())) {
				object = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public String getTableByEntity(Object entity) {
		String tableName = "";
		try {
			Class<?> clazz = entity.getClass();
			Table table = clazz.<Table>getAnnotation(Table.class);
			tableName = table.name();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableName;
	}

	public JSONObject getDetailsOfQuery(String query, String typeOfQuery) {
		JSONObject queryDetails = new JSONObject();
		try {
			query = query.trim();
			query = query.replaceAll("\\s+", " ");
			String operationName = "";
			String tableName = "";
			String pojoName = "";
			String columnsString = "";
			String conditionString = "";
			String selectQuery = "";
			if (query.startsWith("UPDATE") || query.startsWith("update")) {
				operationName = "UPDATE";
			} else if (query.startsWith("DELETE") || query.startsWith("delete")) {
				operationName = "DELETE";
			} else if (query.startsWith("INSERT") || query.startsWith("insert")) {
				operationName = "INSERT";
			} else if (query.startsWith("SELECT") || query.startsWith("select")) {
				operationName = "SELECT";
			}

			if (typeOfQuery != null && !"".equalsIgnoreCase(typeOfQuery) && typeOfQuery.equalsIgnoreCase("SQL")) {
				query = query.toUpperCase().trim();
				if (operationName != null && !"".equalsIgnoreCase(operationName)
						&& ("DELETE".equalsIgnoreCase(operationName) || "SELECT".equalsIgnoreCase(operationName))) {

					conditionString = query.substring(query.indexOf("WHERE") + 5);
					tableName = query.substring(query.indexOf("FROM") + 4, query.indexOf("WHERE"));
					selectQuery = " select * from " + tableName + " where " + conditionString;

				} else if (operationName != null && !"".equalsIgnoreCase(operationName)
						&& "UPDATE".equalsIgnoreCase(operationName)) {
					if (query.contains(")=(") || query.contains(") = (")) {
						tableName = query.substring(query.indexOf("UPDATE") + 6, query.indexOf("SET"));
						selectQuery = query.substring(query.indexOf("=") + 1);
						selectQuery = selectQuery.substring(selectQuery.indexOf("(") + 1, selectQuery.indexOf(")"));
						columnsString = query.substring(query.indexOf("SET") + 3, query.indexOf(")"));
						conditionString = selectQuery.substring(selectQuery.indexOf("WHERE") + 5);
					} else {

						columnsString = query.substring(query.indexOf("SET") + 3, query.indexOf("WHERE"));
						conditionString = query.substring(query.indexOf("WHERE") + 5);
						tableName = query.substring(query.indexOf("UPDATE") + 6, query.indexOf("SET"));

						selectQuery = " select * from " + tableName + " where " + conditionString;

					}

				} else if (operationName != null && !"".equalsIgnoreCase(operationName)
						&& "INSERT".equalsIgnoreCase(operationName)) {

					tableName = query.substring(query.indexOf("INTO") + 4, query.indexOf("("));
					conditionString = query.substring(query.indexOf("WHERE") + 5);

					columnsString = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
					selectQuery = query.substring(query.indexOf(")") + 1);

				}

			} else if (typeOfQuery != null && !"".equalsIgnoreCase(typeOfQuery)
					&& typeOfQuery.equalsIgnoreCase("HQL")) {
				if (operationName != null && !"".equalsIgnoreCase(operationName)
						&& "DELETE".equalsIgnoreCase(operationName)) {

					if (query.contains("from") || query.contains("FROM")) {
						pojoName = query.substring(query.indexOf("from") + 4, query.indexOf("where"));
					} else if (query.contains("FROM")) {
						pojoName = query.substring(query.indexOf("FROM") + 4, query.indexOf("WHERE"));
					} else if (query.trim().startsWith("delete")) {
						pojoName = query.substring(query.indexOf("delete") + 6, query.indexOf("where"));
					} else if (query.trim().startsWith("DELETE")) {
						pojoName = query.substring(query.indexOf("DELETE") + 6, query.indexOf("WHERE"));
					}

					if (pojoName != null && !"".equalsIgnoreCase(pojoName)) {
						pojoName = pojoName.trim();
					}
					conditionString = "";
					if (query.contains("WHERE")) {
						conditionString = query.substring(query.indexOf("WHERE") + 5);

					} else if (query.contains("where")) {
						conditionString = query.substring(query.indexOf("where") + 5);
					}

					selectQuery = "  from " + pojoName + " where " + conditionString;
					tableName = getTableByPojo(pojoName);

				} else if (operationName != null && !"".equalsIgnoreCase(operationName)
						&& "UPDATE".equalsIgnoreCase(operationName)) {
					String setQuery = "";
					String setQuery1 = "";
					if (query.contains("set")) {

						setQuery = query.substring(query.indexOf("set") + 3);
						setQuery1 = query.substring(0, query.indexOf("set"));
					} else {
						setQuery = query.substring(query.indexOf("SET") + 3);
						setQuery1 = query.substring(0, query.indexOf("SET"));
					}

					if (query.contains("WHERE")) {
						columnsString = setQuery.substring(0, setQuery.indexOf("WHERE"));
						conditionString = setQuery.substring(setQuery.indexOf("WHERE") + 5);
					} else {
						columnsString = setQuery.substring(0, setQuery.indexOf("where"));
						conditionString = setQuery.substring(setQuery.indexOf("where") + 5);
					}
					if (query.contains("UPDATE")) {
						pojoName = setQuery1.substring(setQuery1.indexOf("UPDATE") + 6);
					} else {
						pojoName = setQuery1.substring(setQuery1.indexOf("update") + 6);
					}

					if (pojoName != null && !"".equalsIgnoreCase(pojoName)) {
						pojoName = pojoName.trim();
					}
					tableName = getTableByPojo(pojoName.trim());
				}
			}

			if (tableName != null && !"".equalsIgnoreCase(tableName)) {
				tableName = tableName.replaceAll("\\s+", "").trim();
				tableName = tableName.replaceAll("\\r|\\n", "").trim();
			}

			queryDetails.put("operationName", operationName);
			queryDetails.put("pojoName", pojoName);
			queryDetails.put("tableName", tableName);
			queryDetails.put("conditionString", conditionString);
			queryDetails.put("columnsString", columnsString);
			queryDetails.put("typeOfQuery", typeOfQuery);
			queryDetails.put("selectQuery", selectQuery.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryDetails;
	}

	public String getTableByPojo(String pojoname) throws Exception {
		String tablename = "";

		pojoname = pojoname.trim();
		Class<?> clazz = Class.forName(getFullQualifiedNameOfPOJO(pojoname.trim()));

		Table table = clazz.<Table>getAnnotation(Table.class);
		tablename = table.name();

		return tablename.toUpperCase();
	}

	public String getTableByView(String viewName) throws Exception {
		String tableName = "";
		try {
			String query = " SELECT  NAME, TYPE, REFERENCED_NAME, REFERENCED_TYPE  FROM DBA_DEPENDENCIES WHERE NAME =:NAME AND TYPE =:TYPE  AND REFERENCED_TYPE =:REFERENCED_TYPE";

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("NAME", viewName);
			map.put("TYPE", "VIEW");
			map.put("REFERENCED_TYPE", "TABLE");

			List<Object[]> list = sqlqueryWithParams(query, map);
			if (list != null && !list.isEmpty() && list.size() == 1) {
				Object[] objects = list.get(0);
				tableName = (objects[2] != null) ? (String) objects[2] : "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableName;
	}

	public List getAuditTables(String orgnId) {
		List tableList = new ArrayList();

		try {
			String query = " select distinct tableName from CAuditRecord where id.orgnId =:orgnId";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgnId", orgnId);
			tableList = queryWithParams(query, map);
			System.err.println("tableList:::" + tableList);
		} catch (Exception exception) {
		}

		return tableList;
	}

	public String stripValue(String value) throws Exception {
		if (value != null && !"".equalsIgnoreCase(value)) {
			if (value.startsWith("'")) {
				value = value.substring(1);
			}
			System.err.println("::2197::::value:::::" + value);
			if (value.endsWith("'")) {
				value = value.substring(0, value.length() - 1);
			}
			System.err.println(":::2201:::value:::::" + value);
		}
		return value;
	}

	public String[] splitAnd(String value) {
		String[] valueArray = null;
		try {
			if (value != null && !"".equalsIgnoreCase(value)) {
				if (value.contains(" and ")) {
					valueArray = value.split(" and ");
				} else if (value.contains(" AND ")) {
					valueArray = value.split(" AND ");
				} else {
					valueArray = new String[1];
					valueArray[0] = value;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueArray;
	}

	public void evict2ndLevelCache(String pojoName) {
		try {
			Map<String, ClassMetadata> classesMetadata = this.sessionFactory.getAllClassMetadata();
			for (String entityName : classesMetadata.keySet()) {

				if (entityName != null && !"".equalsIgnoreCase(entityName) && entityName.contains(pojoName)) {

					logger.info("Evicting Entity from 2nd level cache: " + entityName);

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFullQualifiedNameOfPOJO(String pojoName) throws Exception {
		try {
			Map<String, ClassMetadata> classesMetadata = this.sessionFactory.getAllClassMetadata();
			for (String entityName : classesMetadata.keySet()) {

				if (entityName != null && !"".equalsIgnoreCase(entityName) && entityName.endsWith(pojoName)) {
					pojoName = entityName.trim();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pojoName;
	}

	public boolean isEntityProperty(String colname, String tableName) {
		JSONObject jsEntityobject = null;

		AbstractEntityPersister as = null;

		boolean entityType = false;
		try {
			jsEntityobject = getEntityByTable(tableName);
			String entity = (String) jsEntityobject.get("entityname");
			Class<?> classname = Class.forName(getFullQualifiedNameOfPOJO(entity.trim()));
			ClassMetadata classMetadata = this.sessionFactory.getClassMetadata(classname);

			as = (AbstractEntityPersister) classMetadata;

			Type type = as.getPropertyType(as.getIdentifierPropertyName());

			if (type.isComponentType()) {
				entityType = true;
			} else {
				entityType = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityType;
	}

	public Object getPersEntityObj(String tableName, JSONArray jsColVals) {
		Session session = getCurrentSession();
		String query = "select * from " + tableName;

		Object returnObj = null;
		try {
			for (int i = 0; i < jsColVals.size(); i++) {
				JSONObject jsColValObj = (JSONObject) jsColVals.get(i);
				if (i == 0) {
					query = String.valueOf(String.valueOf(query)) + " where " + jsColValObj.get("colName") + " = '"
							+ jsColValObj.get("colVal") + "'";
				} else {
					query = String.valueOf(String.valueOf(query)) + " and " + jsColValObj.get("colName") + " = '"
							+ jsColValObj.get("colVal") + "'";
				}
			}

			JSONObject entityObj = getEntityByTable(tableName);

			String entityClass = (String) entityObj.get("entityname");
			List list = session.createSQLQuery(query).addEntity(entityClass).list();
			if (!list.isEmpty()) {
				returnObj = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}

	public Connection getConnection() {

		Connection conn = null;
		try {
			conn = this.sessionFactory.getSessionFactoryOptions().getServiceRegistry()
					.getService(ConnectionProvider.class).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
