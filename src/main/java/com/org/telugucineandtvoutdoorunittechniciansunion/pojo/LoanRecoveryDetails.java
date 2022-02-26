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
/*     */ @Entity
/*     */ @Table(name = "loan_recovery_details")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "LoanRecoveryDetails.findAll", query = "SELECT l FROM LoanRecoveryDetails l"), @NamedQuery(name = "LoanRecoveryDetails.findByLoanId", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.loanRecoveryDetailsPK.loanId = :loanId"), @NamedQuery(name = "LoanRecoveryDetails.findByBalance", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.balance = :balance"), @NamedQuery(name = "LoanRecoveryDetails.findByPaidAmount", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.paidAmount = :paidAmount"), @NamedQuery(name = "LoanRecoveryDetails.findByPaidDate", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.paidDate = :paidDate"), @NamedQuery(name = "LoanRecoveryDetails.findByReceiptNo", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.receiptNo = :receiptNo"), @NamedQuery(name = "LoanRecoveryDetails.findByRemarks", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.remarks = :remarks"), @NamedQuery(name = "LoanRecoveryDetails.findBySNo", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.sNo = :sNo"), @NamedQuery(name = "LoanRecoveryDetails.findByMemberId", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.loanRecoveryDetailsPK.memberId = :memberId"), @NamedQuery(name = "LoanRecoveryDetails.findByStatus", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.status = :status"), @NamedQuery(name = "LoanRecoveryDetails.findByTransactionId", query = "SELECT l FROM LoanRecoveryDetails l WHERE l.loanRecoveryDetailsPK.transactionId = :transactionId")})
/*     */ public class LoanRecoveryDetails
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected LoanRecoveryDetailsPK loanRecoveryDetailsPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "BALANCE")
/*     */   private int balance;
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
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Column(name = "STATUS")
/*     */   private String status;
/*     */   
/*     */   public LoanRecoveryDetails() {}
/*     */   
/*     */   public LoanRecoveryDetails(LoanRecoveryDetailsPK loanRecoveryDetailsPK) {
/*  70 */     this.loanRecoveryDetailsPK = loanRecoveryDetailsPK;
/*     */   }
/*     */   
/*     */   public LoanRecoveryDetails(LoanRecoveryDetailsPK loanRecoveryDetailsPK, int balance, int paidAmount, Date paidDate, String receiptNo, int sNo) {
/*  74 */     this.loanRecoveryDetailsPK = loanRecoveryDetailsPK;
/*  75 */     this.balance = balance;
/*  76 */     this.paidAmount = paidAmount;
/*  77 */     this.paidDate = paidDate;
/*  78 */     this.receiptNo = receiptNo;
/*  79 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public LoanRecoveryDetails(String loanId, String memberId, String transactionId) {
/*  83 */     this.loanRecoveryDetailsPK = new LoanRecoveryDetailsPK(loanId, memberId, transactionId);
/*     */   }
/*     */   
/*     */   public LoanRecoveryDetailsPK getLoanRecoveryDetailsPK() {
/*  87 */     return this.loanRecoveryDetailsPK;
/*     */   }
/*     */   
/*     */   public void setLoanRecoveryDetailsPK(LoanRecoveryDetailsPK loanRecoveryDetailsPK) {
/*  91 */     this.loanRecoveryDetailsPK = loanRecoveryDetailsPK;
/*     */   }
/*     */   
/*     */   public int getBalance() {
/*  95 */     return this.balance;
/*     */   }
/*     */   
/*     */   public void setBalance(int balance) {
/*  99 */     this.balance = balance;
/*     */   }
/*     */   
/*     */   public int getPaidAmount() {
/* 103 */     return this.paidAmount;
/*     */   }
/*     */   
/*     */   public void setPaidAmount(int paidAmount) {
/* 107 */     this.paidAmount = paidAmount;
/*     */   }
/*     */   
/*     */   public Date getPaidDate() {
/* 111 */     return this.paidDate;
/*     */   }
/*     */   
/*     */   public void setPaidDate(Date paidDate) {
/* 115 */     this.paidDate = paidDate;
/*     */   }
/*     */   
/*     */   public String getReceiptNo() {
/* 119 */     return this.receiptNo;
/*     */   }
/*     */   
/*     */   public void setReceiptNo(String receiptNo) {
/* 123 */     this.receiptNo = receiptNo;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 127 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 131 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 135 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 139 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 143 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 147 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 152 */     int hash = 0;
/* 153 */     return (this.loanRecoveryDetailsPK != null) ? this.loanRecoveryDetailsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 160 */     if (!(object instanceof LoanRecoveryDetails)) {
/* 161 */       return false;
/*     */     }
/* 163 */     LoanRecoveryDetails other = (LoanRecoveryDetails)object;
/* 164 */     if ((this.loanRecoveryDetailsPK == null && other.loanRecoveryDetailsPK != null) || (this.loanRecoveryDetailsPK != null && !this.loanRecoveryDetailsPK.equals(other.loanRecoveryDetailsPK))) {
/* 165 */       return false;
/*     */     }
/* 167 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 172 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.LoanRecoveryDetails[ loanRecoveryDetailsPK=" + this.loanRecoveryDetailsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\LoanRecoveryDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */