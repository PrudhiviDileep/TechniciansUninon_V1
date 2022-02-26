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
/*     */ @Entity
/*     */ @Table(name = "units")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Units.findAll", query = "SELECT u FROM Units u"), @NamedQuery(name = "Units.findBySNo", query = "SELECT u FROM Units u WHERE u.sNo = :sNo"), @NamedQuery(name = "Units.findByUnitId", query = "SELECT u FROM Units u WHERE u.unitId = :unitId"), @NamedQuery(name = "Units.findByUnitName", query = "SELECT u FROM Units u WHERE u.unitName = :unitName"), @NamedQuery(name = "Units.findByUnitAddress", query = "SELECT u FROM Units u WHERE u.unitAddress = :unitAddress"), @NamedQuery(name = "Units.findByUnitManager", query = "SELECT u FROM Units u WHERE u.unitManager = :unitManager"), @NamedQuery(name = "Units.findByRemarks1", query = "SELECT u FROM Units u WHERE u.remarks1 = :remarks1"), @NamedQuery(name = "Units.findByRemarks2", query = "SELECT u FROM Units u WHERE u.remarks2 = :remarks2")})
/*     */ public class Units
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Column(name = "S_NO")
/*     */   private Integer sNo;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @Column(name = "UNIT_ID")
/*     */   private String unitId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "UNIT_NAME")
/*     */   private String unitName;
/*     */   @Column(name = "UNIT_ADDRESS")
/*     */   private String unitAddress;
/*     */   @Column(name = "UNIT_MANAGER")
/*     */   private String unitManager;
/*     */   @Column(name = "REMARKS1")
/*     */   private String remarks1;
/*     */   @Column(name = "REMARKS2")
/*     */   private String remarks2;
/*     */   
/*     */   public Units() {}
/*     */   
/*     */   public Units(String unitId) {
/*  59 */     this.unitId = unitId;
/*     */   }
/*     */   
/*     */   public Units(String unitId, String unitName) {
/*  63 */     this.unitId = unitId;
/*  64 */     this.unitName = unitName;
/*     */   }
/*     */   
/*     */   public Integer getSNo() {
/*  68 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(Integer sNo) {
/*  72 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getUnitId() {
/*  76 */     return this.unitId;
/*     */   }
/*     */   
/*     */   public void setUnitId(String unitId) {
/*  80 */     this.unitId = unitId;
/*     */   }
/*     */   
/*     */   public String getUnitName() {
/*  84 */     return this.unitName;
/*     */   }
/*     */   
/*     */   public void setUnitName(String unitName) {
/*  88 */     this.unitName = unitName;
/*     */   }
/*     */   
/*     */   public String getUnitAddress() {
/*  92 */     return this.unitAddress;
/*     */   }
/*     */   
/*     */   public void setUnitAddress(String unitAddress) {
/*  96 */     this.unitAddress = unitAddress;
/*     */   }
/*     */   
/*     */   public String getUnitManager() {
/* 100 */     return this.unitManager;
/*     */   }
/*     */   
/*     */   public void setUnitManager(String unitManager) {
/* 104 */     this.unitManager = unitManager;
/*     */   }
/*     */   
/*     */   public String getRemarks1() {
/* 108 */     return this.remarks1;
/*     */   }
/*     */   
/*     */   public void setRemarks1(String remarks1) {
/* 112 */     this.remarks1 = remarks1;
/*     */   }
/*     */   
/*     */   public String getRemarks2() {
/* 116 */     return this.remarks2;
/*     */   }
/*     */   
/*     */   public void setRemarks2(String remarks2) {
/* 120 */     this.remarks2 = remarks2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 125 */     int hash = 0;
/* 126 */     return (this.unitId != null) ? this.unitId.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 133 */     if (!(object instanceof Units)) {
/* 134 */       return false;
/*     */     }
/* 136 */     Units other = (Units)object;
/* 137 */     if ((this.unitId == null && other.unitId != null) || (this.unitId != null && !this.unitId.equals(other.unitId))) {
/* 138 */       return false;
/*     */     }
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 145 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Units[ unitId=" + this.unitId + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Units.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */