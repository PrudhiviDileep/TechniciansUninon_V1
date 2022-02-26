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
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "subscription_payments")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "SubscriptionPayments.findAll", query = "SELECT s FROM SubscriptionPayments s"), @NamedQuery(name = "SubscriptionPayments.findByMemberId", query = "SELECT s FROM SubscriptionPayments s WHERE s.memberId = :memberId"), @NamedQuery(name = "SubscriptionPayments.findByPaidDate", query = "SELECT s FROM SubscriptionPayments s WHERE s.paidDate = :paidDate"), @NamedQuery(name = "SubscriptionPayments.findByPaymentStatus", query = "SELECT s FROM SubscriptionPayments s WHERE s.paymentStatus = :paymentStatus"), @NamedQuery(name = "SubscriptionPayments.findByReceiptNo", query = "SELECT s FROM SubscriptionPayments s WHERE s.subscriptionPaymentsPK.receiptNo = :receiptNo"), @NamedQuery(name = "SubscriptionPayments.findByRemarks", query = "SELECT s FROM SubscriptionPayments s WHERE s.remarks = :remarks"), @NamedQuery(name = "SubscriptionPayments.findBySNo", query = "SELECT s FROM SubscriptionPayments s WHERE s.sNo = :sNo"), @NamedQuery(name = "SubscriptionPayments.findBySubscriptionAmount", query = "SELECT s FROM SubscriptionPayments s WHERE s.subscriptionAmount = :subscriptionAmount"), @NamedQuery(name = "SubscriptionPayments.findBySubscriptionYear", query = "SELECT s FROM SubscriptionPayments s WHERE s.subscriptionPaymentsPK.subscriptionYear = :subscriptionYear"), @NamedQuery(name = "SubscriptionPayments.findByTransactionId", query = "SELECT s FROM SubscriptionPayments s WHERE s.transactionId = :transactionId"), @NamedQuery(name = "SubscriptionPayments.findByPaymentConfId", query = "SELECT s FROM SubscriptionPayments s WHERE s.paymentConfId = :paymentConfId"), @NamedQuery(name = "SubscriptionPayments.findByCategory", query = "SELECT s FROM SubscriptionPayments s WHERE s.category = :category"), @NamedQuery(name = "SubscriptionPayments.findByPaidAmount", query = "SELECT s FROM SubscriptionPayments s WHERE s.paidAmount = :paidAmount"), @NamedQuery(name = "SubscriptionPayments.findByDeptId", query = "SELECT s FROM SubscriptionPayments s WHERE s.subscriptionPaymentsPK.deptId = :deptId")})
/*     */ public class SubscriptionPayments
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected SubscriptionPaymentsPK subscriptionPaymentsPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBER_ID")
/*     */   private String memberId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAID_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date paidDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_STATUS")
/*     */   private String paymentStatus;
/*     */   @Column(name = "REMARKS")
/*     */   private String remarks;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "SUBSCRIPTION_AMOUNT")
/*     */   private int subscriptionAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "TRANSACTION_ID")
/*     */   private String transactionId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_CONF_ID")
/*     */   private String paymentConfId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CATEGORY")
/*     */   private String category;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAID_AMOUNT")
/*     */   private int paidAmount;
/*     */   
/*     */   public SubscriptionPayments() {}
/*     */   
/*     */   public SubscriptionPayments(SubscriptionPaymentsPK subscriptionPaymentsPK) {
/*  83 */     this.subscriptionPaymentsPK = subscriptionPaymentsPK;
/*     */   }
/*     */   
/*     */   public SubscriptionPayments(SubscriptionPaymentsPK subscriptionPaymentsPK, String memberId, Date paidDate, String paymentStatus, int sNo, int subscriptionAmount, String transactionId, String paymentConfId, String category, int paidAmount) {
/*  87 */     this.subscriptionPaymentsPK = subscriptionPaymentsPK;
/*  88 */     this.memberId = memberId;
/*  89 */     this.paidDate = paidDate;
/*  90 */     this.paymentStatus = paymentStatus;
/*  91 */     this.sNo = sNo;
/*  92 */     this.subscriptionAmount = subscriptionAmount;
/*  93 */     this.transactionId = transactionId;
/*  94 */     this.paymentConfId = paymentConfId;
/*  95 */     this.category = category;
/*  96 */     this.paidAmount = paidAmount;
/*     */   }
/*     */   
/*     */   public SubscriptionPayments(String receiptNo, int subscriptionYear, String deptId) {
/* 100 */     this.subscriptionPaymentsPK = new SubscriptionPaymentsPK(receiptNo, subscriptionYear, deptId);
/*     */   }
/*     */   
/*     */   public SubscriptionPaymentsPK getSubscriptionPaymentsPK() {
/* 104 */     return this.subscriptionPaymentsPK;
/*     */   }
/*     */   
/*     */   public void setSubscriptionPaymentsPK(SubscriptionPaymentsPK subscriptionPaymentsPK) {
/* 108 */     this.subscriptionPaymentsPK = subscriptionPaymentsPK;
/*     */   }
/*     */   
/*     */   public String getMemberId() {
/* 112 */     return this.memberId;
/*     */   }
/*     */   
/*     */   public void setMemberId(String memberId) {
/* 116 */     this.memberId = memberId;
/*     */   }
/*     */   
/*     */   public Date getPaidDate() {
/* 120 */     return this.paidDate;
/*     */   }
/*     */   
/*     */   public void setPaidDate(Date paidDate) {
/* 124 */     this.paidDate = paidDate;
/*     */   }
/*     */   
/*     */   public String getPaymentStatus() {
/* 128 */     return this.paymentStatus;
/*     */   }
/*     */   
/*     */   public void setPaymentStatus(String paymentStatus) {
/* 132 */     this.paymentStatus = paymentStatus;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 136 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 140 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 144 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 148 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public int getSubscriptionAmount() {
/* 152 */     return this.subscriptionAmount;
/*     */   }
/*     */   
/*     */   public void setSubscriptionAmount(int subscriptionAmount) {
/* 156 */     this.subscriptionAmount = subscriptionAmount;
/*     */   }
/*     */   
/*     */   public String getTransactionId() {
/* 160 */     return this.transactionId;
/*     */   }
/*     */   
/*     */   public void setTransactionId(String transactionId) {
/* 164 */     this.transactionId = transactionId;
/*     */   }
/*     */   
/*     */   public String getPaymentConfId() {
/* 168 */     return this.paymentConfId;
/*     */   }
/*     */   
/*     */   public void setPaymentConfId(String paymentConfId) {
/* 172 */     this.paymentConfId = paymentConfId;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 176 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 180 */     this.category = category;
/*     */   }
/*     */   
/*     */   public int getPaidAmount() {
/* 184 */     return this.paidAmount;
/*     */   }
/*     */   
/*     */   public void setPaidAmount(int paidAmount) {
/* 188 */     this.paidAmount = paidAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 193 */     int hash = 0;
/* 194 */     return (this.subscriptionPaymentsPK != null) ? this.subscriptionPaymentsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 201 */     if (!(object instanceof SubscriptionPayments)) {
/* 202 */       return false;
/*     */     }
/* 204 */     SubscriptionPayments other = (SubscriptionPayments)object;
/* 205 */     if ((this.subscriptionPaymentsPK == null && other.subscriptionPaymentsPK != null) || (this.subscriptionPaymentsPK != null && !this.subscriptionPaymentsPK.equals(other.subscriptionPaymentsPK))) {
/* 206 */       return false;
/*     */     }
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 213 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.SubscriptionPayments[ subscriptionPaymentsPK=" + this.subscriptionPaymentsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\SubscriptionPayments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */