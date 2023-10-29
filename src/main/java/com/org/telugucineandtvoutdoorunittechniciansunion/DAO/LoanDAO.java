package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.NotValidAmountException;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetailsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Loandetails;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoandetailsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
@SuppressWarnings("unchecked")
public class LoanDAO {
	@Autowired
	public DataAccess dataAccess;
	@Autowired
	IdGenerator idGenerator;
	@Autowired
	MiscellaneousDAO miscellaneousDAO;
	Utils utils = new Utils();
	public static final String TR_START = "<tr>";
	public static final String TR_END = "</tr>";
	public static final String TD_START = "<td>";
	public static final String TD_END = "</td>";
	public static final String RECEIPT_NO = "RECEIPT_NO";
	@Autowired
	GenericCRUDOperationsDAO genericCRUDOperationsDAO;

	@Transactional
	public String sanctionLoan(HttpServletRequest request) {
		String result = "Sorry System failed to Sanction Loan ";
		JSONObject resutlObject = new JSONObject();
		String memberId = request.getParameter("memberId");
		try {

			Registration registredUserDetials = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);

			String loanAmount = request.getParameter("loanAmount");
			String loanSanctioneddDate = request.getParameter("loanSanctioneddDate");
			if (this.utils.isNumericString(loanAmount)) {
				String loanId = this.idGenerator.get("LOAN_ID", "LOAN_ID");
				if (loanId != null && !"".equals(loanId)) {
					Loandetails loanDetails = new Loandetails();
					LoandetailsPK loandetailsPK = new LoandetailsPK();
					loandetailsPK.setLoanId(loanId);
					loandetailsPK.setMemberId(memberId);
					loanDetails.setLoandetailsPK(loandetailsPK);
					loanDetails.setLoanAmount(Integer.parseInt(loanAmount));
					loanDetails.setLoanSanctionedDate((new SimpleDateFormat("dd/MM/yyyy")).parse(loanSanctioneddDate));
					loanDetails.setMemberName(registredUserDetials.getFirstName());
					loanDetails.setRemarks(request.getParameter("remarks"));
					loanDetails.setLoanStatus("LOAN_UNDER_RECOVERY");
					Object isSaved = this.dataAccess.save(loanDetails);

					if (isSaved instanceof Loandetails && isSaved != null) {

						result = "Loan sanctioned sucessfully!";
						resutlObject.put("FINAL_RESULT_CODE", "400");
						resutlObject.put("DATA_DETAILS", result);
					} else {
						result = " Failed to sanctioned Loan due to system not able to update loan deails in Register ";
						resutlObject.put("FINAL_RESULT_CODE", "200");
						resutlObject.put("ERROR_MSG", result);
					}

				} else {

					result = "Sorry System failed to Sanction Loan due to IdGenerator Failure !";
					resutlObject.put("FINAL_RESULT_CODE", "200");
					resutlObject.put("ERROR_MSG", result);
				}

			} else {

				throw new NotValidAmountException("Not Valid Amount !", loanAmount);
			}
			updateLoanBalance(memberId);

		} catch (NotValidAmountException nva) {
			ApplicationUtilities.error(this.getClass(), nva.getMessage(), nva);
			result = " Sorry System failed to Sanction loan because User entered invalid loamount! >> "
					+ nva.getAmmount();
			resutlObject.put("FINAL_RESULT_CODE", "300");
			resutlObject.put("ERROR_MSG", result);
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result = String.valueOf(String.valueOf(String.valueOf(result))) + " :: " + e.getMessage();
			resutlObject.put("FINAL_RESULT_CODE", "300");
			resutlObject.put("ERROR_MSG", result);
		}

