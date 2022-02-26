/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.LoanService;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class LoanController
/*     */ {
/*     */   @Autowired
/*     */   public LoanService loanService;
/*     */   @Autowired
/*     */   public MiscellaneousService miscellaneousService;
/*  30 */   Utils utils = new Utils();
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/sanctionLoanForm"}, method = {RequestMethod.GET})
/*     */   public String sanctionLoanForm(HttpServletRequest request, Map<String, Object> model) {
/*  35 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*     */ 
/*     */     
/*  38 */     return "sanctionLoanForm";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/loanSummaryPage"}, method = {RequestMethod.GET})
/*     */   public String loanSummary(HttpServletRequest request, Map<String, Object> model) {
/*  45 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     return "loanSummary";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getLoanSummary"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getLoanSummary(HttpServletRequest request, Map<String, Object> model) {
/*  59 */     return this.loanService.loanSummary(request);
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/sanctionLoan"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String sanctionLoan(HttpServletRequest request, Map<String, Object> model) {
/*  65 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*  66 */     return this.loanService.sanctionLoan(request);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/payLoanAmountForm"}, method = {RequestMethod.GET})
/*     */   public String payLoanAmountForm(HttpServletRequest request, Map<String, Object> model) {
/*  76 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/*  77 */     return "payLoanAmount";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/payLoanAmount"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String payLoanAmount(HttpServletRequest request, Map<String, Object> model) {
/*  85 */     return this.loanService.payLoanAmount(request);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateLoanRecoveryDetailsForm"}, method = {RequestMethod.POST})
/*     */   public String updateLoanRecoveryDetailsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/*  94 */       JSONObject obj = this.loanService.updateLoanRecoveryDetailsFormDetails(request);
/*  95 */       String transId = request.getParameter("update_LoanRecoveryDetails_transId");
/*  96 */       String memberId = request.getParameter("update_LoanRecoveryDetails_memberId");
/*  97 */       String loanId = request.getParameter("update_LoanRecoveryDetails_loanId");
/*     */       
/*  99 */       if (obj != null) {
/* 100 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/* 102 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 103 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 104 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 105 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 106 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 107 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 108 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 109 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 110 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 111 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 112 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 113 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 114 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 115 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/*     */           
/* 117 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 118 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 119 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/*     */           
/* 121 */           LoanRecoveryDetails loanRecoveryDetails = this.loanService.getLoanRecoveryDetails(memberId, transId, loanId);
/* 122 */           if (loanRecoveryDetails != null)
/*     */           {
/* 124 */             model.put("PAID_DATE", (new SimpleDateFormat("dd/MM/yyyy")).format(loanRecoveryDetails.getPaidDate()));
/* 125 */             model.put("PAID_AMOUNT", Integer.valueOf(loanRecoveryDetails.getPaidAmount()));
/* 126 */             model.put("RECEIPT_NO", loanRecoveryDetails.getReceiptNo());
/* 127 */             model.put("REMARKS", loanRecoveryDetails.getRemarks());
/* 128 */             model.put("TRANSACTION_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getTransactionId());
/* 129 */             model.put("LOAN_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getLoanId());
/* 130 */             model.put("MEMBER_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getMemberId());
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 136 */     } catch (Exception e) {
/*     */       
/* 138 */       e.printStackTrace();
/*     */     } 
/* 140 */     return "updateLoanRecoveryDetailsForm";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/deleteLoanRecoveryDetailsForm"}, method = {RequestMethod.POST})
/*     */   public String deleteLoanRecoveryDetailsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 147 */       JSONObject obj = this.loanService.deleteLoanRecoveryDetailsFormDetails(request);
/* 148 */       String transId = request.getParameter("delete_LoanRecoveryDetails_transId");
/* 149 */       String memberId = request.getParameter("delete_LoanRecoveryDetails_memberId");
/* 150 */       String loanId = request.getParameter("delete_LoanRecoveryDetails_loanId");
/* 151 */       if (obj != null) {
/* 152 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/* 154 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 155 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 156 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 157 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 158 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 159 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 160 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 161 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 162 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 163 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 164 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 165 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 166 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 167 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/* 168 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 169 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 170 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/* 171 */           LoanRecoveryDetails loanRecoveryDetails = this.loanService.getLoanRecoveryDetails(memberId, transId, loanId);
/* 172 */           if (loanRecoveryDetails != null) {
/* 173 */             model.put("PAID_DATE", (new SimpleDateFormat("dd/MM/yyyy")).format(loanRecoveryDetails.getPaidDate()));
/* 174 */             model.put("PAID_AMOUNT", Integer.valueOf(loanRecoveryDetails.getPaidAmount()));
/* 175 */             model.put("RECEIPT_NO", loanRecoveryDetails.getReceiptNo());
/* 176 */             model.put("REMARKS", loanRecoveryDetails.getRemarks());
/* 177 */             model.put("TRANSACTION_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getTransactionId());
/* 178 */             model.put("LOAN_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getLoanId());
/* 179 */             model.put("MEMBER_ID", loanRecoveryDetails.getLoanRecoveryDetailsPK().getMemberId());
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 186 */     } catch (Exception e) {
/*     */       
/* 188 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 191 */     return "deleteLoanRecoveryDetailsForm";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateLoanRecoveryDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String updateLoanRecoveryDetails(HttpServletRequest request) {
/* 197 */     String result = "";
/*     */     
/*     */     try {
/* 200 */       result = this.loanService.updateLoanRecoveryDetails(request);
/* 201 */     } catch (Exception e) {
/* 202 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 205 */     return result;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/deleteLoanRecoveryDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String deleteLoanRecoveryDetails(HttpServletRequest request) {
/* 211 */     String result = "";
/*     */     try {
/* 213 */       result = this.loanService.deleteLoanRecoveryDetails(request);
/* 214 */     } catch (Exception e) {
/* 215 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 218 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/updateLoanSanctionDetailsForm"}, method = {RequestMethod.POST})
/*     */   public String updateLoanSanctionDetailsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 227 */       JSONObject obj = this.loanService.updateLoanSanctionDetailsFormDetails(request);
/*     */       
/* 229 */       String memberId = request.getParameter("update_LoanSanctionDetails_memberId");
/* 230 */       String loanId = request.getParameter("update_LoanSanctionDetails_loanId");
/* 231 */       String loanSanctinedDate = request.getParameter("update_LoanSanctionDetails_loanSanctionDate");
/* 232 */       String loanSanctinedAmount = request.getParameter("update_LoanSanctionDetails_loanAmount");
/* 233 */       String loanSanctinedFatherName = request.getParameter("update_LoanSanctionDetails_fatherName");
/*     */ 
/*     */       
/* 236 */       if (obj != null) {
/* 237 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/* 239 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 240 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 241 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 242 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 243 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 244 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 245 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 246 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 247 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 248 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 249 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 250 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 251 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 252 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/*     */           
/* 254 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 255 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 256 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/*     */           
/* 258 */           model.put("LOAN_SANCTIONED_DATE", loanSanctinedDate);
/* 259 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 260 */           model.put("FATHER_NAME", loanSanctinedFatherName);
/* 261 */           model.put("LOAN_AMOUNT", loanSanctinedAmount);
/* 262 */           model.put("LOAN_ID", loanId);
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 267 */     } catch (Exception e) {
/*     */       
/* 269 */       e.printStackTrace();
/*     */     } 
/* 271 */     return "updateLoanSanctionDetailsForm";
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/deleteLoanSanctionDetailsForm"}, method = {RequestMethod.POST})
/*     */   public String deleteLoanSanctionDetailsForm(HttpServletRequest request, Map<String, Object> model) {
/*     */     try {
/* 278 */       JSONObject obj = this.loanService.deleteLoanRecoveryDetailsFormDetails(request);
/* 279 */       String memberId = request.getParameter("update_LoanSanctionDetails_memberId");
/* 280 */       String loanId = request.getParameter("update_LoanSanctionDetails_loanId");
/* 281 */       String loanSanctinedDate = request.getParameter("update_LoanSanctionDetails_loanSanctionDate");
/* 282 */       String loanSanctinedAmount = request.getParameter("update_LoanSanctionDetails_loanAmount");
/* 283 */       String loanSanctinedFatherName = request.getParameter("update_LoanSanctionDetails_fatherName");
/*     */       
/* 285 */       if (obj != null) {
/* 286 */         String topPanelresultCode = (String)obj.get("TOP_PANEL_RESULT_CODE");
/*     */         
/* 288 */         if (topPanelresultCode != null && !"".equalsIgnoreCase(topPanelresultCode) && 
/* 289 */           "400".equalsIgnoreCase(topPanelresultCode)) {
/* 290 */           JSONObject objectTopPanel = (JSONObject)obj.get("TOP_PANEL_DETAILS");
/* 291 */           model.put("CARD_NO", objectTopPanel.get("CARD_NO"));
/* 292 */           model.put("DEPT_ID", objectTopPanel.get("DEPT_ID"));
/* 293 */           model.put("MEMBER_ID", objectTopPanel.get("MEMBER_ID"));
/* 294 */           model.put("DEPT_NAME", objectTopPanel.get("DEPT_NAME"));
/* 295 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 296 */           model.put("PAYMENT_RECEIPT_NO", objectTopPanel.get("PAYMENT_RECEIPT_NO"));
/* 297 */           model.put("PERMINENT_ADDRESS", objectTopPanel.get("PERMINENT_ADDRESS"));
/* 298 */           model.put("REGISTERED_DATE", objectTopPanel.get("REGISTERED_DATE"));
/* 299 */           model.put("DATE_OF_BIRTH", objectTopPanel.get("DATE_OF_BIRTH"));
/* 300 */           model.put("CURRENT_LOAN_BALANCE", objectTopPanel.get("CURRENT_LOAN_BALANCE"));
/* 301 */           model.put("CARD_BALANCE", objectTopPanel.get("CARD_BALANCE"));
/* 302 */           model.put("PHONE_NO", objectTopPanel.get("PHONE_NO"));
/* 303 */           model.put("FILE_CONTENT", objectTopPanel.get("FILE_CONTENT"));
/* 304 */           model.put("FILE_TYPE", objectTopPanel.get("FILE_TYPE"));
/*     */           
/* 306 */           model.put("LOAN_SANCTIONED_DATE", loanSanctinedDate);
/* 307 */           model.put("FIRST_NAME", objectTopPanel.get("FIRST_NAME"));
/* 308 */           model.put("FATHER_NAME", loanSanctinedFatherName);
/* 309 */           model.put("LOAN_AMOUNT", loanSanctinedAmount);
/* 310 */           model.put("LOAN_ID", loanId);
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 316 */     catch (Exception e) {
/*     */       
/* 318 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 321 */     return "deleteLoanSanctionDetailsForm";
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/updateLoanSanctionDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String updateLoanSanctionDetails(HttpServletRequest request) {
/* 327 */     String result = "";
/*     */     
/*     */     try {
/* 330 */       result = this.loanService.updateSanctionDetails(request);
/* 331 */     } catch (Exception e) {
/* 332 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 335 */     return result;
/*     */   }
/*     */   @RequestMapping(value = {"/deleteLoanSanctionDetails"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String deleteLoanSanctionDetails(HttpServletRequest request) {
/* 340 */     String result = "";
/*     */     try {
/* 342 */       result = this.loanService.deleteLoanSanctionDetails(request);
/* 343 */     } catch (Exception e) {
/* 344 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 347 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getLoanDetails"}, method = {RequestMethod.GET})
/*     */   public String getLoanDetails(HttpServletRequest request, Map<String, Object> model) {
/* 356 */     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
/* 357 */     return this.loanService.getLoanDetails(request);
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\controller\LoanController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */