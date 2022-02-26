/*      */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*      */ 
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.NotValidAmountException;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetailsPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Loandetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoandetailsPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.transaction.Transactional;
/*      */ import org.json.simple.JSONArray;
/*      */ import org.json.simple.JSONObject;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Repository;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @Repository
/*      */ public class LoanDAO
/*      */ {
/*      */   @Autowired
/*      */   public DataAccess dataAccess;
/*      */   @Autowired
/*      */   IdGenerator idGenerator;
/*      */   @Autowired
/*      */   MiscellaneousDAO miscellaneousDAO;
/*   43 */   Utils utils = new Utils();
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String sanctionLoan(HttpServletRequest request) {
/*   49 */     String result = "Sorry System failed to Sanction Loan ";
/*   50 */     JSONObject resutlObject = new JSONObject();
/*      */     
/*      */     try {
/*   53 */       String memberId = request.getParameter("memberId");
/*   54 */       Registration registredUserDetials = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);
/*   55 */       String currentloadIdFromRegister = registredUserDetials.getCurrentLoanId();
/*   56 */       String currentLoanStatusFromRegister = registredUserDetials.getCurrentLoanStatus();
/*   57 */       currentLoanStatusFromRegister = "LOAN_NOT_SANCTIONED";
/*   58 */       if (currentLoanStatusFromRegister != null && !"".equalsIgnoreCase(currentLoanStatusFromRegister))
/*      */       {
/*   60 */         if ("LOAN_NOT_SANCTIONED".equalsIgnoreCase(currentLoanStatusFromRegister) || 
/*   61 */           "LOAN_CLOSED".equalsIgnoreCase(currentLoanStatusFromRegister)) {
/*      */           
/*   63 */           String loanAmount = request.getParameter("loanAmount");
/*   64 */           String loanSanctioneddDate = request.getParameter("loanSanctioneddDate");
/*   65 */           if (this.utils.isNumericString(loanAmount)) {
/*   66 */             String loanId = this.idGenerator.get("LOAN_ID", "LOAN_ID");
/*   67 */             if (loanId != null && !"".equals(loanId)) {
/*   68 */               Loandetails loanDetails = new Loandetails();
/*   69 */               LoandetailsPK loandetailsPK = new LoandetailsPK();
/*   70 */               loandetailsPK.setLoanId(loanId);
/*   71 */               loandetailsPK.setMemberId(memberId);
/*   72 */               loanDetails.setLoandetailsPK(loandetailsPK);
/*   73 */               loanDetails.setLoanAmount(Integer.parseInt(loanAmount));
/*   74 */               loanDetails.setLoanSanctionedDate((new SimpleDateFormat("dd/MM/yyyy")).parse(loanSanctioneddDate));
/*   75 */               loanDetails.setMemberName(registredUserDetials.getFirstName());
/*   76 */               loanDetails.setRemarks(request.getParameter("remarks"));
/*   77 */               loanDetails.setLoanStatus("LOAN_UNDER_RECOVERY");
/*   78 */               Object isSaved = this.dataAccess.save(loanDetails);
/*      */               
/*   80 */               if (isSaved instanceof Loandetails && isSaved != null) {
/*      */                 
/*   82 */                 registredUserDetials.setCurrentLoanId(loanId);
/*   83 */                 registredUserDetials.setCurrentLoanStatus("LOAN_UNDER_RECOVERY");
/*   84 */                 registredUserDetials.setCurrentLoanBalance(loanAmount);
/*   85 */                 registredUserDetials.setCurrentLoanIssuedAmount(loanAmount);
/*      */                 
/*   87 */                 result = "Loan sanctioned sucessfully!";
/*   88 */                 resutlObject.put("FINAL_RESULT_CODE", "400");
/*   89 */                 resutlObject.put("DATA_DETAILS", result);
/*      */               } else {
/*   91 */                 result = " Failed to sanctioned Loan due to system not able to update loan deails in Register ";
/*   92 */                 resutlObject.put("FINAL_RESULT_CODE", "200");
/*   93 */                 resutlObject.put("ERROR_MSG", result);
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*   98 */               result = "Sorry System failed to Sanction Loan due to IdGenerator Failure !";
/*   99 */               resutlObject.put("FINAL_RESULT_CODE", "200");
/*  100 */               resutlObject.put("ERROR_MSG", result);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  105 */             throw new NotValidAmountException("Not Valid Amount !", loanAmount);
/*      */           }
/*      */         
/*  108 */         } else if ("LOAN_UNDER_RECOVERY".equalsIgnoreCase(currentLoanStatusFromRegister)) {
/*  109 */           result = " This member already took loan, please clear the old loan first ";
/*  110 */           resutlObject.put("FINAL_RESULT_CODE", "200");
/*  111 */           resutlObject.put("ERROR_MSG", result);
/*      */         } else {
/*      */           
/*  114 */           result = " This member already took loan, please clear the old loan first ";
/*  115 */           resutlObject.put("FINAL_RESULT_CODE", "200");
/*  116 */           resutlObject.put("ERROR_MSG", result);
/*      */         }
/*      */       
/*      */       }
/*  120 */     } catch (NotValidAmountException nva) {
/*  121 */       nva.printStackTrace();
/*  122 */       result = " Sorry System failed to Sanction loan because User entered invalid loamount! >> " + 
/*  123 */         nva.getAmmount();
/*  124 */       resutlObject.put("FINAL_RESULT_CODE", "300");
/*  125 */       resutlObject.put("ERROR_MSG", result);
/*  126 */     } catch (Exception e) {
/*      */       
/*  128 */       e.printStackTrace();
/*  129 */       result = String.valueOf(String.valueOf(String.valueOf(result))) + " :: " + e.getMessage();
/*  130 */       resutlObject.put("FINAL_RESULT_CODE", "300");
/*  131 */       resutlObject.put("ERROR_MSG", result);
/*      */     } 
/*      */ 
/*      */     
/*  135 */     return resutlObject.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String loanSummary(HttpServletRequest request) {
/*  143 */     JSONArray arrObj = new JSONArray();
/*  144 */     String table = "";
/*      */ 
/*      */     
/*      */     try {
/*  148 */       String query = "SELECT R.DEPT_ID,R.FIRST_NAME,R.CARD_NO ,LD.LOAN_AMOUNT,SUM(L.PAID_AMOUNT) as TOTALPAID_AMOUNT , L.MEMBER_ID FROM TEST.LOAN_RECOVERY_DETAILS L, TEST.REGISTRATION R  ,TEST.LOANDETAILS LD  WHERE R.MEMBER_ID=L.MEMBER_ID  AND LD.LOAN_ID =L.LOAN_ID   GROUP BY L.MEMBER_ID           ORDER BY      R.DEPT_ID DESC";
/*      */ 
/*      */ 
/*      */       
/*  152 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */       
/*  154 */       List<Object[]> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/*      */       
/*  156 */       table = "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>";
/*  157 */       table = String.valueOf(String.valueOf(String.valueOf(table))) + "<thead><tr><th>Department</th><th>Name</th><th>Card No</th><th>Loan Amount</th><th>Total Paid</th></tr></thead>";
/*  158 */       if (list != null && list.size() > 0) {
/*  159 */         table = String.valueOf(String.valueOf(String.valueOf(table))) + "<tbody>";
/*  160 */         for (int i = 0; i < list.size(); i++) {
/*  161 */           Object[] objectArr = list.get(i);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  170 */           table = String.valueOf(String.valueOf(String.valueOf(table))) + "<tr>" + 
/*  171 */             "<td>" + this.utils.convertNullToEmptyString(objectArr[0]) + "</td>" + 
/*  172 */             "<td>" + this.utils.convertNullToEmptyString(objectArr[1]) + "</td>" + 
/*  173 */             "<td>" + this.utils.convertNullToEmptyString(objectArr[2]) + "</td>" + 
/*  174 */             "<td>" + this.utils.convertNullToEmptyString(objectArr[3]) + "</td>" + 
/*  175 */             "<td>" + this.utils.convertNullToEmptyString(objectArr[4]) + "</td>" + 
/*  176 */             "</tr>";
/*      */         } 
/*      */         
/*  179 */         table = String.valueOf(String.valueOf(String.valueOf(table))) + "</tbody>";
/*      */       } else {
/*  181 */         table = String.valueOf(String.valueOf(String.valueOf(table))) + "<tbody><tr><td colspan='5' align='center'> No Data found</td></tr></tbody>";
/*      */       } 
/*      */       
/*  184 */       table = String.valueOf(String.valueOf(String.valueOf(table))) + "</table>";
/*      */ 
/*      */     
/*      */     }
/*  188 */     catch (Exception e) {
/*      */       
/*  190 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  193 */     return table;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String payLoanAmount(HttpServletRequest request) {
/*  202 */     JSONObject result = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/*  206 */       String cardNo = request.getParameter("cardNo");
/*  207 */       String deptId = request.getParameter("deptId");
/*  208 */       String memberId = request.getParameter("memberId");
/*  209 */       String paidAmount = request.getParameter("payingAmount");
/*  210 */       String paidDate = request.getParameter("paidDate");
/*  211 */       String receiptNo = request.getParameter("receiptNo");
/*  212 */       String remarks = request.getParameter("remarks");
/*      */ 
/*      */       
/*  215 */       JSONObject validateJsnObj = new JSONObject();
/*  216 */       validateJsnObj.put("MEMBER_ID", memberId);
/*  217 */       validateJsnObj.put("CARD_NO", cardNo);
/*  218 */       validateJsnObj.put("DEPT_ID", deptId);
/*  219 */       validateJsnObj.put("PAID_AMOUNT", paidAmount);
/*  220 */       validateJsnObj.put("PAID_DATE", paidDate);
/*  221 */       validateJsnObj.put("RECEIPT_NO", receiptNo);
/*  222 */       Registration registeredDetails = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
/*      */ 
/*      */       
/*  225 */       String validationResult = loanPaymentValidation(validateJsnObj);
/*  226 */       if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {
/*  227 */         LoanRecoveryDetails loanRecoveryDetails = new LoanRecoveryDetails();
/*  228 */         LoanRecoveryDetailsPK loanRecoveryDetailsPK = new LoanRecoveryDetailsPK();
/*  229 */         loanRecoveryDetails.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/*  230 */         loanRecoveryDetails.setPaidAmount(Integer.parseInt(paidAmount));
/*  231 */         loanRecoveryDetails.setRemarks(remarks);
/*  232 */         loanRecoveryDetails.setReceiptNo(receiptNo);
/*  233 */         loanRecoveryDetailsPK.setLoanId(registeredDetails.getCurrentLoanId());
/*  234 */         loanRecoveryDetailsPK.setMemberId(memberId);
/*  235 */         loanRecoveryDetailsPK.setTransactionId(this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID"));
/*  236 */         loanRecoveryDetails.setLoanRecoveryDetailsPK(loanRecoveryDetailsPK);
/*  237 */         this.dataAccess.save(loanRecoveryDetails);
/*  238 */         this.miscellaneousDAO.updateLoanBalance(memberId);
/*  239 */         result.put("FINAL_RESULT_CODE", "400");
/*  240 */         result.put("DATA_DETAILS", "Loan amount paid sucessfullty!");
/*      */       }
/*      */       else {
/*      */         
/*  244 */         result.put("FINAL_RESULT_CODE", "200");
/*  245 */         result.put("ERROR_MSG", validationResult);
/*      */       }
/*      */     
/*      */     }
/*  249 */     catch (NumberFormatException nfe) {
/*  250 */       nfe.printStackTrace();
/*  251 */       result.put("FINAL_RESULT_CODE", "300");
/*  252 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/*  254 */     catch (Exception e) {
/*  255 */       e.printStackTrace();
/*  256 */       result.put("FINAL_RESULT_CODE", "300");
/*  257 */       result.put("ERROR_MSG", "Unable to pay loan amount" + e.getMessage());
/*      */     } 
/*  259 */     return result.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLoanDetails(HttpServletRequest request) {
/*  265 */     String result = "";
/*      */     try {
/*  267 */       String memberId = request.getParameter("memberId");
/*  268 */       String currentloadIdFromRegister = request.getParameter("currentloadIdFromRegister");
/*  269 */       String currentLoanStatusFromRegister = request.getParameter("currentLoanStatusFromRegister");
/*  270 */       result = getLoanDetails(memberId, currentloadIdFromRegister, currentLoanStatusFromRegister);
/*      */     }
/*  272 */     catch (Exception e) {
/*      */       
/*  274 */       e.printStackTrace();
/*      */     } 
/*  276 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLoanSanctionedDetails(String memberId) {
/*  282 */     String result = "";
/*  283 */     JSONArray loanDetalsArr = new JSONArray();
/*  284 */     JSONArray loanRecoveryDetalsArr = new JSONArray();
/*  285 */     JSONObject topPanelResultObj = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/*  289 */       if (memberId != null && !"".equalsIgnoreCase(memberId))
/*      */       {
/*      */         
/*  292 */         String query = " from Loandetails where loandetailsPK.memberId=:memberId ";
/*  293 */         Map<String, Object> parameters = new HashMap<String, Object>();
/*  294 */         parameters.put("memberId", memberId.trim());
/*      */         
/*  296 */         List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);
/*      */         
/*  298 */         JSONObject loanDetailsObj = new JSONObject();
/*  299 */         if (loandetailsList != null && loandetailsList.size() > 0)
/*      */         {
/*  301 */           for (int i = 0; i < loandetailsList.size(); i++) {
/*  302 */             Loandetails LoandetailsObj = loandetailsList.get(i);
/*  303 */             JSONObject jsnObj = new JSONObject();
/*  304 */             String ccc = this.utils.convertNullToEmptyString(LoandetailsObj.getLoanSanctionedDate());
/*  305 */             String[] date = ccc.split("-");
/*  306 */             jsnObj.put("LOAN_SANCTIONED_DATE", String.valueOf(String.valueOf(String.valueOf(date[2]))) + "/" + date[1] + "/" + date[0]);
/*      */             
/*  308 */             jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(Integer.valueOf(LoandetailsObj.getLoanAmount())));
/*  309 */             jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(LoandetailsObj.getLoandetailsPK().getLoanId()));
/*  310 */             jsnObj.put("MEMBER_ID", this.utils.convertNullToEmptyString(LoandetailsObj.getLoandetailsPK().getMemberId()));
/*  311 */             jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(LoandetailsObj.getRemarks()));
/*  312 */             loanDetalsArr.add(jsnObj);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  318 */         topPanelResultObj.put("LOAN_SANCTION_DETAILS_RESULT_CODE", "400");
/*  319 */         topPanelResultObj.put("LOAN_SANCTION_DETAILS", loanDetalsArr);
/*      */       }
/*      */     
/*  322 */     } catch (Exception e) {
/*      */       
/*  324 */       topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
/*  325 */       topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
/*  326 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  333 */     return topPanelResultObj.toJSONString();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLoanDetails(String memberId) {
/*  338 */     String result = "";
/*  339 */     JSONArray loanDetalsArr = new JSONArray();
/*  340 */     JSONArray loanRecoveryDetalsArr = new JSONArray();
/*  341 */     JSONObject topPanelResultObj = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/*  345 */       if (memberId != null && !"".equalsIgnoreCase(memberId))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  356 */         String qury1 = " SELECT L.LOAN_SANCTIONED_DATE,R.FIRST_NAME, R.FATHER_NAME,R.DEPT_ID,R.CARD_NO,L.LOAN_AMOUNT,L.LOAN_ID   FROM REGISTRATION R,LOANDETAILS L  WHERE R.MEMBER_ID=L.MEMBER_ID   AND R.MEMBER_ID=:MEMBER_ID   ORDER BY    L.LOAN_SANCTIONED_DATE    DESC";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  363 */         Map<String, Object> qury1parameters = new HashMap<String, Object>();
/*  364 */         qury1parameters.put("MEMBER_ID", memberId.trim());
/*  365 */         List<Object[]> loandetailsList = this.dataAccess.sqlqueryWithParams(qury1, qury1parameters);
/*  366 */         JSONObject loanDetailsObj = new JSONObject();
/*  367 */         if (loandetailsList != null && loandetailsList.size() > 0) {
/*      */           
/*  369 */           for (int i = 0; i < loandetailsList.size(); i++) {
/*  370 */             Object[] objectArr = loandetailsList.get(i);
/*  371 */             JSONObject jsnObj = new JSONObject();
/*  372 */             jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[0]));
/*  373 */             jsnObj.put("FIRST_NAME", this.utils.convertNullToEmptyString(objectArr[1]));
/*  374 */             jsnObj.put("FATHER_NAME", this.utils.convertNullToEmptyString(objectArr[2]));
/*  375 */             jsnObj.put("DEPT_ID", this.utils.convertNullToEmptyString(objectArr[3]));
/*  376 */             jsnObj.put("CARD_NO", this.utils.convertNullToEmptyString(objectArr[4]));
/*  377 */             jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[5]));
/*  378 */             jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[6]));
/*      */             
/*  380 */             loanDetailsObj.put(this.utils.convertNullToEmptyString(objectArr[6]), jsnObj);
/*  381 */             loanDetalsArr.add(jsnObj);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  404 */           String query = " SELECT  recoveryDetails.TRANSACTION_ID,  recoveryDetails.PAID_AMOUNT,  recoveryDetails.PAID_DATE,  recoveryDetails.RECEIPT_NO,  recoveryDetails.REMARKS,  recoveryDetails.STATUS,  recoveryDetails.LOAN_ID  FROM LOAN_RECOVERY_DETAILS recoveryDetails  WHERE recoveryDetails.MEMBER_ID=:MEMBER_ID";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  417 */           Map<String, Object> parameters = new HashMap<String, Object>();
/*  418 */           parameters.put("MEMBER_ID", memberId.trim());
/*      */           
/*  423 */           List<Object[]> loanRecoverydetailsList = this.dataAccess.sqlqueryWithParams(query, parameters);
/*  425 */           if (loanRecoverydetailsList != null && loanRecoverydetailsList.size() > 0)
/*      */           {
/*      */ 
/*      */             
/*  429 */             for (int j = 0; j < loanRecoverydetailsList.size(); j++) {
/*      */               
/*  431 */               Object[] objectArr = loanRecoverydetailsList.get(j);
/*      */               
/*  434 */               JSONObject jsnObj = new JSONObject();
/*  436 */               JSONObject loanDetObj = (JSONObject)loanDetailsObj.get(objectArr[6]);
/*      */               if(loanDetObj!=null) {
/*  440 */               jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(loanDetObj.get("LOAN_SANCTIONED_DATE")));
/*  441 */               jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(loanDetObj.get("LOAN_AMOUNT")));
/*  442 */               jsnObj.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objectArr[0]));
/*  443 */               jsnObj.put("PAID_AMOUNT", this.utils.convertNullToEmptyString(objectArr[1]));
/*  444 */               jsnObj.put("PAID_DATE", this.utils.convertNullToEmptyString(objectArr[2]));
/*  445 */               jsnObj.put("RECEIPT_NO", this.utils.convertNullToEmptyString(objectArr[3]));
/*  446 */               jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(objectArr[4]));
/*  447 */               jsnObj.put("STATUS", this.utils.convertNullToEmptyString(objectArr[5]));
/*  448 */               jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[6]));
/*  449 */               jsnObj.put("MEMBER_ID", memberId.trim());
/*      */               
/*  451 */               loanRecoveryDetalsArr.add(jsnObj);
}
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  459 */         topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "400");
/*  460 */         topPanelResultObj.put("LOAN_DETAILS", loanDetalsArr);
/*  461 */         topPanelResultObj.put("LOAN_RECOVERY_DETAILS", loanRecoveryDetalsArr);
/*      */       }
/*      */     
/*      */     }
/*  465 */     catch (Exception e) {
/*      */       
/*  467 */       topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
/*  468 */       topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
/*  469 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  476 */     return topPanelResultObj.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLoanDetails(String memberId, String currentloanIdFromRegister, String currentLoanStatusFromRegister) {
/*  483 */     String result = "";
/*  484 */     JSONArray loanDetalsArr = new JSONArray();
/*  485 */     JSONArray loanRecoveryDetalsArr = new JSONArray();
/*  486 */     JSONObject topPanelResultObj = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/*  490 */       if (memberId != null && !"".equalsIgnoreCase(memberId))
/*      */       {
/*      */         
/*  493 */         int loanBalance = this.miscellaneousDAO.caluclateLoanBalance(memberId);
/*      */         
/*  495 */         String queryForLoanRecoveryDetaisls = "from LoanRecoveryDetails where LoanRecoveryDetailsPK.memberId=:MEMBER_ID";
/*      */         
/*  497 */         Map<String, Object> loanRecoveryDetQryParams = new HashMap<String, Object>();
/*  498 */         loanRecoveryDetQryParams.put("MEMBER_ID", memberId.trim());
/*      */         
/*  500 */         List loanRecoveryDetList = this.dataAccess.queryWithParams(queryForLoanRecoveryDetaisls, loanRecoveryDetQryParams);
/*  501 */         if (loanRecoveryDetList != null) loanRecoveryDetList.size();
/*      */ 
/*      */ 
/*      */         
/*  505 */         String qury1 = " SELECT L.LOAN_SANCTIONED_DATE,R.FIRST_NAME, R.FATHER_NAME,R.DEPT_ID,R.CARD_NO,L.LOAN_AMOUNT   FROM REGISTRATION R,LOANDETAILS L  WHERE R.MEMBER_ID=L.MEMBER_ID   AND R.MEMBER_ID=:MEMBER_ID   ORDER BY    L.LOAN_SANCTIONED_DATE    DESC";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  512 */         Map<String, Object> qury1parameters = new HashMap<String, Object>();
/*  513 */         qury1parameters.put("MEMBER_ID", memberId.trim());
/*  514 */         List<Object[]> loandetailsList = this.dataAccess.sqlqueryWithParams(qury1, qury1parameters);
/*      */         
/*  516 */         if (loandetailsList != null && loandetailsList.size() > 0)
/*      */         {
/*  518 */           for (int i = 0; i < loandetailsList.size(); i++) {
/*  519 */             Object[] objectArr = loandetailsList.get(i);
/*  520 */             JSONObject jsnObj = new JSONObject();
/*      */             
/*  522 */             jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[0]));
/*  523 */             jsnObj.put("FIRST_NAME", this.utils.convertNullToEmptyString(objectArr[1]));
/*  524 */             jsnObj.put("FATHER_NAME", this.utils.convertNullToEmptyString(objectArr[2]));
/*  525 */             jsnObj.put("DEPT_ID", this.utils.convertNullToEmptyString(objectArr[3]));
/*  526 */             jsnObj.put("CARD_NO", this.utils.convertNullToEmptyString(objectArr[4]));
/*  527 */             jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[5]));
/*      */             
/*  529 */             loanDetalsArr.add(jsnObj);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  535 */         String query = " SELECT  loanDetails .LOAN_ID,  loanDetails .MEMBER_ID,  loanDetails .LOAN_AMOUNT,  loanDetails .LOAN_SANCTIONED_DATE,  loanDetails .MEMBER_NAME,  loanDetails .REMARKS AS LOAN_SANCTION_REMARKS,  recoveryDetails.TRANSACTION_ID,  recoveryDetails.PAID_AMOUNT,  recoveryDetails.PAID_DATE,  recoveryDetails.RECEIPT_NO,  recoveryDetails.REMARKS,  recoveryDetails.STATUS  FROM LOANDETAILS loanDetails ,LOAN_RECOVERY_DETAILS recoveryDetails  WHERE loanDetails.MEMBER_ID=recoveryDetails.MEMBER_ID    AND loanDetails.MEMBER_ID=:MEMBER_ID ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  551 */         Map<String, Object> parameters = new HashMap<String, Object>();
/*  552 */         parameters.put("MEMBER_ID", memberId.trim());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  557 */         List<Object[]> loanRecoverydetailsList = this.dataAccess.sqlqueryWithParams(query, parameters);
/*      */         
/*  559 */         if (loanRecoverydetailsList != null && loanRecoverydetailsList.size() > 0)
/*      */         {
/*  561 */           Object[] objArrOne = loanRecoverydetailsList.get(0);
/*  562 */           JSONObject jsnObject = new JSONObject();
/*  563 */           jsnObject.put("MEMBER_ID", this.utils.convertNullToEmptyString(objArrOne[1]));
/*  564 */           jsnObject.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objArrOne[2]));
/*  565 */           jsnObject.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objArrOne[3]));
/*  566 */           jsnObject.put("MEMBER_NAME", this.utils.convertNullToEmptyString(objArrOne[4]));
/*  567 */           jsnObject.put("REMARKS", this.utils.convertNullToEmptyString(objArrOne[5]));
/*  568 */           jsnObject.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objArrOne[6]));
/*      */           
/*  570 */           for (int i = 0; i < loanRecoverydetailsList.size(); i++) {
/*      */             
/*  572 */             Object[] objectArr = loanRecoverydetailsList.get(i);
/*  573 */             JSONObject jsnObj = new JSONObject();
/*      */             
/*  575 */             jsnObj.put("LOAN_SANCTIONED_DATE", this.utils.convertNullToEmptyString(objectArr[3]));
/*  576 */             jsnObj.put("LOAN_AMOUNT", this.utils.convertNullToEmptyString(objectArr[2]));
/*  577 */             jsnObj.put("TRANSACTION_ID", this.utils.convertNullToEmptyString(objectArr[6]));
/*  578 */             jsnObj.put("PAID_AMOUNT", this.utils.convertNullToEmptyString(objectArr[7]));
/*  579 */             jsnObj.put("PAID_DATE", this.utils.convertNullToEmptyString(objectArr[8]));
/*  580 */             jsnObj.put("RECEIPT_NO", this.utils.convertNullToEmptyString(objectArr[9]));
/*  581 */             jsnObj.put("REMARKS", this.utils.convertNullToEmptyString(objectArr[10]));
/*  582 */             jsnObj.put("STATUS", this.utils.convertNullToEmptyString(objectArr[11]));
/*  583 */             jsnObj.put("LOAN_ID", this.utils.convertNullToEmptyString(objectArr[0]));
/*  584 */             jsnObj.put("MEMBER_ID", this.utils.convertNullToEmptyString(objectArr[1]));
/*      */             
/*  586 */             loanRecoveryDetalsArr.add(jsnObj);
/*      */           } 
/*      */           
/*  589 */           topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "400");
/*  590 */           topPanelResultObj.put("LOAN_DETAILS", loanDetalsArr);
/*  591 */           topPanelResultObj.put("LOAN_RECOVERY_DETAILS", loanRecoveryDetalsArr);
/*      */         }
/*      */         else
/*      */         {
/*  595 */           topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "200");
/*  596 */           topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", 
/*  597 */               " No loan records found with for Loanid =" + currentloanIdFromRegister);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  604 */     catch (Exception e) {
/*      */       
/*  606 */       topPanelResultObj.put("LOAN_DETAILS_RESULT_CODE", "300");
/*  607 */       topPanelResultObj.put("LOAN_DETAILS_ERROR_MSG", e.getMessage());
/*  608 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  614 */     return topPanelResultObj.toJSONString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String loanPaymentValidation(JSONObject obj) {
/*  620 */     String result = "Sorry Registration Failed !";
/*      */     
/*      */     try {
/*  623 */       if (obj != null) {
/*  624 */         String cardNo = (String)obj.get("CARD_NO");
/*  625 */         String deptId = (String)obj.get("DEPT_ID");
/*  626 */         String memberId = (String)obj.get("MEMBER_ID");
/*  627 */         String paidAmount = (String)obj.get("PAID_AMOUNT");
/*  628 */         String paidDate = (String)obj.get("PAID_DATE");
/*  629 */         String receiptNo = (String)obj.get("RECEIPT_NO");
/*  630 */         if (receiptNo == null || "".equalsIgnoreCase(receiptNo))
/*      */         {
/*  632 */           return "Please Enter ReceiptNo ";
/*      */         }
/*  634 */         if (paidDate == null || "".equalsIgnoreCase(paidDate))
/*      */         {
/*  636 */           return "Please Select Paid Date Date.";
/*      */         }
/*  638 */         if (cardNo == null || "".equalsIgnoreCase(cardNo))
/*      */         {
/*  640 */           return "Please Select Card No.";
/*      */         }
/*  642 */         if (deptId == null || "".equalsIgnoreCase(deptId) || "SELECT".equalsIgnoreCase(deptId))
/*      */         {
/*  644 */           return "Please select member department.";
/*      */         }
/*      */ 
/*      */         
/*  648 */         if (!this.utils.isValidDate(paidDate)) {
/*  649 */           return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
/*      */         }
/*      */         
/*  652 */         if (!this.utils.isNumericString(paidAmount)) {
/*  653 */           return "Please enter only numbers in PaidAmount";
/*      */         }
/*      */         
/*  656 */         if (this.miscellaneousDAO.isDuplicateReceiptNumberInRegistration(receiptNo)) {
/*  657 */           return "Receipt no already used!";
/*      */         }
/*      */         
/*  660 */         return "SUCCESS";
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  666 */     catch (Exception e) {
/*      */       
/*  668 */       e.printStackTrace();
/*      */     } 
/*  670 */     return result;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public boolean isLoanAlreadySanctioned(String memberId) {
/*  675 */     boolean isLoanAlreadySanctioned = false;
/*      */     
/*      */     try {
/*  678 */       if (memberId != null && !"".equalsIgnoreCase(memberId)) {
/*  679 */         String query = " from Loandetails where loandetailsPK.memberId=:memberId and loanStatus=:loanStatus";
/*  680 */         Map<String, Object> parameters = new HashMap<String, Object>();
/*  681 */         parameters.put("memberId", memberId.trim());
/*  682 */         parameters.put("loanStatus", "UNDER_REOCVERY");
/*      */         
/*  684 */         List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);
/*  685 */         if (loandetailsList != null && loandetailsList.size() > 0) {
/*  686 */           isLoanAlreadySanctioned = true;
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  691 */     catch (Exception e) {
/*      */       
/*  693 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  696 */     return isLoanAlreadySanctioned;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String memberLoanStatusFromLoanDetials(String memberId, String loanId) {
/*  702 */     String loanStatus = "";
/*      */     
/*      */     try {
/*  705 */       if (memberId != null && !"".equalsIgnoreCase(memberId) && 
/*  706 */         loanId != null && !"".equalsIgnoreCase(loanId))
/*      */       {
/*  708 */         String query = " from Loandetails where loandetailsPK.memberId=:memberId and LoandetailsPK.loanId=:loanId";
/*  709 */         Map<String, Object> parameters = new HashMap<String, Object>();
/*  710 */         parameters.put("memberId", memberId.trim());
/*  711 */         parameters.put("loanId", loanId);
/*      */         
/*  713 */         List<Loandetails> loandetailsList = this.dataAccess.queryWithParams(query, parameters);
/*  714 */         if (loandetailsList != null && loandetailsList.size() > 0) {
/*  715 */           Loandetails loanDetials = loandetailsList.get(0);
/*  716 */           loanStatus = loanDetials.getLoanStatus();
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  721 */     } catch (Exception e) {
/*      */       
/*  723 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  726 */     return loanStatus;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject updateLoanSanctionDetailsFormDetails(HttpServletRequest request) {
/*  732 */     JSONObject resultObj = new JSONObject();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  740 */       String cardNo = request.getParameter("update_LoanSanctionDetails_cardNo");
/*  741 */       int cardNumber = Integer.parseInt(cardNo);
/*  742 */       String deptId = request.getParameter("update_LoanSanctionDetails_deptId");
/*  743 */       String pageId = request.getParameter("update_LoanSanctionDetails_pageId");
/*  744 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/*      */     }
/*  746 */     catch (Exception e) {
/*      */       
/*  748 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  751 */     return resultObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject updateLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
/*  759 */     JSONObject resultObj = new JSONObject();
/*      */     
/*      */     try {
/*  762 */       String cardNo = request.getParameter("update_LoanRecoveryDetails_cardNo");
/*      */       
/*  764 */       int cardNumber = Integer.parseInt(cardNo);
/*  765 */       String deptId = request.getParameter("update_LoanRecoveryDetails_deptId");
/*  766 */       String pageId = request.getParameter("update_LoanRecoveryDetails_pageId");
/*      */       
/*  768 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/*  769 */     } catch (Exception e) {
/*      */       
/*  771 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  774 */     return resultObj;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject deleteLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
/*  780 */     JSONObject resultObj = new JSONObject();
/*      */     try {
/*  782 */       String cardNo = request.getParameter("delete_LoanRecoveryDetails_cardNo");
/*      */       
/*  784 */       int cardNumber = Integer.parseInt(cardNo);
/*  785 */       String deptId = request.getParameter("delete_LoanRecoveryDetails_deptId");
/*  786 */       String pageId = request.getParameter("delete_LoanRecoveryDetails_pageId");
/*      */ 
/*      */       
/*  789 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/*  790 */     } catch (Exception e) {
/*      */       
/*  792 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  795 */     return resultObj;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public LoanRecoveryDetails getLoanRecoveryDetails(String memberId, String transId, String loanId) {
/*  800 */     JSONObject resultObj = new JSONObject();
/*  801 */     LoanRecoveryDetails loanRecoveryDetails = null;
/*      */     try {
/*  803 */       String query = "from LoanRecoveryDetails where loanRecoveryDetailsPK.memberId =:memberId and loanRecoveryDetailsPK.loanId =:loanId  and loanRecoveryDetailsPK.transactionId =:transactionId";
/*  804 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  805 */       parametersMap.put("memberId", memberId);
/*  806 */       parametersMap.put("transactionId", transId);
/*  807 */       parametersMap.put("loanId", loanId);
/*  808 */       List<LoanRecoveryDetails> list = this.dataAccess.queryWithParams(query, parametersMap);
/*  809 */       if (list != null && list.size() > 0)
/*      */       {
/*  811 */         loanRecoveryDetails = list.get(0);
/*      */       }
/*  813 */     } catch (Exception e) {
/*      */       
/*  815 */       e.printStackTrace();
/*      */     } 
/*  817 */     return loanRecoveryDetails;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public String updateLoanRecoveryDetails(HttpServletRequest request) {
/*  822 */     JSONObject result = new JSONObject();
/*      */     
/*      */     try {
/*  825 */       String memberId = request.getParameter("memberId");
/*  826 */       String transactionId = request.getParameter("transactionId");
/*  827 */       String paidDate = request.getParameter("paidDate");
/*  828 */       String paidAmount = request.getParameter("paidAmount");
/*  829 */       String receiptNo = request.getParameter("receiptNo");
/*  830 */       String remarks = request.getParameter("remarks");
/*  831 */       String loanId = request.getParameter("loanId");
/*  832 */       String cardNo = request.getParameter("cardNo");
/*  833 */       String deptId = request.getParameter("deptId");
/*      */ 
/*      */       
/*  836 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId))
/*      */       {
/*  838 */         JSONObject validateJsnObj = new JSONObject();
/*  839 */         validateJsnObj.put("MEMBER_ID", memberId);
/*  840 */         validateJsnObj.put("CARD_NO", cardNo);
/*  841 */         validateJsnObj.put("DEPT_ID", deptId);
/*  842 */         validateJsnObj.put("PAID_AMOUNT", paidAmount);
/*  843 */         validateJsnObj.put("PAID_DATE", paidDate);
/*  844 */         validateJsnObj.put("RECEIPT_NO", receiptNo);
/*  845 */         String validationMessage = loanPaymentValidation(validateJsnObj);
/*      */         
/*  847 */         if (!"".equals(validationMessage) && "SUCCESS".equalsIgnoreCase(validationMessage))
/*      */         {
/*  849 */           String updateQuery = "update LoanRecoveryDetails set  paidDate=:paidDate , receiptNo=:receiptNo ,  paidAmount=:paidAmount ,  remarks=:remarks   where loanRecoveryDetailsPK.transactionId=:transactionId and loanRecoveryDetailsPK.memberId=:memberId  and loanRecoveryDetailsPK.loanId=:loanId";
/*      */ 
/*      */ 
/*      */           
/*  853 */           Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */           
/*  855 */           parametersMap.put("memberId", memberId);
/*  856 */           parametersMap.put("transactionId", transactionId);
/*  857 */           parametersMap.put("loanId", loanId);
/*  858 */           parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/*  859 */           parametersMap.put("receiptNo", receiptNo);
/*  860 */           parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
/*  861 */           parametersMap.put("remarks", remarks);
/*      */           
/*  863 */           this.dataAccess.updateQuery(updateQuery, parametersMap);
/*  864 */           this.miscellaneousDAO.updateLoanBalance(memberId);
/*      */           
/*  866 */           result.put("FINAL_RESULT_CODE", "400");
/*  867 */           result.put("DATA_DETAILS", "LoanRecoveryDetails Upadated Sucessfullty!");
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  878 */           result.put("FINAL_RESULT_CODE", "200");
/*  879 */           result.put("ERROR_MSG", validationMessage);
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  885 */         result.put("FINAL_RESULT_CODE", "200");
/*  886 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
/*      */       }
/*      */     
/*      */     }
/*  890 */     catch (NumberFormatException nfe) {
/*  891 */       nfe.printStackTrace();
/*  892 */       result.put("FINAL_RESULT_CODE", "300");
/*  893 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/*  895 */     catch (Exception e) {
/*  896 */       e.printStackTrace();
/*  897 */       result.put("FINAL_RESULT_CODE", "300");
/*  898 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/*  901 */     return result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String updateSanctionDetails(HttpServletRequest request) {
/*  907 */     JSONObject result = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/*  911 */       String loanId = request.getParameter("loanId");
/*  912 */       String loanAmount = request.getParameter("loanAmount");
/*  913 */       String loanSanctionedDate = request.getParameter("loanSanctionedDate");
/*      */       
/*  915 */       Date date1 = (new SimpleDateFormat("dd/MM/yyyy")).parse(loanSanctionedDate);
/*      */ 
/*      */       
/*  918 */       String memberId = request.getParameter("memberId");
/*  919 */       if (loanAmount != null && !"".equals(loanAmount) && loanSanctionedDate != null && !"".equals(loanSanctionedDate))
/*      */       {
/*      */ 
/*      */         
/*  923 */         String updateQuery = "update Loandetails set  loanSanctionedDate=:loanSanctionedDate , loanAmount=:loanAmount  where loandetailsPK.memberId=:memberId and loandetailsPK.loanId=:loanId ";
/*      */ 
/*      */         
/*  926 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  927 */         parametersMap.put("memberId", memberId);
/*  928 */         parametersMap.put("loanId", loanId);
/*  929 */         parametersMap.put("loanSanctionedDate", date1);
/*  930 */         parametersMap.put("loanAmount", Integer.valueOf(Integer.parseInt(loanAmount)));
/*      */ 
/*      */ 
/*      */         
/*  934 */         this.dataAccess.updateQuery(updateQuery, parametersMap);
/*      */ 
/*      */         
/*  937 */         result.put("FINAL_RESULT_CODE", "400");
/*  938 */         result.put("DATA_DETAILS", "LoanSanctionDetails Upadated Sucessfullty!");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  951 */         result.put("FINAL_RESULT_CODE", "200");
/*  952 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
/*      */       }
/*      */     
/*      */     }
/*  956 */     catch (NumberFormatException nfe) {
/*  957 */       nfe.printStackTrace();
/*  958 */       result.put("FINAL_RESULT_CODE", "300");
/*  959 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/*  961 */     catch (Exception e) {
/*  962 */       e.printStackTrace();
/*  963 */       result.put("FINAL_RESULT_CODE", "300");
/*  964 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/*  967 */     return result.toString();
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public String deleteLoanRecoveryDetails(HttpServletRequest request) {
/*  972 */     JSONObject result = new JSONObject();
/*      */     
/*      */     try {
/*  975 */       String memberId = request.getParameter("memberId");
/*  976 */       String transactionId = request.getParameter("transactionId");
/*  977 */       String paidDate = request.getParameter("paidDate");
/*  978 */       String paidAmount = request.getParameter("paidAmount");
/*  979 */       String receiptNo = request.getParameter("receiptNo");
/*  980 */       String remarks = request.getParameter("remarks");
/*  981 */       String loanId = request.getParameter("loanId");
/*  982 */       String cardNo = request.getParameter("cardNo");
/*  983 */       String deptId = request.getParameter("deptId");
/*      */ 
/*      */ 
/*      */       
/*  987 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId))
/*      */       {
/*  989 */         String updateQuery = "delete from LoanRecoveryDetails   where loanRecoveryDetailsPK.transactionId=:transactionId and loanRecoveryDetailsPK.memberId=:memberId  and loanRecoveryDetailsPK.loanId=:loanId";
/*      */ 
/*      */ 
/*      */         
/*  993 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */         
/*  995 */         parametersMap.put("memberId", memberId);
/*  996 */         parametersMap.put("transactionId", transactionId);
/*  997 */         parametersMap.put("loanId", loanId);
/*  998 */         parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/*  999 */         parametersMap.put("receiptNo", receiptNo);
/* 1000 */         parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
/* 1001 */         parametersMap.put("remarks", remarks);
/* 1002 */         int updatedRecords = this.dataAccess.updateQueryByCount(updateQuery, parametersMap);
/* 1003 */         this.miscellaneousDAO.updateLoanBalance(memberId);
/* 1004 */         result.put("FINAL_RESULT_CODE", "400");
/* 1005 */         result.put("DATA_DETAILS", "LoanRecoveryDetails Upadated Sucessfullty!");
/*      */       }
/*      */       else
/*      */       {
/* 1009 */         result.put("FINAL_RESULT_CODE", "200");
/* 1010 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
/*      */       }
/*      */     
/*      */     }
/* 1014 */     catch (NumberFormatException nfe) {
/* 1015 */       nfe.printStackTrace();
/* 1016 */       result.put("FINAL_RESULT_CODE", "300");
/* 1017 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/* 1019 */     catch (Exception e) {
/* 1020 */       e.printStackTrace();
/* 1021 */       result.put("FINAL_RESULT_CODE", "300");
/* 1022 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/* 1025 */     return result.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String deleteLoanSanctionDetails(HttpServletRequest request) {
/* 1032 */     JSONObject result = new JSONObject();
/*      */     
/*      */     try {
/* 1035 */       String memberId = request.getParameter("memberId");
/*      */       
/* 1037 */       String loanId = request.getParameter("loanId");
/*      */ 
/*      */ 
/*      */       
/* 1041 */       if (memberId != null && !"".equals(memberId) && loanId != null && !"".equals(loanId))
/*      */       {
/* 1043 */         String updateQuery = "delete from Loandetails   where loandetailsPK.loanId=:loanId and loandetailsPK.memberId=:memberId  ";
/*      */ 
/*      */ 
/*      */         
/* 1047 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */         
/* 1049 */         parametersMap.put("memberId", memberId);
/* 1050 */         parametersMap.put("loanId", loanId);
/*      */         
/* 1052 */         int updatedRecords = this.dataAccess.updateQueryByCount(updateQuery, parametersMap);
/* 1053 */         this.miscellaneousDAO.updateLoanBalance(memberId);
/* 1054 */         result.put("FINAL_RESULT_CODE", "400");
/* 1055 */         result.put("DATA_DETAILS", "LoanSanctionDetails deleted Sucessfullty!");
/*      */       }
/*      */       else
/*      */       {
/* 1059 */         result.put("FINAL_RESULT_CODE", "200");
/* 1060 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Subscriptions!");
/*      */       }
/*      */     
/*      */     }
/* 1064 */     catch (NumberFormatException nfe) {
/* 1065 */       nfe.printStackTrace();
/* 1066 */       result.put("FINAL_RESULT_CODE", "300");
/* 1067 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/* 1069 */     catch (Exception e) {
/* 1070 */       e.printStackTrace();
/* 1071 */       result.put("FINAL_RESULT_CODE", "300");
/* 1072 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/* 1075 */     return result.toJSONString();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLoanSanctionedDetails(String deptId, String cardNo) {
/* 1080 */     return "";
/*      */   }
/*      */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\LoanDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */