package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.SubscriptionService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class SubscriptionController {
	@Autowired
	public SubscriptionService subscriptionService;
	@Autowired
	public MiscellaneousService miscellaneousService;
	Utils utils = new Utils();

	@RequestMapping(value = { "/getSubscriptionDetatils" }, method = { RequestMethod.GET })
	public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return this.subscriptionService.getSubscriptionDetatils(request, model);
	}

	@RequestMapping(value = { "/paySubscriptionAmountForm" }, method = { RequestMethod.GET })
	public String paySubscriptionAmountForm(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		model.put("PAYMENT_STATUS", this.utils.getSubscriptionPaymentStatus());

		return "paySubscriptionAmount";
	}

	@RequestMapping(value = { "/paySubscriptionAmount" }, method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String paySubscriptionAmount(HttpServletRequest request, Map<String, Object> model) {
		return this.subscriptionService.paySubscriptionAmount(request);
	}

	@RequestMapping(value = { "/updateSubcriptionsForm" }, method = { RequestMethod.POST })
	public String updateSubcriptionsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.subscriptionService.updateSubcriptionsFormDetails(request);

			String transId = request.getParameter("update_subscription_transId");

			String memberId = request.getParameter("update_subscription_memberId");
			if (obj != null) {
				String topPanelresultCode = (String) obj.get("TOP_PANEL_RESULT_CODE");

				if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode)
						&& "400".equalsIgnoreCase(topPanelresultCode)) {
					JSONObject objectTopPanel = (JSONObject) obj.get("TOP_PANEL_DETAILS");
					model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
					model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
					model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
					model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
					model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
					model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
					model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
					model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
					model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
					model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
					model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));

					model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
					model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
					model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));

					SubscriptionPayments subscriptionPayments = this.subscriptionService
							.getSubscriptionDetails(memberId, transId);
					if (subscriptionPayments != null) {
						model.put("PAID_DATE",
								(new SimpleDateFormat("dd/MM/yyyy")).format(subscriptionPayments.getPaidDate()));
						model.put("PAID_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
						model.put("PAYMENT_STATUS",
								this.utils.setSubscriptionPaymentStatus(subscriptionPayments.getPaymentStatus()));
						model.put("RECEIPT_NO", subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo());
						model.put("REMARKS", subscriptionPayments.getRemarks());
						model.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));

						model.put("TRANSACTION_ID", subscriptionPayments.getTransactionId());
						model.put("CATEGORY", subscriptionPayments.getCategory());
						model.put("PAYMENT_CONF_ID",
								this.utils.convertPaymentConfigDetailsToHTMLSelect(
										this.miscellaneousService.getPaymentConfigDetialsForSelect(
												(String) objectTopPanel.get("DEPT_ID"), "SUBSCRIPTION")));

						model.put("MEMBER_ID", subscriptionPayments.getMemberId());
						model.put("SUBSCRIPTION_YEAR", this.utils.setSubscribedYearsSelectedToGiverYear(
								subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear()));
					}

				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "updateSubcriptionsForm");
		}

		return "updateSubcriptionsForm";
	}

	@RequestMapping(value = { "/deleteSubcriptionsForm" }, method = { RequestMethod.POST })
	public String deleteSubcriptionsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.subscriptionService.deleteSubcriptionsFormDetails(request);

			String transId = request.getParameter("delete_subscription_transId");

			String memberId = request.getParameter("delete_subscription_memberId");
			if (obj != null) {
				String topPanelresultCode = (String) obj.get("TOP_PANEL_RESULT_CODE");

				if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode)
						&& "400".equalsIgnoreCase(topPanelresultCode)) {
					JSONObject objectTopPanel = (JSONObject) obj.get("TOP_PANEL_DETAILS");
					model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
					model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
					model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
					model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
					model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
					model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
					model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
					model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
					model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
					model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
					model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));

					model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
					model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
					model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
					SubscriptionPayments subscriptionPayments = this.subscriptionService
							.getSubscriptionDetails(memberId, transId);
					if (subscriptionPayments != null) {
						model.put("PAID_DATE",
								(new SimpleDateFormat("dd/MM/yyyy")).format(subscriptionPayments.getPaidDate()));
						model.put("PAID_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
						model.put("PAYMENT_STATUS",
								this.utils.setSubscriptionPaymentStatus(subscriptionPayments.getPaymentStatus()));
						model.put("RECEIPT_NO", subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo());
						model.put("REMARKS", subscriptionPayments.getRemarks());
						model.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));

						model.put("TRANSACTION_ID", subscriptionPayments.getTransactionId());
						model.put("CATEGORY", subscriptionPayments.getCategory());
						model.put("PAYMENT_CONF_ID", subscriptionPayments.getPaymentConfId());
						model.put("MEMBER_ID", subscriptionPayments.getMemberId());
						model.put("SUBSCRIPTION_YEAR", this.utils.setSubscribedYearsSelectedToGiverYear(
								subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear()));
					}

				}

			}

		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "deleteSubcriptionsForm");
		}

		return "deleteSubcriptionsForm";
	}

	@RequestMapping(value = { "/updateSubcriptions" }, method = { RequestMethod.POST })
	@ResponseBody
	public String updateSubcriptions(HttpServletRequest request) {
		String result = "";

		try {
			result = this.subscriptionService.updateSubcriptions(request);
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "updateSubcriptions");
		}

		return result;
	}

	@RequestMapping(value = { "/deleteSubcriptions" }, method = { RequestMethod.POST })
	@ResponseBody
	public String deleteSubcriptions(HttpServletRequest request) {
		String result = "";
		try {
			result = this.subscriptionService.deleteSubcriptions(request);
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "deleteSubcriptions");
		}

		return result;
	}
}
