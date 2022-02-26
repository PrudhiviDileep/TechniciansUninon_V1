/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.SubscriptionService;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class SubscriptionController
/*     */ {
/*     */   @Autowired
/*     */   public SubscriptionService subscriptionService;
/*     */   @Autowired
/*     */   public MiscellaneousService miscellaneousService;
/*  27 */   Utils utils = new Utils();
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getSubscriptionDetatils"}, method = {RequestMethod.GET})
/*     */   public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
/*  32 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*  33 */     return this.subscriptionService.getSubscriptionDetatils(request, model);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/paySubscriptionAmountForm"}, method = {RequestMethod.GET})
/*     */   public String paySubscriptionAmountForm(HttpServletRequest request, Map<String, Object> model) {
/*  40 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*  41 */     model.put("PAYMENT_STATUS", this.utils.getSubscriptionPaymentStatus());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     return "paySubscriptionAmount";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/paySubscriptionAmount"}, method = {RequestMethod.GET, RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String paySubscriptionAmount(HttpServletRequest request, Map<String, Object> model) {
/*  62 */     return this.subscriptionService.paySubscriptionAmount(request);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateSubcriptionsForm"}, method = {RequestMethod.POST})
/*     */   public String updateSubcriptionsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/*  70 */       JSONObject obj = this.subscriptionService.updateSubcriptionsFormDetails(request);
/*     */       
/*  72 */       String transId = request.getParameter("update_subscription_transId");
/*     */       
/*  74 */       String memberId = request.getParameter("update_subscription_memberId");
/*  75 */       if (obj != null) {
/*  76 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/*  78 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/*  79 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/*  80 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/*  81 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/*  82 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/*  83 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/*  84 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/*  85 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/*  86 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/*  87 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/*  88 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/*  89 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/*  90 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/*  91 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/*     */ 
/*     */           
/*  94 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/*  95 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/*  96 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/*     */           
/*  98 */           SubscriptionPayments subscriptionPayments = this.subscriptionService.getSubscriptionDetails(memberId, 
/*  99 */               transId);
/* 100 */           if (subscriptionPayments != null)
/*     */           {
/* 102 */             model.put("PAID_DATE", (
/* 103 */                 new SimpleDateFormat("dd/MM/yyyy")).format(subscriptionPayments.getPaidDate()));
/* 104 */             model.put("PAID_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
/* 105 */             model.put("PAYMENT_STATUS", 
/* 106 */                 this.utils.setSubscriptionPaymentStatus(subscriptionPayments.getPaymentStatus()));
/* 107 */             model.put("RECEIPT_NO", subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo());
/* 108 */             model.put("REMARKS", subscriptionPayments.getRemarks());
/* 109 */             model.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
/*     */             
/* 111 */             model.put("TRANSACTION_ID", subscriptionPayments.getTransactionId());
/* 112 */             model.put("CATEGORY", subscriptionPayments.getCategory());
/* 113 */             model.put("PAYMENT_CONF_ID", 
/* 114 */                 this.utils.convertPaymentConfigDetailsToHTMLSelect(
/* 115 */                   this.miscellaneousService.getPaymentConfigDetialsForSelect(
/* 116 */                     (String)objectTopPanel.get("DEPT_ID"), "SUBSCRIPTION")));
/*     */ 
/*     */             
/* 119 */             model.put("MEMBER_ID", subscriptionPayments.getMemberId());
/* 120 */             model.put("SUBSCRIPTION_YEAR", this.utils.setSubscribedYearsSelectedToGiverYear(
/* 121 */                   subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear()));
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 127 */     } catch (Exception e) {
/*     */       
/* 129 */       ApplicationUtilities.error(getClass(), e, "updateSubcriptionsForm");
/*     */     } 
/*     */     
/* 132 */     return "updateSubcriptionsForm";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/deleteSubcriptionsForm"}, method = {RequestMethod.POST})
/*     */   public String deleteSubcriptionsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 139 */       JSONObject obj = this.subscriptionService.deleteSubcriptionsFormDetails(request);
/*     */       
/* 141 */       String transId = request.getParameter("delete_subscription_transId");
/*     */       
/* 143 */       String memberId = request.getParameter("delete_subscription_memberId");
/* 144 */       if (obj != null) {
/* 145 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/* 147 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 148 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 149 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 150 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 151 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 152 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 153 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 154 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 155 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 156 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 157 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 158 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 159 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 160 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/*     */ 
/*     */           
/* 163 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 164 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 165 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/* 166 */           SubscriptionPayments subscriptionPayments = this.subscriptionService.getSubscriptionDetails(memberId, 
/* 167 */               transId);
/* 168 */           if (subscriptionPayments != null)
/*     */           {
/* 170 */             model.put("PAID_DATE", (
/* 171 */                 new SimpleDateFormat("dd/MM/yyyy")).format(subscriptionPayments.getPaidDate()));
/* 172 */             model.put("PAID_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
/* 173 */             model.put("PAYMENT_STATUS", 
/* 174 */                 this.utils.setSubscriptionPaymentStatus(subscriptionPayments.getPaymentStatus()));
/* 175 */             model.put("RECEIPT_NO", subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo());
/* 176 */             model.put("REMARKS", subscriptionPayments.getRemarks());
/* 177 */             model.put("SUBSCRIPTION_AMOUNT", Integer.valueOf(subscriptionPayments.getSubscriptionAmount()));
/*     */             
/* 179 */             model.put("TRANSACTION_ID", subscriptionPayments.getTransactionId());
/* 180 */             model.put("CATEGORY", subscriptionPayments.getCategory());
/* 181 */             model.put("PAYMENT_CONF_ID", subscriptionPayments.getPaymentConfId());
/* 182 */             model.put("MEMBER_ID", subscriptionPayments.getMemberId());
/* 183 */             model.put("SUBSCRIPTION_YEAR", this.utils.setSubscribedYearsSelectedToGiverYear(
/* 184 */                   subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear()));
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 191 */     } catch (Exception e) {
/*     */       
/* 193 */       ApplicationUtilities.error(getClass(), e, "deleteSubcriptionsForm");
/*     */     } 
/*     */     
/* 196 */     return "deleteSubcriptionsForm";
/*     */   }
/*     */   @RequestMapping(value = {"/updateSubcriptions"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String updateSubcriptions(HttpServletRequest request) {
/* 201 */     String result = "";
/*     */     
/*     */     try {
/* 204 */       result = this.subscriptionService.updateSubcriptions(request);
/* 205 */     } catch (Exception e) {
/* 206 */       ApplicationUtilities.error(getClass(), e, "updateSubcriptions");
/*     */     } 
/*     */     
/* 209 */     return result;
/*     */   }
/*     */   @RequestMapping(value = {"/deleteSubcriptions"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String deleteSubcriptions(HttpServletRequest request) {
/* 214 */     String result = "";
/*     */     try {
/* 216 */       result = this.subscriptionService.deleteSubcriptions(request);
/* 217 */     } catch (Exception e) {
/* 218 */       ApplicationUtilities.error(getClass(), e, "deleteSubcriptions");
/*     */     } 
/*     */     
/* 221 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\controller\SubscriptionController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */