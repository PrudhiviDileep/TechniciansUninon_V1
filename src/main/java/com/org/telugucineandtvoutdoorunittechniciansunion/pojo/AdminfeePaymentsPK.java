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
/*    */ public class AdminfeePaymentsPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "MEMBER_ID")
/*    */   private String memberId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "TRANSACTION_ID")
/*    */   private String transactionId;
/*    */   
/*    */   public AdminfeePaymentsPK() {}
/*    */   
/*    */   public AdminfeePaymentsPK(String memberId, String transactionId) {
/* 31 */     this.memberId = memberId;
/* 32 */     this.transactionId = transactionId;
/*    */   }
/*    */   
/*    */   public String getMemberId() {
/* 36 */     return this.memberId;
/*    */   }
/*    */   
/*    */   public void setMemberId(String memberId) {
/* 40 */     this.memberId = memberId;
/*    */   }
/*    */   
/*    */   public String getTransactionId() {
/* 44 */     return this.transactionId;
/*    */   }
/*    */   
/*    */   public void setTransactionId(String transactionId) {
/* 48 */     this.transactionId = transactionId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 53 */     int hash = 0;
/* 54 */     hash += (this.memberId != null) ? this.memberId.hashCode() : 0;
/* 55 */     return (this.transactionId != null) ? this.transactionId.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (!(object instanceof AdminfeePaymentsPK)) {
/* 63 */       return false;
/*    */     }
/* 65 */     AdminfeePaymentsPK other = (AdminfeePaymentsPK)object;
/* 66 */     if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
/* 67 */       return false;
/*    */     }
/* 69 */     if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "com.telugucineandtvoutdoorunittechniciansunion.annotation.pojo.AdminfeePaymentsPK[ memberId=" + this.memberId + ", transactionId=" + this.transactionId + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\AdminfeePaymentsPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */