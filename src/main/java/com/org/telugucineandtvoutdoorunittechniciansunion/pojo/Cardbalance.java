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
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "cardbalance")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Cardbalance.findAll", query = "SELECT c FROM Cardbalance c"), @NamedQuery(name = "Cardbalance.findBySno", query = "SELECT c FROM Cardbalance c WHERE c.sno = :sno"), @NamedQuery(name = "Cardbalance.findByMcardno", query = "SELECT c FROM Cardbalance c WHERE c.mcardno = :mcardno"), @NamedQuery(name = "Cardbalance.findByMdeptname", query = "SELECT c FROM Cardbalance c WHERE c.mdeptname = :mdeptname"), @NamedQuery(name = "Cardbalance.findByCardcost", query = "SELECT c FROM Cardbalance c WHERE c.cardcost = :cardcost"), @NamedQuery(name = "Cardbalance.findByRecptno", query = "SELECT c FROM Cardbalance c WHERE c.recptno = :recptno"), @NamedQuery(name = "Cardbalance.findByRdate", query = "SELECT c FROM Cardbalance c WHERE c.rdate = :rdate"), @NamedQuery(name = "Cardbalance.findByRamount", query = "SELECT c FROM Cardbalance c WHERE c.ramount = :ramount"), @NamedQuery(name = "Cardbalance.findByBalance", query = "SELECT c FROM Cardbalance c WHERE c.balance = :balance")})
/*     */ public class Cardbalance
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
/*     */   @Basic(optional = false)
/*     */   @Column(name = "cardcost")
/*     */   private int cardcost;
/*     */   @Column(name = "recptno")
/*     */   private String recptno;
/*     */   @Column(name = "rdate")
/*     */   private String rdate;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "ramount")
/*     */   private int ramount;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "balance")
/*     */   private int balance;
/*     */   
/*     */   public Cardbalance() {}
/*     */   
/*     */   public Cardbalance(Integer sno) {
/*  64 */     this.sno = sno;
/*     */   }
/*     */   
/*     */   public Cardbalance(Integer sno, int cardcost, int ramount, int balance) {
/*  68 */     this.sno = sno;
/*  69 */     this.cardcost = cardcost;
/*  70 */     this.ramount = ramount;
/*  71 */     this.balance = balance;
/*     */   }
/*     */   
/*     */   public Integer getSno() {
/*  75 */     return this.sno;
/*     */   }
/*     */   
/*     */   public void setSno(Integer sno) {
/*  79 */     this.sno = sno;
/*     */   }
/*     */   
/*     */   public String getMcardno() {
/*  83 */     return this.mcardno;
/*     */   }
/*     */   
/*     */   public void setMcardno(String mcardno) {
/*  87 */     this.mcardno = mcardno;
/*     */   }
/*     */   
/*     */   public String getMdeptname() {
/*  91 */     return this.mdeptname;
/*     */   }
/*     */   
/*     */   public void setMdeptname(String mdeptname) {
/*  95 */     this.mdeptname = mdeptname;
/*     */   }
/*     */   
/*     */   public int getCardcost() {
/*  99 */     return this.cardcost;
/*     */   }
/*     */   
/*     */   public void setCardcost(int cardcost) {
/* 103 */     this.cardcost = cardcost;
/*     */   }
/*     */   
/*     */   public String getRecptno() {
/* 107 */     return this.recptno;
/*     */   }
/*     */   
/*     */   public void setRecptno(String recptno) {
/* 111 */     this.recptno = recptno;
/*     */   }
/*     */   
/*     */   public String getRdate() {
/* 115 */     return this.rdate;
/*     */   }
/*     */   
/*     */   public void setRdate(String rdate) {
/* 119 */     this.rdate = rdate;
/*     */   }
/*     */   
/*     */   public int getRamount() {
/* 123 */     return this.ramount;
/*     */   }
/*     */   
/*     */   public void setRamount(int ramount) {
/* 127 */     this.ramount = ramount;
/*     */   }
/*     */   
/*     */   public int getBalance() {
/* 131 */     return this.balance;
/*     */   }
/*     */   
/*     */   public void setBalance(int balance) {
/* 135 */     this.balance = balance;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 140 */     int hash = 0;
/* 141 */     return (this.sno != null) ? this.sno.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 148 */     if (!(object instanceof Cardbalance)) {
/* 149 */       return false;
/*     */     }
/* 151 */     Cardbalance other = (Cardbalance)object;
/* 152 */     if ((this.sno == null && other.sno != null) || (this.sno != null && !this.sno.equals(other.sno))) {
/* 153 */       return false;
/*     */     }
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Cardbalance[ sno=" + this.sno + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Cardbalance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */