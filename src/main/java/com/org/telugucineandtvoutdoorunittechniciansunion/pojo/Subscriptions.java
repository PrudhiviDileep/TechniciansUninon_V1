/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Id;
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
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "subscriptions")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Subscriptions.findAll", query = "SELECT s FROM Subscriptions s"), @NamedQuery(name = "Subscriptions.findBySno", query = "SELECT s FROM Subscriptions s WHERE s.sno = :sno"), @NamedQuery(name = "Subscriptions.findByMcardno", query = "SELECT s FROM Subscriptions s WHERE s.mcardno = :mcardno"), @NamedQuery(name = "Subscriptions.findByMdeptname", query = "SELECT s FROM Subscriptions s WHERE s.mdeptname = :mdeptname"), @NamedQuery(name = "Subscriptions.findByAmount", query = "SELECT s FROM Subscriptions s WHERE s.amount = :amount"), @NamedQuery(name = "Subscriptions.findByRyear", query = "SELECT s FROM Subscriptions s WHERE s.ryear = :ryear"), @NamedQuery(name = "Subscriptions.findByPaid", query = "SELECT s FROM Subscriptions s WHERE s.paid = :paid")})
/*     */ public class Subscriptions
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @Column(name = "sno")
/*     */   private Integer sno;
/*     */   @Column(name = "mcardno")
/*     */   private String mcardno;
/*     */   @Column(name = "mdeptname")
/*     */   private String mdeptname;
/*     */   @Column(name = "amount")
/*     */   private String amount;
/*     */   @Column(name = "ryear")
/*     */   private String ryear;
/*     */   @Column(name = "paid")
/*     */   private String paid;
/*     */   
/*     */   public Subscriptions() {}
/*     */   
/*     */   public Subscriptions(Integer sno) {
/*  55 */     this.sno = sno;
/*     */   }
/*     */   
/*     */   public Integer getSno() {
/*  59 */     return this.sno;
/*     */   }
/*     */   
/*     */   public void setSno(Integer sno) {
/*  63 */     this.sno = sno;
/*     */   }
/*     */   
/*     */   public String getMcardno() {
/*  67 */     return this.mcardno;
/*     */   }
/*     */   
/*     */   public void setMcardno(String mcardno) {
/*  71 */     this.mcardno = mcardno;
/*     */   }
/*     */   
/*     */   public String getMdeptname() {
/*  75 */     return this.mdeptname;
/*     */   }
/*     */   
/*     */   public void setMdeptname(String mdeptname) {
/*  79 */     this.mdeptname = mdeptname;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/*  83 */     return this.amount;
/*     */   }
/*     */   
/*     */   public void setAmount(String amount) {
/*  87 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public String getRyear() {
/*  91 */     return this.ryear;
/*     */   }
/*     */   
/*     */   public void setRyear(String ryear) {
/*  95 */     this.ryear = ryear;
/*     */   }
/*     */   
/*     */   public String getPaid() {
/*  99 */     return this.paid;
/*     */   }
/*     */   
/*     */   public void setPaid(String paid) {
/* 103 */     this.paid = paid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 108 */     int hash = 0;
/* 109 */     return (this.sno != null) ? this.sno.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 116 */     if (!(object instanceof Subscriptions)) {
/* 117 */       return false;
/*     */     }
/* 119 */     Subscriptions other = (Subscriptions)object;
/* 120 */     if ((this.sno == null && other.sno != null) || (this.sno != null && !this.sno.equals(other.sno))) {
/* 121 */       return false;
/*     */     }
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 128 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Subscriptions[ sno=" + this.sno + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Subscriptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */