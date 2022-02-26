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
/*    */ public class CardNumberChangeHistoryPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "CARD_NO")
/*    */   private int cardNo;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "DEPT_ID")
/*    */   private String deptId;
/*    */   
/*    */   public CardNumberChangeHistoryPK() {}
/*    */   
/*    */   public CardNumberChangeHistoryPK(int cardNo, String deptId) {
/* 31 */     this.cardNo = cardNo;
/* 32 */     this.deptId = deptId;
/*    */   }
/*    */   
/*    */   public int getCardNo() {
/* 36 */     return this.cardNo;
/*    */   }
/*    */   
/*    */   public void setCardNo(int cardNo) {
/* 40 */     this.cardNo = cardNo;
/*    */   }
/*    */   
/*    */   public String getDeptId() {
/* 44 */     return this.deptId;
/*    */   }
/*    */   
/*    */   public void setDeptId(String deptId) {
/* 48 */     this.deptId = deptId;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 53 */     int hash = 0;
/* 54 */     hash += this.cardNo;
/* 55 */     return (this.deptId != null) ? this.deptId.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (!(object instanceof CardNumberChangeHistoryPK)) {
/* 63 */       return false;
/*    */     }
/* 65 */     CardNumberChangeHistoryPK other = (CardNumberChangeHistoryPK)object;
/* 66 */     if (this.cardNo != other.cardNo) {
/* 67 */       return false;
/*    */     }
/* 69 */     if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.CardNumberChangeHistoryPK[ cardNo=" + this.cardNo + ", deptId=" + this.deptId + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\CardNumberChangeHistoryPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */