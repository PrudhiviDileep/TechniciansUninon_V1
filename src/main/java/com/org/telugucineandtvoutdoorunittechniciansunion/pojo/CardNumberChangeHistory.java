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
/*     */ @Entity
/*     */ @Table(name = "card_number_change_history")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "CardNumberChangeHistory.findAll", query = "SELECT c FROM CardNumberChangeHistory c"), @NamedQuery(name = "CardNumberChangeHistory.findByCardNo", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.cardNumberChangeHistoryPK.cardNo = :cardNo"), @NamedQuery(name = "CardNumberChangeHistory.findByDeptId", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.cardNumberChangeHistoryPK.deptId = :deptId"), @NamedQuery(name = "CardNumberChangeHistory.findByAction", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.action = :action"), @NamedQuery(name = "CardNumberChangeHistory.findByAllotedToMemberCardNo", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.allotedToMemberCardNo = :allotedToMemberCardNo"), @NamedQuery(name = "CardNumberChangeHistory.findByAllotedToMemberDeptId", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.allotedToMemberDeptId = :allotedToMemberDeptId"), @NamedQuery(name = "CardNumberChangeHistory.findByAllotedToMemberId", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.allotedToMemberId = :allotedToMemberId"), @NamedQuery(name = "CardNumberChangeHistory.findByAllotedToMemberName", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.allotedToMemberName = :allotedToMemberName"), @NamedQuery(name = "CardNumberChangeHistory.findByEditDate", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.editDate = :editDate"), @NamedQuery(name = "CardNumberChangeHistory.findBySNo", query = "SELECT c FROM CardNumberChangeHistory c WHERE c.sNo = :sNo")})
/*     */ public class CardNumberChangeHistory
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected CardNumberChangeHistoryPK cardNumberChangeHistoryPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ACTION")
/*     */   private String action;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ALLOTED_TO_MEMBER_CARD_NO")
/*     */   private int allotedToMemberCardNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ALLOTED_TO_MEMBER_DEPT_ID")
/*     */   private String allotedToMemberDeptId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ALLOTED_TO_MEMBER_ID")
/*     */   private String allotedToMemberId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ALLOTED_TO_MEMBER_NAME")
/*     */   private String allotedToMemberName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "EDIT_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date editDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   
/*     */   public CardNumberChangeHistory() {}
/*     */   
/*     */   public CardNumberChangeHistory(CardNumberChangeHistoryPK cardNumberChangeHistoryPK) {
/*  71 */     this.cardNumberChangeHistoryPK = cardNumberChangeHistoryPK;
/*     */   }
/*     */   
/*     */   public CardNumberChangeHistory(CardNumberChangeHistoryPK cardNumberChangeHistoryPK, String action, int allotedToMemberCardNo, String allotedToMemberDeptId, String allotedToMemberId, String allotedToMemberName, Date editDate, int sNo) {
/*  75 */     this.cardNumberChangeHistoryPK = cardNumberChangeHistoryPK;
/*  76 */     this.action = action;
/*  77 */     this.allotedToMemberCardNo = allotedToMemberCardNo;
/*  78 */     this.allotedToMemberDeptId = allotedToMemberDeptId;
/*  79 */     this.allotedToMemberId = allotedToMemberId;
/*  80 */     this.allotedToMemberName = allotedToMemberName;
/*  81 */     this.editDate = editDate;
/*  82 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public CardNumberChangeHistory(int cardNo, String deptId) {
/*  86 */     this.cardNumberChangeHistoryPK = new CardNumberChangeHistoryPK(cardNo, deptId);
/*     */   }
/*     */   
/*     */   public CardNumberChangeHistoryPK getCardNumberChangeHistoryPK() {
/*  90 */     return this.cardNumberChangeHistoryPK;
/*     */   }
/*     */   
/*     */   public void setCardNumberChangeHistoryPK(CardNumberChangeHistoryPK cardNumberChangeHistoryPK) {
/*  94 */     this.cardNumberChangeHistoryPK = cardNumberChangeHistoryPK;
/*     */   }
/*     */   
/*     */   public String getAction() {
/*  98 */     return this.action;
/*     */   }
/*     */   
/*     */   public void setAction(String action) {
/* 102 */     this.action = action;
/*     */   }
/*     */   
/*     */   public int getAllotedToMemberCardNo() {
/* 106 */     return this.allotedToMemberCardNo;
/*     */   }
/*     */   
/*     */   public void setAllotedToMemberCardNo(int allotedToMemberCardNo) {
/* 110 */     this.allotedToMemberCardNo = allotedToMemberCardNo;
/*     */   }
/*     */   
/*     */   public String getAllotedToMemberDeptId() {
/* 114 */     return this.allotedToMemberDeptId;
/*     */   }
/*     */   
/*     */   public void setAllotedToMemberDeptId(String allotedToMemberDeptId) {
/* 118 */     this.allotedToMemberDeptId = allotedToMemberDeptId;
/*     */   }
/*     */   
/*     */   public String getAllotedToMemberId() {
/* 122 */     return this.allotedToMemberId;
/*     */   }
/*     */   
/*     */   public void setAllotedToMemberId(String allotedToMemberId) {
/* 126 */     this.allotedToMemberId = allotedToMemberId;
/*     */   }
/*     */   
/*     */   public String getAllotedToMemberName() {
/* 130 */     return this.allotedToMemberName;
/*     */   }
/*     */   
/*     */   public void setAllotedToMemberName(String allotedToMemberName) {
/* 134 */     this.allotedToMemberName = allotedToMemberName;
/*     */   }
/*     */   
/*     */   public Date getEditDate() {
/* 138 */     return this.editDate;
/*     */   }
/*     */   
/*     */   public void setEditDate(Date editDate) {
/* 142 */     this.editDate = editDate;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 146 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 150 */     this.sNo = sNo;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 155 */     int hash = 0;
/* 156 */     return (this.cardNumberChangeHistoryPK != null) ? this.cardNumberChangeHistoryPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 163 */     if (!(object instanceof CardNumberChangeHistory)) {
/* 164 */       return false;
/*     */     }
/* 166 */     CardNumberChangeHistory other = (CardNumberChangeHistory)object;
/* 167 */     if ((this.cardNumberChangeHistoryPK == null && other.cardNumberChangeHistoryPK != null) || (this.cardNumberChangeHistoryPK != null && !this.cardNumberChangeHistoryPK.equals(other.cardNumberChangeHistoryPK))) {
/* 168 */       return false;
/*     */     }
/* 170 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 175 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.CardNumberChangeHistory[ cardNumberChangeHistoryPK=" + this.cardNumberChangeHistoryPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\CardNumberChangeHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */