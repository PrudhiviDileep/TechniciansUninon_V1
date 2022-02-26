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
/*    */ public class LoandetailsPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "LOAN_ID")
/*    */   private String loanId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "MEMBER_ID")
/*    */   private String memberId;
/*    */   
/*    */   public LoandetailsPK() {}
/*    */   
/*    */   public LoandetailsPK(String loanId, String memberId) {
/* 31 */     this.loanId = loanId;
/* 32 */     this.memberId = memberId;
/*    */   }
/*    */   
/*    */   public String getLoanId() {
/* 36 */     return this.loanId;
/*    */   }
/*    */   
/*    */   public void setLoanId(String loanId) {
/* 40 */     this.loanId = loanId;
/*    */   }
/*    */   
/*    */   public String getMemberId() {
/* 44 */     return this.memberId;
/*    */   }
/*    */   
/*    */   public void setMemberId(String memberId) {
/* 48 */     this.memberId = memberId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 53 */     int hash = 0;
/* 54 */     hash += (this.loanId != null) ? this.loanId.hashCode() : 0;
/* 55 */     return (this.memberId != null) ? this.memberId.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (!(object instanceof LoandetailsPK)) {
/* 63 */       return false;
/*    */     }
/* 65 */     LoandetailsPK other = (LoandetailsPK)object;
/* 66 */     if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
/* 67 */       return false;
/*    */     }
/* 69 */     if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.LoandetailsPK[ loanId=" + this.loanId + ", memberId=" + this.memberId + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\LoandetailsPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */