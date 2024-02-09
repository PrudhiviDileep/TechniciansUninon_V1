package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class SearchDAO {
	@Autowired
	public DataAccess dataAccess;

	@Autowired
	public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO registerDAO;

	@Autowired
	public MiscellaneousDAO miscDAO;

	@Autowired
	public LoanDAO loanDAO;

	@Autowired
	ServletContext context;

	Utils utils = new Utils();
	public static final String TABLE_BODY_START = "<tbody>";
	public static final String TABLE_BODY_CLOSE = "</tbody>";
	public static final String TD_CLOSE = "</td>";
	public static final String TD_START = "<td>";
	public static final String TABLE_CLOSE = "</table>";
	public static final String TOP_PANEL_RESULT_CODE = "TOP_PANEL_RESULT_CODE";
	public static final String FINAL_RESULT_CODE = "FINAL_RESULT_CODE";
	public static final String OTHER_PANEL_ERROR_MSG = "OTHER_PANEL_ERROR_MSG";
	public static final String OTHER_PANEL_RESULT_CODE = "OTHER_PANEL_RESULT_CODE";
	public static final String TOP_PANEL_ERROR_MSG = "TOP_PANEL_ERROR_MSG";
	public static final String CARD_BALANCE_PAYMENT = "CARD_BALANCE_PAYMENT";
	public static final String PAID_AMOUNT = "PAID_AMOUNT";

	public static final String RESULT_CODE_200 = "200";
	public static final String RESULT_CODE_300 = "300";
	public static final String ERROR_MSG = "ERROR_MSG";
	public static final String TOP_PANEL_DETAILS = "TOP_PANEL_DETAILS";
	public static final String RESULT_CODE_400 = "400";
	public static final String RECEIPT_NO = "RECEIPT_NO";
	public static final String MEMBER_ID = "MEMBER_ID";
	public static final String PAYMENT_CONF_ID = "PAYMENT_CONF_ID";
	public static final String LOAN_SUMMARY = "LOAN_SUMMARY";
	public static final String DATA_DETAILS = "DATA_DETAILS";
	public static final String PAY_SUBSCRIPTION_AMOUNT = "PAY_SUBSCRIPTION_AMOUNT";
	public static final String DATA_DETAILS1 = "DATA_DETAILS1";
	public static final String GET_SCUBSRIPTION_DETAILS = "GET_SCUBSRIPTION_DETAILS";
	public static final String FATHER_NAME = "FATHER_NAME";
	public static final String DEPT_ID = "DEPT_ID";
	public static final String LOAN_AMOUNT = "LOAN_AMOUNT";
	public static final String LOAN_PAYMENT = "LOAN_PAYMENT";
	public static final String LOAN_SANCTION = "LOAN_SANCTION";
	public static final String PAGE_ID = "PAGE_ID";
	public static final String NO_DATA_FOUND = "No data found !";

	public String search(HttpServletRequest request, Map<String, Object> model) {
		return "search";
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public JSONObject getCommonSearchResults(HttpServletRequest request) {
		JSONObject finalResultObj = new JSONObject();
		JSONObject otherPanelResultObj = new JSONObject();
		JSONObject topPanelResultObj = new JSONObject();
		String pageId = request.getParameter("pageId");
		String cardNo = request.getParameter("cardNo");
		String deptId = request.getParameter("deptId");
		try {
			int cardNumber = Integer.parseInt(cardNo);
			if (pageId != null && !"".equalsIgnoreCase(pageId) && cardNo != null && !"".equalsIgnoreCase(cardNo)) {
				pageId = pageId.trim();
				finalResultObj.put(PAGE_ID, pageId);
				Registration registredMember = this.miscDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
				if (!"UPDATE_MEMBER_DETAILS".equalsIgnoreCase(pageId)) {
					otherPanelResultObj = new JSONObject();
					topPanelResultObj = new JSONObject();
					topPanelResultObj = this.miscDAO.getTopPanel(cardNumber, deptId, pageId);
					String topPanelResultCode = (String) topPanelResultObj.get(TOP_PANEL_RESULT_CODE);
					if (topPanelResultCode != null && !"".equalsIgnoreCase(topPanelResultCode))
						if (topPanelResultCode.equalsIgnoreCase(RESULT_CODE_200)
								|| topPanelResultCode.equalsIgnoreCase(RESULT_CODE_300)) {
							finalResultObj.put(FINAL_RESULT_CODE, topPanelResultCode);
							finalResultObj.put(ERROR_MSG, topPanelResultObj.get(TOP_PANEL_ERROR_MSG));
							finalResultObj.put(TOP_PANEL_DETAILS, topPanelResultObj);
						} else if (topPanelResultCode.equalsIgnoreCase(RESULT_CODE_400)) {
							JSONObject obj = this.utils.requestParamsToJSON((ServletRequest) request);
							if (CARD_BALANCE_PAYMENT.equalsIgnoreCase(pageId)) {
								otherPanelResultObj = (JSONObject) JSONValue.parse(getDetialsByPageId(obj));
								finalResultObj.put("MEMBERSHIP_AMOUNT",
										Integer.valueOf(registredMember.getMembershipAmount()));
								finalResultObj.put(PAID_AMOUNT, Integer.valueOf(registredMember.getPaidAmount()));
								finalResultObj.put("BALANCE_AMOUNT", registredMember.getCardBalance());
								finalResultObj.put(RECEIPT_NO, registredMember.getReceiptNo());
								finalResultObj.put(MEMBER_ID, registredMember.getRegistrationPK().getMemberId());
								finalResultObj.put(PAYMENT_CONF_ID, this.utils.convertPaymentConfigDetailsToHTMLSelect(
										this.miscDAO.getPaymentConfigDetialsForSelect(deptId, "REGISTRATION")));
							} else if (PAY_SUBSCRIPTION_AMOUNT.equalsIgnoreCase(pageId)
									|| GET_SCUBSRIPTION_DETAILS.equalsIgnoreCase(pageId)) {
								otherPanelResultObj = (JSONObject) JSONValue.parse(getDetialsByPageId(obj));
								if (PAY_SUBSCRIPTION_AMOUNT.equalsIgnoreCase(pageId)) {
									finalResultObj.put("SUBSCRIPTION_YEAR",
											this.utils.setSubscribedYearsSelectedToCurrentYear());
									finalResultObj.put(PAYMENT_CONF_ID,
											this.utils.convertPaymentConfigDetailsToHTMLSelect(this.miscDAO
													.getPaymentConfigDetialsForSelect(deptId, "SUBSCRIPTION")));
								}
							} else if (LOAN_SANCTION.equalsIgnoreCase(pageId)) {
								otherPanelResultObj = (JSONObject) JSONValue.parse(getDetialsByPageId(obj));
							} else if (LOAN_PAYMENT.equalsIgnoreCase(pageId)) {
								otherPanelResultObj = (JSONObject) JSONValue.parse(getDetialsByPageId(obj));
							} else if (LOAN_SUMMARY.equalsIgnoreCase(pageId)) {
								otherPanelResultObj = (JSONObject) JSONValue.parse(getDetialsByPageId(obj));
							}
							if (otherPanelResultObj != null && !otherPanelResultObj.isEmpty()
									&& otherPanelResultObj.get(OTHER_PANEL_RESULT_CODE) != null) {
								String otherPanelResultCode = (String) otherPanelResultObj.get(OTHER_PANEL_RESULT_CODE);
								finalResultObj.put(TOP_PANEL_DETAILS, topPanelResultObj);
								if (otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_300)) {
									finalResultObj.put(ERROR_MSG, topPanelResultObj.get(OTHER_PANEL_ERROR_MSG));
								} else if (otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_200)
										|| otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_400)) {
									finalResultObj.put(pageId, otherPanelResultObj.get(DATA_DETAILS));
									if (LOAN_SUMMARY.equalsIgnoreCase(pageId))
										finalResultObj.put("LOAN_DETAILS", otherPanelResultObj.get(DATA_DETAILS1));
								}
								finalResultObj.put(FINAL_RESULT_CODE, otherPanelResultCode);
							} else {
								finalResultObj.put(FINAL_RESULT_CODE, RESULT_CODE_400);
								finalResultObj.put(TOP_PANEL_DETAILS, topPanelResultObj);
								finalResultObj.put(pageId, "");
							}
						}
				} else if ("UPDATE_MEMBER_DETAILS".equalsIgnoreCase(pageId)) {
					otherPanelResultObj = new JSONObject();
					if (registredMember != null) {
						otherPanelResultObj = this.registerDAO
								.getMemberDetails(registredMember.getRegistrationPK().getMemberId());
						String otherPanelResultCode = (String) otherPanelResultObj.get(FINAL_RESULT_CODE);
						finalResultObj.put(FINAL_RESULT_CODE, otherPanelResultCode);
						if (otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_200)
								|| otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_300)) {
							finalResultObj.put(ERROR_MSG, topPanelResultObj.get(ERROR_MSG));
						} else if (otherPanelResultCode.equalsIgnoreCase(RESULT_CODE_400)) {
							finalResultObj.put(pageId, otherPanelResultObj.get(DATA_DETAILS));
						}
					} else {
						finalResultObj.put(FINAL_RESULT_CODE, RESULT_CODE_200);
						finalResultObj.put(ERROR_MSG, new StringBuilder("No registered member found with  deptId =")
								.append(deptId).append(" and  cardNo =").append(cardNo).toString());
						finalResultObj.put(PAGE_ID, pageId);
					}
				} else {
					finalResultObj.put(FINAL_RESULT_CODE, RESULT_CODE_300);
					finalResultObj.put(ERROR_MSG,
							new StringBuilder(
									"Unable to perform any action due to 'OUT OF SCOPE OPERATION REQUEST FROM >> '")
											.append(pageId).toString());
					finalResultObj.put(PAGE_ID, pageId);
				}
			} else {
				finalResultObj.put(FINAL_RESULT_CODE, RESULT_CODE_300);
				finalResultObj.put(ERROR_MSG, " No Operation specified ! ");
				finalResultObj.put(PAGE_ID, pageId);
			}
		} catch (Exception e) {
			finalResultObj.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			finalResultObj.put(ERROR_MSG,
					new StringBuilder("Technical error occured while performing action, the detials are ")
							.append(e.getMessage()).toString());
			finalResultObj.put(PAGE_ID, pageId);
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return finalResultObj;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String getDetialsByPageId(JSONObject items) {
		JSONObject resultObj = new JSONObject();
		int colCount = 0;
		StringBuilder table = new StringBuilder("");
		table.append("<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>");
		table.append(
				"<thead><tr><th>DATE</th><th>NAME</th><th>FATHER NAME</th><th>DEPT ID</th><th>CARD NO</th><th>AMOUNT</th><th>Update</th><th>Delete</th></tr></thead>");
		StringBuilder theadStr = new StringBuilder(
				"<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'><thead><tr><th align='center'>SNo</th>");
		StringBuilder tbody = new StringBuilder(TABLE_BODY_START);
		List list = null;
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String pageId = (String) items.get("pageId");
			String cardNo = (String) items.get("cardNo");
			String deptId = (String) items.get("deptId");
			Registration registeredMember = this.miscDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
			String memberId = registeredMember.getRegistrationPK().getMemberId();
			StringBuilder query = new StringBuilder(" from ");
			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("memberId", memberId);
			if (CARD_BALANCE_PAYMENT.equalsIgnoreCase(pageId)) {
				colCount = 7;
				theadStr.append(
						"<th align='left'>Paid Amt</th><th align='left'>Reciept No</th><th align='left'>Date of Payment</th><th align='left'>Remarks</th><th align='left'>Update</th><th align='left'>Delete</th>");
				query.append(" MembershipPayments where membershipPaymentsPK.memberId=:memberId order by paidDate ");
			} else if (PAY_SUBSCRIPTION_AMOUNT.equalsIgnoreCase(pageId)
					|| GET_SCUBSRIPTION_DETAILS.equalsIgnoreCase(pageId)) {
				colCount = 10;
				theadStr.append(
						"<th align='left'>Description</th><th align='left'>Subscriptoin Amt</th><th align='left'>Paid Amt</th><th align='left'>Receipt No</th><th align='left'>Year</th><th align='left'>Status</th><th align='left'>Paid Date</th>");
				theadStr.append("<th align='left'>Update</th><th align='left'>Delete</th>");
				query.append(
						" SubscriptionPayments where memberId=:memberId and category='SUBSCRIPTION' order by paidDate ");
			} else if (LOAN_PAYMENT.equalsIgnoreCase(pageId) || LOAN_SUMMARY.equalsIgnoreCase(pageId)) {
				colCount = 8;
				theadStr.append(
						"<th align='left'>Receipt No</th><th align='left'>Date of Payment</th><th align='left'>Paid Amount</th><th align='left'>Particulars</th><th>Update</th><th>Delete</th>");
				String str = this.loanDAO.getLoanDetails(memberId);
				JSONObject loandetails = new JSONObject();
				if (str != null && !"".equalsIgnoreCase(str)) {
					loandetails = (JSONObject) JSONValue.parse(str);
					String lonDetilsResultcode = (String) loandetails.get("LOAN_DETAILS_RESULT_CODE");
					if (lonDetilsResultcode != null && !"".equalsIgnoreCase(lonDetilsResultcode))
						if (lonDetilsResultcode.equalsIgnoreCase(RESULT_CODE_200)
								|| lonDetilsResultcode.equalsIgnoreCase(RESULT_CODE_300)) {
							tbody.append("<tr><td colspan='").append(colCount).append("' align='center'> ")
									.append(NO_DATA_FOUND).append("</td></tr>");
							tbody.append(TABLE_BODY_CLOSE);
							resultObj.put(OTHER_PANEL_RESULT_CODE, lonDetilsResultcode);
							resultObj.put(OTHER_PANEL_ERROR_MSG, loandetails.get("LOAN_DETAILS_ERROR_MSG"));
							resultObj.put(DATA_DETAILS, theadStr.append(tbody).append(TABLE_CLOSE).toString());
						} else if (lonDetilsResultcode.equalsIgnoreCase(RESULT_CODE_400)) {
							JSONArray jsnArry = (JSONArray) loandetails.get("LOAN_RECOVERY_DETAILS");
							JSONArray loanDetailsArr = (JSONArray) loandetails.get("LOAN_DETAILS");
							if (loanDetailsArr != null && loanDetailsArr.size() > 0) {
								table.append(TABLE_BODY_START);
								for (int i = 0; i < loanDetailsArr.size(); i++) {
									JSONObject oobj = (JSONObject) loanDetailsArr.get(i);
									String ccc = this.utils.convertNullToEmptyString(oobj.get("LOAN_SANCTIONED_DATE"));
									String[] date = ccc.split("-");
									table.append("<tr>").append(TD_START).append(date[2]).append("/").append(date[1])
											.append("/").append(date[0]).append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(oobj.get("FIRST_NAME")))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(oobj.get(FATHER_NAME)))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(oobj.get(DEPT_ID)))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(oobj.get("CARD_NO")))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(oobj.get(LOAN_AMOUNT)))
											.append(TD_CLOSE)

											.append("<td align='left'><form action='updateLoanSanctionDetailsForm' method='POST'  ><input type='hidden' name='update_LoanSanctionDetails_memberId' id='del_LoanSancitonDetails_memberId' value='")
											.append(memberId).append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_loanId' id='del_LoanSancitonDetails_loanId'")
											.append(" value='")
											.append(this.utils.convertNullToEmptyString(oobj.get("LOAN_ID")))
											.append("' >")
											.append("<input type='hidden' name='update_LoanSanctionDetails_loanSanctionDate' id='del_LoanSancitonDetails_loanSanctionDate'")
											.append(" value='").append(date[2]).append("/").append(date[1]).append("/")
											.append(date[0]).append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_cardNo' id='del_LoanSancitonDetails_cardNo' value='")
											.append(cardNo).append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_deptId' id='del_LoanSancitonDetails_deptId' value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(DEPT_ID))).append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_loanAmount' id='del_LoanSancitonDetails_loanAmount' ")
											.append("value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(LOAN_AMOUNT)))
											.append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_fatherName' id='del_LoanSancitonDetails_fatherName' ")
											.append(" value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(FATHER_NAME)))
											.append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_pageId' id='del_LoanSancitonDetails_pageId' ")
											.append("value='").append("UPDATE_LOAN_SANCTION").append("' >")
											.append("<input type='submit' value='Update'>").append("</form></td>")
											.append("<td align='left'><form action='deleteLoanSanctionDetailsForm' method='POST'  ><input type='hidden' name='update_LoanSanctionDetails_memberId' id='del_LoanSancitonDetails_memberId' value='")
											.append(memberId)
											.append("'>").append("<input type='hidden' name='update_LoanSanctionDetails_loanId' id='del_LoanSancitonDetails_loanId'")
											.append(" value='")
											.append(this.utils.convertNullToEmptyString(oobj.get("LOAN_ID")))
											.append("' >")
											.append("<input type='hidden' name='update_LoanSanctionDetails_loanSanctionDate' id='del_LoanSancitonDetails_loanSanctionDate'")
											.append(" value='").append(date[2]).append("/").append(date[1]).append("/")
											.append(date[0]).append("'>").append(

													"<input type='hidden' name='delete_LoanRecoveryDetails_cardNo' id='del_LoanSancitonDetails_cardNo' value='")
											.append(cardNo).append("'>").append(

													"<input type='hidden' name='delete_LoanRecoveryDetails_deptId' id='del_LoanSancitonDetails_deptId' value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(DEPT_ID))).append("'>")
											.append(

													"<input type='hidden' name='update_LoanSanctionDetails_loanAmount' id='del_LoanSancitonDetails_loanAmount' ")
											.append("value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(LOAN_AMOUNT)))
											.append("'>")
											.append("<input type='hidden' name='update_LoanSanctionDetails_fatherName' id='del_LoanSancitonDetails_fatherName' ")
											.append(" value='")
											.append(this.utils.convertNullToEmptyString(oobj.get(FATHER_NAME)))
											.append("'>")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_pageId' id='del_LoanSancitonDetails_pageId' ")
											.append("value='").append("DELETE_LOAN_SANCTION").append("' >")
											.append("<input type='submit' value='Delete'>").append("</form></td>")
											.append("</tr>");
								}
								table.append(TABLE_BODY_CLOSE);
							} else {
								table.append(
										"<tbody><tr><td colspan='6' align='center'> No Data found</td></tr></tbody>");
							}
							table.append(TABLE_CLOSE);
							int totalPaidAmount = 0;
							int totalLoanAmount = this.miscDAO.getTotalLoanAmount(memberId);
							if (jsnArry != null && jsnArry.size() > 0) {
								int old_bal = totalLoanAmount;
								for (int i = 0; i < jsnArry.size(); i++) {
									JSONObject jsnObj = (JSONObject) jsnArry.get(i);
									int paidAmount = Integer.parseInt((String) jsnObj.get(PAID_AMOUNT));
									old_bal -= paidAmount;
									tbody.append("<tr><td>").append((i + 1)).append(TD_CLOSE);
									tbody.append(TD_START)
											.append(this.utils.convertNullToEmptyString(jsnObj.get(RECEIPT_NO)))
											.append(TD_CLOSE).append(TD_START).append((String) jsnObj.get("PAID_DATE"))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(jsnObj.get(PAID_AMOUNT)))
											.append(TD_CLOSE).append(TD_START)
											.append(this.utils.convertNullToEmptyString(jsnObj.get("REMARKS")))
											.append(TD_CLOSE)
											.append("<td align='left'><form action='updateLoanRecoveryDetailsForm' method='POST'  ><input type='hidden' name='update_LoanRecoveryDetails_memberId' id='upd_LoanRecoveryDetails_memberId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get(MEMBER_ID)))
											.append("'>")
											.append("<input type='hidden' name='update_LoanRecoveryDetails_transId' id='upd_LoanRecoveryDetails_transId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get("TRANSACTION_ID")))
											.append("' >")
											.append("<input type='hidden' name='update_LoanRecoveryDetails_loanId' id='upd_LoanRecoveryDetails_loanId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get("LOAN_ID")))
											.append("' >")
											.append("<input type='hidden' name='update_LoanRecoveryDetails_cardNo' id='upd_LoanRecoveryDetails_cardNo' value='")
											.append(cardNo).append("'>")
											.append("<input type='hidden' name='update_LoanRecoveryDetails_deptId' id='upd_LoanRecoveryDetails_deptId' value='")
											.append(deptId).append("'>")
											.append("<input type='hidden' name='update_LoanRecoveryDetails_pageId' id='upd_LoanRecoveryDetails_pageId' value='")
											.append(pageId).append("' >").append("<input type='submit' value='Update'>")
											.append("</form></td>")
											.append("<td align='left'><form action='deleteLoanRecoveryDetailsForm' method='POST'  ><input type='hidden' name='delete_LoanRecoveryDetails_memberId' id='del_LoanRecoveryDetails_memberId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get(MEMBER_ID)))
											.append("'>")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_transId' id='del_LoanRecoveryDetails_transId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get("TRANSACTION_ID")))
											.append("' >")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_loanId' id='del_LoanRecoveryDetails_loanId' value='")
											.append(this.utils.convertNullToEmptyString(jsnObj.get("LOAN_ID")))
											.append("' >")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_cardNo' id='del_LoanRecoveryDetails_cardNo' value='")
											.append(cardNo).append("'>")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_deptId' id='del_LoanRecoveryDetails_deptId' value='")
											.append(deptId).append("'>")
											.append("<input type='hidden' name='delete_LoanRecoveryDetails_pageId' id='del_LoanRecoveryDetails_pageId' value='")
											.append(pageId).append("' >").append("<input type='submit' value='Delete'>")
											.append("</form></td></tr>").append(totalPaidAmount += Integer.parseInt(
													this.utils.convertNullToEmptyString(jsnObj.get(PAID_AMOUNT))));
								}
								theadStr.append("</tr></thead>");
								resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_400);
								resultObj.put(DATA_DETAILS, new StringBuilder(String.valueOf(String.valueOf(theadStr)))
										.append(tbody).append(TABLE_CLOSE).toString());
								table.append(
										"<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>")
										.append("<thead><tr><th>TOTAL LOAN AMOUNT</th><th>TOTAL PAID AMOUNT</th><th>BALANCE</th></tr></thead>")
										.append("<tbody><tr><td align='center'>").append(totalLoanAmount)
										.append("</td><td align='center'>").append(totalPaidAmount)
										.append("</td><td align='center'>").append((totalLoanAmount - totalPaidAmount))
										.append("</td></tr></tbody></table>");
								resultObj.put(DATA_DETAILS1, table.toString());
							} else {
								tbody.append(String.valueOf(String.valueOf(tbody))).append("<tr><td colspan='")
										.append((colCount + 2)).append("' align='center'> ").append(NO_DATA_FOUND)
										.append("</td></tr>");
								tbody.append(String.valueOf(String.valueOf(tbody))).append(TABLE_BODY_CLOSE);
								resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_200);
								resultObj.put(OTHER_PANEL_ERROR_MSG, NO_DATA_FOUND);
								resultObj.put(DATA_DETAILS, theadStr.append(tbody).append(TABLE_CLOSE).toString());
								table.append(
										"<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>");
								table.append(
										"<thead><tr><th>TOTAL LOAN AMOUNT</th><th>TOTAL PAID AMOUNT</th><th>BALANCE</th></tr></thead>");
								table.append("<tbody><tr><td align='center'>").append(totalLoanAmount)
										.append("</td><td align='center'>").append(totalPaidAmount)
										.append("</td><td align='center'>").append((totalLoanAmount - totalPaidAmount))
										.append("</td></tr></tbody></table>");
								resultObj.put(DATA_DETAILS1, table.toString());
							}
						}
				} else {

					tbody.append("<tr><td colspan='").append(colCount).append("' align='center'> ")
							.append(NO_DATA_FOUND).append("</td></tr>");

					tbody.append(TABLE_BODY_CLOSE);
					resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_200);
					resultObj.put(OTHER_PANEL_ERROR_MSG, NO_DATA_FOUND);
					resultObj.put(DATA_DETAILS, new StringBuilder(String.valueOf(String.valueOf(theadStr)))
							.append(tbody).append(TABLE_CLOSE).toString());
					table.append("<tbody><tr><td colspan='6' align='center'> No Data found</td></tr></tbody></table>");
					resultObj.put(DATA_DETAILS1, table.toString());
				}
			}
			if (!LOAN_PAYMENT.equalsIgnoreCase(pageId) && !LOAN_SANCTION.equalsIgnoreCase(pageId)
					&& !LOAN_SUMMARY.equalsIgnoreCase(pageId)) {
				list = this.dataAccess.queryWithParams(String.valueOf(query), parametersMap);
				theadStr.append("</tr></thead>");
				if (list != null && list.size() > 0) {
					int k = 1;
					for (int j = 0; j < list.size(); j++) {
						tbody = new StringBuilder(String.valueOf(String.valueOf(tbody)))
								.append("<tr><td align='center' >").append(k).append(TD_CLOSE);
						if (pageId.equalsIgnoreCase(CARD_BALANCE_PAYMENT)) {
							MembershipPayments membershipPayments = (MembershipPayments) list.get(j);
							tbody.append(TD_START).append(this.utils
									.convertNullToEmptyString(Integer.valueOf(membershipPayments.getPaidAmount())))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(membershipPayments.getReceiptNo()))
									.append(TD_CLOSE);
							tbody.append(TD_START).append(this.utils
									.convertNullToEmptyString(dateFormater.format(membershipPayments.getPaidDate())))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(membershipPayments.getRemarks()))
									.append(TD_CLOSE);
							tbody.append(
									"<td align='left'><form action='updateCardBalanceForm' method='POST'  ><input type='hidden' name='update_cardBalance_memberId' id='upd_cardBalance_memberId' value='")
									.append(membershipPayments.getMembershipPaymentsPK().getMemberId()).append("'>")
									.append("<input type='hidden' name='update_cardBalance_transId' id='upd_cardBalance_transId' value='")
									.append(membershipPayments.getMembershipPaymentsPK().getTransactionId())
									.append("' >")
									.append("<input type='hidden' name='update_cardBalance_cardNo' id='upd_cardBalance_cardNo' value='")
									.append(cardNo).append("'>")
									.append("<input type='hidden' name='update_cardBalance_deptId' id='upd_cardBalance_deptId' value='")
									.append(deptId).append("'>")
									.append("<input type='hidden' name='update_cardBalance_pageId' id='upd_cardBalance_pageId' value='")
									.append(pageId).append("' >").append("<input type='submit' value='Update'>")
									.append("</form></td>");
							tbody.append(
									"<td align='left'><form action='deleteCardBalanceForm' method='POST'  ><input type='hidden' name='delete_cardBalance_memberId' id='del_cardBalance_memberId' value='")
									.append(membershipPayments.getMembershipPaymentsPK().getMemberId()).append("'>")
									.append("<input type='hidden' name='delete_cardBalance_transId' id='del_cardBalance_transId' value='")
									.append(membershipPayments.getMembershipPaymentsPK().getTransactionId())
									.append("' >")
									.append("<input type='hidden' name='delete_cardBalance_cardNo' id='del_cardBalance_cardNo' value='")
									.append(cardNo).append("'>")
									.append("<input type='hidden' name='delete_cardBalance_deptId' id='del_cardBalance_deptId' value='")
									.append(deptId).append("'>")
									.append("<input type='hidden' name='delete_cardBalance_pageId' id='del_cardBalance_pageId' value='")
									.append(pageId).append("' >").append("<input type='submit' value='Delete'>")
									.append("</form></td>");
						} else if (pageId.equalsIgnoreCase(PAY_SUBSCRIPTION_AMOUNT)
								|| pageId.equalsIgnoreCase(GET_SCUBSRIPTION_DETAILS)) {
							SubscriptionPayments subscriptionPayments = (SubscriptionPayments) list.get(j);
							StringBuilder subscriptionStringFormat = new StringBuilder("Rs.")
									.append(subscriptionPayments.getSubscriptionAmount()).append("/").append("Rec No.")
									.append(subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo()).append("@")
									.append(dateFormater.format(subscriptionPayments.getPaidDate()));
							tbody.append(TD_START).append(this.utils.convertNullToEmptyString(subscriptionStringFormat))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(
											Integer.valueOf(subscriptionPayments.getSubscriptionAmount())))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(
											Integer.valueOf(subscriptionPayments.getSubscriptionAmount())))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(
											subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo()))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils.convertNullToEmptyString(Integer.valueOf(
											subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear())))
									.append(TD_CLOSE);
							tbody.append(TD_START)
									.append(this.utils
											.convertNullToEmptyString(subscriptionPayments.getPaymentStatus()))
									.append(TD_CLOSE);
							tbody.append(TD_START).append(this.utils
									.convertNullToEmptyString(dateFormater.format(subscriptionPayments.getPaidDate())))
									.append(TD_CLOSE);
							tbody.append(
									"<td align='left'><form action='updateSubcriptionsForm' method='POST'  ><input type='hidden' name='update_subscription_memberId' id='upd_subscrib_memberId' value='")
									.append(subscriptionPayments.getMemberId()).append("'>")
									.append("<input type='hidden' name='update_subscription_transId' id='upd_subscrib_transId' value='")
									.append(subscriptionPayments.getTransactionId()).append("' >")
									.append("<input type='hidden' name='update_subscription_cardNo' id='upd_subscrib_cardNo' value='")
									.append(cardNo).append("'>")
									.append("<input type='hidden' name='update_subscription_deptId' id='upd_subscrib_deptId' value='")
									.append(deptId).append("'>")
									.append("<input type='hidden' name='update_scubscription_pageId' id='upd_subscrib_pageId' value='")
									.append(pageId).append("' >").append("<input type='submit' value='Update'>")
									.append("</form></td>");
							tbody.append("<td align='left'><form action='deleteSubcriptionsForm' method='POST'  ><input type='hidden' name='delete_subscription_memberId' id='del_subscrib_memberId' value='")
									.append(subscriptionPayments.getMemberId()).append("'>")
									.append("<input type='hidden' name='delete_subscription_transId' id='del_subscrib_transId' value='")
									.append(subscriptionPayments.getTransactionId()).append("' >")
									.append("<input type='hidden' name='delete_subscription_cardNo' id='del_subscrib_cardNo' value='")
									.append(cardNo).append("'>")
									.append("<input type='hidden' name='delete_subscription_deptId' id='del_subscrib_deptId' value='")
									.append(deptId).append("'>")
									.append("<input type='hidden' name='delete_scubscription_pageId' id='del_subscrib_pageId' value='")
									.append(pageId).append("' >").append("<input type='submit' value='Delete'>")
									.append("</form></td>");
						}
						tbody.append("</tr>");
						k++;
					}
					tbody.append(TABLE_BODY_CLOSE);
					resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_400);
					resultObj.put(DATA_DETAILS, new StringBuilder(String.valueOf(String.valueOf(theadStr)))
							.append(tbody).append(TABLE_CLOSE).toString());
				} else {
					tbody.append("<tr><td colspan='").append(colCount).append("' align='center'> ")
							.append(NO_DATA_FOUND).append("</td></tr>");
					tbody.append(TABLE_BODY_CLOSE);
					resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_200);
					resultObj.put(OTHER_PANEL_ERROR_MSG, NO_DATA_FOUND);
					resultObj.put(DATA_DETAILS, new StringBuilder(String.valueOf(String.valueOf(theadStr)))
							.append(tbody).append(TABLE_CLOSE).toString());
				}
			}
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
			tbody.append("<tr><td colspan='").append(colCount).append("' align='center'> ").append(NO_DATA_FOUND)
					.append("</td></tr>");
			tbody.append(TABLE_BODY_CLOSE);
			resultObj.put(OTHER_PANEL_RESULT_CODE, RESULT_CODE_300);
			resultObj.put(OTHER_PANEL_ERROR_MSG, e.getMessage());
			tbody.append("<tr><td colspan='").append(colCount)
					.append("' align='center'>Unable to fetch records!</td></tr>");
			tbody.append(TABLE_BODY_CLOSE);
			resultObj.put(DATA_DETAILS, new StringBuilder(String.valueOf(String.valueOf(theadStr))).append(tbody)
					.append(TABLE_CLOSE).toString());
		}
		return resultObj.toJSONString();
	}

	@Transactional
	public List searchDetails(String deptId) {
		List searchDetailsList = null;
		try {
			String getRegistrationQuery = "from Registration where registrationPK.deptId =:deptId ";
			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("deptId", deptId);
			searchDetailsList = this.dataAccess.queryWithParams(getRegistrationQuery, parametersMap);
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return searchDetailsList;
	}
}