		return resutlObject.toJSONString();
	}

	@Transactional
	public String loanSummary(HttpServletRequest request) {
		String table = "";

		try {
			String query = "SELECT R.DEPT_ID,R.FIRST_NAME,R.CARD_NO ,LD.LOAN_AMOUNT,SUM(L.PAID_AMOUNT) as TOTALPAID_AMOUNT , L.MEMBER_ID FROM TEST.LOAN_RECOVERY_DETAILS L, TEST.REGISTRATION R  ,TEST.LOANDETAILS LD  WHERE R.MEMBER_ID=L.MEMBER_ID  AND LD.LOAN_ID =L.LOAN_ID   GROUP BY L.MEMBER_ID           ORDER BY      R.DEPT_ID DESC";

			Map<String, Object> parametersMap = new HashMap<String, Object>();

			List<Object[]> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);

			table = "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>";
			table = String.valueOf(String.valueOf(String.valueOf(table)))
					+ "<thead><tr><th>Department</th><th>Name</th><th>Card No</th><th>Loan Amount</th><th>Total Paid</th></tr></thead>";
			if (list != null && list.size() > 0) {
				table = String.valueOf(String.valueOf(String.valueOf(table))) + "<tbody>";
				for (int i = 0; i < list.size(); i++) {
					Object[] objectArr = list.get(i);

					table = String.valueOf(String.valueOf(String.valueOf(table))) + TR_START + TD_START
							+ this.utils.convertNullToEmptyString(objectArr[0]) + TD_END + TD_START
							+ this.utils.convertNullToEmptyString(objectArr[1]) + TD_END + TD_START
							+ this.utils.convertNullToEmptyString(objectArr[2]) + TD_END + TD_START
							+ this.utils.convertNullToEmptyString(objectArr[3]) + TD_END + TD_START
							+ this.utils.convertNullToEmptyString(objectArr[4]) + TD_END + TR_END;
				}

				table = String.valueOf(String.valueOf(String.valueOf(table))) + "</tbody>";
			} else {
				table = String.valueOf(String.valueOf(String.valueOf(table)))
						+ "<tbody><tr><td colspan='5' align='center'> No Data found</td></tr></tbody>";
			}

			table = String.valueOf(String.valueOf(String.valueOf(table))) + "</table>";

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return table;
	}

	@Transactional
	public String payLoanAmount(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String cardNo = request.getParameter("cardNo");
			String deptId = request.getParameter("deptId");
			String memberId = request.getParameter("memberId");
			String paidAmount = request.getParameter("payingAmount");
			String paidDate = request.getParameter("paidDate");
			String receiptNo = this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);

			String remarks = request.getParameter("remarks");

			JSONObject validateJsnObj = new JSONObject();
			validateJsnObj.put("MEMBER_ID", memberId);
			validateJsnObj.put("CARD_NO", cardNo);
			validateJsnObj.put("DEPT_ID", deptId);
			validateJsnObj.put("PAID_AMOUNT", paidAmount);
			validateJsnObj.put("PAID_DATE", paidDate);
			validateJsnObj.put(RECEIPT_NO, receiptNo);
			Registration registeredDetails = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);

			String validationResult = loanPaymentValidation(validateJsnObj);
			if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {
				LoanRecoveryDetails loanRecoveryDetails = new LoanRecoveryDetails();
				LoanRecoveryDetailsPK loanRecoveryDetailsPK = new LoanRecoveryDetailsPK();
				loanRecoveryDetails.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
				loanRecoveryDetails.setPaidAmount(Integer.parseInt(paidAmount));
				loanRecoveryDetails.setRemarks(remarks);
				loanRecoveryDetails.setReceiptNo(receiptNo);
				loanRecoveryDetailsPK.setLoanId(registeredDetails.getCurrentLoanId());
				loanRecoveryDetailsPK.setMemberId(memberId);
				String transId = this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID");
				loanRecoveryDetailsPK.setTransactionId(transId);
				loanRecoveryDetails.setLoanRecoveryDetailsPK(loanRecoveryDetailsPK);
				this.dataAccess.save(loanRecoveryDetails);

				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "Loan amount paid sucessfullty!");
				Map<String, String> actionData = new HashMap<>();
				actionData.put("PROC_ID", "SAVE_RECEIPT_DETAILS");
				actionData.put("RECEIPT_TYPE", "LOAN_RECOVERY_AMOUNT");
				actionData.put("TRANSACTION_ID", transId);
				actionData.put("MEMBER_ID", memberId);
				actionData.put("CREATED_BY", "P.Durga Rao");
				actionData.put("AMOUNT", paidAmount);
				actionData.put("REMARKS", "");
				actionData.put(RECEIPT_NO, receiptNo);
				genericCRUDOperationsDAO.doGenericCRUDOpertion(actionData);
				result.put(RECEIPT_NO, receiptNo);
				updateLoanBalance(memberId);
			} else {

				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", validationResult);
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(this.getClass(), nfe.getMessage(), nfe);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Unable to pay loan amount" + e.getMessage());
		}
		return result.toJSONString();
	}

	public String getLoanDetails(HttpServletRequest request) {
		String result = "";
		try {
			String memberId = request.getParameter("memberId");
			String currentloadIdFromRegister = request.getParameter("currentloadIdFromRegister");
			String currentLoanStatusFromRegister = request.getParameter("currentLoanStatusFromRegister");
			result = getLoanDetails(memberId, currentloadIdFromRegister, currentLoanStatusFromRegister);
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}
		return result;
	}

	public String getLoanSanctionedDetails(String memberId) {
		JSONArray loanDetalsArr = new JSONArray();
		JSONObject topPanelResultObj = new JSONObject();

		try {
			if (memberId != null && !"".equalsIgnoreCase(memberId)) {

				String query = " from Loandetails where loandetailsPK.memberId=:memberId ";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("memberId", memberId.trim());

				List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);

				if (loandetailsList != null && loandetailsList.size() > 0) {
					for (int i = 0; i < loandetailsList.size(); i++) {
						Loandetails LoandetailsObj = loandetailsList.get(i);
						JSONObject jsnObj = new JSONObject();
						String ccc = this.utils.convertNullToEmptyString(LoandetailsObj.getLoanSanctionedDate());
						String[] date = ccc.split("-");
						jsnObj.put("LOAN_SANCTIONED_DATE", String.valueOf(String.valueOf(String.valueOf(date[2]))) + "/"
								+ date[1] + "/" + date[0]);

						jsnObj.put("LOAN_AMOUNT",
								this.utils.convertNullToEmptyString(Integer.valueOf(LoandetailsObj.getLoanAmount())));
						jsnObj.put("LOAN_ID",
								this.utils.convertNullToEmptyString(LoandetailsObj.getLoandetailsPK().getLoanId()));
						jsnObj.put("MEMBER_ID",
								this.utils.convertNullToEmptyString(LoandetailsObj.getLoandetailsPK().getMemberId()));
						jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(LoandetailsObj.getRemarks()));
						loanDetalsArr.add(jsnObj);
					}
				}

				topPanelResultObj.put("LOAN_SANCTION_DETAILS_RESULT_CODE", "400");
				topPanelResultObj.put("LOAN_SANCTION_DETAILS", loanDetalsArr);
			}

		} catch (Exception e) {

			topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
			topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return topPanelResultObj.toJSONString();
	}

	public String getLoanDetails(String memberId) {
		JSONArray loanDetalsArr = new JSONArray();
		JSONArray loanRecoveryDetalsArr = new JSONArray();
		JSONObject topPanelResultObj = new JSONObject();

		try {
			if (memberId != null && !"".equalsIgnoreCase(memberId)) {

				String qury1 = " SELECT L.LOAN_SANCTIONED_DATE,R.FIRST_NAME, R.FATHER_NAME,R.DEPT_ID,R.CARD_NO,L.LOAN_AMOUNT,L.LOAN_ID   FROM REGISTRATION R,LOANDETAILS L  WHERE R.MEMBER_ID=L.MEMBER_ID   AND R.MEMBER_ID=:MEMBER_ID   ORDER BY    L.LOAN_SANCTIONED_DATE    DESC";

				Map<String, Object> qury1parameters = new HashMap<String, Object>();
				qury1parameters.put("MEMBER_ID", memberId.trim());
				List<Object[]> loandetailsList = this.dataAccess.sqlqueryWithParams(qury1, qury1parameters);
				JSONObject loanDetailsObj = new JSONObject();
				if (loandetailsList != null && loandetailsList.size() > 0) {

					for (int i = 0; i < loandetailsList.size(); i++) {
						Object[] objectArr = loandetailsList.get(i);
						JSONObject jsnObj = new JSONObject();
						jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[0]));
						jsnObj.put("FIRST_NAME", this.utils.convertNullToEmptyString(objectArr[1]));
						jsnObj.put("FATHER_NAME", this.utils.convertNullToEmptyString(objectArr[2]));
						jsnObj.put("DEPT_ID", this.utils.convertNullToEmptyString(objectArr[3]));
						jsnObj.put("CARD_NO", this.utils.convertNullToEmptyString(objectArr[4]));
						jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[5]));
						jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[6]));

						loanDetailsObj.put(this.utils.convertNullToEmptyString(objectArr[6]), jsnObj);
						loanDetalsArr.add(jsnObj);
					}

					String query = " SELECT  recoveryDetails.TRANSACTION_ID,  recoveryDetails.PAID_AMOUNT,  recoveryDetails.PAID_DATE,  recoveryDetails.RECEIPT_NO,  recoveryDetails.REMARKS,  recoveryDetails.STATUS,  recoveryDetails.LOAN_ID  FROM LOAN_RECOVERY_DETAILS recoveryDetails  WHERE recoveryDetails.MEMBER_ID=:MEMBER_ID";

					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("MEMBER_ID", memberId.trim());

					List<Object[]> loanRecoverydetailsList = this.dataAccess.sqlqueryWithParams(query, parameters);
					if (loanRecoverydetailsList != null && loanRecoverydetailsList.size() > 0) {

						for (int j = 0; j < loanRecoverydetailsList.size(); j++) {

							Object[] objectArr = loanRecoverydetailsList.get(j);

							JSONObject jsnObj = new JSONObject();
							JSONObject loanDetObj = (JSONObject) loanDetailsObj.get(objectArr[6]);
							if (loanDetObj != null) {
								jsnObj.put("LOAN_SANCTIONED_DATE",
										this.utils.convertNullToEmptyString(loanDetObj.get("LOAN_SANCTIONED_DATE")));
								jsnObj.put("LOAN_AMOUNT",
										this.utils.convertNullToEmptyString(loanDetObj.get("LOAN_AMOUNT")));
								jsnObj.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objectArr[0]));
								jsnObj.put("PAID_AMOUNT", this.utils.convertNullToEmptyString(objectArr[1]));
								jsnObj.put("PAID_DATE", this.utils.convertNullToEmptyString(objectArr[2]));
								jsnObj.put(RECEIPT_NO, this.utils.convertNullToEmptyString(objectArr[3]));
								jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(objectArr[4]));
								jsnObj.put("STATUS", this.utils.convertNullToEmptyString(objectArr[5]));
								jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[6]));
								jsnObj.put("MEMBER_ID", memberId.trim());

								loanRecoveryDetalsArr.add(jsnObj);
							}
						}
					}
				}

				topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "400");
				topPanelResultObj.put("LOAN_DETAILS", loanDetalsArr);
				topPanelResultObj.put("LOAN_RECOVERY_DETAILS", loanRecoveryDetalsArr);
			}

		} catch (Exception e) {

			topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
			topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return topPanelResultObj.toJSONString();
	}

	public String getLoanDetails(String memberId, String currentloanIdFromRegister,
			String currentLoanStatusFromRegister) {
		JSONArray loanDetalsArr = new JSONArray();
		JSONArray loanRecoveryDetalsArr = new JSONArray();
		JSONObject topPanelResultObj = new JSONObject();

		try {
			if (memberId != null && !"".equalsIgnoreCase(memberId)) {

				String queryForLoanRecoveryDetaisls = "from LoanRecoveryDetails where LoanRecoveryDetailsPK.memberId=:MEMBER_ID";

				Map<String, Object> loanRecoveryDetQryParams = new HashMap<String, Object>();
				loanRecoveryDetQryParams.put("MEMBER_ID", memberId.trim());

				List loanRecoveryDetList = this.dataAccess.queryWithParams(queryForLoanRecoveryDetaisls,
						loanRecoveryDetQryParams);
				if (loanRecoveryDetList != null)
					loanRecoveryDetList.size();

				String qury1 = " SELECT L.LOAN_SANCTIONED_DATE,R.FIRST_NAME, R.FATHER_NAME,R.DEPT_ID,R.CARD_NO,L.LOAN_AMOUNT   FROM REGISTRATION R,LOANDETAILS L  WHERE R.MEMBER_ID=L.MEMBER_ID   AND R.MEMBER_ID=:MEMBER_ID   ORDER BY    L.LOAN_SANCTIONED_DATE    DESC";

				Map<String, Object> qury1parameters = new HashMap<String, Object>();
				qury1parameters.put("MEMBER_ID", memberId.trim());
				List<Object[]> loandetailsList = this.dataAccess.sqlqueryWithParams(qury1, qury1parameters);

				if (loandetailsList != null && loandetailsList.size() > 0) {
					for (int i = 0; i < loandetailsList.size(); i++) {
						Object[] objectArr = loandetailsList.get(i);
						JSONObject jsnObj = new JSONObject();

						jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[0]));
						jsnObj.put("FIRST_NAME", this.utils.convertNullToEmptyString(objectArr[1]));
						jsnObj.put("FATHER_NAME", this.utils.convertNullToEmptyString(objectArr[2]));
						jsnObj.put("DEPT_ID", this.utils.convertNullToEmptyString(objectArr[3]));
						jsnObj.put("CARD_NO", this.utils.convertNullToEmptyString(objectArr[4]));
						jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[5]));

						loanDetalsArr.add(jsnObj);
					}
				}

				String query = " SELECT  loanDetails .LOAN_ID,  loanDetails .MEMBER_ID,  loanDetails .LOAN_AMOUNT,  loanDetails .LOAN_SANCTIONED_DATE,  loanDetails .MEMBER_NAME,  loanDetails .REMARKS AS LOAN_SANCTION_REMARKS,  recoveryDetails.TRANSACTION_ID,  recoveryDetails.PAID_AMOUNT,  recoveryDetails.PAID_DATE,  recoveryDetails.RECEIPT_NO,  recoveryDetails.REMARKS,  recoveryDetails.STATUS  FROM LOANDETAILS loanDetails ,LOAN_RECOVERY_DETAILS recoveryDetails  WHERE loanDetails.MEMBER_ID=recoveryDetails.MEMBER_ID    AND loanDetails.MEMBER_ID=:MEMBER_ID ";

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("MEMBER_ID", memberId.trim());

				List<Object[]> loanRecoverydetailsList = this.dataAccess.sqlqueryWithParams(query, parameters);

				if (loanRecoverydetailsList != null && loanRecoverydetailsList.size() > 0) {
					Object[] objArrOne = loanRecoverydetailsList.get(0);
					JSONObject jsnObject = new JSONObject();
					jsnObject.put("MEMBER_ID", this.utils.convertNullToEmptyString(objArrOne[1]));
					jsnObject.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objArrOne[2]));
					jsnObject.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objArrOne[3]));
					jsnObject.put("MEMBER_NAME", this.utils.convertNullToEmptyString(objArrOne[4]));
					jsnObject.put("REMARKS", this.utils.convertNullToEmptyString(objArrOne[5]));
					jsnObject.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objArrOne[6]));

					for (int i = 0; i < loanRecoverydetailsList.size(); i++) {

						Object[] objectArr = loanRecoverydetailsList.get(i);
						JSONObject jsnObj = new JSONObject();

						jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[3]));
						jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[2]));
						jsnObj.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objectArr[6]));
						jsnObj.put("PAID_AMOUNT", this.utils.convertNullToEmptyString(objectArr[7]));
						jsnObj.put("PAID_DATE", this.utils.convertNullToEmptyString(objectArr[8]));
						jsnObj.put(RECEIPT_NO, this.utils.convertNullToEmptyString(objectArr[9]));
						jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(objectArr[10]));
						jsnObj.put("STATUS", this.utils.convertNullToEmptyString(objectArr[11]));
						jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[0]));
						jsnObj.put("MEMBER_ID", this.utils.convertNullToEmptyString(objectArr[1]));

						loanRecoveryDetalsArr.add(jsnObj);
					}

					topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "400");
					topPanelResultObj.put("LOAN_DETAILS", loanDetalsArr);
					topPanelResultObj.put("LOAN_RECOVERY_DETAILS", loanRecoveryDetalsArr);
				} else {
					topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "200");
					topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG",
							" No loan records found with for Loanid =" + currentloanIdFromRegister);

				}

			}

		} catch (Exception e) {

			topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
			topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return topPanelResultObj.toJSONString();
	}

	@Transactional
	public String loanPaymentValidation(JSONObject obj) {
		String result = "Sorry Registration Failed !";

		try {
			if (obj != null) {
				String cardNo = (String) obj.get("CARD_NO");
				String deptId = (String) obj.get("DEPT_ID");
				String paidAmount = (String) obj.get("PAID_AMOUNT");
				String paidDate = (String) obj.get("PAID_DATE");
				String receiptNo = (String) obj.get(RECEIPT_NO);
				if (receiptNo == null || "".equalsIgnoreCase(receiptNo)) {
					return "Please Enter ReceiptNo ";
				}
				if (paidDate == null || "".equalsIgnoreCase(paidDate)) {
					return "Please Select Paid Date Date.";
				}
				if (cardNo == null || "".equalsIgnoreCase(cardNo)) {
					return "Please Select Card No.";
				}
				if (deptId == null || "".equalsIgnoreCase(deptId) || "SELECT".equalsIgnoreCase(deptId)) {
					return "Please select member department.";
				}

				if (!this.utils.isValidDate(paidDate)) {
					return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
				}

				if (!this.utils.isNumericString(paidAmount)) {
					return "Please enter only numbers in PaidAmount";
				}

				if (this.miscellaneousDAO.isDuplicateReceiptNumberInRegistration(receiptNo)) {
					return "Receipt no already used!";
				}

				return "SUCCESS";

			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}
		return result;
	}

	@Transactional
	public boolean isLoanAlreadySanctioned(String memberId) {
		boolean isLoanAlreadySanctioned = false;

		try {
			if (memberId != null && !"".equalsIgnoreCase(memberId)) {
				String query = " from Loandetails where loandetailsPK.memberId=:memberId and loanStatus=:loanStatus";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("memberId", memberId.trim());
				parameters.put("loanStatus", "UNDER_REOCVERY");

				List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);
				if (loandetailsList != null && loandetailsList.size() > 0) {
					isLoanAlreadySanctioned = true;
				}
			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return isLoanAlreadySanctioned;
	}

	@Transactional
	public String memberLoanStatusFromLoanDetials(String memberId, String loanId) {
		String loanStatus = "";

		try {
			if (memberId != null && !"".equalsIgnoreCase(memberId) && loanId != null && !"".equalsIgnoreCase(loanId)) {
				String query = " from Loandetails where loandetailsPK.memberId=:memberId and LoandetailsPK.loanId=:loanId";
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("memberId", memberId.trim());
				parameters.put("loanId", loanId);

				List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);
				if (loandetailsList != null && loandetailsList.size() > 0) {
					Loandetails loanDetials = loandetailsList.get(0);
					loanStatus = loanDetials.getLoanStatus();
				}

			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return loanStatus;
	}

	@Transactional
	public JSONObject updateLoanSanctionDetailsFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();

		try {
			String cardNo = request.getParameter("update_LoanSanctionDetails_cardNo");
			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("update_LoanSanctionDetails_deptId");
			String pageId = request.getParameter("update_LoanSanctionDetails_pageId");
			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return resultObj;
	}

	@Transactional
	public JSONObject updateLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();

		try {
			String cardNo = request.getParameter("update_LoanRecoveryDetails_cardNo");

			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("update_LoanRecoveryDetails_deptId");
			String pageId = request.getParameter("update_LoanRecoveryDetails_pageId");

			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return resultObj;
	}

	@Transactional
	public JSONObject deleteLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();
		try {
			String cardNo = request.getParameter("delete_LoanRecoveryDetails_cardNo");

			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("delete_LoanRecoveryDetails_deptId");
			String pageId = request.getParameter("delete_LoanRecoveryDetails_pageId");

			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}

		return resultObj;
	}

	@Transactional
	public LoanRecoveryDetails getLoanRecoveryDetails(String memberId, String transId, String loanId) {
		LoanRecoveryDetails loanRecoveryDetails = null;
		try {
			String query = "from LoanRecoveryDetails where loanRecoveryDetailsPK.memberId =:memberId and loanRecoveryDetailsPK.loanId =:loanId  and loanRecoveryDetailsPK.transactionId =:transactionId";
			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("memberId", memberId);
			parametersMap.put("transactionId", transId);
			parametersMap.put("loanId", loanId);
			List<LoanRecoveryDetails> list = this.dataAccess.queryWithParams(query, parametersMap);
			if (list != null && list.size() > 0) {
				loanRecoveryDetails = list.get(0);
			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
		}
		return loanRecoveryDetails;
	}

	@Transactional
	public String updateLoanRecoveryDetails(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");
			String paidDate = request.getParameter("paidDate");
			String paidAmount = request.getParameter("paidAmount");
			String receiptNo = request.getParameter("receiptNo");
			String remarks = request.getParameter("remarks");
			String loanId = request.getParameter("loanId");
			String cardNo = request.getParameter("cardNo");
			String deptId = request.getParameter("deptId");

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {
				JSONObject validateJsnObj = new JSONObject();
				validateJsnObj.put("MEMBER_ID", memberId);
				validateJsnObj.put("CARD_NO", cardNo);
				validateJsnObj.put("DEPT_ID", deptId);
				validateJsnObj.put("PAID_AMOUNT", paidAmount);
				validateJsnObj.put("PAID_DATE", paidDate);
				validateJsnObj.put(RECEIPT_NO, receiptNo);
				String validationMessage = loanPaymentValidation(validateJsnObj);

				if (!"".equals(validationMessage) && "SUCCESS".equalsIgnoreCase(validationMessage)) {
					String updateQuery = "update LoanRecoveryDetails set  paidDate=:paidDate , receiptNo=:receiptNo ,  paidAmount=:paidAmount ,  remarks=:remarks   where loanRecoveryDetailsPK.transactionId=:transactionId and loanRecoveryDetailsPK.memberId=:memberId  and loanRecoveryDetailsPK.loanId=:loanId";

					Map<String, Object> parametersMap = new HashMap<String, Object>();

					parametersMap.put("memberId", memberId);
					parametersMap.put("transactionId", transactionId);
					parametersMap.put("loanId", loanId);
					parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
					parametersMap.put("receiptNo", receiptNo);
					parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
					parametersMap.put("remarks", remarks);

					this.dataAccess.updateQuery(updateQuery, parametersMap);

					result.put("FINAL_RESULT_CODE", "400");
					result.put("DATA_DETAILS", "LoanRecoveryDetails Upadated Sucessfullty!");

				} else {

					result.put("FINAL_RESULT_CODE", "200");
					result.put("ERROR_MSG", validationMessage);
				}
				updateLoanBalance(cardNo, deptId);
			} else {
				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(this.getClass(), nfe.getMessage(), nfe);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		}

		return result.toString();
	}

	@Transactional
	public String updateSanctionDetails(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String loanId = request.getParameter("loanId");
			String loanAmount = request.getParameter("loanAmount");
			String loanSanctionedDate = request.getParameter("loanSanctionedDate");

			Date date1 = (new SimpleDateFormat("dd/MM/yyyy")).parse(loanSanctionedDate);

			String memberId = request.getParameter("memberId");
			if (loanAmount != null && !"".equals(loanAmount) && loanSanctionedDate != null
					&& !"".equals(loanSanctionedDate)) {

				String updateQuery = "update Loandetails set  loanSanctionedDate=:loanSanctionedDate , loanAmount=:loanAmount  where loandetailsPK.memberId=:memberId and loandetailsPK.loanId=:loanId ";

				Map<String, Object> parametersMap = new HashMap<String, Object>();
				parametersMap.put("memberId", memberId);
				parametersMap.put("loanId", loanId);
				parametersMap.put("loanSanctionedDate", date1);
				parametersMap.put("loanAmount", Integer.valueOf(Integer.parseInt(loanAmount)));

				this.dataAccess.updateQuery(updateQuery, parametersMap);

				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "LoanSanctionDetails Upadated Sucessfullty!");

			} else {

				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
			}
			updateLoanBalance(memberId);

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(this.getClass(), nfe.getMessage(), nfe);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		}

		return result.toString();
	}

	@Transactional
	public String deleteLoanRecoveryDetails(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");
			String paidDate = request.getParameter("paidDate");
			String paidAmount = request.getParameter("paidAmount");
			String receiptNo = request.getParameter("receiptNo");
			String remarks = request.getParameter("remarks");
			String loanId = request.getParameter("loanId");

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {
				String updateQuery = "delete from LoanRecoveryDetails   where loanRecoveryDetailsPK.transactionId=:transactionId and loanRecoveryDetailsPK.memberId=:memberId  and loanRecoveryDetailsPK.loanId=:loanId";

				Map<String, Object> parametersMap = new HashMap<String, Object>();

				parametersMap.put("memberId", memberId);
				parametersMap.put("transactionId", transactionId);
				parametersMap.put("loanId", loanId);
				parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
				parametersMap.put("receiptNo", receiptNo);
				parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
				parametersMap.put("remarks", remarks);
				this.dataAccess.updateQueryByCount(updateQuery, parametersMap);

				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "LoanRecoveryDetails Upadated Sucessfullty!");
			} else {
				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
			}
			updateLoanBalance(memberId);

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(this.getClass(), nfe.getMessage(), nfe);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		}

		return result.toJSONString();
	}

	@Transactional
	public String deleteLoanSanctionDetails(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String memberId = request.getParameter("memberId");

			String loanId = request.getParameter("loanId");

			if (memberId != null && !"".equals(memberId) && loanId != null && !"".equals(loanId)) {
				String updateQuery = "delete from Loandetails   where loandetailsPK.loanId=:loanId and loandetailsPK.memberId=:memberId  ";

				Map<String, Object> parametersMap = new HashMap<String, Object>();

				parametersMap.put("memberId", memberId);
				parametersMap.put("loanId", loanId);

				this.dataAccess.updateQueryByCount(updateQuery, parametersMap);

				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "LoanSanctionDetails deleted Sucessfullty!");
			} else {
				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
			}
			updateLoanBalance(memberId);

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(this.getClass(), nfe.getMessage(), nfe);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(), e.getMessage(), e);
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		}

		return result.toJSONString();
	}

	public String getLoanSanctionedDetails(String deptId, String cardNo) {
		return "";
	}

	public void updateLoanBalance(String memberId) {

		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall("{call UPDATE_LOAN_BALANCE_BY_MEMBER_ID(?)}");

		) {

			cs.setString(1, memberId);
			cs.execute();
		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(), ex.getMessage(), ex);
		}
	}

	public void updateLoanBalance(String cardNo, String deptId) {

		try (Connection conn = dataAccess.getConnection();
				CallableStatement cs = conn.prepareCall("{call UPDATE_LOAN_BALANCE(?,?)}");

		) {
			cs.setString(1, deptId);
			cs.setString(2, cardNo);
			cs.execute();

		} catch (SQLException ex) {
			ApplicationUtilities.error(this.getClass(), ex.getMessage(), ex);
		}
	}
}