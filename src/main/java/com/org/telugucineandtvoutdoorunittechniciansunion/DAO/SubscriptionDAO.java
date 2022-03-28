package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

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

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPaymentsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class SubscriptionDAO {
	public static final String RECEIPT_NO = "RECEIPT_NO";
	@Autowired
	public DataAccess dataAccess;
	@Autowired
	public SearchDAO searchDaO;
	@Autowired
	private IdGenerator idGenerator;
	@Autowired
	public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO appDAO;
	@Autowired
	public MiscellaneousDAO miscellaneousDAO;
	Utils utils = new Utils();
	@Autowired
	GenericCRUDOperationsDAO genericCRUDOperationsDAO;
	public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
		return "getSubscriptionDetatils";
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String paySubscriptionAmount(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String cardNo = request.getParameter("cardNo");
			String deptId = request.getParameter("deptId");
			String memberId = request.getParameter("memberId");
			String paymentConfId = request.getParameter("paymentConfId");
			String subscriptionAmount = request.getParameter("subscriptionAmount");
			String subscriptionYear = request.getParameter("subscriptionYear");
			String paidAmount = request.getParameter("paidAmount");
			String paidDate = request.getParameter("paidDate");
			String receiptNo= this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);
			String paymentStatus = request.getParameter("paymentStatus");
			String remarks = request.getParameter("remarks");

			JSONObject validateJsnObj = new JSONObject();
			validateJsnObj.put("MEMBER_ID", memberId);
			validateJsnObj.put("CARD_NO", cardNo);
			validateJsnObj.put("DEPT_ID", deptId);
			validateJsnObj.put("PAYMENT_CONF_ID", paymentConfId);
			validateJsnObj.put("SUBSCRIPTION_AMOUNT", subscriptionAmount);
			validateJsnObj.put("SUBSCRIBING_YEAR", subscriptionYear);
			validateJsnObj.put("PAID_AMOUNT", paidAmount);
			validateJsnObj.put("PAID_DATE", paidDate);
			validateJsnObj.put(	RECEIPT_NO, receiptNo);
			validateJsnObj.put("PAYMENT_STATUS", paymentStatus);

			String validationResult = subscriptionValidation(validateJsnObj);
			if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {
				SubscriptionPayments subscriptionPayments = new SubscriptionPayments();
				subscriptionPayments.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
				subscriptionPayments.setCategory("SUBSCRIPTION");
				subscriptionPayments.setSubscriptionAmount(Integer.parseInt(paidAmount));
				subscriptionPayments.setPaymentConfId(paymentConfId);
				subscriptionPayments.setPaymentStatus(paymentStatus);

				subscriptionPayments.setRemarks(remarks);
				subscriptionPayments.setSubscriptionAmount(Integer.parseInt(subscriptionAmount));

				SubscriptionPaymentsPK subscriptionPaymentsPK = new SubscriptionPaymentsPK();
				subscriptionPaymentsPK.setSubscriptionYear(Integer.parseInt(subscriptionYear));
				subscriptionPaymentsPK.setReceiptNo(receiptNo);
				subscriptionPayments.setMemberId(memberId);
				subscriptionPaymentsPK.setDeptId(deptId);
				String transId=this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID");
				subscriptionPayments.setTransactionId(transId);
				subscriptionPayments.setSubscriptionPaymentsPK(subscriptionPaymentsPK);

				this.dataAccess.save(subscriptionPayments);

				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "Subscriptoin amount paid sucessfullty!");
				
				Map<String, String> actionData=new HashMap<>();
				actionData.put("PROC_ID", "SAVE_RECEIPT_DETAILS");
				actionData.put("RECEIPT_TYPE", "SUBSCRIPTION_FOR_THE_YEAR_"+subscriptionYear);
				actionData.put("TRANSACTION_ID", transId);
				actionData.put("MEMBER_ID", memberId);
				actionData.put("CREATED_BY", "P.Durga Rao");
				actionData.put("AMOUNT", paidAmount);
				actionData.put(RECEIPT_NO, receiptNo);
				actionData.put("REMARKS", "");
				genericCRUDOperationsDAO.doGenericCRUDOpertion(actionData);
				result.put(RECEIPT_NO,receiptNo );
			} else {
				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", validationResult);
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "paySubscriptionAmount");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount/SubscriptionAmount/SubscriptionYear");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "paySubscriptionAmount");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Unable to Subscibe due to " + e.getMessage());
		}
		return result.toJSONString();
	}

	@Transactional
	public JSONObject updateSubcriptionsFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();

		try {
			String cardNo = request.getParameter("update_subscription_cardNo");
			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("update_subscription_deptId");
			String pageId = request.getParameter("update_scubscription_pageId");
			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "updateSubcriptionsFormDetails");
		}

		return resultObj;
	}

	@Transactional
	public JSONObject deleteSubcriptionsFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();
		try {
			String cardNo = request.getParameter("delete_subscription_cardNo");
			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("delete_subscription_deptId");
			String pageId = request.getParameter("delete_scubscription_pageId");

			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "deleteSubcriptionsFormDetails");
		}

		return resultObj;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public SubscriptionPayments getSubscriptionDetails(String memberId, String transId) {
		SubscriptionPayments subscriptionPayments = null;

		try {
			String query = "from SubscriptionPayments where memberId =:memberId and transactionId =:transactionId";
			Map<String, Object> parametersMap = new HashMap<String, Object>();
			parametersMap.put("memberId", memberId);
			parametersMap.put("transactionId", transId);
			List<SubscriptionPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
			if (list != null && list.size() > 0) {
				subscriptionPayments = list.get(0);

			}
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getSubscriptionDetails");
		}

		return subscriptionPayments;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String updateSubcriptions(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			String cardNo = request.getParameter("cardNo");
			String deptId = request.getParameter("deptId");
			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");
			String subscriptionYear = request.getParameter("subscriptionYear");
			String paidDate = request.getParameter("paidDate");
			String paidAmount = request.getParameter("paidAmount");
			String receiptNo = request.getParameter("receiptNo");
			String paymentStatus = request.getParameter("paymentStatus");
			String remarks = request.getParameter("remarks");
			String subscriptionAmount = request.getParameter("subscriptionAmount");
			String paymentConfId = request.getParameter("paymentConfId");

			JSONObject obj = new JSONObject();

			obj.put("CARD_NO", cardNo);
			obj.put("DEPT_ID", deptId);
			obj.put("MEMBER_ID", memberId);
			obj.put("PAYMENT_CONF_ID", paymentConfId);
			obj.put("SUBSCRIPTION_AMOUNT", subscriptionAmount);
			obj.put("SUBSCRIBING_YEAR", subscriptionYear);
			obj.put("PAID_AMOUNT", paidAmount);
			obj.put("PAID_DATE", paidDate);
			obj.put(RECEIPT_NO, receiptNo);
			obj.put("PAYMENT_STATUS", paymentStatus);

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {

				String validationResult = subscriptionValidation(obj);
				if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {

					String updateQuery = "update SubscriptionPayments set  paymentStatus=:paymentStatus,  paidDate=:paidDate , subscriptionPaymentsPK.receiptNo=:receiptNo ,  paidAmount=:paidAmount ,  subscriptionAmount=:subscriptionAmount ,  subscriptionPaymentsPK.subscriptionYear=:subscriptionYear ,  paymentConfId=:paymentConfId ,  remarks=:remarks   where transactionId=:transactionId and memberId=:memberId ";

					Map<String, Object> parametersMap = new HashMap<String, Object>();

					parametersMap.put("memberId", memberId);
					parametersMap.put("transactionId", transactionId);

					parametersMap.put("paymentStatus", paymentStatus);
					parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
					parametersMap.put("receiptNo", receiptNo);
					parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
					parametersMap.put("subscriptionAmount", Integer.valueOf(Integer.parseInt(subscriptionAmount)));
					parametersMap.put("subscriptionYear", Integer.valueOf(Integer.parseInt(subscriptionYear)));
					parametersMap.put("paymentConfId", paymentConfId);
					parametersMap.put("remarks", remarks);

					this.dataAccess.updateQuery(updateQuery, parametersMap);

					result.put("FINAL_RESULT_CODE", "400");
					result.put("DATA_DETAILS", "Subscriptoin Upadated Sucessfullty!");

				} else {

					result.put("FINAL_RESULT_CODE", "200");
					result.put("ERROR_MSG", validationResult);
				}
			} else {

				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "updateSubcriptions");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "updateSubcriptions");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Unable to update subscritions!");
		}

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String deleteSubcriptions(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {

				String hql = "delete from SUBSCRIPTION_PAYMENTS where member_Id= :memberId AND transaction_Id= :transactionId";
				Map<String, Object> parametersMap = new HashMap<String, Object>();
				parametersMap.put("memberId", memberId);
				parametersMap.put("transactionId", transactionId);
				this.dataAccess.executeUpdateSQL(hql, parametersMap);
				result.put("FINAL_RESULT_CODE", "400");
				result.put("DATA_DETAILS", "Deleted Sucessfullty!");
			} else {
				result.put("FINAL_RESULT_CODE", "200");
				result.put("ERROR_MSG", "Wrong memberid and transaction id for delete Subscriptions!");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "deleteSubcriptions");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "deleteSubcriptions");
			result.put("FINAL_RESULT_CODE", "300");
			result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
		}

		return result.toString();
	}

	@Transactional
	public JSONArray getUnsubsribedYears(String deptId, String cardNo) {
		JSONArray resultArray = new JSONArray();

		try {
			Registration registeredMember = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
			int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
			int joinedYear = Integer
					.parseInt((new SimpleDateFormat("yyyy")).format(registeredMember.getRegisteredDate()));
			List list = null;
			String query = "from SubscriptionPayments where memberId =:memberId  and paymentStatus=:paymentStatus and subscriptionYear=:subscriptionYear  ";
			for (int i = joinedYear; i <= currentYear; i++) {
				Map<String, Object> parametersMap = new HashMap<String, Object>();
				parametersMap.put("memberId", registeredMember.getRegistrationPK().getMemberId());
				parametersMap.put("paymentStatus", "Yes");
				parametersMap.put("subscriptionYear", Integer.valueOf(i));
				list = this.dataAccess.queryWithParams(query, parametersMap);
				if (list != null && list.size() > 0) {
					resultArray.add(Integer.valueOf(i));
				}
			}
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getUnsubsribedYears");
		}

		return resultArray;
	}

	@Transactional
	public String subscriptionValidation(JSONObject obj) {
		String result = "Sorry Registration Failed !";

		try {
			if (obj != null) {

				String cardNo = (String) obj.get("CARD_NO");
				String deptId = (String) obj.get("DEPT_ID");
				String memberId = (String) obj.get("MEMBER_ID");
				String paymentConfId = (String) obj.get("PAYMENT_CONF_ID");
				String subscriptionAmount = (String) obj.get("SUBSCRIPTION_AMOUNT");
				String subscriptionYear = (String) obj.get("SUBSCRIBING_YEAR");
				String paidAmount = (String) obj.get("PAID_AMOUNT");
				String paidDate = (String) obj.get("PAID_DATE");
				String receiptNo = (String) obj.get(RECEIPT_NO);
				String paymentStatus = (String) obj.get("PAYMENT_STATUS");

				if (subscriptionYear == null || "".equalsIgnoreCase(subscriptionYear)) {
					return "Please Select Subscription Year.";
				}

				if (paymentStatus == null || "".equalsIgnoreCase(paymentStatus)
						|| "No".equalsIgnoreCase(paymentStatus)) {
					return "Please Select Subscription Payment Status.";
				}
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
				if (subscriptionAmount == null || "".equalsIgnoreCase(subscriptionAmount)) {
					return "Please Enter Subscription Amount.";
				}
				if (paymentConfId == null || "".equalsIgnoreCase(paymentConfId)
						|| "SELECT".equalsIgnoreCase(paymentConfId)) {
					return "Please select payment type.";
				}

				if (!this.utils.isValidDate(paidDate)) {
					return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
				}

				if (!this.utils.isNumericString(subscriptionAmount)) {
					return "Please enter only numbers in Subscription Amount";
				}

				if (!this.utils.isNumericString(paidAmount)) {
					return "Please enter only numbers in PaidAmount";
				}

				if (this.miscellaneousDAO.isDuplicateReceiptNumberInRegistration(receiptNo)) {
					return "Receipt no already used!";
				}

				String dupliacateRecptNoMemberId = this.miscellaneousDAO
						.isDuplicateReceiptNumberInSubscriptionPayments(receiptNo, memberId);
				if (dupliacateRecptNoMemberId != null && !"".equals(dupliacateRecptNoMemberId)) {

					Registration member = this.miscellaneousDAO.getMemberDetailsByMemberId(dupliacateRecptNoMemberId);
					return "Receipt number " + receiptNo + " is already used in Subscription for DEPARTMENT = "
							+ member.getDeptName() + " CARD NO = " + member.getRegistrationPK().getCardNo();
				}

				return "SUCCESS";

			}

		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "subscriptionValidation");
		}
		return result;
	}
}