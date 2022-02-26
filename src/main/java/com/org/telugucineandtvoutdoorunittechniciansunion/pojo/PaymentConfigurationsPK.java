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
/*    */ public class PaymentConfigurationsPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "PAYMENT_CONF_ID")
/*    */   private String paymentConfId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "DEPT_ID")
/*    */   private String deptId;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "CATEGORY")
/*    */   private String category;
/*    */   
/*    */   public PaymentConfigurationsPK() {}
/*    */   
/*    */   public PaymentConfigurationsPK(String paymentConfId, String deptId, String category) {
/* 34 */     this.paymentConfId = paymentConfId;
/* 35 */     this.deptId = deptId;
/* 36 */     this.category = category;
/*    */   }
/*    */   
/*    */   public String getPaymentConfId() {
/* 40 */     return this.paymentConfId;
/*    */   }
/*    */   
/*    */   public void setPaymentConfId(String paymentConfId) {
/* 44 */     this.paymentConfId = paymentConfId;
/*    */   }
/*    */   
/*    */   public String getDeptId() {
/* 48 */     return this.deptId;
/*    */   }
/*    */   
/*    */   public void setDeptId(String deptId) {
/* 52 */     this.deptId = deptId;
/*    */   }
/*    */   
/*    */   public String getCategory() {
/* 56 */     return this.category;
/*    */   }
/*    */   
/*    */   public void setCategory(String category) {
/* 60 */     this.category = category;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 65 */     int hash = 0;
/* 66 */     hash += (this.paymentConfId != null) ? this.paymentConfId.hashCode() : 0;
/* 67 */     hash += (this.deptId != null) ? this.deptId.hashCode() : 0;
/* 68 */     return (this.category != null) ? this.category.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 75 */     if (!(object instanceof PaymentConfigurationsPK)) {
/* 76 */       return false;
/*    */     }
/* 78 */     PaymentConfigurationsPK other = (PaymentConfigurationsPK)object;
/* 79 */     if ((this.paymentConfId == null && other.paymentConfId != null) || (this.paymentConfId != null && !this.paymentConfId.equals(other.paymentConfId))) {
/* 80 */       return false;
/*    */     }
/* 82 */     if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
/* 83 */       return false;
/*    */     }
/* 85 */     if ((this.category == null && other.category != null) || (this.category != null && !this.category.equals(other.category))) {
/* 86 */       return false;
/*    */     }
/* 88 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.PaymentConfigurationsPK[ paymentConfId=" + this.paymentConfId + ", deptId=" + this.deptId + ", category=" + this.category + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\PaymentConfigurationsPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */