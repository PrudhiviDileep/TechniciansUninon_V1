/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.EmbeddedId;
/*     */ import javax.persistence.Entity;
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
/*     */ @Entity
/*     */ @Table(name = "membership_payments")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "MembershipPayments.findAll", query = "SELECT m FROM MembershipPayments m"), @NamedQuery(name = "MembershipPayments.findByMemberId", query = "SELECT m FROM MembershipPayments m WHERE m.membershipPaymentsPK.memberId = :memberId"), @NamedQuery(name = "MembershipPayments.findByPaidAmount", query = "SELECT m FROM MembershipPayments m WHERE m.paidAmount = :paidAmount"), @NamedQuery(name = "MembershipPayments.findByPaidDate", query = "SELECT m FROM MembershipPayments m WHERE m.paidDate = :paidDate"), @NamedQuery(name = "MembershipPayments.findByReceiptNo", query = "SELECT m FROM MembershipPayments m WHERE m.receiptNo = :receiptNo"), @NamedQuery(name = "MembershipPayments.findByRemarks", query = "SELECT m FROM MembershipPayments m WHERE m.remarks = :remarks"), @NamedQuery(name = "MembershipPayments.findBySNo", query = "SELECT m FROM MembershipPayments m WHERE m.sNo = :sNo"), @NamedQuery(name = "MembershipPayments.findByTransactionId", query = "SELECT m FROM MembershipPayments m WHERE m.membershipPaymentsPK.transactionId = :transactionId"), @NamedQuery(name = "MembershipPayments.findByCategory", query = "SELECT m FROM MembershipPayments m WHERE m.category = :category"), @NamedQuery(name = "MembershipPayments.findByPaymentConfId", query = "SELECT m FROM MembershipPayments m WHERE m.paymentConfId = :paymentConfId"), @NamedQuery(name = "MembershipPayments.findByPaymentType", query = "SELECT m FROM MembershipPayments m WHERE m.paymentType = :paymentType"), @NamedQuery(name = "MembershipPayments.findByDdNo", query = "SELECT m FROM MembershipPayments m WHERE m.ddNo = :ddNo"), @NamedQuery(name = "MembershipPayments.findByDonationAmount", query = "SELECT m FROM MembershipPayments m WHERE m.donationAmount = :donationAmount")})
/*     */ public class MembershipPayments
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected MembershipPaymentsPK membershipPaymentsPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAID_AMOUNT")
/*     */   private int paidAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAID_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date paidDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECEIPT_NO")
/*     */   private String receiptNo;
/*     */   @Column(name = "REMARKS")
/*     */   private String remarks;
/*     */   @Column(name = "S_NO")
/*     */   private Integer sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CATEGORY")
/*     */   private String category;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_CONF_ID")
/*     */   private String paymentConfId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_TYPE")
/*     */   private String paymentType;
/*     */   @Column(name = "DD_NO")
/*     */   private String ddNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DONATION_AMOUNT")
/*     */   private int donationAmount;
/*     */   
/*     */   public MembershipPayments() {}
/*     */   
/*     */   public MembershipPayments(MembershipPaymentsPK membershipPaymentsPK) {
/*  80 */     this.membershipPaymentsPK = membershipPaymentsPK;
/*     */   }
/*     */   
/*     */   public MembershipPayments(MembershipPaymentsPK membershipPaymentsPK, int paidAmount, Date paidDate, String receiptNo, String category, String paymentConfId, String paymentType, int donationAmount) {
/*  84 */     this.membershipPaymentsPK = membershipPaymentsPK;
/*  85 */     this.paidAmount = paidAmount;
/*  86 */     this.paidDate = paidDate;
/*  87 */     this.receiptNo = receiptNo;
/*  88 */     this.category = category;
/*  89 */     this.paymentConfId = paymentConfId;
/*  90 */     this.paymentType = paymentType;
/*  91 */     this.donationAmount = donationAmount;
/*     */   }
/*     */   
/*     */   public MembershipPayments(String memberId, String transactionId) {
/*  95 */     this.membershipPaymentsPK = new MembershipPaymentsPK(memberId, transactionId);
/*     */   }
/*     */   
/*     */   public MembershipPaymentsPK getMembershipPaymentsPK() {
/*  99 */     return this.membershipPaymentsPK;
/*     */   }
/*     */   
/*     */   public void setMembershipPaymentsPK(MembershipPaymentsPK membershipPaymentsPK) {
/* 103 */     this.membershipPaymentsPK = membershipPaymentsPK;
/*     */   }
/*     */   
/*     */   public int getPaidAmount() {
/* 107 */     return this.paidAmount;
/*     */   }
/*     */   
/*     */   public void setPaidAmount(int paidAmount) {
/* 111 */     this.paidAmount = paidAmount;
/*     */   }
/*     */   
/*     */   public Date getPaidDate() {
/* 115 */     return this.paidDate;
/*     */   }
/*     */   
/*     */   public void setPaidDate(Date paidDate) {
/* 119 */     this.paidDate = paidDate;
/*     */   }
/*     */   
/*     */   public String getReceiptNo() {
/* 123 */     return this.receiptNo;
/*     */   }
/*     */   
/*     */   public void setReceiptNo(String receiptNo) {
/* 127 */     this.receiptNo = receiptNo;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 131 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 135 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public Integer getSNo() {
/* 139 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(Integer sNo) {
/* 143 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 147 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 151 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getPaymentConfId() {
/* 155 */     return this.paymentConfId;
/*     */   }
/*     */   
/*     */   public void setPaymentConfId(String paymentConfId) {
/* 159 */     this.paymentConfId = paymentConfId;
/*     */   }
/*     */   
/*     */   public String getPaymentType() {
/* 163 */     return this.paymentType;
/*     */   }
/*     */   
/*     */   public void setPaymentType(String paymentType) {
/* 167 */     this.paymentType = paymentType;
/*     */   }
/*     */   
/*     */   public String getDdNo() {
/* 171 */     return this.ddNo;
/*     */   }
/*     */   
/*     */   public void setDdNo(String ddNo) {
/* 175 */     this.ddNo = ddNo;
/*     */   }
/*     */   
/*     */   public int getDonationAmount() {
/* 179 */     return this.donationAmount;
/*     */   }
/*     */   
/*     */   public void setDonationAmount(int donationAmount) {
/* 183 */     this.donationAmount = donationAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 188 */     int hash = 0;
/* 189 */     return (this.membershipPaymentsPK != null) ? this.membershipPaymentsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 196 */     if (!(object instanceof MembershipPayments)) {
/* 197 */       return false;
/*     */     }
/* 199 */     MembershipPayments other = (MembershipPayments)object;
/* 200 */     if ((this.membershipPaymentsPK == null && other.membershipPaymentsPK != null) || (this.membershipPaymentsPK != null && !this.membershipPaymentsPK.equals(other.membershipPaymentsPK))) {
/* 201 */       return false;
/*     */     }
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 208 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.MembershipPayments[ membershipPaymentsPK=" + this.membershipPaymentsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\MembershipPayments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */