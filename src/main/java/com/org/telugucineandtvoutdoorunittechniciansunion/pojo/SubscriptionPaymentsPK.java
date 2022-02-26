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
/*    */ public class SubscriptionPaymentsPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "RECEIPT_NO")
/*    */   private String receiptNo;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "SUBSCRIPTION_YEAR")
/*    */   private int subscriptionYear;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "DEPT_ID")
/*    */   private String deptId;
/*    */   
/*    */   public SubscriptionPaymentsPK() {}
/*    */   
/*    */   public SubscriptionPaymentsPK(String receiptNo, int subscriptionYear, String deptId) {
/* 34 */     this.receiptNo = receiptNo;
/* 35 */     this.subscriptionYear = subscriptionYear;
/* 36 */     this.deptId = deptId;
/*    */   }
/*    */   
/*    */   public String getReceiptNo() {
/* 40 */     return this.receiptNo;
/*    */   }
/*    */   
/*    */   public void setReceiptNo(String receiptNo) {
/* 44 */     this.receiptNo = receiptNo;
/*    */   }
/*    */   
/*    */   public int getSubscriptionYear() {
/* 48 */     return this.subscriptionYear;
/*    */   }
/*    */   
/*    */   public void setSubscriptionYear(int subscriptionYear) {
/* 52 */     this.subscriptionYear = subscriptionYear;
/*    */   }
/*    */   
/*    */   public String getDeptId() {
/* 56 */     return this.deptId;
/*    */   }
/*    */   
/*    */   public void setDeptId(String deptId) {
/* 60 */     this.deptId = deptId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 65 */     int hash = 0;
/* 66 */     hash += (this.receiptNo != null) ? this.receiptNo.hashCode() : 0;
/* 67 */     hash += this.subscriptionYear;
/* 68 */     return (this.deptId != null) ? this.deptId.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 75 */     if (!(object instanceof SubscriptionPaymentsPK)) {
/* 76 */       return false;
/*    */     }
/* 78 */     SubscriptionPaymentsPK other = (SubscriptionPaymentsPK)object;
/* 79 */     if ((this.receiptNo == null && other.receiptNo != null) || (this.receiptNo != null && !this.receiptNo.equals(other.receiptNo))) {
/* 80 */       return false;
/*    */     }
/* 82 */     if (this.subscriptionYear != other.subscriptionYear) {
/* 83 */       return false;
/*    */     }
/* 85 */     if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
/* 86 */       return false;
/*    */     }
/* 88 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.SubscriptionPaymentsPK[ receiptNo=" + this.receiptNo + ", subscriptionYear=" + this.subscriptionYear + ", deptId=" + this.deptId + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\SubscriptionPaymentsPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */