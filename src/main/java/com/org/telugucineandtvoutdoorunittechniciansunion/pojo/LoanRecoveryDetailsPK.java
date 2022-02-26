/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Embeddable
/*    */ public class LoanRecoveryDetailsPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "LOAN_ID")
/*    */   private String loanId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "MEMBER_ID")
/*    */   private String memberId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "TRANSACTION_ID")
/*    */   private String transactionId;
/*    */   
/*    */   public LoanRecoveryDetailsPK() {}
/*    */   
/*    */   public LoanRecoveryDetailsPK(String loanId, String memberId, String transactionId) {
/* 34 */     this.loanId = loanId;
/* 35 */     this.memberId = memberId;
/* 36 */     this.transactionId = transactionId;
/*    */   }
/*    */   
/*    */   public String getLoanId() {
/* 40 */     return this.loanId;
/*    */   }
/*    */   
/*    */   public void setLoanId(String loanId) {
/* 44 */     this.loanId = loanId;
/*    */   }
/*    */   
/*    */   public String getMemberId() {
/* 48 */     return this.memberId;
/*    */   }
/*    */   
/*    */   public void setMemberId(String memberId) {
/* 52 */     this.memberId = memberId;
/*    */   }
/*    */   
/*    */   public String getTransactionId() {
/* 56 */     return this.transactionId;
/*    */   }
/*    */   
/*    */   public void setTransactionId(String transactionId) {
/* 60 */     this.transactionId = transactionId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 65 */     int hash = 0;
/* 66 */     hash += (this.loanId != null) ? this.loanId.hashCode() : 0;
/* 67 */     hash += (this.memberId != null) ? this.memberId.hashCode() : 0;
/* 68 */     return (this.transactionId != null) ? this.transactionId.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 75 */     if (!(object instanceof LoanRecoveryDetailsPK)) {
/* 76 */       return false;
/*    */     }
/* 78 */     LoanRecoveryDetailsPK other = (LoanRecoveryDetailsPK)object;
/* 79 */     if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
/* 80 */       return false;
/*    */     }
/* 82 */     if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
/* 83 */       return false;
/*    */     }
/* 85 */     if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
/* 86 */       return false;
/*    */     }
/* 88 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.LoanRecoveryDetailsPK[ loanId=" + this.loanId + ", memberId=" + this.memberId + ", transactionId=" + this.transactionId + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\LoanRecoveryDetailsPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */