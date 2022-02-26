/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.EmbeddedId;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
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
/*     */ @Entity
/*     */ @Table(name = "registration")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"), @NamedQuery(name = "Registration.findByCardNo", query = "SELECT r FROM Registration r WHERE r.registrationPK.cardNo = :cardNo"), @NamedQuery(name = "Registration.findByDeptId", query = "SELECT r FROM Registration r WHERE r.registrationPK.deptId = :deptId"), @NamedQuery(name = "Registration.findByMemberId", query = "SELECT r FROM Registration r WHERE r.registrationPK.memberId = :memberId"), @NamedQuery(name = "Registration.findByAltPhoneNo", query = "SELECT r FROM Registration r WHERE r.altPhoneNo = :altPhoneNo"), @NamedQuery(name = "Registration.findByAppliedDatae", query = "SELECT r FROM Registration r WHERE r.appliedDatae = :appliedDatae"), @NamedQuery(name = "Registration.findByBloodGroup", query = "SELECT r FROM Registration r WHERE r.bloodGroup = :bloodGroup"), @NamedQuery(name = "Registration.findByDeptName", query = "SELECT r FROM Registration r WHERE r.deptName = :deptName"), @NamedQuery(name = "Registration.findByEduQualification", query = "SELECT r FROM Registration r WHERE r.eduQualification = :eduQualification"), @NamedQuery(name = "Registration.findByFefsiMember", query = "SELECT r FROM Registration r WHERE r.fefsiMember = :fefsiMember"), @NamedQuery(name = "Registration.findByFirstName", query = "SELECT r FROM Registration r WHERE r.firstName = :firstName"), @NamedQuery(name = "Registration.findByKnownLanguages", query = "SELECT r FROM Registration r WHERE r.knownLanguages = :knownLanguages"), @NamedQuery(name = "Registration.findByLastName", query = "SELECT r FROM Registration r WHERE r.lastName = :lastName"), @NamedQuery(name = "Registration.findByMembershipAmount", query = "SELECT r FROM Registration r WHERE r.membershipAmount = :membershipAmount"), @NamedQuery(name = "Registration.findByMiddleName", query = "SELECT r FROM Registration r WHERE r.middleName = :middleName"), @NamedQuery(name = "Registration.findByMotherTongue", query = "SELECT r FROM Registration r WHERE r.motherTongue = :motherTongue"), @NamedQuery(name = "Registration.findByNameOfCompany", query = "SELECT r FROM Registration r WHERE r.nameOfCompany = :nameOfCompany"), @NamedQuery(name = "Registration.findByNatureOfWork", query = "SELECT r FROM Registration r WHERE r.natureOfWork = :natureOfWork"), @NamedQuery(name = "Registration.findByNominie", query = "SELECT r FROM Registration r WHERE r.nominie = :nominie"), @NamedQuery(name = "Registration.findByPaidAmount", query = "SELECT r FROM Registration r WHERE r.paidAmount = :paidAmount"), @NamedQuery(name = "Registration.findByPerminentAddress", query = "SELECT r FROM Registration r WHERE r.perminentAddress = :perminentAddress"), @NamedQuery(name = "Registration.findByPhoneNo", query = "SELECT r FROM Registration r WHERE r.phoneNo = :phoneNo"), @NamedQuery(name = "Registration.findByPresentAddress", query = "SELECT r FROM Registration r WHERE r.presentAddress = :presentAddress"), @NamedQuery(name = "Registration.findByRegisteredDate", query = "SELECT r FROM Registration r WHERE r.registeredDate = :registeredDate"), @NamedQuery(name = "Registration.findByRelationWithNominie", query = "SELECT r FROM Registration r WHERE r.relationWithNominie = :relationWithNominie"), @NamedQuery(name = "Registration.findBySNo", query = "SELECT r FROM Registration r WHERE r.sNo = :sNo"), @NamedQuery(name = "Registration.findByFatherName", query = "SELECT r FROM Registration r WHERE r.fatherName = :fatherName"), @NamedQuery(name = "Registration.findByDateOfBirth", query = "SELECT r FROM Registration r WHERE r.dateOfBirth = :dateOfBirth"), @NamedQuery(name = "Registration.findByUnitId", query = "SELECT r FROM Registration r WHERE r.unitId = :unitId"), @NamedQuery(name = "Registration.findByCurrentLoanStatus", query = "SELECT r FROM Registration r WHERE r.currentLoanStatus = :currentLoanStatus"), @NamedQuery(name = "Registration.findByCurrentLoanId", query = "SELECT r FROM Registration r WHERE r.currentLoanId = :currentLoanId"), @NamedQuery(name = "Registration.findByCurrentLoanBalance", query = "SELECT r FROM Registration r WHERE r.currentLoanBalance = :currentLoanBalance"), @NamedQuery(name = "Registration.findByCardBalance", query = "SELECT r FROM Registration r WHERE r.cardBalance = :cardBalance"), @NamedQuery(name = "Registration.findByBankAccHolderName", query = "SELECT r FROM Registration r WHERE r.bankAccHolderName = :bankAccHolderName"), @NamedQuery(name = "Registration.findByBankAccNo", query = "SELECT r FROM Registration r WHERE r.bankAccNo = :bankAccNo"), @NamedQuery(name = "Registration.findByBankBranch", query = "SELECT r FROM Registration r WHERE r.bankBranch = :bankBranch"), @NamedQuery(name = "Registration.findByBankIfscCode", query = "SELECT r FROM Registration r WHERE r.bankIfscCode = :bankIfscCode"), @NamedQuery(name = "Registration.findByCardAmount", query = "SELECT r FROM Registration r WHERE r.cardAmount = :cardAmount"), @NamedQuery(name = "Registration.findByCurrentLoanIssuedAmount", query = "SELECT r FROM Registration r WHERE r.currentLoanIssuedAmount = :currentLoanIssuedAmount"), @NamedQuery(name = "Registration.findByTransactionId", query = "SELECT r FROM Registration r WHERE r.registrationPK.transactionId = :transactionId"), @NamedQuery(name = "Registration.findByPaymentConfId", query = "SELECT r FROM Registration r WHERE r.paymentConfId = :paymentConfId"), @NamedQuery(name = "Registration.findByAadharCardNo", query = "SELECT r FROM Registration r WHERE r.aadharCardNo = :aadharCardNo"), @NamedQuery(name = "Registration.findByBankName", query = "SELECT r FROM Registration r WHERE r.bankName = :bankName"), @NamedQuery(name = "Registration.findByReceiptNo", query = "SELECT r FROM Registration r WHERE r.receiptNo = :receiptNo"), @NamedQuery(name = "Registration.findByWorkingPlace", query = "SELECT r FROM Registration r WHERE r.workingPlace = :workingPlace"), @NamedQuery(name = "Registration.findByIsActive", query = "SELECT r FROM Registration r WHERE r.isActive = :isActive"), @NamedQuery(name = "Registration.findByFileName", query = "SELECT r FROM Registration r WHERE r.fileName = :fileName"), @NamedQuery(name = "Registration.findByFileType", query = "SELECT r FROM Registration r WHERE r.fileType = :fileType"), @NamedQuery(name = "Registration.findByStatus", query = "SELECT r FROM Registration r WHERE r.status = :status"), @NamedQuery(name = "Registration.findByRegTimeStamp", query = "SELECT r FROM Registration r WHERE r.regTimeStamp = :regTimeStamp")})
/*     */ public class Registration
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected RegistrationPK registrationPK;
/*     */   @Column(name = "ALT_PHONE_NO")
/*     */   private String altPhoneNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "APPLIED_DATAE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date appliedDatae;
/*     */   @Column(name = "BLOOD_GROUP")
/*     */   private String bloodGroup;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DEPT_NAME")
/*     */   private String deptName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "EDU_QUALIFICATION")
/*     */   private String eduQualification;
/*     */   @Column(name = "FEFSI_MEMBER")
/*     */   private String fefsiMember;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "FIRST_NAME")
/*     */   private String firstName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "KNOWN_LANGUAGES")
/*     */   private String knownLanguages;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "LAST_NAME")
/*     */   private String lastName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBERSHIP_AMOUNT")
/*     */   private int membershipAmount;
/*     */   @Column(name = "MIDDLE_NAME")
/*     */   private String middleName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MOTHER_TONGUE")
/*     */   private String motherTongue;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "NAME_OF_COMPANY")
/*     */   private String nameOfCompany;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "NATURE_OF_WORK")
/*     */   private String natureOfWork;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "NOMINIE")
/*     */   private String nominie;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAID_AMOUNT")
/*     */   private int paidAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PERMINENT_ADDRESS")
/*     */   private String perminentAddress;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PHONE_NO")
/*     */   private String phoneNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PRESENT_ADDRESS")
/*     */   private String presentAddress;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "REGISTERED_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date registeredDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RELATION_WITH_NOMINIE")
/*     */   private String relationWithNominie;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "FATHER_NAME")
/*     */   private String fatherName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DATE_OF_BIRTH")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateOfBirth;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "UNIT_ID")
/*     */   private String unitId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CURRENT_LOAN_STATUS")
/*     */   private String currentLoanStatus;
/*     */   @Column(name = "CURRENT_LOAN_ID")
/*     */   private String currentLoanId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CURRENT_LOAN_BALANCE")
/*     */   private String currentLoanBalance;
/*     */   @Column(name = "CARD_BALANCE")
/*     */   private String cardBalance;
/*     */   @Column(name = "BANK_ACC_HOLDER_NAME")
/*     */   private String bankAccHolderName;
/*     */   @Column(name = "BANK_ACC_NO")
/*     */   private String bankAccNo;
/*     */   @Column(name = "BANK_BRANCH")
/*     */   private String bankBranch;
/*     */   @Column(name = "BANK_IFSC_CODE")
/*     */   private String bankIfscCode;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CARD_AMOUNT")
/*     */   private String cardAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CURRENT_LOAN_ISSUED_AMOUNT")
/*     */   private String currentLoanIssuedAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_CONF_ID")
/*     */   private String paymentConfId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "AADHAR_CARD_NO")
/*     */   private String aadharCardNo;
/*     */   @Column(name = "BANK_NAME")
/*     */   private String bankName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECEIPT_NO")
/*     */   private String receiptNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "WORKING_PLACE")
/*     */   private String workingPlace;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "IS_ACTIVE")
/*     */   private String isActive;
/*     */   @Column(name = "FILE_NAME")
/*     */   private String fileName;
/*     */   @Column(name = "FILE_TYPE")
/*     */   private String fileType;
/*     */   @Column(name = "STATUS")
/*     */   private String status;
/*     */   @Lob
/*     */   @Column(name = "FILE_CONTENT")
/*     */   private byte[] fileContent;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "REG_TIME_STAMP")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date regTimeStamp;
/*     */   
/*     */   public Registration() {}
/*     */   
/*     */   public Registration(RegistrationPK registrationPK) {
/* 218 */     this.registrationPK = registrationPK;
/*     */   }
/*     */   
/*     */   public Registration(RegistrationPK registrationPK, Date appliedDatae, String deptName, String eduQualification, String firstName, String knownLanguages, String lastName, int membershipAmount, String motherTongue, String nameOfCompany, String natureOfWork, String nominie, int paidAmount, String perminentAddress, String phoneNo, String presentAddress, Date registeredDate, String relationWithNominie, int sNo, String fatherName, Date dateOfBirth, String unitId, String currentLoanStatus, String currentLoanBalance, String cardAmount, String currentLoanIssuedAmount, String paymentConfId, String aadharCardNo, String receiptNo, String workingPlace, String isActive, Date regTimeStamp) {
/* 222 */     this.registrationPK = registrationPK;
/* 223 */     this.appliedDatae = appliedDatae;
/* 224 */     this.deptName = deptName;
/* 225 */     this.eduQualification = eduQualification;
/* 226 */     this.firstName = firstName;
/* 227 */     this.knownLanguages = knownLanguages;
/* 228 */     this.lastName = lastName;
/* 229 */     this.membershipAmount = membershipAmount;
/* 230 */     this.motherTongue = motherTongue;
/* 231 */     this.nameOfCompany = nameOfCompany;
/* 232 */     this.natureOfWork = natureOfWork;
/* 233 */     this.nominie = nominie;
/* 234 */     this.paidAmount = paidAmount;
/* 235 */     this.perminentAddress = perminentAddress;
/* 236 */     this.phoneNo = phoneNo;
/* 237 */     this.presentAddress = presentAddress;
/* 238 */     this.registeredDate = registeredDate;
/* 239 */     this.relationWithNominie = relationWithNominie;
/* 240 */     this.sNo = sNo;
/* 241 */     this.fatherName = fatherName;
/* 242 */     this.dateOfBirth = dateOfBirth;
/* 243 */     this.unitId = unitId;
/* 244 */     this.currentLoanStatus = currentLoanStatus;
/* 245 */     this.currentLoanBalance = currentLoanBalance;
/* 246 */     this.cardAmount = cardAmount;
/* 247 */     this.currentLoanIssuedAmount = currentLoanIssuedAmount;
/* 248 */     this.paymentConfId = paymentConfId;
/* 249 */     this.aadharCardNo = aadharCardNo;
/* 250 */     this.receiptNo = receiptNo;
/* 251 */     this.workingPlace = workingPlace;
/* 252 */     this.isActive = isActive;
/* 253 */     this.regTimeStamp = regTimeStamp;
/*     */   }
/*     */   
/*     */   public Registration(int cardNo, String deptId, String memberId, String transactionId) {
/* 257 */     this.registrationPK = new RegistrationPK(cardNo, deptId, memberId, transactionId);
/*     */   }
/*     */   
/*     */   public RegistrationPK getRegistrationPK() {
/* 261 */     return this.registrationPK;
/*     */   }
/*     */   
/*     */   public void setRegistrationPK(RegistrationPK registrationPK) {
/* 265 */     this.registrationPK = registrationPK;
/*     */   }
/*     */   
/*     */   public String getAltPhoneNo() {
/* 269 */     return this.altPhoneNo;
/*     */   }
/*     */   
/*     */   public void setAltPhoneNo(String altPhoneNo) {
/* 273 */     this.altPhoneNo = altPhoneNo;
/*     */   }
/*     */   
/*     */   public Date getAppliedDatae() {
/* 277 */     return this.appliedDatae;
/*     */   }
/*     */   
/*     */   public void setAppliedDatae(Date appliedDatae) {
/* 281 */     this.appliedDatae = appliedDatae;
/*     */   }
/*     */   
/*     */   public String getBloodGroup() {
/* 285 */     return this.bloodGroup;
/*     */   }
/*     */   
/*     */   public void setBloodGroup(String bloodGroup) {
/* 289 */     this.bloodGroup = bloodGroup;
/*     */   }
/*     */   
/*     */   public String getDeptName() {
/* 293 */     return this.deptName;
/*     */   }
/*     */   
/*     */   public void setDeptName(String deptName) {
/* 297 */     this.deptName = deptName;
/*     */   }
/*     */   
/*     */   public String getEduQualification() {
/* 301 */     return this.eduQualification;
/*     */   }
/*     */   
/*     */   public void setEduQualification(String eduQualification) {
/* 305 */     this.eduQualification = eduQualification;
/*     */   }
/*     */   
/*     */   public String getFefsiMember() {
/* 309 */     return this.fefsiMember;
/*     */   }
/*     */   
/*     */   public void setFefsiMember(String fefsiMember) {
/* 313 */     this.fefsiMember = fefsiMember;
/*     */   }
/*     */   
/*     */   public String getFirstName() {
/* 317 */     return this.firstName;
/*     */   }
/*     */   
/*     */   public void setFirstName(String firstName) {
/* 321 */     this.firstName = firstName;
/*     */   }
/*     */   
/*     */   public String getKnownLanguages() {
/* 325 */     return this.knownLanguages;
/*     */   }
/*     */   
/*     */   public void setKnownLanguages(String knownLanguages) {
/* 329 */     this.knownLanguages = knownLanguages;
/*     */   }
/*     */   
/*     */   public String getLastName() {
/* 333 */     return this.lastName;
/*     */   }
/*     */   
/*     */   public void setLastName(String lastName) {
/* 337 */     this.lastName = lastName;
/*     */   }
/*     */   
/*     */   public int getMembershipAmount() {
/* 341 */     return this.membershipAmount;
/*     */   }
/*     */   
/*     */   public void setMembershipAmount(int membershipAmount) {
/* 345 */     this.membershipAmount = membershipAmount;
/*     */   }
/*     */   
/*     */   public String getMiddleName() {
/* 349 */     return this.middleName;
/*     */   }
/*     */   
/*     */   public void setMiddleName(String middleName) {
/* 353 */     this.middleName = middleName;
/*     */   }
/*     */   
/*     */   public String getMotherTongue() {
/* 357 */     return this.motherTongue;
/*     */   }
/*     */   
/*     */   public void setMotherTongue(String motherTongue) {
/* 361 */     this.motherTongue = motherTongue;
/*     */   }
/*     */   
/*     */   public String getNameOfCompany() {
/* 365 */     return this.nameOfCompany;
/*     */   }
/*     */   
/*     */   public void setNameOfCompany(String nameOfCompany) {
/* 369 */     this.nameOfCompany = nameOfCompany;
/*     */   }
/*     */   
/*     */   public String getNatureOfWork() {
/* 373 */     return this.natureOfWork;
/*     */   }
/*     */   
/*     */   public void setNatureOfWork(String natureOfWork) {
/* 377 */     this.natureOfWork = natureOfWork;
/*     */   }
/*     */   
/*     */   public String getNominie() {
/* 381 */     return this.nominie;
/*     */   }
/*     */   
/*     */   public void setNominie(String nominie) {
/* 385 */     this.nominie = nominie;
/*     */   }
/*     */   
/*     */   public int getPaidAmount() {
/* 389 */     return this.paidAmount;
/*     */   }
/*     */   
/*     */   public void setPaidAmount(int paidAmount) {
/* 393 */     this.paidAmount = paidAmount;
/*     */   }
/*     */   
/*     */   public String getPerminentAddress() {
/* 397 */     return this.perminentAddress;
/*     */   }
/*     */   
/*     */   public void setPerminentAddress(String perminentAddress) {
/* 401 */     this.perminentAddress = perminentAddress;
/*     */   }
/*     */   
/*     */   public String getPhoneNo() {
/* 405 */     return this.phoneNo;
/*     */   }
/*     */   
/*     */   public void setPhoneNo(String phoneNo) {
/* 409 */     this.phoneNo = phoneNo;
/*     */   }
/*     */   
/*     */   public String getPresentAddress() {
/* 413 */     return this.presentAddress;
/*     */   }
/*     */   
/*     */   public void setPresentAddress(String presentAddress) {
/* 417 */     this.presentAddress = presentAddress;
/*     */   }
/*     */   
/*     */   public Date getRegisteredDate() {
/* 421 */     return this.registeredDate;
/*     */   }
/*     */   
/*     */   public void setRegisteredDate(Date registeredDate) {
/* 425 */     this.registeredDate = registeredDate;
/*     */   }
/*     */   
/*     */   public String getRelationWithNominie() {
/* 429 */     return this.relationWithNominie;
/*     */   }
/*     */   
/*     */   public void setRelationWithNominie(String relationWithNominie) {
/* 433 */     this.relationWithNominie = relationWithNominie;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 437 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 441 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getFatherName() {
/* 445 */     return this.fatherName;
/*     */   }
/*     */   
/*     */   public void setFatherName(String fatherName) {
/* 449 */     this.fatherName = fatherName;
/*     */   }
/*     */   
/*     */   public Date getDateOfBirth() {
/* 453 */     return this.dateOfBirth;
/*     */   }
/*     */   
/*     */   public void setDateOfBirth(Date dateOfBirth) {
/* 457 */     this.dateOfBirth = dateOfBirth;
/*     */   }
/*     */   
/*     */   public String getUnitId() {
/* 461 */     return this.unitId;
/*     */   }
/*     */   
/*     */   public void setUnitId(String unitId) {
/* 465 */     this.unitId = unitId;
/*     */   }
/*     */   
/*     */   public String getCurrentLoanStatus() {
/* 469 */     return this.currentLoanStatus;
/*     */   }
/*     */   
/*     */   public void setCurrentLoanStatus(String currentLoanStatus) {
/* 473 */     this.currentLoanStatus = currentLoanStatus;
/*     */   }
/*     */   
/*     */   public String getCurrentLoanId() {
/* 477 */     return this.currentLoanId;
/*     */   }
/*     */   
/*     */   public void setCurrentLoanId(String currentLoanId) {
/* 481 */     this.currentLoanId = currentLoanId;
/*     */   }
/*     */   
/*     */   public String getCurrentLoanBalance() {
/* 485 */     return this.currentLoanBalance;
/*     */   }
/*     */   
/*     */   public void setCurrentLoanBalance(String currentLoanBalance) {
/* 489 */     this.currentLoanBalance = currentLoanBalance;
/*     */   }
/*     */   
/*     */   public String getCardBalance() {
/* 493 */     return this.cardBalance;
/*     */   }
/*     */   
/*     */   public void setCardBalance(String cardBalance) {
/* 497 */     this.cardBalance = cardBalance;
/*     */   }
/*     */   
/*     */   public String getBankAccHolderName() {
/* 501 */     return this.bankAccHolderName;
/*     */   }
/*     */   
/*     */   public void setBankAccHolderName(String bankAccHolderName) {
/* 505 */     this.bankAccHolderName = bankAccHolderName;
/*     */   }
/*     */   
/*     */   public String getBankAccNo() {
/* 509 */     return this.bankAccNo;
/*     */   }
/*     */   
/*     */   public void setBankAccNo(String bankAccNo) {
/* 513 */     this.bankAccNo = bankAccNo;
/*     */   }
/*     */   
/*     */   public String getBankBranch() {
/* 517 */     return this.bankBranch;
/*     */   }
/*     */   
/*     */   public void setBankBranch(String bankBranch) {
/* 521 */     this.bankBranch = bankBranch;
/*     */   }
/*     */   
/*     */   public String getBankIfscCode() {
/* 525 */     return this.bankIfscCode;
/*     */   }
/*     */   
/*     */   public void setBankIfscCode(String bankIfscCode) {
/* 529 */     this.bankIfscCode = bankIfscCode;
/*     */   }
/*     */   
/*     */   public String getCardAmount() {
/* 533 */     return this.cardAmount;
/*     */   }
/*     */   
/*     */   public void setCardAmount(String cardAmount) {
/* 537 */     this.cardAmount = cardAmount;
/*     */   }
/*     */   
/*     */   public String getCurrentLoanIssuedAmount() {
/* 541 */     return this.currentLoanIssuedAmount;
/*     */   }
/*     */   
/*     */   public void setCurrentLoanIssuedAmount(String currentLoanIssuedAmount) {
/* 545 */     this.currentLoanIssuedAmount = currentLoanIssuedAmount;
/*     */   }
/*     */   
/*     */   public String getPaymentConfId() {
/* 549 */     return this.paymentConfId;
/*     */   }
/*     */   
/*     */   public void setPaymentConfId(String paymentConfId) {
/* 553 */     this.paymentConfId = paymentConfId;
/*     */   }
/*     */   
/*     */   public String getAadharCardNo() {
/* 557 */     return this.aadharCardNo;
/*     */   }
/*     */   
/*     */   public void setAadharCardNo(String aadharCardNo) {
/* 561 */     this.aadharCardNo = aadharCardNo;
/*     */   }
/*     */   
/*     */   public String getBankName() {
/* 565 */     return this.bankName;
/*     */   }
/*     */   
/*     */   public void setBankName(String bankName) {
/* 569 */     this.bankName = bankName;
/*     */   }
/*     */   
/*     */   public String getReceiptNo() {
/* 573 */     return this.receiptNo;
/*     */   }
/*     */   
/*     */   public void setReceiptNo(String receiptNo) {
/* 577 */     this.receiptNo = receiptNo;
/*     */   }
/*     */   
/*     */   public String getWorkingPlace() {
/* 581 */     return this.workingPlace;
/*     */   }
/*     */   
/*     */   public void setWorkingPlace(String workingPlace) {
/* 585 */     this.workingPlace = workingPlace;
/*     */   }
/*     */   
/*     */   public String getIsActive() {
/* 589 */     return this.isActive;
/*     */   }
/*     */   
/*     */   public void setIsActive(String isActive) {
/* 593 */     this.isActive = isActive;
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 597 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public void setFileName(String fileName) {
/* 601 */     this.fileName = fileName;
/*     */   }
/*     */   
/*     */   public String getFileType() {
/* 605 */     return this.fileType;
/*     */   }
/*     */   
/*     */   public void setFileType(String fileType) {
/* 609 */     this.fileType = fileType;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 613 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 617 */     this.status = status;
/*     */   }
/*     */   
/*     */   public byte[] getFileContent() {
/* 621 */     return this.fileContent;
/*     */   }
/*     */   
/*     */   public void setFileContent(byte[] fileContent) {
/* 625 */     this.fileContent = fileContent;
/*     */   }
/*     */   
/*     */   public Date getRegTimeStamp() {
/* 629 */     return this.regTimeStamp;
/*     */   }
/*     */   
/*     */   public void setRegTimeStamp(Date regTimeStamp) {
/* 633 */     this.regTimeStamp = regTimeStamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 638 */     int hash = 0;
/* 639 */     return (this.registrationPK != null) ? this.registrationPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 646 */     if (!(object instanceof Registration)) {
/* 647 */       return false;
/*     */     }
/* 649 */     Registration other = (Registration)object;
/* 650 */     if ((this.registrationPK == null && other.registrationPK != null) || (this.registrationPK != null && !this.registrationPK.equals(other.registrationPK))) {
/* 651 */       return false;
/*     */     }
/* 653 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 658 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Registration[ registrationPK=" + this.registrationPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Registration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */