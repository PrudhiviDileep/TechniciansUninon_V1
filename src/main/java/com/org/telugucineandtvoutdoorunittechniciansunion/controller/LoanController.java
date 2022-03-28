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

import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.LoanService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class LoanController {
	@Autowired
	public LoanService loanService;
	@Autowired
	public MiscellaneousService miscellaneousService;
	Utils utils = new Utils();
private static final String DEPARTMENTS="DEPARTMENTS";
	@RequestMapping(value = { "/sanctionLoanForm" }, method = { RequestMethod.GET })
	public String sanctionLoanForm(HttpServletRequest request, Map<String, Object> model) {
		model.put(DEPARTMENTS, this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));

		return "sanctionLoanForm";
	}

	@RequestMapping(value = { "/loanSummaryPage" }, method = { RequestMethod.GET })
	public String loanSummary(HttpServletRequest request, Map<String, Object> model) {
		model.put(DEPARTMENTS, this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));

		return "loanSummary";
	}

	@RequestMapping(value = { "/getLoanSummary" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getLoanSummary(HttpServletRequest request, Map<String, Object> model) {
		return this.loanService.loanSummary(request);
	}

	@RequestMapping(value = { "/sanctionLoan" }, method = { RequestMethod.POST })
	@ResponseBody
	public String sanctionLoan(HttpServletRequest request, Map<String, Object> model) {
		model.put(DEPARTMENTS, this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return this.loanService.sanctionLoan(request);
	}

	@RequestMapping(value = { "/payLoanAmountForm" }, method = { RequestMethod.GET })
	public String payLoanAmountForm(HttpServletRequest request, Map<String, Object> model) {
		model.put(DEPARTMENTS, this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return "payLoanAmount";
	}

	@RequestMapping(value = { "/payLoanAmount" }, method = { RequestMethod.POST })
	@ResponseBody
	public String payLoanAmount(HttpServletRequest request, Map<String, Object> model) {
		return this.loanService.payLoanAmount(request);
	}

	@RequestMapping(value = { "/updateLoanRecoveryDetailsForm" }, method = { RequestMethod.POST })
	public String updateLoanRecoveryDetailsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.loanService.updateLoanRecoveryDetailsFormDetails(request);
			String transId = request.getParameter("update_LoanRecoveryDetails_transId");
			String memberId = request.getParameter("update_LoanRecoveryDetails_memberId");
			String loanId = request.getParameter("update_LoanRecoveryDetails_loanId");

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

					LoanRecoveryDetails loanRecoveryDetails = this.loanService.getLoanRecoveryDetails(memberId, transId,
							loanId);
					if (loanRecoveryDetails != null) {
						model.put("PAID_DATE",
								(new SimpleDateFormat("dd/MM/yyyy")).format(loanRecoveryDetails.getPaidDate()));
						model.put("PAID_AMOUNT", Integer.valueOf(loanRecoveryDetails.getPaidAmount()));
						model.put("RECEIPT_NO", loanRecoveryDetails.getReceiptNo());
						model.put("REMARKS", loanRecoveryDetails.getRemarks());
						model.put("TRANSACTION_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getTransactionId());
						model.put("LOAN_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getLoanId());
						model.put("MEMBER_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getMemberId());
					}

				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "updateLoanRecoveryDetailsForm";
	}

	@RequestMapping(value = { "/deleteLoanRecoveryDetailsForm" }, method = { RequestMethod.POST })
	public String deleteLoanRecoveryDetailsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.loanService.deleteLoanRecoveryDetailsFormDetails(request);
			String transId = request.getParameter("delete_LoanRecoveryDetails_transId");
			String memberId = request.getParameter("delete_LoanRecoveryDetails_memberId");
			String loanId = request.getParameter("delete_LoanRecoveryDetails_loanId");
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
					LoanRecoveryDetails loanRecoveryDetails = this.loanService.getLoanRecoveryDetails(memberId, transId,
							loanId);
					if (loanRecoveryDetails != null) {
						model.put("PAID_DATE",
								(new SimpleDateFormat("dd/MM/yyyy")).format(loanRecoveryDetails.getPaidDate()));
						model.put("PAID_AMOUNT", Integer.valueOf(loanRecoveryDetails.getPaidAmount()));
						model.put("RECEIPT_NO", loanRecoveryDetails.getReceiptNo());
						model.put("REMARKS", loanRecoveryDetails.getRemarks());
						model.put("TRANSACTION_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getTransactionId());
						model.put("LOAN_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getLoanId());
						model.put("MEMBER_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getMemberId());
					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "deleteLoanRecoveryDetailsForm";
	}

	@RequestMapping(value = { "/updateLoanRecoveryDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String updateLoanRecoveryDetails(HttpServletRequest request) {
		String result = "";

		try {
			result = this.loanService.updateLoanRecoveryDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = { "/deleteLoanRecoveryDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String deleteLoanRecoveryDetails(HttpServletRequest request) {
		String result = "";
		try {
			result = this.loanService.deleteLoanRecoveryDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = { "/updateLoanSanctionDetailsForm" }, method = { RequestMethod.POST })
	public String updateLoanSanctionDetailsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.loanService.updateLoanSanctionDetailsFormDetails(request);

			String memberId = request.getParameter("update_LoanSanctionDetails_memberId");
			String loanId = request.getParameter("update_LoanSanctionDetails_loanId");
			String loanSanctinedDate = request.getParameter("update_LoanSanctionDetails_loanSanctionDate");
			String loanSanctinedAmount = request.getParameter("update_LoanSanctionDetails_loanAmount");
			String loanSanctinedFatherName = request.getParameter("update_LoanSanctionDetails_fatherName");

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

					model.put("LOAN_SANCTIONED_DATE", loanSanctinedDate);
					model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
					model.put("FATHER_NAME", loanSanctinedFatherName);
					model.put("LOAN_AMOUNT", loanSanctinedAmount);
					model.put("LOAN_ID", loanId);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return "updateLoanSanctionDetailsForm";
	}

	@RequestMapping(value = { "/deleteLoanSanctionDetailsForm" }, method = { RequestMethod.POST })
	public String deleteLoanSanctionDetailsForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			JSONObject obj = this.loanService.deleteLoanRecoveryDetailsFormDetails(request);
			String memberId = request.getParameter("update_LoanSanctionDetails_memberId");
			String loanId = request.getParameter("update_LoanSanctionDetails_loanId");
			String loanSanctinedDate = request.getParameter("update_LoanSanctionDetails_loanSanctionDate");
			String loanSanctinedAmount = request.getParameter("update_LoanSanctionDetails_loanAmount");
			String loanSanctinedFatherName = request.getParameter("update_LoanSanctionDetails_fatherName");

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

					model.put("LOAN_SANCTIONED_DATE", loanSanctinedDate);
					model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
					model.put("FATHER_NAME", loanSanctinedFatherName);
					model.put("LOAN_AMOUNT", loanSanctinedAmount);
					model.put("LOAN_ID", loanId);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "deleteLoanSanctionDetailsForm";
	}

	@RequestMapping(value = { "/updateLoanSanctionDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String updateLoanSanctionDetails(HttpServletRequest request) {
		String result = "";

		try {
			result = this.loanService.updateSanctionDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = { "/deleteLoanSanctionDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String deleteLoanSanctionDetails(HttpServletRequest request) {
		String result = "";
		try {
			result = this.loanService.deleteLoanSanctionDetails(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@RequestMapping(value = { "/getLoanDetails" }, method = { RequestMethod.GET })
	public String getLoanDetails(HttpServletRequest request, Map<String, Object> model) {
		model.put(DEPARTMENTS, this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return this.loanService.getLoanDetails(request);
	}
}
