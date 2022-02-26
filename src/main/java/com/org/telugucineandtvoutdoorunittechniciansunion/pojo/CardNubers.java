/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.EmbeddedId;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.Table;
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
/*     */ @Entity
/*     */ @Table(name = "card_nubers")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "CardNubers.findAll", query = "SELECT c FROM CardNubers c"), @NamedQuery(name = "CardNubers.findByCardNo", query = "SELECT c FROM CardNubers c WHERE c.cardNubersPK.cardNo = :cardNo"), @NamedQuery(name = "CardNubers.findByDeptId", query = "SELECT c FROM CardNubers c WHERE c.cardNubersPK.deptId = :deptId"), @NamedQuery(name = "CardNubers.findByCardStatus", query = "SELECT c FROM CardNubers c WHERE c.cardStatus = :cardStatus"), @NamedQuery(name = "CardNubers.findBySNo", query = "SELECT c FROM CardNubers c WHERE c.sNo = :sNo"), @NamedQuery(name = "CardNubers.findByMemberId", query = "SELECT c FROM CardNubers c WHERE c.memberId = :memberId")})
/*     */ public class CardNubers
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected CardNubersPK cardNubersPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CARD_STATUS")
/*     */   private String cardStatus;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Column(name = "MEMBER_ID")
/*     */   private String memberId;
/*     */   
/*     */   public CardNubers() {}
/*     */   
/*     */   public CardNubers(CardNubersPK cardNubersPK) {
/*  50 */     this.cardNubersPK = cardNubersPK;
/*     */   }
/*     */   
/*     */   public CardNubers(CardNubersPK cardNubersPK, String cardStatus, int sNo) {
/*  54 */     this.cardNubersPK = cardNubersPK;
/*  55 */     this.cardStatus = cardStatus;
/*  56 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public CardNubers(int cardNo, String deptId) {
/*  60 */     this.cardNubersPK = new CardNubersPK(cardNo, deptId);
/*     */   }
/*     */   
/*     */   public CardNubersPK getCardNubersPK() {
/*  64 */     return this.cardNubersPK;
/*     */   }
/*     */   
/*     */   public void setCardNubersPK(CardNubersPK cardNubersPK) {
/*  68 */     this.cardNubersPK = cardNubersPK;
/*     */   }
/*     */   
/*     */   public String getCardStatus() {
/*  72 */     return this.cardStatus;
/*     */   }
/*     */   
/*     */   public void setCardStatus(String cardStatus) {
/*  76 */     this.cardStatus = cardStatus;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/*  80 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/*  84 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getMemberId() {
/*  88 */     return this.memberId;
/*     */   }
/*     */   
/*     */   public void setMemberId(String memberId) {
/*  92 */     this.memberId = memberId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  97 */     int hash = 0;
/*  98 */     return (this.cardNubersPK != null) ? this.cardNubersPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 105 */     if (!(object instanceof CardNubers)) {
/* 106 */       return false;
/*     */     }
/* 108 */     CardNubers other = (CardNubers)object;
/* 109 */     if ((this.cardNubersPK == null && other.cardNubersPK != null) || (this.cardNubersPK != null && !this.cardNubersPK.equals(other.cardNubersPK))) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 117 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.CardNubers[ cardNubersPK=" + this.cardNubersPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\CardNubers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */