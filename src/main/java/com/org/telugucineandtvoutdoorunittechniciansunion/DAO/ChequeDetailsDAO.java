package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.jdbc.ReturningWork;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class ChequeDetailsDAO {
	@Autowired
	public DataAccess dataAccess;
	@Autowired
	IdGenerator idGenerator;

	public Connection getConnection() {

		return dataAccess.getSessionFactory().getCurrentSession().doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException {
				return conn;
			}
		});
	}

	public String getAllChequeDetails(String fromDate, String toDate, String chequeNo, String chequeType, String amount,
			String chequeRecievedFrom, String companyName, String bankName) {
		String result = "";

		chequeNo = chequeNo != null ? chequeNo.trim() : "";
		chequeType = chequeType != null ? chequeType.trim() : "";
		amount = amount != null ? amount.trim() : "";
		chequeRecievedFrom = chequeRecievedFrom != null ? chequeRecievedFrom.trim() : "";
		companyName = companyName != null ? companyName.trim() : "";
		fromDate = fromDate != null ? fromDate.trim() : "";
		toDate = toDate != null ? toDate.trim() : "";
		bankName = bankName != null ? bankName.trim() : "";
		StringBuffer whereCond = new StringBuffer();

		if (new StringBuffer(chequeNo).append(chequeType).append(amount).append(chequeRecievedFrom).append(companyName)
				.append(bankName).length() > 0) {
			whereCond.append(" WHERE CHEQUE_DATE BETWEEN  STR_TO_DATE('").append(fromDate).append("','")
					.append("%d-%m-%Y").append("') AND  STR_TO_DATE('").append(toDate).append("','")
					.append("%d-%m-%Y')");
			if (!"".contentEquals(chequeNo))
				whereCond.append(" AND CHEQUE_NO like'").append(chequeNo).append("%'");
			if (!"".contentEquals(chequeType))
				whereCond.append(" AND CHEQUE_TYPE like'").append(chequeType).append("%'");
			if (!"".contentEquals(amount))
				whereCond.append(" AND AMOUNT like'").append(amount).append("%' ");
			if (!"".contentEquals(chequeRecievedFrom))
				whereCond.append(" AND CHEQUE_RECIEVED_FROM like'").append(chequeRecievedFrom).append("%' ");
			if (!"".contentEquals(companyName))
				whereCond.append(" AND COMPANY_NAME like'").append(companyName).append("%' ");
			if (!"".contentEquals(bankName))
				whereCond.append(" AND BANKNAME like'").append(bankName).append("%' ");

			if (!"".contentEquals(bankName))
				whereCond.append(" AND BANKNAME like'").append(bankName).append("%' ");

			if (!"".contentEquals(bankName))
				whereCond.append(" AND BANKNAME like'").append(bankName).append("%' ");

		}
		StringBuffer selectQuery = new StringBuffer(
				"select CHEQUE_NO,COMPANY_NAME,PHONE_NO,DATE_FORMAT(CHEQUE_DATE,'%d-%m-%Y') as CHEQUE_DATE,AMOUNT,CHEQUE_RECIEVED_FROM,CHEQUE_TYPE,FIELD1,BANKNAME from cheque_details ");
		selectQuery.append(whereCond);
		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery.toString());) {
			result = Utils.convertResultSetToJsonArray(stmt.executeQuery()).toJSONString();
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return result;
	}

	public String getChequeDetails(String chequeNo) {
		String result = "";
		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement("select * from cheque_disburs where checque_no= ?");) {

			stmt.setString(1, chequeNo);
			ResultSet rs = stmt.executeQuery();

			result = Utils.convertResultSetToJsonArray(rs).toJSONString();
			while (rs.next()) {

			}

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}

		return result;

	}

	public String getChequeDesburseDetails(String chequeNo) {
		return null;
	}

	public String saveChequeDetails(String chequeNo, String chequeType, String phoneNo, String amount,
			String chequeRecievedFrom, String companyName, String chequeDate, String bankName, String remarks) {
		String result = "Error occured while saving...";

		try (

				Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL SAVE_CHEQUE_DETAILS(?,?, ?,?,?,?,?,?,?)}");) {

			statement.setString(1, chequeNo);
			statement.setString(2, chequeType);
			statement.setString(3, phoneNo);
			statement.setString(4, amount);
			statement.setString(5, chequeRecievedFrom);
			statement.setString(6, companyName);
			statement.setString(7, chequeDate);
			statement.setString(8, bankName);
			statement.setString(9, remarks);
			statement.execute();
			statement.close();
			result = "Saved successfully!";

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String saveChequeDisburseDetails(String chequeNo, String deptId, String cardNo, String bankAccNo,
			String amount, String ourChequeId) {
		String result = "Error occured while saving...";
		String CHQ_DSB_ID = "";
		try {
			CHQ_DSB_ID = this.idGenerator.get("CHQ_DSB_ID", "CHQ_DSB_ID");
		} catch (Exception e) {
			ApplicationUtilities.error(ChequeDetailsDAO.class,e.getMessage(),e);
		}
		try (Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL SAVE_CHEQUE_DESBURS(?, ?, ?, ?, ?, ?, ?,?)}");) {

			statement.setString(1, CHQ_DSB_ID);
			statement.setString(2, chequeNo);
			statement.setString(3, deptId);
			statement.setString(4, cardNo);
			statement.setString(5, bankAccNo);
			statement.setString(6, amount);
			statement.setString(7, ourChequeId);
			statement.registerOutParameter(8, Types.VARCHAR);
			statement.execute();
			result = statement.getString(8);
			result = result.equalsIgnoreCase("Y") ? "Updated successfully!"
					: (result.equalsIgnoreCase("N") ? "Error occurred while updating!" : "Cheque does not exist!");
			statement.close();
			// result="Saved successfully!";

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String updateChequeDetails(String chequeNo, String chequeType, String phoneNo, String amount,
			String chequeRecievedFrom, String companyName, String chequeDate, String bankName, String remarks) {
		String result = "Error occurred while updating...";

		try (

				Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn
						.prepareCall("{CALL UPDATE_CHEQUE_DETAILS(?,?, ?, ?, ?, ?, ?, ?, ?, ?)}");) {

			statement.setString(1, chequeNo);
			statement.setString(2, chequeType);
			statement.setString(3, phoneNo);
			statement.setString(4, amount);
			statement.setString(5, chequeRecievedFrom);
			statement.setString(6, companyName);
			statement.setString(7, chequeDate);
			statement.setString(8, bankName);
			statement.setString(9, remarks);
			statement.registerOutParameter(10, Types.VARCHAR);
			statement.execute();
			result = statement.getString(10);
			result = result.equalsIgnoreCase("Y") ? "Updated successfully!" : "Error occurred while updating!";
			// statement.close();
			// result=Utils.convertResultSetToJsonArray(rset).toJSONString();
			// result="Updated successfully!";
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String updateChequeDisburseDetails(String chequeDsbId, String chequeNo, String deptId, String cardNo,
			String bankAccNo, String amount, String ourChequeId) {
		String result = "Error occurred while updating...";

		try (

				Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL UPDATE_CHEQUE_DESBURS(?,?, ?, ?, ?, ?,?)}");) {
			statement.setString(1, chequeDsbId);
			statement.setString(2, chequeNo);
			statement.setString(3, deptId);
			statement.setString(4, cardNo);
			statement.setString(5, amount);
			statement.setString(6, ourChequeId);
			statement.registerOutParameter(7, Types.VARCHAR);
			statement.execute();
			result = statement.getString(7);
			result = result.equalsIgnoreCase("Y") ? "Updated successfully!" : "Error occurred while updating!";
			/*
			 * result = result.equalsIgnoreCase("Y") ? "Updated successfully!" :
			 * (result.equalsIgnoreCase("N") ? "Error occurred while updating!" :
			 * "Record already exist!");
			 */
		} catch (SQLIntegrityConstraintViolationException ie) {
			result = "Record already exist!";
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String deleteChequeDetails(String chequeNo) {
		String result = "Error occurred while deleting...";

		try (

				Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL DELETE_CHEQUE_DETAILS(?, ?)}");) {

			statement.setString(1, chequeNo);
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.execute();
			result = statement.getString(2);
			result = result.equalsIgnoreCase("Y") ? "Deleted successfully!" : "Error occurred while deleting!";
			// statement.close();
			// result=Utils.convertResultSetToJsonArray(rset).toJSONString();
			// result="Updated successfully!";
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String deleteCheques(String chequeNos) {
		String result = "Error occurred while deleting...";
		try (Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL DELETE_CHEQUES(?, ?)}");) {
			if (chequeNos.length() > 0) {

				if (chequeNos.indexOf(",") > -1) {

					chequeNos = chequeNos.replace(",", "','");
					chequeNos = "'" + chequeNos + "'";
				}

			}
			statement.setString(1, chequeNos);
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.execute();
			result = statement.getString(2);
			result = result.equalsIgnoreCase("Y") ? "Deleted successfully!" : "Error occurred while deleting!";
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	public String deleteChequeDesburseDetails(String chqDsbId) {
		String result = "Error occurred while deleting...";
		try (Connection conn = dataAccess.getConnection();
				CallableStatement statement = conn.prepareCall("{CALL DELETE_CHEQUE_DESBURS(?, ?)}");) {
			statement.setString(1, chqDsbId);
			statement.registerOutParameter(2, Types.VARCHAR);
			statement.execute();
			result = statement.getString(2);
			result = result.equalsIgnoreCase("Y") ? "Deleted successfully!" : "Error occurred while deleting!";
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
			result = ex.getMessage();

		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public String getChequeNos(String searchTerm) {
		String selectQuery = "select CHEQUE_NO from cheque_details ";
		ResultSet rs = null;
		JSONArray jsArr = new JSONArray();

		if (searchTerm != null && !"".contentEquals(searchTerm))

			selectQuery = selectQuery + " where lower(CHEQUE_NO) like  '% " + searchTerm + "'";

		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {
			rs = stmt.executeQuery();

			while (rs.next())
				jsArr.add(rs.getObject(1));

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return jsArr.toString();
	}

	// getChequeNosByDeptId
	@SuppressWarnings("unchecked")
	public String getCardNosByDeptId(String deptId) {
		String selectQuery = "SELECT CARD_NO from REGISTRATION ";
		ResultSet rs = null;
		JSONArray jsArr = new JSONArray();

		if (deptId != null && !"".contentEquals(deptId))
			selectQuery = selectQuery + " WHERE DEPT_ID= ? ";

		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {
			stmt.setString(1, deptId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				jsArr.add(rs.getObject(1));
			}
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return jsArr.toString();
	}

	@SuppressWarnings("unchecked")
	public String getMemberDetailsByDeptAndCardNo(String deptId, String cardNo) {
		String selectQuery = "SELECT MEMBER_ID,FIRST_NAME,BANK_ACC_NO FROM REGISTRATION ";
		ResultSet rs = null;
		selectQuery = selectQuery + "  WHERE DEPT_ID = ? AND CARD_NO = ? ";

		JSONObject obj = new JSONObject();

		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {
			stmt.setString(1, deptId);
			stmt.setString(2, cardNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				obj.put("MEMBER_ID", rs.getObject(1));
				obj.put("FIRST_NAME", rs.getObject(2));
				obj.put("BANK_ACC_NO", rs.getObject(3));
			}
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return obj.toString();
	}

	public String getAllCardNo() {
		String result = "";
		String selectQuery = "select dept_id,card_no from registration ";
		ResultSet rs = null;
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {

			rs = stmt.executeQuery();
			while (rs.next()) {
				String key = rs.getObject(1).toString();
				ArrayList<String> cardNoList = new ArrayList<String>();
				if (!map.containsKey(key)) {

					cardNoList.add(rs.getObject(2).toString());
					map.put(key, cardNoList);
				} else {
					cardNoList = (ArrayList<String>) map.get(key);
					cardNoList.add(rs.getObject(2).toString());
					map.put(key, cardNoList);
				}

			}
			result = JSONValue.toJSONString(map);

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return result;
	}

	public String getSelectedChequeDetails(String chequeNos) {
		String result = "";
		String inStr = "";
		String selectQuery = "select CHEQUE_NO,COMPANY_NAME,CHEQUE_DATE,AMOUNT,CHEQUE_RECIEVED_FROM,CHEQUE_TYPE,FIELD1,BANKNAME from cheque_details ";
		if (chequeNos != null && !"".contentEquals(chequeNos)) {

			if (chequeNos.indexOf(",") > -1) {
				String chequesArr[] = chequeNos.split("\\,");
				int i = 0;
				for (; i < chequesArr.length; i++) {
					inStr = inStr + "'" + chequesArr[i] + "',";
				}
				if (i > 0)
					inStr = inStr.substring(0, inStr.length() - 1);
			} else
				return inStr = " where CHEQUE_NO ='" + chequeNos + "'";

			selectQuery = selectQuery + " where CHEQUE_NO in (" + inStr + ")";
		}

		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {

			result = Utils.convertResultSetToJsonArray(stmt.executeQuery()).toJSONString();

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}

		return result;
	}

	public String getSelectedChequeDisburseDetails(String selectedChequeNo, String chequeNo, String deptId,
			String cardNo, String amount, String ourChequeId) {
		String result = "";
		String inStr = "";
		String selectQuery = "select a.CHQ_DSB_ID, a.CHEQUE_NO,b.FIRST_NAME, b.BANK_ACC_NO,a.CREATED_DATE, a.AMOUNT, a.DEPT_ID, a.CARD_NO ,"
				+ " a.PAID_CHQ_NO from cheque_desburs a ,registration b  ";
		if (selectedChequeNo != null && !"".contentEquals(selectedChequeNo)) {

			if (selectedChequeNo.indexOf(",") > -1) {
				String chequesArr[] = selectedChequeNo.split("\\,");
				int i = 0;

				for (; i < chequesArr.length; i++) {
					if (chequeNo != null && !"".equals(chequeNo)) {
						if (chequesArr[i] != null && !"".equals(chequesArr[i])) {
							if (chequesArr[i].toLowerCase().startsWith(chequeNo.toLowerCase()))
								inStr = inStr + "'" + chequesArr[i] + "',";
						}
					} else
						inStr = inStr + "'" + chequesArr[i] + "',";
				}

				if (i > 0) {

					if (!"".equals(inStr))
						inStr = inStr.substring(0, inStr.length() - 1);
					if ("".equals(inStr))
						inStr = "''";
				}

				selectQuery = selectQuery + " where  a.CARD_NO=b.CARD_NO AND a.DEPT_ID=b.DEPT_ID and a.CHEQUE_NO in ("
						+ inStr + ")";
			} else
				selectQuery = selectQuery + " where  a.CARD_NO=b.CARD_NO AND a.DEPT_ID=b.DEPT_ID and a.CHEQUE_NO ='"
						+ selectedChequeNo + "'";

		}

		// if( chequeNo!=null && !"".equals(chequeNo))
		// selectQuery = selectQuery + " AND CHEQUE_NO LIKE '"+chequeNo+"%' ";

		if (deptId != null && !"".equals(deptId) && !"ALL".equals(deptId))
			selectQuery = selectQuery + "  AND a.DEPT_ID LIKE '" + deptId + "%' ";

		if (cardNo != null && !"".equals(cardNo))
			selectQuery = selectQuery + "  AND a.CARD_NO LIKE '" + cardNo + "%' ";

		if (amount != null && !"".equals(amount))
			selectQuery = selectQuery + "  AND a.AMOUNT LIKE '" + amount + "%' ";

		if (ourChequeId != null && !"".equals(ourChequeId))
			selectQuery = selectQuery + "  AND a.PAID_CHQ_NO LIKE '" + ourChequeId + "%' ";

		try (Connection conn = dataAccess.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectQuery);) {
			result = Utils.convertResultSetToJsonArray(stmt.executeQuery()).toJSONString();

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
		return result;
	}

	public void getGenericData(String exportConfigId) {
		String query = "{CALL GET_GENERIC_PROC_DETAILS(?)}";

		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

		) {
			cs.setString(1, "GRID_CONFIG_DETAILS");
			boolean hadResults = cs.execute();
			while (hadResults) {
				ResultSet resultSet = cs.getResultSet();
				hadResults = cs.getMoreResults();
			}
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}

	}

//getAllPaidCheques getPaidChequeById updatePaidCheque deletePaidCheque
	public String getAllPaidCheques(Map<String, String> requestData) {

		dataAccess.getConnection();

		return "";

	}

	public String getPaidChequeById(Map<String, String> requestData) {

		return "";

	}

	public String savePaidCheque(Map<String, String> requestData) {

		return "";

	}

	public String updatePaidCheque(Map<String, String> requestData) {

		return "";

	}

	public String deletePaidCheque(Map<String, String> requestData) {

		return "";

	}

}
