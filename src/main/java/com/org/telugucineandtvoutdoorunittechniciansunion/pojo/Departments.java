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
/*     */ @Entity
/*     */ @Table(name = "departments")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Departments d"), @NamedQuery(name = "Departments.findByDeptId", query = "SELECT d FROM Departments d WHERE d.deptId = :deptId"), @NamedQuery(name = "Departments.findByDeptName", query = "SELECT d FROM Departments d WHERE d.deptName = :deptName"), @NamedQuery(name = "Departments.findBySNo", query = "SELECT d FROM Departments d WHERE d.sNo = :sNo")})
/*     */ public class Departments
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DEPT_ID")
/*     */   private String deptId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DEPT_NAME")
/*     */   private String deptName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   
/*     */   public Departments() {}
/*     */   
/*     */   public Departments(String deptId) {
/*  48 */     this.deptId = deptId;
/*     */   }
/*     */   
/*     */   public Departments(String deptId, String deptName, int sNo) {
/*  52 */     this.deptId = deptId;
/*  53 */     this.deptName = deptName;
/*  54 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getDeptId() {
/*  58 */     return this.deptId;
/*     */   }
/*     */   
/*     */   public void setDeptId(String deptId) {
/*  62 */     this.deptId = deptId;
/*     */   }
/*     */   
/*     */   public String getDeptName() {
/*  66 */     return this.deptName;
/*     */   }
/*     */   
/*     */   public void setDeptName(String deptName) {
/*  70 */     this.deptName = deptName;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/*  74 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/*  78 */     this.sNo = sNo;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  83 */     int hash = 0;
/*  84 */     return (this.deptId != null) ? this.deptId.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  91 */     if (!(object instanceof Departments)) {
/*  92 */       return false;
/*     */     }
/*  94 */     Departments other = (Departments)object;
/*  95 */     if ((this.deptId == null && other.deptId != null) || (this.deptId != null && !this.deptId.equals(other.deptId))) {
/*  96 */       return false;
/*     */     }
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Departments[ deptId=" + this.deptId + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Departments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */