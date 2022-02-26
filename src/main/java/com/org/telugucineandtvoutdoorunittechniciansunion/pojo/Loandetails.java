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
/*     */ @Entity
/*     */ @Table(name = "loandetails")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Loandetails.findAll", query = "SELECT l FROM Loandetails l"), @NamedQuery(name = "Loandetails.findByLoanId", query = "SELECT l FROM Loandetails l WHERE l.loandetailsPK.loanId = :loanId"), @NamedQuery(name = "Loandetails.findByMemberId", query = "SELECT l FROM Loandetails l WHERE l.loandetailsPK.memberId = :memberId"), @NamedQuery(name = "Loandetails.findByLoanAmount", query = "SELECT l FROM Loandetails l WHERE l.loanAmount = :loanAmount"), @NamedQuery(name = "Loandetails.findByLoanSanctionedDate", query = "SELECT l FROM Loandetails l WHERE l.loanSanctionedDate = :loanSanctionedDate"), @NamedQuery(name = "Loandetails.findByMemberName", query = "SELECT l FROM Loandetails l WHERE l.memberName = :memberName"), @NamedQuery(name = "Loandetails.findByRemarks", query = "SELECT l FROM Loandetails l WHERE l.remarks = :remarks"), @NamedQuery(name = "Loandetails.findBySNo", query = "SELECT l FROM Loandetails l WHERE l.sNo = :sNo"), @NamedQuery(name = "Loandetails.findByLoanStatus", query = "SELECT l FROM Loandetails l WHERE l.loanStatus = :loanStatus")})
/*     */ public class Loandetails
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected LoandetailsPK loandetailsPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "LOAN_AMOUNT")
/*     */   private int loanAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "LOAN_SANCTIONED_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date loanSanctionedDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBER_NAME")
/*     */   private String memberName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "REMARKS")
/*     */   private String remarks;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Column(name = "LOAN_STATUS")
/*     */   private String loanStatus;
/*     */   
/*     */   public Loandetails() {}
/*     */   
/*     */   public Loandetails(LoandetailsPK loandetailsPK) {
/*  66 */     this.loandetailsPK = loandetailsPK;
/*     */   }
/*     */   
/*     */   public Loandetails(LoandetailsPK loandetailsPK, int loanAmount, Date loanSanctionedDate, String memberName, String remarks, int sNo) {
/*  70 */     this.loandetailsPK = loandetailsPK;
/*  71 */     this.loanAmount = loanAmount;
/*  72 */     this.loanSanctionedDate = loanSanctionedDate;
/*  73 */     this.memberName = memberName;
/*  74 */     this.remarks = remarks;
/*  75 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public Loandetails(String loanId, String memberId) {
/*  79 */     this.loandetailsPK = new LoandetailsPK(loanId, memberId);
/*     */   }
/*     */   
/*     */   public LoandetailsPK getLoandetailsPK() {
/*  83 */     return this.loandetailsPK;
/*     */   }
/*     */   
/*     */   public void setLoandetailsPK(LoandetailsPK loandetailsPK) {
/*  87 */     this.loandetailsPK = loandetailsPK;
/*     */   }
/*     */   
/*     */   public int getLoanAmount() {
/*  91 */     return this.loanAmount;
/*     */   }
/*     */   
/*     */   public void setLoanAmount(int loanAmount) {
/*  95 */     this.loanAmount = loanAmount;
/*     */   }
/*     */   
/*     */   public Date getLoanSanctionedDate() {
/*  99 */     return this.loanSanctionedDate;
/*     */   }
/*     */   
/*     */   public void setLoanSanctionedDate(Date loanSanctionedDate) {
/* 103 */     this.loanSanctionedDate = loanSanctionedDate;
/*     */   }
/*     */   
/*     */   public String getMemberName() {
/* 107 */     return this.memberName;
/*     */   }
/*     */   
/*     */   public void setMemberName(String memberName) {
/* 111 */     this.memberName = memberName;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 115 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 119 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 123 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 127 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getLoanStatus() {
/* 131 */     return this.loanStatus;
/*     */   }
/*     */   
/*     */   public void setLoanStatus(String loanStatus) {
/* 135 */     this.loanStatus = loanStatus;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 140 */     int hash = 0;
/* 141 */     return (this.loandetailsPK != null) ? this.loandetailsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 148 */     if (!(object instanceof Loandetails)) {
/* 149 */       return false;
/*     */     }
/* 151 */     Loandetails other = (Loandetails)object;
/* 152 */     if ((this.loandetailsPK == null && other.loandetailsPK != null) || (this.loandetailsPK != null && !this.loandetailsPK.equals(other.loandetailsPK))) {
/* 153 */       return false;
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Loandetails[ loandetailsPK=" + this.loandetailsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Loandetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */