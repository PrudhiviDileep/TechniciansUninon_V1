/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPaymentsPK;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.transaction.Transactional;
/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ public class SubscriptionDAO
/*     */ {
/*     */   @Autowired
/*     */   public DataAccess dataAccess;
/*     */   @Autowired
/*     */   public SearchDAO searchDaO;
/*     */   @Autowired
/*     */   private IdGenerator idGenerator;
/*     */   @Autowired
/*     */   public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO appDAO;
/*     */   @Autowired
/*     */   public MiscellaneousDAO miscellaneousDAO;
/*  37 */   Utils utils = new Utils();
/*     */ 
/*     */   
/*     */   public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
/*  42 */     return "getSubscriptionDetatils";
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public String paySubscriptionAmount(HttpServletRequest request) {
/*  48 */     JSONObject result = new JSONObject();
/*     */ 
/*     */     
/*     */     try {
/*  52 */       String cardNo = request.getParameter("cardNo");
/*  53 */       String deptId = request.getParameter("deptId");
/*  54 */       String memberId = request.getParameter("memberId");
/*  55 */       String paymentConfId = request.getParameter("paymentConfId");
/*  56 */       String subscriptionAmount = request.getParameter("subscriptionAmount");
/*  57 */       String subscriptionYear = request.getParameter("subscriptionYear");
/*  58 */       String paidAmount = request.getParameter("paidAmount");
/*  59 */       String paidDate = request.getParameter("paidDate");
/*  60 */       String receiptNo = request.getParameter("receiptNo");
/*  61 */       String paymentStatus = request.getParameter("paymentStatus");
/*  62 */       String remarks = request.getParameter("remarks");
/*     */       
/*  64 */       JSONObject validateJsnObj = new JSONObject();
/*  65 */       validateJsnObj.put("MEMBER_ID", memberId);
/*  66 */       validateJsnObj.put("CARD_NO", cardNo);
/*  67 */       validateJsnObj.put("DEPT_ID", deptId);
/*  68 */       validateJsnObj.put("PAYMENT_CONF_ID", paymentConfId);
/*  69 */       validateJsnObj.put("SUBSCRIPTION_AMOUNT", subscriptionAmount);
/*  70 */       validateJsnObj.put("SUBSCRIBING_YEAR", subscriptionYear);
/*  71 */       validateJsnObj.put("PAID_AMOUNT", paidAmount);
/*  72 */       validateJsnObj.put("PAID_DATE", paidDate);
/*  73 */       validateJsnObj.put("RECEIPT_NO", receiptNo);
/*  74 */       validateJsnObj.put("PAYMENT_STATUS", paymentStatus);
/*     */ 
/*     */ 
/*     */       
/*  78 */       String validationResult = subscriptionValidation(validateJsnObj);
/*  79 */       if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult))
/*     */       {
/*  81 */         SubscriptionPayments subscriptionPayments = new SubscriptionPayments();
/*  82 */         subscriptionPayments.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/*  83 */         subscriptionPayments.setCategory("SUBSCRIPTION");
/*  84 */         subscriptionPayments.setSubscriptionAmount(Integer.parseInt(paidAmount));
/*  85 */         subscriptionPayments.setPaymentConfId(paymentConfId);
/*  86 */         subscriptionPayments.setPaymentStatus(paymentStatus);
/*     */         
/*  88 */         subscriptionPayments.setRemarks(remarks);
/*  89 */         subscriptionPayments.setSubscriptionAmount(Integer.parseInt(subscriptionAmount));
/*     */         
/*  91 */         SubscriptionPaymentsPK subscriptionPaymentsPK = new SubscriptionPaymentsPK();
/*  92 */         subscriptionPaymentsPK.setSubscriptionYear(Integer.parseInt(subscriptionYear));
/*  93 */         subscriptionPaymentsPK.setReceiptNo(receiptNo);
/*  94 */         subscriptionPayments.setMemberId(memberId);
/*  95 */         subscriptionPaymentsPK.setDeptId(deptId);
/*     */         
/*  97 */         subscriptionPayments.setTransactionId(this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID"));
/*  98 */         subscriptionPayments.setSubscriptionPaymentsPK(subscriptionPaymentsPK);
/*     */         
/* 100 */         this.dataAccess.save(subscriptionPayments);
/*     */         
/* 102 */         result.put("FINAL_RESULT_CODE", "400");
/* 103 */         result.put("DATA_DETAILS", "Subscriptoin amount paid sucessfullty!");
/*     */       }
/*     */       else
/*     */       {
/* 107 */         result.put("FINAL_RESULT_CODE", "200");
/* 108 */         result.put("ERROR_MSG", validationResult);
/*     */       }
/*     */     
/*     */     }
/* 112 */     catch (NumberFormatException nfe) {
/* 113 */       ApplicationUtilities.error(getClass(), nfe, "paySubscriptionAmount");
/* 114 */       result.put("FINAL_RESULT_CODE", "300");
/* 115 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount/SubscriptionAmount/SubscriptionYear");
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       ApplicationUtilities.error(getClass(), e, "paySubscriptionAmount");
/* 119 */       result.put("FINAL_RESULT_CODE", "300");
/* 120 */       result.put("ERROR_MSG", "Unable to Subscibe due to " + e.getMessage());
/*     */     } 
/* 122 */     return result.toJSONString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public JSONObject updateSubcriptionsFormDetails(HttpServletRequest request) {
/* 129 */     JSONObject resultObj = new JSONObject();
/*     */     
/*     */     try {
/* 132 */       String cardNo = request.getParameter("update_subscription_cardNo");
/* 133 */       int cardNumber = Integer.parseInt(cardNo);
/* 134 */       String deptId = request.getParameter("update_subscription_deptId");
/* 135 */       String pageId = request.getParameter("update_scubscription_pageId");
/* 136 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/* 137 */     } catch (Exception e) {
/*     */       
/* 139 */       ApplicationUtilities.error(getClass(), e, "updateSubcriptionsFormDetails");
/*     */     } 
/*     */     
/* 142 */     return resultObj;
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public JSONObject deleteSubcriptionsFormDetails(HttpServletRequest request) {
/* 148 */     JSONObject resultObj = new JSONObject();
/*     */     try {
/* 150 */       String cardNo = request.getParameter("delete_subscription_cardNo");
/* 151 */       int cardNumber = Integer.parseInt(cardNo);
/* 152 */       String deptId = request.getParameter("delete_subscription_deptId");
/* 153 */       String pageId = request.getParameter("delete_scubscription_pageId");
/*     */       
/* 155 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/* 156 */     } catch (Exception e) {
/*     */       
/* 158 */       ApplicationUtilities.error(getClass(), e, "deleteSubcriptionsFormDetails");
/*     */     } 
/*     */     
/* 161 */     return resultObj;
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   public SubscriptionPayments getSubscriptionDetails(String memberId, String transId) {
/* 166 */     JSONObject resultObj = new JSONObject();
/* 167 */     SubscriptionPayments subscriptionPayments = null;
/*     */     
/*     */     try {
/* 170 */       String query = "from SubscriptionPayments where memberId =:memberId and transactionId =:transactionId";
/* 171 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 172 */       parametersMap.put("memberId", memberId);
/* 173 */       parametersMap.put("transactionId", transId);
/* 174 */       List<SubscriptionPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 175 */       if (list != null && list.size() > 0)
/*     */       {
/* 177 */         subscriptionPayments = list.get(0);
/*     */       
/*     */       }
/*     */     }
/* 181 */     catch (Exception e) {
/*     */       
/* 183 */       ApplicationUtilities.error(getClass(), e, "getSubscriptionDetails");
/*     */     } 
/*     */     
/* 186 */     return subscriptionPayments;
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   public String updateSubcriptions(HttpServletRequest request) {
/* 191 */     JSONObject result = new JSONObject();
/*     */     try {
/* 193 */       String cardNo = request.getParameter("cardNo");
/* 194 */       String deptId = request.getParameter("deptId");
/* 195 */       String category = request.getParameter("category");
/* 196 */       String memberId = request.getParameter("memberId");
/* 197 */       String transactionId = request.getParameter("transactionId");
/* 198 */       String subscriptionYear = request.getParameter("subscriptionYear");
/* 199 */       String paidDate = request.getParameter("paidDate");
/* 200 */       String paidAmount = request.getParameter("paidAmount");
/* 201 */       String receiptNo = request.getParameter("receiptNo");
/* 202 */       String paymentStatus = request.getParameter("paymentStatus");
/* 203 */       String remarks = request.getParameter("remarks");
/* 204 */       String subscriptionAmount = request.getParameter("subscriptionAmount");
/* 205 */       String paymentConfId = request.getParameter("paymentConfId");
/*     */       
/* 207 */       JSONObject obj = new JSONObject();
/*     */       
/* 209 */       obj.put("CARD_NO", cardNo);
/* 210 */       obj.put("DEPT_ID", deptId);
/* 211 */       obj.put("MEMBER_ID", memberId);
/* 212 */       obj.put("PAYMENT_CONF_ID", paymentConfId);
/* 213 */       obj.put("SUBSCRIPTION_AMOUNT", subscriptionAmount);
/* 214 */       obj.put("SUBSCRIBING_YEAR", subscriptionYear);
/* 215 */       obj.put("PAID_AMOUNT", paidAmount);
/* 216 */       obj.put("PAID_DATE", paidDate);
/* 217 */       obj.put("RECEIPT_NO", receiptNo);
/* 218 */       obj.put("PAYMENT_STATUS", paymentStatus);
/*     */ 
/*     */       
/* 221 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {
/*     */ 
/*     */         
/* 224 */         String validationResult = subscriptionValidation(obj);
/* 225 */         if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {
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
/* 250 */           String updateQuery = "update SubscriptionPayments set  paymentStatus=:paymentStatus,  paidDate=:paidDate , subscriptionPaymentsPK.receiptNo=:receiptNo ,  paidAmount=:paidAmount ,  subscriptionAmount=:subscriptionAmount ,  subscriptionPaymentsPK.subscriptionYear=:subscriptionYear ,  paymentConfId=:paymentConfId ,  remarks=:remarks   where transactionId=:transactionId and memberId=:memberId ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 260 */           Map<String, Object> parametersMap = new HashMap<String, Object>();
/*     */           
/* 262 */           parametersMap.put("memberId", memberId);
/* 263 */           parametersMap.put("transactionId", transactionId);
/*     */           
/* 265 */           parametersMap.put("paymentStatus", paymentStatus);
/* 266 */           parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/* 267 */           parametersMap.put("receiptNo", receiptNo);
/* 268 */           parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
/* 269 */           parametersMap.put("subscriptionAmount", Integer.valueOf(Integer.parseInt(subscriptionAmount)));
/* 270 */           parametersMap.put("subscriptionYear", Integer.valueOf(Integer.parseInt(subscriptionYear)));
/* 271 */           parametersMap.put("paymentConfId", paymentConfId);
/* 272 */           parametersMap.put("remarks", remarks);
/*     */           
/* 274 */           this.dataAccess.updateQuery(updateQuery, parametersMap);
/*     */           
/* 276 */           result.put("FINAL_RESULT_CODE", "400");
/* 277 */           result.put("DATA_DETAILS", "Subscriptoin Upadated Sucessfullty!");
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 287 */           result.put("FINAL_RESULT_CODE", "200");
/* 288 */           result.put("ERROR_MSG", validationResult);
/*     */         } 
/*     */       } else {
/*     */         
/* 292 */         result.put("FINAL_RESULT_CODE", "200");
/* 293 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
/*     */       }
/*     */     
/*     */     }
/* 297 */     catch (NumberFormatException nfe) {
/* 298 */       ApplicationUtilities.error(getClass(), nfe, "updateSubcriptions");
/* 299 */       result.put("FINAL_RESULT_CODE", "300");
/* 300 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*     */     }
/* 302 */     catch (Exception e) {
/* 303 */       ApplicationUtilities.error(getClass(), e, "updateSubcriptions");
/* 304 */       result.put("FINAL_RESULT_CODE", "300");
/* 305 */       result.put("ERROR_MSG", "Unable to update subscritions!");
/*     */     } 
/*     */     
/* 308 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public String deleteSubcriptions(HttpServletRequest request) {
/* 314 */     JSONObject result = new JSONObject();
/*     */     
/*     */     try {
/* 317 */       String category = request.getParameter("category");
/* 318 */       String memberId = request.getParameter("memberId");
/* 319 */       String transactionId = request.getParameter("transactionId");
/* 320 */       String subscriptionYear = request.getParameter("subscriptionYear");
/* 321 */       String paidDate = request.getParameter("paidDate");
/* 322 */       String paidAmount = request.getParameter("paidAmount");
/* 323 */       String receiptNo = request.getParameter("receiptNo");
/* 324 */       String paymentStatus = request.getParameter("paymentStatus");
/* 325 */       String remarks = request.getParameter("remarks");
/* 326 */       String subscriptionAmount = request.getParameter("subscriptionAmount");
/* 327 */       String paymentConfId = request.getParameter("paymentConfId");
/*     */ 
/*     */ 
/*     */       
/* 331 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId))
/*     */       {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 352 */         String hql = "delete from SUBSCRIPTION_PAYMENTS where member_Id= :memberId AND transaction_Id= :transactionId";
/* 353 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 354 */         parametersMap.put("memberId", memberId);
/* 355 */         parametersMap.put("transactionId", transactionId);
/* 356 */         int count = this.dataAccess.executeUpdateSQL(hql, parametersMap);
/* 357 */         result.put("FINAL_RESULT_CODE", "400");
/* 358 */         result.put("DATA_DETAILS", "Deleted Sucessfullty!");
/*     */       }
/*     */       else
/*     */       {
/* 362 */         result.put("FINAL_RESULT_CODE", "200");
/* 363 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for delete Subscriptions!");
/*     */       }
/*     */     
/*     */     }
/* 367 */     catch (NumberFormatException nfe) {
/* 368 */       ApplicationUtilities.error(getClass(), nfe, "deleteSubcriptions");
/* 369 */       result.put("FINAL_RESULT_CODE", "300");
/* 370 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*     */     }
/* 372 */     catch (Exception e) {
/* 373 */       ApplicationUtilities.error(getClass(), e, "deleteSubcriptions");
/* 374 */       result.put("FINAL_RESULT_CODE", "300");
/* 375 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*     */     } 
/*     */     
/* 378 */     return result.toString();
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   public JSONArray getUnsubsribedYears(String deptId, String cardNo) {
/* 383 */     JSONArray resultArray = new JSONArray();
/*     */     
/*     */     try {
/* 386 */       Registration registeredMember = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
/* 387 */       int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/* 388 */       int joinedYear = 
/* 389 */         Integer.parseInt((new SimpleDateFormat("yyyy")).format(registeredMember.getRegisteredDate()));
/* 390 */       List list = null;
/* 391 */       String query = "from SubscriptionPayments where memberId =:memberId  and paymentStatus=:paymentStatus and subscriptionYear=:subscriptionYear  ";
/* 392 */       for (int i = joinedYear; i <= currentYear; i++) {
/* 393 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 394 */         parametersMap.put("memberId", registeredMember.getRegistrationPK().getMemberId());
/* 395 */         parametersMap.put("paymentStatus", "Yes");
/* 396 */         parametersMap.put("subscriptionYear", Integer.valueOf(i));
/* 397 */         list = this.dataAccess.queryWithParams(query, parametersMap);
/* 398 */         if (list != null && list.size() > 0) {
/* 399 */           resultArray.add(Integer.valueOf(i));
/*     */         }
/*     */       } 
/* 402 */     } catch (Exception e) {
/*     */       
/* 404 */       ApplicationUtilities.error(getClass(), e, "getUnsubsribedYears");
/*     */     } 
/*     */     
/* 407 */     return resultArray;
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   public String subscriptionValidation(JSONObject obj) {
/* 412 */     String result = "Sorry Registration Failed !";
/*     */ 
/*     */     
/*     */     try {
/* 416 */       if (obj != null)
/*     */       {
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
/* 442 */         String cardNo = (String)obj.get("CARD_NO");
/* 443 */         String deptId = (String)obj.get("DEPT_ID");
/* 444 */         String memberId = (String)obj.get("MEMBER_ID");
/* 445 */         String paymentConfId = (String)obj.get("PAYMENT_CONF_ID");
/* 446 */         String subscriptionAmount = (String)obj.get("SUBSCRIPTION_AMOUNT");
/* 447 */         String subscriptionYear = (String)obj.get("SUBSCRIBING_YEAR");
/* 448 */         String paidAmount = (String)obj.get("PAID_AMOUNT");
/* 449 */         String paidDate = (String)obj.get("PAID_DATE");
/* 450 */         String receiptNo = (String)obj.get("RECEIPT_NO");
/* 451 */         String paymentStatus = (String)obj.get("PAYMENT_STATUS");
/*     */ 
/*     */         
/* 454 */         if (subscriptionYear == null || "".equalsIgnoreCase(subscriptionYear))
/*     */         {
/* 456 */           return "Please Select Subscription Year.";
/*     */         }
/*     */         
/* 459 */         if (paymentStatus == null || "".equalsIgnoreCase(paymentStatus) || 
/* 460 */           "No".equalsIgnoreCase(paymentStatus))
/*     */         {
/* 462 */           return "Please Select Subscription Payment Status.";
/*     */         }
/* 464 */         if (receiptNo == null || "".equalsIgnoreCase(receiptNo))
/*     */         {
/* 466 */           return "Please Enter ReceiptNo ";
/*     */         }
/* 468 */         if (paidDate == null || "".equalsIgnoreCase(paidDate))
/*     */         {
/* 470 */           return "Please Select Paid Date Date.";
/*     */         }
/* 472 */         if (cardNo == null || "".equalsIgnoreCase(cardNo))
/*     */         {
/* 474 */           return "Please Select Card No.";
/*     */         }
/* 476 */         if (deptId == null || "".equalsIgnoreCase(deptId) || "SELECT".equalsIgnoreCase(deptId))
/*     */         {
/* 478 */           return "Please select member department.";
/*     */         }
/* 480 */         if (subscriptionAmount == null || "".equalsIgnoreCase(subscriptionAmount))
/*     */         {
/* 482 */           return "Please Enter Subscription Amount."; } 
/* 483 */         if (paymentConfId == null || "".equalsIgnoreCase(paymentConfId) || 
/* 484 */           "SELECT".equalsIgnoreCase(paymentConfId))
/*     */         {
/* 486 */           return "Please select payment type.";
/*     */         }
/*     */         
/* 489 */         if (!this.utils.isValidDate(paidDate)) {
/* 490 */           return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
/*     */         }
/*     */ 
/*     */         
/* 494 */         if (!this.utils.isNumericString(subscriptionAmount)) {
/* 495 */           return "Please enter only numbers in Subscription Amount";
/*     */         }
/*     */         
/* 498 */         if (!this.utils.isNumericString(paidAmount)) {
/* 499 */           return "Please enter only numbers in PaidAmount";
/*     */         }
/*     */         
/* 502 */         if (this.miscellaneousDAO.isDuplicateReceiptNumberInRegistration(receiptNo)) {
/* 503 */           return "Receipt no already used!";
/*     */         }
/*     */         
/* 506 */         String dupliacateRecptNoMemberId = this.miscellaneousDAO.isDuplicateReceiptNumberInSubscriptionPayments(receiptNo, memberId);
/* 507 */         if (dupliacateRecptNoMemberId != null && !"".equals(dupliacateRecptNoMemberId)) {
/*     */           
/* 509 */           Registration member = this.miscellaneousDAO.getMemberDetailsByMemberId(dupliacateRecptNoMemberId);
/* 510 */           return "Receipt number " + receiptNo + " is already used in Subscription for DEPARTMENT = " + member.getDeptName() + " CARD NO = " + member.getRegistrationPK().getCardNo();
/*     */         } 
/*     */         
/* 513 */         return "SUCCESS";
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 519 */     catch (Exception e) {
/*     */ 
/*     */       
/* 522 */       ApplicationUtilities.error(getClass(), e, "subscriptionValidation");
/*     */     } 
/* 524 */     return result;
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\SubscriptionDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */