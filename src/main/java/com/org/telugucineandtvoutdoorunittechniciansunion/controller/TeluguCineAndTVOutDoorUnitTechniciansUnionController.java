/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.MembershipDAO;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RegistrationPK;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.TeluguCineAndTVOutDoorUnitTechniciansUnionService;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class TeluguCineAndTVOutDoorUnitTechniciansUnionController
/*     */ {
/*     */   @Autowired
/*     */   public TeluguCineAndTVOutDoorUnitTechniciansUnionService appService;
/*     */   @Autowired
/*     */   public MembershipDAO membershipDAO;
/*     */   @Autowired
/*     */   public HttpSession httpSession;
/*     */   @Autowired
/*     */   public MiscellaneousService miscellaneousService;
/*  41 */   Utils utils = new Utils();
/*     */   
/*     */   @RequestMapping(value = {"/welcome"}, method = {RequestMethod.GET})
/*     */   public String welcome(Map<String, Object> model) {
/*  45 */     return "welcome";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
/*     */   public String login(Map<String, Object> model) {
/*  50 */     return "redirect:login";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/logoutnew"}, method = {RequestMethod.GET})
/*     */   public String logout(Map<String, Object> model) {
/*  55 */     if (this.httpSession != null)
/*  56 */       this.httpSession.invalidate(); 
/*  57 */     return "redirect:login";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/registrationForm"}, method = {RequestMethod.GET})
/*     */   public String registrationForm(HttpServletRequest request, Map<String, Object> model) {
/*  62 */     model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
/*  63 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*  64 */     model.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelect(this.miscellaneousService.getPaymentConfigDetialsForSelect("", "REGISTRATION")));
/*  65 */     return "registrationForm";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/registration"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
/*  71 */     String allDetails = "";
/*     */     try {
/*  73 */       allDetails = this.appService.registration(this.utils.requestParamsToJSON((ServletRequest)request), file);
/*  74 */     } catch (Exception e) {
/*  75 */       ApplicationUtilities.error(getClass(), e, "upload");
/*     */     } 
/*  77 */     return allDetails;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getPaymentConfDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getPaymentConfDetails(HttpServletRequest request, HttpServletResponse response) {
/*  83 */     JSONObject resutlObj = new JSONObject();
/*     */     try {
/*  85 */       String deptId = request.getParameter("deptId");
/*  86 */       JSONObject paymentConfigDetails = this.miscellaneousService.getPaymentConfigDetials(deptId, "REGISTRATION");
/*  87 */       PaymentConfigurations payconfigObj = (PaymentConfigurations)paymentConfigDetails.get(deptId);
/*  88 */       resutlObj.put("DONATION_AMOUNT", Integer.valueOf(payconfigObj.getDonationAmount()));
/*  89 */       resutlObj.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(payconfigObj.getSubscriptionAmount()));
/*  90 */       resutlObj.put("ADMIN_AMOUNT", Integer.valueOf(payconfigObj.getAdminAmount()));
/*  91 */       resutlObj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(payconfigObj.getMembershipAmount()));
/*  92 */       resutlObj.put("PAYMENT_CONF_ID", payconfigObj.getPaymentConfigurationsPK().getPaymentConfId());
/*  93 */     } catch (Exception e) {
/*  94 */       ApplicationUtilities.error(getClass(), e, "getPaymentConfDetails");
/*     */     } 
/*  96 */     return resutlObj.toJSONString();
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/memberregistration"}, method = {RequestMethod.GET})
/*     */   public String registration(HttpServletRequest request, Map<String, Object> model) {
/* 101 */     return "registration";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getMemberDetailsByDeptIdAndCardNo"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getMemberDetailsByDeptIdAndCardNo(@RequestParam("deptId") String deptId, @RequestParam("cardNo") String cardNo, HttpServletRequest request) {
/* 107 */     String result = "";
/*     */     try {
/* 109 */       Registration registration = this.miscellaneousService.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
/* 110 */       RegistrationPK registrationPK = registration.getRegistrationPK();
/* 111 */     } catch (Exception e) {
/* 112 */       ApplicationUtilities.error(getClass(), e, "getMemberDetailsByDeptIdAndCardNo");
/*     */     } 
/* 114 */     return result;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getMemberDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String viewMemberDetails(HttpServletRequest request, Map<String, Object> model) {
/* 120 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 121 */     model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
/* 122 */     return this.appService.viewMemberDetails(request);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/displayMemberDetails"}, method = {RequestMethod.GET})
/*     */   public String displayMemberDetails(HttpServletRequest request, Map<String, Object> model) {
/* 127 */     model.put("MEMBER_ID", request.getParameter("memberId"));
/* 128 */     model.put("POFILE_PIC", "");
/* 129 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 130 */     model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
/* 131 */     model.put("MEMBER_DETAILS", this.appService.viewMemberDetails(request));
/* 132 */     return "viewMemberDetails";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateMemberDetails"}, method = {RequestMethod.GET})
/*     */   public String updateMemberDetails(HttpServletRequest request, Map<String, Object> model) {
/* 137 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 138 */     model.put("UNITS", this.utils.getUnitsAsHTMLSelect(this.miscellaneousService.getUnits()));
/* 139 */     return "updateMemberDetails";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateRegisteredMemeberDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String updateRegisteredMemeberDetails(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws Exception {
/* 145 */     return this.appService.updateMemberDetails(this.utils.requestParamsToJSON((ServletRequest)request), file);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/payMembershipAmount"}, method = {RequestMethod.GET})
/*     */   public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
/* 150 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 151 */     return "payMembershipAmount";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/payCardBalance"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String payCardBalance(HttpServletRequest request) {
/* 157 */     return this.appService.payCardBalance(request);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getCardBalance"}, method = {RequestMethod.GET})
/*     */   public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
/* 162 */     return this.appService.getCardBalance(request, model);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getMembersDetails"}, method = {RequestMethod.POST})
/*     */   public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
/* 167 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 168 */     return this.appService.getMembersDetails(request, model);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateCardBalanceForm"}, method = {RequestMethod.POST})
/*     */   public String updateCardBalanceForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 174 */       model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 175 */       JSONObject obj = this.appService.updateCardBalanceFormDetails(request);
/* 176 */       String transId = request.getParameter("update_cardBalance_transId");
/* 177 */       String memberId = request.getParameter("update_cardBalance_memberId");
/* 178 */       if (obj != null) {
/* 179 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/* 180 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 181 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 182 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 183 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 184 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 185 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 186 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 187 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 188 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 189 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 190 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 191 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 192 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 193 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/* 194 */           model.put("LOAN_BALANCE", objectTopPanel.get("LOAN_BALANCE"));
/* 195 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 196 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 197 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/* 198 */           MembershipPayments membershipPayments = this.membershipDAO.getMembershipPaymentsDetails(memberId, transId);
/* 199 */           if (membershipPayments != null) {
/* 200 */             model.put("PAYMENT_CONF_ID", 
/* 201 */                 this.utils.convertPaymentConfigDetailsToHTMLSelected(
/* 202 */                   this.miscellaneousService.getPaymentConfigDetialsForSelect((String)objectTopPanel.get("DEPT_ID"), membershipPayments.getCategory()), membershipPayments.getPaymentConfId()));
/* 203 */             PaymentConfigurations paymentConfigurations = this.miscellaneousService.getPaymentConfigurationsDetailsById(membershipPayments.getPaymentConfId());
/*     */             
/* 205 */             ApplicationUtilities.debug(getClass(), "getCategory  >> " + membershipPayments.getCategory());
/* 206 */             ApplicationUtilities.debug(getClass(), "getPaymentConfId  >> " + membershipPayments.getPaymentConfId());
/* 207 */             ApplicationUtilities.debug(getClass(), "PAYMENT_CONF_ID  >> " + model.get("PAYMENT_CONF_ID"));
/*     */             
/* 209 */             if (paymentConfigurations != null) {
/* 210 */               model.put("MEMBERSHIP_AMOUNT", Integer.valueOf(paymentConfigurations.getMembershipAmount()));
/* 211 */               model.put("PAID_AMOUNT", Integer.valueOf(membershipPayments.getPaidAmount()));
/* 212 */               model.put("PAID_DATE", (
/* 213 */                   new SimpleDateFormat("dd/MM/yyyy")).format(membershipPayments.getPaidDate()));
/* 214 */               model.put("RECEIPT_NO", membershipPayments.getReceiptNo());
/* 215 */               model.put("REMARKS", membershipPayments.getRemarks());
/* 216 */               model.put("TRANSACTION_ID", membershipPayments.getMembershipPaymentsPK().getTransactionId());
/* 217 */               model.put("MEMBER_ID", membershipPayments.getMembershipPaymentsPK().getMemberId());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 222 */     } catch (Exception e) {
/* 223 */       ApplicationUtilities.error(getClass(), e, "updateCardBalanceForm");
/*     */     } 
/* 225 */     return "updateCardBalanceForm";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/deleteCardBalanceForm"}, method = {RequestMethod.POST})
/*     */   public String deleteCardBalanceForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 231 */       model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 232 */       JSONObject obj = this.appService.deleteCardBalanceFormDetails(request);
/* 233 */       String transId = request.getParameter("delete_cardBalance_transId");
/* 234 */       String memberId = request.getParameter("delete_cardBalance_memberId");
/* 235 */       if (obj != null) {
/* 236 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/* 237 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 238 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 239 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 240 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 241 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 242 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 243 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 244 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 245 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 246 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 247 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 248 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 249 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 250 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/* 251 */           model.put("LOAN_BALANCE", objectTopPanel.get("LOAN_BALANCE"));
/* 252 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 253 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 254 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/* 255 */           MembershipPayments membershipPayments = this.membershipDAO.getMembershipPaymentsDetails(memberId, transId);
/* 256 */           if (membershipPayments != null) {
/* 257 */             model.put("PAYMENT_CONF_ID", 
/* 258 */                 this.utils.convertPaymentConfigDetailsToHTMLSelected(
/* 259 */                   this.miscellaneousService.getPaymentConfigDetialsForSelect(
/* 260 */                     (String)objectTopPanel.get("DEPT_ID"), membershipPayments.getCategory()), membershipPayments.getPaymentConfId()));
/* 261 */             PaymentConfigurations paymentConfigurations = this.miscellaneousService.getPaymentConfigurationsDetailsById(membershipPayments.getPaymentConfId());
/* 262 */             model.put("MEMBERSHIP_AMOUNT", Integer.valueOf(paymentConfigurations.getMembershipAmount()));
/* 263 */             model.put("PAID_AMOUNT", Integer.valueOf(membershipPayments.getPaidAmount()));
/* 264 */             model.put("PAID_DATE", (
/* 265 */                 new SimpleDateFormat("dd/MM/yyyy")).format(membershipPayments.getPaidDate()));
/* 266 */             model.put("RECEIPT_NO", membershipPayments.getReceiptNo());
/* 267 */             model.put("REMARKS", membershipPayments.getRemarks());
/* 268 */             model.put("TRANSACTION_ID", membershipPayments.getMembershipPaymentsPK().getTransactionId());
/* 269 */             model.put("MEMBER_ID", membershipPayments.getMembershipPaymentsPK().getMemberId());
/*     */           } 
/*     */         } 
/*     */       } 
/* 273 */     } catch (Exception e) {
/* 274 */       ApplicationUtilities.error(getClass(), e, "deleteCardBalanceForm");
/*     */     } 
/* 276 */     return "deleteCardBalanceForm";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateCardBalance"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String updateMembershipPayments(HttpServletRequest request) {
/* 282 */     String result = "";
/*     */     try {
/* 284 */       result = this.appService.updateMembershipPayments(request);
/* 285 */     } catch (Exception e) {
/* 286 */       ApplicationUtilities.error(getClass(), e, "updateMembershipPayments");
/*     */     } 
/* 288 */     return result;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/deleteCardBalance"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String deleteMembershipPayments(HttpServletRequest request) {
/* 294 */     String result = "";
/*     */     try {
/* 296 */       result = this.appService.deleteMembershipPayments(request);
/* 297 */     } catch (Exception e) {
/* 298 */       ApplicationUtilities.error(getClass(), e, "deleteMembershipPayments");
/*     */     } 
/* 300 */     return result;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/error"}, method = {RequestMethod.GET})
/*     */   public String error(HttpServletRequest request, Map<String, Object> model) {
/* 305 */     model.put("ERROR_MESSAGE", "OOPS :(  Sorry for inconvinence some error has uccured !");
/* 306 */     return "Error";
/*     */   }

/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\controller\TeluguCineAndTVOutDoorUnitTechniciansUnionController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */