/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Embeddable;
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
/*     */ @Embeddable
/*     */ public class RegistrationPK
/*     */   implements Serializable
/*     */ {
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CARD_NO")
/*     */   private int cardNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DEPT_ID")
/*     */   private String deptId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBER_ID")
/*     */   private String memberId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "TRANSACTION_ID")
/*     */   private String transactionId;
/*     */   
/*     */   public RegistrationPK() {}
/*     */   
/*     */   public RegistrationPK(int cardNo, String deptId, String memberId, String transactionId) {
/*  37 */     this.cardNo = cardNo;
/*  38 */     this.deptId = deptId;
/*  39 */     this.memberId = memberId;
/*  40 */     this.transactionId = transactionId;
/*     */   }
/*     */   
/*     */   public int getCardNo() {
/*  44 */     return this.cardNo;
/*     */   }
/*     */   
/*     */   public void setCardNo(int cardNo) {
/*  48 */     this.cardNo = cardNo;
/*     */   }
/*     */   
/*     */   public String getDeptId() {
/*  52 */     return this.deptId;
/*     */   }
/*     */   
/*     */   public void setDeptId(String deptId) {
/*  56 */     this.deptId = deptId;
/*     */   }
/*     */   
/*     */   public String getMemberId() {
/*  60 */     return this.memberId;
/*     */   }
/*     */   
/*     */   public void setMemberId(String memberId) {
/*  64 */     this.memberId = memberId;
/*     */   }
/*     */   
/*     */   public String getTransactionId() {
/*  68 */     return this.transactionId;
/*     */   }
/*     */   
/*     */   public void setTransactionId(String transactionId) {
/*  72 */     this.transactionId = transactionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  77 */     int hash = 0;
/*  78 */     hash += this.cardNo;
/*  79 */     hash += (this.deptId != null) ? this.deptId.hashCode() : 0;
/*  80 */     hash += (this.memberId != null) ? this.memberId.hashCode() : 0;
/*  81 */     return (this.transactionId != null) ? this.transactionId.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  88 */     if (!(object instanceof RegistrationPK)) {
/*  89 */       return false;
/*     */     }
/*  91 */     RegistrationPK other = (RegistrationPK)object;
/*  92 */     if (this.cardNo != other.cardNo) {
/*  93 */       return false;
/*     */     }
/*  95 */     if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
/*  96 */       return false;
/*     */     }
/*  98 */     if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
/*  99 */       return false;
/*     */     }
/* 101 */     if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
/* 102 */       return false;
/*     */     }
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 109 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.RegistrationPK[ cardNo=" + this.cardNo + ", deptId=" + this.deptId + ", memberId=" + this.memberId + ", transactionId=" + this.transactionId + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\RegistrationPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */