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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "recommendation_details")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "RecommendationDetails.findAll", query = "SELECT r FROM RecommendationDetails r"), @NamedQuery(name = "RecommendationDetails.findByMemberId", query = "SELECT r FROM RecommendationDetails r WHERE r.memberId = :memberId"), @NamedQuery(name = "RecommendationDetails.findByRecommend1DeptId", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1DeptId = :recommend1DeptId"), @NamedQuery(name = "RecommendationDetails.findByRecommend1DeptName", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1DeptName = :recommend1DeptName"), @NamedQuery(name = "RecommendationDetails.findByRecommend1Name", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1Name = :recommend1Name"), @NamedQuery(name = "RecommendationDetails.findByRecommend1WorkingPlace", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1WorkingPlace = :recommend1WorkingPlace"), @NamedQuery(name = "RecommendationDetails.findByRecommend2DeptId", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2DeptId = :recommend2DeptId"), @NamedQuery(name = "RecommendationDetails.findByRecommend2DeptName", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2DeptName = :recommend2DeptName"), @NamedQuery(name = "RecommendationDetails.findByRecommend2Name", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2Name = :recommend2Name"), @NamedQuery(name = "RecommendationDetails.findByRecommend2WorkingPlace", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2WorkingPlace = :recommend2WorkingPlace"), @NamedQuery(name = "RecommendationDetails.findBySNo", query = "SELECT r FROM RecommendationDetails r WHERE r.sNo = :sNo"), @NamedQuery(name = "RecommendationDetails.findByRecommend1CardNo", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1CardNo = :recommend1CardNo"), @NamedQuery(name = "RecommendationDetails.findByRecommend2CardNo", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2CardNo = :recommend2CardNo"), @NamedQuery(name = "RecommendationDetails.findByRecommend1UnitId", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend1UnitId = :recommend1UnitId"), @NamedQuery(name = "RecommendationDetails.findByRecommend2UnitId", query = "SELECT r FROM RecommendationDetails r WHERE r.recommend2UnitId = :recommend2UnitId")})
/*     */ public class RecommendationDetails
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @Column(name = "MEMBER_ID")
/*     */   private String memberId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_DEPT_ID")
/*     */   private String recommend1DeptId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_DEPT_NAME")
/*     */   private String recommend1DeptName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_NAME")
/*     */   private String recommend1Name;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_WORKING_PLACE")
/*     */   private String recommend1WorkingPlace;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_DEPT_ID")
/*     */   private String recommend2DeptId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_DEPT_NAME")
/*     */   private String recommend2DeptName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_NAME")
/*     */   private String recommend2Name;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_WORKING_PLACE")
/*     */   private String recommend2WorkingPlace;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "S_NO")
/*     */   private int sNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_CARD_NO")
/*     */   private int recommend1CardNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_CARD_NO")
/*     */   private int recommend2CardNo;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND1_UNIT_ID")
/*     */   private String recommend1UnitId;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "RECOMMEND2_UNIT_ID")
/*     */   private String recommend2UnitId;
/*     */   
/*     */   public RecommendationDetails() {}
/*     */   
/*     */   public RecommendationDetails(String memberId) {
/*  92 */     this.memberId = memberId;
/*     */   }
/*     */   
/*     */   public RecommendationDetails(String memberId, String recommend1DeptId, String recommend1DeptName, String recommend1Name, String recommend1WorkingPlace, String recommend2DeptId, String recommend2DeptName, String recommend2Name, String recommend2WorkingPlace, int sNo, int recommend1CardNo, int recommend2CardNo, String recommend1UnitId, String recommend2UnitId) {
/*  96 */     this.memberId = memberId;
/*  97 */     this.recommend1DeptId = recommend1DeptId;
/*  98 */     this.recommend1DeptName = recommend1DeptName;
/*  99 */     this.recommend1Name = recommend1Name;
/* 100 */     this.recommend1WorkingPlace = recommend1WorkingPlace;
/* 101 */     this.recommend2DeptId = recommend2DeptId;
/* 102 */     this.recommend2DeptName = recommend2DeptName;
/* 103 */     this.recommend2Name = recommend2Name;
/* 104 */     this.recommend2WorkingPlace = recommend2WorkingPlace;
/* 105 */     this.sNo = sNo;
/* 106 */     this.recommend1CardNo = recommend1CardNo;
/* 107 */     this.recommend2CardNo = recommend2CardNo;
/* 108 */     this.recommend1UnitId = recommend1UnitId;
/* 109 */     this.recommend2UnitId = recommend2UnitId;
/*     */   }
/*     */   
/*     */   public String getMemberId() {
/* 113 */     return this.memberId;
/*     */   }
/*     */   
/*     */   public void setMemberId(String memberId) {
/* 117 */     this.memberId = memberId;
/*     */   }
/*     */   
/*     */   public String getRecommend1DeptId() {
/* 121 */     return this.recommend1DeptId;
/*     */   }
/*     */   
/*     */   public void setRecommend1DeptId(String recommend1DeptId) {
/* 125 */     this.recommend1DeptId = recommend1DeptId;
/*     */   }
/*     */   
/*     */   public String getRecommend1DeptName() {
/* 129 */     return this.recommend1DeptName;
/*     */   }
/*     */   
/*     */   public void setRecommend1DeptName(String recommend1DeptName) {
/* 133 */     this.recommend1DeptName = recommend1DeptName;
/*     */   }
/*     */   
/*     */   public String getRecommend1Name() {
/* 137 */     return this.recommend1Name;
/*     */   }
/*     */   
/*     */   public void setRecommend1Name(String recommend1Name) {
/* 141 */     this.recommend1Name = recommend1Name;
/*     */   }
/*     */   
/*     */   public String getRecommend1WorkingPlace() {
/* 145 */     return this.recommend1WorkingPlace;
/*     */   }
/*     */   
/*     */   public void setRecommend1WorkingPlace(String recommend1WorkingPlace) {
/* 149 */     this.recommend1WorkingPlace = recommend1WorkingPlace;
/*     */   }
/*     */   
/*     */   public String getRecommend2DeptId() {
/* 153 */     return this.recommend2DeptId;
/*     */   }
/*     */   
/*     */   public void setRecommend2DeptId(String recommend2DeptId) {
/* 157 */     this.recommend2DeptId = recommend2DeptId;
/*     */   }
/*     */   
/*     */   public String getRecommend2DeptName() {
/* 161 */     return this.recommend2DeptName;
/*     */   }
/*     */   
/*     */   public void setRecommend2DeptName(String recommend2DeptName) {
/* 165 */     this.recommend2DeptName = recommend2DeptName;
/*     */   }
/*     */   
/*     */   public String getRecommend2Name() {
/* 169 */     return this.recommend2Name;
/*     */   }
/*     */   
/*     */   public void setRecommend2Name(String recommend2Name) {
/* 173 */     this.recommend2Name = recommend2Name;
/*     */   }
/*     */   
/*     */   public String getRecommend2WorkingPlace() {
/* 177 */     return this.recommend2WorkingPlace;
/*     */   }
/*     */   
/*     */   public void setRecommend2WorkingPlace(String recommend2WorkingPlace) {
/* 181 */     this.recommend2WorkingPlace = recommend2WorkingPlace;
/*     */   }
/*     */   
/*     */   public int getSNo() {
/* 185 */     return this.sNo;
/*     */   }
/*     */   
/*     */   public void setSNo(int sNo) {
/* 189 */     this.sNo = sNo;
/*     */   }
/*     */   
/*     */   public int getRecommend1CardNo() {
/* 193 */     return this.recommend1CardNo;
/*     */   }
/*     */   
/*     */   public void setRecommend1CardNo(int recommend1CardNo) {
/* 197 */     this.recommend1CardNo = recommend1CardNo;
/*     */   }
/*     */   
/*     */   public int getRecommend2CardNo() {
/* 201 */     return this.recommend2CardNo;
/*     */   }
/*     */   
/*     */   public void setRecommend2CardNo(int recommend2CardNo) {
/* 205 */     this.recommend2CardNo = recommend2CardNo;
/*     */   }
/*     */   
/*     */   public String getRecommend1UnitId() {
/* 209 */     return this.recommend1UnitId;
/*     */   }
/*     */   
/*     */   public void setRecommend1UnitId(String recommend1UnitId) {
/* 213 */     this.recommend1UnitId = recommend1UnitId;
/*     */   }
/*     */   
/*     */   public String getRecommend2UnitId() {
/* 217 */     return this.recommend2UnitId;
/*     */   }
/*     */   
/*     */   public void setRecommend2UnitId(String recommend2UnitId) {
/* 221 */     this.recommend2UnitId = recommend2UnitId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 226 */     int hash = 0;
/* 227 */     return (this.memberId != null) ? this.memberId.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 234 */     if (!(object instanceof RecommendationDetails)) {
/* 235 */       return false;
/*     */     }
/* 237 */     RecommendationDetails other = (RecommendationDetails)object;
/* 238 */     if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
/* 239 */       return false;
/*     */     }
/* 241 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.RecommendationDetails[ memberId=" + this.memberId + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\RecommendationDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */