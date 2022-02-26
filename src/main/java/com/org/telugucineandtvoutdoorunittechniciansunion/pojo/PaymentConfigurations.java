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
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "payment_configurations")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "PaymentConfigurations.findAll", query = "SELECT p FROM PaymentConfigurations p"), @NamedQuery(name = "PaymentConfigurations.findBySNo", query = "SELECT p FROM PaymentConfigurations p WHERE p.sNo = :sNo"), @NamedQuery(name = "PaymentConfigurations.findByPaymentConfId", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.paymentConfId = :paymentConfId"), @NamedQuery(name = "PaymentConfigurations.findByDeptId", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.deptId = :deptId"), @NamedQuery(name = "PaymentConfigurations.findByCategory", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.category = :category"), @NamedQuery(name = "PaymentConfigurations.findByDonationAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.donationAmount = :donationAmount"), @NamedQuery(name = "PaymentConfigurations.findBySubscriptionAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.subscriptionAmount = :subscriptionAmount"), @NamedQuery(name = "PaymentConfigurations.findByAdminAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.adminAmount = :adminAmount"), @NamedQuery(name = "PaymentConfigurations.findByConfiguredDate", query = "SELECT p FROM PaymentConfigurations p WHERE p.configuredDate = :configuredDate"), @NamedQuery(name = "PaymentConfigurations.findByConfiguredBy", query = "SELECT p FROM PaymentConfigurations p WHERE p.configuredBy = :configuredBy"), @NamedQuery(name = "PaymentConfigurations.findByMembershipAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.membershipAmount = :membershipAmount"), @NamedQuery(name = "PaymentConfigurations.findByRemarks", query = "SELECT p FROM PaymentConfigurations p WHERE p.remarks = :remarks"), @NamedQuery(name = "PaymentConfigurations.findByStatus", query = "SELECT p FROM PaymentConfigurations p WHERE p.status = :status")})
/*     */ public class PaymentConfigurations
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected PaymentConfigurationsPK paymentConfigurationsPK;
/*     */   @Column(name = "S_NO")
/*     */   private Integer sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DONATION_AMOUNT")
/*     */   private int donationAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "SUBSCRIPTION_AMOUNT")
/*     */   private int subscriptionAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ADMIN_AMOUNT")
/*     */   private int adminAmount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CONFIGURED_DATE")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date configuredDate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "CONFIGURED_BY")
/*     */   private String configuredBy;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBERSHIP_AMOUNT")
/*     */   private int membershipAmount;
/*     */   @Column(name = "REMARKS")
/*     */   private String remarks;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "STATUS")
/*     */   private String status;
/*     */   
/*     */   public PaymentConfigurations() {}
/*     */   
/*     */   public PaymentConfigurations(PaymentConfigurationsPK paymentConfigurationsPK) {
/*  78 */     this.paymentConfigurationsPK = paymentConfigurationsPK;
/*     */   }
/*     */   
/*     */   public PaymentConfigurations(PaymentConfigurationsPK paymentConfigurationsPK, int donationAmount, int subscriptionAmount, int adminAmount, Date configuredDate, String configuredBy, int membershipAmount, String status) {
/*  82 */     this.paymentConfigurationsPK = paymentConfigurationsPK;
/*  83 */     this.donationAmount = donationAmount;
/*  84 */     this.subscriptionAmount = subscriptionAmount;
/*  85 */     this.adminAmount = adminAmount;
/*  86 */     this.configuredDate = configuredDate;
/*  87 */     this.configuredBy = configuredBy;
/*  88 */     this.membershipAmount = membershipAmount;
/*  89 */     this.status = status;
/*     */   }
/*     */   
/*     */   public PaymentConfigurations(String paymentConfId, String deptId, String category) {
/*  93 */     this.paymentConfigurationsPK = new PaymentConfigurationsPK(paymentConfId, deptId, category);
/*     */   }
/*     */   
/*     */   public PaymentConfigurationsPK getPaymentConfigurationsPK() {
/*  97 */     return this.paymentConfigurationsPK;
/*     */   }
/*     */   
/*     */   public void setPaymentConfigurationsPK(PaymentConfigurationsPK paymentConfigurationsPK) {
/* 101 */     this.paymentConfigurationsPK = paymentConfigurationsPK;
/*     */   }
/*     */   
/*     */   public Integer getSNo() {
/* 105 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(Integer sNo) {
/* 109 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public int getDonationAmount() {
/* 113 */     return this.donationAmount;
/*     */   }
/*     */   
/*     */   public void setDonationAmount(int donationAmount) {
/* 117 */     this.donationAmount = donationAmount;
/*     */   }
/*     */   
/*     */   public int getSubscriptionAmount() {
/* 121 */     return this.subscriptionAmount;
/*     */   }
/*     */   
/*     */   public void setSubscriptionAmount(int subscriptionAmount) {
/* 125 */     this.subscriptionAmount = subscriptionAmount;
/*     */   }
/*     */   
/*     */   public int getAdminAmount() {
/* 129 */     return this.adminAmount;
/*     */   }
/*     */   
/*     */   public void setAdminAmount(int adminAmount) {
/* 133 */     this.adminAmount = adminAmount;
/*     */   }
/*     */   
/*     */   public Date getConfiguredDate() {
/* 137 */     return this.configuredDate;
/*     */   }
/*     */   
/*     */   public void setConfiguredDate(Date configuredDate) {
/* 141 */     this.configuredDate = configuredDate;
/*     */   }
/*     */   
/*     */   public String getConfiguredBy() {
/* 145 */     return this.configuredBy;
/*     */   }
/*     */   
/*     */   public void setConfiguredBy(String configuredBy) {
/* 149 */     this.configuredBy = configuredBy;
/*     */   }
/*     */   
/*     */   public int getMembershipAmount() {
/* 153 */     return this.membershipAmount;
/*     */   }
/*     */   
/*     */   public void setMembershipAmount(int membershipAmount) {
/* 157 */     this.membershipAmount = membershipAmount;
/*     */   }
/*     */   
/*     */   public String getRemarks() {
/* 161 */     return this.remarks;
/*     */   }
/*     */   
/*     */   public void setRemarks(String remarks) {
/* 165 */     this.remarks = remarks;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 169 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 173 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 178 */     int hash = 0;
/* 179 */     return (this.paymentConfigurationsPK != null) ? this.paymentConfigurationsPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 186 */     if (!(object instanceof PaymentConfigurations)) {
/* 187 */       return false;
/*     */     }
/* 189 */     PaymentConfigurations other = (PaymentConfigurations)object;
/* 190 */     if ((this.paymentConfigurationsPK == null && other.paymentConfigurationsPK != null) || (this.paymentConfigurationsPK != null && !this.paymentConfigurationsPK.equals(other.paymentConfigurationsPK))) {
/* 191 */       return false;
/*     */     }
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 198 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.PaymentConfigurations[ paymentConfigurationsPK=" + this.paymentConfigurationsPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\PaymentConfigurations.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */