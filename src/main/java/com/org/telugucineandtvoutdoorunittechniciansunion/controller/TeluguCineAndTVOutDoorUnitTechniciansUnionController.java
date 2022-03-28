package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.MembershipDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericCRUDOperationsService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.TeluguCineAndTVOutDoorUnitTechniciansUnionService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class TeluguCineAndTVOutDoorUnitTechniciansUnionController {
	@Autowired
	public TeluguCineAndTVOutDoorUnitTechniciansUnionService appService;
	@Autowired
	public MembershipDAO membershipDAO;
	@Autowired
	public HttpSession httpSession;
	@Autowired
	public MiscellaneousService miscellaneousService;
	Utils utils = new Utils();

	@RequestMapping(value = { "/welcome" }, method = { RequestMethod.GET })
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}

	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String login(Map<String, Object> model) {
		return "redirect:login";
	}

	@RequestMapping(value = { "/logoutnew" }, method = { RequestMethod.GET })
	public String logout(Map<String, Object> model) {
		if (this.httpSession != null)
			this.httpSession.invalidate();
		return "redirect:login";
	}

	@RequestMapping(value = { "/registrationForm" }, method = { RequestMethod.GET })
	public String registrationForm(HttpServletRequest request, Map<String, Object> model) {
		model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		model.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelect(
				this.miscellaneousService.getPaymentConfigDetialsForSelect("", "REGISTRATION")));
		return "registrationForm";
	}

	@RequestMapping(value = { "/registration" }, method = { RequestMethod.POST })
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
		String allDetails = "";
		try {
			allDetails = this.appService.registration(this.utils.requestParamsToJSON((ServletRequest) request), file);
		} catch (Exception e) {
			
			ApplicationUtilities.error(getClass(), e, "upload");
		}
		return allDetails;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/getPaymentConfDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getPaymentConfDetails(HttpServletRequest request, HttpServletResponse response) {
		JSONObject resutlObj = new JSONObject();
		try {
			String deptId = request.getParameter("deptId");
			JSONObject paymentConfigDetails = this.miscellaneousService.getPaymentConfigDetials(deptId, "REGISTRATION");
			PaymentConfigurations payconfigObj = (PaymentConfigurations) paymentConfigDetails.get(deptId);
			resutlObj.put("DONATION_AMOUNT", Integer.valueOf(payconfigObj.getDonationAmount()));
			resutlObj.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(payconfigObj.getSubscriptionAmount()));
			resutlObj.put("ADMIN_AMOUNT", Integer.valueOf(payconfigObj.getAdminAmount()));
			resutlObj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(payconfigObj.getMembershipAmount()));
			resutlObj.put("PAYMENT_CONF_ID", payconfigObj.getPaymentConfigurationsPK().getPaymentConfId());
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "getPaymentConfDetails");
		}
		return resutlObj.toJSONString();
	}

	@RequestMapping(value = { "/memberregistration" }, method = { RequestMethod.GET })
	public String registration(HttpServletRequest request, Map<String, Object> model) {
		return "registration";
	}


	@RequestMapping(value = { "/getMemberDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String viewMemberDetails(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
		return this.appService.viewMemberDetails(request);
	}

	@RequestMapping(value = { "/displayMemberDetails" }, method = { RequestMethod.GET })
	public String displayMemberDetails(HttpServletRequest request, Map<String, Object> model) {
		model.put("MEMBER_ID", request.getParameter("memberId"));
		model.put("POFILE_PIC", "");
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
		model.put("MEMBER_DETAILS", this.appService.viewMemberDetails(request));
		return "viewMemberDetails";
	}

	@RequestMapping(value = { "/updateMemberDetails" }, method = { RequestMethod.GET })
	public String updateMemberDetails(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
		return "updateMemberDetails";
	}

	@RequestMapping(value = { "/updateRegisteredMemeberDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String updateRegisteredMemeberDetails(@RequestParam("file") MultipartFile[] file, HttpServletRequest request)
			throws Exception {
		return this.appService.updateMemberDetails(this.utils.requestParamsToJSON((ServletRequest) request), file);
	}

	@RequestMapping(value = { "/payMembershipAmount" }, method = { RequestMethod.GET })
	public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return "payMembershipAmount";
	}

	@RequestMapping(value = { "/payCardBalance" }, method = { RequestMethod.POST })
	@ResponseBody
	public String payCardBalance(HttpServletRequest request) {
		return this.appService.payCardBalance(request);
	}

	@RequestMapping(value = { "/getCardBalance" }, method = { RequestMethod.GET })
	public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
		return this.appService.getCardBalance(request, model);
	}

	@RequestMapping(value = { "/getMembersDetails" }, method = { RequestMethod.POST })
	public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		return this.appService.getMembersDetails(request, model);
	}

	@RequestMapping(value = { "/updateCardBalanceForm" }, method = { RequestMethod.POST })
	public String updateCardBalanceForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
			JSONObject obj = this.appService.updateCardBalanceFormDetails(request);
			String transId = request.getParameter("update_cardBalance_transId");
			String memberId = request.getParameter("update_cardBalance_memberId");
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
					model.put("LOAN_BALANCE", objectTopPanel.get("LOAN_BALANCE"));
					model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
					model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
					model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
					MembershipPayments membershipPayments = this.membershipDAO.getMembershipPaymentsDetails(memberId,
							transId);
					if (membershipPayments != null) {
						model.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelected(
								this.miscellaneousService.getPaymentConfigDetialsForSelect(
										(String) objectTopPanel.get("DEPT_ID"), membershipPayments.getCategory()),
								membershipPayments.getPaymentConfId()));
						PaymentConfigurations paymentConfigurations = this.miscellaneousService
								.getPaymentConfigurationsDetailsById(membershipPayments.getPaymentConfId());

						ApplicationUtilities.debug(getClass(), "getCategory  >> " + membershipPayments.getCategory());
						ApplicationUtilities.debug(getClass(),
								"getPaymentConfId  >> " + membershipPayments.getPaymentConfId());
						ApplicationUtilities.debug(getClass(), "PAYMENT_CONF_ID  >> " + model.get("PAYMENT_CONF_ID"));

						if (paymentConfigurations != null) {
							model.put("MEMBERSHIP_AMOUNT",
									Integer.valueOf(paymentConfigurations.getMembershipAmount()));
							model.put("PAID_AMOUNT", Integer.valueOf(membershipPayments.getPaidAmount()));
							model.put("PAID_DATE",
									(new SimpleDateFormat("dd/MM/yyyy")).format(membershipPayments.getPaidDate()));
							model.put("RECEIPT_NO", membershipPayments.getReceiptNo());
							model.put("REMARKS", membershipPayments.getRemarks());
							model.put("TRANSACTION_ID",
									membershipPayments.getMembershipPaymentsPK().getTransactionId());
							model.put("MEMBER_ID", membershipPayments.getMembershipPaymentsPK().getMemberId());
						}
					}
				}
			}
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "updateCardBalanceForm");
		}
		return "updateCardBalanceForm";
	}

	@RequestMapping(value = { "/deleteCardBalanceForm" }, method = { RequestMethod.POST })
	public String deleteCardBalanceForm(HttpServletRequest request, Map<String, Object> model) {
		try {
			model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
			JSONObject obj = this.appService.deleteCardBalanceFormDetails(request);
			String transId = request.getParameter("delete_cardBalance_transId");
			String memberId = request.getParameter("delete_cardBalance_memberId");
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
					model.put("LOAN_BALANCE", objectTopPanel.get("LOAN_BALANCE"));
					model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
					model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
					model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
					MembershipPayments membershipPayments = this.membershipDAO.getMembershipPaymentsDetails(memberId,
							transId);
					if (membershipPayments != null) {
						model.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelected(
								this.miscellaneousService.getPaymentConfigDetialsForSelect(
										(String) objectTopPanel.get("DEPT_ID"), membershipPayments.getCategory()),
								membershipPayments.getPaymentConfId()));
						PaymentConfigurations paymentConfigurations = this.miscellaneousService
								.getPaymentConfigurationsDetailsById(membershipPayments.getPaymentConfId());
						model.put("MEMBERSHIP_AMOUNT", Integer.valueOf(paymentConfigurations.getMembershipAmount()));
						model.put("PAID_AMOUNT", Integer.valueOf(membershipPayments.getPaidAmount()));
						model.put("PAID_DATE",
								(new SimpleDateFormat("dd/MM/yyyy")).format(membershipPayments.getPaidDate()));
						model.put("RECEIPT_NO", membershipPayments.getReceiptNo());
						model.put("REMARKS", membershipPayments.getRemarks());
						model.put("TRANSACTION_ID", membershipPayments.getMembershipPaymentsPK().getTransactionId());
						model.put("MEMBER_ID", membershipPayments.getMembershipPaymentsPK().getMemberId());
					}
				}
			}
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "deleteCardBalanceForm");
		}
		return "deleteCardBalanceForm";
	}

	@RequestMapping(value = { "/updateCardBalance" }, method = { RequestMethod.POST })
	@ResponseBody
	public String updateMembershipPayments(HttpServletRequest request) {
		String result = "";
		try {
			result = this.appService.updateMembershipPayments(request);
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "updateMembershipPayments");
		}
		return result;
	}

	@RequestMapping(value = { "/deleteCardBalance" }, method = { RequestMethod.POST })
	@ResponseBody
	public String deleteMembershipPayments(HttpServletRequest request) {
		String result = "";
		try {
			result = this.appService.deleteMembershipPayments(request);
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "deleteMembershipPayments");
		}
		return result;
	}

	@RequestMapping(value = { "/error" }, method = { RequestMethod.GET })
	public String error(HttpServletRequest request, Map<String, Object> model) {
		model.put("ERROR_MESSAGE", "OOPS :(  Sorry for inconvinence some error has uccured !");
		return "Error";
	}
	
	@RequestMapping(value = { "/getBalanceInfo" }, method = { RequestMethod.GET })
	public String getBalanceInfo(HttpServletRequest request, Map<String, Object> model) {
		model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
		
		return "getAllBalances";
	}
	@Autowired
	public GenericCRUDOperationsService genericCRUDOperationsService;
	
	@RequestMapping(value = { "/showPrintReciept" }, method = { RequestMethod.GET })
	public String showPrintReciept(HttpServletRequest request, Map<String, Object> model) {
		
		Map<String,String> params=Utils.requestParamsToMap(request);
		params.put("PROC_ID", "GET_RECEIPT_DETAILS");
		String result=genericCRUDOperationsService.doGenericCRUDOpertion(params);
		System.out.println(" showPrintReciept result "+result);
		JSONArray recietpDetArr=(JSONArray)JSONValue.parse(result);
		JSONObject recietpDetObj=new JSONObject();
		if(recietpDetArr!=null && recietpDetArr.size()>0)
		recietpDetObj=(JSONObject)recietpDetArr.get(0);
		
		String receiptType=(String)recietpDetObj.get("RECEIPT_TYPE");
		
		if(receiptType!=null && "MEMBERSHIP_AMOUNT".equalsIgnoreCase(receiptType)) {
			receiptType="  mebership card payment ";
		}else if(receiptType!=null && "LOAN_RECOVERY_AMOUNT".equalsIgnoreCase(receiptType)) {
			receiptType=" loan repayment ";
		}else if(receiptType!=null && receiptType.indexOf("SUBSCRIPTION_FOR_THE_YEAR_")>-1) {
			receiptType=receiptType.replaceAll("_", " ").toLowerCase();
			}
		
		StringBuilder otherInfo=new StringBuilder();
		otherInfo.append("Card No : "+recietpDetObj.get("CARD_NO"));
		
		otherInfo.append(" Department : "+recietpDetObj.get("DEPT_ID"));
		otherInfo.append(" S/O : "+recietpDetObj.get("FATHER_NAME"));
		
		model.put("AMOUNT_IN_WORDS", Utils.convertToWord(Integer.parseInt((String)recietpDetObj.get("AMOUNT")))+" rupees only.");
		model.put("RECEIPT_ID",recietpDetObj.get("RECEIPT_ID"));
		model.put("RECEIPT_TYPE",receiptType);
		model.put("TRANSACTION_ID",recietpDetObj.get("TRANSACTION_ID"));
		model.put("MEMBER_ID",recietpDetObj.get("MEMBER_ID"));
		model.put("CREATED_DATAE",recietpDetObj.get("CREATED_DATAE"));
		model.put("CREATED_BY",recietpDetObj.get("CREATED_BY"));
		model.put("AMOUNT",recietpDetObj.get("AMOUNT")+" /-");
		model.put("REMARKS",recietpDetObj.get("REMARKS"));
		model.put("OTHER_DETAILS",otherInfo.toString());
		model.put("FIRST_NAME",recietpDetObj.get("FIRST_NAME"));
		
		return "printReciept";
	}

}
