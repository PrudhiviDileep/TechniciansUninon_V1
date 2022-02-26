/*      */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*      */ 
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.AdminfeePayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.AdminfeePaymentsPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.CardNubers;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.CardNubersPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Departments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPaymentsPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RecommendationDetails;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RegistrationPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPaymentsPK;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Units;
/*      */ import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import javax.servlet.ServletContext;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import javax.servlet.http.HttpSession;
/*      */ import javax.transaction.Transactional;
/*      */ import org.json.simple.JSONObject;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.stereotype.Repository;
/*      */ import org.springframework.web.multipart.MultipartFile;
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
/*      */ public class TeluguCineAndTVOutDoorUnitTechniciansUnionDAO
/*      */ {
/*      */   @Autowired
/*      */   private DataAccess dataAccess;
/*      */   @Autowired
/*      */   public MiscellaneousDAO miscellaneousDAO;
/*      */   @Autowired
/*      */   private IdGenerator idGenerator;
/*      */   @Autowired
/*      */   private HttpSession httpSession;
/*      */   private static final String UPLOADED_FOLDER = "\\AppFiles\\Uploads";
/*      */   private static final String DOWNLOAD_FOLDER = "\\AppFiles\\Downloads";
/*   56 */   Utils utils = new Utils();
/*      */   
/*      */   @Autowired
/*      */   ServletContext context;
/*      */   
/*      */   @Autowired
/*      */   LoanDAO loanDAO;
/*   63 */   public String registratinErrorMessage = "Sorry Registration Failed !";
/*      */ 
/*      */ 
/*      */   
/*      */   public String login(HttpServletRequest request, Map<String, Object> model) {
/*   68 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String registration(JSONObject items, MultipartFile[] file) {
/*   74 */     JSONObject finalResult = new JSONObject();
/*   75 */     JSONObject resultObj = new JSONObject();
/*   76 */     int insertedImages = 0;
/*   77 */     int insertedDefaultImages = 0;
/*      */ 
/*      */     
/*      */     try {
/*   81 */       if (items != null)
/*      */       {
/*      */ 
/*      */         
/*   85 */         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/*   86 */         String category = "REGISTRATION";
/*   87 */         String paymentStatus = "Yes";
/*   88 */         String remarks = "Registration";
/*   89 */         String firstName = ((String)items.get("memberName") != null) ? (String)items.get("memberName") : "";
/*   90 */         String fatherName = ((String)items.get("fatherName") != null) ? (String)items.get("fatherName") : "";
/*   91 */         String dateOfBirth = ((String)items.get("dateOfBirth") != null) ? (String)items.get("dateOfBirth") : 
/*   92 */           "";
/*   93 */         String eduQualification = ((String)items.get("eduQualification") != null) ? 
/*   94 */           (String)items.get("eduQualification") : "";
/*   95 */         String motherTongue = ((String)items.get("motherTongue") != null) ? (String)items.get("motherTongue") : 
/*   96 */           "";
/*   97 */         String knownLanguages = ((String)items.get("knownLang") != null) ? (String)items.get("knownLang") : 
/*   98 */           "";
/*   99 */         String bloodGroup = ((String)items.get("bloodGroup") != null) ? (String)items.get("bloodGroup") : "";
/*  100 */         String fefsiMember = ((String)items.get("workDetails_fefsiNumber") != null) ? 
/*  101 */           (String)items.get("workDetails_fefsiNumber") : "";
/*  102 */         String registeredDate = ((String)items.get("workDetails_dateOfJoining") != null) ? 
/*  103 */           (String)items.get("workDetails_dateOfJoining") : "";
/*      */ 
/*      */         
/*  106 */         String nameOfcompany = ((String)items.get("workDetails_placeOfWork") != null) ? (String)items.get("workDetails_placeOfWork") : "";
/*  107 */         String natureOfWork = ((String)items.get("workDetails_deptName") != null) ? (String)items.get("workDetails_deptName") : "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  116 */         String deptName = ((String)items.get("deptName") != null) ? (String)items.get("deptName") : "";
/*  117 */         String deptId = (String)items.get("deptId");
/*      */ 
/*      */         
/*  120 */         String receiptNo = ((String)items.get("regPaymentDetails_receiptNo") != null) ? 
/*  121 */           (String)items.get("regPaymentDetails_receiptNo") : "";
/*  122 */         String appliedDatae = ((String)items.get("approvedDetails_appliedDate") != null) ? 
/*  123 */           (String)items.get("approvedDetails_appliedDate") : "";
/*      */         
/*  125 */         String unitId = ((String)items.get("approvedDetails_unitId") != null) ? 
/*  126 */           (String)items.get("approvedDetails_unitId") : "";
/*  127 */         String unitName = ((String)items.get("approvedDetails_placeOfWork") != null) ? 
/*  128 */           (String)items.get("approvedDetails_placeOfWork") : "";
/*  129 */         String presentAddress = ((String)items.get("presentAddress") != null) ? 
/*  130 */           (String)items.get("presentAddress") : "";
/*  131 */         String phoneNo1 = ((String)items.get("phoneNo1") != null) ? (String)items.get("phoneNo1") : "";
/*  132 */         String perminentAddress = ((String)items.get("perminentAddress") != null) ? 
/*  133 */           (String)items.get("perminentAddress") : "";
/*  134 */         String phoneNo2 = ((String)items.get("phoneNo2") != null) ? (String)items.get("phoneNo2") : "";
/*  135 */         String nominie = ((String)items.get("nomineeDetails_name") != null) ? 
/*  136 */           (String)items.get("nomineeDetails_name") : "";
/*  137 */         String relationWithNominie = ((String)items.get("nomineeDetails_relation") != null) ? 
/*  138 */           (String)items.get("nomineeDetails_relation") : "";
/*  139 */         String recommend1DeptName = ((String)items.get("recommand1_deptName") != null) ? 
/*  140 */           (String)items.get("recommand1_deptName") : "";
/*  141 */         String recommend1DeptId = ((String)items.get("recommand1_deptId") != null) ? 
/*  142 */           (String)items.get("recommand1_deptId") : "";
/*  143 */         String recommand1cardNo = ((String)items.get("recommand1_cardNo") != null) ? 
/*  144 */           (String)items.get("recommand1_cardNo") : "";
/*  145 */         String recommend1Name = ((String)items.get("recommand1_name") != null) ? 
/*  146 */           (String)items.get("recommand1_name") : "";
/*  147 */         String recommend1UnitId = ((String)items.get("recommand1_unitId") != null) ? 
/*  148 */           (String)items.get("recommand1_unitId") : "";
/*  149 */         String recommend1WorkingPlace = ((String)items.get("recommand1_placeOfWork") != null) ? 
/*  150 */           (String)items.get("recommand1_placeOfWork") : "";
/*  151 */         String recommend2DeptName = ((String)items.get("recommand2_deptName") != null) ? 
/*  152 */           (String)items.get("recommand2_deptName") : "";
/*      */         
/*  154 */         String approvedDetails_unitId = ((String)items.get("approvedDetails_unitId") != null) ? 
/*  155 */           (String)items.get("approvedDetails_unitId") : "";
/*      */         
/*  157 */         String approvedDetails_placeOfWork = ((String)items.get("approvedDetails_placeOfWork") != null) ? 
/*  158 */           (String)items.get("approvedDetails_placeOfWork") : "";
/*      */         
/*  160 */         String recommend2DeptId = ((String)items.get("recommand2_deptId") != null) ? 
/*  161 */           (String)items.get("recommand2_deptId") : "";
/*  162 */         String recommand2cardNo = ((String)items.get("recommand2_cardNo") != null) ? 
/*  163 */           (String)items.get("recommand2_cardNo") : "";
/*  164 */         String recommend2Name = ((String)items.get("recommand2_name") != null) ? 
/*  165 */           (String)items.get("recommand2_name") : "";
/*  166 */         String recommend2UnitId = ((String)items.get("recommand2_unitId") != null) ? 
/*  167 */           (String)items.get("recommand2_unitId") : "";
/*  168 */         String recommend2WorkingPlace = ((String)items.get("recommand2_placeOfWork") != null) ? 
/*  169 */           (String)items.get("recommand2_placeOfWork") : "";
/*      */         
/*  171 */         String aadhar = ((String)items.get("aadharCardNo") != null) ? (String)items.get("aadharCardNo") : "";
/*      */         
/*  173 */         String bankname = ((String)items.get("bankDetails_bankName") != null) ? 
/*  174 */           (String)items.get("bankDetails_bankName") : "";
/*  175 */         String bankAccHolderName = ((String)items.get("bankDetails_accHolderName") != null) ? 
/*  176 */           (String)items.get("bankDetails_accHolderName") : "";
/*  177 */         String bankAccNo = ((String)items.get("bankDetails_accNumber") != null) ? 
/*  178 */           (String)items.get("bankDetails_accNumber") : "";
/*      */         
/*  180 */         String bankBranch = ((String)items.get("bankDetails_branch") != null) ? 
/*  181 */           (String)items.get("bankDetails_branch") : "";
/*      */         
/*  183 */         String bankIfscCode = ((String)items.get("bankDetails_ifsc") != null) ? 
/*  184 */           (String)items.get("bankDetails_ifsc") : "";
/*      */         
/*  186 */         String regPaymentDetails_cardAmount = ((String)items.get("regPaymentDetails_cardAmount") != null) ? 
/*  187 */           (String)items.get("regPaymentDetails_cardAmount") : "";
/*      */         
/*  189 */         String regPaymentDetails_paymentType = ((String)items
/*  190 */           .get("regPaymentDetails_paymentTypeOption") != null) ? 
/*  191 */           (String)items.get("regPaymentDetails_paymentTypeOption") : "";
/*      */         
/*  193 */         String regPaymentDetails_paymentMode = ((String)items.get("regPaymentDetails_paymentMode") != null) ? 
/*  194 */           (String)items.get("regPaymentDetails_paymentMode") : "";
/*      */         
/*  196 */         String regPaymentDetails_ddNo = ((String)items.get("regPaymentDetails_ddNo") != null) ? 
/*  197 */           (String)items.get("regPaymentDetails_ddNo") : "";
/*      */         
/*  199 */         String regPaymentDetails_paidDate = ((String)items.get("regPaymentDetails_paidDate") != null) ? 
/*  200 */           (String)items.get("regPaymentDetails_paidDate") : "";
/*  201 */         String regPaymentDetails_remarks = ((String)items.get("regPaymentDetails_remarks") != null) ? 
/*  202 */           (String)items.get("regPaymentDetails_remarks") : "";
/*  203 */         JSONObject validateObj = new JSONObject();
/*  204 */         validateObj.put("MEMBER_DOB", dateOfBirth);
/*  205 */         validateObj.put("MEMBER_APPLIED_DATE", appliedDatae);
/*  206 */         validateObj.put("MEMBER_REGISTERED_DATE", registeredDate);
/*  207 */         validateObj.put("MEMBER_DEPT_ID", deptId);
/*  208 */         validateObj.put("MEMBER_CARD_NO", 
/*  209 */             (items.get("approvedDetails_cardNo") != null) ? items.get("approvedDetails_cardNo") : "");
/*  210 */         validateObj.put("RECOMOND1_CARD_NO", recommand1cardNo);
/*  211 */         validateObj.put("RECOMOND1_DEPT_ID", recommend1DeptId);
/*  212 */         validateObj.put("RECOMOND2_CARD_NO", recommand2cardNo);
/*  213 */         validateObj.put("RECOMOND2_DEPT_ID", recommend2DeptId);
/*  214 */         validateObj.put("NOMINEE_NAME", nominie);
/*  215 */         validateObj.put("NOMINEE_RELATION", relationWithNominie);
/*  216 */         validateObj.put("AADHAR_CARD_NO", aadhar);
/*  217 */         validateObj.put("RECEIPT_NO", receiptNo);
/*      */         
/*  219 */         String validationResult = registrationValidation(validateObj, "REGISTRATION");
/*      */         
/*  221 */         if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult))
/*      */         {
/*  223 */           int cardNo = Integer.parseInt(((String)items.get("approvedDetails_cardNo") != null) ? 
/*  224 */               (String)items.get("approvedDetails_cardNo") : "");
/*  225 */           int paidAmount = Integer.parseInt(((String)items.get("regPaymentDetails_paidAmmount") != null) ? 
/*  226 */               (String)items.get("regPaymentDetails_paidAmmount") : "");
/*  227 */           PaymentConfigurations paymentConfigurations = this.miscellaneousDAO
/*  228 */             .getPaymentConfigurationsDetailsById(regPaymentDetails_paymentType);
/*      */           
/*  230 */           int adminFeeAmount = paymentConfigurations.getAdminAmount();
/*  231 */           int subscriptionAmount = paymentConfigurations.getSubscriptionAmount();
/*  232 */           int membershipAmount = paymentConfigurations.getMembershipAmount();
/*  233 */           int donationAmount = paidAmount - adminFeeAmount + subscriptionAmount;
/*      */           
/*  235 */           if (paidAmount <= adminFeeAmount + subscriptionAmount) {
/*  236 */             finalResult.put("FINAL_RESULT_CODE", "300");
/*  237 */             finalResult.put("ERROR_MSG", "Registration failed for paying very low membership amount !");
/*  238 */             return finalResult.toJSONString();
/*  239 */           }  if (paidAmount > membershipAmount) {
/*  240 */             finalResult.put("FINAL_RESULT_CODE", "300");
/*  241 */             finalResult.put("ERROR_MSG", "Registration failed due to paying more than membership amount !");
/*  242 */             return finalResult.toJSONString();
/*      */           } 
/*      */           
/*  245 */           int cardBalance = membershipAmount - paidAmount;
/*  246 */           String memberId = this.idGenerator.get("MEM-REG-ID", "MEMBER_ID");
/*  247 */           String transactionId = this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID");
/*  248 */           RegistrationPK registrationPK = new RegistrationPK();
/*  249 */           Registration registration = new Registration();
/*  250 */           registrationPK.setCardNo(cardNo);
/*  251 */           registrationPK.setDeptId(deptId);
/*  252 */           registrationPK.setMemberId(memberId);
/*  253 */           registration.setUnitId(unitId);
/*      */           
/*  255 */           registration.setAadharCardNo(aadhar);
/*  256 */           registrationPK.setTransactionId(transactionId);
/*  257 */           Units units = new Units();
/*  258 */           units.setUnitId(unitId);
/*  259 */           units.setUnitName(unitName);
/*  260 */           CardNubers cardNubers = new CardNubers();
/*  261 */           CardNubersPK cardNubersPk = new CardNubersPK();
/*  262 */           cardNubersPk.setCardNo(cardNo);
/*  263 */           cardNubersPk.setDeptId(deptId);
/*  264 */           cardNubers.setCardNubersPK(cardNubersPk);
/*  265 */           cardNubers.setCardStatus("ACTIVE");
/*  266 */           Departments departments = new Departments();
/*  267 */           departments.setDeptId(deptId);
/*  268 */           departments.setDeptName(deptName);
/*  269 */           this.dataAccess.save(cardNubers);
/*  270 */           registration.setRegistrationPK(registrationPK);
/*  271 */           registration.setFatherName(fatherName);
/*  272 */           registration.setFirstName(firstName);
/*  273 */           registration.setPhoneNo(phoneNo1);
/*  274 */           registration.setDateOfBirth(formatter.parse(dateOfBirth));
/*  275 */           registration.setAltPhoneNo(phoneNo2);
/*  276 */           registration.setAppliedDatae(formatter.parse(appliedDatae));
/*      */           
/*  278 */           registration.setRegTimeStamp(new Date());
/*  279 */           registration.setBloodGroup(bloodGroup);
/*  280 */           registration.setDeptName(deptName);
/*  281 */           registration.setEduQualification(eduQualification);
/*  282 */           registration.setFefsiMember(fefsiMember);
/*  283 */           registration.setKnownLanguages(knownLanguages);
/*  284 */           registration.setLastName("");
/*  285 */           registration.setMembershipAmount(membershipAmount);
/*  286 */           registration.setMiddleName("");
/*  287 */           registration.setMotherTongue(motherTongue);
/*  288 */           registration.setNameOfCompany(nameOfcompany);
/*  289 */           registration.setNatureOfWork(natureOfWork);
/*  290 */           registration.setNominie(nominie);
/*  291 */           registration.setPaidAmount(paidAmount);
/*  292 */           registration.setReceiptNo(receiptNo);
/*  293 */           registration.setPerminentAddress(perminentAddress);
/*  294 */           registration.setPresentAddress(presentAddress);
/*  295 */           registration.setRegisteredDate(formatter.parse(registeredDate));
/*  296 */           registration.setRelationWithNominie(relationWithNominie);
/*  297 */           registration.setCurrentLoanBalance("LOAN_NOT_SANCTIONED");
/*  298 */           registration.setCurrentLoanIssuedAmount("0");
/*  299 */           registration.setCurrentLoanStatus("LOAN_NOT_SANCTIONED");
/*  300 */           registration.setPaymentConfId(regPaymentDetails_paymentType);
/*  301 */           registration.setCardAmount(Integer.toString(membershipAmount));
/*  302 */           registration.setCardBalance(Integer.toString(cardBalance));
/*  303 */           registration.setMotherTongue(motherTongue);
/*  304 */           registration.setBankName(bankname);
/*      */           
/*  306 */           RecommendationDetails reccomendDetails = new RecommendationDetails();
/*  307 */           reccomendDetails.setMemberId(memberId);
/*  308 */           reccomendDetails.setRecommend1DeptId(recommend1DeptId);
/*  309 */           reccomendDetails.setRecommend1DeptName(recommend1DeptName);
/*  310 */           reccomendDetails.setRecommend1Name(recommend1Name);
/*  311 */           reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
/*  312 */           reccomendDetails.setRecommend1UnitId(recommend1UnitId);
/*  313 */           reccomendDetails.setRecommend1CardNo(Integer.parseInt(recommand1cardNo));
/*  314 */           reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
/*  315 */           reccomendDetails.setRecommend2DeptId(recommend2DeptId);
/*  316 */           reccomendDetails.setRecommend2DeptName(recommend2DeptName);
/*  317 */           reccomendDetails.setRecommend2Name(recommend2Name);
/*  318 */           reccomendDetails.setRecommend2WorkingPlace(recommend2WorkingPlace);
/*  319 */           reccomendDetails.setRecommend2CardNo(Integer.parseInt(recommand2cardNo));
/*  320 */           reccomendDetails.setRecommend2UnitId(recommend2UnitId);
/*  321 */           reccomendDetails.setMemberId(memberId);
/*      */           
/*  323 */           this.dataAccess.save(reccomendDetails);
/*      */           
/*  325 */           if (file != null && file.length > 0 && 
/*  326 */             !file[0].isEmpty()) {
/*  327 */             byte[] bytes = file[0].getBytes();
/*  328 */             String filetype = file[0].getOriginalFilename().substring(
/*  329 */                 file[0].getOriginalFilename().lastIndexOf(".") + 1, 
/*  330 */                 file[0].getOriginalFilename().length());
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
/*  349 */             registration.setFileContent(bytes);
/*  350 */             registration.setFileName(String.valueOf(String.valueOf(memberId)) + "_PROFILE_PIC");
/*  351 */             registration.setFileType(filetype.toUpperCase());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  381 */           registration.setBankAccHolderName(bankAccHolderName);
/*  382 */           registration.setBankAccNo(bankAccNo);
/*  383 */           registration.setBankBranch(bankBranch);
/*      */ 
/*      */           
/*      */           
/*  390 */           registration.setWorkingPlace(approvedDetails_placeOfWork);
/*  391 */           registration.setBankIfscCode(bankIfscCode);
/*  392 */           registration.setIsActive("Y");
/*  393 */           Date sqlDate = new Date((new Date()).getTime());
/*  394 */           MembershipPayments membershipPayments = new MembershipPayments();
/*  395 */           MembershipPaymentsPK membershipPaymentsPK = new MembershipPaymentsPK();
/*  396 */           membershipPaymentsPK.setMemberId(memberId);
/*  397 */           membershipPaymentsPK.setTransactionId(transactionId);
/*  398 */           membershipPayments.setMembershipPaymentsPK(membershipPaymentsPK);
/*  399 */           membershipPayments.setPaidDate(formatter.parse(regPaymentDetails_paidDate));
/*  400 */           membershipPayments.setPaidAmount(paidAmount);
/*  401 */           membershipPayments.setDonationAmount(donationAmount);
/*  402 */           membershipPayments.setReceiptNo(receiptNo);
/*  403 */           membershipPayments.setRemarks(regPaymentDetails_remarks);
/*  404 */           membershipPayments.setCategory("REGISTRATION");
/*  405 */           membershipPayments.setPaymentConfId(regPaymentDetails_paymentType);
/*  406 */           membershipPayments.setPaymentType(regPaymentDetails_paymentMode);
/*  407 */           membershipPayments.setDdNo(regPaymentDetails_ddNo);
/*  408 */           this.dataAccess.save(membershipPayments);
/*  409 */           SubscriptionPayments subscriptionPayments = new SubscriptionPayments();
/*  410 */           SubscriptionPaymentsPK subscriptionPaymentsPK = new SubscriptionPaymentsPK();
/*  411 */           subscriptionPayments.setMemberId(memberId);
/*  412 */           subscriptionPayments.setTransactionId(transactionId);
/*  413 */           subscriptionPaymentsPK.setDeptId(deptId);
/*  414 */           subscriptionPayments.setSubscriptionPaymentsPK(subscriptionPaymentsPK);
/*  415 */           subscriptionPayments.setCategory(category);
/*  416 */           subscriptionPayments.setSubscriptionAmount(subscriptionAmount);
/*  417 */           subscriptionPayments.setPaidDate(formatter.parse(registeredDate));
/*  418 */           subscriptionPayments.setPaymentConfId(regPaymentDetails_paymentType);
/*  419 */           subscriptionPayments.setPaymentStatus(paymentStatus);
/*  420 */           subscriptionPayments.getSubscriptionPaymentsPK().setReceiptNo(receiptNo);
/*  421 */           subscriptionPayments.setRemarks(remarks);
/*  422 */           int subscriptionYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/*  423 */           subscriptionPayments.getSubscriptionPaymentsPK().setSubscriptionYear(subscriptionYear);
/*      */           
/*  425 */           this.dataAccess.save(subscriptionPayments);
/*  426 */           AdminfeePayments adminfeePayments = new AdminfeePayments();
/*  427 */           AdminfeePaymentsPK adminfeePaymentsPK = new AdminfeePaymentsPK();
/*  428 */           adminfeePaymentsPK.setMemberId(memberId);
/*  429 */           adminfeePaymentsPK.setTransactionId(transactionId);
/*  430 */           adminfeePayments.setAdminFeeAmount(adminFeeAmount);
/*  431 */           adminfeePayments.setCategory(category);
/*  432 */           adminfeePayments.setPaymentConfId(regPaymentDetails_paymentType);
/*  433 */           adminfeePayments.setPaymentDate(sqlDate);
/*  434 */           adminfeePayments.setRemarks(remarks);
/*  435 */           adminfeePayments.setAdminfeePaymentsPK(adminfeePaymentsPK);
/*  436 */           this.dataAccess.save(adminfeePayments);
/*  437 */           resultObj.put("REGISTRATION_RESULT", "Registered Successfully !");
/*  438 */           resultObj.put("MEBER_ID", memberId);
/*  439 */           resultObj.put("MEBER_DEPT_ID", deptId);
/*  440 */           resultObj.put("MEBER_CARD_NO", Integer.valueOf(cardNo));
/*  441 */           finalResult.put("FINAL_RESULT_CODE", "400");
/*  442 */           finalResult.put("DATA_DETAILS", resultObj);
/*  443 */           this.dataAccess.save(registration);
/*      */         }
/*      */         else
/*      */         {
/*  447 */           finalResult.put("FINAL_RESULT_CODE", "300");
/*  448 */           finalResult.put("ERROR_MSG", validationResult);
/*      */         }
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*  454 */         finalResult.put("FINAL_RESULT_CODE", "300");
/*  455 */         finalResult.put("ERROR_MSG", "No registration form data found !");
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  460 */     catch (Exception e) {
/*      */       
/*  462 */       finalResult.put("FINAL_RESULT_CODE", "300");
/*  463 */       finalResult.put("ERROR_MSG", e.getMessage());
/*  464 */       e.printStackTrace();
/*      */     } 
/*  466 */     return finalResult.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String registrationValidation(JSONObject obj, String type) {
/*  473 */     String result = "Sorry Registration Failed !";
/*      */     
/*      */     try {
/*  476 */       if (obj != null) {
/*  477 */         String memberDob = (String)obj.get("MEMBER_DOB");
/*  478 */         String memberAppliedDate = (String)obj.get("MEMBER_APPLIED_DATE");
/*  479 */         String memberRegisteredDate = (String)obj.get("MEMBER_REGISTERED_DATE");
/*  480 */         String memberDeptId = (String)obj.get("MEMBER_DEPT_ID");
/*  481 */         String memberCardNo = (String)obj.get("MEMBER_CARD_NO");
/*  482 */         String recomond1CardNo = (String)obj.get("RECOMOND1_CARD_NO");
/*  483 */         String recomond1DeptId = (String)obj.get("RECOMOND1_DEPT_ID");
/*  484 */         String recomond2CardNo = (String)obj.get("RECOMOND2_CARD_NO");
/*  485 */         String recomond2DeptId = (String)obj.get("RECOMOND2_DEPT_ID");
/*  486 */         String nomineeName = (String)obj.get("NOMINEE_NAME");
/*  487 */         String nomineeRelation = (String)obj.get("NOMINEE_RELATION");
/*  488 */         String aadhar = (String)obj.get("AADHAR_CARD_NO");
/*  489 */         String receiptNo = (String)obj.get("RECEIPT_NO");
/*      */ 
/*      */         
/*  492 */         if (memberDob == null || "".equalsIgnoreCase(memberDob))
/*      */         {
/*  494 */           return "Please Select Date of Birth.";
/*      */         }
/*  496 */         if (memberAppliedDate == null || "".equalsIgnoreCase(memberAppliedDate))
/*      */         {
/*  498 */           return "Please Select Applied Date";
/*      */         }
/*  500 */         if (memberRegisteredDate == null || "".equalsIgnoreCase(memberRegisteredDate))
/*      */         {
/*  502 */           return "Please Select Registered Date.";
/*      */         }
/*  504 */         if (memberCardNo == null || "".equalsIgnoreCase(memberCardNo))
/*      */         {
/*  506 */           return "Please Enter Card No.";
/*      */         }
/*  508 */         if (memberDeptId == null || "".equalsIgnoreCase(memberDeptId) || 
/*  509 */           "SELECT".equalsIgnoreCase(memberDeptId))
/*      */         {
/*  511 */           return "Please select member department.";
/*      */         }
/*  513 */         if ("REGISTRATION".equalsIgnoreCase(type) && (
/*  514 */           receiptNo == null || "".equalsIgnoreCase(receiptNo)))
/*      */         {
/*  516 */           return "Please enter receipt no";
/*      */         }
/*      */         
/*  519 */         if (recomond1CardNo == null || "".equalsIgnoreCase(recomond1CardNo))
/*      */         {
/*  521 */           return "Please enter recommondation1 card no.";
/*      */         }
/*  523 */         if (recomond1DeptId == null || "".equalsIgnoreCase(recomond1DeptId) || 
/*  524 */           "SELECT".equalsIgnoreCase(recomond1DeptId))
/*      */         {
/*  526 */           return "Please select member recommondation1 department.";
/*      */         }
/*      */ 
/*      */         
/*  530 */         if (recomond2CardNo == null || "".equalsIgnoreCase(recomond2CardNo))
/*      */         {
/*  532 */           return "Please enter recommondation2 card no.";
/*      */         }
/*  534 */         if (recomond2DeptId == null || "".equalsIgnoreCase(recomond2DeptId) || 
/*  535 */           "SELECT".equalsIgnoreCase(recomond2DeptId))
/*      */         {
/*  537 */           return "Please select member recommondation2 department.";
/*      */         }
/*  539 */         if (nomineeName == null || "".equalsIgnoreCase(nomineeName))
/*  540 */           return "Please enter nominee Name."; 
/*  541 */         if (nomineeRelation == null || "".equalsIgnoreCase(nomineeRelation) || 
/*  542 */           "SELECT".equalsIgnoreCase(nomineeRelation))
/*      */         {
/*  544 */           return "Please select nominee relationship."; } 
/*  545 */         if (aadhar == null || "".equalsIgnoreCase(aadhar))
/*      */         {
/*  547 */           return "Please Addhar number";
/*      */         }
/*      */ 
/*      */         
/*  551 */         if (!this.utils.isValidDate(memberDob)) {
/*  552 */           return " Incorrect date format for Date of birth, and entered date  format should be (dd/mm/yyyy)";
/*      */         }
/*      */ 
/*      */         
/*  556 */         if (!this.utils.isValidDate(memberAppliedDate)) {
/*  557 */           return " Incorrect date format for applied date, and entered date format should be (dd/mm/yyyy)";
/*      */         }
/*      */         
/*  560 */         if (!this.utils.isValidDate(memberRegisteredDate)) {
/*  561 */           return " Incorrect date format for registered , and entered date format should be (dd/mm/yyyy)";
/*      */         }
/*      */ 
/*      */         
/*  565 */         if (type != null && !"".equalsIgnoreCase(type) && "REGISTRATION".equalsIgnoreCase(type)) {
/*  566 */           Registration isMemberExist = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(memberDeptId, 
/*  567 */               memberCardNo);
/*  568 */           if (isMemberExist != null) {
/*  569 */             return " Already member exist with  " + memberCardNo + " in " + memberDeptId + "Department";
/*      */           }
/*      */         } 
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
/*  589 */         if (this.miscellaneousDAO.isDuplicateReceiptNumberInSubscriptionPayments(receiptNo) || this.miscellaneousDAO.isDuplicateReceiptNumberInRegistration(receiptNo)) {
/*  590 */           return "Duplicate Receipt No, this Recept no is already used.";
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  599 */         if (type != null && !"".equalsIgnoreCase(type) && "UPDATE".equalsIgnoreCase(type)) {
/*      */           
/*  601 */           if (recomond2CardNo.equalsIgnoreCase(memberCardNo)) {
/*  602 */             return " Recommod2 card number should not be Member card number  ";
/*      */           }
/*      */           
/*  605 */           if (recomond1CardNo.equalsIgnoreCase(memberCardNo)) {
/*  606 */             return " Recommod1 card number should not be Member card number  ";
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  611 */         if (!this.utils.isNumericString(aadhar))
/*  612 */           return "Please enter only numbers in Aadhar"; 
/*  613 */         if (aadhar.length() != 12) {
/*  614 */           return "Please enter only 12 digit Aadhar number";
/*      */         }
/*  616 */         return "SUCCESS";
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  621 */     catch (Exception exception) {}
/*      */ 
/*      */     
/*  624 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String viewMemberDetails(HttpServletRequest request) {
/*  630 */     String result = "";
/*      */     try {
/*  632 */       String memberId = request.getParameter("memberId");
/*  633 */       result = getMemberDetails(memberId).toJSONString();
/*  634 */     } catch (Exception e) {
/*      */       
/*  636 */       e.printStackTrace();
/*      */     } 
/*  638 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject getMemberDetails(String memberId) {
/*  645 */     JSONObject Obj = new JSONObject();
/*  646 */     JSONObject finalResult = new JSONObject();
/*  647 */     SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
/*      */     
/*      */     try {
/*  650 */       ApplicationUtilities.debug(getClass(), "getMemberDetails ");
/*      */       
/*  654 */       Registration registerdMember = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);
/*  655 */       if (registerdMember != null) {
/*  656 */         RegistrationPK registerdMemberPK = registerdMember.getRegistrationPK();
/*      */         
/*  658 */         Obj.put("CARD_NO", Integer.valueOf(registerdMemberPK.getCardNo()));
/*  659 */         Obj.put("DEPT_ID", (registerdMemberPK.getDeptId() != null) ? registerdMemberPK.getDeptId() : "");
/*  660 */         Obj.put("MEMBER_ID", (registerdMemberPK.getMemberId() != null) ? registerdMemberPK.getMemberId() : "");
/*  661 */         Obj.put("AADHAR_CARD_NO", 
/*  662 */             (registerdMember.getAadharCardNo() != null) ? registerdMember.getAadharCardNo() : "");
/*  663 */         Obj.put("TRANSACTION_ID", 
/*  664 */             (registerdMemberPK.getTransactionId() != null) ? registerdMemberPK.getTransactionId() : "");
/*  665 */         Obj.put("UNIT_ID", (registerdMember.getUnitId() != null) ? registerdMember.getUnitId() : "");
/*  666 */         Obj.put("ALT_PHONE_NO", (registerdMember.getAltPhoneNo() != null) ? registerdMember.getAltPhoneNo() : "");
/*  667 */         Obj.put("APPLIED_DATAE", dateFormater.format(registerdMember.getRegisteredDate()));
/*  668 */         Obj.put("BLOOD_GROUP", (registerdMember.getBloodGroup() != null) ? registerdMember.getBloodGroup() : "");
/*  669 */         Obj.put("DEPT_NAME", (registerdMember.getDeptName() != null) ? registerdMember.getDeptName() : "");
/*  670 */         Obj.put("EDU_QUALIFICATION", 
/*  671 */             (registerdMember.getEduQualification() != null) ? registerdMember.getEduQualification() : "");
/*  672 */         Obj.put("FEFSI_MEMBER", 
/*  673 */             (registerdMember.getFefsiMember() != null) ? registerdMember.getFefsiMember() : "");
/*  674 */         Obj.put("FIRST_NAME", (registerdMember.getFirstName() != null) ? registerdMember.getFirstName() : "");
/*  675 */         Obj.put("KNOWN_LANGUAGES", 
/*  676 */             (registerdMember.getKnownLanguages() != null) ? registerdMember.getKnownLanguages() : "");
/*  677 */         Obj.put("LAST_NAME", (registerdMember.getLastName() != null) ? registerdMember.getLastName() : "");
/*  678 */         Obj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(registerdMember.getMembershipAmount()));
/*  679 */         Obj.put("MIDDLE_NAME", (registerdMember.getMiddleName() != null) ? registerdMember.getMiddleName() : "");
/*  680 */         Obj.put("MOTHER_TONGUE", 
/*  681 */             (registerdMember.getMotherTongue() != null) ? registerdMember.getMotherTongue() : "");
/*  682 */         Obj.put("NAME_OF_COMPANY", 
/*  683 */             (registerdMember.getNameOfCompany() != null) ? registerdMember.getNameOfCompany() : "");
/*  684 */         Obj.put("NATURE_OF_WORK", 
/*  685 */             (registerdMember.getNatureOfWork() != null) ? registerdMember.getNatureOfWork() : "");
/*  686 */         Obj.put("NOMINIE", (registerdMember.getNominie() != null) ? registerdMember.getNominie() : "");
/*  687 */         Obj.put("PAID_AMOUNT", Integer.valueOf(registerdMember.getPaidAmount()));
/*  688 */         Obj.put("PAYMENT_RECEIPT_NO", 
/*  689 */             (registerdMember.getReceiptNo() != null) ? registerdMember.getReceiptNo() : "");
/*  690 */         Obj.put("PERMINENT_ADDRESS", 
/*  691 */             (registerdMember.getPerminentAddress() != null) ? registerdMember.getPerminentAddress() : "");
/*  692 */         Obj.put("PHONE_NO", (registerdMember.getPhoneNo() != null) ? registerdMember.getPhoneNo() : "");
/*  693 */         Obj.put("PRESENT_ADDRESS", 
/*  694 */             (registerdMember.getPresentAddress() != null) ? registerdMember.getPresentAddress() : "");
/*  695 */         Obj.put("REGISTERED_DATE", dateFormater.format(registerdMember.getRegisteredDate()));
/*  696 */         Obj.put("RELATION_WITH_NOMINIE", (registerdMember.getRelationWithNominie() != null) ? 
/*  697 */             registerdMember.getRelationWithNominie() : "");
/*      */         
/*  699 */         Obj.put("FATHER_NAME", (registerdMember.getFatherName() != null) ? registerdMember.getFatherName() : "");
/*  700 */         Obj.put("DATE_OF_BIRTH", dateFormater.format(registerdMember.getDateOfBirth()));
/*  701 */         Obj.put("CURRENT_LOAN_STATUS", 
/*  702 */             (registerdMember.getCurrentLoanStatus() != null) ? registerdMember.getCurrentLoanStatus() : "");
/*  703 */         Obj.put("CURRENT_LOAN_ID", 
/*  704 */             (registerdMember.getCurrentLoanId() != null) ? registerdMember.getCurrentLoanId() : "");
/*  705 */         Obj.put("CURRENT_LOAN_BALANCE", 
/*  706 */             (registerdMember.getCurrentLoanBalance() != null) ? registerdMember.getCurrentLoanBalance() : "");
/*  707 */         Obj.put("CARD_BALANCE", 
/*  708 */             (registerdMember.getCardBalance() != null) ? registerdMember.getCardBalance() : "");
/*  709 */         Obj.put("BANK_ACC_HOLDER_NAME", 
/*  710 */             (registerdMember.getBankAccHolderName() != null) ? registerdMember.getBankAccHolderName() : "");
/*  711 */         Obj.put("BANK_ACC_NO", (registerdMember.getBankAccNo() != null) ? registerdMember.getBankAccNo() : "");
/*  712 */         Obj.put("BANK_BRANCH", (registerdMember.getBankBranch() != null) ? registerdMember.getBankBranch() : "");
/*  713 */         Obj.put("BANK_NAME", (registerdMember.getBankName() != null) ? registerdMember.getBankName() : "");
/*  714 */         Obj.put("BANK_IFSC_CODE", 
/*  715 */             (registerdMember.getBankIfscCode() != null) ? registerdMember.getBankIfscCode() : "");
/*  716 */         Obj.put("CARD_AMOUNT", (registerdMember.getCardAmount() != null) ? registerdMember.getCardAmount() : "");
/*  717 */         Obj.put("CURRENT_LOAN_ISSUED_AMOUNT", (registerdMember.getCurrentLoanIssuedAmount() != null) ? 
/*  718 */             registerdMember.getCurrentLoanIssuedAmount() : "");
/*  719 */         Obj.put("PAYMENT_CONF_ID", 
/*  720 */             (registerdMember.getPaymentConfId() != null) ? registerdMember.getPaymentConfId() : "");
/*  721 */         Obj.put("RECEIPT_NO", (registerdMember.getReceiptNo() != null) ? registerdMember.getReceiptNo() : "");
/*      */ 
/*      */         
/*  724 */         Obj.put("FILE_CONTENT", 
/*  725 */             this.utils.convertImageToBase64(registerdMember.getFileContent(), registerdMember.getFileType()));
/*  726 */         Obj.put("FILE_NAME", (registerdMember.getFileName() != null) ? registerdMember.getFileName() : "");
/*  727 */         Obj.put("FILE_TYPE", (registerdMember.getFileType() != null) ? registerdMember.getFileType() : "");
/*      */         
/*  729 */         RecommendationDetails recommondationDetails = this.miscellaneousDAO
/*  730 */           .getRecommondationDetailsByMemberId(memberId);
/*  731 */         if (recommondationDetails != null) {
/*      */           
/*  733 */           Obj.put("RECOMMEND1_NAME", (recommondationDetails.getRecommend1Name() != null) ? 
/*  734 */               recommondationDetails.getRecommend1Name() : "");
/*  735 */           Obj.put("RECOMMEND1_DEPT_NAME", (recommondationDetails.getRecommend1DeptName() != null) ? 
/*  736 */               recommondationDetails.getRecommend1DeptName() : "");
/*      */           
/*  738 */           Obj.put("RECOMMEND1_DEPT_ID", (recommondationDetails.getRecommend1DeptId() != null) ? 
/*  739 */               recommondationDetails.getRecommend1DeptId() : "");
/*  740 */           Obj.put("RECOMMEND1_WORKING_PLACE", (recommondationDetails.getRecommend1WorkingPlace() != null) ? 
/*  741 */               recommondationDetails.getRecommend1WorkingPlace() : "");
/*  742 */           Obj.put("RECOMMEND1_UNIT_ID", (recommondationDetails.getRecommend1UnitId() != null) ? 
/*  743 */               recommondationDetails.getRecommend1UnitId() : "");
/*      */           
/*  745 */           Obj.put("RECOMMEND1_CARD_NO", Integer.valueOf(recommondationDetails.getRecommend1CardNo()));
/*      */           
/*  747 */           Obj.put("RECOMMEND2_NAME", (recommondationDetails.getRecommend2Name() != null) ? 
/*  748 */               recommondationDetails.getRecommend2Name() : "");
/*      */           
/*  750 */           Obj.put("RECOMMEND2_DEPT_NAME", (recommondationDetails.getRecommend2DeptName() != null) ? 
/*  751 */               recommondationDetails.getRecommend2DeptName() : "");
/*  752 */           Obj.put("RECOMMEND2_DEPT_ID", (recommondationDetails.getRecommend2DeptId() != null) ? 
/*  753 */               recommondationDetails.getRecommend2DeptId() : "");
/*      */           
/*  755 */           Obj.put("RECOMMEND2_WORKING_PLACE", (recommondationDetails.getRecommend2WorkingPlace() != null) ? 
/*  756 */               recommondationDetails.getRecommend2WorkingPlace() : "");
/*  757 */           Obj.put("RECOMMEND2_UNIT_ID", (recommondationDetails.getRecommend2UnitId() != null) ? 
/*  758 */               recommondationDetails.getRecommend2UnitId() : "");
/*      */           
/*  760 */           Obj.put("RECOMMEND2_CARD_NO", Integer.valueOf(recommondationDetails.getRecommend2CardNo()));
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  767 */         Obj.put("BANK_ACC_HOLDER_NAME", registerdMember.getBankAccHolderName());
/*  768 */         Obj.put("BANK_ACC_NO", registerdMember.getBankAccNo());
/*  769 */         Obj.put("BANK_BRANCH", registerdMember.getBankBranch());
/*  770 */         Obj.put("", registerdMember.getBankIfscCode());
/*  771 */         finalResult.put("FINAL_RESULT_CODE", "400");
/*  772 */         finalResult.put("DATA_DETAILS", Obj);
/*      */       }
/*      */       else {
/*      */         
/*  776 */         finalResult.put("FINAL_RESULT_CODE", "300");
/*  777 */         finalResult.put("ERROR_MSG", "No member found with registered no :: " + memberId);
/*      */       }
/*      */     
/*  780 */     } catch (Exception e) {
/*      */       
/*  782 */       ApplicationUtilities.error(getClass(), e, "getMemberDetails");
/*  783 */       finalResult.put("FINAL_RESULT_CODE", "300");
/*  784 */       finalResult.put("ERROR_MSG", e.getMessage());
/*  785 */       e.printStackTrace();
/*      */     } 
/*      */     
/*  788 */     return finalResult;
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
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String updateMemberDetails(JSONObject items, MultipartFile[] file) {
/*  845 */     JSONObject finalResult = new JSONObject();
/*  846 */     JSONObject resultObj = new JSONObject();
/*  847 */     int insertedImages = 0;
/*  848 */     int insertedDefaultImages = 0;
/*      */     
/*      */     try {
/*  851 */       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
/*  852 */       String category = "REGISTRATION";
/*  853 */       String paymentStatus = "Yes";
/*  854 */       String remarks = "Registration";
/*  855 */       String firstName = ((String)items.get("memberName") != null) ? (String)items.get("memberName") : "";
/*  856 */       String fatherName = ((String)items.get("fatherName") != null) ? (String)items.get("fatherName") : "";
/*  857 */       String dateOfBirth = ((String)items.get("dateOfBirth") != null) ? (String)items.get("dateOfBirth") : "";
/*  858 */       String eduQualification = ((String)items.get("eduQualification") != null) ? 
/*  859 */         (String)items.get("eduQualification") : "";
/*  860 */       String motherTongue = ((String)items.get("motherTongue") != null) ? (String)items.get("motherTongue") : 
/*  861 */         "";
/*  862 */       String knownLanguages = ((String)items.get("knownLang") != null) ? (String)items.get("knownLang") : "";
/*  863 */       String bloodGroup = ((String)items.get("bloodGroup") != null) ? (String)items.get("bloodGroup") : "";
/*  864 */       String fefsiMember = ((String)items.get("workDetails_fefsiNumber") != null) ? 
/*  865 */         (String)items.get("workDetails_fefsiNumber") : "";
/*  866 */       String natureOfWork = ((String)items.get("workDetails_deptName") != null) ? 
/*  867 */         (String)items.get("workDetails_deptName") : "";
/*  868 */       String nameOfcompany = ((String)items.get("workDetails_placeOfWork") != null) ? 
/*  869 */         (String)items.get("workDetails_placeOfWork") : "";
/*      */       
/*  871 */       String appliedDatae = ((String)items.get("approvedDetails_appliedDate") != null) ? 
/*  872 */         (String)items.get("approvedDetails_appliedDate") : "";
/*  873 */       String unitId = ((String)items.get("approvedDetails_unitId") != null) ? 
/*  874 */         (String)items.get("approvedDetails_unitId") : "";
/*      */       
/*  876 */       String unitName = ((String)items.get("approvedDetails_placeOfWork") != null) ? 
/*  877 */         (String)items.get("approvedDetails_placeOfWork") : "";
/*  878 */       String presentAddress = ((String)items.get("presentAddress") != null) ? (String)items.get("presentAddress") : 
/*  879 */         "";
/*  880 */       String phoneNo1 = ((String)items.get("phoneNo1") != null) ? (String)items.get("phoneNo1") : "";
/*  881 */       String perminentAddress = ((String)items.get("perminentAddress") != null) ? 
/*  882 */         (String)items.get("perminentAddress") : "";
/*  883 */       String phoneNo2 = ((String)items.get("phoneNo2") != null) ? (String)items.get("phoneNo2") : "";
/*  884 */       String nominie = ((String)items.get("nomineeDetails_name") != null) ? 
/*  885 */         (String)items.get("nomineeDetails_name") : "";
/*  886 */       String relationWithNominie = ((String)items.get("nomineeDetails_relation") != null) ? 
/*  887 */         (String)items.get("nomineeDetails_relation") : "";
/*  888 */       String recommend1DeptName = ((String)items.get("recommand1_deptName") != null) ? 
/*  889 */         (String)items.get("recommand1_deptName") : "";
/*  890 */       String recommend1DeptId = ((String)items.get("recommand1_deptId") != null) ? 
/*  891 */         (String)items.get("recommand1_deptId") : "";
/*  892 */       String recommand1cardNo = ((String)items.get("recommand1_cardNo") != null) ? 
/*  893 */         (String)items.get("recommand1_cardNo") : "";
/*  894 */       String recommend1Name = ((String)items.get("recommand1_name") != null) ? 
/*  895 */         (String)items.get("recommand1_name") : "";
/*  896 */       String recommend1UnitId = ((String)items.get("recommand1_unitId") != null) ? 
/*  897 */         (String)items.get("recommand1_unitId") : "";
/*  898 */       String recommend1WorkingPlace = ((String)items.get("recommand1_placeOfWork") != null) ? 
/*  899 */         (String)items.get("recommand1_placeOfWork") : "";
/*  900 */       String recommend2DeptName = ((String)items.get("recommand2_deptName") != null) ? 
/*  901 */         (String)items.get("recommand2_deptName") : "";
/*  902 */       String approvedDetails_unitId = ((String)items.get("approvedDetails_unitId") != null) ? 
/*  903 */         (String)items.get("approvedDetails_unitId") : "";
/*  904 */       String approvedDetails_placeOfWork = ((String)items.get("approvedDetails_placeOfWork") != null) ? 
/*  905 */         (String)items.get("approvedDetails_placeOfWork") : "";
/*  906 */       String recommend2DeptId = ((String)items.get("recommand2_deptId") != null) ? 
/*  907 */         (String)items.get("recommand2_deptId") : "";
/*  908 */       String recommand2cardNo = ((String)items.get("recommand2_cardNo") != null) ? 
/*  909 */         (String)items.get("recommand2_cardNo") : "";
/*  910 */       String recommend2Name = ((String)items.get("recommand2_name") != null) ? 
/*  911 */         (String)items.get("recommand2_name") : "";
/*  912 */       String recommend2UnitId = ((String)items.get("recommand2_unitId") != null) ? 
/*  913 */         (String)items.get("recommand2_unitId") : "";
/*  914 */       String recommend2WorkingPlace = ((String)items.get("recommand2_placeOfWork") != null) ? 
/*  915 */         (String)items.get("recommand2_placeOfWork") : "";
/*  916 */       String aadhar = ((String)items.get("aadharCardNo") != null) ? (String)items.get("aadharCardNo") : "";
/*  917 */       String bankname = ((String)items.get("bankDetails_bankName") != null) ? 
/*  918 */         (String)items.get("bankDetails_bankName") : "";
/*  919 */       String bankAccHolderName = ((String)items.get("bankDetails_accHolderName") != null) ? 
/*  920 */         (String)items.get("bankDetails_accHolderName") : "";
/*  921 */       String bankAccNo = ((String)items.get("bankDetails_accNumber") != null) ? 
/*  922 */         (String)items.get("bankDetails_accNumber") : "";
/*  923 */       String bankBranch = ((String)items.get("bankDetails_branch") != null) ? 
/*  924 */         (String)items.get("bankDetails_branch") : "";
/*  925 */       String bankIfscCode = ((String)items.get("bankDetails_ifsc") != null) ? 
/*  926 */         (String)items.get("bankDetails_ifsc") : "";
/*  927 */       String appliedDate = ((String)items.get("approvedDetails_appliedDate") != null) ? 
/*  928 */         (String)items.get("approvedDetails_appliedDate") : "";
/*  929 */       String memberId = ((String)items.get("memberId") != null) ? (String)items.get("memberId") : "";
/*  930 */       String cardNo = ((String)items.get("approvedDetails_cardNo") != null) ? 
/*  931 */         (String)items.get("approvedDetails_cardNo") : "";
/*      */ 
/*      */       
/*  934 */       String deptId = ((String)items.get("deptId") != null) ? (String)items.get("deptId") : "";
/*  935 */       String receiptNo = ((String)items.get("RECEIPT_NO") != null) ? (String)items.get("RECEIPT_NO") : "";
/*      */ 
/*      */       
/*  938 */       JSONObject validateObj = new JSONObject();
/*  939 */       validateObj.put("MEMBER_DOB", dateOfBirth);
/*  940 */       validateObj.put("MEMBER_APPLIED_DATE", appliedDatae);
/*  941 */       validateObj.put("MEMBER_REGISTERED_DATE", appliedDatae);
/*  942 */       validateObj.put("MEMBER_DEPT_ID", deptId);
/*  943 */       validateObj.put("MEMBER_CARD_NO", cardNo);
/*  944 */       validateObj.put("RECOMOND1_CARD_NO", recommand1cardNo);
/*  945 */       validateObj.put("RECOMOND1_DEPT_ID", recommend1DeptId);
/*  946 */       validateObj.put("RECOMOND2_CARD_NO", recommand2cardNo);
/*  947 */       validateObj.put("RECOMOND2_DEPT_ID", recommend2DeptId);
/*  948 */       validateObj.put("NOMINEE_NAME", nominie);
/*  949 */       validateObj.put("NOMINEE_RELATION", relationWithNominie);
/*  950 */       validateObj.put("AADHAR_CARD_NO", aadhar);
/*  951 */       validateObj.put("RECEIPT_NO", receiptNo);
/*      */ 
/*      */       
/*  954 */       String validationResult = registrationValidation(validateObj, "UPDATE");
/*  955 */       if (!"".equals(validationResult) && ("SUCCESS".equalsIgnoreCase(validationResult) || "Duplicate Receipt No, this Recept no is already used.".equalsIgnoreCase(validationResult))) {
/*      */         
/*  957 */         Registration registration = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);
/*      */         
/*  959 */         registration.setUnitId(unitId);
/*  960 */         registration.setAadharCardNo(aadhar);
/*  961 */         registration.setFatherName(fatherName);
/*  962 */         registration.setFirstName(firstName);
/*  963 */         registration.setDateOfBirth(formatter.parse(dateOfBirth));
/*      */         
/*  965 */         registration.setAppliedDatae(formatter.parse(appliedDatae));
/*      */         
/*  967 */         registration.setBloodGroup(bloodGroup);
/*  968 */         registration.setEduQualification(eduQualification);
/*  969 */         registration.setFefsiMember(fefsiMember);
/*  970 */         registration.setKnownLanguages(knownLanguages);
/*  971 */         registration.setMotherTongue(motherTongue);
/*  972 */         registration.setNameOfCompany(nameOfcompany);
/*  973 */         registration.setNatureOfWork(natureOfWork);
/*  974 */         registration.setNominie(nominie);
/*  975 */         registration.setPerminentAddress(perminentAddress);
/*  976 */         registration.setPresentAddress(presentAddress);
/*  977 */         registration.setRelationWithNominie(relationWithNominie);
/*  978 */         registration.setMotherTongue(motherTongue);
/*  979 */         registration.setBankName(bankname);
/*      */         
/*  981 */         RecommendationDetails reccomendDetails = new RecommendationDetails();
/*  982 */         reccomendDetails.setMemberId(memberId);
/*  983 */         reccomendDetails.setRecommend1DeptId(recommend1DeptId);
/*  984 */         reccomendDetails.setRecommend1DeptName(recommend1DeptName);
/*  985 */         reccomendDetails.setRecommend1Name(recommend1Name);
/*  986 */         reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
/*  987 */         reccomendDetails.setRecommend1UnitId(recommend1UnitId);
/*  988 */         reccomendDetails.setRecommend1CardNo(Integer.parseInt(recommand1cardNo));
/*      */         
/*  990 */         reccomendDetails.setRecommend2DeptId(recommend2DeptId);
/*  991 */         reccomendDetails.setRecommend2DeptName(recommend2DeptName);
/*  992 */         reccomendDetails.setRecommend2Name(recommend2Name);
/*  993 */         reccomendDetails.setRecommend2WorkingPlace(recommend2WorkingPlace);
/*  994 */         reccomendDetails.setRecommend2UnitId(recommend2UnitId);
/*  995 */         reccomendDetails.setRecommend2CardNo(Integer.parseInt(recommand2cardNo));
/*      */         
/*  997 */         reccomendDetails.setMemberId(memberId);
/*  998 */         this.dataAccess.update(reccomendDetails);
/*      */         
/* 1000 */         if (file != null && file.length > 0 && 
/* 1001 */           !file[0].isEmpty()) {
/* 1002 */           byte[] bytes = file[0].getBytes();
/* 1003 */           String filetype = file[0].getOriginalFilename().substring(
/* 1004 */               file[0].getOriginalFilename().lastIndexOf(".") + 1, 
/* 1005 */               file[0].getOriginalFilename().length());
/*      */           
/* 1007 */           registration.setFileContent(bytes);
/* 1008 */           registration.setFileName(String.valueOf(String.valueOf(memberId)) + "_PROFILE_PIC");
/* 1009 */           registration.setFileType(filetype.toUpperCase());
/*      */         } 
/*      */         
/* 1012 */         registration.setBankAccHolderName(bankAccHolderName);
/* 1013 */         registration.setBankAccNo(bankAccNo);
/* 1014 */         registration.setBankBranch(bankBranch);
/* 1015 */         registration.setWorkingPlace(approvedDetails_placeOfWork);
/* 1016 */         registration.setBankIfscCode(bankIfscCode);
/*      */ 
/*      */         
/* 1019 */         registration.setAltPhoneNo(phoneNo2);
/* 1020 */         registration.setPhoneNo(phoneNo1);
/* 1021 */         registration.setRegisteredDate(formatter.parse(appliedDatae));
/* 1022 */         this.dataAccess.update(registration);
/*      */         
/* 1024 */         resultObj.put("REGISTRATION_RESULT", "Udated Registered Member Details Successfully !");
/* 1025 */         resultObj.put("MEBER_ID", memberId);
/* 1026 */         resultObj.put("MEBER_DEPT_ID", deptId);
/* 1027 */         resultObj.put("MEBER_CARD_NO", cardNo);
/* 1028 */         finalResult.put("FINAL_RESULT_CODE", "400");
/* 1029 */         finalResult.put("DATA_DETAILS", resultObj);
/*      */       } else {
/* 1031 */         finalResult.put("FINAL_RESULT_CODE", "300");
/* 1032 */         finalResult.put("ERROR_MSG", validationResult);
/*      */       } 
/*      */       
/*      */ 
/*      */     
/*      */     }
/* 1039 */     catch (Exception e) {
/*      */       
/* 1041 */       finalResult.put("FINAL_RESULT_CODE", "300");
/* 1042 */       finalResult.put("ERROR_MSG", e.getMessage());
/*      */       
/* 1044 */       ApplicationUtilities.error(getClass(), e, "updateMemberDetails");
/*      */     } 
/*      */     
/* 1047 */     return finalResult.toJSONString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
/* 1054 */     return "payMembershipAmount";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
/* 1060 */     return "getCardBalance";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
/* 1066 */     return "getCardBalance";
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
/*      */   public String payCardBalance(HttpServletRequest request) {
/* 1139 */     JSONObject result = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/* 1143 */       String cardNo = request.getParameter("cardNo");
/* 1144 */       String deptId = request.getParameter("deptId");
/* 1145 */       String memberId = request.getParameter("memberId");
/* 1146 */       int cardBalance = this.miscellaneousDAO.caluclateCardBalance(memberId);
/* 1147 */       if (cardBalance > 0)
/*      */       {
/* 1149 */         String membershipAmount = request.getParameter("membershipAmount");
/* 1150 */         String paymentConfId = request.getParameter("paymentConfId");
/* 1151 */         String paidAmount = request.getParameter("paidAmount");
/* 1152 */         String balanceAmount = request.getParameter("balanceAmount");
/* 1153 */         String payingAmount = request.getParameter("payingAmount");
/* 1154 */         String receiptNo = request.getParameter("receiptNo");
/* 1155 */         String pageId = request.getParameter("pageId");
/* 1156 */         String paidDate = request.getParameter("paidDate");
/* 1157 */         String remarks = request.getParameter("remarks");
/* 1158 */         String paymentMode = request.getParameter("paymentMode");
/* 1159 */         String ddNo = request.getParameter("ddNo");
/*      */         
/* 1161 */         JSONObject validateJsnObj = new JSONObject();
/* 1162 */         validateJsnObj.put("MEMBER_ID", memberId);
/* 1163 */         validateJsnObj.put("CARD_NO", cardNo);
/* 1164 */         validateJsnObj.put("DEPT_ID", deptId);
/* 1165 */         validateJsnObj.put("PAYMENT_CONF_ID", paymentConfId);
/* 1166 */         validateJsnObj.put("MEMBERSHIP_AMOUNT", membershipAmount);
/* 1167 */         validateJsnObj.put("PAID_AMOUNT", paidAmount);
/* 1168 */         validateJsnObj.put("PAYIING_AMOUNT", payingAmount);
/* 1169 */         validateJsnObj.put("PAID_DATE", paidDate);
/* 1170 */         validateJsnObj.put("RECEIPT_NO", receiptNo);
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
/* 1184 */         String validationResult = membershipPaymentsValidation(validateJsnObj);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1189 */         MembershipPayments membershipPayments = new MembershipPayments();
/* 1190 */         membershipPayments.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/* 1191 */         membershipPayments.setPaidAmount(Integer.parseInt(payingAmount));
/* 1192 */         membershipPayments.setPaymentConfId(paymentConfId);
/* 1193 */         membershipPayments.setRemarks(remarks);
/* 1194 */         membershipPayments.setReceiptNo(receiptNo);
/* 1195 */         membershipPayments.setCategory("INSTALMENT");
/* 1196 */         membershipPayments.setPaymentType(paymentMode);
/* 1197 */         membershipPayments.setDdNo(ddNo);
/* 1198 */         MembershipPaymentsPK membershipPaymentsPK = new MembershipPaymentsPK();
/* 1199 */         membershipPaymentsPK.setTransactionId(this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID"));
/* 1200 */         membershipPaymentsPK.setMemberId(memberId);
/* 1201 */         membershipPayments.setMembershipPaymentsPK(membershipPaymentsPK);
/* 1202 */         this.dataAccess.save(membershipPayments);
/* 1203 */         this.miscellaneousDAO.updateCardBalance(memberId);
/*      */         
/* 1205 */         result.put("FINAL_RESULT_CODE", "400");
/* 1206 */         result.put("DATA_DETAILS", "Membership amount paid sucessfullty!");
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/* 1215 */         result.put("FINAL_RESULT_CODE", "300");
/* 1216 */         result.put("ERROR_MSG", "This member paid total membership amount !");
/*      */       }
/*      */     
/*      */     }
/* 1220 */     catch (NumberFormatException nfe) {
/* 1221 */       ApplicationUtilities.error(getClass(), nfe, "payCardBalance");
/* 1222 */       result.put("FINAL_RESULT_CODE", "300");
/* 1223 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount/SubscriptionAmount/SubscriptionYear");
/*      */     }
/* 1225 */     catch (Exception e) {
/* 1226 */       ApplicationUtilities.error(getClass(), e, "payCardBalance");
/* 1227 */       result.put("FINAL_RESULT_CODE", "300");
/* 1228 */       result.put("ERROR_MSG", "Unable to Subscibe due to " + e.getMessage());
/*      */     } 
/* 1230 */     return result.toJSONString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String membershipPaymentsValidation(JSONObject obj) {
/* 1236 */     String result = "Sorry Registration Failed !";
/*      */ 
/*      */     
/*      */     try {
/* 1240 */       if (obj != null)
/*      */       {
/* 1242 */         String cardNo = (String)obj.get("CARD_NO");
/* 1243 */         String deptId = (String)obj.get("DEPT_ID");
/* 1244 */         String memberId = (String)obj.get("MEMBER_ID");
/* 1245 */         String paymentConfId = (String)obj.get("PAYMENT_CONF_ID");
/* 1246 */         String subscriptionAmount = (String)obj.get("SUBSCRIPTION_AMOUNT");
/* 1247 */         String subscriptionYear = (String)obj.get("SUBSCRIBING_YEAR");
/* 1248 */         String paidAmount = (String)obj.get("PAID_AMOUNT");
/* 1249 */         String paidDate = (String)obj.get("PAID_DATE");
/* 1250 */         String receiptNo = (String)obj.get("RECEIPT_NO");
/* 1251 */         String paymentStatus = (String)obj.get("PAYMENT_STATUS");
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
/* 1264 */         if (subscriptionYear == null || "".equalsIgnoreCase(subscriptionYear))
/*      */         {
/* 1266 */           return "Please Select Subscription Year.";
/*      */         }
/*      */         
/* 1269 */         if (paymentStatus == null || "".equalsIgnoreCase(paymentStatus) || 
/* 1270 */           "No".equalsIgnoreCase(paymentStatus))
/*      */         {
/* 1272 */           return "Please Select Subscription Payment Status.";
/*      */         }
/* 1274 */         if (receiptNo == null || "".equalsIgnoreCase(receiptNo))
/*      */         {
/* 1276 */           return "Please Enter ReceiptNo ";
/*      */         }
/* 1278 */         if (paidDate == null || "".equalsIgnoreCase(paidDate))
/*      */         {
/* 1280 */           return "Please Select Paid Date Date.";
/*      */         }
/* 1282 */         if (cardNo == null || "".equalsIgnoreCase(cardNo))
/*      */         {
/* 1284 */           return "Please Select Card No.";
/*      */         }
/* 1286 */         if (deptId == null || "".equalsIgnoreCase(deptId) || "SELECT".equalsIgnoreCase(deptId))
/*      */         {
/* 1288 */           return "Please select member department.";
/*      */         }
/* 1290 */         if (subscriptionAmount == null || "".equalsIgnoreCase(subscriptionAmount))
/*      */         {
/* 1292 */           return "Please Enter Subscription Amount."; } 
/* 1293 */         if (paymentConfId == null || "".equalsIgnoreCase(paymentConfId) || 
/* 1294 */           "SELECT".equalsIgnoreCase(paymentConfId))
/*      */         {
/* 1296 */           return "Please select payment type.";
/*      */         }
/*      */         
/* 1299 */         if (!this.utils.isValidDate(paidDate)) {
/* 1300 */           return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
/*      */         }
/*      */ 
/*      */         
/* 1304 */         if (!this.utils.isNumericString(subscriptionAmount)) {
/* 1305 */           System.out.println(String.valueOf(String.valueOf(subscriptionAmount)) + "   isNumericString  " + 
/* 1306 */               this.utils.isNumericString(subscriptionAmount));
/* 1307 */           return "Please enter only numbers in Subscription Amount";
/*      */         } 
/*      */         
/* 1310 */         if (!this.utils.isNumericString(paidAmount)) {
/* 1311 */           System.out.println(String.valueOf(String.valueOf(paidAmount)) + "   isNumericString  " + this.utils.isNumericString(paidAmount));
/* 1312 */           return "Please enter only numbers in PaidAmount";
/*      */         } 
/*      */         
/* 1315 */         return "SUCCESS";
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/* 1321 */     catch (Exception e) {
/*      */       
/* 1323 */       ApplicationUtilities.error(getClass(), e, "membershipPaymentsValidation");
/*      */     } 
/* 1325 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject updateCardBalanceFormDetails(HttpServletRequest request) {
/* 1331 */     JSONObject resultObj = new JSONObject();
/*      */     
/*      */     try {
/* 1334 */       String cardNo = request.getParameter("update_cardBalance_cardNo");
/*      */       
/* 1336 */       int cardNumber = Integer.parseInt(cardNo);
/* 1337 */       String deptId = request.getParameter("update_cardBalance_deptId");
/*      */       
/* 1339 */       String pageId = request.getParameter("update_cardBalance_pageId");
/*      */       
/* 1341 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/* 1342 */     } catch (Exception e) {
/*      */       
/* 1344 */       ApplicationUtilities.error(getClass(), e, "updateCardBalanceFormDetails");
/*      */     } 
/*      */     
/* 1347 */     return resultObj;
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public JSONObject deleteCardBalanceFormDetails(HttpServletRequest request) {
/* 1353 */     JSONObject resultObj = new JSONObject();
/*      */     try {
/* 1355 */       String cardNo = request.getParameter("delete_cardBalance_cardNo");
/*      */       
/* 1357 */       int cardNumber = Integer.parseInt(cardNo);
/* 1358 */       String deptId = request.getParameter("delete_cardBalance_deptId");
/*      */       
/* 1360 */       String pageId = request.getParameter("delete_cardBalance_pageId");
/*      */ 
/*      */       
/* 1363 */       resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
/* 1364 */     } catch (Exception e) {
/*      */       
/* 1366 */       ApplicationUtilities.error(getClass(), e, "deleteCardBalanceFormDetails");
/*      */     } 
/*      */     
/* 1369 */     return resultObj;
/*      */   }
/*      */   
/*      */   @Transactional
/*      */   public String updateMembershipPayments(HttpServletRequest request) {
/* 1374 */     JSONObject result = new JSONObject();
/*      */     
/*      */     try {
/* 1377 */       String membershipAmount = request.getParameter("membershipAmount");
/* 1378 */       String paidAmount = request.getParameter("paidAmount");
/* 1379 */       String paymentConfId = request.getParameter("paymentConfId");
/* 1380 */       String paidDate = request.getParameter("paidDate");
/* 1381 */       String receiptNo = request.getParameter("receiptNo");
/* 1382 */       String remarks = request.getParameter("remarks");
/* 1383 */       String memberId = request.getParameter("memberId");
/* 1384 */       String transactionId = request.getParameter("transactionId");
/* 1385 */       String deptId = request.getParameter("deptId");
/* 1386 */       String cardNo = request.getParameter("cardNo");
/*      */ 
/*      */ 
/*      */       
/* 1390 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId))
/*      */       {
/* 1392 */         String updateQuery = "update MembershipPayments set  paidDate=:paidDate , receiptNo=:receiptNo ,  paidAmount=:paidAmount ,  paymentConfId=:paymentConfId ,  remarks=:remarks   where membershipPaymentsPK.transactionId=:transactionId and membershipPaymentsPK.memberId=:memberId ";
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1397 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/*      */         
/* 1399 */         parametersMap.put("memberId", memberId);
/* 1400 */         parametersMap.put("transactionId", transactionId);
/* 1401 */         parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
/* 1402 */         parametersMap.put("receiptNo", receiptNo);
/* 1403 */         parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
/*      */         
/* 1405 */         parametersMap.put("paymentConfId", paymentConfId);
/* 1406 */         parametersMap.put("remarks", remarks);
/*      */         
/* 1408 */         int updatedRecords = this.dataAccess.updateQueryByCount(updateQuery, parametersMap);
/*      */         
/* 1410 */         result.put("FINAL_RESULT_CODE", "400");
/* 1411 */         result.put("DATA_DETAILS", "Upadated  membership payment  sucessfullty!");
/* 1412 */         this.miscellaneousDAO.updateCardBalance(memberId);
/*      */       }
/*      */       else
/*      */       {
/* 1416 */         result.put("FINAL_RESULT_CODE", "200");
/* 1417 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Membership Paments!");
/*      */       }
/*      */     
/*      */     }
/* 1421 */     catch (NumberFormatException nfe) {
/* 1422 */       ApplicationUtilities.error(getClass(), nfe, "updateMembershipPayments");
/*      */       
/* 1424 */       result.put("FINAL_RESULT_CODE", "300");
/* 1425 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/* 1427 */     catch (Exception e) {
/* 1428 */       ApplicationUtilities.error(getClass(), e, "updateMembershipPayments");
/* 1429 */       result.put("FINAL_RESULT_CODE", "300");
/* 1430 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/* 1433 */     return result.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   @Transactional
/*      */   public String deleteMembershipPayments(HttpServletRequest request) {
/* 1439 */     JSONObject result = new JSONObject();
/*      */ 
/*      */     
/*      */     try {
/* 1443 */       String membershipAmount = request.getParameter("membershipAmount");
/* 1444 */       String paidAmount = request.getParameter("paidAmount");
/* 1445 */       String paymentConfId = request.getParameter("paymentConfId");
/* 1446 */       String paidDate = request.getParameter("paidDate");
/* 1447 */       String receiptNo = request.getParameter("receiptNo");
/* 1448 */       String remarks = request.getParameter("remarks");
/* 1449 */       String memberId = request.getParameter("memberId");
/* 1450 */       String transactionId = request.getParameter("transactionId");
/* 1451 */       String deptId = request.getParameter("deptId");
/* 1452 */       String cardNo = request.getParameter("cardNo");
/*      */       
/* 1454 */       if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {
/* 1455 */         String updateQuery = "delete from  MembershipPayments  where membershipPaymentsPK.transactionId=:transactionId and membershipPaymentsPK.memberId=:memberId ";
/*      */         
/* 1457 */         Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 1458 */         parametersMap.put("memberId", memberId);
/* 1459 */         parametersMap.put("transactionId", transactionId);
/* 1460 */         int updatedRecords = this.dataAccess.updateQueryByCount(updateQuery, parametersMap);
/* 1461 */         result.put("FINAL_RESULT_CODE", "400");
/* 1462 */         result.put("DATA_DETAILS", "Upadated  membership payment  sucessfullty!");
/* 1463 */         this.miscellaneousDAO.updateCardBalance(memberId);
/*      */       }
/*      */       else {
/*      */         
/* 1467 */         result.put("FINAL_RESULT_CODE", "200");
/* 1468 */         result.put("ERROR_MSG", "Wrong memberid and transaction id for updating Membership Paments!");
/*      */       }
/*      */     
/*      */     }
/* 1472 */     catch (NumberFormatException nfe) {
/* 1473 */       ApplicationUtilities.error(getClass(), nfe, "deleteMembershipPayments");
/*      */       
/* 1475 */       result.put("FINAL_RESULT_CODE", "300");
/* 1476 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     }
/* 1478 */     catch (Exception e) {
/*      */       
/* 1480 */       ApplicationUtilities.error(getClass(), e, "deleteMembershipPayments");
/* 1481 */       result.put("FINAL_RESULT_CODE", "300");
/* 1482 */       result.put("ERROR_MSG", "Please provide valid input for PaidAmount");
/*      */     } 
/*      */     
/* 1485 */     return result.toString();
/*      */   }
/*      */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\TeluguCineAndTVOutDoorUnitTechniciansUnionDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */