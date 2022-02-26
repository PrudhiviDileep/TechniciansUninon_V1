package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.jdbc.ReturningWork;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class GenericGridDAO {
	@Autowired(required = true)
	public DataAccess dataAccess;

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	@Autowired
	IdGenerator idGenerator;


	

	@Transactional
	public String getGenericGridConfigData(Map<String, String> reqData) {
		JSONObject gridCompleteObj = new JSONObject();
		JSONArray innerArr = genericGridObject("{CALL GET_GRID_CONFIG_BY_GRID_ID(?)}", reqData.get("GRID_ID"));
		if (innerArr != null && innerArr.size() > 0) {
			gridCompleteObj = (JSONObject) innerArr.get(0);
			JSONArray fieldsObj = (JSONArray) genericGridObject("{CALL GET_GRID_COLUMN_CONFIG_BY_ID(?)}",
					reqData.get("GRID_ID"));
			gridCompleteObj.put("fields", fieldsObj);
		}
		return gridCompleteObj.toJSONString();
	}

	public JSONArray genericGridObject(String procCallStr, String procId) {
		JSONArray jsnArr = new JSONArray();

		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall(procCallStr, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

		) {
			cs.setString(1, procId.toUpperCase());
			boolean hadResults = cs.execute();
			while (hadResults) {

				ResultSet resultSet = cs.getResultSet();
				jsnArr.add(genericGridObjectToJsonArray(resultSet));
				hadResults = cs.getMoreResults();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (jsnArr != null && jsnArr.size() == 1)

			return (JSONArray) jsnArr.get(0);
		else
			return jsnArr;
	}

	public static JSONArray genericGridObjectToJsonArray(ResultSet rs) {
		// CPCT/CPCT2==MASTER BRANCH==TSTING ENVIROMENT
		// TBDUDEV ==DEV BRANCH==DEV ENVIROMENT

		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd;
		try {
			if (rs == null)
				return json;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= numColumns; i++) {
					String column_name = rsmd.getColumnName(i).toUpperCase();
					String data = rs.getObject(column_name) != null ? rs.getObject(column_name).toString() : "";

					if (!"TITLE".equalsIgnoreCase(column_name))
						obj.put(column_name.toLowerCase(), data);
					else	
						obj.put(column_name.toLowerCase(), data.charAt(0) + data.substring(1).toLowerCase());
				}
				json.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
}