package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class GenericCRUDOperationsDAO {

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

	public String doGenericCRUDOpertion(Map<String, String> actionData) {
		try {

			String procId = actionData.get("PROC_ID") != null ? actionData.get("PROC_ID")
					: actionData.get("fetch_proc_id") != null ? actionData.get("fetch_proc_id") : "";
			if ("".contentEquals(procId)) {
				procId = actionData.get("GRID_ID") != null ? actionData.get("GRID_ID")
						: actionData.get("grid_id") != null ? actionData.get("grid_id") : "";

				if ("".contentEquals(procId))
					throw new GenericProcedureCallException("Error : Procid not found!");
			}
			JSONArray procConf = genericFetchtProcCall("{CALL GET_GENERIC_PROC_DETAILS(?)}", procId);

			return getDataByGenericProcObject(procConf, actionData);

		} catch (GenericProcedureCallException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

	public String getDataForGenericExportToExcel(Map<String, String> actionData, String procId)
			throws GenericProcedureCallException {

		if (procId == null || "".equals(procId))
			throw new GenericProcedureCallException("Error : Procid should not be null or empty!");

		JSONArray procConf = genericFetchtProcCall("{CALL GET_GENERIC_PROC_DETAILS(?)}", procId);
		return getDataByGenericProcObject(procConf, actionData);

	}

	public String getDataByGenericProcObject(JSONArray procConf, Map<String, String> actionData)
			throws GenericProcedureCallException {

		if (procConf != null && procConf.size() > 0) {
			JSONArray procConfigObjArr = (JSONArray) procConf.get(0);
			JSONObject procConfigObj = (JSONObject) procConfigObjArr.get(0);
			JSONArray procParmDetailsArr = (JSONArray) procConf.get(1);

			// if (procParmDetailsArr != null && procParmDetailsArr.size() > 0) {
			String procCallStr = generateProcCallStr((String) procConfigObj.get("PROC_NAME"),
					procParmDetailsArr.size());
			return callProcedure(procCallStr, (String) procConfigObj.get("PROC_OPERATION_TYPE"), procParmDetailsArr,
					actionData);
			// }
		}

		else
			throw new GenericProcedureCallException("Error : Procedure Configuraitions Not found! ");

	}

	public String callProcedure(String procCall, String operationType, JSONArray procParamDetails,
			Map<String, String> actionData) throws GenericProcedureCallException {

		String dataStr = "";

		if (procCall == null || "".equals(procCall))
			throw new GenericProcedureCallException("Error : Procedure Name Missied in Configurations! ");
		if (operationType == null || "".equals(operationType))
			throw new GenericProcedureCallException(
					"Error : Procedure Operation Type Missed in Configurations ! Found Empty, Expected F,S,U,D,O");

		switch (operationType.toUpperCase()) {

		case "S":
			dataStr = doGenericCRUDOperation(procCall, procParamDetails, actionData);
			break;

		case "F":
			dataStr = genericFetchtProcCall(procCall, procParamDetails, actionData).toJSONString();
			break;
		case "U":
			dataStr = doGenericCRUDOperation(procCall, procParamDetails, actionData);
			break;
		case "D":
			dataStr = doGenericCRUDOperation(procCall, procParamDetails, actionData);
			break;

		case "O":

			break;

		default:

			throw new GenericProcedureCallException("Error : Unknown Procedure Operation Type Configured! Found "
					+ operationType + ", Expected F,S,U,D,O");

		}

		return dataStr;

	}

	public JSONArray genericFetchtProcCall(String procCallStr, JSONArray inputDataArr, Map<String, String> values) {
		JSONArray jsnArr = new JSONArray();
		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall(procCallStr, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

		) {
System.out.println("procCallStr = "+procCallStr);
			if (inputDataArr != null && inputDataArr.size() > 0)
				for (int i = 0; i < inputDataArr.size(); i++) {

					JSONObject paramObj = (JSONObject) inputDataArr.get(i);

					String key = (String) paramObj.get("PARAM_NAME_UI");
					String value = values.get(key) != null ? values.get(key) : "";
					System.out.println(" key = "+key +" value = "+value);
					String procParamType = (String) paramObj.get("INPUT_OUTPUT_TYPE");
					if ("SELECTED".equalsIgnoreCase(procParamType)) {
						String inQueryStr = Utils.setSelectedItemsToPassInSQLIn(value);
						value = inQueryStr;
					}

					cs.setString(i + 1, value);
				}

			boolean hadResults = cs.execute();
			while (hadResults) {
				ResultSet resultSet = cs.getResultSet();
				jsnArr.add(Utils.convertResultSetToJsonArray(resultSet));
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

	public String doGenericCRUDOperation(String procCallStr, JSONArray inputDataArr, Map<String, String> values)
			throws GenericProcedureCallException {
		String result = "Error occurred while updating...";
		boolean isOutParamConfigured = false;
		try (

				Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall(procCallStr);) {
			int i = 0;

			if (inputDataArr != null && inputDataArr.size() > 0) {

				for (; i < inputDataArr.size(); i++) {

					JSONObject paramObj = (JSONObject) inputDataArr.get(i);

					String paramName = (String) paramObj.get("PARAM_NAME_UI");
					String procParamType = (String) paramObj.get("INPUT_OUTPUT_TYPE");
					String procParamDataType = (String) paramObj.get("PROC_PARAM_DATA_TYPE");

					if (paramName == null || "".equals(paramName))
						throw new GenericProcedureCallException("Error : Procedure  Param name configuration missed ");

					if (procParamType == null || "".equals(procParamType))
						throw new GenericProcedureCallException(
								"Error : Procedure Param Type(IN or OUT) configuration missed ");

					if (procParamDataType == null || "".equals(procParamDataType))
						throw new GenericProcedureCallException(
								"Error : Procedure  Param Input or Output data type configuration missed ");

					if ("IN".equalsIgnoreCase(procParamType))

						statement.setString(i + 1, values.get(paramName) != null ? values.get(paramName) : "");

					if ("SEQUENCE".equalsIgnoreCase(procParamType))

						statement.setString(i + 1, idGenerator.get(paramName, paramName));

					if ("SELECTED".equalsIgnoreCase(procParamType)) {
						String selectedValues = values.get(paramName).toString() != null
								? values.get(paramName).toString()
								: "";
						String inQueryStr = Utils.setSelectedItemsToPassInSQLIn(selectedValues);
						statement.setString(i + 1, inQueryStr);
					}
					if ("INOUT".equalsIgnoreCase(procParamType)) {
						isOutParamConfigured = true;
						result = "Y";
						statement.registerOutParameter(i + 1, Types.VARCHAR);
					}

				}
			}
			statement.execute();
			if (isOutParamConfigured)
				result = statement.getString(i);

			result = result.equalsIgnoreCase("Y") ? "Action Performed successfully!"
					: "Error occurred while doing action!";
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			result = ex.getMessage();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}

		return result;
	}

	public String generateProcCallStr(String procName, int noOfParams) throws GenericProcedureCallException {

		StringBuilder paramBldr = new StringBuilder("");

		if (procName == null || "".equals(procName))
			throw new GenericProcedureCallException("Error : Procedure Name Missied in Configurations! ");

		if (noOfParams == 0)

			return paramBldr.append("{ CALL ").append(procName).append("()}").toString();

		else {
			paramBldr.append("{ CALL ").append(procName).append("( ");
			for (int i = 0; i < noOfParams; i++)
				paramBldr.append("?,");

			paramBldr = paramBldr.deleteCharAt(paramBldr.length() - 1);

			paramBldr.append(")}");

			return paramBldr.toString();
		}

	}

	public JSONArray genericFetchtProcCall(String procCallStr, String procId) {
		JSONArray jsnArr = new JSONArray();

		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall(procCallStr, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

		) {
			cs.setString(1, procId.toUpperCase());
			boolean hadResults = cs.execute();
			while (hadResults) {

				ResultSet resultSet = cs.getResultSet();
				jsnArr.add(Utils.convertResultSetToJsonArray(resultSet));
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

}
