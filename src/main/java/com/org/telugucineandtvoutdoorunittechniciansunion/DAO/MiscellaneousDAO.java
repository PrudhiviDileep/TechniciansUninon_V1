/*      */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*      */ 
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.NotValidCardNumberException;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.AdminfeePayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.CardNubers;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Departments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Loandetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RecommendationDetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Units;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.servlet.ServletContext;
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
/*      */ @Repository
/*      */ public class MiscellaneousDAO
/*      */ {
/*      */   @Autowired
/*      */   public DataAccess dataAccess;
/*      */   @Autowired
/*      */   ServletContext miscContext;
/*      */   @Autowired
/*      */   MembershipDAO membershipDAO;
/*   43 */   Utils utils = new Utils();
/*      */   
/*      */   private static final String UPLOADED_FOLDER = "\\AppFiles\\Uploads";
/*      */   private static final String DOWNLOAD_FOLDER = "\\AppFiles\\Downloads";
/*      */   
/*      */   @Transactional
/*      */   public JSONArray getDepartments() {
/*   50 */     JSONArray resultArry = new JSONArray();
/*      */     try {
/*   52 */       String getDepartMentsquery = "from Departments ";
/*   53 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*   54 */       List<Departments> list = this.dataAccess.queryWithParams(getDepartMentsquery, parametersMap);
/*      */       
/*   56 */       if (list != null && !list.isEmpty() && list.size() > 0) {
/*   57 */         for (int i = 0; i < list.size(); i++) {
/*   58 */           Departments dept = list.get(i);
/*   59 */           JSONObject jsnObj = new JSONObject();
/*   60 */           jsnObj.put("DEPT_ID", dept.getDeptId());
/*   61 */           jsnObj.put("DEPT_NAME", dept.getDeptName());
/*   62 */           resultArry.add(jsnObj);
/*      */         }
/*      */       
/*      */       }
/*      */     }
/*   67 */     catch (Exception e) {
/*      */       
/*   69 */       ApplicationUtilities.error(getClass(), e, "getDepartments");
/*      */     } 
/*   71 */     return resultArry;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject getMemberDetailsForRecomondation(String deptId, String cardNo) {
/*   77 */     JSONObject resutlJsnObj = new JSONObject();
/*   78 */     JSONObject recomondationDet = new JSONObject();
/*      */     
/*      */     try {
/*   81 */       if (deptId != null && !"".equalsIgnoreCase(deptId) && cardNo != null && !"".equalsIgnoreCase(cardNo)) {
/*   82 */         int cardNumber = Integer.parseInt(cardNo);
/*   83 */         String getDepartMentsquery = "from Registration where registrationPK.cardNo=:cardNo and registrationPK.deptId=:deptId ";
/*   84 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*   85 */         parametersMap.put("deptId", deptId);
/*   86 */         parametersMap.put("cardNo", Integer.valueOf(cardNumber));
/*   87 */         List<Registration> list = this.dataAccess.queryWithParams(getDepartMentsquery, parametersMap);
/*   88 */         if (list != null && !list.isEmpty() && list.size() > 0) {
/*   89 */           Registration member = list.get(0);
/*   90 */           resutlJsnObj = new JSONObject();
/*   91 */           resutlJsnObj.put("NAME", member.getFirstName());
/*   92 */           resutlJsnObj.put("UNIT_ID", member.getUnitId());
/*   93 */           recomondationDet.put("FINAL_RESULT_CODE", "400");
/*   94 */           recomondationDet.put("DATA_DETAILS", resutlJsnObj);
/*      */         } else {
/*   96 */           recomondationDet.put("FINAL_RESULT_CODE", "200");
/*   97 */           recomondationDet.put("ERROR_MSG", 
/*   98 */               "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  103 */         recomondationDet.put("FINAL_RESULT_CODE", "200");
/*  104 */         recomondationDet.put("ERROR_MSG", 
/*  105 */             "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */       }
/*      */     
/*  108 */     } catch (NotValidCardNumberException nvcne) {
/*      */       
/*  110 */       nvcne.printStackTrace();
/*  111 */       recomondationDet.put("FINAL_RESULT_CODE", "300");
/*  112 */       recomondationDet.put("ERROR_MSG", nvcne.getMessage());
/*  113 */       nvcne.printStackTrace();
/*  114 */       ApplicationUtilities.error(getClass(), (Exception)nvcne, "getMemberDetailsForRecomondation");
/*      */     }
/*  116 */     catch (Exception e) {
/*  117 */       e.printStackTrace();
/*  118 */       recomondationDet.put("FINAL_RESULT_CODE", "300");
/*  119 */       recomondationDet.put("ERROR_MSG", e.getMessage());
/*  120 */       ApplicationUtilities.error(getClass(), e, "getMemberDetailsForRecomondation");
/*      */     } 
/*  122 */     return recomondationDet;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public RecommendationDetails getRecommondationDetailsByMemberId(String memberId) {
/*  129 */     RecommendationDetails recommendationDetails = null;
/*      */     try {
/*  131 */       String query = " from RecommendationDetails where id.memberId=:memberId";
/*  132 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  133 */       parametersMap.put("memberId", memberId);
/*  134 */       List<RecommendationDetails> list = this.dataAccess.queryWithParams(query, parametersMap);
/*  135 */       if (list != null && list.size() > 0) {
/*  136 */         recommendationDetails = list.get(0);
/*      */       }
/*      */     }
/*  139 */     catch (Exception e) {
/*  140 */       ApplicationUtilities.error(getClass(), e, "getRecommondationDetailsByMemberIds");
/*      */     } 
/*      */     
/*  143 */     return recommendationDetails;
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String getCardNubersByDeptId(String deptId) {
/*  198 */     JSONArray cardNoArr = new JSONArray();
/*      */     try {
/*  200 */       String query = " from CardNubers where cardNubersPK.deptId=:deptId and cardStatus=:cardStatus";
/*      */       
/*  202 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  203 */       parametersMap.put("deptId", deptId);
/*  204 */       parametersMap.put("cardStatus", "ACTIVE");
/*      */       
/*  206 */       List<CardNubers> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  208 */       if (list != null && list.size() > 0) {
/*  209 */         for (int j = 0; j < list.size(); j++) {
/*  210 */           CardNubers cardNumbers = list.get(j);
/*  211 */           cardNoArr.add(Integer.valueOf(cardNumbers.getCardNubersPK().getCardNo()));
/*      */         }
/*      */       
/*      */       }
/*  215 */     } catch (Exception e) {
/*      */       
/*  217 */       ApplicationUtilities.error(getClass(), e, "getCardNubersByDeptId");
/*      */     } 
/*      */     
/*  220 */     return cardNoArr.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String getCardNumbersByDeptIdForAutocomplete(String deptId, String term) {
/*  227 */     JSONArray cardNoArr = new JSONArray();
/*      */     try {
/*  229 */       String query = " from CardNubers where cardNubersPK.deptId=:deptId and cardStatus=:cardStatus  ";
/*      */       
/*  231 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  232 */       parametersMap.put("deptId", deptId);
/*  233 */       parametersMap.put("cardStatus", "ACTIVE");
/*      */ 
/*      */       
/*      */       try {
/*  237 */         int cardNumber = Integer.parseInt(term);
/*  238 */         if (cardNumber > 0) {
/*  239 */           query = String.valueOf(query) + " and cardNubersPK.cardNo >=:term  order by cardNubersPK.cardNo  asc";
/*  240 */           parametersMap.put("term", Integer.valueOf(cardNumber));
/*      */         }
/*      */       
/*  243 */       } catch (NumberFormatException ne) {
/*      */         
/*  245 */         query = String.valueOf(query) + "   order by cardNubersPK.cardNo  asc";
/*      */       } 
/*      */       
/*  248 */       List<CardNubers> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  250 */       if (list != null && list.size() > 0) {
/*  251 */         for (int j = 0; j < list.size(); j++) {
/*  252 */           CardNubers cardNumbers = list.get(j);
/*  253 */           cardNoArr.add(Integer.valueOf(cardNumbers.getCardNubersPK().getCardNo()));
/*      */         }
/*      */       
/*      */       }
/*      */     }
/*  258 */     catch (Exception e) {
/*      */       
/*  260 */       ApplicationUtilities.error(getClass(), e, "getCardNumbersByDeptIdForAutocomplete");
/*      */     } 
/*      */     
/*  263 */     return cardNoArr.toJSONString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONArray getUnits() {
/*  269 */     JSONArray resultArry = new JSONArray();
/*      */     
/*      */     try {
/*  272 */       String getUnitsQuery = "from Units ";
/*  273 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  274 */       List<Units> list = this.dataAccess.queryWithParams(getUnitsQuery, parametersMap);
/*      */       
/*  276 */       if (list != null && !list.isEmpty() && list.size() > 0) {
/*  277 */         for (int i = 0; i < list.size(); i++) {
/*  278 */           Units units = list.get(i);
/*  279 */           JSONObject jsnObj = new JSONObject();
/*  280 */           jsnObj.put("UNIT_ID", units.getUnitId());
/*  281 */           jsnObj.put("UNIT_NAME", units.getUnitName());
/*  282 */           resultArry.add(jsnObj);
/*      */         }
/*      */       
/*      */       }
/*      */     }
/*  287 */     catch (Exception e) {
/*      */       
/*  289 */       ApplicationUtilities.error(getClass(), e, "getUnits");
/*      */     } 
/*  291 */     return resultArry;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String getDetialsBySelectAtion(HttpServletRequest request) {
/*  297 */     JSONArray resutlJsnArr = new JSONArray();
/*  298 */     int colCount = 0;
/*  299 */     String theadStr = "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'><thead><tr><th align='center'>SNo</th><th align='center'>Name</th><th align='center'>Department Name</th><th align='center'>Card No</th>";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  304 */     String tbody = "<tbody>";
/*      */     
/*      */     try {
/*  307 */       String action = request.getParameter("action");
/*  308 */       String deptId = request.getParameter("deptId");
/*      */ 
/*      */       
/*  311 */       String query = " from Registration ";
/*  312 */       deptId = (deptId != null && !"".equals(deptId)) ? deptId : "ALL_DEPARTMENTS";
/*  313 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  314 */       action = (action != null && !"".equals(action)) ? action : "CONTACT_DETAILS";
/*      */       
/*  316 */       if (!deptId.equalsIgnoreCase("ALL_DEPARTMENTS")) {
/*  317 */         query = String.valueOf(query) + " where registrationPK.deptId=:deptId ";
/*  318 */         parametersMap.put("deptId", deptId);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  329 */       if (action.equalsIgnoreCase("CARD_BALANCE_DETAILS")) {
/*  330 */         colCount = 7;
/*  331 */         theadStr = String.valueOf(theadStr) + "<th align='center'>Card Amount</th><th align='center'>Paid Amount</th><th align='center'>Card Balance</th>";
/*      */       
/*      */       }
/*  334 */       else if (action.equalsIgnoreCase("LOAN_BALANCE_DETAILS")) {
/*      */         
/*  336 */         colCount = 8;
/*  337 */         theadStr = String.valueOf(theadStr) + "<th align='center'>Loan Amount</th><th align='center'>Total Paid</th><th align='center'>Loan Balance</th><th align='center'>Phone Number</th>";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  343 */       else if (action.equalsIgnoreCase("BANK_DETAILS")) {
/*  344 */         colCount = 8;
/*  345 */         theadStr = String.valueOf(theadStr) + "<th align='center'>Acc Holder Name</th><th align='center'>Acc Number</th><th align='center'>Branch</th><th align='center'>IFSC</th>";
/*      */       } else {
/*      */         
/*  348 */         colCount = 6;
/*  349 */         theadStr = String.valueOf(theadStr) + "<th align='center'>Perminent Address</th><th align='center'>Phone No</th>";
/*      */       } 
/*      */       
/*  352 */       theadStr = String.valueOf(theadStr) + "</tr></thead>";
/*      */ 
/*      */ 
/*      */       
/*  356 */       if (!action.equalsIgnoreCase("LOAN_BALANCE_DETAILS")) {
/*  357 */         query = String.valueOf(query) + " order by  deptName asc ";
/*  358 */         List<Registration> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */         
/*  360 */         if (list != null && list.size() > 0) {
/*  361 */           int k = 1;
/*  362 */           for (int j = 0; j < list.size(); j++) {
/*      */             
/*  364 */             JSONObject detailsObj = new JSONObject();
/*  365 */             Registration member = list.get(j);
/*  366 */             tbody = String.valueOf(tbody) + "<tr><td align='center' >" + k + "</td>";
/*  367 */             if (action.equalsIgnoreCase("CARD_BALANCE_DETAILS")) {
/*      */ 
/*      */               
/*  370 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getFirstName()) + "</td>";
/*  371 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getDeptName()) + "</td>";
/*  372 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getRegistrationPK().getCardNo())) + 
/*  373 */                 "</td>";
/*  374 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getCardAmount()) + "</td>";
/*  375 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getPaidAmount())) + "</td>";
/*  376 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getCardBalance()) + "</td>";
/*      */             }
/*  378 */             else if (action.equalsIgnoreCase("LOAN_BALANCE_DETAILS")) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  383 */               String loanDetailsSearch = " SELECT MEMBER_ID,SUM(LOAN_AMOUNT) as LOAN_AMOUNT FROM LOANDETAILS     GROUP BY MEMBER_ID ";
/*      */ 
/*      */               
/*  386 */               List<Object[]> loanDetailsSearchList = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/*      */               
/*  388 */               if (loanDetailsSearchList != null && loanDetailsSearchList.size() > 0)
/*      */               {
/*  390 */                 for (int i = 0; i < loanDetailsSearchList.size(); i++) {
/*  391 */                   Object[] objectArr = loanDetailsSearchList.get(i);
/*      */                   
/*  393 */                   int totalPaidAmount = getPaidLoanAmount((String)objectArr[0]);
/*  394 */                   int totalLoanAmount = Integer.parseInt((String)objectArr[1]);
/*  395 */                   int balance = totalLoanAmount - totalPaidAmount;
/*      */                   
/*  397 */                   if (balance > 0)
/*      */                   {
/*      */                     
/*  400 */                     String sqlLoanQuery = "from Registration  where  RegistrationPK.memberId=:MEMBER_ID ";
/*  401 */                     Map<String, Object> loanBalMap = new HashMap<String, Object>();
/*  402 */                     loanBalMap.put("MEMBER_ID", objectArr[0]);
/*      */                     
/*  404 */                     List<Registration> membrLonDetlist = this.dataAccess.queryWithParams(sqlLoanQuery, loanBalMap);
/*  405 */                     if (membrLonDetlist != null && membrLonDetlist.size() > 0)
/*      */                     {
/*  407 */                       member = membrLonDetlist.get(0);
/*      */                       
/*  409 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getFirstName()) + "</td>";
/*  410 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getDeptName()) + "</td>";
/*  411 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getRegistrationPK().getCardNo())) + "</td>";
/*  412 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(totalLoanAmount)) + "</td>";
/*  413 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(totalPaidAmount)) + "</td>";
/*  414 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(balance)) + "</td>";
/*  415 */                       tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getPhoneNo()) + "</td>";
/*      */                     
/*      */                     }
/*      */ 
/*      */                   
/*      */                   }
/*      */ 
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */             }
/*  427 */             else if (action.equalsIgnoreCase("BANK_DETAILS")) {
/*      */ 
/*      */               
/*  430 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getFirstName()) + "</td>";
/*  431 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getDeptName()) + "</td>";
/*  432 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getRegistrationPK().getCardNo())) + 
/*  433 */                 "</td>";
/*  434 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getBankAccHolderName()) + "</td>";
/*  435 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getBankAccNo()) + "</td>";
/*  436 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getBankBranch()) + "</td>";
/*  437 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getBankIfscCode()) + "</td>";
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  442 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getFirstName()) + "</td>";
/*  443 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getDeptName()) + "</td>";
/*  444 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getRegistrationPK().getCardNo())) + 
/*  445 */                 "</td>";
/*  446 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getPerminentAddress()) + "</td>";
/*  447 */               tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getPhoneNo()) + "</td>";
/*      */             } 
/*  449 */             tbody = String.valueOf(tbody) + "</tr>";
/*  450 */             k++;
/*      */           } 
/*      */         } 
/*  453 */       } else if (action.equalsIgnoreCase("LOAN_BALANCE_DETAILS")) {
/*      */ 
/*      */ 
/*      */         
/*  457 */         String loanDetailsSearch = " SELECT MEMBER_ID,SUM(LOAN_AMOUNT) as LOAN_AMOUNT FROM LOANDETAILS     GROUP BY MEMBER_ID ";
/*  458 */         Map<String, Object> loanDetailsSearchMap = new HashMap<String, Object>();
/*      */         
/*  460 */         List<Object[]> loanDetailsSearchList = this.dataAccess.sqlqueryWithParams(loanDetailsSearch, loanDetailsSearchMap);
/*      */         
/*  462 */         if (loanDetailsSearchList != null && loanDetailsSearchList.size() > 0) {
/*  463 */           int countt = 0;
/*  464 */           for (int i = 0; i < loanDetailsSearchList.size(); i++)
/*      */           {
/*  466 */             Object[] objectArr = loanDetailsSearchList.get(i);
/*      */             
/*  468 */             int totalPaidAmount = getPaidLoanAmount((String)objectArr[0]);
/*  469 */             int totalLoanAmount = Integer.parseInt(String.valueOf(objectArr[1]));
/*  470 */             int balance = totalLoanAmount - totalPaidAmount;
/*      */             
/*  472 */             if (balance > 0) {
/*  473 */               tbody = String.valueOf(tbody) + "<tr><td align='center' >" + countt++ + "</td>";
/*      */               
/*  475 */               String sqlLoanQuery = "from Registration  where  registrationPK.memberId=:MEMBER_ID ";
/*  476 */               Map<String, Object> loanBalMap = new HashMap<String, Object>();
/*  477 */               loanBalMap.put("MEMBER_ID", objectArr[0]);
/*      */               
/*  479 */               List<Registration> membrLonDetlist = this.dataAccess.queryWithParams(sqlLoanQuery, loanBalMap);
/*  480 */               if (membrLonDetlist != null && membrLonDetlist.size() > 0)
/*      */               {
/*  482 */                 Registration member = membrLonDetlist.get(0);
/*      */                 
/*  484 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getFirstName()) + "</td>";
/*  485 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getDeptName()) + "</td>";
/*  486 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(member.getRegistrationPK().getCardNo())) + "</td>";
/*  487 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(totalLoanAmount)) + "</td>";
/*  488 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(totalPaidAmount)) + "</td>";
/*  489 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(balance)) + "</td>";
/*  490 */                 tbody = String.valueOf(tbody) + "<td>" + this.utils.convertNullToEmptyString(member.getPhoneNo()) + "</td></tr>";
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  504 */         tbody = String.valueOf(tbody) + "<tr><td colspan='" + colCount + "' align='center'> No data found !</td></tr>";
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  509 */       tbody = String.valueOf(tbody) + "</tbody>";
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  514 */     catch (Exception e) {
/*      */ 
/*      */ 
/*      */       
/*  518 */       ApplicationUtilities.error(getClass(), e, "getDetialsBySelectAtion");
/*      */     } 
/*      */     
/*  521 */     return String.valueOf(theadStr) + tbody + "</table>";
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public JSONArray getPaymentConfigDetialsForSelect(String deptId, String category) {
/*  526 */     PaymentConfigurations paymentConfigurations = null;
/*  527 */     JSONArray objArray = new JSONArray();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  532 */       String query = " from PaymentConfigurations where paymentConfigurationsPK.category=:category and status=:status  ";
/*  533 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  534 */       parametersMap.put("status", "ACTIVE");
/*  535 */       parametersMap.put("category", category);
/*  536 */       if (deptId != null && !"".equalsIgnoreCase(deptId)) {
/*      */         
/*  538 */         query = String.valueOf(query) + " and paymentConfigurationsPK.deptId=:deptId  ";
/*  539 */         parametersMap.put("deptId", deptId);
/*      */       } 
/*      */       
/*  542 */       List<PaymentConfigurations> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  544 */       if (list != null && list.size() > 0) {
/*  545 */         for (int j = 0; j < list.size(); j++) {
/*  546 */           JSONObject obj = new JSONObject();
/*  547 */           paymentConfigurations = list.get(j);
/*  548 */           obj.put("DEPT_ID", paymentConfigurations.getPaymentConfigurationsPK().getDeptId());
/*  549 */           obj.put("PAYMENT_CONF_ID", paymentConfigurations.getPaymentConfigurationsPK().getPaymentConfId());
/*  550 */           obj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(paymentConfigurations.getMembershipAmount()));
/*  551 */           objArray.add(obj);
/*      */         }
/*      */       
/*      */       }
/*  555 */     } catch (Exception e) {
/*      */       
/*  557 */       ApplicationUtilities.error(getClass(), e, "getPaymentConfigDetialsForSelect");
/*      */     } 
/*      */     
/*  560 */     return objArray;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject getPaymentConfigDetials(String deptId, String category) {
/*  566 */     PaymentConfigurations paymentConfigurations = null;
/*  567 */     JSONObject obj = null;
/*      */     try {
/*  569 */       String query = " from PaymentConfigurations where paymentConfigurationsPK.category=:category  ";
/*      */ 
/*      */       
/*  572 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */ 
/*      */       
/*  575 */       parametersMap.put("category", category);
/*  576 */       List<PaymentConfigurations> list = this.dataAccess.queryWithParams(query, parametersMap);
/*  577 */       if (list != null && list.size() > 0) {
/*  578 */         obj = new JSONObject();
/*  579 */         for (int j = 0; j < list.size(); j++) {
/*  580 */           paymentConfigurations = list.get(j);
/*  581 */           obj.put(paymentConfigurations.getPaymentConfigurationsPK().getDeptId(), paymentConfigurations);
/*      */         }
/*      */       
/*      */       } 
/*  585 */     } catch (Exception e) {
/*      */       
/*  587 */       ApplicationUtilities.error(getClass(), e, "getPaymentConfigDetials");
/*      */     } 
/*      */     
/*  590 */     return obj;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONArray getUnsubsribedYears(String deptId, String cardNo) {
/*  596 */     JSONArray resultArray = new JSONArray();
/*      */     
/*      */     try {
/*  599 */       Registration registeredMember = getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
/*  600 */       int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/*  601 */       int joinedYear = 
/*  602 */         Integer.parseInt((new SimpleDateFormat("yyyy")).format(registeredMember.getRegisteredDate()));
/*  603 */       List list = null;
/*  604 */       String query = "from SubscriptionPayments where memberId =:memberId  and paymentStatus=:paymentStatus and subscriptionPaymentsPK.subscriptionYear=:subscriptionYear  ";
/*  605 */       for (int i = joinedYear; i <= currentYear; i++) {
/*  606 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  607 */         parametersMap.put("memberId", registeredMember.getRegistrationPK().getMemberId());
/*  608 */         parametersMap.put("paymentStatus", "Yes");
/*  609 */         parametersMap.put("subscriptionYear", Integer.valueOf(i));
/*  610 */         list = this.dataAccess.queryWithParams(query, parametersMap);
/*  611 */         if (list == null || list.size() <= 0)
/*      */         {
/*      */           
/*  614 */           resultArray.add(Integer.valueOf(i));
/*      */         }
/*      */       } 
/*  617 */     } catch (Exception e) {
/*  618 */       ApplicationUtilities.error(getClass(), e, "getUnsubsribedYears");
/*      */     } 
/*      */     
/*  621 */     return resultArray;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public String isDuplicateReceiptNumberInSubscriptionPayments(String receiptNo, String memberId) {
/*  626 */     String result = "";
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  631 */       String query = "from SubscriptionPayments where subscriptionPaymentsPK.receiptNo=:receiptNo and category='SUBSCRIPTION' and memberId!='" + memberId + "'";
/*  632 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  633 */       parametersMap.put("receiptNo", receiptNo);
/*      */       
/*  635 */       List<SubscriptionPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  637 */       if (list != null && list.size() > 0)
/*      */       {
/*  639 */         SubscriptionPayments obj = list.get(0);
/*  640 */         parametersMap.put("memeber id found  >>> ", obj.getMemberId());
/*      */         
/*  642 */         result = obj.getMemberId();
/*      */       }
/*      */     
/*      */     }
/*  646 */     catch (Exception e) {
/*      */       
/*  648 */       ApplicationUtilities.error(getClass(), e, "isDuplicateReceiptNumberInSubscriptionPayments");
/*      */     } 
/*  650 */     return result;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public boolean isDuplicateReceiptNumberInSubscriptionPayments(String receiptNo) {
/*  655 */     boolean result = false;
/*      */     
/*      */     try {
/*  658 */       String query = "from SubscriptionPayments where subscriptionPaymentsPK.receiptNo=:receiptNo and category='SUBSCRIPTION' ";
/*  659 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  660 */       parametersMap.put("receiptNo", receiptNo);
/*      */       
/*  662 */       List list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  664 */       if (list != null && list.size() > 0) {
/*  665 */         result = true;
/*      */       
/*      */       }
/*      */     }
/*  669 */     catch (Exception e) {
/*      */       
/*  671 */       ApplicationUtilities.error(getClass(), e, "isDuplicateReceiptNumberInSubscriptionPayments");
/*      */     } 
/*  673 */     return result;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public boolean isDuplicateReceiptNumberInRegistration(String receiptNo) {
/*  678 */     boolean result = false;
/*      */     
/*      */     try {
/*  681 */       String query = "from Registration where receiptNo=:receiptNo  ";
/*  682 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  683 */       parametersMap.put("receiptNo", receiptNo);
/*  684 */       List list = this.dataAccess.queryWithParams(query, parametersMap);
/*  685 */       if (list != null && list.size() > 0) {
/*  686 */         result = true;
/*      */       
/*      */       }
/*      */     }
/*  690 */     catch (Exception e) {
/*      */       
/*  692 */       ApplicationUtilities.error(getClass(), e, "isDuplicateReceiptNumberInRegistration");
/*      */     } 
/*  694 */     return result;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public SubscriptionPayments getSubscriptionByTransId(String transactionId) {
/*  699 */     SubscriptionPayments result = null;
/*      */     
/*      */     try {
/*  702 */       String query = "from SubscriptionPayments where transactionId=:transactionId  ";
/*  703 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/*  704 */       parametersMap.put("transactionId", transactionId);
/*      */       
/*  706 */       List<SubscriptionPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
/*      */       
/*  708 */       if (list != null && list.size() > 0) {
/*  709 */         result = list.get(0);
/*      */       
/*      */       }
/*      */     }
/*  713 */     catch (Exception e) {
/*      */       
/*  715 */       ApplicationUtilities.error(getClass(), e, "getSubscriptionByTransId");
/*      */     } 
/*  717 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject getTopPanel(int cardNo, String deptId, String pageId) {
/*  723 */     JSONObject topPanelResultObj = new JSONObject();
/*  724 */     JSONObject queryDataObj = new JSONObject();
/*      */     try {
/*  726 */       ApplicationUtilities.debug(getClass(), " getTopPanel ::: calling");
/*  727 */       Registration registeredMember = getMemberDetailsByDeptIdAndCardNo(deptId, ""+cardNo);
/*      */       
/*  729 */       if (registeredMember != null) {
/*  730 */         ApplicationUtilities.debug(getClass(), " registeredMember ::: " + registeredMember.toString());
/*  731 */         String fileName = String.valueOf(registeredMember.getFileName()) + "." + registeredMember.getFileType();
/*  732 */         queryDataObj.put("CARD_NO", Integer.valueOf(registeredMember.getRegistrationPK().getCardNo()));
/*  733 */         queryDataObj.put("DEPT_ID", registeredMember.getRegistrationPK().getDeptId());
/*  734 */         queryDataObj.put("MEMBER_ID", registeredMember.getRegistrationPK().getMemberId());
/*  735 */         queryDataObj.put("DEPT_NAME", registeredMember.getDeptName());
/*  736 */         queryDataObj.put("FIRST_NAME", registeredMember.getFirstName());
/*  737 */         queryDataObj.put("PAYMENT_RECEIPT_NO", registeredMember.getReceiptNo());
/*  738 */         queryDataObj.put("PERMINENT_ADDRESS", registeredMember.getPerminentAddress());
/*      */         
/*  740 */         queryDataObj.put("REGISTERED_DATE", (
/*  741 */             new SimpleDateFormat("dd/MM/yyyy")).format(registeredMember.getRegisteredDate()));
/*  742 */         queryDataObj.put("DATE_OF_BIRTH", (
/*  743 */             new SimpleDateFormat("dd/MM/yyyy")).format(registeredMember.getDateOfBirth()));
/*      */         
/*  745 */         queryDataObj.put("CARD_BALANCE", registeredMember.getCardBalance());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  750 */         queryDataObj.put("CURRENT_LOAN_BALANCE", registeredMember.getCurrentLoanBalance());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  755 */         queryDataObj.put("PHONE_NO", registeredMember.getPhoneNo());
/*  756 */         queryDataObj.put("ALT_PHONE_NO", registeredMember.getAltPhoneNo());
/*      */         
/*  758 */         queryDataObj.put("FATHER_NAME", registeredMember.getFatherName());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  763 */         queryDataObj.put("FILE_CONTENT", 
/*  764 */             this.utils.convertImageToBase64(registeredMember.getFileContent(), registeredMember.getFileType()));
/*  765 */         queryDataObj.put("FILE_TYPE", registeredMember.getFileType());
/*  766 */         topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "400");
/*  767 */         topPanelResultObj.put("TOP_PANEL_DETAILS", queryDataObj);
/*      */       } else {
/*      */         
/*  770 */         topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "200");
/*  771 */         topPanelResultObj.put("TOP_PANEL_ERROR_MSG", 
/*  772 */             "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */       }
/*      */     
/*  775 */     } catch (Exception e) {
/*      */       
/*  777 */       topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "300");
/*  778 */       topPanelResultObj.put("TOP_PANEL_ERROR_MSG", e.getMessage());
/*  779 */       ApplicationUtilities.error(getClass(), e, "getTopPanel");
/*      */     } 
/*      */ 
/*      */     
/*  783 */     return topPanelResultObj;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public JSONObject getTopPanelDetails(int cardNo, String deptId, String pageId) {
/*  788 */     Registration registeredMember = getMemberDetailsByDeptIdAndCardNo(deptId, ""+cardNo);
/*      */ 
/*      */ 
/*      */     
/*  792 */     JSONObject topPanelResultObj = new JSONObject();
/*      */     
/*  794 */     JSONObject queryDataObj = new JSONObject();
/*      */     try {
/*  796 */       if (registeredMember != null) {
/*  797 */         String memberId = registeredMember.getRegistrationPK().getMemberId();
/*  798 */         if (registeredMember != null && !"".equalsIgnoreCase(memberId)) {
/*      */           
/*  800 */           String query = " SELECT  C.FILE_NAME,  A.CARD_NO,  A.DEPT_ID,  A.MEMBER_ID,  A.DEPT_NAME,  A.FIRST_NAME,  A.RECEIPT_NO,  A.PERMINENT_ADDRESS,  A.REGISTERED_DATE,  A.DATE_OF_BIRTH,  A.CURRENT_LOAN_BALANCE ,  A.CARD_BALANCE,  A.PHONE_NO,  C.FILE_CONTENT,  C.FILE_TYPE , A.PAYMENT_CONF_ID,  A.TRANSACTION_ID  FROM    REGISTRATION A LEFT OUTER JOIN ATTACHMENTS C  ON A.MEMBER_ID=C.MEMBER_ID  WHERE  A.MEMBER_ID=:memberId AND C.ATTACHMENT_CATEGORY='PROFILE_PIC' ";
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
/*  821 */           query = "from Registration where registrationPK.cardNo=:cardNo and registrationPK.deptId=:deptId ";
/*      */ 
/*      */ 
/*      */           
/*  825 */           if (registeredMember != null)
/*      */           {
/*  827 */             String fileName = String.valueOf(registeredMember.getFileName()) + "." + registeredMember.getFileType();
/*  828 */             queryDataObj.put("CARD_NO", Integer.valueOf(registeredMember.getRegistrationPK().getCardNo()));
/*  829 */             queryDataObj.put("DEPT_ID", registeredMember.getRegistrationPK().getDeptId());
/*  830 */             queryDataObj.put("MEMBER_ID", registeredMember.getRegistrationPK().getMemberId());
/*  831 */             queryDataObj.put("DEPT_NAME", registeredMember.getDeptName());
/*  832 */             queryDataObj.put("FIRST_NAME", registeredMember.getFirstName());
/*  833 */             queryDataObj.put("PAYMENT_RECEIPT_NO", registeredMember.getPaymentConfId());
/*  834 */             queryDataObj.put("PERMINENT_ADDRESS", registeredMember.getPerminentAddress());
/*  835 */             queryDataObj.put("REGISTERED_DATE", (
/*  836 */                 new SimpleDateFormat("dd/MM/yyyy")).format(registeredMember.getRegisteredDate()));
/*  837 */             queryDataObj.put("DATE_OF_BIRTH", (
/*  838 */                 new SimpleDateFormat("dd/MM/yyyy")).format(registeredMember.getDateOfBirth()));
/*  839 */             queryDataObj.put("CURRENT_LOAN_BALANCE", registeredMember.getCurrentLoanBalance());
/*  840 */             queryDataObj.put("CARD_BALANCE", 
/*  841 */                 Integer.valueOf(getCardBalance(memberId, deptId, registeredMember.getPaymentConfId(), 
/*  842 */                     registeredMember.getRegistrationPK().getTransactionId(), "REGISTRATION")));
/*  843 */             queryDataObj.put("LOAN_BALANCE", registeredMember.getCurrentLoanBalance());
/*  844 */             queryDataObj.put("PHONE_NO", registeredMember.getPhoneNo());
/*  845 */             this.utils.fileWriter(String.valueOf(this.miscContext.getContextPath()) + "\\AppFiles\\Downloads", fileName, 
/*  846 */                 registeredMember.getFileContent());
/*  847 */             queryDataObj.put("FILE_CONTENT", this.utils.convertImageToBase64(registeredMember.getFileContent(), 
/*  848 */                   registeredMember.getFileType()));
/*  849 */             queryDataObj.put("FILE_TYPE", registeredMember.getFileType());
/*  850 */             topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "400");
/*  851 */             topPanelResultObj.put("TOP_PANEL_DETAILS", queryDataObj);
/*      */           }
/*      */           else
/*      */           {
/*  855 */             topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "200");
/*  856 */             topPanelResultObj.put("TOP_PANEL_ERROR_MSG", 
/*  857 */                 "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  862 */           topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "200");
/*  863 */           topPanelResultObj.put("TOP_PANEL_ERROR_MSG", 
/*  864 */               "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  869 */         topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "200");
/*  870 */         topPanelResultObj.put("TOP_PANEL_ERROR_MSG", 
/*  871 */             "No records found with DEPARTMENT=" + deptId + " and CARD NO =" + cardNo);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  876 */     catch (Exception e) {
/*      */       
/*  878 */       topPanelResultObj.put("TOP_PANEL_RESULT_CODE", "300");
/*  879 */       topPanelResultObj.put("TOP_PANEL_ERROR_MSG", e.getMessage());
/*  880 */       ApplicationUtilities.error(getClass(), e, "getTopPanelDetails");
/*      */     } 
/*  882 */     return topPanelResultObj;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public int getCardBalance(String memberId, String deptId, String paymentConfId, String transactionId, String category) {
/*  888 */     int cardBalance = 0;
/*      */ 
/*      */     
/*      */     try {
/*  892 */       PaymentConfigurations paymentConfigurations = getPaymentConfigurationsDetails(
/*  893 */           paymentConfId, deptId, category);
/*  894 */       if (paymentConfigurations != null) {
/*  895 */         int finalMemberShipAmmount = 0;
/*  896 */         int adminFeeAmount = getAdminFeePaidByMemberIdAndTransId(memberId, transactionId, category);
/*  897 */         int subscripionAmount = getSubscriptionAmountPaidByMemberIdAndTransId(memberId, transactionId, 
/*  898 */             category);
/*  899 */         int memberShipAmmount = getMebershipAmountPaidByMemberIdAndTransId(memberId);
/*  900 */         if (adminFeeAmount > -1 && subscripionAmount > -1 && memberShipAmmount > -1) {
/*  901 */           cardBalance = adminFeeAmount + subscripionAmount + memberShipAmmount;
/*      */         }
/*      */       }
/*      */     
/*      */     }
/*  906 */     catch (Exception e) {
/*      */       
/*  908 */       ApplicationUtilities.error(getClass(), e, "getCardBalance");
/*      */     } 
/*  910 */     return cardBalance;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int caluclateCardBalance(String memberId) {
/*  915 */     int cardBalance = 0;
/*  916 */     int paidAmount = 0;
/*  917 */     int cardAmount = 0;
/*      */     try {
/*  919 */       if (memberId != null && !"".equalsIgnoreCase(memberId)) {
/*  920 */         Registration registeredMember = getMemberDetailsByMemberId(memberId);
/*  921 */         if (registeredMember != null) {
/*  922 */           PaymentConfigurations paymentConfigurations = getPaymentConfigurationsDetailsById(
/*  923 */               registeredMember.getPaymentConfId());
/*  924 */           if (paymentConfigurations != null) {
/*  925 */             cardAmount = paymentConfigurations.getMembershipAmount();
/*  926 */             List<MembershipPayments> list = this.membershipDAO.getMembershipPaymentsByMemberId(memberId);
/*  927 */             if (list != null && list.size() > 0) {
/*  928 */               for (int i = 0; i < list.size(); i++) {
/*  929 */                 MembershipPayments membershipPayments = list.get(i);
/*  930 */                 paidAmount += membershipPayments.getPaidAmount();
/*      */               } 
/*      */             }
/*      */             
/*  934 */             cardBalance = cardAmount - paidAmount;
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*  940 */     } catch (Exception e) {
/*      */       
/*  942 */       ApplicationUtilities.error(getClass(), e, "caluclateCardBalance");
/*      */     } 
/*      */     
/*  945 */     return cardBalance;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int caluclateCardBalance(Registration registeredMember) {
/*  950 */     int cardBalance = 0;
/*  951 */     int paidAmount = 0;
/*  952 */     int cardAmount = 0;
/*      */     
/*      */     try {
/*  955 */       if (registeredMember != null) {
/*  956 */         PaymentConfigurations paymentConfigurations = getPaymentConfigurationsDetailsById(
/*  957 */             registeredMember.getPaymentConfId());
/*  958 */         if (paymentConfigurations != null) {
/*  959 */           cardAmount = paymentConfigurations.getMembershipAmount();
/*  960 */           List<MembershipPayments> list = this.membershipDAO
/*  961 */             .getMembershipPaymentsByMemberId(registeredMember.getRegistrationPK().getMemberId());
/*  962 */           if (list != null && list.size() > 0) {
/*  963 */             for (int i = 0; i < list.size(); i++) {
/*  964 */               MembershipPayments membershipPayments = list.get(i);
/*  965 */               paidAmount += membershipPayments.getPaidAmount();
/*      */             } 
/*      */           }
/*      */           
/*  969 */           cardBalance = cardAmount - paidAmount;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  976 */     catch (Exception e) {
/*      */       
/*  978 */       ApplicationUtilities.error(getClass(), e, "caluclateCardBalance");
/*      */     } 
/*      */     
/*  981 */     return cardBalance;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public void updateCardBalance(String memberId) {
/*      */     try {
/*  987 */       if (memberId != null && !"".equalsIgnoreCase(memberId)) {
/*  988 */         Registration registeredMember = getMemberDetailsByMemberId(memberId);
/*  989 */         registeredMember.setCardBalance(String.valueOf(caluclateCardBalance(registeredMember)));
/*  990 */         this.dataAccess.update(registeredMember);
/*      */       }
/*      */     
/*  993 */     } catch (Exception e) {
/*  994 */       ApplicationUtilities.error(getClass(), e, "updateCardBalance");
/*      */     } 
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int caluclateLoanBalance(Registration registeredMember) {
/* 1000 */     int loanBalance = 0;
/* 1001 */     int sanctionLaonAmount = 0;
/* 1002 */     int paidLoanAmount = 0;
/*      */     try {
/* 1004 */       if (registeredMember != null) {
/* 1005 */         Loandetails loandetails = getCurrentLoanDetails(registeredMember.getRegistrationPK().getMemberId(), 
/* 1006 */             registeredMember.getCurrentLoanId());
/*      */         
/* 1008 */         if (loandetails != null) {
/* 1009 */           sanctionLaonAmount = getTotalLoanAmount(registeredMember.getRegistrationPK().getMemberId());
/* 1010 */           paidLoanAmount = getPaidLoanAmount(registeredMember.getRegistrationPK().getMemberId());
/* 1011 */           loanBalance = sanctionLaonAmount - paidLoanAmount;
/*      */         }
/*      */       
/*      */       } 
/* 1015 */     } catch (Exception e) {
/*      */       
/* 1017 */       ApplicationUtilities.error(getClass(), e, "caluclateLoanBalance");
/*      */     } 
/*      */     
/* 1020 */     return loanBalance;
/*      */   }
/*      */   @Transactional
/*      */   public int caluclateLoanBalance(String memberId) {
/* 1024 */     int loanBalance = 0;
/* 1025 */     int sanctionLaonAmount = 0;
/* 1026 */     int paidLoanAmount = 0;
/*      */ 
/*      */     
/*      */     try {
/* 1030 */       sanctionLaonAmount = getTotalLoanAmount(memberId);
/* 1031 */       if (sanctionLaonAmount > 0) {
/* 1032 */         paidLoanAmount = getPaidLoanAmount(memberId);
/* 1033 */         loanBalance = sanctionLaonAmount - paidLoanAmount;
/*      */       } else {
/*      */         
/* 1036 */         return loanBalance;
/*      */       }
/*      */     
/* 1039 */     } catch (Exception e) {
/* 1040 */       ApplicationUtilities.error(getClass(), e, "caluclateLoanBalance");
/*      */     } 
/*      */     
/* 1043 */     return loanBalance;
/*      */   }
/*      */   @Transactional
/*      */   public void updateLoanBalance(String memberId) {
/* 1047 */     String finalLoanBalanceStr = "";
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1052 */       if (memberId != null && !"".equalsIgnoreCase(memberId)) {
/* 1053 */         Registration registeredMember = getMemberDetailsByMemberId(memberId);
/*      */         
/* 1055 */         if (registeredMember != null) {
/* 1056 */           int loanBalance = caluclateLoanBalance(memberId);
/* 1057 */           if (loanBalance > 0) {
/* 1058 */             int i = loanBalance;
/* 1059 */             registeredMember.setCurrentLoanStatus("LOAN_UNDER_RECOVERY");
/* 1060 */             registeredMember.setCurrentLoanBalance(""+i);
/* 1061 */           } else if (loanBalance == 0) {
/* 1062 */             int i = loanBalance;
/* 1063 */             registeredMember.setCurrentLoanStatus("LOAN_CLOSED");
/* 1064 */             registeredMember.setCurrentLoanBalance(""+i);
/* 1065 */           } else if (loanBalance < 0) {
/* 1066 */             finalLoanBalanceStr = "0";
/* 1067 */             registeredMember.setCurrentLoanStatus("LOAN_CLOSED");
/* 1068 */             registeredMember.setCurrentLoanBalance(finalLoanBalanceStr);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1073 */           this.dataAccess.update(registeredMember);
/*      */         } 
/*      */       } 
/* 1076 */     } catch (Exception e) {
/* 1077 */       ApplicationUtilities.error(getClass(), e, "updateLoanBalance");
/*      */     } 
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public Loandetails getCurrentLoanDetails(String memberId, String loanId) {
/* 1083 */     Loandetails loanDetails = null;
/*      */     
/*      */     try {
/* 1086 */       if (loanId != null && memberId != null && 
/* 1087 */         !"".equalsIgnoreCase(loanId) && !"".equalsIgnoreCase(memberId))
/*      */       {
/* 1089 */         String query = "from Loandetails where loandetailsPK.memberId=:memberId and   loandetailsPK.loanId=:loanId";
/* 1090 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1091 */         parametersMap.put("memberId", memberId);
/* 1092 */         parametersMap.put("loanId", loanId);
/*      */         
/* 1094 */         List<Loandetails> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 1095 */         if (list != null && list.size() > 0) {
/* 1096 */           loanDetails = list.get(0);
/*      */         }
/*      */       }
/*      */     
/* 1100 */     } catch (Exception e) {
/* 1101 */       ApplicationUtilities.error(getClass(), e, "getCurrentLoanDetails");
/*      */     } 
/* 1103 */     return loanDetails;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int getPaidLoanAmount(String memberId, String loanId) {
/* 1108 */     int paidLoanAmount = 0;
/*      */     
/*      */     try {
/* 1111 */       if (loanId != null && memberId != null && 
/* 1112 */         !"".equalsIgnoreCase(loanId) && !"".equalsIgnoreCase(memberId)) {
/*      */         
/* 1114 */         String query = "from LoanRecoveryDetails where loanRecoveryDetailsPK.memberId=:memberId and   loanRecoveryDetailsPK.loanId=:loanId";
/* 1115 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1116 */         parametersMap.put("memberId", memberId);
/*      */         
/* 1118 */         parametersMap.put("loanId", loanId);
/* 1119 */         List<LoanRecoveryDetails> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 1120 */         if (list != null && list.size() > 0)
/*      */         {
/* 1122 */           for (int i = 0; i < list.size(); i++) {
/* 1123 */             LoanRecoveryDetails obj = list.get(i);
/* 1124 */             paidLoanAmount += obj.getPaidAmount();
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/* 1129 */     } catch (Exception e) {
/*      */       
/* 1131 */       ApplicationUtilities.error(getClass(), e, "getPaidLoanAmount");
/*      */     } 
/* 1133 */     return paidLoanAmount;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int getPaidLoanAmount(String memberId) {
/* 1138 */     int paidLoanAmount = 0;
/*      */     
/*      */     try {
/* 1141 */       if (memberId != null && 
/* 1142 */         !"".equalsIgnoreCase(memberId)) {
/*      */         
/* 1144 */         String query = "from LoanRecoveryDetails where loanRecoveryDetailsPK.memberId=:memberId ";
/* 1145 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1146 */         parametersMap.put("memberId", memberId);
/*      */ 
/*      */         
/* 1149 */         List<LoanRecoveryDetails> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 1150 */         if (list != null && list.size() > 0)
/*      */         {
/* 1152 */           for (int i = 0; i < list.size(); i++) {
/* 1153 */             LoanRecoveryDetails obj = list.get(i);
/* 1154 */             paidLoanAmount += obj.getPaidAmount();
/*      */           }
/*      */         
/*      */         }
/*      */       } 
/* 1159 */     } catch (Exception e) {
/*      */       
/* 1161 */       ApplicationUtilities.error(getClass(), e, "getPaidLoanAmount");
/*      */     } 
/* 1163 */     return paidLoanAmount;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int getAdminFeePaidByMemberIdAndTransId(String memberId, String transactionId, String category) {
/* 1168 */     int paidPdminFee = -1;
/*      */     
/*      */     try {
/* 1171 */       if (transactionId != null && memberId != null && category != null && !"".equalsIgnoreCase(transactionId) && 
/* 1172 */         !"".equalsIgnoreCase(memberId) && !"".equalsIgnoreCase(category)) {
/*      */         
/* 1174 */         String query = "from AdminfeePayments where adminfeePaymentsPK.memberId=:memberId and   adminfeePaymentsPK.transactionId=:transactionId and category=:category";
/* 1175 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1176 */         parametersMap.put("memberId", memberId);
/* 1177 */         parametersMap.put("transactionId", transactionId);
/* 1178 */         parametersMap.put("category", category);
/* 1179 */         List<AdminfeePayments> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/*      */         
/* 1181 */         if (list != null && list.size() > 0) {
/* 1182 */           paidPdminFee = 0;
/* 1183 */           for (int i = 0; i < list.size(); i++) {
/* 1184 */             AdminfeePayments obj = list.get(i);
/* 1185 */             paidPdminFee += obj.getAdminFeeAmount();
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/* 1191 */     } catch (Exception e) {
/*      */       
/* 1193 */       ApplicationUtilities.error(getClass(), e, "getAdminFeePaidByMemberIdAndTransId");
/*      */     } 
/* 1195 */     return paidPdminFee;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int getSubscriptionAmountPaidByMemberIdAndTransId(String memberId, String transactionId, String category) {
/* 1200 */     int paidSubscriptionAmount = -1;
/*      */     
/*      */     try {
/* 1203 */       if (transactionId != null && memberId != null && category != null && !"".equalsIgnoreCase(transactionId) && 
/* 1204 */         !"".equalsIgnoreCase(memberId) && !"".equalsIgnoreCase(category)) {
/*      */         
/* 1206 */         String query = "from SubscriptionPayments where subscriptionPaymentsPK.memberId=:memberId and   subscriptionPaymentsPK.transactionId=:transactionId and category=:category";
/* 1207 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1208 */         parametersMap.put("memberId", memberId);
/* 1209 */         parametersMap.put("transactionId", transactionId);
/* 1210 */         parametersMap.put("category", category);
/* 1211 */         List<SubscriptionPayments> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/* 1212 */         if (list != null && list.size() > 0) {
/* 1213 */           paidSubscriptionAmount = 0;
/* 1214 */           for (int i = 0; i < list.size(); i++) {
/* 1215 */             SubscriptionPayments obj = list.get(i);
/* 1216 */             paidSubscriptionAmount += obj.getSubscriptionAmount();
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/* 1222 */     } catch (Exception e) {
/*      */       
/* 1224 */       ApplicationUtilities.error(getClass(), e, "getSubscriptionAmountPaidByMemberIdAndTransId");
/*      */     } 
/* 1226 */     return paidSubscriptionAmount;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public int getMebershipAmountPaidByMemberIdAndTransId(String memberId) {
/* 1231 */     int paidMebershiAmount = -1;
/*      */     
/*      */     try {
/* 1234 */       if (memberId != null && !"".equalsIgnoreCase(memberId)) {
/* 1235 */         String query = "from MembershipPayments where membershipPaymentsPK.memberId=:memberId ";
/* 1236 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1237 */         parametersMap.put("memberId", memberId);
/* 1238 */         List<MembershipPayments> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/* 1239 */         if (list != null && list.size() > 0) {
/* 1240 */           paidMebershiAmount = 0;
/* 1241 */           for (int i = 0; i < list.size(); i++) {
/* 1242 */             MembershipPayments obj = list.get(i);
/* 1243 */             paidMebershiAmount += obj.getPaidAmount();
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/* 1249 */     } catch (Exception e) {
/*      */       
/* 1251 */       ApplicationUtilities.error(getClass(), e, "getMebershipAmountPaidByMemberIdAndTransId");
/*      */     } 
/* 1253 */     return paidMebershiAmount;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public Registration getMemberDetailsByMemberId(String memberId) {
/* 1258 */     Registration registration = null;
/*      */     
/*      */     try {
/* 1262 */       String getRegistrationQuery = "from Registration where registrationPK.memberId =:memberId ";
/* 1263 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1264 */       parametersMap.put("memberId", memberId);
/*      */ 
/*      */       
/* 1267 */       List<Registration> list = this.dataAccess.queryWithParams(getRegistrationQuery, parametersMap);
/* 1269 */       if (list != null && !list.isEmpty() && list.size() > 0) {
/* 1270 */         registration = list.get(0);
/*      */       }
/*      */     }
/* 1273 */     catch (Exception e) {
/*      */       
/* 1275 */       ApplicationUtilities.error(getClass(), e, "getMemberDetailsByMemberId");
/*      */     } 
/* 1277 */     return registration;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public PaymentConfigurations getPaymentConfigurationsDetails(String paymentConfId, String deptId, String category) {
/* 1283 */     PaymentConfigurations paymentConfigurations = null;
/*      */     
/*      */     try {
/* 1286 */       if (paymentConfId != null && deptId != null && category != null && !"".equalsIgnoreCase(paymentConfId) && 
/* 1287 */         !"".equalsIgnoreCase(deptId) && !"".equalsIgnoreCase(category))
/*      */       {
/* 1289 */         String query = "from PaymentConfigurations where paymentConfigurationsPK.paymentConfId=:paymentConfId and paymentConfigurationsPK.deptId=:deptId and paymentConfigurationsPK.category=:category ";
/* 1290 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1291 */         parametersMap.put("paymentConfId", paymentConfId);
/* 1292 */         parametersMap.put("deptId", deptId);
/* 1293 */         parametersMap.put("category", category);
/* 1294 */         List<PaymentConfigurations> list = this.dataAccess.sqlqueryWithParams(query, parametersMap);
/*      */         
/* 1296 */         if (list != null && list.size() > 0) {
/* 1297 */           paymentConfigurations = list.get(0);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1302 */     catch (Exception e) {
/*      */       
/* 1304 */       ApplicationUtilities.error(getClass(), e, "getPaymentConfigurationsDetails");
/*      */     } 
/* 1306 */     return paymentConfigurations;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public PaymentConfigurations getPaymentConfigurationsDetailsById(String paymentConfId) {
/* 1311 */     PaymentConfigurations paymentConfigurations = null;
/*      */     
/*      */     try {
/* 1314 */       if (paymentConfId != null && !"".equalsIgnoreCase(paymentConfId)) {
/* 1315 */         String query = "from PaymentConfigurations where paymentConfigurationsPK.paymentConfId=:paymentConfId ";
/* 1316 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1317 */         parametersMap.put("paymentConfId", paymentConfId);
/* 1318 */         List<PaymentConfigurations> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 1319 */         if (list != null && list.size() > 0) {
/* 1320 */           paymentConfigurations = list.get(0);
/*      */         }
/*      */       }
/*      */     
/*      */     }
/* 1325 */     catch (Exception e) {
/*      */       
/* 1327 */       ApplicationUtilities.error(getClass(), e, "getPaymentConfigurationsDetailsById");
/*      */     } 
/* 1329 */     return paymentConfigurations;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public Registration getMemberDetailsByDeptIdAndCardNo(String deptId, String cardNo) {
/* 1334 */     Registration registration = null;
/*      */     
/*      */     try {
/* 1337 */       String getRegistrationQuery = "from Registration where registrationPK.cardNo =:cardNo and  registrationPK.deptId=:deptId ";
/* 1338 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1339 */       parametersMap.put("deptId", deptId);
/* 1340 */       parametersMap.put("cardNo", Integer.valueOf(Integer.parseInt(cardNo)));
/* 1341 */       List<Registration> list = this.dataAccess.queryWithParams(getRegistrationQuery, parametersMap);
/*      */       
/* 1343 */       if (list != null && !list.isEmpty() && list.size() > 0) {
/* 1344 */         registration = list.get(0);
/*      */       
/*      */       }
/*      */     }
/* 1348 */     catch (Exception e) {
/*      */       
/* 1350 */       ApplicationUtilities.error(getClass(), e, "getMemberDetailsByDeptIdAndCardNo");
/*      */     } 
/* 1352 */     return registration;
/*      */   }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public int getTotalLoanAmount(String memberId) {
/* 1384 */     List searchDetailsList = null;
/* 1385 */     int totalLoanAmount = 0;
/*      */     
/*      */     try {
/* 1388 */       String getTotalLoanAmount = "from Loandetails where loandetailsPK.memberId =:memberId and loanStatus=:LOAN_STATUS";
/* 1389 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1390 */       parametersMap.put("memberId", memberId);
/* 1391 */       parametersMap.put("LOAN_STATUS", "LOAN_UNDER_RECOVERY");
/* 1392 */       List<Loandetails> list = this.dataAccess.queryWithParams(getTotalLoanAmount, parametersMap);
/*      */       
/* 1394 */       if (list != null && list.size() > 0)
/*      */       {
/* 1396 */         for (int j = 0; j < list.size(); j++) {
/* 1397 */           Loandetails loandetails = list.get(j);
/* 1398 */           totalLoanAmount += loandetails.getLoanAmount();
/*      */         }
/*      */       
/*      */       }
/*      */     }
/* 1403 */     catch (Exception e) {
/*      */       
/* 1405 */       ApplicationUtilities.error(getClass(), e, "getTotalLoanAmount");
/*      */     } 
/* 1407 */     return totalLoanAmount;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public int getLoanDetails(String memberId) {
/* 1413 */     List searchDetailsList = null;
/* 1414 */     int totalLoanAmount = 0;
/*      */     
/*      */     try {
/* 1417 */       String getTotalLoanAmount = "from Loandetails where loandetailsPK.memberId =:memberId and loanStatus=:LOAN_STATUS";
/* 1418 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1419 */       parametersMap.put("memberId", memberId);
/* 1420 */       parametersMap.put("LOAN_STATUS", "LOAN_UNDER_RECOVERY");
/* 1421 */       List<Loandetails> list = this.dataAccess.queryWithParams(getTotalLoanAmount, parametersMap);
/*      */       
/* 1423 */       if (list != null && list.size() > 0)
/*      */       {
/* 1425 */         for (int j = 0; j < list.size(); j++) {
/* 1426 */           Loandetails loandetails = list.get(j);
/* 1427 */           totalLoanAmount += loandetails.getLoanAmount();
/*      */         }
/*      */       
/*      */       }
/*      */     }
/* 1432 */     catch (Exception e) {
/*      */       
/* 1434 */       e.printStackTrace();
/* 1435 */       ApplicationUtilities.error(getClass(), e, "ApplicationUtilities.error(this.getClass(), e, \"getTotalLoanAmount\") ;");
/*      */     } 
/* 1437 */     return totalLoanAmount;
/*      */   }







/*      */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\MiscellaneousDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */