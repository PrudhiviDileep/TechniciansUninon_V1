/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
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
/*     */ @Table(name = "login")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"), @NamedQuery(name = "Login.findBySNo", query = "SELECT l FROM Login l WHERE l.sNo = :sNo"), @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"), @NamedQuery(name = "Login.findByUserName", query = "SELECT l FROM Login l WHERE l.userName = :userName")})
/*     */ public class Login
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private Integer sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "PASSWORD")
/*     */   private String password;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "USER_NAME")
/*     */   private String userName;
/*     */   
/*     */   public Login() {}
/*     */   
/*     */   public Login(Integer sNo) {
/*  51 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public Login(Integer sNo, String password, String userName) {
/*  55 */     this.sNo = sNo;
/*  56 */     this.password = password;
/*  57 */     this.userName = userName;
/*     */   }
/*     */   
/*     */   public Integer getSNo() {
/*  61 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(Integer sNo) {
/*  65 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  69 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/*  73 */     this.password = password;
/*     */   }
/*     */   
/*     */   public String getUserName() {
/*  77 */     return this.userName;
/*     */   }
/*     */   
/*     */   public void setUserName(String userName) {
/*  81 */     this.userName = userName;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  86 */     int hash = 0;
/*  87 */     return (this.sNo != null) ? this.sNo.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  94 */     if (!(object instanceof Login)) {
/*  95 */       return false;
/*     */     }
/*  97 */     Login other = (Login)object;
/*  98 */     if ((this.sNo == null && other.sNo != null) || (this.sNo != null && !this.sNo.equals(other.sNo))) {
/*  99 */       return false;
/*     */     }
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 106 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Login[ sNo=" + this.sNo + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Login.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */