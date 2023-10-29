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
/*     */ @Table(name = "adminfee_payments")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "AdminfeePayments.findAll", query = "SELECT a FROM AdminfeePayments a"), @NamedQuery(name = "AdminfeePayments.findByMemberId", query = "SELECT a FROM AdminfeePayments a WHERE a.adminfeePaymentsPK.memberId = :memberId"), @NamedQuery(name = "AdminfeePayments.findByTransactionId", query = "SELECT a FROM AdminfeePayments a WHERE a.adminfeePaymentsPK.transactionId = :transactionId"), @NamedQuery(name = "AdminfeePayments.findByPaymentConfId", query = "SELECT a FROM AdminfeePayments a WHERE a.paymentConfId = :paymentConfId"), @NamedQuery(name = "AdminfeePayments.findByAdminFeeAmount", query = "SELECT a FROM AdminfeePayments a WHERE a.adminFeeAmount = :adminFeeAmount"), @NamedQuery(name = "AdminfeePayments.findByPaymentDate", query = "SELECT a FROM AdminfeePayments a WHERE a.paymentDate = :paymentDate"), @NamedQuery(name = "AdminfeePayments.findByCategory", query = "SELECT a FROM AdminfeePayments a WHERE a.category = :category"), @NamedQuery(name = "AdminfeePayments.findByRemarks", query = "SELECT a FROM AdminfeePayments a WHERE a.remarks = :remarks"), @NamedQuery(name = "AdminfeePayments.findBySNo", query = "SELECT a FROM AdminfeePayments a WHERE a.sNo = :sNo")})
/*     */ public class AdminfeePayments
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected AdminfeePaymentsPK adminfeePaymentsPK;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_CONF_ID")
/*     */   private String paymentConfId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ADMIN_FEE_AMOUNT")
/*     */   private int adminFeeAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PAYMENT_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date paymentDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CATEGORY")
/*     */   private String category;
/*     */   @Column(name = "REMARKS")
/*     */   private String remarks;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   
/*     */   public AdminfeePayments() {}
/*     */   
/*     */   public AdminfeePayments(AdminfeePaymentsPK adminfeePaymentsPK) {
/*  66 */     this.adminfeePaymentsPK = adminfeePaymentsPK;
/*     */   }
/*     */   
/*     */   public AdminfeePayments(AdminfeePaymentsPK adminfeePaymentsPK, String paymentConfId, int adminFeeAmount, Date paymentDate, String category, int sNo) {
/*  70 */     this.adminfeePaymentsPK = adminfeePaymentsPK;
/*  71 */     this.paymentConfId = paymentConfId;
/*  72 */     this.adminFeeAmount = adminFeeAmount;
/*  73 */     this.paymentDate = paymentDate;
/*  74 */     this.category = category;
/*  75 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public AdminfeePayments(String memberId, String transactionId) {
/*  79 */     this.adminfeePaymentsPK = new AdminfeePaymentsPK(memberId, transactionId);
/*     */   }
/*     */   
/*     */   public AdminfeePaymentsPK getAdminfeePaymentsPK() {
/*  83 */     return this.adminfeePaymentsPK;
/*     */   }
/*     */   
/*     */   public void setAdminfeePaymentsPK(AdminfeePaymentsPK adminfeePaymentsPK) {
/*  87 */     this.adminfeePaymentsPK = adminfeePaymentsPK;
/*     */   }
/*     */   
/*     */   public String getPaymentConfId() {
/*  91 */     return this.paymentConfId;
/*     */   }
/*     */   
/*     */   public void setPaymentConfId(String paymentConfId) {
/*  95 */     this.paymentConfId = paymentConfId;
/*     */   }
/*     */   
/*     */   public int getAdminFeeAmount() {
/*  99 */     return this.adminFeeAmount;
/*     */   }
/*     */   
/*     */   public void setAdminFeeAmount(int adminFeeAmount) {
/* 103 */     this.adminFeeAmount = adminFeeAmount;
/*     */   }
/*     */   
/*     */   public Date getPaymentDate() {
/* 107 */     return this.paymentDate;
/*     */   }
/*     */   
/*     */   public void setPaymentDate(Date paymentDate) {
/* 111 */     this.paymentDate = paymentDate;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 115 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 119 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 123 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 127 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 131 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 135 */     this.sNo = sNo;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 141 */     return (this.adminfeePaymentsPK != null) ? this.adminfeePaymentsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 148 */     if (!(object instanceof AdminfeePayments)) {
/* 149 */       return false;
/*     */     }
/* 151 */     AdminfeePayments other = (AdminfeePayments)object;
/* 152 */     if ((this.adminfeePaymentsPK == null && other.adminfeePaymentsPK != null) || (this.adminfeePaymentsPK != null && !this.adminfeePaymentsPK.equals(other.adminfeePaymentsPK))) {
/* 153 */       return false;
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return "com.telugucineandtvoutdoorunittechniciansunion.annotation.pojo.AdminfeePayments[ adminfeePaymentsPK=" + this.adminfeePaymentsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\AdminfeePayments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */